import java.util.Random;

public class Game {
    private boolean isGameOver;
    private CircularLinkedList<BoardSpace> spaces;
    private CircularLinkedList<Player> playerTurnOrder;
    private Link<Player> currentPlayer;
    private Random random = new Random();

    /*Andrew*/
    public void next() {
        currentPlayer = currentPlayer.next;
        playerTurn(currentPlayer.data, null, 0);

        Link<Player> currentPlayerCheck = currentPlayer;

        checkIfBankrupt(currentPlayerCheck.data);
        currentPlayerCheck = currentPlayerCheck.next;

        while(currentPlayerCheck != currentPlayer) {
            checkIfBankrupt(currentPlayerCheck.data);
            currentPlayerCheck = currentPlayerCheck.next;
        }

        checkGameOver();
    }

    private void playerTurn(Player player, int[] inputDiceRoll, int numOfDoubles) {
        int[] diceRoll;

        if (inputDiceRoll == null) {
            System.out.println("" + player.getName() + "'s Turn | Current Money: " + player.getMoney());
            diceRoll = rollDice();
        }
        else {
            diceRoll = inputDiceRoll;
        }

        if (player.getTurnsLeftInJail() > 0) {
            player.setTurnsLeftInJail(player.getTurnsLeftInJail() - 1);

            System.out.println("" + player.getName() + ", you rolled a " + diceRoll[0] + " and a " + diceRoll[1]);

            if (diceRoll[0] == diceRoll[1]) {
                System.out.println("It's a double! You are free from jail. Move forward immediately with this roll.");
                //ADD TURN STUFF HERE
                return;
            }
            else if (player.getTurnsLeftInJail() == 0) {
                System.out.println("After 3 turns in jail, you are free. Please pay $50. Start your normal turn.");
                player.setMoney(player.getMoney() - 50);
                playerTurn(player);
                return;
            }
            else {
                System.out.println("Would you like to pay $50 to leave jail early?");
                boolean choice = earlyJailLeaveInput();
                if (choice) {
                    System.out.println("You paid $50. You are free. Start your normal turn.");
                    player.setMoney(player.getMoney() - 50);
                    player.setTurnsLeftInJail(0);
                    playerTurn(player);
                    return;
                }
            }
            System.out.println("You have " + player.getTurnsLeftInJail() + " turns left in jail.");
            return;
        }

        int[] diceRoll = rollDice();
        for (int i=0; i<(diceRoll[0]+diceRoll[1]); i++) {
            if (player.getLocation().data.getName().equals("GO")) {
                System.out.println("You passed GO! Collect $200.");
                player.setMoney(player.getMoney() + 200);
            }
            player.setLocation(player.getLocation().next);
        }
        System.out.println("You landed on " + player.getLocation().data.getName());

        if (player.getLocation().data.getType().equals("Property")) {
            propertyLand(player);
        }
    }

    /*Andrew*/
    private void propertyLand(Player player) {
    }

    /*Andrew*/
    private void checkIfBankrupt(Player player) {
        if (player.getMoney() <= 0) {
            playerTurnOrder.delete(player);

            for (BoardSpace property : player.getProperties()) {
                spaces.replace(property, new BoardSpace(property.getName(), "Bankrupt Property"));
            }
        }
    }

    /*Andrew*/
    private void checkGameOver() {
        if (playerTurnOrder.getFirst() == playerTurnOrder.getFirst().next) {
            System.out.print("" + playerTurnOrder.getFirst().data.getName() + " wins!");
            isGameOver = true;
        }
    }

    /*Andrew*/
    private int[] rollDice() {
        int firstVal = (random.nextInt( 6) + 1);
        int secondVal = (random.nextInt( 6) + 1);

        int[] values = new int[2];
        values[0] = firstVal;
        values[1] = secondVal;

        return values;
    }

    /*Andrew*/
    private void printBoard() {
        System.out.println("--------------------------------------------------------------------------------");

        //TopRow
        String topRowString = "| ";
        topRowString += colorSpaceString("JAIL", "[JAIL]");
        topRowString += " ";
        topRowString += colorSpaceString("C1", "[ C1 ]");
        topRowString += " ";
        topRowString += colorSpaceString("EC", "[ EC ]");
        topRowString += " ";
        topRowString += colorSpaceString("C2", "[ C2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("C3", "[ C3 ]");
        topRowString += " ";
        topRowString += colorSpaceString("R2", "[ R2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("D1", "[ D1 ]");
        topRowString += " ";
        topRowString += colorSpaceString("CC_TopRow", "[ CC ]");
        topRowString += " ";
        topRowString += colorSpaceString("D2", "[ D2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("D3", "[ D3 ]");
        topRowString += " ";
        topRowString += colorSpaceString("FrPk", "[FrPk]");
        topRowString += " |";
        System.out.println(topRowString);

        //Middle Rows
        System.out.println("| " + colorSpaceString("B3", "[ B3 ]") +
                " -------------------------------------------------------------- " +
                colorSpaceString("E1", "[ E1 ]") + " |");

        System.out.println("| " + colorSpaceString("B2", "[ B2 ]") +
                " |                                                            | " +
                colorSpaceString("CH_RightCol", "[ CH ]") + " |");

        System.out.println("| " + colorSpaceString("CH_LeftCol", "[ CH ]") +
                " |                                                            | " +
                colorSpaceString("E2", "[ E2 ]") + " |");

        System.out.println("| " + colorSpaceString("B1", "[ B1 ]") +
                " |                                                            | " +
                colorSpaceString("E3", "[ E3 ]") + " |");

        System.out.println("| " + colorSpaceString("R1", "[ R1 ]") +
                " |                      M O N O P O L Y                       | " +
                colorSpaceString("R3", "[ R3 ]") + " |");

        System.out.println("| " + colorSpaceString("InTx", "[InTx]") +
                " |                                                            | " +
                colorSpaceString("F1", "[ F1 ]") + " |");

        System.out.println("| " + colorSpaceString("A2", "[ A2 ]") +
                " |                                                            | " +
                colorSpaceString("F2", "[ F2 ]") + " |");

        System.out.println("| " + colorSpaceString("CC_LeftCol", "[ CC ]") +
                " |                                                            | " +
                colorSpaceString("WW", "[ WW ]") + " |");

        System.out.println("| " + colorSpaceString("A1", "[ A1 ]") +
                " -------------------------------------------------------------- " +
                colorSpaceString("F3", "[ F3 ]") + " |");

        //TopRow
        String bottomRowString = "| ";
        bottomRowString += colorSpaceString("GO", "[ GO ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("H2", "[ H2 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("LuTx", "[LuTx]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("H1", "[ H1 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("CH_bottomRow", "[ CH ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("R4", "[ R4 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("G3", "[ G3 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("CC_bottomRow", "[ CC ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("G2", "[ G2 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("G1", "[ G1 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("ToJL", "[ToJL]");
        bottomRowString += " |";
        System.out.println(bottomRowString);

        System.out.println("--------------------------------------------------------------------------------");
    }

    /*Andrew*/
    private String colorSpaceString(String spaceName, String uncoloredOutput) {
        String RESET = "\033[0m";

        String char0 = uncoloredOutput.substring(0, 1);
        String char1 = uncoloredOutput.substring(1, 2);
        String char2 = uncoloredOutput.substring(2, 3);
        String char3 = uncoloredOutput.substring(3, 4);
        String char4 = uncoloredOutput.substring(4, 5);
        String char5 = uncoloredOutput.substring(5, 6);

        String[] chars = new String[]{char0, char1, char2, char3, char4, char5};

        Link<Player> currentPlayer = playerTurnOrder.getFirst();
        if (currentPlayer.data.getLocation().data.getType().equals(spaceName)) {
            chars[0] = ("" + currentPlayer.data.getColorString() + chars[0] + RESET);
        }
        currentPlayer = currentPlayer.next;

        int i = 1;
        while(currentPlayer != playerTurnOrder.getFirst()) {
            if (currentPlayer.data.getLocation().data.getType().equals(spaceName)) {
                chars[i] = ("" + currentPlayer.data.getColorString() + chars[i] + RESET);
            }
            currentPlayer = currentPlayer.next;
            i++;
        }

        return ("" + chars[0] + chars[1] + chars[2] + chars[3] + chars[4] + chars[5]);
    }
}
