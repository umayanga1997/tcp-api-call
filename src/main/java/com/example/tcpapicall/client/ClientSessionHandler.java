package com.example.tcpapicall.client;

import com.example.tcpapicall.models.OutgoingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Slf4j
@Service
public class ClientSessionHandler extends StompSessionHandlerAdapter {

    private OutgoingMessage data;

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutgoingMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        setData((OutgoingMessage) payload);
        log.info("Payload :: " + this.data.getContent());
    }

    public void setData(OutgoingMessage data){
        this.data = data;
    }
    public OutgoingMessage getData(){
        return this.data;
    }

}
