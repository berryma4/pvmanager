/**
 * Copyright (C) 2010-14 pvmanager developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.epics.pvmanager.pva.adapters;


import java.util.List;

import org.epics.pvdata.pv.PVField;
import org.epics.pvdata.pv.PVShortArray;
import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.PVUShortArray;
import org.epics.pvdata.pv.ShortArrayData;
import org.epics.util.array.ArrayInt;
import org.epics.util.array.ArrayShort;
import org.epics.util.array.ListInt;
import org.epics.util.array.ListShort;
import org.epics.vtype.ArrayDimensionDisplay;
import org.epics.vtype.VShortArray;
import org.epics.vtype.VTypeToString;
import org.epics.vtype.ValueUtil;

/**
 * @author msekoranja
 *
 */
public class PVFieldToVShortArray extends AlarmTimeDisplayExtractor implements VShortArray {

	private final ListInt size;
	private final ListShort list;
	
	public PVFieldToVShortArray(PVStructure pvField, boolean disconnected) {
		this("value", pvField, disconnected);
	}

	public PVFieldToVShortArray(String fieldName, PVStructure pvField, boolean disconnected) {
		this(pvField.getSubField(fieldName), pvField, disconnected);
	}

	public PVFieldToVShortArray(PVField field, PVStructure pvParent, boolean disconnected) {
		super(pvParent, disconnected);

		if (field instanceof PVShortArray)
		{
			PVShortArray valueField = (PVShortArray)field;

			ShortArrayData data = new ShortArrayData();
			valueField.get(0, valueField.getLength(), data);
			
			this.size = new ArrayInt(data.data.length);
			this.list = new ArrayShort(data.data);
		}
		else if (field instanceof PVUShortArray)
		{
			PVUShortArray valueField = (PVUShortArray)field;

			ShortArrayData data = new ShortArrayData();
			valueField.get(0, valueField.getLength(), data);
			
			this.size = new ArrayInt(data.data.length);
			this.list = new ArrayShort(data.data);
		}
		else
		{
			size = null;
			list = null;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.epics.pvmanager.data.Array#getSizes()
	 */
	@Override
	public ListInt getSizes() {
		return size;
	}

	/* (non-Javadoc)
	 * @see org.epics.pvmanager.data.VShortArray#getData()
	 */
	@Override
	public ListShort getData() {
		return list;
	}
    
    @Override
    public String toString() {
        return VTypeToString.toString(this);
    }

    @Override
    public List<ArrayDimensionDisplay> getDimensionDisplay() {
        return ValueUtil.defaultArrayDisplay(this);
    }

}
