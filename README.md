# Trading Application
## Endpoint
Get
/tradingApi/signal/{signal} 
Details are there in swagger.yaml 
NOTE : I have defined this as a get endpoint, possibly can be a post if required.
May be that is the suitable one but as there does not make much difference as of the present requirement, so keeping it as get
## Problem statement
The trading application will be able to process one of these
signals (a simple integer). Each signal specification is given in the form of a JIRA
from your analysts. The role of your team is to implement the new signal specifications and
release to production as quickly as possible.

Once the application is in production, it is
expected that up to 50 new signals will be added per month (600 after year one, 1200 after
year two etc.). The code should be a running service with a single http endpoint for receiving the ‘signal’
which will then be passed through the ‘SignalHandler’ interface and onto your application.

## Requirement
1. There will be an endpoint which will accept an integer signal
2. The chain of actions for this signal will be defined by the analyst team - which will be a subset of the task defined in the third party library Algo class
3. A new signal specification can easily be added and can quickly be deployed in production
## Approach
There could be many solutions of a specific problem. I have tried to make the code as configurable as possible.
As there is a possibility to have many signal specification will be added in a month i.e. 50 and 600 in a year, so our code should be configurable to get as minimal code build and deployment possible and get this new signal specification done through configuration.
This is the motivation of my approach, to keep the signal specification in configuration file and decide the actions based on that.
## Application Property
The configuration is defined a Map of <Key, Value>, where signal is the key and the values will be the set of actions allowed
i.e. tradingapp.properties.signal.2=REVERSE, SET_ALGO_PARAM$1$80, SUBMIT_TO_MARKET
Here for signal 2 we have actions 1. reverse 2. set algo param and 3. submit to market
For Set algo param we have two argument which is identified with separator '$'. For the given example the two arguments are 1 and 80.
## Implementation Details
We have an enum defined for the actions that will map the config values
```aidl
enum OperationTypes {
DO_ALGO,
CANCEL_TRADES,
REVERSE,
SUBMIT_TO_MARKET,
PERFORM_CALC,
SETUP,
SET_ALGO_PARAM
}
```

and the following code snipet will be called for the set of actions for a specific signal
```aidl
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
```
## Response
Response class look as following
```aidl
public class Response {
    int status;
    String message;
}
```
