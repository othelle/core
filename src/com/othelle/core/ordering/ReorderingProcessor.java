package com.othelle.core.ordering;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface ReorderingProcessor<T extends OrderedItem> {
    void addOrderChangeListener(OrderChangeListener<T> listener);

    void removeOrderChangeListener(OrderChangeListener<T> listener);

    void addPositionChangeListener(PositionChangeListener<T> listener);

    void removePositionChangeListener(PositionChangeListener<T> listener);

    void reorder(List<T> items, int oldPosition, int newPosition);
}
