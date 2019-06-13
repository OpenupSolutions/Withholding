/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.spin.model.MWHDefinition;
import org.spin.model.MWHSetting;

/**
 * Abstract class for handle all withholding document
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public abstract class AbstractWithholdingSetting {
	
	
	public AbstractWithholdingSetting(MWHSetting setting) {
		this.setting = setting;
		this.ctx = setting.getCtx();
		this.baseAmount = Env.ZERO;
		this.withholdingAmount = Env.ZERO;
	}
	
	/**	Setting	*/
	private MWHSetting setting;
	/**	Withholding	*/
	private MWHDefinition withholdingDefinition;
	/**	Value Parameters	*/
	private HashMap<String, Object> parameters = new HashMap<String, Object>();
	/**	Return Value */
	private HashMap<String, Object> returnValues = new HashMap<String, Object>();
	/**	Context	*/
	private Properties ctx;
	/**	Transaction Name	*/
	private String transactionName;
	/**	Process Message	*/
	private StringBuffer processLog = new StringBuffer();
	/**	Process Description	*/
	private StringBuffer processDescription = new StringBuffer();
	/**	Base Amount	*/
	private BigDecimal baseAmount;
	/**	Withholding Amount	*/
	private BigDecimal withholdingAmount;
	/**	Document	*/
	private DocAction document;
	/**	Logger							*/
	protected CLogger	log = CLogger.getCLogger (getClass());
	/**
	 * Get Context
	 * @return
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Set document
	 * @param document
	 */
	public void setDocument(DocAction document) {
		this.document = document;
	}
	
	/**
	 * Get Document
	 * @return
	 */
	public DocAction getDocument() {
		return document;
	}
	
	/**
	 * Set Transaction Name
	 * @param transactionName
	 */
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	/**
	 * Get Transaction Name for this process
	 * @return
	 */
	public String getTransactionName() {
		return transactionName;
	}
	
	/**
	 * Add Message for document
	 * @param message
	 */
	protected void addLog(String message) {
		if(processLog.length() > 0) {
			processLog.append(Env.NL);
		}
		processLog.append(message);
	}
	
	/**
	 * Get Process Message
	 * @return
	 */
	public String getProcessLog() {
		if(processLog.length() > 0) {
			return processLog.toString();
		}
		//	Default nothing
		return null;
	}
	
	/**
	 * Add description for document
	 * @param description
	 */
	protected void addDescription(String description) {
		if(processDescription.length() > 0) {
			processDescription.append(Env.NL);
		}
		processDescription.append(description);
	}
	
	/**
	 * Get Process Message
	 * @return
	 */
	public String getProcessDescription() {
		if(processDescription.length() > 0) {
			return processDescription.toString();
		}
		//	Default nothing
		return null;
	}
	
	/**
	 * Set Withholding Definition
	 * @param withholdingDefinition
	 */
	public void setWithholdingDefinition(MWHDefinition withholdingDefinition) {
		this.withholdingDefinition = withholdingDefinition;
	}
	
	/**
	 * Get Functional Setting Applicability
	 * @return
	 */
	public MWHDefinition getDefinition() {
		return withholdingDefinition;
	}
	
	/**
	 * Get Functional Setting
	 * @return
	 */
	public MWHSetting getSetting() {
		return setting;
	}
	
	/**
	 * Set Parameter Value
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	/**
	 * Set from Parameters hash
	 * @param parameters
	 */
	public void setParameters(HashMap<String, Object> parameters) {
		for(Entry<String, Object> entry : parameters.entrySet()) {
			this.parameters.put(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * Get a Parameter value from key
	 * @param key
	 * @return
	 */
	public Object getParameter(String key) {
		return parameters.get(key);
	}

	/**
	 * Get Parameter as Integer
	 * @param key
	 * @return
	 */
	public int getParameterAsInt(String key) {
		Object parameter = getParameter(key);
		if(parameter != null 
				&& parameter instanceof Integer) {
			return ((Integer) parameter).intValue();
		}
		//	Default
		return 0;
	}
	
	/**
	 * Get Parameter as BigDecimal
	 * @param key
	 * @return
	 */
	public BigDecimal getParameterAsBigDecimal(String key) {
		Object parameter = getParameter(key);
		if(parameter != null 
				&& parameter instanceof BigDecimal) {
			return ((BigDecimal) parameter);
		}
		//	Default
		return Env.ZERO;
	}
	
	/**
	 * Set Parameter Value
	 * @param key
	 * @param value
	 */
	public void setReturnValue(String key, Object value) {
		returnValues.put(key, value);
	}
	
	/**
	 * Get a Parameter value from key
	 * @param key
	 * @return
	 */
	public Object getReturnValue(String key) {
		return returnValues.get(key);
	}
	
	/**
	 * Get All return values
	 * @return
	 */
	public HashMap<String, Object> getReturnValues() {
		return returnValues;
	}
	
	/**
	 * Get Calculated Withholding Amount
	 * @return
	 */
	public BigDecimal getWithholdingAmount() {
		return withholdingAmount;
	}

	/**
	 * Set Withholding Amount
	 * @param withholdingAmount
	 */
	public void setWithholdingAmount(BigDecimal withholdingAmount) {
		this.withholdingAmount = withholdingAmount;
	}
	
	/**
	 * Add Amount for Withholding
	 * @param withholdingAmount
	 */
	public void addWithholdingAmount(BigDecimal withholdingAmount) {
		this.withholdingAmount = this.withholdingAmount.add(withholdingAmount);
	}

	/**
	 * Get Base Amount for calculation
	 * @return
	 */
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	/**
	 * Base Amount for calculate withholding
	 * @param baseAmount
	 */
	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * Add Base Amount
	 * @param baseAmount
	 */
	public void addBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = this.baseAmount.add(baseAmount);
	}
	
	/**
	 * Validate if the current document is valid for process
	 * @return
	 */
	public abstract boolean isValid();
	
	/**
	 * Run Process
	 * @return
	 */
	public abstract String run();
	
}	//	PaymentExport ???? WTF