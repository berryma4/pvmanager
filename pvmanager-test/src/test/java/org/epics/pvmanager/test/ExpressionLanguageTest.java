/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.test;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.epics.pvmanager.test.ExpressionLanguage.*;
import org.epics.pvmanager.expression.DesiredRateExpression;

/**
 *
 * @author carcassi
 */
public class ExpressionLanguageTest {

    @Test
    public void vType1() {
        DesiredRateExpression<Integer> counter = counter();
        assertThat(counter.getFunction().readValue(), equalTo(0));
        assertThat(counter.getFunction().readValue(), equalTo(1));
        assertThat(counter.getFunction().readValue(), equalTo(2));
        assertThat(counter.getFunction().readValue(), equalTo(3));
    }
}