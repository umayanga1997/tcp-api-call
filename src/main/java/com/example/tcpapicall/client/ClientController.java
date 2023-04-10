package com.example.tcpapicall.client;

import com.example.tcpapicall.models.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/api/socket/data")
    public OutgoingMessage getSocketResponse(@RequestParam(name = "name") String name) throws ExecutionException, InterruptedException {
        return clientService.getData(name);

    }
}
