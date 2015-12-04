"use strict"
/**
 * Created by s-wada on 2015/11/27.
 */
$(function () {

    /**
     * ヘッダのメタタグからcsrfトークンを取得
     */
    var csrfHeader = function () {
        var header = {};
        header[csrfHeader.csrfHeader] = csrfHeader.csrfToken;
        return header;
    };
    csrfHeader.csrfHeader = $('meta[name=_csrf_header]').attr('content');
    csrfHeader.csrfToken = $('meta[name=_csrf]').attr('content');

    /**
     * WebSocketエンドポイント
     * @type {string}
     */
    var endpoint = "/wsendpoint";

    // TODO Stomp接続でデータをやり取りするパーツをスーパクラスとして実装し、chatroomで継承するように。
    var _StompClient = function () {
    };
    _StompClient.prototype = {
        receive: function (message) {
        },
        send: function (message) {
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
        this.topic = "/topic/stduser/chat/" + this.roomId;
        this.$view = $('<textarea data-viewer name="viewer" cols="30" rows="10" readonly=""></textarea>');
        this.$input = $('<input data-input type="text" name="message"/>');
        this.$button = $('<button data-button>送信</button>');
        $widget
            .append(this.$view)
            .append(this.$input)
            .append(this.$button);
        this.$button.on('click', this.send.bind(this));
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
                "/app/stduser/chat/" + this.roomId,
                csrfHeader(),
                JSON.stringify({content: this.$input.val()})
            );
            this.$input.val('');
            return false;
        },
        receive: function (msg) {
            var message = JSON.parse(msg.body);
            if (message.success) {
                this.$view.val(this.$view.val() + "\r\n" + message.body.content);
            } else {
                alert(message.body.content);
            }
        }
    };

    var ExceptionListner = function(){
        this.connection = null;
        this.topic = "/user/queue/error";
    };
    ExceptionListner.prototype = {
        /**
         * @param {StompConnection} con
         */
        connect: function (con) {
            con.register(this);
            this.connection = con;
        },
        receive: function (msg) {
            var message = JSON.parse(msg.body);
            console.error(message);
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
         * コネクションはconnectメソッドに対して非同期で張られるので、
         * 直後にはsubscribe出来ない。
         * コネクションが確立するまえにsubscribeしようとしたwidgetをストックして、
         * コネクトコールバックの中でsubscribeする仕組みにしている。
         * 以下は、コネクション確立までウィジェットを格納しておくための配列。
         * @type {Array.<_StompClient>}
         */
        this.preConnect = []
    };
    StompConnection.prototype = {
        /**
         * 指定したサーバのwebsocketエンドポイントに接続します。
         * @param {string} endPoint
         */
        connect: function (endPoint) {
            var socket = new SockJS(endPoint);
            this.client = Stomp.over(socket);
            this.client.connect(csrfHeader(), this._onConnect.bind(this));
        },

        /**
         * サーバとのwebsocketコネクションを切断します。
         */
        disconnect: function () {
        },

        /**
         * このコネクションに指定のクライアントオブジェクトを登録します（subscribe出来るようにする）。
         * @param {_StompClient} stompClient
         */
        register: function (stompClient) {
            if (this.client.connected) {
                stompClient.connectionId
                    = this.client.subscribe(stompClient.topic, stompClient.receive.bind(stompClient), csrfHeader());
            } else {
                this.preConnect.push(stompClient);
            }
        },

        /**
         * このコネクションから指定のクライアントオブジェクトを切り離します。
         * @param {_StompClient} stompClient
         */
        unregister: function (stompClient) {
            this.client.disconnect(stompClient.connectionId);
        },

        /**
         * コネクション完了時に、保留にしていたオブジェクトをsubscribeさせます。
         * @private
         */
        _onConnect: function () {
            this.preConnect.forEach(function (stompClient) {
                this.register(stompClient);
            }, this);
        }
    };

    var stompConnection = new StompConnection();
    stompConnection.connect(endpoint);
    rooms.forEach(function (room) {
        room.connect(stompConnection);
    });
    var listner = new ExceptionListner();
    listner.connect(stompConnection);
});