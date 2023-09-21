package com.merchant.trading.trading.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TradingUtilsTest {
    @Test
    public void testExtractParamSuccess() {
        String op = "ET_ALGO_PARAM$1$80";
        int[] ret = TradingUtils.extractParam(op);
        Assertions.assertEquals(2, ret.length);
        Assertions.assertEquals(1, ret[0]);
        Assertions.assertEquals(80, ret[1]);
    }

    @Test
    public void testExtractParamError() {
        String op = "ET_ALGO_PARAM$1";
        int[] ret = TradingUtils.extractParam(op);
        Assertions.assertEquals(0, ret.length);
    }

    @Test
    public void testExtractParamError1() {
        String op = "ET_ALGO_PARAM";
        int[] ret = TradingUtils.extractParam(op);
        Assertions.assertEquals(0, ret.length);
    }
}