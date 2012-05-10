package com.othelle.core.ordering.impl;


import com.othelle.core.ordering.OrderedItem;
import org.hamcrest.Matchers;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */
public class SimpleOrderGeneratorTest {
    private static final long START = 1000L;
    SimpleOrderGenerator generator = new SimpleOrderGenerator();


    ArrayList<OrderedItem> emptyOrderedItems = new ArrayList<OrderedItem>();
    ArrayList<OrderedItem> defaultOrderedItems = new ArrayList<OrderedItem>();
    ArrayList<OrderedItem> miniStepOrderedItems = new ArrayList<OrderedItem>();


    @Before
    public void configureCollections() throws Exception {
        initOrderedItem(defaultOrderedItems, 1000);
        initOrderedItem(miniStepOrderedItems, 2);

    }

    private void initOrderedItem(ArrayList<OrderedItem> items, int step) {
        long start = START;
        for (int i = 0; i < 10; i++) {
            SimpleOrderedItem e1 = new SimpleOrderedItem("SubItem1", start + (3 * i + 1) * step);
            SimpleOrderedItem e2 = new SimpleOrderedItem("SubItem2", start + (3 * i + 2) * step);

            SimpleOrderedItem item = new SimpleOrderedItem("Item " + i, start + 3 * i);
            item.addChild(e1);
            item.addChild(e2);
            items.add(item);
            items.add(e1);
            items.add(e2);
        }
    }


    @org.junit.Test
    public void testFindStartingOrder() throws Exception {
        assertThat(generator.findStartingOrder(emptyOrderedItems), equalTo(0L));

        assertThat(generator.findStartingOrder(defaultOrderedItems), equalTo(START));

        miniStepOrderedItems.get(0).setOrder(Long.MIN_VALUE / 2 - 1);
        assertThat(generator.findStartingOrder(miniStepOrderedItems), equalTo(0L));

        for (OrderedItem orderedItem : miniStepOrderedItems) {
            orderedItem.setOrder(Long.MAX_VALUE / 2 + 1);
        }

        assertThat(generator.findStartingOrder(miniStepOrderedItems), equalTo(0L));

    }

    @org.junit.Test
    public void testFindItemOrder() throws Exception {
        List<OrderedItem> defaultListItems = Arrays.asList(defaultOrderedItems.get(9), defaultOrderedItems.get(10), defaultOrderedItems.get(11));
        long[] itemOrder = generator.findItemOrder(defaultListItems, defaultOrderedItems.get(0), defaultOrderedItems.get(1));
        assertThat(itemOrder, Matchers.equalTo(new long[]{1250, 1500, 1750}));

        OrderedItem firstItem = defaultOrderedItems.get(0);
        OrderedItem lastItem = defaultOrderedItems.get(defaultListItems.size() - 1);
        itemOrder = generator.findItemOrder(defaultListItems, null, lastItem);
        assertThat(itemOrder, Matchers.equalTo(new long[]{lastItem.getOrder() - SimpleOrderGenerator.STEP * 3,
                lastItem.getOrder() - SimpleOrderGenerator.STEP * 2, lastItem.getOrder() - SimpleOrderGenerator.STEP}));


        itemOrder = generator.findItemOrder(defaultListItems, firstItem, null);
        assertThat(itemOrder, Matchers.equalTo(new long[]{firstItem.getOrder() + SimpleOrderGenerator.STEP,
                firstItem.getOrder() + SimpleOrderGenerator.STEP * 2, firstItem.getOrder() + SimpleOrderGenerator.STEP * 3}));


        lastItem = defaultOrderedItems.get(0);
        lastItem.setOrder(Long.MIN_VALUE + 1);
        itemOrder = generator.findItemOrder(defaultListItems,  null, lastItem);
        assertThat(itemOrder, Matchers.nullValue());

        firstItem.setOrder(Long.MAX_VALUE - 100);
        itemOrder = generator.findItemOrder(defaultListItems, firstItem, null);
        assertThat(itemOrder, Matchers.nullValue());

    }
}
