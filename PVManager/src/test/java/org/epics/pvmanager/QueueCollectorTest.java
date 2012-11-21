/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager;

import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the QueueCollector.
 *
 * @author carcassi
 */
public class QueueCollectorTest {

    @Test
    public void inputOutput() {
        QueueCollector<Integer> collector = new QueueCollector<>(5);
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(0);
        assertThat(collector.getValue(), equalTo(Arrays.asList(0)));
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(1);
        collector.setValue(2);
        collector.setValue(3);
        assertThat(collector.getValue(), equalTo(Arrays.asList(1,2,3)));
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(1);
        collector.setValue(2);
        collector.setValue(3);
        collector.setValue(4);
        collector.setValue(5);
        collector.setValue(6);
        assertThat(collector.getValue(), equalTo(Arrays.asList(2,3,4,5,6)));
        assertThat(collector.getValue().size(), equalTo(0));
    }

    @Test
    public void setMaxSize() {
        QueueCollector<Integer> collector = new QueueCollector<>(5);
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(1);
        collector.setValue(2);
        collector.setValue(3);
        collector.setValue(4);
        collector.setValue(5);
        collector.setValue(6);
        collector.setMaxSize(2);
        assertThat(collector.getValue(), equalTo(Arrays.asList(5,6)));
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(1);
        collector.setValue(2);
        collector.setValue(3);
        collector.setMaxSize(5);
        collector.setValue(4);
        collector.setValue(5);
        collector.setValue(6);
        assertThat(collector.getValue(), equalTo(Arrays.asList(2,3,4,5,6)));
        assertThat(collector.getValue().size(), equalTo(0));
    }
}
