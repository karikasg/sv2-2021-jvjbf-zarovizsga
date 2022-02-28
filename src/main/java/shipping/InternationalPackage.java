package shipping;

public class InternationalPackage implements Transportable {

    static final int PRICE = 1200;
    private int weight;
    private boolean isBreakable;
    private String destination;
    private int distance;

    public InternationalPackage(int weight, boolean isBreakable, String destination, int distance) {
        this.weight = weight;
        this.isBreakable = isBreakable;
        this.destination = destination;
        this.distance = distance;
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
        return  (isBreakable ? PRICE * 2 : PRICE) + distance * 10;
    }

    @Override
    public String getDestinationCountry() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int compareTo(InternationalPackage o) {
        return distance - o.getDistance() ;
    }
}
