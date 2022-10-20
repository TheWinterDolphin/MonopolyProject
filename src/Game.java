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

    private String colorSpaceString(String spaceName, String uncoloredOutput) {
        String RESET = "\033[0m";

        String char0 = String.valueOf(uncoloredOutput.charAt(0));
        String char1 = String.valueOf(uncoloredOutput.charAt(1));
        String char2 = String.valueOf(uncoloredOutput.charAt(2));
        String char3 = String.valueOf(uncoloredOutput.charAt(3));
        String char4 = String.valueOf(uncoloredOutput.charAt(4));
        String char5 = String.valueOf(uncoloredOutput.charAt(5));

        String[] chars = new String[]{char0, char1, char2, char3, char4, char5};

        Link<Player> currentPlayer = playerTurnOrder.getFirst();
        if (currentPlayer.data.getLocation().data.getType().equals(spaceName)) {
            chars[0] = currentPlayer.data.getColorString() + chars[0] + RESET;
        }
        currentPlayer = currentPlayer.next;

        int i = 1;
        while(currentPlayer != playerTurnOrder.getFirst()) {
            if (currentPlayer.data.getLocation().data.getType().equals(spaceName)) {
                chars[i] = currentPlayer.data.getColorString() + chars[i] + RESET;
            }
            currentPlayer = currentPlayer.next;
        }
        
        return ("" + chars[0] + chars[1] + chars[2] + chars[3] + chars[4] + chars[5]);
    }
}
