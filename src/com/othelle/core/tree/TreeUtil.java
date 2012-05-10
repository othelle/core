package com.othelle.core.tree;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/8/11
 */
public class TreeUtil {
    public static boolean isChild(TreeItem item, TreeItem candidate) {
        while (candidate != null) {
            if (candidate == item) return true;
            candidate = candidate.getParent();
        }
        return false;
    }

    public static <T extends TreeItem> T findTheSameLevel(List<T> items, int index) {
        if (items.size() < index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("");
        }

        if (index == 0) return null;

        T item = items.get(index);
        T sameLevelItem = null;
        for (int i = index - 1; i >= 0; i--) {
            T candidate = items.get(i);

            int candidateIndent = candidate.getIndent();
            int indent = item.getIndent();
            if (candidateIndent == indent) {
                sameLevelItem = candidate;
                break;
            }
            if (candidateIndent < indent) break;
        }
        return sameLevelItem;
    }
}
