public class Chance {
    private BoardSpace newLocation;
    private String type; // "utility", "railroad", "getOutOfJail", "backThreeSpaces" and "goToJail"
    private int moneyFromBank; // positive to receive, negative to give
    private int moneyfromPlayers; // positive to receive, negative to give

    public void useCard(Player player, CircularLinkedList<Player> players) {

    }
    /*
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
     */
}
