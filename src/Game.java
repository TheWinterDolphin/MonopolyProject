public class Game {
    private CircularLinkedList<BoardSpace> spaces;
    private CircularLinkedList<Player> playerTurnOrder;

    private void printBoard() {
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------");

        //TopRow
        String topRowString = "| ";

        /*
                | [JAIL] [ C1 ] [ EC ] [ C2 ] [ C3 ] [ R2 ] [ D1 ] [ CC ] [ D2 ] [ D3 ] [FrPk] |
                | [ B3 ] -------------------------------------------------------------- [ E1 ] |
                | [ B2 ] |                                                            | [ CH ] |
                | [ CH ] |                                                            | [ E2 ] |
                | [ B1 ] |                                                            | [ E3 ] |
                | [ R1 ] |                      M O N O P O L Y                       | [ R3 ] |
                | [ IT ] |                                                            | [ F1 ] |
                | [ A2 ] |                                                            | [ F2 ] |
                | [ CC ] |                                                            | [ WW ] |
                | [ A1 ] -------------------------------------------------------------- [ F3 ] |
                | [ GO ] [ H2 ] [ LT ] [ H1 ] [ CH ] [ R4 ] [ G3 ] [ CC ] [ G2 ] [ G1 ] [ToJL] |
                --------------------------------------------------------------------------------
                */
    }

    public void colorSpaceString(String spaceName, String uncoloredOutput) {
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
        }

        System.out.print("" + chars[0] + chars[1] + chars[2] + chars[3] + chars[4] + chars[5]);
    }
}
