package com.othelle.core.ordering;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class OrderChangeEvent<T extends OrderedItem> {
    T item;
    long oldOrder;
    long newOrder;

    public OrderChangeEvent(T item, long oldOrder, long newOrder) {
        this.item = item;
        this.oldOrder = oldOrder;
        this.newOrder = newOrder;
    }

    public T getItem() {
        return item;
    }

    public long getOldOrder() {
        return oldOrder;
    }

    public long getNewOrder() {
        return newOrder;
    }
}
