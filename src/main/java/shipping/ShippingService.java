package shipping;

import java.util.*;

public class ShippingService {

    List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable pack) {
        packages.add(pack);
    }

    public List<Transportable> getPackages() {
        return packages;
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        List<Transportable> result = new ArrayList<>();
        for (Transportable pack : packages) {
            if (pack.isBreakable() == breakable && pack.getWeight() >= weight) {
                result.add(pack);
            }
        }
        return result;
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> transport = new HashMap<>();

        for (Transportable pack : packages) {
            String destinationCountry = pack.getDestinationCountry();
            int value = 1;
            if (transport.containsKey(destinationCountry)) {
                value = transport.get(destinationCountry)+1;
            }
            transport.put(destinationCountry, value);
        }
        return transport;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.
                stream().
                filter(o -> o.getClass() == InternationalPackage.class).
                sorted(Comparator.comparing(o -> ((InternationalPackage) o).getDistance())).
                toList();
    }
}
