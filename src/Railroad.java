public class Railroad extends BoardSpace {
    private Player owner;
    private int rent;
    private int numOwnedBySamePlayer;

    public Railroad(String name, int rent) {
        super(name, "Railroad");
        this.rent = rent;
        numOwnedBySamePlayer = 0;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getNumOwnedBySamePlayer() {
        return numOwnedBySamePlayer;
    }

    public void setNumOwnedBySamePlayer(int numOwnedBySamePlayer) {
        this.numOwnedBySamePlayer = numOwnedBySamePlayer;
    }
}
