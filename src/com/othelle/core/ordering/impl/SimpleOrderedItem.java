package com.othelle.core.ordering.impl;

import com.othelle.core.ordering.OrderedItem;
import com.othelle.core.tree.impl.SimpleTreeItem;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleOrderedItem extends SimpleTreeItem<String, SimpleOrderedItem> implements OrderedItem<String, SimpleOrderedItem> {
    private long order;

    public SimpleOrderedItem(String value) {
        this(value, -1);
    }

    public SimpleOrderedItem(String value, long order) {
        super(value);
        this.order = order;
    }

    @Override
    public long getOrder() {
        return order;
    }

    @Override
    public void setOrder(long order) {
        this.order = order;
    }
}
