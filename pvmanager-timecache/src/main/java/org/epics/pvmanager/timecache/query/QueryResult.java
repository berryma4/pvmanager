/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.pvmanager.timecache.query;

import java.util.List;

/**
 * @author Fred Arnaud (Sopra Group) - ITER
 */
public interface QueryResult {

	public List<QueryData> getData();

}
