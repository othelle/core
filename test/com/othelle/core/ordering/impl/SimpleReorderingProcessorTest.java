package com.othelle.core.ordering.impl;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleReorderingProcessorTest {
    List<SimpleOrderedItem> items = new ArrayList<SimpleOrderedItem>();
    private SimpleOrderedItem test5;
    private SimpleOrderedItem test6;
    private SimpleOrderedItem test4;
    private SimpleOrderedItem test3;
    private SimpleOrderedItem test2;
    private SimpleOrderedItem test1;

    @Before
    public void setup() {
        test1 = new SimpleOrderedItem("Test1");
        items.add(test1);
        test2 = new SimpleOrderedItem("Test2");
        items.add(test2);
        test3 = new SimpleOrderedItem("Test3");
        items.add(test3);
        test4 = new SimpleOrderedItem("Test4");
        items.add(test4);
        test5 = new SimpleOrderedItem("Test5");
        items.add(test5);
        test6 = new SimpleOrderedItem("Test6");
        test6.setParent(test5);
        items.add(test6);
    }

    @Test
    public void testReorderSimple() throws Exception {
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1000);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 4, 1);

        assertThat(items.indexOf(test6), Matchers.equalTo(2));
        assertThat(items.indexOf(test5), Matchers.equalTo(1));


        processor.reorder(items, 1, 0);
        assertThat(items.indexOf(test6), Matchers.equalTo(1));
        assertThat(items.indexOf(test5), Matchers.equalTo(0));

        assertThat(test5.getOrder(), Matchers.lessThan(test6.getOrder()));
        assertThat(test6.getOrder(), Matchers.lessThan(test1.getOrder()));
    }

    @Test
    public void testReorderSimpleClose() throws Exception {
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 4, 1);

        assertThat(items.indexOf(test6), Matchers.equalTo(2));
        assertThat(items.indexOf(test5), Matchers.equalTo(1));
        assertThat(items.size(), Matchers.equalTo(6));

        processor.reorder(items, 1, 0);
        assertThat(items.indexOf(test6), Matchers.equalTo(1));
        assertThat(items.indexOf(test5), Matchers.equalTo(0));
        assertThat(items.size(), Matchers.equalTo(6));

        assertThat(test5.getOrder(), Matchers.lessThan(test6.getOrder()));
        assertThat(test6.getOrder(), Matchers.lessThan(test1.getOrder()));
        assertThat(items.size(), Matchers.equalTo(6));

        processor.reorder(items, 1, items.size() - 1);
        assertThat(items.indexOf(test6), Matchers.equalTo(5));
        assertThat(items.indexOf(test4), Matchers.equalTo(4));
        assertThat(test6.getOrder(), Matchers.greaterThan(test4.getOrder()));
        assertThat(items.size(), Matchers.equalTo(6));
    }

    @Test
    public void testReorderSimpleClose1() throws Exception {
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 4, 0);
        processor.reorder(items, 1, items.size() - 1);
        assertThat(items.indexOf(test6), Matchers.equalTo(5));
        assertThat(items.indexOf(test4), Matchers.equalTo(4));
        assertThat(test6.getOrder(), Matchers.greaterThan(test4.getOrder()));
        assertThat(test6.getParent(), Matchers.nullValue());
        assertThat(items.size(), Matchers.equalTo(6));
    }


    @Test
    public void testReorderSimpleParentCheck() throws Exception {
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 0, 4);
        assertThat(items.indexOf(test1), Matchers.equalTo(4));
        assertThat(items.indexOf(test5), Matchers.equalTo(3));
        assertThat(test1.getParent(), Matchers.equalTo(test5));
        assertThat(test1.getOrder(), Matchers.greaterThan(test4.getOrder()));
        assertThat(items.size(), Matchers.equalTo(6));
    }


    @Test
    public void testReorderSimpleParentToChild() throws Exception {

        test5.setParent(test4);
        test6.setParent(test4);
        SimpleOrderedItem test7 = new SimpleOrderedItem("Test7");
        test7.setParent(test3);
        items.add(test7);
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 3, 4);
        assertThat(items.indexOf(test4), Matchers.equalTo(3));
    }

    @Test
    public void testParentWhenMoving() {
        test4.setParent(test3);
        test5.setParent(test4);
        test6.setParent(test4);
        SimpleOrderedItem test7 = new SimpleOrderedItem("Test7");
        test7.setParent(test3);
        items.add(test7);
        long index = 0;
        for (SimpleOrderedItem item : items) {
            item.setOrder(index += 1);
        }

        final SimpleReorderingProcessor<SimpleOrderedItem> processor = new SimpleReorderingProcessor<SimpleOrderedItem>(new SimpleOrderGenerator());
        processor.reorder(items, 4, 5);
        assertThat(test5.getParent(), Matchers.equalTo(test4));
    }
}
