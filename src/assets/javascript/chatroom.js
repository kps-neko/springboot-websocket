"use strict"
/**
 * Created by s-wada on 2015/11/27.
 */
$(function () {
    var _StompClient = function () {
    };
    _StompClient.prototype = {
        receive: function (message) {
        },
        connectionId: null
    };

    /**
     * STOMPクライアント用チャットルームウィジェット
     * @param {jQuery} $widget
     * @property {jQuery} $form
     * @property {jQuery} $view
     * @property {jQuery} $input
     * @property {jQuery} $button
     * @constructor
     */
    var ChatRoom = function ($widget) {
        this.roomId = $widget.data('chatroomId');
        this.topic = "/topic/chat/" + this.roomId;
        this.$form = $('<form>');
        this.$view = $('<textarea data-viewer name="viewer" cols="30" rows="10" readonly=""></textarea>');
        this.$input = $('<input data-input type="text" name="message"/>');
        this.$buton = $('<button data-button>送信</button>');
        this.$form
            .append(this.$view)
            .append(this.$input)
            .append(this.$buton);
        $widget.append(this.$form);
        this.$form.on('submit', this.send.bind(this));
    };
    ChatRoom.prototype = {
        /**
         * @param {StompConnection} con
         */
        connect: function (con) {
            con.register(this);
            this.connection = con;
        },
        send: function () {
            this.connection.client.send(
                "/app/chat/" + this.roomId,
                {},
                JSON.stringify({name: this.$input.val()})
            );
            this.$input.val('');
            return false;
        },
        receive: function (msg) {
            var message = JSON.parse(msg.body);
            this.$view.val(this.$view.val() + "\r\n" + message.name);
        }
    };

    /**
     * @type {Array.<ChatRoom>}
     */
    var rooms = $('[data-chatroom-id]').get().map(function (elem, idx) {
        return new ChatRoom($(elem));
    });

    var StompConnection = function () {
        this.client = null;
        /**
         * @type {Array.<_StompClient>}
         */
        this.preConnect = []
    };
    StompConnection.prototype = {
        connect: function (endPoint) {
            var socket = new SockJS(endPoint);
            this.client = Stomp.over(socket);
            this.client.connect({}, this._onConnect.bind(this));
        },
        disconnect: function () {
        },
        /**
         * @param {_StompClient} stompClient
         */
        register: function (stompClient) {
            if (this.client.connected) {
                stompClient.connectionId
                    = this.client.subscribe(stompClient.topic, stompClient.receive.bind(stompClient));
            } else {
                this.preConnect.push(stompClient);
            }
        },
        unregister: function (stompClient) {
            this.client.disconnect(stompClient.connectionId);
        },
        _onConnect: function () {
            this.preConnect.forEach(function (stompClient) {
                this.register(stompClient);
            }, this);
        }
    };

    var stompConnection = new StompConnection();
    stompConnection.connect('/hello');
    rooms.forEach(function (room) {
        room.connect(stompConnection);
    });
});