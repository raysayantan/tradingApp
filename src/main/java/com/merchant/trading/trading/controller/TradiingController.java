package com.merchant.trading.trading.controller;

import com.merchant.trading.trading.model.Response;
import com.merchant.trading.trading.thirdparty.SignalHandler;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tradingApi")
public class TradiingController {
    @GetMapping("/signal/{signal}")
    public ResponseEntity<Response> getSignal(@PathVariable int signal) {
        System.out.println(signal);
        return new ResponseEntity(Response.builder().build(), HttpStatus.OK);
    }
}
