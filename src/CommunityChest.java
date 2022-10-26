/* Jaya */
public class CommunityChest {
    private int moneyFromBank; // positive to receive, negative to give
    private String type; // goToJail, advanceToGo, getOutOfJail
    private int moneyFromPlayers; // how much money each player owes you--positive to receive

    private String message; // message on the card

    public CommunityChest(String message, int moneyFromBank, String type, int moneyFromPlayers) {
        this.message = message;
        this.moneyFromBank = moneyFromBank;
        this.type = type;
        this.moneyFromPlayers = moneyFromPlayers;
    }

    public void useCard(Player player, CircularLinkedList<Player> players, CircularLinkedList<BoardSpace> spaces, Game game) {
        System.out.println(message);
        player.setMoney(player.getMoney() + moneyFromBank);
        // get money from players
        int sum = 0;
        Link<Player> first = players.getFirst();
        Link<Player> current = first;
        do { // iterate through the players, adding the money that each player owes to sum and removing that money from that player's balance
            sum += moneyFromPlayers;
            current.data.setMoney(current.data.getMoney() - moneyFromPlayers);
            current = current.next;
        } while (current != first); // stop when you get back to the beginning of the loop
        player.setMoney(player.getMoney() + sum); // increase player's money by the amount gained from the other players
        // do different things depending on type
        if (type == null);
        else if (type.equals("advanceToGo")) {
            player.setLocation(spaces.getFirst()); // move them to Go
        }
        else if (type.equals("goToJail")) {
            System.out.println("You move to jail, directly to jail. Do not pass GO, do not collect $200");
            while(!player.getLocation().data.getType().equals("Jail")) { // move them until they get to Jail
                player.setLocation(player.getLocation().next);
            }
            player.setTurnsLeftInJail(3);
            System.out.println("End of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney() + "\n-------------------------------------------");
        }
        else if (type.equals("getOutOfJail")) {
            player.setComChestGetOutOfJail(true);
            System.out.println("The card has been added to your hand.");
            game.getCommunityChestCards().delete(this); // remove the card from the Community Chest deck while it's in the player's hand
        }
    }

}
