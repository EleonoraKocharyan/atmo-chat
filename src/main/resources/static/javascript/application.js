$(function () {
    "use strict";

    var header = $('#header');
    var rooms = $('#rooms');
    var content = $('#content');
    var users = $('#users');
    var input = $('#input');
    var status = $('#status');
    var myName = false;
    var author = null;
    var logged = false;
    var socket = atmosphere;
    var subSocket;
    var transport = 'websocket';
    var fallbackTransport = 'long-polling'
    var connected = false;
    var uuid = 0;

    header.html($('<h3>', { text: 'Atmosphere Chat. Default transport is ' + transport + ', fallback is ' + fallbackTransport }));
    status.text('Chatroom ID:');
    input.removeAttr('disabled').focus();

    input.keydown(function (e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();

            $(this).val('');
            if (!connected) {
                connected = true;
                connect(msg);
                return;
            }

            // First message received is always the author's name
            if (author == null) {
                author = msg;
            }

            input.removeAttr('disabled').focus();
            // Private message
            if (msg.indexOf(":") !== -1) {
                var a = msg.split(":")[0];
                subSocket.push(atmosphere.util.stringifyJSON({ user: a, message: msg}));
            } else {
                subSocket.push(atmosphere.util.stringifyJSON({author: author, message: msg}));
            }

            if (myName === false) {
                myName = msg;
            }
        }
    });

    function connect(chatroom) {
        // We are now ready to cut the request
        var request = {
            url: document.location.toString() + 'chat/' + chatroom,
            contentType: "application/json",
            logLevel: 'debug',
            transport: transport,
            trackMessageLength: true,
            enableProtocol: true,
            reconnectInterval: 5000,
            fallbackTransport: fallbackTransport};

        request.onOpen = function (response) {
            content.html($('<p>', { text: 'Atmosphere connected using ' + response.transport }));
            status.text('User ID:');
            input.removeAttr('disabled').focus();
            transport = response.transport;
            uuid = response.request.uuid;
        };

        request.onReopen = function (response) {
            content.html($('<p>', { text: 'Atmosphere re-connected using ' + response.transport }));
            input.removeAttr('disabled').focus();
        };

        request.reconnectOnServerError = false;

        request.onMessage = function (response) {

            var message = response.responseBody;

            if (status.text() === 'Chatroom ID:') {
                status.text('User ID:');
            }
            try {
                var json = atmosphere.util.parseJSON(message);

            } catch (e) {
                console.log('This doesn\'t look like a valid JSON: ', message);
                message=null;
                return;
            }

            input.removeAttr('disabled').focus();
            if (json.rooms) {
                rooms.html($('<h2>', { text: 'Current room: ' + chatroom}));

                var r = 'Available rooms: ';
                for (var i = 0; i < json.rooms.length; i++) {
                    r += json.rooms[i] + "  ";
                }
                rooms.append($('<h3>', { text: r }))
            }

            if (json.users) {
                var r = 'Connected users: ';
                Object.keys(json.users).forEach(function (key) {
                    r += json.users[key] + " ";
                });
                // for (var i = 0; i < json.users.length; i++) {
                //     r += json.users[i] + "  ";
                // }
                users.html($('<h3>', { text: r }))
            }


            if (json.author) {
                if (!logged && myName) {
                    logged = true;
                    status.text(json.users[myName] + ': ').css('color', 'blue');
                } else {
                    var me = json.author == author;
                    var date = typeof(json.time) == 'string' ? parseInt(json.time) : json.time;
                    addMessage(json.users[json.author], json.message, me ? 'blue' : 'black', new Date(date));
                }
            }
        };

        request.onClose = function (response) {
            subSocket.push(atmosphere.util.stringifyJSON({ author: author, message: 'disconnecting' }));
        };

        request.onError = function (response) {
            content.html($('<p>', { text: 'Sorry, but there\'s some problem with your '
                + 'socket or the server is down' }));
            // aut
            logged = false;
        };

        request.onReconnect = function (request, response) {
            content.html($('<p>', { text: 'Connection lost, trying to reconnect. Trying to reconnect ' + request.reconnectInterval}));
            input.attr('disabled', 'disabled');
        };

        subSocket = socket.subscribe(request);
    }

    function addMessage(author, message, color, datetime) {
        content.append('<p><span style="color:' + color + '">' + author + '</span> @ ' + +(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
            + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())
            + ': ' + message + '</p>');
    }
});