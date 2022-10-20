public class TaxSpace extends BoardSpace {
    private int fixedTax; //Luxury is 75, Income is 200
    double dynamicTax; //Luxury is 0, income is 0.10
    public TaxSpace(int fixedTax, double dynamicTax) {
        super("Tax");
        this.fixedTax = fixedTax;
        this.dynamicTax = dynamicTax;
    }
}
