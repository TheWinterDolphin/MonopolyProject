public class Railroad extends BoardSpace {
    private Player owner;
    private int rent;
    private String name;
    private int numOwnedBySamePlayer;

    public Railroad(String name, int rent) {
        super("Railroad");
        this.name = name;
        this.rent = rent;
        numOwnedBySamePlayer = 0;
    }
}
