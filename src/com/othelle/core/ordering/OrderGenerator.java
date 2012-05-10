package com.othelle.core.ordering;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface OrderGenerator {
    long findStartingOrder(List<OrderedItem> items);

    long getSubsequentOrder(long startingOrder, int position);

    long[] findItemOrder(List<OrderedItem> item, OrderedItem before, OrderedItem after);
}
