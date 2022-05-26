package org.campus02.ecom;

import java.util.Comparator;

public class BasketComparator implements Comparator<BasketData> {
    @Override
    public int compare(BasketData o1, BasketData o2) {
        int result = o1.getBuyingLocation().compareTo(o2.getBuyingLocation());

        if(result == 0)
            result = Double.compare(o2.getOrderTotal(), o1.getOrderTotal());

        return result;
    }
}
