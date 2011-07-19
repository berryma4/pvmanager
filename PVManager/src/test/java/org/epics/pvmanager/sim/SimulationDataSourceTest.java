/*
 * Copyright 2010-11 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.sim;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import org.epics.pvmanager.PVReader;
import org.epics.pvmanager.data.VDouble;
import org.epics.pvmanager.PVManager;
import org.epics.pvmanager.PVReaderListener;
import org.epics.pvmanager.util.TimeDuration;
import org.epics.pvmanager.data.VMultiDouble;
import org.junit.Test;
import static org.epics.pvmanager.data.ExpressionLanguage.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.epics.pvmanager.util.TimeDuration.*;


/**
 *
 * @author carcassi
 */
public class SimulationDataSourceTest {

    @Test
    public void ramp1() throws InterruptedException {
        // Read data from a ramp PVReader
        final AtomicInteger sampleCounter = new AtomicInteger();
        PVManager.setDefaultDataSource(SimulationDataSource.simulatedData());
        final PVReader<VDouble> pv = PVManager.read(vDouble("ramp(0,10,1,.05)"))
                .every(hz(200));
        pv.addPVReaderListener(new PVReaderListener() {

            @Override
            public void pvChanged() {
//                // Check that the value is right
                assertTrue("Counter was " + sampleCounter.get() + " and value was " + pv.getValue().getValue().intValue(),
                        sampleCounter.get() == pv.getValue().getValue().intValue() ||
                        sampleCounter.get() == pv.getValue().getValue().intValue() + 11 ||
                        sampleCounter.get() == pv.getValue().getValue().intValue() + 22);
                sampleCounter.incrementAndGet();
            }
        });
        Thread.sleep(1000);
        pv.close();
        // After 10s, expect about 20 samples
        assertTrue("Less than 19 calls", sampleCounter.get() >= 19);
        assertTrue("More than 21 calls", sampleCounter.get() <= 21);
        pv.removePVReaderListener(null);
    }

    @Test
    public void ramp2() throws InterruptedException {
        // Read data from a ramp PVReader
        final AtomicInteger sampleCounter = new AtomicInteger();
        PVManager.setDefaultDataSource(SimulationDataSource.simulatedData());
        final PVReader<VDouble> pv = PVManager.read(vDouble("ramp(0,10,1,0.2)"))
                .every(hz(50));
        pv.addPVReaderListener(new PVReaderListener() {

            @Override
            public void pvChanged() {
                // Check that the value is right
                assertTrue("Counter was " + sampleCounter.get() + " and value was " + pv.getValue().getValue().intValue(),
                        sampleCounter.get() == pv.getValue().getValue().intValue() ||
                        sampleCounter.get() == pv.getValue().getValue().intValue() + 11);
                sampleCounter.incrementAndGet();
            }
        });
        Thread.sleep(2000);
        pv.close();
        // After 10s, expect about 10 samples
        assertTrue("Less than 9 calls", sampleCounter.get() >= 9);
        assertTrue("More than 11 calls", sampleCounter.get() <= 11);
    }

    @Test
    public void synchRamp() throws InterruptedException {
        // Read data from a ramp PVReader
        final AtomicInteger sampleCounter = new AtomicInteger();
        final AtomicInteger failedComparisons = new AtomicInteger();
        PVManager.setDefaultDataSource(SimulationDataSource.simulatedData());
        // Data generation every 100 ms
        // Tolerance 200 ms
        // Cache last 5 samples
        final PVReader<VMultiDouble> pv = PVManager.read(synchronizedArrayOf(TimeDuration.ms(10), TimeDuration.ms(250), vDoubles(Collections.nCopies(100, "ramp(0,10,1,0.05)"))))
                .every(hz(10));
        Thread.sleep(300);
        pv.addPVReaderListener(new PVReaderListener() {

            @Override
            public void pvChanged() {
                VMultiDouble array = pv.getValue();
                if (array == null)
                    return;
                // Check that all values are the same
                Double refValue = array.getValues().get(0).getValue();
                for (VDouble value : array.getValues()) {
                    if (value == null || !refValue.equals(value.getValue())) {
                        if (value != null) {
                            System.out.println(value.getValue() + " " + refValue);
                            System.out.println(value.getTimeStamp() + " " + array.getValues().get(0).getTimeStamp());
                        }
                        failedComparisons.incrementAndGet();
                    }
                }
                sampleCounter.incrementAndGet();
            }
        });
        Thread.sleep(1000);
        pv.close();
        // After 10s, expect about 10 samples
        assertTrue("There were " + failedComparisons.get() + " failed comparisons", failedComparisons.get() < 20);
        assertTrue("Less than 9 calls", sampleCounter.get() >= 9);
        assertTrue("More than 11 calls", sampleCounter.get() <= 11);
    }
}
