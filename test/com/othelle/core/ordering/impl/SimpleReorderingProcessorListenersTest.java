package com.othelle.core.ordering.impl;

import com.othelle.core.ordering.OrderChangeEvent;
import com.othelle.core.ordering.OrderChangeListener;
import com.othelle.core.ordering.PositionChangeListener;
import com.othelle.core.ordering.PositionChangedEvent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * User: v.vlasov
 * Date: 12/7/11
 */

@RunWith(JMock.class)
public class SimpleReorderingProcessorListenersTest {
    private final Mockery context = new JUnit4Mockery();

    private final OrderChangeListener orderListener1 = context.mock(OrderChangeListener.class);
    private final PositionChangeListener positionListener1 = context.mock(PositionChangeListener.class);

    @Test
    public void testListeners() throws Exception {
        SimpleReorderingProcessor processor = new SimpleReorderingProcessor(new SimpleOrderGenerator());


        final OrderChangeEvent orderChangeEvent = new OrderChangeEvent(null, 1, 1);
        final PositionChangedEvent positionChangedEvent = new PositionChangedEvent(null, 1, 1);

        context.checking(new Expectations() {{
            oneOf(orderListener1).orderChanged(orderChangeEvent);
            oneOf(positionListener1).positionChanged(positionChangedEvent);
        }});

        processor.addOrderChangeListener(orderListener1);
        processor.addPositionChangeListener(positionListener1);

        processor.fireOrderChanged(orderChangeEvent);
        processor.firePositionChanged(positionChangedEvent);
    }
}
