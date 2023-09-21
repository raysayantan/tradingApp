package com.merchant.trading.trading.controller;

import com.merchant.trading.trading.model.Response;
import com.merchant.trading.trading.services.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tradingApi")
public class TradingController {

    private final SignalService service;

    @Autowired
    public TradingController(SignalService service) {
        this.service = service;
    }

    @GetMapping("/signal/{signal}")
    public ResponseEntity<Response> getSignal(@PathVariable int signal) {
        service.handleSignal(signal);
        return new ResponseEntity(Response.builder()
                .status(HttpStatus.OK.value())
                .message("Success")
                .build(),
                HttpStatus.OK);
    }
}
