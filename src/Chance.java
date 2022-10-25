public class Chance {
    private Link<BoardSpace> newLocation;
    private String type; // "utility", "railroad", "getOutOfJail", "backThreeSpaces" and "goToJail"
    private int moneyFromBank; // positive to receive, negative to give
    private int moneyFromPlayers; // positive to receive, negative to give
    private String message;

    public void useCard(Player player, CircularLinkedList<Player> players, CircularLinkedList<BoardSpace> spaces, Game game) {
        player.setMoney(player.getMoney() + moneyFromBank);
        // pay each player
        int sum = 0;
        Link<Player> first = players.getFirst();
        Link<Player> current = first;
        do {
            sum += moneyFromPlayers;
            current.data.setMoney(current.data.getMoney() - moneyFromPlayers);
            current = current.next;
        } while (current != first);
        player.setMoney(player.getMoney() + sum);
        if (newLocation != null && !type.equals("goToJail")) {
            // move to location, collect $200 if pass go
            Link<BoardSpace> currentLocation = player.getLocation();
            while (!currentLocation.data.equals(newLocation.data)) {
                currentLocation = currentLocation.next;
                if (currentLocation.equals(spaces.getFirst())) { // if they pass Go
                    player.setMoney(player.getMoney() + 200);
                }
            }
        }
        // do different things depending on type
        if (type.equals("getOutOfJail")) {
            player.setNumGetOutOfJailFree(player.getNumGetOutOfJailFree() + 1);
        }
        else if (type.equals("goToJail")) {
            player.setLocation(newLocation);
            player.setTurnsLeftInJail(3);
        }
        else if (type.equals("backThreeSpaces")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            for (int i = 0; i < 37; i++) {
                currentLocation = currentLocation.next;
            }
            player.setLocation(currentLocation);
        }
        else if (type.equals("railroad")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            while (!(currentLocation.data.getType().equals("Railroad"))) {
                currentLocation = currentLocation.next;
            }
            Railroad railroad = (Railroad) currentLocation.data;
            System.out.println("You landed on " + railroad.getRealName() + " (" + railroad.getSpaceName() + ")");
            if (railroad.getOwner() == null) { // railroad is unowned
                System.out.println("This railroad is available to purchase. It costs $" + railroad.getPrice());
                System.out.println("Would you like to buy this railroad? (Yes/No)");
                if (game.yesNoInput()) {
                    railroad.setOwner(player);
                    System.out.println("You now own " + railroad.getRealName());
                }
            }
            else if (railroad.getOwner() == player) {
                System.out.println("You already own this railroad. You do not need to pay rent.");
            }
            else {
                System.out.println("This property is owned by " + railroad.getOwner().getName() + ", and rent costs $" + railroad.getRent() + ", so you have to pay $" + railroad.getRent() * 2);
                player.setMoney(player.getMoney() - railroad.getRent() * 2);
                railroad.getOwner().setMoney(railroad.getOwner().getMoney() + railroad.getRent());

            }
        }
        else if (type.equals("utility")) {
            Link<BoardSpace> currentLocation = player.getLocation();
            while (!(currentLocation.data.getType().equals("Utility"))) {
                currentLocation = currentLocation.next;
            }
            Utility utility = (Utility) currentLocation.data;
            System.out.println("You landed on " + utility.getRealName() + " (" + utility.getSpaceName() + ")");
            if (utility.getOwner() == null) { // railroad is unowned
                System.out.println("This utility is available to purchase. It costs $" + utility.getPrice());
                System.out.println("Would you like to buy this utility? (Yes/No)");
                if (game.yesNoInput()) {
                    utility.setOwner(player);
                    System.out.println("You now own " + utility.getRealName());
                }
            }
            else if (utility.getOwner() == player) {
                System.out.println("You already own this utility. You do not need to pay rent.");
            }
            else {
                System.out.println("This property is owned by " + utility.getOwner().getName());
                int[] diceRoll = game.rollDice();
                System.out.println("" + player.getName() + ", you rolled a " + diceRoll[0] + " and a " + diceRoll[1]);
                int payment = 10 * (diceRoll[0] + diceRoll[1]);
                System.out.println("You have to pay $" + payment + ".");
                player.setMoney(player.getMoney() - payment);
                utility.getOwner().setMoney(utility.getOwner().getMoney() + payment);
            }
        }
    }
}
