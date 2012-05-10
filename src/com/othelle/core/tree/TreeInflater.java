package com.othelle.core.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class TreeInflater<ResultItem extends TreeItem> {
    private Comparator<ResultItem> comparator;

    public TreeInflater() {
        this(null);
    }

    public TreeInflater(Comparator<ResultItem> comparator) {
        this.comparator = comparator;
    }

    public void inflate(List<ResultItem> items) {
        ArrayList<ResultItem> rootItems = new ArrayList<ResultItem>();
        if (items.size() == 1) {
            rootItems.addAll(items);
        } else {
            for (ResultItem item : items) {
                if (item.getParent() == null) {
                    rootItems.add(item);
                }
            }
        }

        List<ResultItem> newOrder = inflate0(rootItems, new ArrayList<ResultItem>(items.size()));
        items.clear();
        items.addAll(newOrder);
    }

    @SuppressWarnings(value = "unchecked")
    protected List<ResultItem> inflate0(List<ResultItem> currentLevel, List<ResultItem> accumulator) {
        if (comparator != null) {
            Collections.sort(currentLevel, comparator);
        }
        for (ResultItem parent : currentLevel) {
            accumulator.add(parent);
            List<ResultItem> children = parent.getChildren();
            if (children != null && children.size() > 0) {
                inflate0(children, accumulator);
            }
        }
        return accumulator;
    }
}
