public class Railroad extends BoardSpace {
    private Player owner;
    private int rent;

    public Railroad(String name, int rent) {
        super(name, "Railroad");
        this.rent = rent;
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
}
