package com.othelle.core.ordering;

import java.util.Comparator;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class OrderComparator<T extends OrderedItem> implements Comparator<T> {
    @Override
    public int compare(OrderedItem o1, OrderedItem o2) {
        return (int) Math.signum(o1.getOrder() - o2.getOrder());
    }
}
