package com.othelle.core.ordering;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface OrderChangeListener<T extends OrderedItem> {
    public void orderChanged(OrderChangeEvent<T> event);
}
