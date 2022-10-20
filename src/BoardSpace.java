public class BoardSpace {
    private String name;
    private String type; // options: Property, GO, Chance, Community Chest, Utility, Railroad, To Jail, Tax, Blank
    public BoardSpace(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
