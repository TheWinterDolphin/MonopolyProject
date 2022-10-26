public class CommunityChest {
    private int moneyFromBank; // positive to receive, negative to give
    private String type; // goToJail, advanceToGo, getOutOfJail
    private int moneyFromPlayers; // positive to receive

    private String message;

    public CommunityChest(String message, int moneyFromBank, String type, int moneyFromPlayers) {
        this.message = message;
        this.moneyFromBank = moneyFromBank;
        this.type = type;
        this.moneyFromPlayers = moneyFromPlayers;
    }

    public void useCard(Player player, CircularLinkedList<Player> players, CircularLinkedList<BoardSpace> spaces) {
        System.out.println(message);
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
        if (type == null);
        else if (type.equals("advanceToGo")) {
            player.setLocation(spaces.getFirst()); // move them to Go
        }
        else if (type.equals("goToJail")) {
            System.out.println("You move to jail, directly to jail. Do not pass GO, do not collect $200");
            while(!player.getLocation().data.getType().equals("Jail")) {
                player.setLocation(player.getLocation().next);
            }
            player.setTurnsLeftInJail(3);
            System.out.println("End of " + player.getName() + "'s Turn | Current Money: $" + player.getMoney() + "\n-------------------------------------------");
        }
        else if (type.equals("getOutOfJail")) {
            player.setNumGetOutOfJailFree(player.getNumGetOutOfJailFree() + 1);
            System.out.println("The card has been added to your hand.");
        }
    }

}
