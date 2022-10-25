import java.util.Random;
import java.util.Scanner;

public class Game {
    private CircularLinkedList<BoardSpace> spaces;
    private CircularLinkedList<Player> playerTurnOrder;
    private Scanner input = new Scanner(System.in);
    private boolean isGameOver;
    private Link<Player> currentPlayer;
    private Random random = new Random();

    public Game() {

    }

    public void setup() {
        // initialize spaces
        playerTurnOrder = inputPlayers();
        Property h2 = new Property("H2", "Boardwalk", 400, 50);
        Property h1 = new Property("H1", "Park Place", 350, 35);
        Property g3 = new Property("G3", "Pennsylvania Avenue", 320, 28);
        Property g2 = new Property("G2", "North Carolina Avenue", 300, 26);
        Property g1 = new Property("G1", "Pacific Avenue", 300, 26);
        Property f3 = new Property("F3", "Marvin Gardens", 280, 24);
        Property f2 = new Property("F2", "Ventnor Avenue", 260, 22);
        Property f1 = new Property("F1", "Atlantic Avenue", 260, 22);
        Property e3 = new Property("E3", "Illinois Avenue", 240, 20);
        Property e2 = new Property("E2", "Indiana Avenue", 220, 18);
        Property e1 = new Property("E1", "Kentucky Avenue", 220, 18);
        Property d3 = new Property("D3", "New York Avenue", 200, 16);
        Property d2 = new Property("D2", "Tennessee Avenue", 180, 14);
        Property d1 = new Property("D1", "St. James Place", 180, 14);
        Property c3 = new Property("C3", "Virginia Avenue", 160, 12);
        Property c2 = new Property("C2", "States Avenue", 140, 10);
        Property c1 = new Property("C1", "St. Charles Place", 140, 10);
        Property b3 = new Property("B3", "Connecticut Avenue", 120, 8);
        Property b2 = new Property("B2", "Vermont Avenue", 100, 6);
        Property b1 = new Property("B1", "Oriental Avenue", 100, 6);
        Property a2 = new Property("A2", "Baltic Avenue", 60, 4);
        Property a1 = new Property("A1", "Mediterranean Avenue", 60, 2);

        Railroad r4 = new Railroad("R4", "Short Line");
        Railroad r3 = new Railroad("R3", "B.&O. Railroad");
        Railroad r2 = new Railroad("R2", "Pennsylvania Railroad");
        Railroad r1 = new Railroad("R1", "Reading Railroad");

        Utility ww = new Utility("WW", "Water Works", 150);
        Utility ec = new Utility("EC", "Electric Company", 150);

        TaxSpace lutx = new TaxSpace("Luxury Tax",75, 0);
        TaxSpace intx = new TaxSpace("Income Tax",200,0.1);



    }
    /*
    public Property(String name, int price, int rent, String colorGroup) {
        super(name, "Property");
        this.price = price;
        this.rent = rent;
        this.colorGroup = colorGroup;
    }
            --------------------------------------------------------------------------------
            | [JAIL] [ C1 ] [ EC ] [ C2 ] [ C3 ] [ R2 ] [ D1 ] [ CC ] [ D2 ] [ D3 ] [FrPk] |
            | [ B3 ] -------------------------------------------------------------- [ E1 ] |
            | [ B2 ] |                                                            | [ CH ] |
            | [ CH ] |                                                            | [ E2 ] |
            | [ B1 ] |                                                            | [ E3 ] |
            | [ R1 ] |                      M O N O P O L Y                       | [ R3 ] |
            | [InTx] |                                                            | [ F1 ] |
            | [ A2 ] |                                                            | [ F2 ] |
            | [ CC ] |                                                            | [ WW ] |
            | [ A1 ] -------------------------------------------------------------- [ F3 ] |
            | [ GO ] [ H2 ] [LuTx] [ H1 ] [ CH ] [ R4 ] [ G3 ] [ CC ] [ G2 ] [ G1 ] [ToJl] |
            --------------------------------------------------------------------------------
     */
    public CircularLinkedList<Player> inputPlayers() {
        CircularLinkedList<Player> players = new CircularLinkedList<>();
        int numPlayers = inputPlayerInt();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Name of Player " + i + ": ");
            String name = input.nextLine();
            Player player = new Player(name, spaces);
            players.insertFirst(player);
        }
        return players;
    }

    public int inputPlayerInt() {
        while (true) {
            try {
                System.out.print("Number of players: ");
                String response = input.nextLine();
                int num = Integer.parseInt(response);
                if (num < 2) {
                    System.out.println("Please input a number that is greater than or equal to 2.");
                } else {
                    return num;
                }

            } catch (Exception e) {
                System.out.println("Please input an integer.");
            }
        }
    }
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
                System.out.println("Would you like to pay $50 to leave jail early? (Yes/No)");
                boolean choice = yesNoInput();
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

    private boolean yesNoInput() {
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        }
        else if (response.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            System.out.println("Please input either \"yes\" or \"no\".");
            yesNoInput();
        }
        return false;
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
