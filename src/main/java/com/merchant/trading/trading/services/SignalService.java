package com.merchant.trading.trading.services;

import com.merchant.trading.trading.OperationTypes;
import com.merchant.trading.trading.configurations.ConfigProperties;
import com.merchant.trading.trading.thirdparty.Algo;
import com.merchant.trading.trading.thirdparty.SignalHandler;
import com.merchant.trading.trading.utils.TradingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignalService implements SignalHandler {
    private final String SET_ALGO_PARAM = "SET_ALGO_PARAM";
    @Autowired
    ConfigProperties config;
    @Override
    public void handleSignal(int signal) {
        Algo algo = new Algo();

        List<String> operationList = config.getSignal().getOrDefault(signal, new ArrayList<>());
        if(operationList.isEmpty()) {
            algo.cancelTrades();
        }
        for(String op : operationList) {
            int[] oprnd = new int[2];
            //Special processing for SET_ALGO_PARAM, as it has operands as well
            if(op.contains(SET_ALGO_PARAM)) {
                StringBuilder sb = new StringBuilder();
                oprnd = TradingUtils.extractParam(op, sb);
                op = sb.toString();
                if(oprnd.length == 0) continue;
            }
            switch(OperationTypes.valueOf(op)) {
                case DO_ALGO :
                    algo.doAlgo();
                    break;
                case SETUP:
                    algo.setUp();
                    break;
                case REVERSE:
                    algo.reverse();
                    break;
                case PERFORM_CALC:
                    algo.performCalc();
                    break;
                case CANCEL_TRADES:
                    algo.cancelTrades();
                    break;
                case SET_ALGO_PARAM:
                    algo.setAlgoParam(oprnd[0], oprnd[1]);
                    break;
                case SUBMIT_TO_MARKET:
                    algo.submitToMarket();
                    break;
                default :
                    algo.cancelTrades();
                    break;
            }
        }
    }

    public HttpStatus processSignal(int signal) {
        try {
            handleSignal(signal);
            return HttpStatus.OK;
        } catch(Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
