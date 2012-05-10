package com.othelle.core.ordering;

import com.othelle.core.ordering.impl.SimpleOrderGenerator;
import com.othelle.core.ordering.impl.SimpleReorderingProcessor;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public abstract class OrderingFactory<T extends OrderedItem> {
    public static <T extends OrderedItem> OrderingFactory<T> getInstance() {
        return new OrderingFactory<T>() {
        };
    }

    public ReorderingProcessor<T> getReorderingProcessor() {
        return new SimpleReorderingProcessor<T>(getOrderGenerator());
    }

    public OrderGenerator getOrderGenerator() {
        return new SimpleOrderGenerator();
    }
}
