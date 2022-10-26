/* Jaya */
public class Chance {
    private Link<BoardSpace> newLocation; // the location the card moves the player to, if there is no location, this is null
    private String type; // "utility", "railroad", "getOutOfJail", "backThreeSpaces" and "goToJail"
    private int moneyFromBank; // positive to receive, negative to give
    private int moneyFromPlayers; // positive to receive, negative to give
    private String message;

    public Chance(String message, Link<BoardSpace> newLocation, String type, int moneyFromBank, int moneyFromPlayers) {
        this.message = message;
        this.newLocation = newLocation;
        this.type = type;
        this.moneyFromBank = moneyFromBank;
        this.moneyFromPlayers = moneyFromPlayers;
    }

    public void useCard(Player player, CircularLinkedList<Player> players, CircularLinkedList<BoardSpace> spaces, Game game) {
        System.out.println(message);
        player.setMoney(player.getMoney() + moneyFromBank); // add the moneyFromBank to the player's balance
        // pay each player
        int sum = 0;
        Link<Player> first = players.getFirst();
        Link<Player> current = first;
        do { // iterate through each player, adding the moneyFromPlayers to sum and subtracting moneyFromPlayers from each player's balance--note: this does the opposite if moneyFromPlayers is negative
            sum += moneyFromPlayers;
            current.data.setMoney(current.data.getMoney() - moneyFromPlayers);
            current = current.next;
        } while (current != first); // stop when you get to the beginning of the loop
        player.setMoney(player.getMoney() + sum);
        if (newLocation != null) { // if the card says to move somewhere
            if (type != null && type.equals("goToJail")); // and if it isn't the "go to jail" card
            else {
                // move to location, collect $200 if pass go
                Link<BoardSpace> currentLocation = player.getLocation();
                while (!currentLocation.data.equals(newLocation.data)) { // while they haven't reached their destination
                    currentLocation = currentLocation.next;
                    if (currentLocation.equals(spaces.getFirst())) { // if they pass Go
                        player.setMoney(player.getMoney() + 200);
                    }
                }
                player.setLocation(currentLocation);
                // give them options to do things
                if (!currentLocation.data.getSpaceName().equals("GO")) { // don't call propertyLand() if they have landed on Go
                    if (currentLocation.data.getType().equals("Railroad"))
                        game.railroadLand(player);
                    else
                        game.propertyLand(player);
                }
            }
        }
        // do different things depending on type
        if (type == null);
        else if (type.equals("getOutOfJail")) {
            player.setChanceGetOutOfJail(true);
            System.out.println("The card has been added to your hand.");
            game.getChanceCards().delete(this); // remove Get Out of Jail Free card from the Chance deck
        }
        else if (type.equals("goToJail")) {
            player.setLocation(newLocation);
            System.out.println("You move to jail, directly to jail. Do not pass GO, do not collect $200");
            player.setTurnsLeftInJail(3);
        }
        else if (type.equals("backThreeSpaces")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            for (int i = 0; i < 37; i++) { // move forward 37 spaces, therefore move back 3 spaces
                currentLocation = currentLocation.next;
            }
            player.setLocation(currentLocation);
        }
        else if (type.equals("railroad")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            while (!(currentLocation.data.getType().equals("Railroad"))) { // move until you get to a railroad
                currentLocation = currentLocation.next;
            }
            Railroad railroad = (Railroad) currentLocation.data;
            if (railroad.getOwner() == null) { // railroad is unowned
                if (player.getMoney() >= railroad.getPrice()) { // if the player has enough money to buy the railroad
                    System.out.println("Would you like to buy this railroad? (Yes/No)");
                    if (game.yesNoInput()) {
                        railroad.setOwner(player);
                        player.setMoney(player.getMoney() - railroad.getPrice()); // subtract the railroad's price from the player's balance
                        System.out.println("You now own " + railroad.getRealName());
                    }
                }
                else {
                    System.out.println("You unfortunately cannot afford this railroad.");
                }
            }
            else if (railroad.getOwner() == player) {
                System.out.println("You already own this railroad. You do not need to pay rent.");
            }
            else {
                System.out.println("This railroad is owned by " + railroad.getOwner().getName() + ", and rent costs $" + railroad.getRent() + ", so you have to pay $" + railroad.getRent() * 2);
                player.setMoney(player.getMoney() - railroad.getRent() * 2); // player pays railroad's rents * 2
                railroad.getOwner().setMoney(railroad.getOwner().getMoney() + railroad.getRent()); // pay the owner

            }
        }
        else if (type.equals("utility")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            while (!(currentLocation.data.getType().equals("Utility"))) { // move until you get to a utility
                currentLocation = currentLocation.next;
            }
            Utility utility = (Utility) currentLocation.data;
            System.out.println("You landed on " + utility.getRealName() + " (" + utility.getSpaceName() + ")");
            if (utility.getOwner() == null) { // railroad is unowned
                System.out.println("This utility is available to purchase. It costs $" + utility.getPrice());
                if (player.getMoney() >= utility.getPrice()) { // if the player has enough money to buy the utility
                    System.out.println("Would you like to buy this property? (Yes/No)");
                    if (game.yesNoInput()) {
                        utility.setOwner(player);
                        player.setMoney(player.getMoney() - utility.getPrice()); // subtract the utility's price from the player's balance
                        System.out.println("You now own " + utility.getRealName());
                    }
                }
                else {
                    System.out.println("You unfortunately cannot afford this utility.");
                }
            }
            else if (utility.getOwner() == player) {
                System.out.println("You already own this utility. You do not need to pay rent.");
            }
            else {
                System.out.println("This property is owned by " + utility.getOwner().getName());
                int[] diceRoll = game.rollDice(); // roll dice
                System.out.println("" + player.getName() + ", you rolled a " + diceRoll[0] + " and a " + diceRoll[1]); // print results of dice roll
                int payment = 10 * (diceRoll[0] + diceRoll[1]); // pay your dice roll * 10
                System.out.println("You have to pay $" + payment + ".");
                player.setMoney(player.getMoney() - payment); // subtract the payment from the player's balance
                utility.getOwner().setMoney(utility.getOwner().getMoney() + payment); // pay owner
            }
        }
    }
}
