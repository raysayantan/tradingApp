package com.merchant.trading.trading.services;

import com.merchant.trading.trading.configurations.ConfigProperties;
import com.merchant.trading.trading.thirdparty.SignalHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class SignalServiceTest {
    @Autowired
    private SignalHandler signalHandler;
    @Autowired
    private ConfigProperties configProperties;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testExtractParamSuccessSignal_1() {
        signalHandler.handleSignal(1);
        Assertions.assertEquals("setUp\n" +
                "setAlgoParam 1,60\n" +
                "performCalc\n" +
                "submitToMarket\n", outContent.toString());
    }

    @Test
    public void testExtractParamSuccessSignal_2() {
        signalHandler.handleSignal(2);
        Assertions.assertEquals("reverse\n" +
                "setAlgoParam 1,80\n" +
                "submitToMarket\n", outContent.toString());
    }

    @Test
    public void testExtractParamSuccessSignal_3() {
        signalHandler.handleSignal(3);
        Assertions.assertEquals("setAlgoParam 1,90\n" +
                "setAlgoParam 2,15\n" +
                "performCalc\n" +
                "submitToMarket\n", outContent.toString());
    }

}