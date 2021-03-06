/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.vtype.table;

import java.util.Arrays;
import org.epics.util.array.ArrayDouble;
import org.epics.vtype.VTable;
import org.epics.vtype.ValueFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.epics.vtype.table.VTableFactory.*;
import static org.epics.vtype.ValueFactory.*;

/**
 *
 * @author carcassi
 */
public class ValueFilterTest {
    
    @Test
    public void filterRowNumber() {
        VTable table1 = newVTable(column("Rack", newVStringArray(Arrays.asList("A", "A", "B"), alarmNone(), timeNow())),
                                 column("Slot", newVDoubleArray(new ArrayDouble(1,2,3), alarmNone(), timeNow(), displayNone())),
                                 column("CPU", newVStringArray(Arrays.asList("286", "286", "386"), alarmNone(), timeNow())));
        ValueFilter valueFilter = new ValueFilter(table1, "Slot", ValueFactory.toVType(2));
        assertThat(valueFilter.filterRow(0), equalTo(false));
        assertThat(valueFilter.filterRow(1), equalTo(true));
        assertThat(valueFilter.filterRow(2), equalTo(false));
    }
    
    @Test
    public void filterRowString() {
        VTable table1 = newVTable(column("Rack", newVStringArray(Arrays.asList("A", "A", "B"), alarmNone(), timeNow())),
                                 column("Slot", newVDoubleArray(new ArrayDouble(1,2,3), alarmNone(), timeNow(), displayNone())),
                                 column("CPU", newVStringArray(Arrays.asList("286", "286", "386"), alarmNone(), timeNow())));
        ValueFilter valueFilter = new ValueFilter(table1, "CPU", ValueFactory.toVType("286"));
        assertThat(valueFilter.filterRow(0), equalTo(true));
        assertThat(valueFilter.filterRow(1), equalTo(true));
        assertThat(valueFilter.filterRow(2), equalTo(false));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void filterRowTypeMismatch1() {
        VTable table1 = newVTable(column("Rack", newVStringArray(Arrays.asList("A", "A", "B"), alarmNone(), timeNow())),
                                 column("Slot", newVDoubleArray(new ArrayDouble(1,2,3), alarmNone(), timeNow(), displayNone())),
                                 column("CPU", newVStringArray(Arrays.asList("286", "286", "386"), alarmNone(), timeNow())));
        ValueFilter valueFilter = new ValueFilter(table1, "CPU", ValueFactory.toVType(2));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void filterRowTypeMismatch2() {
        VTable table1 = newVTable(column("Rack", newVStringArray(Arrays.asList("A", "A", "B"), alarmNone(), timeNow())),
                                 column("Slot", newVDoubleArray(new ArrayDouble(1,2,3), alarmNone(), timeNow(), displayNone())),
                                 column("CPU", newVStringArray(Arrays.asList("286", "286", "386"), alarmNone(), timeNow())));
        ValueFilter valueFilter = new ValueFilter(table1, "Slot", ValueFactory.toVType("286"));
    }
}