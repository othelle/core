package com.othelle.core.tree;

import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public interface TreeItem<ValueClass, SubItemsClass extends TreeItem> {
    SubItemsClass getParent();

    void setParent(SubItemsClass parent);

    void addChild(SubItemsClass child);

    void removeChild(SubItemsClass child);

    int getChildCount();

    SubItemsClass getChild(int position);

    ValueClass getValue();

    List<SubItemsClass> getChildren();

    int getIndent();
}
