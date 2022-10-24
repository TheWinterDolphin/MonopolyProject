import java.util.Scanner;

public class Game {
    private CircularLinkedList<BoardSpace> spaces;
    private CircularLinkedList<Player> playerTurnOrder;
    private Scanner input = new Scanner(System.in);
    private boolean isGameOver;
    private Link<Player> currentPlayer;

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
    public void next() {
        currentPlayer = currentPlayer.next;
        playerTurn(currentPlayer.data);

        Link<Player> currentPlayerCheck = currentPlayer;

        checkIfBankrupt(currentPlayerCheck.data);
        currentPlayerCheck = currentPlayerCheck.next;

        while(currentPlayerCheck != currentPlayer) {
            checkIfBankrupt(currentPlayerCheck.data);
            currentPlayerCheck = currentPlayerCheck.next;
        }

        checkGameOver();
    }

    private void playerTurn(Player player) {

    }

    private void checkIfBankrupt(Player player) {

    }

    /*Andrew*/
    private void checkGameOver() {
        if (playerTurnOrder.getFirst() == playerTurnOrder.getFirst().next) {
            System.out.print("" + playerTurnOrder.getFirst().data.getName() + " wins!");
            isGameOver = true;
        }
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
        bottomRowString += colorSpaceString("ToJL", "[ToJL]");
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


}
