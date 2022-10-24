import java.util.Scanner;

public class Game {
    private CircularLinkedList<BoardSpace> spaces;
    private CircularLinkedList<Player> playerTurnOrder;
    private Scanner input = new Scanner(System.in);

    public Game() {

    }

    public void setup() {
        playerTurnOrder = inputPlayers();
        // initialize spaces
    }

    public CircularLinkedList<Player> inputPlayers() {
        CircularLinkedList<Player> players = new CircularLinkedList<>();
        int numPlayers = inputPlayerInt();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Name of Player " + i + ": ");
            String name = input.nextLine();
            Player player = new Player(name);
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
}