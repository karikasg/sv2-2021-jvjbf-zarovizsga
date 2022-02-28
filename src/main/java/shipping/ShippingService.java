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
            int value = 1;
            if (transport.containsKey(pack.getDestinationCountry())) {
                value = transport.get(pack.getDestinationCountry())+1;
            }
            transport.put(pack.getDestinationCountry(), value);
        }
        return transport;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        List<Transportable> result = new ArrayList<>();

        for (Transportable pack : packages) {
            if (pack.getClass() == InternationalPackage.class) {
                result.add(pack);
            }
        }
//        result.stream().sorted(Comparator.comparing((InternationalPackage o) -> o.getDistance())).toList();
        return result;
    }
}
