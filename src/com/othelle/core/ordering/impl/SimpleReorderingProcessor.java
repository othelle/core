package com.othelle.core.ordering.impl;

import com.othelle.core.ordering.*;
import com.othelle.core.tree.TreeInflater;
import com.othelle.core.tree.TreeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleReorderingProcessor<T extends OrderedItem> implements ReorderingProcessor<T> {
    private List<OrderChangeListener<T>> orderListeners = new ArrayList<OrderChangeListener<T>>();
    private List<PositionChangeListener<T>> positionListeners = new ArrayList<PositionChangeListener<T>>();
    private OrderGenerator generator;


    public SimpleReorderingProcessor(OrderGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void addOrderChangeListener(OrderChangeListener<T> tOrderChangeListener) {
        orderListeners.add(tOrderChangeListener);
    }

    @Override
    public void removeOrderChangeListener(OrderChangeListener<T> tOrderChangeListener) {
        orderListeners.remove(tOrderChangeListener);
    }

    @Override
    public void addPositionChangeListener(PositionChangeListener<T> tPositionChangeListener) {
        positionListeners.add(tPositionChangeListener);
    }

    @Override
    public void removePositionChangeListener(PositionChangeListener<T> tPositionChangeListener) {
        positionListeners.remove(tPositionChangeListener);
    }

    protected void fireOrderChanged(OrderChangeEvent<T> event) {
        for (OrderChangeListener<T> orderListener : orderListeners) {
            orderListener.orderChanged(event);
        }
    }

    protected void firePositionChanged(PositionChangedEvent<T> event) {
        for (PositionChangeListener<T> listener : positionListeners) {
            listener.positionChanged(event);
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void reorder(List<T> items, int oldPosition, int newPosition) {
        if (oldPosition == newPosition)
            return;
        final T item = items.get(oldPosition);

        T before = null;
        T after = null;

        int delta = (oldPosition < newPosition) ? 1 : 0;


        if (items.size() > newPosition + delta) {
            after = items.get(newPosition + delta);
        }
        if (newPosition > 0) {
            before = items.get(newPosition - 1 + delta);
        }

        if (TreeUtil.isChild(item, after)) {
            return;
        }

        if (before == null || (item.getParent() != before.getParent())) {
            item.setParent(null);
        }

        if (after != null && after.getParent() != null && item.getParent() == null) {
            item.setParent(after.getParent());
        }

        if (TreeUtil.isChild(before, after)) {
            item.setParent(after.getParent());
        }

        List<OrderedItem> ts;

        if (item.getChildCount() > 0) {
            ts = new ArrayList<OrderedItem>();
            ts.add(item);
            new TreeInflater().inflate(ts);
        } else {
            ts = (List<OrderedItem>) Collections.singletonList(item);
        }

        final long[] itemOrder = generator.findItemOrder(ts, before, after);
        int factor = delta == 1 ? 0 : 1;
        if (itemOrder != null) {
            for (int i = 0; i < itemOrder.length; i++) {
                long l = itemOrder[i];
                final OrderedItem orderedItem = ts.get(i);

                items.remove(orderedItem);
                items.add(newPosition + (factor * i), (T) orderedItem);

                firePositionChanged(new PositionChangedEvent<T>((T) orderedItem, newPosition + (factor * i), oldPosition));

                final long oldOrder = orderedItem.getOrder();
                orderedItem.setOrder(l);
                fireOrderChanged(new OrderChangeEvent<T>((T) orderedItem, orderedItem.getOrder(), oldOrder));
            }
        } else {
            for (int i = 0; i < ts.size(); i++) {
                final OrderedItem orderedItem = ts.get(i);
                items.remove(orderedItem);
                items.add(newPosition + (factor * i), (T) orderedItem);
                firePositionChanged(new PositionChangedEvent<T>((T) orderedItem, newPosition + (factor * i), oldPosition));
            }

            long order = generator.findStartingOrder((List<OrderedItem>) items);

            for (int i = 0; i < items.size(); i++) {
                final T orderedItem = items.get(i);
                long oldOrder = orderedItem.getOrder();
                orderedItem.setOrder(generator.getSubsequentOrder(order, i));
                fireOrderChanged(new OrderChangeEvent<T>(orderedItem, orderedItem.getOrder(), oldOrder));
            }
        }
    }
}
