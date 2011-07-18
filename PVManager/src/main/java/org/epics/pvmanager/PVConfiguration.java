/*
 * Copyright 2008-2011 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager;

import java.util.concurrent.Executor;
import org.epics.pvmanager.util.TimeDuration;

/**
 *
 * @author carcassi
 */
public class PVConfiguration<R, W> {
    
    private final PVReaderConfiguration<R> pvReaderConfiguration;
    private final PVWriterConfiguration<W> pvWriterConfiguration;

    public PVConfiguration(DesiredRateExpression<R> readExpression, WriteExpression<W> writeExpression) {
        pvReaderConfiguration = new PVReaderConfiguration<R>(readExpression);
        pvWriterConfiguration = new PVWriterConfiguration<W>(writeExpression);
    }
    
    public PVConfiguration<R, W> from(DataSource dataSource) {
        pvReaderConfiguration.from(dataSource);
        pvWriterConfiguration.from(dataSource);
        return this;
    }

    public PVConfiguration<R, W> notifyOn(Executor onThread) {
        pvReaderConfiguration.notifyOn(onThread);
        pvWriterConfiguration.notifyOn(onThread);
        return this;
    }

    /**
     * Forwards exception to the given exception handler. No thread switch
     * is done, so the handler is notified on the thread where the exception
     * was thrown.
     * <p>
     * Giving a custom exception handler will disable the default handler,
     * so {@link PV#lastException() } and {@link PV#lastWriteException() }
     * is no longer set and no notification
     * is done.
     *
     * @param exceptionHandler an exception handler
     * @return this
     */
    public PVConfiguration<R, W> routeExceptionsTo(ExceptionHandler exceptionHandler) {
        pvReaderConfiguration.routeExceptionsTo(exceptionHandler);
        pvWriterConfiguration.routeExceptionsTo(exceptionHandler);
        return this;
    }
    
    public PV<R, W> synchWriteAndReadEvery(TimeDuration period) {
        PVReader<R> pvReader = pvReaderConfiguration.every(period);
        PVWriter<W> pvWriter = pvWriterConfiguration.sync();
        return new PV<R, W>(pvReader, pvWriter);
    }
    
    public PV<R, W> asynchWriteAndReadEvery(TimeDuration period) {
        PVReader<R> pvReader = pvReaderConfiguration.every(period);
        PVWriter<W> pvWriter = pvWriterConfiguration.async();
        return new PV<R, W>(pvReader, pvWriter);
    }
    
}