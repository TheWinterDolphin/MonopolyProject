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

    private CircularLinkedList<CommunityChest> communityChestCards;
    private CircularLinkedList<Chance> chanceCards;

    private Chance topChanceCard;
    private CommunityChest topCommunityChestCard;

    public Game() {
        isGameOver = false;
    }

    public void setup() {
        spaces = new CircularLinkedList<>();
        chanceCards = new CircularLinkedList<>();
        communityChestCards = new CircularLinkedList<>();

        // initialize spaces
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

        TaxSpace lutx = new TaxSpace("LuTx","Luxury Tax",75, -1);
        TaxSpace intx = new TaxSpace("InTx","Income Tax",200,0.1);

        BoardSpace tojl = new BoardSpace("ToJl", "Go To Jail", "To Jail");
        BoardSpace frpk = new BoardSpace("FrPk","Free Parking","Free Parking");
        BoardSpace jail = new BoardSpace("JAIL", "Jail", "Jail");
        BoardSpace go = new BoardSpace("GO","GO","GO");

        BoardSpace ch3 = new BoardSpace("CH","Chance 3","Chance");
        BoardSpace ch2 = new BoardSpace("CH","Chance 2","Chance");
        BoardSpace ch1 = new BoardSpace("CH","Chance 1","Chance");
        BoardSpace cc3 = new BoardSpace("CC", "Community Chest 3", "Community Chest");
        BoardSpace cc2 = new BoardSpace("CC", "Community Chest 2", "Community Chest");
        BoardSpace cc1 = new BoardSpace("CC", "Community Chest 1", "Community Chest");

        spaces = new CircularLinkedList<>();

        //make spaces
        spaces.insertFirst(h2);
        spaces.insertFirst(lutx);
        spaces.insertFirst(h1);
        spaces.insertFirst(ch3);
        spaces.insertFirst(r4);
        spaces.insertFirst(g3);
        spaces.insertFirst(cc3);
        spaces.insertFirst(g2);
        spaces.insertFirst(g1);
        spaces.insertFirst(tojl);
        spaces.insertFirst(f3);
        spaces.insertFirst(ww);
        spaces.insertFirst(f2);
        spaces.insertFirst(f1);
        spaces.insertFirst(r3);
        spaces.insertFirst(e3);
        spaces.insertFirst(e2);
        spaces.insertFirst(ch2);
        spaces.insertFirst(e1);
        spaces.insertFirst(frpk);
        spaces.insertFirst(d3);
        spaces.insertFirst(d2);
        spaces.insertFirst(cc2);
        spaces.insertFirst(d1);
        spaces.insertFirst(r2);
        spaces.insertFirst(c3);
        spaces.insertFirst(c2);
        spaces.insertFirst(ec);
        spaces.insertFirst(c1);
        spaces.insertFirst(jail);
        spaces.insertFirst(b3);
        spaces.insertFirst(b2);
        spaces.insertFirst(ch1);
        spaces.insertFirst(b1);
        spaces.insertFirst(r1);
        spaces.insertFirst(intx);
        spaces.insertFirst(a2);
        spaces.insertFirst(cc1);
        spaces.insertFirst(a1);
        spaces.insertFirst(go);

        playerTurnOrder = inputPlayers();


        currentPlayer = playerTurnOrder.getFirst();
        while(currentPlayer.next != playerTurnOrder.getFirst()) {
            currentPlayer = currentPlayer.next;
        }

        //initialize community chest list
        CommunityChest ccc1 = new CommunityChest("Advance to GO (Collect $200).", 200, "advanceToGo", 0);
        CommunityChest ccc2 = new CommunityChest("Bank error in your favor. Collect $200.", 200, null, 0);
        CommunityChest ccc3 = new CommunityChest("Doctor's fees. Pay $50.", -50, null, 0);
        CommunityChest ccc4 = new CommunityChest("From sale of stock you get $50.", 50, null, 0);
        CommunityChest ccc5 = new CommunityChest("Get Out Of Jail Free!", 0, "getOutOfJail", 0);
        CommunityChest ccc6 = new CommunityChest("Go to Jail. Go directly to jail. Do not pass GO, do not collect $200.", 0, "goToJail", 0);
        CommunityChest ccc7 = new CommunityChest("Grand Opera Night. Collect $50 from every player for opening night seats.", 0, null, 50);
        CommunityChest ccc8 = new CommunityChest("Holiday Fund matures. Receive $100.", 100, null, 0);
        CommunityChest ccc9 = new CommunityChest("Income Tax refund. Collect $20.", 20, null, 0);
        CommunityChest ccc10 = new CommunityChest("It is your birthday. Collect $10 from every player.", 0, null, 10);
        CommunityChest ccc11 = new CommunityChest("Life insurance matures - Collect $100", 100, null, 0);
        CommunityChest ccc12 = new CommunityChest("Hospital Fees. Pay $50.", -50, null, 0);
        CommunityChest ccc13 = new CommunityChest("School fees. Pay $50.", -50, null, 0);
        CommunityChest ccc14 = new CommunityChest("Receive $25 consultancy fee.", 25, null, 0);
        CommunityChest ccc15 = new CommunityChest("You have won second prize in a beauty contest. Collect $10.", 10, null, 0);
        CommunityChest ccc16 = new CommunityChest("You inherit $100.", 100, null, 0);

        communityChestCards.insertFirst(ccc1);
        communityChestCards.insertFirst(ccc2);
        communityChestCards.insertFirst(ccc3);
        communityChestCards.insertFirst(ccc4);
        communityChestCards.insertFirst(ccc5);
        communityChestCards.insertFirst(ccc6);
        communityChestCards.insertFirst(ccc7);
        communityChestCards.insertFirst(ccc8);
        communityChestCards.insertFirst(ccc9);
        communityChestCards.insertFirst(ccc10);
        communityChestCards.insertFirst(ccc11);
        communityChestCards.insertFirst(ccc12);
        communityChestCards.insertFirst(ccc13);
        communityChestCards.insertFirst(ccc14);
        communityChestCards.insertFirst(ccc15);
        communityChestCards.insertFirst(ccc16);

        topCommunityChestCard = ccc16;

        // initialize chance list
        Chance chc1 = new Chance("Advance to GO (Collect $200).", spaces.getFirst(), null, 0, 0);
        Chance chc2 = new Chance("Advance to Illinois Ave. If you pass GO, collect $200.", spaces.find(e3), null, 0, 0);
        Chance chc3 = new Chance("Advance to St. Charles Place. If you pass GO, collect $200.", spaces.find(c1), null, 0, 0);
        Chance chc4 = new Chance("Advance token to the nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total 10 times the amount thrown.", null, "utility", 0, 0);
        Chance chc5 = new Chance("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rent to which they are otherwise entitled.", null, "railroad", 0, 0);
        Chance chc6 = new Chance("Bank pays you dividend of $50.", null, null, 50, 0);
        Chance chc7 = new Chance("Get Out of Jail Free!", null, "getOutOfJail", 0, 0);
        Chance chc8 = new Chance("Go back three spaces.", null, "backThreeSpaces", 0, 0);
        Chance chc9 = new Chance("Go to Jail. Do not pass Go, do not collect $200.", spaces.find(jail), "goToJail", 0, 0);
        Chance chc10 = new Chance("Take a trip to Reading Railroad. If you pass Go, collect $200.", spaces.find(r1), null, 0, 0);
        Chance chc11 = new Chance("Take a walk on the Boardwalk. Advance token to Boardwalk.", spaces.find(h2), null, 0, 0);
        Chance chc12 = new Chance("You have been elected Chairman of the Board. Pay each player $50.", null, null, 0, -50);
        Chance chc13 = new Chance("Your building loan matures. Receive $150.", null, null, 150, 0);

        chanceCards.insertFirst(chc1);
        chanceCards.insertFirst(chc2);
        chanceCards.insertFirst(chc3);
        chanceCards.insertFirst(chc4);
        chanceCards.insertFirst(chc5);
        chanceCards.insertFirst(chc6);
        chanceCards.insertFirst(chc7);
        chanceCards.insertFirst(chc8);
        chanceCards.insertFirst(chc9);
        chanceCards.insertFirst(chc10);
        chanceCards.insertFirst(chc11);
        chanceCards.insertFirst(chc12);
        chanceCards.insertFirst(chc13);

        topChanceCard = chc13;

    }

    public CircularLinkedList<Player> inputPlayers() {
        CircularLinkedList<Player> players = new CircularLinkedList<>();
        int numPlayers = inputPlayerInt();

        String[] colorOptions = new String[]{"Red","Green","Yellow","Blue","Magenta","Cyan"};
        for (int i = numPlayers; i > 0; i--) {

        ArrayList<String> colorOptions = new ArrayList<>();
        colorOptions.add("Red");
        colorOptions.add("Green");
        colorOptions.add("Yellow");
        colorOptions.add("Blue");
        colorOptions.add("Magenta");
        colorOptions.add("Cyan");
        for (int i = 1; i <= numPlayers; i++) {

            System.out.print("Name of Player " + i + ": ");
            String name = input.nextLine();
            // get color
            ArrayList<String> colorStrings = inputPlayerColor(colorOptions);
            Player player = new Player(name, spaces, colorStrings.get(0), colorStrings.get(1));
            colorOptions.remove(colorStrings.get(2));
            players.insertFirst(player);
        }
        return players;
    }

    private ArrayList<String> inputPlayerColor(ArrayList<String> colorOptions) {
        // print options
        System.out.print("What color would you like? (");
        for (int i = 0; i < colorOptions.size() - 1; i++) {
            System.out.print(colorOptions.get(i) + ", ");
        }
        System.out.println(colorOptions.get(colorOptions.size() - 1) + ")");
        // get user input
        String color = input.nextLine();
        for (int i = 0; i < colorOptions.size(); i++) {
            if (color.equalsIgnoreCase(colorOptions.get(i))) {
                if (colorOptions.get(i).equals("Red")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;31m");
                    strings.add("\033[41m");
                    strings.add("Red");
                    return strings;
                }
                if (colorOptions.get(i).equals("Green")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;32m");
                    strings.add("\033[42m");
                    strings.add("Green");
                    return strings;
                }
                if (colorOptions.get(i).equals("Yellow")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;33m");
                    strings.add("\033[43m");
                    strings.add("Yellow");
                    return strings;
                }
                if (colorOptions.get(i).equals("Blue")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;34m");
                    strings.add("\033[44m");
                    strings.add("Blue");
                    return strings;
                }
                if (colorOptions.get(i).equals("Magenta")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;35m");
                    strings.add("\033[45m");
                    strings.add("Magenta");
                    return strings;
                }
                if (colorOptions.get(i).equals("Cyan")) {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("\033[0;36m");
                    strings.add("\033[46m");
                    strings.add("Cyan");
                    return strings;
                }
            }
        }
        System.out.println("Please input a valid color.");
        return inputPlayerColor(colorOptions);
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
            System.out.println("-------------------------------------------\nStart of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney());
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
            System.out.println("End of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney() + "\n-------------------------------------------");
            return;
            //ADD STUFF--can you add this stuff to where I wrote "Here!" in CommunityChest?
        }

        player.setLocation(player.getLocation().next);
        for (int i=1; i<(diceRoll[0]+diceRoll[1] - 1); i++) {
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
            System.out.println("End of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney() + "\n-------------------------------------------");
            return;
        }

        else {
            System.out.print("FIX THIS ERROR THIS ISNT SUPPOSED TO BE POSSIBLE!");
        }

        if ((diceRoll[0] == diceRoll[1]) && (numOfDoubles < 2)) {
            System.out.print("Your roll was a double! You get to roll again!");
            playerTurn(player, rollDice(), numOfDoubles+1);
        }

        if ((diceRoll[0] != diceRoll[1])) {
            System.out.print("Would you like to sell any of your properties to the bank? (Yes/No)");
            if (yesNoInput()) {
                while (true) {
                    System.out.print("What is the name of the property you would like to sell?");
                    BoardSpace property = inputProperty(player); //Including Railroads and Utilities
                    if (property.getType().equals("Property")) {
                        ((Property) property).setOwner(null);
                    } else if (property.getType().equals("Railroad")) {
                        player.setNumOfRailroadsOwned(player.getNumOfRailroadsOwned() - 1);
                        ((Railroad) property).setOwner(null);
                    } else {
                        ((Utility) property).setOwner(null);
                    }
                    System.out.print("Would you like to stop selling properties? (Yes/No)");
                    if (yesNoInput()) {
                        break;
                    }
                }
            }

            tradeWithOtherPlayers(player);

            System.out.println("End of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney() + "\n-------------------------------------------");
        }
    }

    public boolean yesNoInput() {
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        }
        else if (response.equalsIgnoreCase("no")) {
            return false;
        }
        else {
            System.out.println("Please input either \"yes\" or \"no\".");
            return yesNoInput();
        }
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
            if (player.getMoney() >= property.getPrice()) {
                System.out.println("Would you like to buy this property? (Yes/No)");
                if (yesNoInput()) {
                    property.setOwner(player);
                    player.setMoney(player.getMoney() - property.getPrice());
                    System.out.println("You now own " + property.getRealName());
                }
            }
            else {
                System.out.println("You unfortunately cannot afford this property.");
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
        topCommunityChestCard.useCard(player, playerTurnOrder, spaces);
        Link<CommunityChest> nextCard = communityChestCards.find(topCommunityChestCard).next;
        topCommunityChestCard = nextCard.data;
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
        topChanceCard.useCard(player, playerTurnOrder, spaces, this);
        Link<Chance> nextCard = chanceCards.find(topChanceCard).next;
        topChanceCard = nextCard.data;
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
    public int[] rollDice() {
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
        topRowString += colorSpaceString("Jail", "[JAIL]");
        topRowString += " ";
        topRowString += colorSpaceString("St. Charles Place", "[ C1 ]");
        topRowString += " ";
        topRowString += colorSpaceString("Electric Company", "[ EC ]");
        topRowString += " ";
        topRowString += colorSpaceString("States Avenue", "[ C2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("Virginia Avenue", "[ C3 ]");
        topRowString += " ";
        topRowString += colorSpaceString("Pennsylvania Railroad", "[ R2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("St. James Place", "[ D1 ]");
        topRowString += " ";
        topRowString += colorSpaceString("Community Chest 2", "[ CC ]");
        topRowString += " ";
        topRowString += colorSpaceString("Tennessee Avenue", "[ D2 ]");
        topRowString += " ";
        topRowString += colorSpaceString("New York Avenue", "[ D3 ]");
        topRowString += " ";
        topRowString += colorSpaceString("Free Parking", "[FrPk]");
        topRowString += " |";
        System.out.println(topRowString);

        //Middle Rows
        System.out.println("| " + colorSpaceString("Connecticut Avenue", "[ B3 ]") +
                " -------------------------------------------------------------- " +
                colorSpaceString("Kentucky Avenue", "[ E1 ]") + " |");

        System.out.println("| " + colorSpaceString("Vermont Avenue", "[ B2 ]") +
                " |                                                            | " +
                colorSpaceString("Chance 2", "[ CH ]") + " |");

        System.out.println("| " + colorSpaceString("Chance 1", "[ CH ]") +
                " |                                                            | " +
                colorSpaceString("Indiana Avenue", "[ E2 ]") + " |");

        System.out.println("| " + colorSpaceString("Oriental Avenue", "[ B1 ]") +
                " |                                                            | " +
                colorSpaceString("Illinois Avenue", "[ E3 ]") + " |");

        System.out.println("| " + colorSpaceString("Reading Railroad", "[ R1 ]") +
                " |                      M O N O P O L Y                       | " +
                colorSpaceString("B.&O. Railroad", "[ R3 ]") + " |");

        System.out.println("| " + colorSpaceString("Income Tax", "[InTx]") +
                " |                                                            | " +
                colorSpaceString("Atlantic Avenue", "[ F1 ]") + " |");

        System.out.println("| " + colorSpaceString("Baltic Avenue", "[ A2 ]") +
                " |                                                            | " +
                colorSpaceString("Ventnor Avenue", "[ F2 ]") + " |");

        System.out.println("| " + colorSpaceString("Community Chest 1", "[ CC ]") +
                " |                                                            | " +
                colorSpaceString("Water Works", "[ WW ]") + " |");

        System.out.println("| " + colorSpaceString("Mediterranean Avenue", "[ A1 ]") +
                " -------------------------------------------------------------- " +
                colorSpaceString("Marvin Gardens", "[ F3 ]") + " |");

        //TopRow
        String bottomRowString = "| ";
        bottomRowString += colorSpaceString("GO", "[ GO ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Boardwalk", "[ H2 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Luxury Tax", "[LuTx]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Park Place", "[ H1 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Chance 3", "[ CH ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Short Line", "[ R4 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Pennsylvania Avenue", "[ G3 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Community Chest 3", "[ CC ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("North Carolina Avenue", "[ G2 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Pacific Avenue", "[ G1 ]");
        bottomRowString += " ";
        bottomRowString += colorSpaceString("Go To Jail", "[ToJl]");
        bottomRowString += " |";
        System.out.println(bottomRowString);

        System.out.println("--------------------------------------------------------------------------------");
    }

    /*Andrew*/
    private String colorSpaceString(String realName, String uncoloredOutput) {
        String RESET = "\033[0m";

        String char0 = uncoloredOutput.substring(0, 1);
        String char1 = uncoloredOutput.substring(1, 2);
        String char2 = uncoloredOutput.substring(2, 3);
        String char3 = uncoloredOutput.substring(3, 4);
        String char4 = uncoloredOutput.substring(4, 5);
        String char5 = uncoloredOutput.substring(5, 6);

        String[] chars = new String[]{char0, char1, char2, char3, char4, char5};

        Link<BoardSpace> current2 = spaces.getFirst();
        while(!((current2.data.getSpaceName().equals(uncoloredOutput.substring(1, 5))) || (current2.data.getSpaceName().equals(uncoloredOutput.substring(2, 4))))) {
            current2 = current2.next;
        }

        String ownerPrefix = "";

        if (current2.data.getType().equals("Property")) {
            if (((Property) current2.data).getOwner() != null){
                ownerPrefix = ((Property) current2.data).getOwner().getForegroundColorString();
            }
        }

        Link<Player> current = playerTurnOrder.getFirst();
        if (current.data.getLocation().data.getRealName().equals(realName)) {
            chars[0] = ("" + current.data.getBackgroundColorString() + chars[0] + RESET);
        }
        current = current.next;

        int i = 1;
        while(current != playerTurnOrder.getFirst()) {
            if (current.data.getLocation().data.getRealName().equals(realName)) {
                chars[i] = ("" + current.data.getBackgroundColorString() + chars[i] + RESET);
            }
            current = current.next;
            i++;
        }
        
        chars[0] = ownerPrefix + chars[0] + RESET;
        chars[1] = ownerPrefix + chars[1] + RESET;
        chars[2] = ownerPrefix + chars[2] + RESET;
        chars[3] = ownerPrefix + chars[3] + RESET;
        chars[4] = ownerPrefix + chars[4] + RESET;
        chars[5] = ownerPrefix + chars[5] + RESET;

        return (chars[0] + chars[1] + chars[2] + chars[3] + chars[4] + chars[5]);
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }
    private BoardSpace inputProperty(Player player) {
        String name = input.nextLine();
        BoardSpace testProperty = new BoardSpace("spaceName",name,"type");
        Link<BoardSpace> propertyLink = spaces.find(testProperty);
        if (propertyLink != null) {
            if (player.getProperties().contains(propertyLink.data))
                return propertyLink.data;
        }
        System.out.println("Please input a valid property.");
        return inputProperty(player);
    }

    private Player inputPlayer() {
        String name = input.nextLine();
        Player testPlayer = new Player(name, spaces, "colorString", "backgroundColorString");
        Link<Player> playerLink = playerTurnOrder.find(testPlayer);
        if (playerLink != null)
            return playerLink.data;
        System.out.println("Please input a valid player name.");
        return inputPlayer();
    }

    private int inputPlayerMoney(Player player) {
        while (true) {
            try {
                String response = input.nextLine();
                int num = Integer.parseInt(response);
                if (num <= 0) {
                    System.out.println("Please input a number that is greater than 0.");
                }
                else if (num > player.getMoney()) {
                    System.out.println("Please input a number that is less than " + player.getName() + "'s current balance.");
                } else {
                    return num;
                }
            } catch (Exception e) {
                System.out.println("Please input an integer.");
            }
        }
    }
}
