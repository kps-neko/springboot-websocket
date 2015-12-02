/**
 * Created by s-wada on 2015/11/27.
 */
var stompClient = null;

var csrfHeader = (function () {
    var csrfHeader = $('meta[name=_csrf_header]').attr('content');
    var csrfToken = $('meta[name=_csrf]').attr('content');
    var header = {};
    header[csrfHeader] = csrfToken;
    return header;
}());

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/wsendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect(csrfHeader, function () {
        setConnected(true);
        console.log('Connected: ');
        stompClient.subscribe('/user/queue/stduser/hello', function (greeting) {
            console.log('receive message: ' + greeting);
            showGreeting(JSON.parse(greeting.body).content);
        })
    }, function (error) {
        alert(error);
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var content = document.getElementById('name').value;
    stompClient.send("/app/stduser/hello", csrfHeader, JSON.stringify({'content': content}));
}

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}