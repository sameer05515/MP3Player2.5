/*
 * Created on Jan 12, 2005
 *
 *Nirmal - 09-02-2008 modify showFormForEvent ie code to get last unfreeze event updateable
 *Rekha    14-02-2008 added new event InvestFinalReportReceivedfrom PPUI.
 *Rekha    05-03-2008 modify showFormForEvent in the case of "ADMIN" login.
 *Rajesh   13-03-2008 added method getPrevCalendarDate(java.util.Date date) for generating previous date.
 *Rajesh   31-03-2008 set current event no to investHeaderPanel.currEvent.
 *Rekha    02-06-2008 added new event Pros Transfer of court.
 *Rajesh   17-06-2008 added this code opening event in update mode for delhi state, by any officer.
 *Rajesh   01-09-2008 Added condition and a method checkForDCP() for DCP/ACP.
 *sumit    21-02-2009 the update screen of NCR case was opening in other cases.
 *Varun   23-02-2009  Added methods numericToAlphaNumeric & alphaNumericToNumeric for location change work
 *sumit    13-04-2009 made objects null for garbage collection.
 *sumit    modified a check in the method showFormForEvent()
 *Varun    06-01-2010 modified methods: formatRegTypeSrNo(),fetchMaxColumnValue(),formatRegTypeSrNoforOtherCase()
 *                      for reg_type_srno increase work from int to char (10000->A000)
 *                    Methods Created : checkAlphaNumericCode(),generateAlphaNumericToNumericCode(),generateNumericToAlphaNumericCode()
 *Jharna   27-05-2009 added for InvestMissingPersonScan in showFormForEvent method.
 
 *Neeraj   03-03-2011 created  new method getSHOPISName() for return SHO Name and getPopulatedPoliceStationStaffDTO() for get policeStationStaffDTO object.
 *Amit Rai 24-03-2011 created new method getYYYYFromStr() for taking the input age as String and return the year of birth as integer.
 *Gaurav Gupta 19-04-2011 Physcial evidence field added by gaurav gupta for Property panel for TN
 *Abhishek        14-05-2011 Made changes for Investigation Module for TN.
 *ANUJ KUMAR    18-05-2011  created  new method mvpAbstract() For MVP Cases Abstract 
 */
package com.prem.mp3.app.comp;

//import gov.nic.cipa.core.client.main.UserLoginScreen;
//import gov.nic.cipa.core.client.ui.components.InvestHeaderPanel;
//import gov.nic.cipa.core.client.ui.components.calendar.JSpinField;
//
//import gov.nic.cipa.core.client.ui.investigation.InvestChanceFPSentToAgency;
//import gov.nic.cipa.core.client.ui.investigation.InvestCaseClosedReopenUI;
//
//import gov.nic.cipa.core.client.ui.investigation.InvestFinalRepoReturnedByCourt;
//
//import gov.nic.cipa.core.client.ui.investigation.InvestFinalReportReceivedfromPPUI;
//import gov.nic.cipa.core.client.ui.investigation.InvestMissingPersonScan;
//
//import gov.nic.cipa.core.client.ui.investigation.InvestMissingPersonRepotedMPSPCR;
//import gov.nic.cipa.core.client.ui.investigation.InvestMssingPersonMatchedUIDB;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropMatchedStolen;
//import gov.nic.cipa.core.client.ui.components.CDialog;
//import gov.nic.cipa.core.client.ui.components.CFrame;
//import gov.nic.cipa.core.client.ui.components.CTextField;
//import gov.nic.cipa.core.client.ui.components.DateTimeComponent;
//import gov.nic.cipa.core.client.ui.components.Feedback;
//import gov.nic.cipa.core.client.ui.components.calendar.JDateChooser;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedArrestSurrender;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedInfoSheetSentToPS;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedProclaimedOffenderUI;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedReleOnBail;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedSentPoliceRemand;
//import gov.nic.cipa.core.client.ui.investigation.InvestAccusedVictimsDetails;
//import gov.nic.cipa.core.client.ui.investigation.InvestAlterationMemo;
//import gov.nic.cipa.core.client.ui.investigation.InvestAutomobileProperties;
//import gov.nic.cipa.core.client.ui.investigation.InvestCaseTransferToOtherIOPSAgency;
//
//import gov.nic.cipa.core.client.ui.investigation.InvestChanceFingerPrintReport;
//import gov.nic.cipa.core.client.ui.investigation.InvestCrimeDetails;
//import gov.nic.cipa.core.client.ui.investigation.InvestCulturalProperty;
//import gov.nic.cipa.core.client.ui.investigation.InvestCurrencyProperty;
//import gov.nic.cipa.core.client.ui.investigation.InvestDeadBodyHandedOver;
//import gov.nic.cipa.core.client.ui.investigation.InvestDeadBodySentPostmortem;
//import gov.nic.cipa.core.client.ui.investigation.InvestDrugNarcoticProperty;
//import gov.nic.cipa.core.client.ui.investigation.InvestFinalRepoFiledInCourt;
//import gov.nic.cipa.core.client.ui.investigation.InvestFinalReportChargeSheetGenerated;
//import gov.nic.cipa.core.client.ui.investigation.InvestFinalReportSentToPP;
//import gov.nic.cipa.core.client.ui.investigation.InvestFingerPrintRecv;
//import gov.nic.cipa.core.client.ui.investigation.InvestFingerPrintSent;
//import gov.nic.cipa.core.client.ui.investigation.InvestInjuredPersonStmtRecorded;
//import gov.nic.cipa.core.client.ui.investigation.InvestInquestRepoReceivMagistrate;
//import gov.nic.cipa.core.client.ui.investigation.InvestInquestRepoSentMagistrate;
//import gov.nic.cipa.core.client.ui.investigation.InvestMLCDepositHospital;
//import gov.nic.cipa.core.client.ui.investigation.InvestMissingPersonTraced;
//import gov.nic.cipa.core.client.ui.investigation.InvestNumberedProperty;
//import gov.nic.cipa.core.client.ui.investigation.InvestPostMortemRepoReceive;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropAnalysisReceivFSL;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropDepositMalkhana;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropPresentCourt;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropReleFromMalkhana;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropSentAnalysisFSL;
//import gov.nic.cipa.core.client.ui.investigation.InvestProperties;
//import gov.nic.cipa.core.client.ui.investigation.InvestPropertiesSeizure;
//import gov.nic.cipa.core.client.ui.investigation.InvestFingerPrintReportRecv;
//import gov.nic.cipa.core.client.ui.prosecution.ProsAccusedDeclaredOffenderUI;
//import gov.nic.cipa.core.client.ui.prosecution.ProsCourtDisposalUI;
//import gov.nic.cipa.core.client.ui.prosecution.ProsFingerPrintSent;  //Added by kapil
//import gov.nic.cipa.core.client.ui.prosecution.ProsHearingInCourtUI;
//import gov.nic.cipa.core.client.ui.prosecution.ProsSummonWarrantExecutedUI;
//import gov.nic.cipa.core.client.ui.prosecution.ProsSummonWarrantIssuedUI;
//import gov.nic.cipa.core.client.ui.prosecution.ProsTransferOfCourt;
//import gov.nic.cipa.core.client.ui.registration.RegDeserterCaseUI;
//import gov.nic.cipa.core.client.ui.registration.RegFIRRegistrationOtherPSUI;
//import gov.nic.cipa.core.client.ui.registration.RegFIRRegistrationUI;
//import gov.nic.cipa.core.client.ui.registration.RegMLCUI;
//import gov.nic.cipa.core.client.ui.registration.RegMissingPersonReportUI;
//import gov.nic.cipa.core.client.ui.registration.RegNonCognizableReportUI;
//import gov.nic.cipa.core.client.ui.registration.RegRegistrationtionOtherCasesUI;
//import gov.nic.cipa.core.client.ui.registration.RegUnclaimedPropertiesUI;
//import gov.nic.cipa.core.client.ui.registration.RegUnnaturalDeathUI;
//import gov.nic.cipa.core.common.codes.ErrorMessages;
//import gov.nic.cipa.core.client.util.Location;
//import gov.nic.cipa.core.common.dto.Mode;
//import gov.nic.cipa.core.common.dto.PoliceStationStaffDTO;
//import gov.nic.cipa.core.common.dto.dailystationdiary.DailyStationDiaryDTO;
//import gov.nic.cipa.core.common.dto.eventdriven.ProclaimedPropertyRewardDTO;
//import gov.nic.cipa.core.common.dto.eventdriven.ProgressEventDTO;
//import gov.nic.cipa.core.common.dto.eventdriven.ProgressEventDrivenDTO;
//import gov.nic.cipa.core.common.dto.eventdriven.ProgressEventFactory;
//import gov.nic.cipa.core.common.dto.investigation.ProgressEventInvestigationDTO;
//import gov.nic.cipa.core.common.dto.investigation.WitnessDetailsCapturedDTO;
//import gov.nic.cipa.core.common.dto.property.AutomobileDTO;
//import gov.nic.cipa.core.common.dto.property.CulturalDTO;
//import gov.nic.cipa.core.common.dto.property.CurrencyDTO;
//import gov.nic.cipa.core.common.dto.property.NarcoticsDTO;
//import gov.nic.cipa.core.common.dto.property.NumberedDTO;
//import gov.nic.cipa.core.common.dto.property.PropertyDTO;
//import gov.nic.cipa.core.common.dto.prosecution.CaseHearingDTO;
//import gov.nic.cipa.core.common.dto.prosecution.CourtDisposalDTO;
//import gov.nic.cipa.core.common.dto.prosecution.SumWarExecutedDTO;
//import gov.nic.cipa.core.common.dto.prosecution.SumWarIssuedDTO;
//import gov.nic.cipa.core.common.dto.prosecution.TransferOfCourtDTO;
//import gov.nic.cipa.core.common.dto.registration.RegDeserterDTO;
//import gov.nic.cipa.core.common.dto.registration.RegFirDTO;
//import gov.nic.cipa.core.common.dto.registration.RegFirOtherPSDTO;
//import gov.nic.cipa.core.common.dto.registration.RegMLCDTO;
//import gov.nic.cipa.core.common.dto.registration.RegMVPettyCaseDTO;
//import gov.nic.cipa.core.common.dto.registration.RegMissingDTO;
//import gov.nic.cipa.core.common.dto.registration.RegOtherCasesDTO;
//import gov.nic.cipa.core.common.dto.registration.RegTnCommunityServiceRegistrationDTO;
//import gov.nic.cipa.core.common.dto.registration.RegUnclaimedPropertyDTO;
//import gov.nic.cipa.core.common.dto.registration.RegUnnaturalDeathDTO;
//import gov.nic.cipa.core.common.dto.registration.RegistrationDTO;
//import gov.nic.cipa.core.server.service.LogMgr;
//import gov.nic.cipa.core.server.session.PoliceStationStaffSession;
//import gov.nic.cipa.core.server.session.RegistrationSession;
//import gov.nic.cipa.core.server.session.dailystationdiary.DailyStationDiarySession;
//import gov.nic.cipa.core.server.session.investigation.AlterationMemoSession;
//import gov.nic.cipa.core.server.session.investigation.AssociateIOSession;
//import gov.nic.cipa.core.server.session.investigation.CaseTransferredSession;
//import gov.nic.cipa.core.server.session.eventdriven.ProgressEventSession;
import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.EditorKit;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

//import gov.nic.cipa.core.common.dto.registration.*;
//import gov.nic.cipa.core.server.bc.registration.RegFirBC;

/**
 * 
 * @author abhi
 */
public class Utility {

	// ////////////////////////////////////////////
	// Validate Methods
	// ////////////////////////////////////////////
	public static boolean validateTextEntered(String textInput) {

		if (textInput.indexOf('\'') != -1)
			return false;

		if (textInput.indexOf(')') != -1)
			return false;

		if (textInput.indexOf('(') != -1)
			return false;

		if (textInput.indexOf('[') != -1)
			return false;

		if (textInput.indexOf(']') != -1)
			return false;

		if (textInput.indexOf('}') != -1)
			return false;

		if (textInput.indexOf('{') != -1)
			return false;

		if (textInput.indexOf('"') != -1)
			return false;

		if (textInput.indexOf('+') != -1)
			return false;

		if (textInput.indexOf('-') != -1)
			return false;

		if (textInput.indexOf('=') != -1)
			return false;

		if (textInput.indexOf('|') != -1)
			return false;

		if (textInput.indexOf('\\') != -1)
			return false;

		if (textInput.indexOf('!') != -1)
			return false;

		if (textInput.indexOf('\'') != -1)
			return false;

		if (textInput.indexOf('~') != -1)
			return false;

		if (textInput.indexOf('*') != -1)
			return false;

		if (textInput.indexOf(';') != -1)
			return false;

		if (textInput.indexOf('^') != -1)
			return false;

		if (textInput.indexOf('@') != -1)
			return false;
		if (textInput.indexOf('#') != -1)
			return false;

		if (textInput.indexOf('$') != -1)
			return false;
		if (textInput.indexOf('%') != -1)
			return false;
		if (textInput.indexOf('^') != -1)
			return false;
		if (textInput.indexOf('`') != -1)
			return false;

		return true;
	}

	public static boolean validateChar(char charInput) {

		switch (charInput) {

		case '\'':
		case ')':
		case '(':
		case '[':
		case ']':
		case '}':
		case '{':
		case '"':
		case '+':
		case '-':
		case '=':
		case '|':
		case '\\':
		case '!':
		case '~':
		case '*':
		case ';':
		case '^':
			return false;
		}
		return true;

	}

	public static boolean validateDate(java.util.Date date) {
		if (date == null)
			return false;

		Calendar cal = Calendar.getInstance();
		java.util.Date upper = cal.getTime(); // //get the upper limit(current
												// time)

		cal.set(1899, 12, 31);
		java.util.Date lower = cal.getTime(); // //get the lower limit(1900)

		if (upper.after(date) && lower.before(date)) {
			return true;

		}

		return false;
	}

	public static boolean validateDateString(String strDate) {
		return validateDate(getDateFromDateString(strDate));
	}

	public static boolean validateTime(String time) {
		if (time.length() > 4)
			return false;
		time = prepareTime(time);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			sdf.setLenient(false);
			if (sdf.parse(time) == null)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean validateDateStringTimeString(String date, String time) {
		java.util.Date objDate = getDateFromDateStringTimeString(date, time);
		return validateDate(objDate);
	}

	public static boolean validateDateAfterDate(java.util.Date beforeDate,
			String date, Vector errList, String msg) {
		if (date != null && !date.trim().equals("")) {
			if (beforeDate.after(Utility.getDateFromDateString(date))) {
				errList.add(ErrorMessages.getErrorCodeDesc(msg));
				return false;
			}
		}
		return true;
	}

	/**
	 * validates the form
	 * 
	 * @author Rohit
	 */
	public static boolean validateDateAfterDate(java.util.Date beforeDate,
			java.util.Date afterdate, Vector errList, String msg) {
		if (beforeDate != null && afterdate != null) {
			if (beforeDate.after(afterdate)) {
				errList.add(ErrorMessages.getErrorCodeDesc(msg));
				return false;
			}
		}
		return true;
	}

	/**
	 * validates the form added by viji on 07-09-2011
	 */

	public static boolean validateDateBeforeDate(java.util.Date afterDate,
			String date, Vector errList, String msg) {
		if (date != null && !date.trim().equals("")) {
			if (afterDate.before(Utility.getDateFromDateString(date))) {
				errList.add(ErrorMessages.getErrorCodeDesc(msg));
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate Time is a given time is after other one
	 * 
	 * @author Manoj
	 */
	public static boolean validateTimeAfterTime(Date afterTime, Date beforeTime) {
		if (beforeTime != null && afterTime != null) {
			try {
				Time t1 = Utility.getTimeFromTimeString(Utility
						.getTimeString(afterTime));
				Time t2 = Utility.getTimeFromTimeString(Utility
						.getTimeString(beforeTime));
				if (t1.after(t2)) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * returns long from date string and time string
	 * 
	 * @author Akash Grover
	 */
	public static long getLongFromDateStringTimeString(String date, String time) {
		java.util.Date objDate = getDateFromDateStringTimeString(date, time);
		if (objDate != null) {
			return objDate.getTime();
		}
		return 0;

	}

	public static java.util.Date getDateFromDateStringTimeString(String date,
			String time) {
		time = prepareTime(time);
		String dateTime = date.trim() + ' ' + time.trim();
		java.util.Date objDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HHmm");
			sdf.setLenient(false);
			objDate = sdf.parse(dateTime);
		} catch (Exception ex) {
			System.out
					.println("Error in date conversion from string to long in util");
		}
		return objDate;
	}

	// public static Vector getStateName() {
	// Vector stateList = new Vector();
	// Connection con = null;
	// PreparedStatement pstmt = null;
	//
	// try {
	// con = gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager()
	// .getNonTConnection();
	// String sql = "select state_code,state_name from t011_State";
	// pstmt = con.prepareStatement(sql);
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// String s1 = rs.getString("state_code");
	// String s2 = rs.getString("state_name").trim();
	// String ps = s1 + ":" + s2;
	// stateList.addElement(ps);
	// }
	// rs.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return stateList;
	// }

	/**
	 * Get date object from a string representation of a date in format
	 * "dd-MM-yyyy"
	 * 
	 * @param date
	 *            in format "dd-MM-yyyy"
	 * @return
	 * @author abhi
	 */
	public static java.util.Date getDateFromDateString(String date) {

		java.util.Date objDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			objDate = sdf.parse(date);
		} catch (Exception ex) {
			System.out
					.println("Error in date conversion from string to long in util");
		}
		return objDate;
	}

	/**
	 * coverts time string to appropriate form( HHmm ) assumption: time string
	 * has length less than 4
	 */
	private static String prepareTime(String time) {
		int strLen = time.length();
		if (time.length() == 3) {
			time = '0' + time;
		} else if (time.length() == 2) {
			time += "00";
		} else if (time.length() == 1) {
			time = '0' + time + "00";
		}
		return time;
	}

	public static boolean validCharField(String str) {

		String numPart = null;
		String alfPart = null;

		int len = str.length();
		int i = 0;
		for (; i < len; i++) {
			if (!Character.isDigit(str.charAt(i)))
				break;
		}

		if (i == 0)
			return true;

		else {
			numPart = str.substring(0, i);
			alfPart = str.substring(i);
		}

		java.math.BigInteger intObj = new java.math.BigInteger(numPart);

		if (intObj.longValue() > 0)
			return true;

		return false;

	}

	public static String getKeyPartFromString(String value) {
		if (value == null) {
			return null;
		}
		int index = value.indexOf(':');
		if (index < 0) {
			return null;
		}

		return value.substring(0, index).trim();
	}

	/**
	 * gets value part from property string added by viji on 31-08-2011
	 */

	public static String getValuePartFromString(String value) {
		if (value == null) {
			return null;
		}
		int index = value.indexOf(':');
		if (index < 0) {
			return null;
		}

		return value.substring(index + 1, value.length()).trim();
	}

	/*
	 * ASSUMPTION: section code can't be selected without selecting act
	 * so.....section can not be null
	 * 
	 * code=>1-->REPEATEDcode=>2-->MISSINGTHROWS NULLPOINTER EXCEPTION IF THE
	 * SECTION COLUMN IS EMPTY
	 */
	public static int validateActSectionTable(JTable table, int rcount) {
		final int OK = 0, REPEATED = 1, MISSING = 2;

		for (int rr = 0; rr < rcount; rr++) {
			// ActAndSection as = new ActAndSection();
			String act_code = (String) table.getValueAt(rr, 1);
			String sec_code = (String) table.getValueAt(rr, 2);
			if (sec_code == null)
				return MISSING;

			for (int i = 0; i < rcount; i++) { // compare with other rows for
												// duplication
				if (rr == i)
					continue;

				String act_code1 = (String) table.getValueAt(i, 1);
				String sec_code1 = (String) table.getValueAt(i, 2);

				if (sec_code1 == null)
					return MISSING;
				if (act_code.equals(act_code1) && sec_code.equals(sec_code1)) {
					return REPEATED;
				}
			}
		}
		return OK;
	}

	public static int validateActSectionTableMPC(JTable table, int rcount) {
		final int OK = 0, REPEATED = 1, MISSING = 2;

		for (int rr = 0; rr < rcount; rr++) {
			// ActAndSection as = new ActAndSection();
			// String act_code = (String) table.getValueAt(rr, 1);
			String sec_code = (String) table.getValueAt(rr, 1);
			// if (sec_code == null)
			// return MISSING;

			for (int i = 0; i < rcount; i++) { // compare with other rows for
												// duplication
				if (rr == i)
					continue;

				// String act_code1 = (String) table.getValueAt(i, 1);
				String sec_code1 = (String) table.getValueAt(i, 1);

				// if (sec_code1 == null)
				// return MISSING;
				if (sec_code.equals(sec_code1)) {
					return REPEATED;
				}
			}
		}
		return OK;
	}

	public static void setTableDisabled(JTable table) {
		JTextField tf = new JTextField();
		tf.setEditable(false);
		int cols = table.getColumnCount();
		for (int i = 0; i < cols; i++)
			table.getColumnModel().getColumn(i)
					.setCellEditor(new DefaultCellEditor(tf));
	}

	// ///////////////////////////////////////////////////////////////////////////
	// /////// DATE + TIME
	// ///////////////////////////////////////////////////////////////////////////

	public static java.sql.Time getTime(int hours, int mins, int seconds)
			throws Exception {
		if ((hours < 0 || hours > 23) || (mins < 0 || mins > 59)
				|| (seconds < 0 || seconds > 59)) {
			throw new Exception("Invalid Time Values");

		}
		Calendar today = Calendar.getInstance();
		GregorianCalendar gcal = new GregorianCalendar(
				today.get(Calendar.YEAR), today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), hours, mins, seconds);
		java.sql.Time time = new java.sql.Time(gcal.getTime().getTime());
		return time;
	}

	public static java.sql.Timestamp getTimestampFromTime(Time time) {

		/*
		 * Calendar today = Calendar.getInstance();
		 * today.setTimeInMillis(time.getTime()); GregorianCalendar gcal = new
		 * GregorianCalendar(1, 0, 1, today .get(Calendar.HOUR_OF_DAY),
		 * today.get(Calendar.MINUTE), today .get(Calendar.SECOND)); Timestamp
		 * timestamp = new java.sql.Timestamp(gcal.getTimeInMillis());
		 */

		Timestamp timestamp = new java.sql.Timestamp(time.getTime());
		// System.out.println(timestamp);
		return timestamp;
	}

	/*
	 * This Method calculate timestamp with the given Date & time.
	 * 
	 * @author Uday
	 * 
	 * @date 28-06-07
	 */
	public static java.sql.Timestamp getTimestampFromTime(Time time, Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		if (date != null) {
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(date);
			cal.set(cal.DAY_OF_MONTH, calDate.get(calDate.DAY_OF_MONTH));
			cal.set(cal.MONDAY, calDate.get(calDate.MONTH));
			cal.set(cal.YEAR, calDate.get(calDate.YEAR));
		}
		Timestamp timestamp = new java.sql.Timestamp(cal.getTime().getTime());

		return timestamp;
	}

	public static java.sql.Time getTimeFromTimestamp(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}

		java.sql.Time time = new java.sql.Time(timestamp.getTime());
		return time;
	}

	/**
	 * return Time object corresponding to time string( HHmm ) if time String is
	 * invalid it returns null
	 * 
	 * @author Akash Grover
	 */
	public static java.sql.Time getTimeFromTimeString(String time)
			throws Exception {

		if (!validateTime(time)) {
			return null;
		}
		time = prepareTime(time);
		int hour = 0;
		int mins = 0;
		try {
			hour = Integer.parseInt(time.substring(0, 2));
			mins = Integer.parseInt(time.substring(2, 4));

		} catch (NumberFormatException e) {
			return null;
		}
		return Utility.getTime(hour, mins, 0);
	}

	public static java.util.Date getClientDate(String strDDMMYYYY) {
		StringTokenizer strTok = new StringTokenizer(strDDMMYYYY, "-");
		String strDD = strTok.nextToken();
		String strMM = strTok.nextToken();
		String strYYYY = strTok.nextToken();
		int iDD = Integer.parseInt(strDD);
		int iMM = Integer.parseInt(strMM) - 1;
		int iYYYY = Integer.parseInt(strYYYY);
		java.sql.Date date = new java.sql.Date((new GregorianCalendar(iYYYY,
				iMM, iDD)).getTime().getTime());
		return date;

	}

	public static String getDateString(java.util.Date date) {
		// java.sql.Date dateSQL = new java.sql.Date((new
		// GregorianCalendar(2001, 0, 1)).getTime().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = "";
		if (date != null) {
			strDate = sdf.format(date);
		}

		return strDate;
	}

	public static String getDateStringFromDate(java.util.Date date) {
		return getDateStringFromLong(date.getTime());
	}

	public static String getDateStringFromLong(long datetime) {

		java.util.Date strDate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		strDate.setTime(datetime);
		String str = sdf.format(strDate);

		return str;
	}

	public static String getTimeStringFromLong(long datetime) {
		java.util.Date strDate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

		strDate.setTime(datetime);
		String str = sdf.format(strDate);
		return str;
	}

	/**
	 * Check numer is even and odd
	 */

	public static boolean validateEvenOdd(int number) {

		if (number % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get time string from date Format HHmm e.g 1823
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		return sdf.format(date);
	}

	/**
	 * Get full time string from date Format HH:mm:ss e.g 18:22:17
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString_HHmmss(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}

	public static long getLongDate(String dt) {
		long retDate = 0;
		try {
			SimpleDateFormat f22 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = f22.parse(dt);
			retDate = date.getTime();
			return retDate;

		} catch (Exception ex) {
			System.out
					.println("Error in date conversion from string to long in util");
		}
		return retDate;
	}

	public static long getLongDateTime(String dt) {
		long retDate = 0;
		try {
			SimpleDateFormat f22 = new SimpleDateFormat("dd-MM-yyyy HHmm");
			java.util.Date date = f22.parse(dt);
			retDate = date.getTime();
			return retDate;
		} catch (Exception ex) {
			try {
				SimpleDateFormat f22 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date date = f22.parse(dt);
				retDate = date.getTime();
				return retDate;
			} catch (Exception e) {
				System.out
						.println("Error in date conversion from string to long in util");
			}
		}
		return retDate;
	}

	public static java.sql.Date getDatabaseDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.util.Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	public static java.sql.Timestamp getCurrentTimeStamp() {
		return new Timestamp(getCurrentDate().getTime());
	}

	/**
	 * Get year from date in format "YY"
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYFromDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String year = sdf.format(date);
		return year;
	}

	/**
	 * Get year from date in format "YYYY"
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYFromDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		return year;
	}

	/**
	 * Get year after calculation from age in format "YYYY"
	 * 
	 * @param String
	 * @return int Added by amit rai for age calculation on 24 march 2011
	 */
	public static int getYYYYFromStr(String age) {
		int year;
		int currentAge = Integer.parseInt(age);
		java.util.Date date = new java.util.Date();
		int currentYear = Integer.parseInt(getYYYYFromDate(date));
		year = currentYear - currentAge;
		return year;
	}

	/**
	 * Get dayNo from date in format "DD"
	 * 
	 * @param date
	 * @return
	 */
	public static String getDDFromDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String dayNo = sdf.format(date);
		return dayNo;
	}

	/**
	 * Get month from date in format "MM"
	 * 
	 * @param date
	 * @return
	 */
	public static String getMMFromDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		return month;
	}

	/**
	 * Get the start of year date as a Date object for a particular date.
	 * 
	 * @return
	 */
	public static java.util.Date getDateStartOfYear(java.util.Date date) {
		String startDate = Utility.getYYYYFromDate(date);
		startDate = "01-01-" + startDate;
		return getDateFromDateString(startDate);
	}

	/**
	 * Get month name (3 Chars) and date from date.
	 * 
	 * @return
	 * 
	 * @author viji
	 */
	public static String getMonthNameDDFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
		String month = sdf.format(date);
		return month;
	}

	/**
	 * Get month name from date.
	 * 
	 * @return
	 * 
	 * @author Manoj
	 */
	public static String getMonthNameFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		String month = sdf.format(date);
		return month;
	}

	/**
	 * Get the day value in a particular date
	 * 
	 * @param date
	 * @return
	 */
	// public static String getDayFromDate(java.util.Date date) {
	// java.util.Calendar objCal = java.util.Calendar.getInstance();
	// objCal.setTime(date);
	// int day = objCal.get(java.util.Calendar.DAY_OF_WEEK);
	// String strDay = "";
	// switch (day) {
	// case java.util.Calendar.SUNDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "1");
	// break;
	// case java.util.Calendar.MONDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "2");
	// break;
	// case java.util.Calendar.TUESDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "3");
	// break;
	// case java.util.Calendar.WEDNESDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "4");
	// break;
	// case java.util.Calendar.THURSDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "5");
	// break;
	// case java.util.Calendar.FRIDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "6");
	// break;
	// case java.util.Calendar.SATURDAY:
	// strDay = ResourceBundleManager.getValue(
	// ResourceBundleManager.DAY_OF_WEEK, "7");
	// break;
	// }
	// return strDay;
	// }

	/**
	 * returns suffix for dd i.e., 'th', 'rd', 'st' and 'nd'
	 * 
	 * @param date
	 * @return
	 * @author Manoj
	 */
	public static String getSuffixForDD(String dd) {
		int d = 0;
		String suffix = null;
		for (int i = 11; i < 20; i++) {
			if (i == Integer.parseInt(dd)) {
				return "th";
			}
		}
		if (dd.length() == 2) {
			d = Integer.parseInt(dd.substring(1, 2));
		} else {
			d = Integer.parseInt(dd.substring(0, 1));
		}
		switch (d) {
		case 1:
			suffix = "st";
			break;
		case 2:
			suffix = "nd";
			break;
		case 3:
			suffix = "rd";
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 0:
			suffix = "th";
		}
		return suffix;
	}

	/**
	 * returns date in 'ddxx day of monthname yyyy' format
	 * 
	 * @param date
	 * @return
	 * @author Manoj
	 */
	public static String getDDDayOfMonthYYYYFromDate(Date date) {
		String dd = getDDFromDate(date);
		return dd + getSuffixForDD(dd) + " day of "
				+ getMonthNameFromDate(date) + " " + getYYYYFromDate(date);
	}

	/**
	 * returns calendar object for a date object passed
	 * 
	 * @param date
	 * @return
	 * @author abhi
	 */
	public static Calendar getCalendarForDate(java.util.Date date) {
		// convert the date to a calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// HACK: Force all fields to update. see link for explanation of this.
		// http://java.sun.com/j2se/1.4/docs/api/java/util/Calendar.html
		cal.getTime();
		return cal;
	}

	/**
	 * This method generates previous date to given date
	 * 
	 * @param date
	 * @return date
	 * @author Rajesh Kumar
	 */
	public static java.util.Date getPrevCalendarDate(java.util.Date date) {
		// convert the date to a calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH) - 1);
		return ((java.util.Date) cal.getTime());
	}

	/**
	 * returns a specified no.(count) of blank space in form of a string
	 * 
	 * @param count
	 * @return
	 * 
	 * @author manisha
	 */
	public static String insertSpaces(int count) {
		String blankStr = "";
		for (int i = 0; i < count; i++) {
			blankStr = blankStr.concat(" ");
		}
		return blankStr;
	}

	/**
	 * returns date after adjusting i.e. +/- offset from year field
	 * 
	 * @param date
	 * @param offset
	 * @return
	 * 
	 * @author abhi
	 */
	public static java.util.Date getDateAfterOffsetYear(java.util.Date date,
			int offset) {

		Calendar calendar = getCalendarForDate(date);
		calendar.add(Calendar.YEAR, offset);
		return new java.util.Date(calendar.getTime().getTime());
	}

	/**
	 * This Method Return time with the Current date.
	 * 
	 * @author Uday
	 * @date 28-06-07
	 * 
	 */

	public static Time getTimeWithCurrentDate(Time time) {
		Calendar calCurrent = Calendar.getInstance();
		calCurrent.setTime(new java.util.Date());

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());

		cal.set(cal.DAY_OF_MONTH, calCurrent.get(calCurrent.DAY_OF_MONTH));
		cal.set(cal.MONDAY, calCurrent.get(calCurrent.MONTH));
		cal.set(cal.YEAR, calCurrent.get(calCurrent.YEAR));

		Time currentTime = new Time(cal.getTime().getTime());
		return currentTime;

	}

	/**
	 * This Method Return time with the given date.
	 * 
	 * @author Uday
	 * @date 28-06-07
	 * 
	 */
	public static Time getTimeWithDate(Time time, Date date) {
		Calendar calCurrent = Calendar.getInstance();
		calCurrent.setTime(date);

		Calendar cal = Calendar.getInstance();
		if (time == null) {
			time = new Time(0, 0, 0);
		}
		cal.setTimeInMillis(time.getTime());

		cal.set(cal.DAY_OF_MONTH, calCurrent.get(calCurrent.DAY_OF_MONTH));
		cal.set(cal.MONDAY, calCurrent.get(calCurrent.MONTH));
		cal.set(cal.YEAR, calCurrent.get(calCurrent.YEAR));

		Time currentTime = new Time(cal.getTime().getTime());
		return currentTime;

	}

	/**
	 * This Method calculate Gd Time Status.
	 * 
	 * @author Uday
	 * @date 28-06-07
	 * 
	 */

	// public static String getGDTimeStatus(Time gdStartTime, Time gdTime) {
	//
	// Calendar cal = Calendar.getInstance();
	// cal.set(cal.HOUR_OF_DAY, 23);
	// cal.set(cal.MINUTE, 59);
	// cal.set(cal.SECOND, 59);
	//
	// Long longMidNight = cal.getTimeInMillis();
	// Time midNight = new Time(cal.getTimeInMillis());
	// if (gdTime == null) {
	// gdTime = new Time(Utility.getCurrentDate().getTime());
	// }
	//
	// if (gdStartTime != null) {
	//
	// String strGDTime = Utility.getTimeString(gdTime);
	// String strGDStartTime = Utility.getTimeString(gdStartTime);
	// String strMidNight = Utility.getTimeString(midNight);
	// if ((Integer.parseInt(strGDTime) >= 0)
	// && (Integer.parseInt(strGDTime) < Integer
	// .parseInt(strGDStartTime))) {
	// return DailyStationDiaryDTO.AFTER_MIDNIGHT;
	// }
	// return DailyStationDiaryDTO.BEFORE_MIDNIGHT;
	// }
	// return DailyStationDiaryDTO.UNKNOWN;
	// }

	/**
	 * This Method Return Day Start Time.
	 * 
	 * @author Uday
	 * @date 28-06-07
	 * 
	 */
	public static Time getDayStartTime(Date date) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		Calendar cal = Calendar.getInstance();

		cal.set(cal.DAY_OF_MONTH, calDate.get(cal.DAY_OF_MONTH));
		cal.set(cal.MONTH, calDate.get(cal.MONTH));
		cal.set(cal.YEAR, calDate.get(cal.YEAR));
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		Time time = new Time(cal.getTime().getTime());

		return time;
	}

	/**
	 * This Method REturn Day End Time.
	 * 
	 * @author Uday
	 * @date 28-06-07
	 * 
	 */
	public static Time getDayEndTime(Date date) {

		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);

		Calendar cal = Calendar.getInstance();

		cal.set(cal.DAY_OF_MONTH, calDate.get(cal.DAY_OF_MONTH));
		cal.set(cal.MONTH, calDate.get(cal.MONTH));
		cal.set(cal.YEAR, calDate.get(cal.YEAR));

		cal.set(cal.HOUR_OF_DAY, 23);
		cal.set(cal.MINUTE, 59);
		cal.set(cal.SECOND, 59);
		Time time = new Time(cal.getTimeInMillis());
		return time;
	}

	/**
	 * Checking If this Machiene is Server
	 * 
	 * @author Manoj
	 */
	// public static boolean isServer() {
	// final Runtime runtime = Runtime.getRuntime();
	// try {
	// createScriptFile(ResourceBundleManager.getValue(
	// ResourceBundleManager.DB_RESTORE_BACKUP_COMMANDS,
	// "CheckForServer").trim());
	// String[] command = { "sh", "/tmp/ScriptFile.sh" };
	// Process proc = runtime.exec(command);
	//
	// InputStream inputstream = proc.getInputStream();
	// InputStreamReader inputstreamreader = new InputStreamReader(
	// inputstream);
	// BufferedReader bufferedreader = new BufferedReader(
	// inputstreamreader);
	//
	// String addIP = bufferedreader.readLine();
	// System.out.println("systemIP:" + addIP);
	// if (ApplicationConfig.getDatabaseIP().equals("127.0.0.1")
	// || ApplicationConfig.getDatabaseIP().equals(addIP)) {
	// return true;
	// } else {
	// return false;
	// }
	// } catch (Exception e) {
	// Feedback.showException(new JFrame(), e);
	// }
	// return false;
	// }

	/**
	 * Creating script file
	 * 
	 * @author Manoj
	 */
	// public static void createScriptFile(String strCommand) {
	// File shellScriptFile = new File("/tmp/ScriptFile.sh");
	// FileOutputStream fos = null;
	// try {
	// fos = new FileOutputStream(shellScriptFile);
	//
	// PrintStream ps = new PrintStream(fos);
	// ps.print(strCommand);
	//
	// if (fos != null) {
	// fos.close();
	// }
	//
	// } catch (Exception e) {
	// Feedback.showException(null, e);
	// }
	// }

	/**
	 * Check GDEntry No. on the basis of GDEntry No & GDEntry Date. If Exist
	 * then it prompt confirmation message. If user select 'No' then GDEntryNo
	 * field and GDEntry Time set to blank and GDEntry Date set to current date.
	 * If 'Yes' option is selected then it simply replace GDEntry Time with the
	 * existing GDEntry Time.
	 * 
	 * @param txtGDEntryNo
	 *            ,dtcGdDateTime
	 * @return
	 * @throws Exception
	 * @author Uday
	 */

	// public static void checkGDEntryNo(CTextField txtGDEntryNo,
	// DateTimeComponent dtcGdDateTime) throws Exception {
	// if (txtGDEntryNo == null || txtGDEntryNo.getText().trim().equals("")) {
	// return;
	// }
	// if (dtcGdDateTime == null || dtcGdDateTime.getDate() == null) {
	// return;
	// }
	//
	// Vector vecDailyStationDiary = new Vector();
	// Vector vecData = new Vector();
	//
	// DailyStationDiaryDTO dailyStationDiaryDTO = new DailyStationDiaryDTO();
	// DailyStationDiarySession dailyStationDiarySession = new
	// DailyStationDiarySession();
	// String gdEntryNo = txtGDEntryNo.getText().trim();
	// Date gdDate = dtcGdDateTime.getDate();
	// Time gdTime = dtcGdDateTime.getTime();
	// int gdNo = 0;
	// try {
	// gdNo = Integer.parseInt(gdEntryNo.substring(0,
	// gdEntryNo.length() - 1));
	// } catch (Exception e) {
	// e.printStackTrace();
	// // return;
	// }
	// String gdSeries = gdEntryNo.substring(gdEntryNo.length() - 1);
	//
	// dailyStationDiaryDTO.setGdSrNo(gdNo);
	// dailyStationDiaryDTO.setGdSeries(gdSeries);
	// dailyStationDiaryDTO.setGdEntryDate(gdDate);
	//
	// try {
	// if (dailyStationDiarySession.checkGdEntry(dailyStationDiaryDTO)) {
	// vecDailyStationDiary = dailyStationDiarySession
	// .fetchDailyStationDairyFromGDNo(dailyStationDiaryDTO);
	// String strDailyStationDairy = "";
	// String gdContent = "";
	// for (Iterator it = vecDailyStationDiary.iterator(); it
	// .hasNext();) {
	// DailyStationDiaryDTO diary = (DailyStationDiaryDTO) it
	// .next();
	//
	// gdContent = diary.getGdContent();
	// if (gdContent != null) {
	// if (gdContent.length() > 100) {
	// gdContent = gdContent.substring(0, 100) + " ...";
	// }
	// }
	// String gdType = ResourceBundleManager
	// .getValuePartFromValue(ResourceBundleManager
	// .getValue(ResourceBundleManager.GD_TYPE,
	// diary.getGdType()));
	// if (!gdContent.equals("")) {
	// gdContent = "-" + gdContent;
	// }
	// strDailyStationDairy = diary.getGdEntryTime() + "-"
	// + gdType + gdContent;
	// vecData.add(strDailyStationDairy);
	// }
	//
	// String msg = "";
	//
	// Vector selectedIndex = new Vector();
	// int option = -1;
	// if (!vecDailyStationDiary.isEmpty()
	// && vecDailyStationDiary.size() > 1) {
	// msg = ErrorMessages.getErrorCodeDesc("GDEntryOption");
	// option = Feedback.showSeslectionOption(null, "", msg,
	// selectedIndex, vecData);
	// } else {
	// msg = ErrorMessages.getErrorCodeDesc("GDEntryExist");
	// msg = msg + "\n"
	// + ErrorMessages.getErrorCodeDesc("KeepInfo");
	// option = Feedback.showOptionYesNo(null, "", msg);
	// selectedIndex.add(0);
	// }
	// if (option == JOptionPane.OK_OPTION
	// || option == JOptionPane.YES_OPTION) {
	// dailyStationDiaryDTO = (DailyStationDiaryDTO) vecDailyStationDiary
	// .get(((Integer) selectedIndex.get(0)).intValue());
	// dtcGdDateTime
	// .setDate(dailyStationDiaryDTO.getGdEntryDate());
	// dtcGdDateTime
	// .setTime(dailyStationDiaryDTO.getGdEntryTime());
	// dtcGdDateTime.setEnabled(false);
	//
	// } else {
	// txtGDEntryNo.setText("");
	// Date date = new Date();
	// dtcGdDateTime.setTime(null);
	// dtcGdDateTime.setDate(date);
	// dtcGdDateTime.setEnabled(true);
	// }
	//
	// } else {
	// dtcGdDateTime.setEnabled(true);
	// }
	// } catch (Exception ex) {
	// Feedback.showException(null, ex);
	// }
	// }

	// public static double round(double dblValue){
	// double rounded = 0.0;
	// rounded = Math.r
	// return rounded;
	// }
	//

	// ///////////////////////////////////////////////////////////////////////
	// /////////////// CHECK
	// ///////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////

	// /**
	// *@author Amar Kumar
	// *Returns vector of Investigation officer or duty officer
	// "who have not been relieved yet"
	// **/
	// public static Vector getPoliceOfficer() throws Exception {
	// PreparedStatement pstmt = null;
	// Vector officerVector = new java.util.Vector();
	// Connection con = ServiceMgr.getDBConnection();
	//
	// try {
	// // edited on 18/10/2005
	// // admin row avoided putting where condition: pis_code<>'********'
	// // @author amar
	// String sqlPsOff =
	// "select * from t015_policestationstaff where pis_code<>'********'  and pis_code<>'########'and date_relieve is null";
	// pstmt = con.prepareStatement(sqlPsOff);
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// String offCode = rs.getString("pis_code");
	// String offName = rs.getString("pis_staffname").trim();
	// String desg = rs.getString("pis_designation").trim();
	// officerVector.add(offCode + ":" + offName + "(" + desg + ")");
	// }
	// } catch (Exception e) {
	// pstmt.close();
	// throw new Exception("Error getting policestation staff");
	// }
	// return officerVector;
	// }

	public static void sortCombo(JComboBox combo) {
		// if (combo instanceof
		// gov.nic.cipa.core.client.ui.components.CAutoComboBox)
		// return;

		Object selectedItem = combo.getSelectedItem();

		DefaultComboBoxModel model = (DefaultComboBoxModel) combo.getModel();
		HashMap hm = new HashMap();
		int size = model.getSize();
		ArrayList vecOthers = new ArrayList();

		javax.swing.event.ListDataListener[] arrList = model
				.getListDataListeners();
		for (int i = 0; i < arrList.length; i++) {
			model.removeListDataListener(arrList[i]);
		}

		for (int i = 0; i < size; i++) {
			String item = (String) model.getElementAt(i);
			int index = item.indexOf(':');

			if (index == -1) {
				vecOthers.add(item);
				continue;
			}

			String key = item.substring(0, index);
			String val = item.substring(index + 1);
			hm.put(val, key);
		}

		Set valSet = hm.keySet();
		ArrayList list = new ArrayList(valSet);
		Collections.sort(list);

		combo.removeAllItems();

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			String val = (String) iter.next();
			model.addElement(hm.get(val) + ":" + val);
		}

		if (vecOthers.size() != 0) {
			Collections.sort(vecOthers);

			iter = vecOthers.iterator();
			while (iter.hasNext()) {
				String val = (String) iter.next();
				model.addElement(val);
			}
		}

		for (int i = 0; i < arrList.length; i++) {
			model.addListDataListener(arrList[i]);
		}
		combo.setSelectedItem(selectedItem);
	}

	/**
	 * Generate SrNo taking into consideration year.
	 * 
	 * @param columnName
	 * @param tableName
	 * @return
	 * @throws Exception
	 * 
	 * @author abhi
	 */
	// public static int generateYearSrNo(String columnName, String tableName)
	// throws Exception {
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// int maxValue = 0;
	// try {
	//
	// String sql = "select max(" + columnName + ") from " + tableName
	// + " where location='" + Location.getLocation() + "'";
	//
	// ResultSet rs = con.createStatement().executeQuery(sql);
	// rs.next();
	// maxValue = rs.getInt(1);
	// // System.out.println("maxValue = " + maxValue);
	//
	// String strMaxValue = "" + maxValue;
	// String strCurrentYear = Utility.getYYYYFromDate(Utility
	// .getCurrentDate());
	//
	// /**
	// * if the year suffix in the generated no. is not current year
	// */
	// if (!strMaxValue.startsWith(strCurrentYear)) {
	// strMaxValue = strCurrentYear + "0001";
	// maxValue = Integer.parseInt(strMaxValue);
	// } else {
	// maxValue++;
	// }
	//
	// } catch (Exception e) {
	// throw e;
	// }
	//
	// return maxValue;
	// }

	/**
	 * Generate SrNo from Sequence, taking into consideration updation of
	 * sequence if its a new year.
	 * 
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	// public static int generateYearSrNoFromSequence(String sequenceName)
	// throws Exception {
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// int seqNo = 0;
	// try {
	//
	// seqNo = Utility.sequenceNextVal(sequenceName);
	//
	// String strSeqNo = "" + seqNo;
	// String strCurrentYear = Utility.getYYYYFromDate(Utility
	// .getCurrentDate());
	//
	// /**
	// * if the year suffix in the generated no. is not current year then
	// * drop the sequence and recreate it
	// */
	// if (!strSeqNo.startsWith(strCurrentYear)) {
	// strSeqNo = strCurrentYear + "0001";
	// seqNo = Integer.parseInt(strSeqNo);
	//
	// Utility.sequenceDrop(sequenceName);
	// Utility.sequenceCreate(sequenceName, seqNo);
	// seqNo = Utility.sequenceNextVal(sequenceName);
	// }
	//
	// } catch (Exception e) {
	// throw e;
	// }
	//
	// return seqNo;
	// }

	/**
	 * Generate SrNo from Sequence.
	 * 
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 */
	// public static int generateSrNoFromSequence(String sequenceName)
	// throws Exception {
	//
	// int seqNo = Utility.sequenceNextVal(sequenceName);
	// return seqNo;
	// }

	/**
	 * Generate Year SrNo from Table.
	 * 
	 * @param tableName
	 *            , columnName
	 * @return
	 * @throws Exception
	 */
	// public static int generateYearSrNoFromTable(String tableName,
	// String columnName) throws Exception {
	// int srNo = 0;
	// try {
	// srNo = fetchMaxColumnValue(tableName, columnName);
	//
	// String strSeqNo = "" + srNo;
	// String strCurrentYear = Utility.getYYYYFromDate(Utility
	// .getCurrentDate());
	// if (!strSeqNo.startsWith(strCurrentYear)) {
	// strSeqNo = strCurrentYear + "0000";
	// srNo = Integer.parseInt(strSeqNo);
	// }
	// srNo++;
	// } catch (Exception e) {
	// throw new Exception("Error Generating SrNo : ", e);
	// }
	// return srNo;
	// }

	// Created by aditya shukla for genrating MVSrno. on 30jun

	// public static int generateMVOPCSRYearSrNoFromTable(String tableName,
	// String columnName) throws Exception {
	// int srNo = 0;
	// try {
	// srNo = fetchMaxColumnValue(tableName, columnName);
	//
	// String strSeqNo = "" + srNo;
	// String strCurrentYear = Utility.getYYYYFromDate(Utility
	// .getCurrentDate());
	// strCurrentYear = strCurrentYear.substring(2);
	// if (!strSeqNo.startsWith(strCurrentYear)) {
	// strSeqNo = strCurrentYear + "000000";
	// srNo = Integer.parseInt(strSeqNo);
	// }
	// srNo++;
	// } catch (Exception e) {
	// throw new Exception("Error Generating SrNo : ", e);
	// }
	// return srNo;
	// }

	/**
	 * Generate SrNo from Table.
	 * 
	 * @param tableName
	 *            , columnName
	 * @return
	 * @throws Exception
	 */
	// public static int generateSrNoFromTable(String tableName, String
	// columnName)
	// throws Exception {
	// int srNo = 0;
	// try {
	//
	// srNo = fetchMaxColumnValue(tableName, columnName);
	// srNo++;
	// } catch (Exception e) {
	// throw new Exception("Error Generating SrNo From Table : "
	// + tableName, e);
	// }
	// return srNo;
	// }

	/**
	 * Fetch Max Column Value from Table.
	 * 
	 * @param tableName
	 *            ,columnName
	 * @return
	 * @throws Exception
	 */
	// public static int fetchMaxColumnValue(String tableName, String
	// columnName)
	// throws Exception {
	// PreparedStatement pstmt = null;
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// int srNo = 0;
	// try {
	// String strSQL = "select max(" + columnName + ") from " + tableName;
	// pstmt = con.prepareStatement(strSQL);
	// LogMgr.getLogger().debug(Utility.class, "" + pstmt);
	// ResultSet rs = pstmt.executeQuery();
	// if (rs.next()) {
	//
	// String regTypeSrNo = rs.getString(1);
	// if (regTypeSrNo == null) {
	// regTypeSrNo = rs.getInt(1) + "";
	// }
	// if (regTypeSrNo.length() > 4
	// && Utility.checkAlphaNumericCode(regTypeSrNo
	// .substring(4))) {
	// srNo = Integer
	// .parseInt(regTypeSrNo.substring(0, 4)
	// + Utility
	// .generateAlphaNumericToNumericCode(regTypeSrNo
	// .substring(4)));// "10000");
	// } else {
	// srNo = Integer.parseInt(regTypeSrNo.trim());
	// }
	// // srNo = rs.getInt(1);
	// } else {
	// throw new Exception("Error getting Max value ");
	// }
	// pstmt.close();
	//
	// } catch (Exception e) {
	// throw new Exception("Error getting Max value ", e);
	// } finally {
	// pstmt = null;
	// }
	// return srNo;
	// }

	// //////////////////////////////////////////////////////////////
	// Sequence manipulation Methods
	// @author abhi
	// //////////////////////////////////////////////////////////////
	// public static int sequenceMinVal(String sequenceName) throws Exception {
	// int seqNo = 0;
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// try {
	// String sql = "select min_value from " + sequenceName;
	// ResultSet rs = con.createStatement().executeQuery(sql);
	// rs.next();
	// seqNo = rs.getInt(1);
	// // System.out.println("seqNo = " + seqNo);
	// } catch (Exception e) {
	// throw new Exception("Error getting 'minval' from sequence - "
	// + sequenceName, e);
	// }
	// return seqNo;
	// }

	// public static int sequenceNextVal(String sequenceName) throws Exception {
	// int seqNo = 0;
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// try {
	// String sql = "select nextval('" + sequenceName + "')";
	// ResultSet rs = con.createStatement().executeQuery(sql);
	// rs.next();
	// seqNo = rs.getInt(1);
	// // System.out.println("seqNo = " + seqNo);
	// } catch (Exception e) {
	// throw new Exception("Error getting 'nextval' from sequence - "
	// + sequenceName, e);
	// }
	// return seqNo;
	// }

	// public static void sequenceDrop(String sequenceName) throws Exception {
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// PreparedStatement pstmt = null;
	// try {
	// pstmt = con.prepareStatement("DROP SEQUENCE " + sequenceName);
	// boolean status = pstmt.execute();
	// if (!status) {
	// throw new Exception();
	// }
	// } catch (Exception e) {
	// throw new Exception("Error dropping sequence - " + sequenceName, e);
	// }
	// }

	// public static void sequenceCreate(String sequenceName, int minValue)
	// throws Exception {
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// PreparedStatement pstmt = null;
	// try {
	// pstmt = con.prepareStatement("create sequence " + sequenceName
	// + " minvalue " + minValue);
	//
	// boolean status = pstmt.execute();
	// if (!status) {
	// throw new Exception();
	// }
	// } catch (Exception e) {
	// throw new Exception("Error recreating sequence - " + sequenceName,
	// e);
	// }
	// }

	/**
	 * returns true if the vector of values contain a vealue for the iven key
	 * 
	 * @author Akash Grover
	 */
	public static boolean containsKey(Vector vecVals, String key) {
		Iterator iter = vecVals.iterator();
		while (iter.hasNext()) {
			String value = (String) iter.next();
			int index = value.indexOf(':');
			String valueKey = value.substring(0, index);
			if (valueKey.equals(key)) {
				return true;
			}
		}
		return false;
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * disables all the added components in the given container
	 * 
	 * @param exceptComponents
	 *            is an array of components that are not to be disabled -- Akash
	 *            Grover
	 */
	public static void disableAllComponents(java.awt.Container objContainer,
			java.awt.Component[] exceptComponents) {
		disableAllComponents(objContainer);
		for (int i = 0; i < exceptComponents.length; i++) {
			exceptComponents[i].setEnabled(true);
		}
	}

	/**
	 * disables all the added components in the given container -- Akash Grover
	 */
	public static void disableAllComponents(java.awt.Container objContainer) {
		java.awt.Component[] objComponents = objContainer.getComponents();

		for (int i = 0, size = objComponents.length; i < size; i++) {
			if (objComponents[i] instanceof java.awt.Container) {
				disableAllComponents((java.awt.Container) objComponents[i]);
				objComponents[i].setEnabled(false);
			} else {
				objComponents[i].setEnabled(false);
			}
		}
	}

	// public static Vector getPoliceStation(String districtCode) {
	// Vector policeStationList = new Vector();
	// PreparedStatement pstmt = null;
	// try {
	// // DBMgr dbr=new DBMgr();
	// String sqlPSList =
	// "select ps_code,ps_name from t013_policestation where district_code=?";
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager()
	// .getNonTConnection();
	// pstmt = con.prepareStatement(sqlPSList);
	// pstmt.setString(1, districtCode);
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// String psCode = rs.getString("ps_code");
	// String psName = rs.getString("ps_name");
	//
	// policeStationList.add(psCode + ":" + psName);
	// // String ps=s1+ "  "+ s2;
	// // RegFIRComplainantDetailsPoliceStationJComboBox2.addItem(ps);
	// }
	// rs.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return policeStationList;
	// }

	/**
	 * returns the current version of CIPA
	 * 
	 * @author Akash Grover, Abhi
	 */
	// public static String getCIPAVersion() throws Exception {
	// // Abhi Apr 11, 2005, 5:03:44 PM
	// return getApplicationNameBuildInfo();
	// }
	//
	// public static String getCIPAVersionTN() throws Exception {
	// // Abhi Apr 11, 2005, 5:03:44 PM
	// return getApplicationNameBuildInfoTN();
	// }

	// /////////////////////////////////////////////

	/**
	 * @throws Exception
	 * 
	 * @author abhi
	 */
	// private static HashMap getConfigProperties() throws Exception {
	// ResourceBundleManager mgr = new ResourceBundleManager();
	// HashMap hmpConfig = new HashMap(
	// ResourceBundleManager
	// .getMap(ResourceBundleManager.CONFIG_PROJECT_CIPA));
	//
	// return hmpConfig;
	// }

	// public static String getApplicationName() throws Exception {
	// HashMap hmpConfig = getConfigProperties();
	// String strValue = "";
	// strValue += (String) hmpConfig.get("build.project");
	// // System.out.println(strValue);
	// return strValue;
	// }

	// public static String getApplicationVersion() throws Exception {
	// HashMap hmpConfig = getConfigProperties();
	// String strValue = "";
	// strValue += (String) hmpConfig.get("build.version");
	// // System.out.println(strValue);
	// return strValue;
	// }

	// public static String getApplicationBuildNo() throws Exception {
	// HashMap hmpConfig = getConfigProperties();
	// String strValue = "";
	// strValue += (String) hmpConfig.get("build.date");
	// // System.out.println(strValue);
	// return strValue;
	// }

	// public static String getApplicationNameBuildInfo() throws Exception {
	// HashMap hmpConfig = getConfigProperties();
	// String strBuildInfo = "";
	// strBuildInfo += (String) hmpConfig.get("build.project");
	// strBuildInfo += (" (" + (String) hmpConfig.get("build.version"));
	// strBuildInfo += ("_" + (String) hmpConfig.get("build.date") + ")"); //
	// Updated
	// // for
	// // to
	// // change
	// // the
	// // "."
	// // from
	// // "_"
	// // into
	// // CipaVersion
	//
	// // System.out.println(strBuildInfo);
	// return strBuildInfo;
	// }

	// public static String getApplicationNameBuildInfoTN() throws Exception {
	// HashMap hmpConfig = getConfigProperties();
	// String strBuildInfo = "";
	// strBuildInfo += (String) hmpConfig.get("build.projecttn");
	// strBuildInfo += (" (" + (String) hmpConfig.get("build.version"));
	// strBuildInfo += ("." + (String) hmpConfig.get("build.date") + ")");
	//
	// // System.out.println(strBuildInfo);
	// return strBuildInfo;
	// }

	// /////////////////////////////////////////////

	/**
	 * Home directory of the application based on the "user.dir" system
	 * property.
	 * 
	 * @author abhi
	 */
	public static String getApplicationHomePath() {
		return getSystemProperty("user.dir");
	}

	/**
	 * Get value of a System property.
	 * 
	 * @author abhi
	 * @param key
	 * @return
	 */
	public static String getSystemProperty(String key) {
		String strValue = "";
		Properties properties = System.getProperties();
		strValue = properties.getProperty(key);
		return strValue;
	}

	// /////////////////

	/**
	 * This Method is used to update read permission in the case of Update Mode.
	 * 
	 * @author Uday Kumar
	 * @date 10-07-2007
	 * 
	 */
	// public static void updateReadPermission(RegistrationDTO registrationDTO,
	// int mode, boolean readPermission) throws Exception {
	// if (mode == Mode.MODE_NEW || mode == Mode.MODE_UPDATE) {
	// RegistrationSession objRegistrationSession = new RegistrationSession();
	// registrationDTO.setReadPermission(readPermission);
	// objRegistrationSession.updateReadPermission(registrationDTO);
	// }
	// }

	// public static Vector getPropertyStatusVector(RegistrationDTO
	// objRegistration) {
	// Vector vecPropStatus = new Vector();
	// if (objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_UNCLAIMED_PROPERTY)) {
	// // String propStatus = ResourceBundleManager.getValue(
	// // ResourceBundleManager.PROPERTY_STATUS, "2");
	// // vecPropStatus.add(propStatus);
	// String propStatus = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROPERTY_STATUS, "4");
	// vecPropStatus.add(propStatus);
	// } else if (objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_FIR)
	// || objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_OTHER_POLICESTATION)) {
	// String propStatus = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROPERTY_STATUS, "1");
	// vecPropStatus.add(propStatus);
	// propStatus = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROPERTY_STATUS, "5");
	// vecPropStatus.add(propStatus);
	// // Physcial evidence field added by gaurav gupta for Property panel
	// // for TN on 19/04/2011
	// propStatus = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROPERTY_STATUS, "10");
	// vecPropStatus.add(propStatus);
	// } else {
	// vecPropStatus = ResourceBundleManager
	// .getValues(ResourceBundleManager.PROPERTY_STATUS);
	// }
	//
	// return vecPropStatus;
	// }

	// public static Vector getPropertyBelongsToVector(
	// RegistrationDTO objRegistration) {
	// Vector vecPropBelongsTo = new Vector();
	// if (objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_UNCLAIMED_PROPERTY)) {
	// String propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "U");
	// vecPropBelongsTo.add(propBelongsTo);
	// } else if (objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_FIR)
	// || objRegistration.getRegistrationType().equalsIgnoreCase(
	// RegistrationDTO.TYPE_OTHER_POLICESTATION)) {
	// String propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "A");
	// vecPropBelongsTo.add(propBelongsTo);
	// propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "V");
	// vecPropBelongsTo.add(propBelongsTo);
	// propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "G");
	// vecPropBelongsTo.add(propBelongsTo);
	// propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "B");
	// vecPropBelongsTo.add(propBelongsTo);
	// propBelongsTo = ResourceBundleManager.getValue(
	// ResourceBundleManager.BELONGS_TO, "C");
	// vecPropBelongsTo.add(propBelongsTo);
	// } else {
	// vecPropBelongsTo = ResourceBundleManager
	// .getValues(ResourceBundleManager.BELONGS_TO);
	// }
	// return vecPropBelongsTo;
	// }

	// public static boolean showDialogForProperty(PropertyDTO objPropertyDB,
	// CDialog parent, int mode) {
	// CDialog dialog = null;
	// if (objPropertyDB instanceof NumberedDTO) {
	// dialog = new InvestNumberedProperty((NumberedDTO) objPropertyDB,
	// parent, mode);
	//
	// } else if (objPropertyDB instanceof CurrencyDTO) {
	// dialog = new InvestCurrencyProperty((CurrencyDTO) objPropertyDB,
	// parent, mode);
	// } else if (objPropertyDB instanceof AutomobileDTO) {
	// dialog = new InvestAutomobileProperties(
	// (AutomobileDTO) objPropertyDB, parent, mode);
	//
	// } else if (objPropertyDB instanceof CulturalDTO) {
	// dialog = new InvestCulturalProperty((CulturalDTO) objPropertyDB,
	// parent, mode);
	//
	// } else if (objPropertyDB instanceof NarcoticsDTO) {
	// dialog = new InvestDrugNarcoticProperty(
	// (NarcoticsDTO) objPropertyDB, parent, mode);
	//
	// } else {
	// dialog = new InvestProperties(objPropertyDB, parent, mode);
	// }
	//
	// if (dialog != null) {
	// dialog.setVisible(true);
	// return true;
	// }
	// return false;
	//
	// }

	// public static boolean showDialogForProperty(
	// RegistrationDTO objRegistration, PropertyDTO objPropertyDB,
	// JFrame parent, int mode) {
	// CDialog dialog = null;
	// if (objPropertyDB instanceof NumberedDTO) {
	// dialog = new InvestNumberedProperty(objRegistration,
	// (NumberedDTO) objPropertyDB, parent, mode);
	//
	// } else if (objPropertyDB instanceof CurrencyDTO) {
	// dialog = new InvestCurrencyProperty(objRegistration,
	// (CurrencyDTO) objPropertyDB, parent, mode);
	// } else if (objPropertyDB instanceof AutomobileDTO) {
	// dialog = new InvestAutomobileProperties(objRegistration,
	// (AutomobileDTO) objPropertyDB, parent, mode);
	//
	// } else if (objPropertyDB instanceof CulturalDTO) {
	// dialog = new InvestCulturalProperty(objRegistration,
	// (CulturalDTO) objPropertyDB, parent, mode);
	//
	// } else if (objPropertyDB instanceof NarcoticsDTO) {
	// dialog = new InvestDrugNarcoticProperty(objRegistration,
	// (NarcoticsDTO) objPropertyDB, parent, mode);
	//
	// } else {
	// dialog = new InvestProperties(objRegistration, objPropertyDB,
	// parent, mode);
	// }
	//
	// if (dialog != null) {
	// dialog.setVisible(true);
	// return true;
	// }
	// return false;
	//
	// }

	/**
	 * displays form corresponding to Registration Type
	 * 
	 * @author Rohit
	 */
	// public static CFrame getFormForRegistration() {
	// CFrame frame = null;
	// if (objRegistration instanceof RegFirDTO) {
	// if (objRegistration instanceof RegFirOtherPSDTO) {
	// frame = new RegFIRRegistrationOtherPSUI(
	// (RegFirOtherPSDTO) objRegistration, mode, parent);
	// } else {
	// frame = new RegFIRRegistrationUI((RegFirDTO) objRegistration,
	// mode, parent);
	//
	// }
	// } else if (objRegistration instanceof RegDeserterDTO) {
	// frame = new RegDeserterCaseUI((RegDeserterDTO) objRegistration,
	// mode, parent);
	// } else if (objRegistration instanceof RegFirOtherPSDTO) {
	// frame = new RegFIRRegistrationOtherPSUI(
	// (RegFirOtherPSDTO) objRegistration, mode, parent);
	// } else if (objRegistration instanceof RegMissingDTO) {
	// frame = new RegMissingPersonReportUI(
	// (RegMissingDTO) objRegistration, mode, parent);
	// } else if (objRegistration instanceof RegMLCDTO) {
	// frame = new RegMLCUI((RegMLCDTO) objRegistration, mode, parent);
	// } else if (objRegistration instanceof RegOtherCasesDTO) {
	// RegOtherCasesDTO regOtherCases = (RegOtherCasesDTO) objRegistration;
	// if (regOtherCases.getSubType().equalsIgnoreCase("10")) {
	// frame = new RegNonCognizableReportUI(regOtherCases, mode,
	// parent);
	// } else {
	// frame = new RegRegistrationtionOtherCasesUI(regOtherCases,
	// mode, parent);
	// }
	// } else if (objRegistration instanceof RegUnclaimedPropertyDTO) {
	// frame = new RegUnclaimedPropertiesUI(
	// (RegUnclaimedPropertyDTO) objRegistration, mode, parent);
	// } else if (objRegistration instanceof RegUnnaturalDeathDTO) {
	// frame = new RegUnnaturalDeathUI(
	// (RegUnnaturalDeathDTO) objRegistration, mode, parent);
	// }
	// return frame;
	// }

	// public static CFrame getFormForRegistrationTN(
	// RegistrationDTO objRegistration, int mode, JFrame parent) {
	// CFrame frame = null;
	// frame = new
	// gov.nic.cipa.tn29.client.ui.registration.RegFIRRegistrationUI(
	// (RegFirDTO) objRegistration, mode, parent);
	// // if (objRegistration instanceof RegFirDTO) {
	// // if (objRegistration instanceof RegFirOtherPSDTO) {
	// // frame = new RegFIRRegistrationOtherPSUI(
	// // (RegFirOtherPSDTO) objRegistration, mode, parent);
	// // } else {
	// // frame = new RegFIRRegistrationUI((RegFirDTO) objRegistration,
	// // mode, parent);
	// //
	// // }
	// // } else if (objRegistration instanceof RegDeserterDTO) {
	// // frame = new RegDeserterCaseUI((RegDeserterDTO) objRegistration, mode,
	// // parent);
	// // } else if (objRegistration instanceof RegFirOtherPSDTO) {
	// // frame = new RegFIRRegistrationOtherPSUI(
	// // (RegFirOtherPSDTO) objRegistration, mode, parent);
	// // } else if (objRegistration instanceof RegMissingDTO) {
	// // frame = new RegMissingPersonReportUI((RegMissingDTO) objRegistration,
	// // mode, parent);
	// // } else if (objRegistration instanceof RegMLCDTO) {
	// // frame = new RegMLCUI((RegMLCDTO) objRegistration, mode, parent);
	// // } else if (objRegistration instanceof RegOtherCasesDTO) {
	// // RegOtherCasesDTO regOtherCases = (RegOtherCasesDTO)objRegistration;
	// // if(regOtherCases.getSubType().equalsIgnoreCase("10")){
	// // frame = new RegNonCognizableReportUI(
	// // regOtherCases, mode, parent);
	// // }else{
	// // frame = new RegRegistrationtionOtherCasesUI(
	// // regOtherCases, mode, parent);
	// // }} else if (objRegistration instanceof RegUnclaimedPropertyDTO) {
	// // frame = new RegUnclaimedPropertiesUI(
	// // (RegUnclaimedPropertyDTO) objRegistration, mode, parent);
	// // } else if (objRegistration instanceof RegUnnaturalDeathDTO) {
	// // frame = new RegUnnaturalDeathUI(
	// // (RegUnnaturalDeathDTO) objRegistration, mode, parent);
	// // }
	// return frame;
	// }

	// Added By C.P.Singh 27.12.2011
	// public static CFrame getFormForRegistrationTN(
	// RegistrationDTO objRegistration, int mode, JFrame parent,
	// boolean fir) {
	// CFrame frame = null;
	//
	// frame = new
	// gov.nic.cipa.tn29.client.ui.registration.RegFIRRegistrationUI(
	// (RegFirDTO) objRegistration, mode, parent, true);
	//
	// return frame;
	// }

	/**
	 * displays form corresponding to events
	 * 
	 * @author akash Grover
	 */
	// public static void showFormForEvent(ProgressEventDTO objEventDB,
	// ProgressEventDrivenDTO objInvestProsDB, JFrame parent)
	// throws Exception {
	//
	// // //////////////////////////
	// // Abhi Jul 18, 2005, 12:18:42 PM
	// // Factory implemented
	// // ProgressEventDTO objSpecEventDB = ProgressEventDTO
	// // .instantiateProgressEventForCode(objEventDB.getEventCode(),
	// // objInvestProsDB);
	// InvestHeaderPanel investHeaderPanel = new InvestHeaderPanel();
	// Vector vecEvents = objInvestProsDB.getAllProgressEvents();
	// ProgressEventDTO lastEventDTO = (ProgressEventDTO) vecEvents
	// .lastElement();
	//
	// ProgressEventFactory eventFactory = ProgressEventFactory.getInstance();
	// ProgressEventDTO objSpecEventDB = (ProgressEventDTO) eventFactory
	// .createEvent(objEventDB.getEventCode(), objInvestProsDB);
	//
	// /*
	// * @author nirmal
	// *
	// * @date : 7-01-08
	// *
	// * @description : if last event is unfreezed, then progress date is
	// * editable (investHeaderPanel.setEditable(true);is editable) else false
	// */
	// ProgressEventSession progEventSession = new ProgressEventSession();
	//
	// objSpecEventDB.setEventSr(objEventDB.getEventSr()); // IMPORTANT: always
	// // set eventSR
	// // before retrieve
	// objSpecEventDB.setLocationID(objEventDB.getLocationID());
	//
	// progEventSession.retrieve(objSpecEventDB);
	// investHeaderPanel.currEvent = objSpecEventDB.getEventSr(); // set
	// // current
	// // event to
	// // investHeaderPanel.currEvent.
	// // by rajesh
	// if (lastEventDTO.getEventCode().equals(objSpecEventDB.getEventCode())
	// && !lastEventDTO.isFreezed()
	// && (lastEventDTO.getEventSr()) == (objSpecEventDB.getEventSr())) {
	//
	// investHeaderPanel.setEditable(true);
	// // investHeaderPanel.validation(lastEventDTO.getEventDate());
	// } else {
	//
	// investHeaderPanel.setEditable(false);
	// }
	//
	// //
	// objSpecEventDB.setEventDate(Utility.getDateFromDateString(investHeaderPanel.jdcProgressDateVal.getDate()));
	// String strEventCode = objSpecEventDB.getEventCode();
	//
	// /*
	// * Before opening Event we have to check for IO, and if current IO is
	// * not the same as the IO at the time of Investigation for this event,
	// * as it is possible after CaseTransferedToOtherIO Event. Then we have
	// * to change the IO accordingly.
	// *
	// * @author Manoj
	// */
	// CaseTransferredSession caseTransferredSession = new
	// CaseTransferredSession();
	// String ioForEvent = caseTransferredSession.fetchIOForEvent(
	// objSpecEventDB, objInvestProsDB);
	// String originalIO = objSpecEventDB.getRegistration()
	// .getInvestigatingOfficer();
	//
	// if (ioForEvent != null && ioForEvent.length() != 0) {
	// objSpecEventDB.getRegistration()
	// .setInvestigatingOfficer(ioForEvent);
	// }
	// /*
	// * @author Fateh Singh
	// *
	// * @date : 7-11-06
	// *
	// * @description : Before opening Event we have to check for Local Head,
	// * and if current Local Head is not the same as the Local Head at the
	// * time of Investigation for this event, as it is possible after
	// * Alteration Memo Event. Then we have to change the Local Head
	// * accordingly.
	// */
	// RegistrationDTO objRegistrationDTO = objSpecEventDB.getRegistration();
	// String localHeadForEvent = null;
	// String originalLocalHead = null;
	//
	// if (objRegistrationDTO instanceof RegFirDTO) {
	// AlterationMemoSession alterationMemoSession = new
	// AlterationMemoSession();
	// localHeadForEvent = alterationMemoSession.fetchLocalHeadForEvent(
	// objSpecEventDB, objInvestProsDB);
	// originalLocalHead = ((RegFirDTO) objRegistrationDTO).getLocalHead();
	//
	// if (localHeadForEvent != null && localHeadForEvent.length() != 0) {
	// ((RegFirDTO) objRegistrationDTO)
	// .setLocalHead(localHeadForEvent);
	// }
	// }
	//
	// CDialog form = null;
	// if (strEventCode.equals(ProgressEventConstants.EC_CRIME_DETAIL)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new gov.nic.cipa.tn29.client.ui.investigation.InvestCrimeDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	//
	// form = new InvestCrimeDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CHANCE_FINGERPRINT_SENT_TO_AGENCY)) {
	// form = new InvestChanceFPSentToAgency(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CHANCE_FINGERPRINT_REPORT_RECD)) {
	// form = new InvestChanceFingerPrintReport(objSpecEventDB, parent); //
	// rekha
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_VICTIM_DETAIL_EVENT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestAccusedVictimsDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestAccusedVictimsDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_ARREST_SURRENDER_EVENT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestAccusedArrestSurrenderUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestAccusedArrestSurrender(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_FP_SENT_FOR_MATCH)) {
	// form = new InvestFingerPrintSent(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_FP_RECD_AFTER_MATCH)) {
	// form = new InvestFingerPrintRecv(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_INFOSHEET_SENT_TO_POLICE_STATION))
	// {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestAccusedInfoSheetSentToPS(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	//
	// form = new InvestAccusedInfoSheetSentToPS(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_RELEASED_ON_BAIL)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestAccusedReleOnBail(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestAccusedReleOnBail(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_SENT_TO_REMAND_CUSTODY)) {
	// form = new InvestAccusedSentPoliceRemand(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_INFOSHEET_SENT_TO_POLICE_STATION))
	// {
	//
	// form = new InvestAccusedInfoSheetSentToPS(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_SEIZED_RECOVERED)) {
	//
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestPropertiesSeizure(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	//
	// form = new InvestPropertiesSeizure(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_DEPOSITED_IN_MALKHANA)) {
	// form = new InvestPropDepositMalkhana(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_RELEASED_FROM_MALKHANA)) {
	// form = new InvestPropReleFromMalkhana(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_SENT_FOR_ANALYSIS_AND_EXPERT_OPINION))
	// {
	// form = new InvestPropSentAnalysisFSL(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_ANALYSIS_REPORT_RECEIVED)) {
	// form = new InvestPropAnalysisReceivFSL(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_PRESENTED_IN_COURT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestPropPresentCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestPropPresentCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_PROP_MATCHED)) {
	// form = new InvestPropMatchedStolen(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_WITNESS_DETAILS_CAPTURED)) {
	// if (Location.getLocation().substring(0, 2).equals("29")) {
	// form = new gov.nic.cipa.tn29.client.ui.registration.RegWitnessStmt(
	// (WitnessDetailsCapturedDTO) objSpecEventDB, parent);
	// } else {
	// form = new gov.nic.cipa.core.client.ui.registration.RegWitnessStmt(
	// (WitnessDetailsCapturedDTO) objSpecEventDB, parent);
	// }
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_FILED_IN_COURT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_INVESTIGATION_DETAILS_SENT_TO_PP))
	// {
	// form = new InvestFinalReportSentToPP(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_MATCHED)) {
	// form = new InvestMssingPersonMatchedUIDB(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_REPORTED_TO_MPS)) {
	// form = new InvestMissingPersonRepotedMPSPCR(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_SCAN)) {
	// form = new InvestMissingPersonScan(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_REPORTED_TO_MPS)) {
	// form = new InvestMissingPersonRepotedMPSPCR(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MLC_DEPOSITEDIN_HOSPITAL)) {
	// form = new InvestMLCDepositHospital(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MLC_INJURED_PERSON_STATEMENT)) {
	// form = new InvestInjuredPersonStmtRecorded(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQBODY_SENT_FOR_PM)) {
	// form = new InvestDeadBodySentPostmortem(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQBODY_HANDEDOVER_AFTER_PM)) {
	// form = new InvestDeadBodyHandedOver(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_PM_REPORT_RECD)) {
	// form = new InvestPostMortemRepoReceive(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_REPORT_SENT_TO_MAGISTRATE)) {
	// form = new InvestInquestRepoSentMagistrate(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_REPORT_RECD_FROM_MAGISTRATE)) {
	// form = new InvestInquestRepoReceivMagistrate(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ALTERATION_MEMO)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestAlterationMemo(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestAlterationMemo(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CASE_TRANSFERRED)) {
	// form = new InvestCaseTransferToOtherIOPSAgency(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// // //////////added code for stopped/closed case, by Rajesh :3-3-2008
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CASE_CLOSED_STOPPED)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_CASE_RE_OPEN)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// // //////////////////////// on date : 3-3-2008
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_GENERATED)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_INVESTIGATION_DETAILS_GENERATED))
	// {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_FILED_IN_COURT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_TRACED)) {
	// form = new InvestMissingPersonTraced(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_PROCLAIMED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHMENT_ORDER)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_REWARD)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_RETURNED_BY_COURT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalRepoReturnedByCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalRepoReturnedByCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// }
	// // ////////////////////////////PROSECUTION
	// // EVENTS///////////////////////////////
	// else if (strEventCode.equals(ProgressEventConstants.EC_CASE_HEARING)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new gov.nic.cipa.tn29.client.ui.prosecution.ProsHearingInCourtUI(
	// (CaseHearingDTO) objSpecEventDB, parent);
	// else
	// form = new ProsHearingInCourtUI(
	// (CaseHearingDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_SUMWAR_EXECUTED)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.prosecution.ProsSummonWarrantExecutedUI(
	// (SumWarExecutedDTO) objSpecEventDB, parent);
	// else
	// form = new ProsSummonWarrantExecutedUI(
	// (SumWarExecutedDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_SUMWAR_ISSUED))
	// {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.prosecution.ProsSummonWarrantIssuedUI(
	// (SumWarIssuedDTO) objSpecEventDB, parent);
	// else
	// form = new ProsSummonWarrantIssuedUI(
	// (SumWarIssuedDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_PROCLAIMED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.prosecution.ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// else
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHMENT_ORDER)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.prosecution.ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// else
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_REWARD)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.prosecution.ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// else
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_COURT_DISPOSAL)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new gov.nic.cipa.tn29.client.ui.prosecution.ProsCourtDisposalUI(
	// (CourtDisposalDTO) objSpecEventDB, parent);
	// else
	// form = new ProsCourtDisposalUI(
	// (CourtDisposalDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CONVICT_FP_SENT_FOR_RECORD)) { // this
	// // else
	// // condition
	// // is
	// // added
	// // for
	// // pros
	// // FPSent
	// // for
	// // Record
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new gov.nic.cipa.tn29.client.ui.prosecution.ProsFingerPrintSent(
	// objSpecEventDB, parent);
	// else
	// form = new ProsFingerPrintSent(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CONVICT_FP_RECD_AFTER_MATCH)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFingerPrintReportRecv(
	// objSpecEventDB, parent);
	// else
	// form = new InvestFingerPrintReportRecv(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_RECE_FROM_PP)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new
	// gov.nic.cipa.tn29.client.ui.investigation.InvestFinalReportReceivedfromPPUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// else
	// form = new InvestFinalReportReceivedfromPPUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_TRANSFER_OF_COURT)) {
	// if (Location.getLocation().substring(0, 2).equals("29"))
	// form = new gov.nic.cipa.tn29.client.ui.prosecution.ProsTransferOfCourt(
	// (TransferOfCourtDTO) objSpecEventDB, parent);
	// else
	// form = new ProsTransferOfCourt(
	// (TransferOfCourtDTO) objSpecEventDB, parent);
	// }
	// /**
	// * Check for the status of admin login, He should permit to view all the
	// * corresponding cases of a particular registration type Sr. No. and he
	// * can only enter the event "case transfered to  other IO/PS/Agency"
	// * event code 31100, so disable the ui components
	// *
	// * @author: rekha
	// * @dated: 05-03-2008
	// *
	// */
	//
	// // ///// check SUB-IO/Main-IO code, if code not zero then form open in
	// // view mode, rajesh sumit . dt.30-04-2008
	// String ioCode = "";
	// String loggedInUserPISCode = "";
	// if (Location.getLocation().substring(0, 2).equals("29")) {
	// loggedInUserPISCode = gov.nic.cipa.tn29.client.main.UserLoginScreen
	// .getScreenInstance().getPoliceStationStaff().getPisCode();
	// } else {
	// loggedInUserPISCode = UserLoginScreen.getScreenInstance()
	// .getPoliceStationStaff().getPisCode();
	// }
	// // String loggedInUserPISCode =
	// //
	// UserLoginScreen.getScreenInstance().getPoliceStationStaff().getPisCode();
	// AssociateIOSession objAssociateIOSession = new AssociateIOSession();
	// try {
	// ioCode = objAssociateIOSession.checkAssociateMainIOs(objEventDB
	// .getLocationID(), objEventDB.getRegistration()
	// .getRegistrationSrNo(), loggedInUserPISCode);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // ///////
	// if (!(checkForAdmin())) {
	// if (!strEventCode.equals("31100")) {
	// setFormUIDisable(form); // "VIEW ONLY");
	// } else {
	// form.setVisible(true);
	// }
	// } else if (!checkForDCPandACP()) { // added by rajesh dt:29-08-2008
	// setFormUIDisable(form);
	// } else if (Location.getLocation().substring(0, 2).equals("08")) { //
	// added
	// // code
	// // for
	// // delhi
	// // dt.17-6-2008.
	// form.setVisible(true);
	// } else if (!ioCode.equals("") && !ioCode.substring(0, 1).equals("0")) {
	// // added
	// // by
	// // rajesh
	// // dt:30-04-2008
	// // //
	// // changed
	// // by
	// // sumit
	// // as
	// // subio_code
	// // has
	// // been
	// // changed
	// // to
	// // character2
	// // .
	// // dt
	// // 06-05-2009
	// setFormUIDisable(form); // ,"VIEW ONLY");
	// } else {
	// form.setVisible(true);
	// }
	//
	// // After displaying Event we have to refresh the IO with current IO in
	// // RegistrationDTO : manoj
	// objSpecEventDB.getRegistration().setInvestigatingOfficer(originalIO);
	// // After displaying Event we have to refresh the Local Head with current
	// // Local Head in RegFirDTO : fateh singh
	// if (objRegistrationDTO instanceof RegFirDTO
	// && originalLocalHead != null) {
	// ((RegFirDTO) objRegistrationDTO).setLocalHead(originalLocalHead);
	// }
	//
	// }

	// //////////
	/**
	 * Set the components of form disable, it should be open in view mode for
	 * admin case
	 * 
	 * @author: rekha
	 * @dated: 05-03-2008
	 * 
	 */
	// private static void setFormUIDisable(CDialog form) { // ,String mode){
	// for (int count = 0; count < form.getContentPane().getComponentCount();
	// count++) {
	// Component comp = form.getContentPane().getComponent(count);
	//
	// System.out.println("- component ....." + comp.toString());
	// if (comp.toString().contains("Panel")
	// || comp.toString().contains("COfficeTxtArea"))
	// setPanelDisable(comp); // ,mode);
	// else if (comp.toString().contains("QUIT"))
	// comp.setEnabled(true);
	// else if (comp.toString().contains("Label"))
	// comp.setEnabled(true);
	// else if (comp.toString().contains("JScrollPane")) {
	// for (int compcount = 0; compcount < ((javax.swing.JScrollPane) comp)
	// .getComponentCount(); compcount++)
	// if (((javax.swing.JScrollPane) comp)
	// .getComponent(compcount).toString()
	// .contains("JView")) {
	// javax.swing.JViewport vport = ((javax.swing.JViewport)
	// ((javax.swing.JScrollPane) comp)
	// .getComponent(compcount));
	// for (int vpcount = 0; vpcount < vport
	// .getComponentCount(); vpcount++) {
	// vport.getComponent(vpcount).setEnabled(false);
	// }
	// }
	// } else {
	// comp.setEnabled(false);
	// }
	// }
	// form.setVisible(true);
	// }

	/**
	 * Set the components of panel disable, it should be open in view mode for
	 * admin case
	 * 
	 * @author: rekha
	 * @dated: 05-03-2008
	 * 
	 */
	private static void setPanelDisable(Component pnl) { // ,String mode){
		for (int pnlcompcount = 0; pnlcompcount < ((javax.swing.JPanel) pnl)
				.getComponentCount(); pnlcompcount++) {
			Component pnlComp = ((javax.swing.JPanel) pnl)
					.getComponent(pnlcompcount);
			if (pnlComp.toString().contains("Panel"))
				setPanelDisable(pnlComp); // ,mode);
			else if (pnlComp.toString().contains("QUIT"))
				pnlComp.setEnabled(true);
			else if (pnlComp.toString().contains("Label"))
				pnlComp.setEnabled(true);
			else if (pnlComp.toString().contains("JScrollPane")) {
				for (int compcount = 0; compcount < ((javax.swing.JScrollPane) pnlComp)
						.getComponentCount(); compcount++)
					if (((javax.swing.JScrollPane) pnlComp)
							.getComponent(compcount).toString()
							.contains("JView")) {
						javax.swing.JViewport vport = ((javax.swing.JViewport) ((javax.swing.JScrollPane) pnlComp)
								.getComponent(compcount));
						for (int vpcount = 0; vpcount < vport
								.getComponentCount(); vpcount++) {
							// if(!mode.equals("VIEW ONLY"))
							// vport.getComponent(vpcount).setEnabled(true);
							// else
							vport.getComponent(vpcount).setEnabled(false);
						}
					}
			} else {
				// if(!mode.equals("VIEW ONLY"))
				// pnlComp.setEnabled(true);
				// else
				pnlComp.setEnabled(false);
			}

		}
	}

	/**
	 * Check for the status of admin
	 * 
	 * @author: rekha
	 * @dated: 05-03-2008
	 * 
	 */
	// public static boolean checkForAdmin() {
	// try {
	// if (Location.getLocation().substring(0, 2).equals("29")) {
	// if (gov.nic.cipa.tn29.client.main.UserLoginScreen
	// .getScreenInstance().getPoliceStationStaff()
	// .getPisCode().equals("********")) {
	// return false;
	// }
	// } else {
	// if (UserLoginScreen.getScreenInstance().getPoliceStationStaff()
	// .getPisCode().equals("********")) {
	// return false;
	// }
	// }
	// } catch (Exception e) {
	// return false;
	// }
	// return true;
	// }

	/**
	 * This method check DCP/ACP is logged user
	 * 
	 * @return boolean
	 * @author Rajesh Kumar
	 * @Date 01-09-2008
	 */
	// public static boolean checkForDCPandACP() {
	// try {
	// if (Location.getLocation().substring(0, 2).equals("29")) {
	// if (gov.nic.cipa.tn29.client.main.UserLoginScreen
	// .getScreenInstance().getPoliceStationStaff()
	// .getPisCode().equals("@@@@@@@@")
	// || gov.nic.cipa.tn29.client.main.UserLoginScreen
	// .getScreenInstance().getPoliceStationStaff()
	// .getPisCode().equals("$$$$$$$$")) {
	// return false;
	// }
	// } else if (UserLoginScreen.getScreenInstance()
	// .getPoliceStationStaff().getPisCode().equals("@@@@@@@@")
	// || UserLoginScreen.getScreenInstance()
	// .getPoliceStationStaff().getPisCode()
	// .equals("$$$$$$$$")) {
	// return false;
	// }
	// } catch (Exception e) {
	// return false;
	// }
	// return true;
	// }

	// public static boolean confirmRegSave(String msg,
	// RegistrationDTO objRegistration, JFrame parent) {
	// if (objRegistration.getRegistrationTypeSrNo() != 0) {
	// return true;
	// }
	// int ans = Feedback.showOptionYesNo(parent, "Save", msg);
	// if (ans == JOptionPane.NO_OPTION) {
	// if (objRegistration.getRegistrationTypeSrNo() == 0) {
	// Feedback.showInfoMessage(
	// parent,
	// ResourceBundleManager.getValue(
	// ResourceBundleManager.FEEDBACK_INFO_MESSAGE,
	// "Save"),
	// ResourceBundleManager.getValue(
	// ResourceBundleManager.FEEDBACK_INFO_MESSAGE,
	// "BookNoexist")
	// + objRegistration.getRegistrationSrNo());
	// }
	// return false;
	// }
	// return true;
	// }

	// ////////////////////////////////////validation functions
	// begin///////////////////////////

	public static boolean validateTFNotEmpty(
			javax.swing.text.JTextComponent objTextField, Vector errList,
			String msg) {
		if (objTextField.getText().trim().equals("")) {
			errList.add(ErrorMessages.getErrorCodeDesc(msg));
			return false;
		}
		return true;
	}

	/**
	 * returns false if both DC and TF are empty
	 * 
	 * @author Akash Grover
	 */
	// public static boolean validateDateChooserTFNotEmpty(JDateChooser objDC,
	// JTextField objTextField, Vector errList, String msg) {
	// if (objDC.getDate().trim().equals("")
	// || objTextField.getText().trim().equals("")) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	// public static boolean validateDateChooserNotEmpty(JDateChooser objDC,
	// Vector errList, String msg) {
	// if (objDC.getDate().trim().equals("")) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	public static boolean validateTFFloat(JTextField objTextField,
			Vector errList, int intIndx, int floatIndx, String msg) {
		String txtInput = objTextField.getText();
		if (txtInput.trim().equals("")) {
			return true;
		}
		try {
			int dotIndx = txtInput.indexOf('.');
			if (dotIndx < 0 && txtInput.length() <= intIndx) {
				return validateTFInteger(objTextField, errList, msg);

			}
			int intLength = txtInput.substring(0, dotIndx).length();
			int floatLength = txtInput.length() - dotIndx - 1;
			if (intLength > intIndx || floatLength > floatIndx) {
				throw new Exception();
			}

			Float.parseFloat(txtInput.trim());
		} catch (Exception e) {
			errList.add(ErrorMessages.getErrorCodeDesc(msg));
			return false;
		}
		return true;
	}

	public static boolean validateTFNotZero(JTextField objTextField,
			Vector errList, String msg) {
		if (!validCharField(objTextField.getText())) {
			errList.add(ErrorMessages.getErrorCodeDesc(msg));
			return false;
		}
		return true;
	}

	public static boolean validateTFInteger(JTextField objTextField,
			Vector errList, String msg) {
		if (objTextField.getText().trim().equals(""))
			return true;

		try {
			Long.parseLong(objTextField.getText().trim());
		} catch (Exception e) {
			errList.add(ErrorMessages.getErrorCodeDesc(msg));
			return false;
		}
		return true;
	}

	// public static boolean validateDateChooser(JDateChooser objDC,
	// Vector errList, String msg) {
	//
	// if (objDC.getDate().trim().equals(""))
	// return true;
	// if (!validateDateString(objDC.getDate())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	/**
	 * returns true if both DC and TF are empty if not empty checks the validity
	 * of both date and time
	 * 
	 * @author Akash Grover
	 */
	// public static boolean validateDateChooserTF(JDateChooser objDC,
	// JTextField objTextField, Vector errList, String dtMsg,
	// String timeMsg) {
	// if (objDC.getDate().trim().equals("")
	// && objTextField.getText().trim().equals(""))
	// return true;
	//
	// boolean valid = true;
	// if (!Utility.validateDateString(objDC.getDate())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(dtMsg));// );
	// valid = false;
	// }
	// if (!Utility.validateTime(objTextField.getText().trim())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(timeMsg));// );
	// valid = false;
	// }
	//
	// if (valid
	// && !Utility.validateDateStringTimeString(objDC.getDate(),
	// objTextField.getText().trim())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(dtMsg));
	// valid = false;
	// }
	// return valid;
	// }

	/**
	 * validates that date1:time1<=date2:time2 expects date time formats are
	 * correct and has been validated previously
	 * 
	 * @author Akash Grover use validateDate() & validateTime() before using thi
	 */
	private static boolean validateDateSequence(java.util.Date date1,
			Time time1, java.util.Date date2, Time time2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		if (date2.before(date1)) {
			return false;// ERROR
		} else if (date2.equals(date1)) {
			if (time1 != null && time2 != null) {
				String strTime1 = getTimeString(time1);
				String strTime2 = getTimeString(time2);
				if (strTime2.compareTo(strTime1) < 0) {
					return false;// ERROR
				}
			}
		}
		return true;
	}

	// public static boolean validateDateTimeSequence(DateTimeComponent dtc1,
	// DateTimeComponent dtc2, Vector errList, String msg) {
	// if (!validateDateSequence(dtc1.getDate(), dtc1.getTime(),
	// dtc2.getDate(), dtc2.getTime())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	// public static boolean validateDateTimeSequenceTN(
	// gov.nic.cipa.tn29.client.ui.components.DateTimeComponent dtc1,
	// gov.nic.cipa.tn29.client.ui.components.DateTimeComponent dtc2,
	// Vector errList, String msg) {
	// if (!validateDateSequence(dtc1.getDate(), dtc1.getTime(),
	// dtc2.getDate(), dtc2.getTime())) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	// public static boolean validateDateTimeSequence(JDateChooser dc1,
	// DateTimeComponent dtc2, Vector errList, String msg) {
	// java.util.Date date1 = Utility.getDateFromDateString(dc1.getDate());
	//
	// if (!validateDateSequence(date1, null, dtc2.getDate(), null)) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }
	//
	// public static boolean validateDateTimeSequenceTN(JDateChooser dc1,
	// gov.nic.cipa.tn29.client.ui.components.DateTimeComponent dtc2,
	// Vector errList, String msg) {
	// java.util.Date date1 = Utility.getDateFromDateString(dc1.getDate());
	//
	// if (!validateDateSequence(date1, null, dtc2.getDate(), null)) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }
	//
	// public static boolean validateDateTimeSequence(DateTimeComponent dtc1,
	// JDateChooser dc2, Vector errList, String msg) {
	// java.util.Date date2 = Utility.getDateFromDateString(dc2.getDate());
	// if (!validateDateSequence(dtc1.getDate(), dtc1.getTime(), date2, null)) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }
	//
	// public static boolean validateDateTimeSequence(JDateChooser dc1,
	// JDateChooser dc2, Vector errList, String msg) {
	// java.util.Date date1 = Utility.getDateFromDateString(dc1.getDate());
	// java.util.Date date2 = Utility.getDateFromDateString(dc2.getDate());
	// if (!validateDateSequence(date1, null, date2, null)) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }
	//
	// public static boolean validateDateTimeSequence(java.util.Date date,
	// DateTimeComponent dtc, Vector errList, String msg) {
	// java.util.Date date1 = date;
	// java.util.Date date2 = dtc.getDate();
	// if (!validateDateSequence(date1, null, date2, null)) {
	// errList.add(ErrorMessages.getErrorCodeDesc(msg));
	// return false;
	// }
	// return true;
	// }

	public static boolean validateComboNotNull(JComboBox objCombo,
			Vector errList, String msg) {
		if (objCombo.getSelectedItem() == null) {
			errList.add(ErrorMessages.getErrorCodeDesc(msg));
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @author VIJI added methods TimevalidationTN() ,
	 *         getTimeFromTimeSpinField() and getTimeStringSpinField() for
	 *         getting time string from spin field, getting time from time
	 *         spinfield and valiadate spinfield time
	 */

	public static boolean TimevalidationTN(String time) {
		if (time.length() > 4)
			return false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			sdf.setLenient(false);
			if (sdf.parse(time) == null)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static java.sql.Time getTimeFromTimeSpinFieldTN(String time)
			throws Exception {

		if (!TimevalidationTN(time)) {
			return null;
		}
		int hour = 0;
		int mins = 0;
		try {
			hour = Integer.parseInt(time.substring(0, 2));
			mins = Integer.parseInt(time.substring(2, 4));

		} catch (NumberFormatException e) {
			return null;
		}
		return Utility.getTime(hour, mins, 0);
	}

	// public static String getTimeStringFromSpinFieldTN(JSpinField spnFldHour,
	// JSpinField spnFldMin) {
	// String timespn;
	// String hour, min;
	// if ((spnFldHour.getValue() >= 0 && spnFldHour.getValue() < 10)
	// && (spnFldMin.getValue() >= 0 && spnFldMin.getValue() < 10)) {
	// hour = "0" + spnFldHour.getValue();
	// min = "0" + spnFldMin.getValue();
	// timespn = hour + min;
	// } else if (spnFldHour.getValue() >= 0 && spnFldHour.getValue() < 10) {
	// hour = "0" + spnFldHour.getValue();
	// timespn = hour + spnFldMin.getValue();
	// } else if (spnFldMin.getValue() >= 0 && spnFldMin.getValue() < 10) {
	// min = "0" + spnFldMin.getValue();
	// timespn = spnFldHour.getValue() + min;
	// } else if ((spnFldHour.getValue() >= 0 && spnFldHour.getValue() < 10)
	// && (spnFldMin.getValue() >= 0 && spnFldMin.getValue() < 10)) {
	// hour = "0" + spnFldHour.getValue();
	// min = "0" + spnFldMin.getValue();
	// timespn = hour + min;
	// } else {
	// timespn = String.valueOf(spnFldHour.getValue())
	// + String.valueOf(spnFldMin.getValue());
	// }
	// return timespn;
	// }

	/*
	 * @author Jatin Ramtrimethod to add leading zeroesi=number to be
	 * formatted,len=length required
	 */
	public static String addLeadingZeroes(String s, int len) {

		// String s = Integer.toString(i);
		if (s.length() > len)
			return s.substring(0, len);
		else if (s.length() < len) // pad on left with zeros
			return "000000000000000000000000000".substring(0, len - s.length())
					+ s;
		else
			return s;
	}

	/**
	 * removes key part(the part before ':') from the string
	 * 
	 * @author Akash Grover
	 */
	public static String removeKeyFromValue(String str) {
		if (str == null) {
			return null;
		}
		int colunIndex = str.indexOf(':');
		if (colunIndex < 0) {
			return str;
		}
		return str.substring(colunIndex + 1).trim();
	}

	public static String removeLeadingZeros(String str) {
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int index = 0;
		for (; index < str.length(); index++) {
			if (chars[index] != '0') {
				break;
			}
		}
		return (index == 0) ? str : str.substring(index);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * get image from path relative to the application home folder i.e. user.dir
	 * 
	 * @author abhi
	 */
	public static ImageIcon getImageIcon(String path) {
		ImageIcon img = null;
		String effPath = "";
		effPath += path;
		/**
		 * In Class, getResource will delegate call to the calling ClassLoader
		 * In ClassLoader, getSystemResource will search path in the system
		 * class path.
		 */
		URL urlImage = Utility.class.getResource(effPath);

		if (urlImage != null) {
			img = new ImageIcon(urlImage);
		} else {
			// LogMgr.getLogger().error(Utility.class,
			// "Couldn't find image file: " + path);
			System.err.println("Couldn't find file: " + path);
			// return null;
		}
		// System.out.println("urlImage = " + urlImage);

		// System.out.println("getImageIcon ->" + effPath);
		// ImageIcon img = new ImageIcon(effPath);

		return img;
	}

	// this method changes the format of RegTypeSrNo to no/year
	// 20050001 to 1/2005
	// public static String formatRegTypeSrNo(RegistrationDTO objRegistration,
	// String regTypeSrNo) {
	//
	// try {
	// if (objRegistration != null
	// && (objRegistration instanceof RegOtherCasesDTO)) {
	//
	// int length = regTypeSrNo.length();
	// String displayTypeSrNo = regTypeSrNo.substring(length - 4);
	// if (length > 8) {// for A000 reg_type_srno increase work
	// displayTypeSrNo = regTypeSrNo.substring(length - 5);// 4
	// }
	// String year = regTypeSrNo.substring(2, 4);
	// int yr = Integer.parseInt(year);
	// int number = Integer.parseInt(displayTypeSrNo);
	// // regTypeSrNo = number+"/"+Utility.getYYYYFromDate(new
	// // java.util.Date());
	// // String currYear=""+Calendar.getInstance().get(Calendar.YEAR);
	// // currYear=currYear.substring(2,4);
	// // int currYr=Integer.parseInt(currYear)
	//
	// String currYear = ""
	// + Calendar.getInstance().get(Calendar.YEAR);
	// currYear = currYear.substring(2, 4);
	// int currYr = Integer.parseInt(currYear);
	// String yearPart = "20";
	// if (yr > currYr) {
	// yearPart = "19";
	// }
	// regTypeSrNo = number + "/" + yearPart + year;
	// } else {
	// // included regTypeSrNo for 9 digits
	// if (regTypeSrNo.length() >= 8 && regTypeSrNo.length() <= 10) {
	//
	// String registrationYear = regTypeSrNo.substring(0, 4);
	// String regTypeNumber = regTypeSrNo.substring(4);
	// int registrationTypeSrNo = Integer.parseInt(regTypeNumber);
	// regTypeSrNo = "" + registrationTypeSrNo + "/"
	// + registrationYear;
	// }
	// }
	// } catch (Exception e) {
	// Feedback.showException(null, e);
	// }
	//
	// return regTypeSrNo;
	// }

	// this method changes the format of RegTypeSrNo to no/year
	// 20050001 to 1/2005 by Aditya Shukla on 18 may 2011

	// public static String formatMVTypeSrNo(RegMVPettyCaseDTO objRegistration,
	// String regTypeSrNo) {
	//
	// try {
	// if (objRegistration != null) {
	//
	// int length = regTypeSrNo.length();
	// String displayTypeSrNo = regTypeSrNo.substring(length - 4);
	// if (length > 8) {// for A000 reg_type_srno increase work
	// displayTypeSrNo = regTypeSrNo.substring(length - 5);// 4
	// }
	// String year = regTypeSrNo.substring(2, 4);
	// int yr = Integer.parseInt(year);
	// int number = Integer.parseInt(displayTypeSrNo);
	// // regTypeSrNo = number+"/"+Utility.getYYYYFromDate(new
	// // java.util.Date());
	// // String currYear=""+Calendar.getInstance().get(Calendar.YEAR);
	// // currYear=currYear.substring(2,4);
	// // int currYr=Integer.parseInt(currYear)
	//
	// String currYear = ""
	// + Calendar.getInstance().get(Calendar.YEAR);
	// currYear = currYear.substring(2, 4);
	// int currYr = Integer.parseInt(currYear);
	// String yearPart = "20";
	// if (yr > currYr) {
	// yearPart = "19";
	// }
	// regTypeSrNo = number + "/" + yearPart + year;
	// } else {
	// // included regTypeSrNo for 9 digits
	// if (regTypeSrNo.length() >= 8 && regTypeSrNo.length() <= 10) {
	//
	// String registrationYear = regTypeSrNo.substring(0, 4);
	// String regTypeNumber = regTypeSrNo.substring(4);
	// int registrationTypeSrNo = Integer.parseInt(regTypeNumber);
	// regTypeSrNo = "" + registrationTypeSrNo + "/"
	// + registrationYear;
	// }
	// }
	// } catch (Exception e) {
	// Feedback.showException(null, e);
	// }
	//
	// return regTypeSrNo;
	// }

	// public static String formatCSRTypeSrNo(
	// RegTnCommunityServiceRegistrationDTO objRegistration,
	// String regTypeSrNo) {
	//
	// try {
	// if (objRegistration != null) {
	//
	// int length = regTypeSrNo.length();
	// String displayTypeSrNo = regTypeSrNo.substring(length - 4);
	// if (length > 8) {// for A000 reg_type_srno increase work
	// displayTypeSrNo = regTypeSrNo.substring(length - 5);// 4
	// }
	// String year = regTypeSrNo.substring(2, 4);
	// int yr = Integer.parseInt(year);
	// int number = Integer.parseInt(displayTypeSrNo);
	// // regTypeSrNo = number+"/"+Utility.getYYYYFromDate(new
	// // java.util.Date());
	// // String currYear=""+Calendar.getInstance().get(Calendar.YEAR);
	// // currYear=currYear.substring(2,4);
	// // int currYr=Integer.parseInt(currYear)
	//
	// String currYear = ""
	// + Calendar.getInstance().get(Calendar.YEAR);
	// currYear = currYear.substring(2, 4);
	// int currYr = Integer.parseInt(currYear);
	// String yearPart = "20";
	// if (yr > currYr) {
	// yearPart = "19";
	// }
	// regTypeSrNo = number + "/" + yearPart + year;
	// } else {
	// // included regTypeSrNo for 9 digits
	// if (regTypeSrNo.length() >= 8 && regTypeSrNo.length() <= 10) {
	//
	// String registrationYear = regTypeSrNo.substring(0, 4);
	// String regTypeNumber = regTypeSrNo.substring(4);
	// int registrationTypeSrNo = Integer.parseInt(regTypeNumber);
	// regTypeSrNo = "" + registrationTypeSrNo + "/"
	// + registrationYear;
	// }
	// }
	// } catch (Exception e) {
	// Feedback.showException(null, e);
	// }
	//
	// return regTypeSrNo;
	// }

	// ////////////////////////////////////////////////////////////
	// Abhi Jun 21, 2005, 12:52:03 PM
	// DO NOT DELETE: Code from JDateChooserButton.java

	// /**
	// * Gets the image from the jar file where this class is.
	// *
	// * @param imageFile Image file name with path (from root of the jar file).
	// * (eg "/images/calendar.gif", where /images dir is there
	// * in the jar file root).
	// *
	// * @return Image created else null
	// */
	// public Image getImageFromResource(String imageFile) {
	// Image img = null;
	//
	// InputStream imgStream = getClass().getResourceAsStream(imageFile);
	// Toolkit tk = Toolkit.getDefaultToolkit();
	// try {
	// byte imageBytes[] = new byte[imgStream.available()];
	// imgStream.read(imageBytes);
	// img = tk.createImage(imageBytes);
	// } catch (Exception e) {
	// img = null;
	// }
	//
	// return img;
	// }
	// //////////////////////////////////////////////////
	public static HashMap mvpAbstract(HashMap oldHM, String crimeno, String hoo) {
		// HashMap HM=new HashMap();
		Vector vechoo = hooVector(hoo);
		String temphoo;
		Iterator iter = vechoo.iterator();
		// Vector vec=new Vector();

		while (iter.hasNext()) {
			temphoo = (String) iter.next();
			if (oldHM.containsKey(temphoo)) {
				Set tempset = (Set) oldHM.get(temphoo);
				tempset.add(crimeno);
				oldHM.remove(temphoo);
				oldHM.put(temphoo, tempset);
				tempset = null;
			} else {
				Set vec = new HashSet();
				vec.add(crimeno);
				oldHM.put(temphoo, vec);

			}
		}

		return oldHM;
	}

	public static Vector hooVector(String hoo) {
		Vector strarr = new Vector();
		int j = 0;
		for (int i = 0; i < (hoo.length() / 3); i++) {
			strarr.add(hoo.substring(j, j + 3));
			j = j + 3;
		}

		return strarr;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		// HashMap oldHM=new HashMap();
		//
		// HashMap HM=mvpAbstract(oldHM,"1","001002003004005006");
		// for(Object key : HM.keySet()) {
		// Object value = HM.get(key);
		// System.out.println(key + " = " + value);
		// }
		// System.out.println("==============================");
		// System.out.println("==============================");
		//
		// HashMap HM1=mvpAbstract(HM,"2","001");
		// for(Object key : HM1.keySet()) {
		// Object value = HM1.get(key);
		// System.out.println(key + " = " + value);
		// }
		// System.out.println("==============================");
		// System.out.println("==============================");
		//
		// HashMap HM12=mvpAbstract(HM,"3","011022033044055");
		// for(Object key : HM12.keySet()) {
		// Object value = HM12.get(key);
		// System.out.println(key + " = " + value);
		// }
		// System.out.println("==============================");
		// System.out.println("==============================");
		//
		//
		// System.out.println("===D"+hooArray("001002003004005"));
		// Utility ut = new Utility();
		// java.util.Date dt = new java.util.Date();
		// System.out.println(getDateString(dt));

		// System.out.println(getClientDate("12-01-2005") + ""
		// + validateTextEntered("jkhgaskslkas"));
		// System.out.println("" + ut.getCurrentYear(dt));

		// JComboBox box = new JComboBox();
		// Vector vec = gov.nic.cipa.codes.Language.getAllLanguage();
		//
		// Iterator iter = vec.iterator();
		// while (iter.hasNext()) {
		// String key = (String) iter.next();
		// String val = gov.nic.cipa.codes.Language.getLanguageCodeDesc(key);
		// box.addItem(val);
		// System.out.println(val);
		// }
		//
		// sortCombo(box);
		// int size = box.getModel().getSize();
		// for (int i = 0; i < size; i++) {
		// System.out.println("" + box.getItemAt(i));
		// }

		// System.out.println("--->" + removeKeyFromValue("23424:") + "|");

		// int val = generateYearSrNo("fr_srno", "t312_finalreport");
		// System.out.println(val);
		// int seqNo = 0;
		// // seqNo =
		// Utility.generateYearSrNoFromSequence("zero_fir_no_sequence");
		// // seqNo = Utility.generateSrNoFromTable("reg_type_srno","8");
		// seqNo = Utility.generateYearSrNoFromTable("reg_type_srno","7");
		// System.out.println("seqNo : "+seqNo);
		//

		// ImageIcon img = getImageIcon("resources/images/reports/open.gif");
		// System.out.println("img = " + img);

		// System.out.println(getTimeString(getCurrentDate()));
		// System.out.println(getTimeString_HHmmss(getCurrentDate()));

		getImageIcon("/resources/core/images/mainpic.jpg");
	}

	// //////////////////////////////////////////////////////////////////////
	/**
	 * returns the instance of the specified class using the given argument list
	 * 
	 * NOTE: this function first searches for the class in the current state
	 * module if the class is not found in state module then the class from core
	 * module is instantiated and returned
	 * 
	 * @author Akash
	 */
	// public static Object getInstaceForClass(String strReportClass, List args)
	// throws ClassNotFoundException, Exception {
	// final String DEFAULT_STATE_CODE = "core";
	// String strReportCoreClass = insertStateCode(strReportClass,
	// DEFAULT_STATE_CODE);
	// String strReportStateClass = insertStateCode(strReportClass,
	// ApplicationConfig.getCurrentStateModule());
	//
	// // get class object
	// Class objClass;
	// try {
	// objClass = Class.forName(strReportStateClass);
	//
	// } catch (ClassNotFoundException exception) {
	// objClass = Class.forName(strReportCoreClass);
	// }
	//
	// // Set keyset = args.keySet();
	//
	// // Iterator iter = keyset.iterator();
	// // Object [] arguments = new Object[args.size()];
	// // int i=0;
	// // while(iter.hasNext()){
	// // arguments[i] = args.get(iter.next());
	// // i++;
	// // }
	// Object object = instantiateClass(objClass, args.toArray());// arguments);
	//
	// return object;
	// }

	/**
	 * instantiates the class specified by class object using given args
	 * 
	 * @author Rohit & Jatin
	 */
	public static Object instantiateClass(Class objClass, Object[] argsArr)
			throws Exception {
		Object object = null;
		Class[] arr = new Class[argsArr.length];
		for (int i = 0; i < argsArr.length; i++) {
			arr[i] = argsArr[i].getClass();
		}

		Constructor constructor = objClass.getConstructor(arr);

		object = constructor.newInstance(argsArr);

		return object;
	}

	/**
	 * replaces the state code part of filename with the given stateCode
	 * 
	 * @author Akash
	 */
	private static String insertStateCode(String fileName, String stateCode) {
		StringBuffer strBuffer = new StringBuffer(fileName);

		int startIndex = ("gov.nic.cipa.").length();

		int endIndex = fileName.indexOf('.', startIndex);

		strBuffer.replace(startIndex, endIndex, stateCode);

		return strBuffer.toString();
	}

	/**
	 * This method parses a HTML file and returns text content of the file
	 * stripped of all the tags
	 * 
	 * @author abhi
	 * @param uriOfHtmlFile
	 * @return
	 */
	public static int testbuflength = 64000; // C.P.Singh 17.10.2011

	public static String getTextFromHTML(String uriOfHtmlFile) throws Exception {
		final StringBuffer buf = new StringBuffer(2000);
		final StringBuffer bufAfterRemovingExtraSpaces = new StringBuffer(2000);
		try {
			// Create an HTML document that appends all text to buf
			HTMLDocument doc = new HTMLDocument() {
				public HTMLEditorKit.ParserCallback getReader(int pos) {
					return new HTMLEditorKit.ParserCallback() {
						// This method is whenever text is encountered in the
						// HTML file
						public void handleText(char[] data, int pos) {
							buf.append(data);
							// buf.append('\n');
							buf.append("<br>"); // Added by Amit on April 12,
												// 2007

							// C.P.Singh 17.10.2011
							int buflength = buf.length();
							if (buflength > testbuflength
									&& buflength < testbuflength * 2) {
								buf.append("<p></p>");
								testbuflength = testbuflength + 64000;

							}

						}

						public void handleSimpleTag(Tag t,
								MutableAttributeSet a, int pos) {
							if (t == HTML.Tag.BR) {
								// System.out.println("2 <BR> 2");
								// buf.append('\n');
								// buf.append("<br>");
								buf.append("<p></p>"); // C.P.Singh 17.10.2011

							}

							// TODO Auto-generated method stub
							super.handleSimpleTag(t, a, pos);
						}

						public void handleEndTag(Tag t, int pos) {
							if (t == HTML.Tag.P) {
								// System.out.println("3 </P> 3");
								// buf.append('\n');
								// buf.append("<br>");
								buf.append("<p></p>"); // C.P.Singh 17.10.2011
							}
							super.handleEndTag(t, pos);
						}
					};
				}
			};

			// The Document class does not yet handle charset's properly.
			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);

			// Create a reader on the HTML content

			// Reader rd = getReader(uriOfHtmlFile);
			// Reader rd = new FileReader(uriOfHtmlFile );
			// ///
			FileInputStream inputFile = new FileInputStream(uriOfHtmlFile);
			Reader rd = new InputStreamReader(inputFile, "UTF-8");

			// ///
			// Reader rd = new StringReader(textToClean);

			// Parse the HTML
			EditorKit kit = new HTMLEditorKit();
			kit.read(rd, doc, 0);
		} catch (Exception e) {
			throw e;
		}

		/*
		 * Removing extra spaces and <ENTER> entered in the last of the
		 * document(entered through OpenOffice)
		 * 
		 * First, split the specified string on the basis of '<br>'. Secondly,
		 * replce the "" which are coming in place of '<br>' present within the
		 * documnet, not at the end of the document. Lastly, we add this string
		 * to a StringBuffer object and return it.
		 * 
		 * Eg.: initial state of document ----start of document---- xyz <ENTER>
		 * <ENTER> abc <ENTER> <ENTER> ----end of document---- : after execution
		 * of these lines ----start of document---- xyz <ENTER> <ENTER> abc
		 * ----end of document----
		 * 
		 * @author Manoj
		 */

		String[] strAfterSplit = buf.toString().split("<br>");

		for (int i = 0; i < strAfterSplit.length; i++) {

			if (strAfterSplit[i].equalsIgnoreCase("")) {
				strAfterSplit[i] = "<br>";
			}

			bufAfterRemovingExtraSpaces.append(strAfterSplit[i]);
		}

		/**
		 * @modified by : Fateh Singh @ date : 7-sep-2006 code to remove file
		 *           name from contents when using KWord
		 */
		// String filename = "cipafirdesc.html";
		// if (bufAfterRemovingExtraSpaces.indexOf(filename) == 0){
		// bufAfterRemovingExtraSpaces.delete(0,filename.length());
		// }

		// Return the text
		return bufAfterRemovingExtraSpaces.toString();
	}

	/*
	 * This will used for set the event desc in t3_caseprogress(Prosecution &
	 * Investigation)
	 * 
	 * @ param progressevntDTO
	 * 
	 * @ return Event code:Event Name
	 */
	// public static String getEventNameFromDTO(ProgressEventDTO
	// progressEventDTO) {
	// String progType = "";
	// if (progressEventDTO.getModule() != null
	// && progressEventDTO.getModule().equals(
	// progressEventDTO.MODULE_INVESTIGATION)) {
	// progType = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROGRESS_TYPE,
	// ""
	// + progressEventDTO.getEventCode()
	// + progressEventDTO.getRegistration()
	// .getRegistrationType());
	// } else if (progressEventDTO.getModule() != null
	// && progressEventDTO.getModule().equals(
	// progressEventDTO.MODULE_PROSECUTION)) {
	// progType = ResourceBundleManager.getValue(
	// ResourceBundleManager.PROGRESS_TYPE_PROS_TN, ""
	// + progressEventDTO.getEventCode()
	// + progressEventDTO.getRegistration()
	// .getRegistrationType());
	// }
	// return progType;
	// }

	/*
	 * This will used for geting format ofRegTypeSrNo for OtherCase author
	 * nirmal
	 * 
	 * @ String regTypeSrNo
	 * 
	 * @ return String
	 */

	public static String formatRegTypeSrNoforOtherCase(String regTypeSrNo) {

		int length = regTypeSrNo.length();

		if (length > 4) {
			// for A000 reg_type_srno increase work
			String displayTypeSrNo = regTypeSrNo.substring(length - 5);// 4
			String year = regTypeSrNo.substring(2, 4);
			int yr = Integer.parseInt(year);
			int number = Integer.parseInt(displayTypeSrNo);

			String currYear = "" + Calendar.getInstance().get(Calendar.YEAR);
			currYear = currYear.substring(2, 4);
			int currYr = Integer.parseInt(currYear);
			String yearPart = "20";
			if (yr > currYr) {
				yearPart = "19";
			}
			regTypeSrNo = number + "/" + yearPart + year;
		}
		return regTypeSrNo;

	}

	/**
	 * To show a Progress Bar in UI (CFrame or CDialog)
	 * 
	 * @author Varun
	 * @param String
	 *            progressBarLabel,Object UIClassObject,String
	 *            methodName,Class[] paramArr (parameters recieved by the
	 *            method)
	 * @return void
	 */
	// public static void showProgressBar(final String progressBarLabel,
	// final Object UIClassObject, final String methodName,
	// final Class[] paramArr) {
	//
	// gov.nic.cipa.core.client.reports.util.components.CPrintWaitDialog
	// printBusyDialog = null;
	// if (UIClassObject instanceof JFrame || UIClassObject instanceof CFrame) {
	// printBusyDialog = new
	// gov.nic.cipa.core.client.reports.util.components.CPrintWaitDialog(
	// (JFrame) UIClassObject, true);
	// } else if (UIClassObject instanceof CDialog) {
	// printBusyDialog = new
	// gov.nic.cipa.core.client.reports.util.components.CPrintWaitDialog(
	// (CDialog) UIClassObject, true);
	// }
	// printBusyDialog.setLable(progressBarLabel);
	// try {
	// final gov.nic.cipa.core.client.reports.util.components.CPrintWaitDialog
	// progressBar = printBusyDialog;
	// Thread worker = new Thread() {
	// public void run() {
	// construct();
	// SwingUtilities.invokeLater(new Thread() {
	// public void run() {
	// finished();
	// }
	// });
	// }
	//
	// public Object construct() {
	// Object error = null;
	//
	// try {
	// System.out.println(progressBarLabel);
	// UIClassObject.getClass()
	// .getMethod(methodName, paramArr)
	// .invoke(UIClassObject, (Object[]) paramArr);
	// ;
	// } catch (Exception e) {
	//
	// error = e;// return Exception object if some exception
	// // occurs
	// }
	// return error;// for no exception
	// }
	//
	// public void finished() {
	// progressBar.setVisible(false);
	// }
	// };
	//
	// worker.start();
	//
	// if (worker.isAlive()) {
	// progressBar.setVisible(true);// show busy dialog
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }

	// }

	/**
	 * To change ps code if the code is alpha numeric into numeric code
	 * (A1=100,A2=101,......)
	 * 
	 * @author varun
	 */
	public static String alphaNumericToNumeric(String asciiCode) {
		if (asciiCode != null && !asciiCode.equals("")
				&& asciiCode.length() == 2) {
			char[] psCodeArr = asciiCode.toCharArray();
			if (psCodeArr[0] >= 65 && psCodeArr[0] <= 90) {
				asciiCode = ((psCodeArr[0] - 55) + "") + (psCodeArr[1] + "");
			}
		}
		return asciiCode;
	}

	/**
	 * To change ps code if the code is numeric (3 char) into alpha numeric code
	 * (A1=100,A2=101,......) to be stored in DB
	 * 
	 * @author varun
	 */
	public static String numericToAlphaNumeric(String code) {
		if (code != null && !code.equals("") && code.length() > 2) {
			int asciiIntCode = Integer.parseInt(code.substring(0, 2));
			char asciiCode = (char) (asciiIntCode + 55);
			code = asciiCode + code.substring(2);
		}
		return code;
	}

	public static byte[] getCompressedByteArray(byte[] arr) {

		byte[] input = arr;// "compression string".getBytes();

		// Compressor with highest level of compression
		Deflater compressor = new Deflater();
		compressor.setLevel(Deflater.BEST_COMPRESSION);

		// Give the compressor the data to compress
		compressor.setInput(input);
		compressor.finish();

		// Create an expandable byte array to hold the compressed data.
		// It is not necessary that the compressed data will be smaller than
		// the uncompressed data.
		ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

		// Compress the data
		byte[] buf = new byte[1024];
		while (!compressor.finished()) {
			int count = compressor.deflate(buf);
			bos.write(buf, 0, count);
		}
		try {
			bos.close();
		} catch (IOException e) {
		}

		// Get the compressed data
		byte[] compressedData = bos.toByteArray();
		return compressedData;// bao.toByteArray();

	}

	public static byte[] getDeCompressedByteArray(byte[] arr) {
		byte[] compressedData = arr;
		Inflater decompressor = new Inflater();
		decompressor.setInput(compressedData);

		// Create an expandable byte array to hold the decompressed data
		ByteArrayOutputStream bos = new ByteArrayOutputStream(
				compressedData.length);

		// Decompress the data
		byte[] buf = new byte[1024];
		while (!decompressor.finished()) {
			try {
				int count = decompressor.inflate(buf);
				bos.write(buf, 0, count);
			} catch (DataFormatException e) {
			}
		}
		try {
			bos.close();
			decompressor = null; // made objects null for garbage collection. -
									// sumit
		} catch (IOException e) {
		}

		// Get the decompressed data
		byte[] decompressedData = bos.toByteArray();

		return decompressedData;
	}

	/**
	 * To generate alpha numeric code for numeric code > 10000 (i.e 10000 ->
	 * A000)
	 * 
	 * @author varun
	 * @date 22-12-2009
	 */
	public static String generateNumericToAlphaNumericCode(String numericCode) {
		if (numericCode.length() > 4 && numericCode.charAt(0) != '0') { // for
																		// regTypeSrNo
																		// >
																		// 9999
			String alphaCode = ((char) ((Integer.parseInt(numericCode) / 1000) + 55))
					+ "";
			return alphaCode + numericCode.substring(2);
		} else if (numericCode.length() > 4 && numericCode.charAt(0) == '0') {// for
																				// reg_type_srno
																				// <=9999
			return numericCode.substring(0, 4) + numericCode.substring(5);
		}
		return numericCode;
	}

	/**
	 * To generate numeric code from alpha numeric code (i.e A000 -> 10000)
	 * 
	 * @author varun
	 * @date 22-12-2009
	 */
	public static String generateAlphaNumericToNumericCode(
			String alphaNumericCode) {
		int asciiValCharAtZeroPos = alphaNumericCode.charAt(0);
		if (alphaNumericCode.charAt(0) != '0' && asciiValCharAtZeroPos >= 65) {
			return ((asciiValCharAtZeroPos - 55) + "")
					+ alphaNumericCode.substring(1);
		} else if (alphaNumericCode.charAt(0) == '0') {// for reg_type_srno
														// <=9999
			return alphaNumericCode.substring(0, 4)
					+ alphaNumericCode.substring(5);
		}
		return alphaNumericCode;
	}

	/**
	 * To check for alpha numeric code (i.e check for 'A' in A000)
	 * 
	 * @author varun
	 * @date 22-12-2009
	 */
	public static boolean checkAlphaNumericCode(String alphaNumericCode) {
		int asciiValCharAtZeroPos = alphaNumericCode.charAt(0);
		if (asciiValCharAtZeroPos >= 65) {
			return true;
		}
		return false;
	}

	/**
	 * To get policeStationStaffDTO object after populating it through
	 * PoliceStationStaffSession's retrieve
	 * 
	 * @author Neeraj
	 * @date 03-03-2011
	 */

	// public static PoliceStationStaffDTO getPopulatedPoliceStationStaffDTO(
	// String policeStationStaffCode, String locationID) throws Exception {
	// PoliceStationStaffDTO policeStationStaffDTO = new
	// PoliceStationStaffDTO();
	//
	// policeStationStaffDTO.setPisCode(policeStationStaffCode);
	// policeStationStaffDTO.setLocationID(locationID);
	//
	// PoliceStationStaffSession policeStationStaffSession = new
	// PoliceStationStaffSession();
	// policeStationStaffSession.fetchRelieveDate(policeStationStaffDTO);
	// policeStationStaffSession.retrieve(policeStationStaffDTO);
	//
	// return policeStationStaffDTO;
	// }

	/*
	 * @author Neeraj
	 * 
	 * @date 03-03-2011 return Name of sho Method modified by Gaurav Gupta on
	 * 23/03/2011
	 */

	// public static String getSHOPISNameTN(String location) throws Exception {
	// String pisCode = "";
	// String finalPISName = "";
	// int pisId = 0;
	// String pcode = "";
	// String pis_Rank = "";
	// PoliceStationStaffSession policeStationStaffSession = new
	// PoliceStationStaffSession();
	// Vector policeOfficers = policeStationStaffSession
	// .fetchPoliceOfficer(location);
	//
	// Iterator iterator = policeOfficers.iterator();
	// while (iterator.hasNext()) {
	// String policeOfficer = (String) iterator.next();
	//
	// pisCode = Utility.getKeyPartFromString(policeOfficer);
	//
	// PoliceStationStaffDTO policeStationStaffDTO =
	// getPopulatedPoliceStationStaffDTO(
	// pisCode, location);
	//
	// if (!policeStationStaffDTO.equals(null)) {
	// String role = policeStationStaffDTO.getPisRole();
	// pis_Rank = policeStationStaffDTO.getPisDesignation();
	// if (role != null && !role.equals("") && role.contains("O")) {
	//
	// finalPISName = policeStationStaffDTO.getPisStaffname();
	// pis_Rank = ResourceBundleManager
	// .getValuePartFromValue(ResourceBundleManager
	// .getValue(ResourceBundleManager.RANK,
	// policeStationStaffDTO
	// .getPisDesignation()));
	// // pis_Rank=policeStationStaffDTO.getPisDesignation();
	// pcode = policeStationStaffDTO.getPisCode();
	// pcode += ":" + finalPISName + "(" + pis_Rank + ")";
	// }
	// }
	// }
	// return pcode;
	// }

	/**
	 * To intialize an event form in VIEW MODE and return it in callee method
	 * code
	 * 
	 * @author varun
	 * @date 15-02-2010
	 */
	// public static CDialog showFormForEventView(ProgressEventDTO objEventDB,
	// ProgressEventDrivenDTO objInvestProsDB, JFrame parent)
	// throws Exception {
	//
	// // //////////////////////////
	// // Abhi Jul 18, 2005, 12:18:42 PM
	// // Factory implemented
	// // ProgressEventDTO objSpecEventDB = ProgressEventDTO
	// // .instantiateProgressEventForCode(objEventDB.getEventCode(),
	// // objInvestProsDB);
	// InvestHeaderPanel investHeaderPanel = new InvestHeaderPanel();
	// Vector vecEvents = objInvestProsDB.getAllProgressEvents();
	// ProgressEventDTO lastEventDTO = (ProgressEventDTO) vecEvents
	// .lastElement();
	//
	// ProgressEventFactory eventFactory = ProgressEventFactory.getInstance();
	// ProgressEventDTO objSpecEventDB = (ProgressEventDTO) eventFactory
	// .createEvent(objEventDB.getEventCode(), objInvestProsDB);
	//
	// /*
	// * @author nirmal
	// *
	// * @date : 7-01-08
	// *
	// * @description : if last event is unfreezed, then progress date is
	// * editable (investHeaderPanel.setEditable(true);is editable) else false
	// */
	// ProgressEventSession progEventSession = new ProgressEventSession();
	//
	// objSpecEventDB.setEventSr(objEventDB.getEventSr()); // IMPORTANT: always
	// // set eventSR
	// // before retrieve
	// objSpecEventDB.setLocationID(objEventDB.getLocationID());
	//
	// progEventSession.retrieve(objSpecEventDB);
	// investHeaderPanel.currEvent = objSpecEventDB.getEventSr(); // set
	// // current
	// // event to
	// // investHeaderPanel.currEvent.
	// // by rajesh
	// if (lastEventDTO.getEventCode().equals(objSpecEventDB.getEventCode())
	// && !lastEventDTO.isFreezed()
	// && (lastEventDTO.getEventSr()) == (objSpecEventDB.getEventSr())) {
	//
	// investHeaderPanel.setEditable(true);
	// // investHeaderPanel.validation(lastEventDTO.getEventDate());
	// } else {
	//
	// investHeaderPanel.setEditable(false);
	// }
	//
	// //
	// objSpecEventDB.setEventDate(Utility.getDateFromDateString(investHeaderPanel.jdcProgressDateVal.getDate()));
	// String strEventCode = objSpecEventDB.getEventCode();
	//
	// /*
	// * Before opening Event we have to check for IO, and if current IO is
	// * not the same as the IO at the time of Investigation for this event,
	// * as it is possible after CaseTransferedToOtherIO Event. Then we have
	// * to change the IO accordingly.
	// *
	// * @author Manoj
	// */
	// CaseTransferredSession caseTransferredSession = new
	// CaseTransferredSession();
	// String ioForEvent = caseTransferredSession.fetchIOForEvent(
	// objSpecEventDB, objInvestProsDB);
	// String originalIO = objSpecEventDB.getRegistration()
	// .getInvestigatingOfficer();
	//
	// if (ioForEvent != null && ioForEvent.length() != 0) {
	// objSpecEventDB.getRegistration()
	// .setInvestigatingOfficer(ioForEvent);
	// }
	// /*
	// * @author Fateh Singh
	// *
	// * @date : 7-11-06
	// *
	// * @description : Before opening Event we have to check for Local Head,
	// * and if current Local Head is not the same as the Local Head at the
	// * time of Investigation for this event, as it is possible after
	// * Alteration Memo Event. Then we have to change the Local Head
	// * accordingly.
	// */
	// RegistrationDTO objRegistrationDTO = objSpecEventDB.getRegistration();
	// String localHeadForEvent = null;
	// String originalLocalHead = null;
	//
	// if (objRegistrationDTO instanceof RegFirDTO) {
	// AlterationMemoSession alterationMemoSession = new
	// AlterationMemoSession();
	// localHeadForEvent = alterationMemoSession.fetchLocalHeadForEvent(
	// objSpecEventDB, objInvestProsDB);
	// originalLocalHead = ((RegFirDTO) objRegistrationDTO).getLocalHead();
	//
	// if (localHeadForEvent != null && localHeadForEvent.length() != 0) {
	// ((RegFirDTO) objRegistrationDTO)
	// .setLocalHead(localHeadForEvent);
	// }
	// }
	//
	// /**
	// * setting freezed value for dto to true...forcefully ..so that form
	// * would open in VIEW MODE
	// *
	// * @author varun
	// * @date 15-02-2010
	// */
	// objSpecEventDB.setFreezed(true);
	//
	// CDialog form = null;
	// if (strEventCode.equals(ProgressEventConstants.EC_CRIME_DETAIL)) {
	//
	// form = new InvestCrimeDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CHANCE_FINGERPRINT_SENT_TO_AGENCY)) {
	// form = new InvestChanceFPSentToAgency(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CHANCE_FINGERPRINT_REPORT_RECD)) {
	// form = new InvestChanceFingerPrintReport(objSpecEventDB, parent); //
	// rekha
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_VICTIM_DETAIL_EVENT)) {
	// form = new InvestAccusedVictimsDetails(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_ARREST_SURRENDER_EVENT)) {
	// form = new InvestAccusedArrestSurrender(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_FP_SENT_FOR_MATCH)) {
	// form = new InvestFingerPrintSent(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_FP_RECD_AFTER_MATCH)) {
	// form = new InvestFingerPrintRecv(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_INFOSHEET_SENT_TO_POLICE_STATION))
	// {
	// form = new InvestAccusedInfoSheetSentToPS(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_RELEASED_ON_BAIL)) {
	// form = new InvestAccusedReleOnBail(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_SENT_TO_REMAND_CUSTODY)) {
	// form = new InvestAccusedSentPoliceRemand(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_INFOSHEET_SENT_TO_POLICE_STATION))
	// {
	//
	// form = new InvestAccusedInfoSheetSentToPS(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_SEIZED_RECOVERED)) {
	//
	// form = new InvestPropertiesSeizure(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_DEPOSITED_IN_MALKHANA)) {
	// form = new InvestPropDepositMalkhana(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_RELEASED_FROM_MALKHANA)) {
	// form = new InvestPropReleFromMalkhana(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_SENT_FOR_ANALYSIS_AND_EXPERT_OPINION))
	// {
	// form = new InvestPropSentAnalysisFSL(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_ANALYSIS_REPORT_RECEIVED)) {
	// form = new InvestPropAnalysisReceivFSL(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROP_PRESENTED_IN_COURT)) {
	// form = new InvestPropPresentCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_PROP_MATCHED)) {
	// form = new InvestPropMatchedStolen(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_WITNESS_DETAILS_CAPTURED)) {
	// form = new gov.nic.cipa.core.client.ui.registration.RegWitnessStmt(
	// (WitnessDetailsCapturedDTO) objSpecEventDB, parent);//
	// InvestWitnessStmtRecorded((ProgressEventInvestigationDTO)objSpecEventDB,
	// // parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_FILED_IN_COURT)) {
	// form = new InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_INVESTIGATION_DETAILS_SENT_TO_PP))
	// {
	// form = new InvestFinalReportSentToPP(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_MATCHED)) {
	// form = new InvestMssingPersonMatchedUIDB(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_REPORTED_TO_MPS)) {
	// form = new InvestMissingPersonRepotedMPSPCR(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MLC_DEPOSITEDIN_HOSPITAL)) {
	// form = new InvestMLCDepositHospital(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MLC_INJURED_PERSON_STATEMENT)) {
	// form = new InvestInjuredPersonStmtRecorded(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQBODY_SENT_FOR_PM)) {
	// form = new InvestDeadBodySentPostmortem(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQBODY_HANDEDOVER_AFTER_PM)) {
	// form = new InvestDeadBodyHandedOver(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_PM_REPORT_RECD)) {
	// form = new InvestPostMortemRepoReceive(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_REPORT_SENT_TO_MAGISTRATE)) {
	// form = new InvestInquestRepoSentMagistrate(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_INQ_REPORT_RECD_FROM_MAGISTRATE)) {
	// form = new InvestInquestRepoReceivMagistrate(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ALTERATION_MEMO)) {
	// form = new InvestAlterationMemo(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CASE_TRANSFERRED)) {
	// form = new InvestCaseTransferToOtherIOPSAgency(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// // //////////added code for stopped/closed case, by Rajesh :3-3-2008
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CASE_CLOSED_STOPPED)) {
	// form = new InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_CASE_RE_OPEN)) {
	// form = new InvestCaseClosedReopenUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// // //////////////////////// on date : 3-3-2008
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_GENERATED)) {
	// form = new InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_INVESTIGATION_DETAILS_GENERATED))
	// {
	// form = new InvestFinalReportChargeSheetGenerated(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	//
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_FILED_IN_COURT)) {
	// form = new InvestFinalRepoFiledInCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_MISSING_PERSON_TRACED)) {
	// form = new InvestMissingPersonTraced(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_PROCLAIMED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHMENT_ORDER)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_REWARD)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_INVESTIGATION)) {
	// investHeaderPanel.setEditable(false);
	// form = new InvestAccusedProclaimedOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_RETURNED_BY_COURT)) {
	// form = new InvestFinalRepoReturnedByCourt(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// }
	// // ////////////////////////////PROSECUTION
	// // EVENTS///////////////////////////////
	// else if (strEventCode.equals(ProgressEventConstants.EC_CASE_HEARING)) {
	// form = new ProsHearingInCourtUI((CaseHearingDTO) objSpecEventDB,
	// parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_SUMWAR_EXECUTED)) {
	// form = new ProsSummonWarrantExecutedUI(
	// (SumWarExecutedDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_SUMWAR_ISSUED))
	// {
	// form = new ProsSummonWarrantIssuedUI(
	// (SumWarIssuedDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_ACCUSED_PROCLAIMED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHMENT_ORDER)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_PROPERTY_ATTACHED)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode.equals(ProgressEventConstants.EC_REWARD)
	// && objSpecEventDB.getModule().equals(
	// ProgressEventDTO.MODULE_PROSECUTION)) {
	// form = new ProsAccusedDeclaredOffenderUI(
	// (ProclaimedPropertyRewardDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_COURT_DISPOSAL)) {
	// form = new ProsCourtDisposalUI((CourtDisposalDTO) objSpecEventDB,
	// parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CONVICT_FP_SENT_FOR_RECORD)) { // this
	// // else
	// // condition
	// // is
	// // added
	// // for
	// // pros
	// // FPSent
	// // for
	// // Record
	// form = new ProsFingerPrintSent(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_CONVICT_FP_RECD_AFTER_MATCH)) {
	// form = new InvestFingerPrintReportRecv(objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_FINAL_REPORT_RECE_FROM_PP)) {
	// form = new InvestFinalReportReceivedfromPPUI(
	// (ProgressEventInvestigationDTO) objSpecEventDB, parent);
	// } else if (strEventCode
	// .equals(ProgressEventConstants.EC_TRANSFER_OF_COURT)) {
	// form = new ProsTransferOfCourt((TransferOfCourtDTO) objSpecEventDB,
	// parent);
	// }
	// /**
	// * Check for the status of admin login, He should permit to view all the
	// * corresponding cases of a particular registration type Sr. No. and he
	// * can only enter the event "case transfered to  other IO/PS/Agency"
	// * event code 31100, so disable the ui components
	// *
	// * @author: rekha
	// * @dated: 05-03-2008
	// *
	// */
	//
	// // ///// check SUB-IO/Main-IO code, if code not zero then form open in
	// // view mode, rajesh sumit . dt.30-04-2008
	// String ioCode = "";
	// String loggedInUserPISCode = "";
	// if (Location.getLocation().substring(0, 2).equals("29")) {
	// loggedInUserPISCode = gov.nic.cipa.tn29.client.main.UserLoginScreen
	// .getScreenInstance().getPoliceStationStaff().getPisCode();
	// } else {
	// loggedInUserPISCode = UserLoginScreen.getScreenInstance()
	// .getPoliceStationStaff().getPisCode();
	// }
	// // String loggedInUserPISCode =
	// //
	// UserLoginScreen.getScreenInstance().getPoliceStationStaff().getPisCode();
	// AssociateIOSession objAssociateIOSession = new AssociateIOSession();
	// try {
	// ioCode = objAssociateIOSession.checkAssociateMainIOs(objEventDB
	// .getLocationID(), objEventDB.getRegistration()
	// .getRegistrationSrNo(), loggedInUserPISCode);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// // /////////
	// // if(!(checkForAdmin())){
	// // if(!strEventCode.equals("31100")){
	// // setFormUIDisable(form); //"VIEW ONLY");
	// // }else{
	// // form.setVisible(true);
	// // }
	// // }else if(!checkForDCPandACP()){ //added by rajesh dt:29-08-2008
	// // setFormUIDisable(form);
	// // }else if(Location.getLocation().substring(0,2).equals("08")){ //added
	// // code for delhi dt.17-6-2008.
	// // form.setVisible(true);
	// // }else if(!ioCode.equals("") && !ioCode.substring(0,1).equals("0")){
	// // //added by rajesh dt:30-04-2008 // changed by sumit as subio_code has
	// // been changed to character2 . dt 06-05-2009
	// // setFormUIDisable(form); //,"VIEW ONLY");
	// // }else{
	//
	// form.setVisible(true);
	// // }
	//
	// // After displaying Event we have to refresh the IO with current IO in
	// // RegistrationDTO : manoj
	// objSpecEventDB.getRegistration().setInvestigatingOfficer(originalIO);
	// // After displaying Event we have to refresh the Local Head with current
	// // Local Head in RegFirDTO : fateh singh
	// if (objRegistrationDTO instanceof RegFirDTO
	// && originalLocalHead != null) {
	// ((RegFirDTO) objRegistrationDTO).setLocalHead(originalLocalHead);
	// }
	//
	// return form;
	//
	// }

	public static Vector getVecFromString(String str, String symbol) {

		Vector vecData = new Vector();
		int index = 0;
		String value = "";

		if (str != null && !str.trim().equals("") && symbol != null
				&& !symbol.trim().equals("")) {

			index = str.indexOf(symbol);

			while (index > 0 || str.length() != 0) {
				index = str.indexOf(symbol);
				if (index < 0) {
					value = str.substring(0);
				} else {
					value = str.substring(0, index);
				}
				vecData.add(value);

				if (index > 0)
					str = str.substring(index + 1);
				else if (index == 0) {
					str = str.substring(index + 1);
				} else
					break;
			}
		}
		return vecData;
	}

	// public static int generateSrNoFromLicenceTable(String tableName,
	// String columnName, String holderType) throws Exception {
	// int srNo = 0;
	// try {
	//
	// srNo = fetchMaxColumnValueForLicense(tableName, columnName,
	// holderType);
	// srNo++;
	// } catch (Exception e) {
	// throw new Exception("Error Generating SrNo From Table : "
	// + tableName, e);
	// }
	// return srNo;
	// }

	// public static int fetchMaxColumnValueForLicense(String tableName,
	// String columnName, String holderType) throws Exception {
	// PreparedStatement pstmt = null;
	// Connection con =
	// gov.nic.cipa.core.server.util.CipaTransactionManagerFactory
	// .getInstance().getCipaTransactionManager().getNonTConnection();
	// int srNo = 0;
	// try {
	// String strSQL = "select max(" + columnName + ") from " + tableName
	// + " where holder_type =" + holderType;
	// pstmt = con.prepareStatement(strSQL);
	// LogMgr.getLogger().debug(Utility.class, "" + pstmt);
	// System.out.println("query is:" + pstmt);
	// ResultSet rs = pstmt.executeQuery();
	// if (rs.next()) {
	//
	// String regTypeSrNo = rs.getString(1);
	// if (regTypeSrNo == null) {
	// // regTypeSrNo = rs.getInt(1)+"";
	// regTypeSrNo = (holderType + "0000").toString();
	// }
	// if (regTypeSrNo.length() > 4
	// && Utility.checkAlphaNumericCode(regTypeSrNo
	// .substring(4))) {
	// srNo = Integer
	// .parseInt(regTypeSrNo.substring(0, 4)
	// + Utility
	// .generateAlphaNumericToNumericCode(regTypeSrNo
	// .substring(4)));// "10000");
	// } else {
	// srNo = Integer.parseInt(regTypeSrNo.trim());
	// }
	// // srNo = rs.getInt(1);
	// } else {
	// throw new Exception("Error getting Max value ");
	// }
	// pstmt.close();
	//
	// } catch (Exception e) {
	// throw new Exception("Error getting Max value ", e);
	// } finally {
	// pstmt = null;
	// }
	// return srNo;
	// }

	/**
	 * Validate Time is a given time is not before other one
	 */
	public static boolean validateTimeBeforeTime(Date after, Date before) {
		if (before != null && after != null) {
			try {
				Time afterTime = Utility.getTimeFromTimeString(Utility
						.getTimeString(after));
				Time beforeTime = Utility.getTimeFromTimeString(Utility
						.getTimeString(before));
				if (afterTime.before(beforeTime)) {
					return false;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return true;
	}
}
