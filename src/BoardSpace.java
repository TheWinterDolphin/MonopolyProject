public class BoardSpace {
    private String type; // options: Property, GO, Chance, Community Chest, Utility, Railroad, To Jail, Tax, Blank
    public BoardSpace(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
