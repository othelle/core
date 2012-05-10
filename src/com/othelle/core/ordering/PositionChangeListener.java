package com.othelle.core.ordering;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface PositionChangeListener<T extends OrderedItem> {
    public void positionChanged(PositionChangedEvent<T> event);
}
