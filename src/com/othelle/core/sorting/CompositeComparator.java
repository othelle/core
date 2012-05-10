package com.othelle.core.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 2/23/12
 */
public class CompositeComparator<T> implements Comparator<T> {

    private List<Comparator<T>> comparators = new ArrayList<Comparator<T>>();

    public CompositeComparator(Comparator<T>... comparators) {
        this.comparators.addAll(Arrays.asList(comparators));
    }

    public void addComparator(Comparator<T> comparator) {
        comparators.add(comparator);
    }

    @Override
    public int compare(T o1, T o2) {
        for (Comparator<T> comparator : comparators) {
            int compare = comparator.compare(o1, o2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
