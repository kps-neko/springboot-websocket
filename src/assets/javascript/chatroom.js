/**
 * Created by s-wada on 2015/11/27.
 */
$(function() {
    var socket;
    var stompClient;
    var subscriber = [];

    socket= new SockJS('/hello');
    stompClient = Stomp.over(socket);

    var send = function(id, msg){
        stompClient.send("/app/chat/" + id, {}, JSON.stringify({name:msg}));
    };

    $('[data-chatroom-id]').each(function(idx, room) {
        var $room = $(this);
        var roomId = $room.data('chatroomId');
        var $textArea = $room.find("[data-viewer]");
        var $input = $room.find('[data-input]');
        var $button = $room.find('[data-button]');
        $button.on('click', function(){
            var val = $input.val();
            $input.val('');
            send(roomId, val);
        });
        var f = function(){
                stompClient.subscribe('/topic/chat/' + roomId , function(msg){
                    var message = JSON.parse(msg.body);
                    $textArea.val($textArea.val() + "\r\n" + message.name);
                });
            };
        subscriber.push( f );
    });

    stompClient.connect({}, function(){
        subscriber.forEach(function(f){
            f();
        });
    })
});