public class CommunityChest {
    private int moneyFromBank; // positive to receive, negative to give
    private String type; // goToJail, advanceToGo, getOutOfJail
    private int moneyFromPlayers; // positive to receive

    public void useCard(Player player, CircularLinkedList<Player> players, CircularLinkedList<BoardSpace> spaces) {
        player.setMoney(player.getMoney() + moneyFromBank);
        // get money from players
        int sum = 0;
        Link<Player> first = players.getFirst();
        Link<Player> current = first;
        do {
            sum += moneyFromPlayers;
            current.data.setMoney(current.data.getMoney() - moneyFromPlayers);
            current = current.next;
        } while (current != first);
        player.setMoney(player.getMoney() + sum);
        // do different things depending on type
        if (type.equals("advanceToGo")) {
            player.setLocation(spaces.getFirst()); // move them to Go
        }
        else if (type.equals("goToJail")) {
            // Here!
        }
        else if (type.equals("getOutOfJail")) {
            player.setNumGetOutOfJailFree(player.getNumGetOutOfJailFree() + 1);
        }
    }

    /*
    types:
    variables:
    - (boolean) go to jail
    - (boolean) advance to go
    - (int) how much money from bank (positive to receive, negative to give)
    - get out of jail free
    - (int) collect money from every player

    - change location and get money
    - get money from the bank
    - pay money to the bank
    - get out of jail free
    - change location (go to jail)
    - collect money from every player

    types of Chance cards:
    variables:
    - (BoardSpace) new location
    - (boolean) utility
    - (boolean) railroad
    - (int) money from bank
    - get out of jail free
    - go back three spaces
    - go to jail
    - pay each player money


    - change location and get money
    - change location, get money if pass go
    - advance to nearest utility (if unowned, buy from Bank--else, dice and pay owner 10 * dice)
    - advance to nearest railroad
    - get money from bank
    - get out of jail free
    - go back three spaces
    - change location (go to jail)
    - pay each player money
     */

}
