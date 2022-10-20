public class Utility extends BoardSpace {
    private Player owner;
    private boolean bothOwned; // rent is dice roll * 4 if 1 owned, * 10 if both owned
    private int price;

    public Utility(int price) {
        super("Utility");
        this.price = price;
    }

}
