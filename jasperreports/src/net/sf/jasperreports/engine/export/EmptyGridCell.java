/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRPrintElement;

	
	
/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class EmptyGridCell extends JRExporterGridCell
{
	
	private GridCellSize size;

	/**
	 *
	 */
	public EmptyGridCell(GridCellSize size)
	{
		this.size = size;
	}

	@Override
	public GridCellSize getSize()
	{
		return size;
	}

	@Override
	public void setSize(GridCellSize size)
	{
		this.size = size;
	}

	public byte getType()
	{
		return TYPE_EMPTY_CELL;
	}

	@Override
	public boolean isOccupied()
	{
		return false;
	}

	@Override
	public JRPrintElement getElement()
	{
		return null;
	}

	@Override
	public String getElementAddress()
	{
		return null;
	}

	@Override
	public String getProperty(String propName)
	{
		return null;
	}

}
