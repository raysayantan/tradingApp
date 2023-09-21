package com.merchant.trading.trading.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TradingUtilsTest {
    @Test
    public void testExtractParamSuccess() {
        String op = "SET_ALGO_PARAM$1$80";
        StringBuilder sb = new StringBuilder();
        int[] ret = TradingUtils.extractParam(op, sb);
        Assertions.assertEquals(2, ret.length);
        Assertions.assertEquals(1, ret[0]);
        Assertions.assertEquals(80, ret[1]);
        Assertions.assertEquals("SET_ALGO_PARAM", sb.toString());
    }

    @Test
    public void testExtractParamError() {
        String op = "SET_ALGO_PARAM$1";
        int[] ret = TradingUtils.extractParam(op, new StringBuilder());
        Assertions.assertEquals(0, ret.length);
    }

    @Test
    public void testExtractParamError1() {
        String op = "SET_ALGO_PARAM";
        int[] ret = TradingUtils.extractParam(op, new StringBuilder());
        Assertions.assertEquals(0, ret.length);
    }
}