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

}
