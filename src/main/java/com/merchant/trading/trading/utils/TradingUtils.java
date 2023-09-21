package com.merchant.trading.trading.utils;

public class TradingUtils {
    public static int[] extractParam(String op, StringBuilder sb) {
        try {
            int oprnd1 = 0;
            int oprnd2 = 0;
            int idx1 = op.indexOf("$");
            int idx2 = op.indexOf("$", idx1 + 1);
            String operation = op.substring(0, idx1);
            String val1 = op.substring(idx1 + 1, idx2);
            String val2 = op.substring(idx2 + 1);
            sb.append(operation);
            oprnd1 = Integer.valueOf(val1);
            oprnd2 = Integer.valueOf(val2);
            return new int[] {oprnd1, oprnd2};
        } catch(Exception e) {
            return new int[]{};
        }
    }
}
