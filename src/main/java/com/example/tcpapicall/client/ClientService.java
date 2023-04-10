package com.example.tcpapicall.client;

import com.example.tcpapicall.models.IncomingMessage;
import com.example.tcpapicall.models.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    @Autowired
    ClientSessionHandler clientSessionHandler;

    public OutgoingMessage getData(String name) throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:8080/websocket-server", clientSessionHandler);
        StompSession session = sessionAsync.get();
        session.subscribe("/topic/messages", clientSessionHandler);
        session.send("/app/process-message", new IncomingMessage(name));

        // Response values always be taken in handleFrame() function of  ClientSessionHandler() class
        // Return Null Object
        OutgoingMessage message = new OutgoingMessage();

        return message;
    }
}
