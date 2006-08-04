/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.engine.xml;

import java.awt.Color;
import java.util.Collection;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignGroup;

import org.xml.sax.Attributes;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id$
 */
public class JRChartFactory extends JRBaseFactory
{

	private static final String ATTRIBUTE_isShowLegend = "isShowLegend";
	private static final String ATTRIBUTE_evaluationTime = "evaluationTime";
	private static final String ATTRIBUTE_evaluationGroup = "evaluationGroup";
	private static final String ATTRIBUTE_hyperlinkType = "hyperlinkType";
	private static final String ATTRIBUTE_hyperlinkTarget = "hyperlinkTarget";
	private static final String ATTRIBUTE_bookmarkLevel = "bookmarkLevel";
	private static final String ATTRIBUTE_customizerClass = "customizerClass";


	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader)digester.peek(digester.getCount() - 1);
		Collection groupEvaluatedCharts = xmlLoader.getGroupEvaluatedCharts();

		JRDesignChart chart = (JRDesignChart) digester.peek();

		String isShowLegend = atts.getValue(ATTRIBUTE_isShowLegend);
		if (isShowLegend != null && isShowLegend.length() > 0)
			chart.setShowLegend(Boolean.valueOf(isShowLegend).booleanValue());

		Byte evaluationTime = (Byte)JRXmlConstants.getEvaluationTimeMap().get(atts.getValue(ATTRIBUTE_evaluationTime));
		if (evaluationTime != null)
		{
			chart.setEvaluationTime(evaluationTime.byteValue());
		}
		if (chart.getEvaluationTime() == JRExpression.EVALUATION_TIME_GROUP)
		{
			groupEvaluatedCharts.add(chart);

			String groupName = atts.getValue(ATTRIBUTE_evaluationGroup);
			if (groupName != null)
			{
				JRDesignGroup group = new JRDesignGroup();
				group.setName(groupName);
				chart.setEvaluationGroup(group);
			}
		}

		String hyperlinkType = atts.getValue(ATTRIBUTE_hyperlinkType);
		if (hyperlinkType != null)
		{
			chart.setLinkType(hyperlinkType);
		}

		Byte hyperlinkTarget = (Byte)JRXmlConstants.getHyperlinkTargetMap().get(atts.getValue(ATTRIBUTE_hyperlinkTarget));
		if (hyperlinkTarget != null)
		{
			chart.setHyperlinkTarget(hyperlinkTarget.byteValue());
		}
		
		String bookmarkLevelAttr = atts.getValue(ATTRIBUTE_bookmarkLevel);
		if (bookmarkLevelAttr != null)
		{
			chart.setBookmarkLevel(Integer.parseInt(bookmarkLevelAttr));
		}		

		String chartCustomizerClass = atts.getValue( ATTRIBUTE_customizerClass );
		if( chartCustomizerClass != null && chartCustomizerClass.length() > 0 ){
			chart.setCustomizerClass(chartCustomizerClass);
		}

		return chart;
	}


	/**                                        	
	 *
	 */
	public static class JRChartTitleFactory extends JRBaseFactory
	{
		private static final String ATTRIBUTE_position = "position";
		private static final String ATTRIBUTE_color = "color";


		public Object createObject(Attributes atts)
		{
			JRDesignChart chart = (JRDesignChart) digester.peek();

			String position = atts.getValue(ATTRIBUTE_position);
			if (position != null && position.length() > 0)
				chart.setTitlePosition(((Byte)JRXmlConstants.getChartTitlePositionMap().get(position)).byteValue());


			Color color = JRXmlConstants.getColor(atts.getValue(ATTRIBUTE_color), Color.black);
			if (color != null)
			{
				chart.setTitleColor(color);
			}

			return chart;
		}
	}


	/**
	 *
	 */
	public static class JRChartSubtitleFactory extends JRBaseFactory
	{
		private static final String ATTRIBUTE_color = "color";


		public Object createObject(Attributes atts)
		{
			JRDesignChart chart = (JRDesignChart) digester.peek();

			Color color = JRXmlConstants.getColor(atts.getValue(ATTRIBUTE_color), Color.black);
			if (color != null)
			{
				chart.setSubtitleColor(color);
			}

			return chart;
		}
	}


}
