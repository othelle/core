package com.othelle.core.ordering.impl;

import com.othelle.core.ordering.OrderGenerator;
import com.othelle.core.ordering.OrderedItem;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleOrderGenerator implements OrderGenerator {
    public static final int STEP = 2 << 15;

    @Override
    public long findStartingOrder(List<OrderedItem> items) {
        long minValue = Long.MAX_VALUE;
        for (OrderedItem item : items) {
            minValue = Math.min(minValue, item.getOrder());
        }
        return (minValue > Long.MAX_VALUE / 2 || minValue < Long.MIN_VALUE / 2) ? 0 : minValue;
    }

    @Override
    public long getSubsequentOrder(long startingOrder, int position) {
        return startingOrder + position * STEP;
    }

    @Override
    public long[] findItemOrder(List<OrderedItem> items, OrderedItem before, OrderedItem after) {
        if (items == null || items.size() == 0) {
            throw new IllegalArgumentException("Items can't be null or empty");
        }

        long startPoint = 0;
        long delta = STEP * (items.size() + 1);

        if (before == null && after == null) {
            throw new IllegalArgumentException("Before and After can't be null at one time. ");
        } else if (before == null) {
            if (Long.MIN_VALUE + delta > after.getOrder()) {
                return null;
            }
            startPoint = after.getOrder() - delta;


        } else if (after == null) {
            if (Long.MAX_VALUE - delta < before.getOrder()) {
                return null;
            }
            startPoint = before.getOrder();

        } else {
            delta = after.getOrder() - before.getOrder();
            startPoint = before.getOrder();
        }

        long miniStep = delta / (items.size() + 1);
        if (miniStep > 0) {
            long[] result = new long[items.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = startPoint + (i + 1) * miniStep;
            }
            return result;
        } else {
            return null;
        }
    }
}
