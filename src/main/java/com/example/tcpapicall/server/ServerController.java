package com.example.tcpapicall.server;

import com.example.tcpapicall.models.IncomingMessage;
import com.example.tcpapicall.models.OutgoingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ServerController {
    @MessageMapping("/process-message")
    @SendTo("/topic/messages")
    public OutgoingMessage processMessage(IncomingMessage incomingMessage)  {
        return new OutgoingMessage("Hello :: "+ incomingMessage.getName());
    }
}
