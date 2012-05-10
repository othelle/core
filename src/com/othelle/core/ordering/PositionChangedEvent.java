package com.othelle.core.ordering;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class PositionChangedEvent<T extends OrderedItem> {
    T item;
    int oldOrder;
    int newOrder;

    public PositionChangedEvent(T item, int oldOrder, int newOrder) {
        this.item = item;
        this.oldOrder = oldOrder;
        this.newOrder = newOrder;
    }




    public T getItem() {
        return item;
    }

    public int getOldOrder() {
        return oldOrder;
    }

    public int getNewOrder() {
        return newOrder;
    }
}
