package com.othelle.core.tree.comparator;

import com.othelle.core.tree.TreeItem;

import java.util.Comparator;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class ValueComparator<T extends TreeItem<? extends Comparable, ?>> implements Comparator<T> {

    private boolean asc;

    public ValueComparator() {
        this(true);
    }

    public ValueComparator(boolean asc) {
        this.asc = asc;
    }


    @Override
    @SuppressWarnings(value = "unchecked")
    public int compare(T o1, T o2) {
        Comparable value1 = o1.getValue();
        Comparable value2 = o2.getValue();
        return value1.compareTo(value2) * (asc ? 1 : -1);
    }
}
