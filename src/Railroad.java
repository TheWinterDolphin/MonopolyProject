/* Jaya */
public class Railroad extends BoardSpace {
    private Player owner; //Which player owns the railroad

    private int price; //Cost to buy railroad
    private int rent; //Cost for landing on railroad and paying owner

    public Railroad(String spaceName, String realName) {
        super(spaceName, realName,"Railroad");
        this.rent = 25; //Rent for 1 railroad is $25, but when multiple are owned it doubles for each additional railroad (handled in Game class in railroadLand)
        this.price = 200;
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

    public int getPrice() {
        return price;
    }
}
