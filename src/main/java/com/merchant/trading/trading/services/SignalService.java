package com.merchant.trading.trading.services;

import com.merchant.trading.trading.configurations.ConfigProperties;
import com.merchant.trading.trading.thirdparty.Algo;
import com.merchant.trading.trading.thirdparty.SignalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignalService implements SignalHandler {
    private final String SET_ALGO_PARAM = "SET_ALGO_PARAM";
    enum OperationTypes {
        DO_ALGO,
        CANCEL_TRADES,
        REVERSE,
        SUBMIT_TO_MARKET,
        PERFORM_CALC,
        SETUP,
        SET_ALGO_PARAM
    }
    @Autowired
    ConfigProperties config;
    @Override
    public void handleSignal(int signal) {
        Algo algo = new Algo();
        //TBD : Implement signal processing
        List<String> operationList = config.getSignal().getOrDefault(signal, new ArrayList<>());
        for(String op : operationList) {
            int oprnd1 = 0;
            int oprnd2 = 0;
            //Special processing for SET_ALGO_PARAM, as it has operands as well
            if(op.contains(SET_ALGO_PARAM)) {
                try {
                    int idx1 = op.indexOf("$");
                    int idx2 = op.indexOf("$", idx1 + 1);
                    String operation = op.substring(0, idx1);
                    String val1 = op.substring(idx1 + 1, idx2);
                    String val2 = op.substring(idx2 + 1);
                    op = operation;
                    oprnd1 = Integer.valueOf(val1);
                    oprnd2 = Integer.valueOf(val2);
                } catch(Exception e) {
                    return;
                }
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
                    algo.setAlgoParam(oprnd1, oprnd2);
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
}
