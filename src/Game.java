import java.util.ArrayList;
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
        isGameOver = false;
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
        printBoard();

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
        //ALSO NEED GET OUT OF JAIL FREE CARD STUFF

        int[] diceRoll;

        if (inputDiceRoll == null) {
            System.out.println("-------------------------------------------\nStart of " + player.getName() + "'s Turn | Current Money: " + player.getMoney());
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
                playerTurn(player, null, 0);
                return;
            }
            else {
                System.out.println("Would you like to pay $50 to leave jail early? (Yes/No)");
                boolean choice = yesNoInput();
                if (choice) {
                    System.out.println("You paid $50. You are free. Start your normal turn.");
                    player.setMoney(player.getMoney() - 50);
                    player.setTurnsLeftInJail(0);
                    playerTurn(player, null, 0);
                    return;
                }
            }
            System.out.println("You have " + player.getTurnsLeftInJail() + " turns left in jail.");
            System.out.println("Turn over!");
            return;
        }

        System.out.println("" + player.getName() + ", you rolled a " + diceRoll[0] + " and a " + diceRoll[1]);

        if ((diceRoll[0] == diceRoll[1]) && (numOfDoubles >= 2)) {
            System.out.print("This was your third double! Sorry, you go to directly to jail.");
            //ADD STUFF
        }

        for (int i=0; i<(diceRoll[0]+diceRoll[1] - 1); i++) {
            if (player.getLocation().data.getType().equals("GO")) {
                System.out.println("You passed GO! Collect $200.");
                player.setMoney(player.getMoney() + 200);
            }
            player.setLocation(player.getLocation().next);
        }
        player.setLocation(player.getLocation().next);

        printBoard();

        if (player.getLocation().data.getType().equals("GO")) {
            System.out.println("You landed on GO! Collect $200");
            player.setMoney(player.getMoney() + 200);
        }

        else if (player.getLocation().data.getType().equals("Property")) {
            propertyLand(player);
        }

        else if (player.getLocation().data.getType().equals("Community Chest")) {
            communityChestLand(player);
        }

        else if (player.getLocation().data.getType().equals("Tax")) {
            taxLand(player);
        }

        else if (player.getLocation().data.getType().equals("Railroad")) {
            railroadLand(player);
        }

        else if (player.getLocation().data.getType().equals("Chance")) {
            chanceLand(player);
        }

        else if (player.getLocation().data.getType().equals("Jail")) {
            jailVisitingLand(player);
        }

        else if (player.getLocation().data.getType().equals("Utility")) {
            utilityLand(player, diceRoll);
        }

        else if (player.getLocation().data.getType().equals("Free Parking")) {
            freeParkingLand(player);
        }

        else if (player.getLocation().data.getType().equals("To Jail")) {
            toJailLand(player);
        }

        else {
            System.out.print("FIX THIS ERROR THIS ISNT SUPPOSED TO BE POSSIBLE!");
        }

        if ((diceRoll[0] == diceRoll[1]) && (numOfDoubles < 2)) {
            System.out.print("Your roll was a double! You get to roll again!");
            playerTurn(player, rollDice(), numOfDoubles+1);
        }

        System.out.print("Would you like to sell any of your properties to the bank? (Yes/No)");
        if (yesNoInput()) {
            while(true) {
                System.out.print("What is the name of the property you would like to sell?");
                BoardSpace property = inputProperty(player); //Including Railroads and Utilities
                if (property.getType().equals("Property")) {
                    ((Property) property).setOwner(null);
                }
                else if (property.getType().equals("Railroad")) {
                    player.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() - 1);
                    ((Railroad) property).setOwner(null);
                }
                else {
                    ((Utility) property).setOwner(null);
                }
                System.out.print("Would you like to stop selling properties? (Yes/No)");
                if (yesNoInput()) {
                    break;
                }
            }
        }

        tradeWithOtherPlayers(player);

        System.out.println("End of " + player.getName() + "'s Turn | Current Money: " + player.getMoney() + "\n-------------------------------------------");
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
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        Property property = (Property) player.getLocation().data;
        if (property.getOwner() == player) {
            System.out.println("You already own this property. You do not need to pay rent.");
        }
        else if (property.getOwner() == null) {
            System.out.println("This property is available to purchase. It costs $" + property.getPrice());
            System.out.println("Would you like to buy this property? (Yes/No)");
            if (yesNoInput()) {
                property.setOwner(player);
                System.out.println("You now own " + property.getName());
            }
        }
        else {
            System.out.println("This property is owned by " + property.getOwner().getName() + ", and rent costs $" + property.getRent());
            player.setMoney(player.getMoney() - property.getRent());
            property.getOwner().setMoney(property.getOwner().getMoney() + property.getRent());
            System.out.println("You paid $" + property.getRent() + " to " + property.getOwner().getName());
        }
    }

    private void communityChestLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
    }

    private void taxLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
    }

    private void railroadLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        Railroad railroad = (Railroad) player.getLocation().data;
        if (railroad.getOwner() == player) {
            System.out.println("You already own this railroad. You do not need to pay rent.");
        }
        else if (railroad.getOwner() == null) {
            System.out.println("This property is available to purchase. It costs $" + railroad.getPrice());
            System.out.println("Would you like to buy this property? (Yes/No)");
            if (yesNoInput()) {
                railroad.setOwner(player);
                player.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() + 1);
                System.out.println("You now own " + railroad.getRealName());
            }
        }
        else {
            int rent = (int) (12.5 * Math.pow(2, railroad.getOwner().getNumOfRailroadsOwned()));
            System.out.println("This railroad is owned by " + railroad.getOwner().getName() + ", and rent costs $" + rent);
            player.setMoney(player.getMoney() - rent);
            railroad.getOwner().setMoney(railroad.getOwner().getMoney() + rent);
            System.out.println("You paid $" + rent + " to " + railroad.getOwner().getName());
        }
    }

    private void chanceLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
    }

    private void jailVisitingLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        System.out.println("Don't worry, you are just visiting.");
    }

    private void utilityLand(Player player, int[] diceRoll) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        //NEED TO FINISH THIS
    }

    private void freeParkingLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        System.out.println("Nothing happens.");
    }

    private void toJailLand(Player player) {
        System.out.println("You landed on " + player.getLocation().data.getRealName() + " (" + player.getLocation().data.getSpaceName() + ")");
        System.out.println("You move to jail, directly to jail. Do not pass GO, do not collect $200");
        while(!player.getLocation().data.getType().equals("Jail")) {
            player.setLocation(player.getLocation().next);
        }
        player.setTurnsLeftInJail(3);
    }

    private void tradeWithOtherPlayers(Player player) {
        //GET OUT OF JAIL FREE CARD TRADING

        System.out.print("Would you like to offer any trades to other player? (Yes/No)");
        if (yesNoInput()) {
            while(true) {
                System.out.print("Who is the player you want to trade with?");
                Player recipient = inputPlayer();
                System.out.print("How much money are you offering?");
                int moneyToRecipient = inputPlayerMoney(player); //Make sure they are not offering more money than they have (and make sure it is a positive number)
                System.out.print("Would you like to offer properties?");
                ArrayList<BoardSpace> propertiesToRecipient = new ArrayList<>();
                if (yesNoInput()) {
                    while(true) {
                        System.out.print("What is the name of the property you would like to offer?");
                        propertiesToRecipient.add(inputProperty(player)); //Including Railroads and Utilities
                        System.out.print("Would you like to stop offering properties? (Yes/No)");
                        if (yesNoInput()) {
                            break;
                        }
                    }
                }

                System.out.print("How much money are you requesting?");
                int moneyFromRecipient = inputPlayerMoney(recipient); //Make sure they are not offering more money than they have (and make sure it is a positive number)
                System.out.print("Would you like to request properties?");
                ArrayList<BoardSpace> propertiesFromRecipient = new ArrayList<>();
                if (yesNoInput()) {
                    while(true) {
                        System.out.print("What is the name of the property you would like to request?");
                        propertiesFromRecipient.add(inputProperty(recipient)); //Including Railroads and Utilities
                        System.out.print("Would you like to stop requesting properties? (Yes/No)");
                        if (yesNoInput()) {
                            break;
                        }
                    }
                }

                System.out.print("Does the player you are trading with agree to the deal you just inputted? (Yes/No)");
                if (yesNoInput()) {
                    player.setMoney(player.getMoney() + moneyFromRecipient - moneyToRecipient);
                    recipient.setMoney(recipient.getMoney() + moneyToRecipient - moneyFromRecipient);

                    for (BoardSpace property : propertiesFromRecipient) {
                        if (property.getType().equals("Property")) {
                            ((Property) property).setOwner(player);
                        }
                        else if (property.getType().equals("Railroad")) {
                            player.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() + 1);
                            recipient.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() - 1);
                            ((Railroad) property).setOwner(player);
                        }
                        else {
                            ((Utility) property).setOwner(player);
                        }
                    }

                    for (BoardSpace property : propertiesToRecipient) {
                        if (property.getType().equals("Property")) {
                            ((Property) property).setOwner(recipient);
                        }
                        else if (property.getType().equals("Railroad")) {
                            player.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() - 1);
                            recipient.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() + 1);
                            ((Railroad) property).setOwner(recipient);
                        }
                        else {
                            ((Utility) property).setOwner(recipient);
                        }
                    }

                    System.out.println("The deal has completed.");
                }

                System.out.print("Would you like to offer another trade to any player? (Yes/No)");
                if (!yesNoInput()) {
                    break;
                }
            }
        }
    }

    /*Andrew*/
    private void checkIfBankrupt(Player player) {
        if (player.getMoney() < 0) {
            playerTurnOrder.delete(player);

            for (BoardSpace property : player.getProperties()) {
                if (property.getType().equals("Property")) {
                    ((Property) property).setOwner(null);
                }
                else if (property.getType().equals("Railroad")) {
                    ((Railroad) property).setOwner(null);
                }
                else {
                    ((Utility) property).setOwner(null);
                }
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
        bottomRowString += colorSpaceString("ToJl", "[ToJl]");
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

    public boolean getIsGameOver() {
        return isGameOver;
    }
}
