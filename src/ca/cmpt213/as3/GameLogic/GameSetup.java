package ca.cmpt213.as3.GameLogic;

public class GameSetup {

    public static int setTokimons(String numToki){
        //default
        int numTokimons = 10;
        if(numToki!=null && !numToki.isEmpty()) {
            if(Integer.parseInt(numToki) >=5) {
                numTokimons = Integer.parseInt(numToki);
            } else {
                System.out.println("Number Tokimons is less than 5, " +
                        "default number of Tokimons have been set");
            }
        }
        return numTokimons;
    }

    public static int setFokimons(String numFoki){
        //default
        int numFokimons = 5;
        if(numFoki!=null && !numFoki.isEmpty()) {
            if(Integer.parseInt(numFoki) >=5) {
                numFokimons = Integer.parseInt(numFoki);
            } else {
                System.out.println("input for number Fokimons is less than 5," +
                        "default number of Fokimons have been set");
            }
        }
        return numFokimons;
    }
}
