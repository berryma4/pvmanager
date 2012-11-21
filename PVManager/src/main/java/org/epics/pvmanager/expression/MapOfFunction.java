/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.expression;

import java.util.Collections;
import org.epics.pvmanager.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A function that takes a set of inputs and transforms them in a new map.
 *
 * @author carcassi
 */
class MapOfFunction<T> implements Function<Map<String, T>> {

    private final Map<String, Function<T>> functions = new HashMap<>();
    private final QueueCollector<MapUpdate<T>> mapUpdateCollector;
    private Map<String, T> previousValue;

    public MapOfFunction(QueueCollector<MapUpdate<T>> mapUpdateCollector) {
        this.mapUpdateCollector = mapUpdateCollector;
    }

    @Override
    public Map<String, T> getValue() {
        for (MapUpdate<T> mapUpdate : mapUpdateCollector.getValue()) {
            for (String name : mapUpdate.getExpressionsToDelete()) {
                functions.remove(name);
            }
            functions.putAll(mapUpdate.getExpressionsToAdd());
            previousValue = null;
        }
        
        Map<String, T> map = new HashMap<String, T>();
        for (Map.Entry<String, Function<T>> entry : functions.entrySet()) {
            String name = entry.getKey();
            T value = entry.getValue().getValue();
            if (value != null) {
                map.put(name, value);
            }
        }
        
        if (Objects.equals(previousValue, map)) {
            return previousValue;
        }
        
        previousValue = map;
        return map;
    }

    public QueueCollector<MapUpdate<T>> getMapUpdateCollector() {
        return mapUpdateCollector;
    }
    
}
