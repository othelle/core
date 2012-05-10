package com.othelle.core.ordering;

import com.othelle.core.tree.TreeItem;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface OrderedItem<ValueClass, SubItemsClass extends OrderedItem> extends TreeItem<ValueClass, SubItemsClass> {
    long getOrder();

    void setOrder(long order);
}
