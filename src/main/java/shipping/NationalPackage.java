package shipping;

public class NationalPackage implements Transportable {

    static final int PRICE = 1000;
    private int weight;
    private boolean isBreakable;

    public NationalPackage(int weight, boolean isFragile) {
        this.weight = weight;
        this.isBreakable = isFragile;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return isBreakable;
    }

    @Override
    public int calculateShippingPrice() {
        return isBreakable ? 2*PRICE : PRICE;
    }

}
