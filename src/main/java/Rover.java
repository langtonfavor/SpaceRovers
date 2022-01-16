import java.util.Stack;

public class Rover {
    private   int xXis ;
    private   int yXis ;
    private   String direction="";
    private String validDirections = "NSEW";
    private String  validCommands = "LRM";

    public Rover(int xXis, int yXis) {
        this.xXis = xXis;
        this.yXis = yXis;
    }

    public Rover() {
    }

    private void move(){
        switch (direction) {
            case "N":
                // northDirection
                ++yXis ;
                break;
            case "E":
                //eastDirection
                ++xXis;
                break;
            case "S":
                //southDirection
                --yXis;
                break;
            case "W":
               // westDirection
                --xXis ;
                break;
        }
    }
    private void spin(String d) {
        direction = ( (validDirections.contains(d)) || validCommands.contains(d)) ? d : direction;

    }
    private  boolean IsInteger(String theValue)
    {
        try
        {
            Integer.parseInt(theValue);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    private void executeSpinMove(String c) {

        switch (c) {
            case "L":
                //leftCommand
                switch (direction) {
                    case "N":
                        //westDirection
                        spin("W");
                        break;
                    case "W":
                        //southDirection
                        spin("S");
                        break;
                    case "S":
                        //eastDirection
                        spin("E");
                        break;
                    case "E":
                        //northDirection
                        spin("N");
                        break;
                }
                break;
            case "R":
                // rightCommand
                switch (direction) {
                    case "N":
                        //eastDirection
                        spin("E");
                        break;
                    case "E":
                        //southDirection
                        spin("S");
                        break;
                    case "S":
                        //westDirection
                        spin("W");
                        break;
                    case "W":
                       //northDirection
                        spin("N");
                        break;
                }
                break;
            case "M":
              //moveCommand
                move();
                break;
        }
    }
    private String sanitizeMoves(String str){
        return str.replaceAll(".(?=.)", "$0 ");
    }
    public  void processCommand(String c) {
        String aTok;
        String aCmd;
        boolean b;
        if(c.split(" ").length<2)
            c=sanitizeMoves(c);

        Stack<String> items = new Stack<>();
        String[] toks = c.split(" ");
        for (String tok : toks) {
            aTok = tok;

            if (aTok.length() > 1) {
                for (int j = 0; j < aTok.length(); j++) {
                    aCmd = aTok.substring(j, 1);

                    executeSpinMove(aCmd);
                }
            } else {
                b = IsInteger(aTok);

                if (b) {
                    items.push(aTok);

                    if (items.size() == 2) {
                        yXis = Integer.parseInt(items.pop());
                        xXis = Integer.parseInt(items.pop());
                    }
                } else if (validDirections.contains(aTok)) {
                    direction = aTok;

                } else if (validCommands.contains(aTok)) {

                    executeSpinMove(aTok);
                }
            }
        }
        //return publish_values();
    }

    @Override
    public String toString() {
         return xXis +" " + yXis +" " + direction ;
    }
}
