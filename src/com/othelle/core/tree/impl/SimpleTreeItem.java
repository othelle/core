package com.othelle.core.tree.impl;

import com.othelle.core.tree.TreeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleTreeItem<Value, Item extends TreeItem> implements TreeItem<Value, Item> {
    private Value value;
    private List<Item> children = new ArrayList<Item>();
    private Item parent;

    public SimpleTreeItem() {
        this(null);
    }

    public SimpleTreeItem(Value value) {
        this.value = value;
    }

    @Override
    public Item getParent() {
        return parent;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void setParent(Item parent) {
        if (this.parent != null) {
            this.parent.removeChild(this);
        }

        this.parent = parent;
        if (parent != null) {
            this.parent.addChild(this);
        }
    }

    @Override
    public void addChild(Item child) {
        children.add(child);
    }

    @Override
    public void removeChild(Item child) {
        children.remove(child);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Item getChild(int position) {
        return children.get(position);
    }

    @Override
    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public List<Item> getChildren() {
        return children;
    }

    public int getIndent() {
        TreeItem parent = this.getParent();
        int level = 0;
        while (parent != null) {
            level++;
            parent = parent.getParent();
        }
        return level;
    }
}
