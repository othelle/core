package com.othelle.core.tree;

import com.othelle.core.tree.comparator.EqualComparator;
import com.othelle.core.tree.comparator.ValueComparator;
import com.othelle.core.tree.impl.DummyTreeItem;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */

public class TreeInflaterTest {
    @Test
    public void testInflate() throws Exception {
        DummyTreeItem task1 = new DummyTreeItem("Task1");
        DummyTreeItem task2 = new DummyTreeItem("Task1");
        DummyTreeItem task3 = new DummyTreeItem("Subtask1");

        task3.setParent(task1);

        EqualComparator comparator = new EqualComparator();
        TreeInflater inflater = new TreeInflater(comparator);

        ArrayList<DummyTreeItem> items = new ArrayList<DummyTreeItem>();
        items.add(task1);
        items.add(task2);
        items.add(task3);

        inflater.inflate(items);
        assertThat(items.indexOf(task3), Matchers.equalTo(items.indexOf(task1) + 1));
    }


    @Test
    public void testInflate2() throws Exception {
        DummyTreeItem task1 = new DummyTreeItem("Task2");
        DummyTreeItem task2 = new DummyTreeItem("Task1");
        DummyTreeItem task3 = new DummyTreeItem("Subtask1");

        task3.setParent(task1);

        ValueComparator<DummyTreeItem> comparator = new ValueComparator<DummyTreeItem>();
        TreeInflater<DummyTreeItem> inflater = new TreeInflater<DummyTreeItem>(comparator);

        ArrayList<DummyTreeItem> items = new ArrayList<DummyTreeItem>();
        items.add(task1);
        items.add(task2);
        items.add(task3);

        inflater.inflate(items);
        assertThat(items.indexOf(task1), Matchers.equalTo(1));
        assertThat(items.indexOf(task3), Matchers.equalTo(items.indexOf(task1) + 1));
    }


    @Test
    public void testInflate3() throws Exception {
        DummyTreeItem task1 = new DummyTreeItem("Task");
        DummyTreeItem task2 = new DummyTreeItem("Task");
        DummyTreeItem task3 = new DummyTreeItem("Subtask");
        DummyTreeItem task4 = new DummyTreeItem("Subtask");

        task4.setParent(task1);
        task3.setParent(task4);

        ValueComparator<DummyTreeItem> comparator = new ValueComparator<DummyTreeItem>(false);
        TreeInflater<DummyTreeItem> inflater = new TreeInflater<DummyTreeItem>(comparator);

        ArrayList<DummyTreeItem> items = new ArrayList<DummyTreeItem>();
        items.add(task1);
        items.add(task2);
        items.add(task3);

        inflater.inflate(items);
        assertThat(items.indexOf(task3), Matchers.equalTo(items.indexOf(task4) + 1));
        assertThat(items.indexOf(task4), Matchers.equalTo(items.indexOf(task1) + 1));
    }

    @Test
    public void testInflateOrdering() throws Exception {
        DummyTreeItem task1 = new DummyTreeItem("Task");
        DummyTreeItem task2 = new DummyTreeItem("Task");
        DummyTreeItem task3 = new DummyTreeItem("Subtask");
        DummyTreeItem task4 = new DummyTreeItem("Subtask");

        task4.setParent(task1);
        task3.setParent(task4);

        ValueComparator<DummyTreeItem> comparator = new ValueComparator<DummyTreeItem>(false);
        TreeInflater<DummyTreeItem> inflater = new TreeInflater<DummyTreeItem>(comparator);

        ArrayList<DummyTreeItem> items = new ArrayList<DummyTreeItem>();
        items.add(task4);
        items.add(task3);
        items.add(task2);
        items.add(task1);

        inflater.inflate(items);
        assertThat(items.indexOf(task3), Matchers.equalTo(items.indexOf(task4) + 1));
        assertThat(items.indexOf(task4), Matchers.equalTo(items.indexOf(task1) + 1));
    }
}