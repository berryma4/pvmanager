/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.pvmanager.test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.epics.pvmanager.ChannelHandler;
import org.epics.pvmanager.ChannelReadRecipe;
import org.epics.pvmanager.ChannelWriteRecipe;
import org.epics.pvmanager.DataSource;
import org.epics.pvmanager.ExceptionHandler;
import org.epics.pvmanager.ReadRecipe;
import org.epics.pvmanager.WriteRecipe;

/**
 *
 * @author carcassi
 */
public class MockDataSource extends DataSource {
    
    private final List<ChannelReadRecipe> connectedReadRecipes = new CopyOnWriteArrayList<>();
    private final List<ChannelWriteRecipe> connectedWriteRecipes = new CopyOnWriteArrayList<>();
    private volatile ReadRecipe readRecipe;
    private volatile WriteRecipe writeRecipe;
    private volatile WriteRecipe writeRecipeForWrite;

    public MockDataSource() {
        super(true);
    }

    @Override
    protected ChannelHandler createChannel(String channelName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connectRead(ReadRecipe recipe) {
        this.readRecipe = recipe;
        connectedReadRecipes.addAll(recipe.getChannelReadRecipes());
    }

    @Override
    public void disconnectRead(ReadRecipe recipe) {
        this.readRecipe = recipe;
        connectedReadRecipes.removeAll(recipe.getChannelReadRecipes());
    }

    @Override
    public void connectWrite(WriteRecipe writeRecipe) {
        this.writeRecipe = writeRecipe;
        connectedWriteRecipes.addAll(writeRecipe.getChannelWriteRecipes());
    }

    @Override
    public void disconnectWrite(WriteRecipe writeRecipe) {
        this.writeRecipe = writeRecipe;
        connectedWriteRecipes.removeAll(writeRecipe.getChannelWriteRecipes());
    }

    @Override
    public void write(WriteRecipe writeRecipe, Runnable callback, ExceptionHandler exceptionHandler) {
        this.writeRecipeForWrite = writeRecipe;
    }

    public ReadRecipe getReadRecipe() {
        return readRecipe;
    }

    public WriteRecipe getWriteRecipe() {
        return writeRecipe;
    }

    public WriteRecipe getWriteRecipeForWrite() {
        return writeRecipeForWrite;
    }

    public List<ChannelReadRecipe> getConnectedReadRecipes() {
        return connectedReadRecipes;
    }

    public List<ChannelWriteRecipe> getConnectedWriteRecipes() {
        return connectedWriteRecipes;
    }
    
}
