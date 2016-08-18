package com.vodafone.global.er.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.common.Duration;
import com.vodafone.config.ConfigProvider;

/**
 * This util class to calculate the end/expiry date Input parametersa are 1.
 * Calendar and period ( in days or constants ) 2. Just a period ( in days or
 * constants )
 * 
 * @author Arnaud Vignon
 * @version $Revision: 1.5 $
 */
public class DateTimeUtil {

	// CR1231
	// private static final LWLogger logger =
	// LWSupportFactoryImpl.getInstance().getLogger(
	// DateTimeUtil.class);
	private static Logger logger = Logger.getLogger(DateTimeUtil.class);

	static String EXPIRY_TIME = "";
	static int HOUR = 23;
	static int MINUTE = 59;
	static int SECOND = 59;


	   private static SimpleDateFormat sDateInYear = new SimpleDateFormat("dd MMM yyyy");


	    private static SimpleDateFormat sYear = new SimpleDateFormat("yyyy");


	    private static SimpleDateFormat sMonth = new SimpleDateFormat("MM");


	    private static SimpleDateFormat sDay = new SimpleDateFormat("dd");


		private static SimpleDateFormat dayOfWeek = new SimpleDateFormat("EEEEE");


		private static SimpleDateFormat monthOfYear = new SimpleDateFormat("MMMMM");


	    public static String ONEDAY = "PECS_RPS_DURATION_ONEDAY";
	    public static String DAILY = "PECS_RPS_DURATION_DAILY";
	    public static String WEEKLY = "PECS_RPS_DURATION_WEEKLY";
	    public static String FORTNIGHTLY = "PECS_RPS_DURATION_FORTNIGHTLY";
	    public static String MONTHLY = "PECS_RPS_DURATION_MONTHLY";
	    public static String QUARTERLY = "PECS_RPS_DURATION_QUARTERLY";
	    public static String HALFYEARLY = "PECS_RPS_DURATION_HALFYEARLY";
	    public static String ANNUALY = "PECS_RPS_DURATION_ANNUALY";
	//Added for ER3
	    public static String TWODAYS = "PECS_RPS_DURATION_TWODAYS";
	    public static String THREEDAYS = "PECS_RPS_DURATION_THREEDAYS";
	    public static String FOURDAYS = "PECS_RPS_DURATION_FOURDAYS";
	    public static String FIVEDAYS = "PECS_RPS_DURATION_FIVEDAYS";
	    public static String SIXDAYS = "PECS_RPS_DURATION_SIXDAYS";
	    public static String SEVENDAYS = "PECS_RPS_DURATION_SEVENDAYS";
	    public static String EIGHTDAYS = "PECS_RPS_DURATION_EIGHTDAYS";
	    public static String NINEDAYS = "PECS_RPS_DURATION_NINEDAYS";
	    public static String TENDAYS = "PECS_RPS_DURATION_TENDAYS";
	    public static String ELEVENDAYS = "PECS_RPS_DURATION_ELEVENDAYS";
	    public static String TWELVEDAYS = "PECS_RPS_DURATION_TWELVEDAYS";
	    public static String THIRTEENDAYS = "PECS_RPS_DURATION_THIRTEENDAYS";
	    public static String FOURTEENDAYS = "PECS_RPS_DURATION_FOURTEENDAYS";
	    public static String SIXTEENDAYS = "PECS_RPS_DURATION_SIXTEENDAYS";
	    public static String EIGHTEENDAYS = "PECS_RPS_DURATION_EIGHTEENDAYS";
	    public static String TWENTYDAYS = "PECS_RPS_DURATION_TWENTYDAYS";
	    public static String TWENTYTWODAYS = "PECS_RPS_DURATION_TWENTYTWODAYS";
	    public static String TWENTYFOURDAYS = "PECS_RPS_DURATION_TWENTYFOURDAYS";
	    public static String TWENTYSIXDAYS = "PECS_RPS_DURATION_TWENTYSIXDAYS";
	    public static String TWENTYEIGHTDAYS = "PECS_RPS_DURATION_TWENTYEIGHTDAYS";
	    public static String THIRTYDAYS = "PECS_RPS_DURATION_THIRTYDAYS";
	    public static String THIRTYFIVEDAYS = "PECS_RPS_DURATION_THIRTYFIVEDAYS";
	    public static String FOURTYDAYS = "PECS_RPS_DURATION_FOURTYDAYS";
	    public static String FOURTYFIVEDAYS = "PECS_RPS_DURATION_FOURTYFIVEDAYS";

	    private static SimpleDateFormat sDateInTime = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	    private static SimpleDateFormat sDateInTimeToMin = new SimpleDateFormat("dd MMM yyyy HH:mm");
	    
	    //CR 2245 - added to calculate nuber of whole days between dates
	    private static int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
	    
	static {
		EXPIRY_TIME = ConfigProvider.getProperty("trnsctrl.util.DateTime.expiryTime", "");

		StringTokenizer stoken = new StringTokenizer(EXPIRY_TIME, ".");

		if (stoken.hasMoreTokens() && stoken.countTokens() == 3) {
			try {
				HOUR = Integer.parseInt(stoken.nextToken());
				MINUTE = Integer.parseInt(stoken.nextToken());
				SECOND = Integer.parseInt(stoken.nextToken());
				if ((HOUR < 0) || (HOUR > 23) || (MINUTE < 0) || (MINUTE > 59)
						|| (SECOND < 0) || (SECOND > 59))
					throw new Exception(
							"property trnsctrl.util.DateTime.expiryTime not validated: "
									+ EXPIRY_TIME);

			} catch (Exception e) {
				logger.debug("property trnsctrl.util.DateTime.expiryTime not validated: "
						+ EXPIRY_TIME);
				EXPIRY_TIME = "";
			}
		} else {
			// HOUR = 23;
			// MINUTE = 59;
			// SECOND = 59;
			HOUR = todaysDate().get(Calendar.HOUR_OF_DAY);
			MINUTE = todaysDate().get(Calendar.MINUTE);
			SECOND = todaysDate().get(Calendar.SECOND);
		}
	}

	/**
 * 
 *
 */
	public DateTimeUtil() {

	}



	

	/**
	 * Thi return today's date
	 * 
	 * @return
	 */
	public static Calendar todaysDate() {
		TimeZone tz = TimeZone.getDefault();
		Calendar gdate = new GregorianCalendar(tz);
		Date time = new Date(); // current date/time - now
		gdate.setTime(time);
		return gdate;
	}

	/**
	 * This calculates the expire date given a start date and a duration
	 * 
	 * @param startDate
	 * @param period
	 * @return
	 */
	public static Calendar calculatePeriod(Calendar startDate, int period,
			boolean midnight) {
		if (midnight) {
			EXPIRY_TIME = "23:59:59";
			HOUR = 23;
			MINUTE = 59;
			SECOND = 59;
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate.getTime());
		Duration objDuration = new Duration(period);
		return calculate(calendar, objDuration);
	}

	/**
	 * This calculates the expire date given a start date and a duration
	 * 
	 * @param startDate
	 * @param period
	 * @return
	 */
	public static Calendar calculatePeriod(Calendar startDate, int period) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate.getTime());
		Duration objDuration = new Duration(period);
		return calculate(calendar, objDuration);
	}

	/**
	 * This calculates the expire date given a duration
	 * 
	 * @param period
	 * @return
	 */
	public static Calendar calculatePeriod(int period) {
		TimeZone tz = TimeZone.getDefault();

		Calendar gdate = new GregorianCalendar(tz);
		Duration objDuration = new Duration(period);
		logger.debug(objDuration.toString());
		Date time = new Date(); // current date/time - now
		gdate.setTime(time);
		return calculate(gdate, objDuration);
	}

	/**
	 * This is internaly used to calculate the expire date
	 * 
	 * @param gdate
	 * @param period
	 * @return
	 */
	private static Calendar calculate(Calendar gdate, Duration objDuration) {
		if (objDuration.isDayUnit()) {
			g_date_add_days(gdate, objDuration.getValue());
		}

		if (objDuration.isMinuteUnit()) {
			g_date_add_minutes(gdate, objDuration.getValue());
		}

		if (objDuration.isMonthUnit()) {
			g_date_add_months(gdate, objDuration.getValue());
		}

		if (objDuration.isYearUnit()) {
			g_date_add_years(gdate, objDuration.getValue());
		}

		if (objDuration.isDaily()) {
			gdate.set(Calendar.AM_PM, Calendar.PM);
			gdate.set(Calendar.HOUR, 11);
			gdate.set(Calendar.MINUTE, 59);
			gdate.set(Calendar.SECOND, 59);
		} else if ((!objDuration.isMinuteUnit()) && !EXPIRY_TIME.equals("")) {
			int tmpHOUR = HOUR;
			if (HOUR > 12) {
				tmpHOUR = HOUR - 12;
				gdate.set(Calendar.AM_PM, Calendar.PM);
			} else
				gdate.set(Calendar.AM_PM, Calendar.AM);

			gdate.set(Calendar.HOUR, tmpHOUR);
			gdate.set(Calendar.MINUTE, MINUTE);
			gdate.set(Calendar.SECOND, SECOND);

		}

		return gdate;
	}

	/**
	 * Adds the number of days
	 * 
	 */
	private static void g_date_add_days(Calendar gdate, int numberofDays) {
		gdate.add(Calendar.DATE, numberofDays);
	}

	/**
	 * Adds the number of minutes
	 * 
	 */
	private static void g_date_add_minutes(Calendar gdate, int numberofMinutes) {
		gdate.set(Calendar.MINUTE, gdate.get(Calendar.MINUTE) + numberofMinutes);
	}

	/**
	 * Adds the number of months
	 * 
	 */
	private static void g_date_add_months(Calendar gdate, int numberofMonths) {
		gdate.set(Calendar.MONTH, gdate.get(Calendar.MONTH) + numberofMonths);

	}

	/**
	 * Adds the number of years
	 * 
	 */
	private static void g_date_add_years(Calendar gdate, int numberofYears) {
		gdate.set(Calendar.YEAR, gdate.get(Calendar.YEAR) + numberofYears);

	}



	/**
	 * moved from BatchTask (where it never should really have been anyway)
	 * 
	 * @param data
	 * @return
	 */
	public static String getTimeString(Date data) {
		String rv = null;
		try {
			SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
			rv = form.format(data);

		} catch (Exception e) {
			logger.error("Invalid date " + data + ":" + e.getMessage());
		}

		return rv;
	}

	/**
	 * moved from BatchTask (where it never should really have been anyway)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDateString(Date data) {
		String rv = null;
		try {
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
			rv = form.format(data);
		} catch (Exception e) {
			logger.error("Invalid date " + data + ":" + e.getMessage());
		}

		return rv;
	}

	/**
	 * moved from BatchTask (where it never should really have been anyway)
	 * 
	 * @param data
	 * @return
	 */
	public static Date getTimeValue(String data)
	{
	    Date rv = null;
	    try {
	        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
	        rv = form.parse(data);
	
	    } catch (Exception e) {
	        logger.error("Invalid time string " + data + ":" + e.getMessage());
	    }
	
	    return rv;
	}

	/**
	 * moved from BatchTask (where it never should really have been anyway)
	 * 
	 * @param data
	 * @return
	 */
	public static Date getDateValue(String data)
	{
	    Date rv = null;
	    try {
	        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
	        rv = form.parse(data);
	    } catch (Exception e) {
	        logger.error("Invalid date string " + data + ":" + e.getMessage());
	    }
	
	    return rv;
	}

	  public static Date getMmDdYy(String date) throws ParseException  {
	        return sDateInYear.parse(date);
	    }


	    public static String convertDateToDdMmYy(Date dat) {
	        return sDateInYear.format(dat);
	    }


	    public static String getYear(Date dat) {
	        return sYear.format(dat);
	    }


	    public static String getMonthOfYear(Date dat) {
	        return monthOfYear.format(dat);
	    }


	    public static String getDay(Date dat) {
	        return sDay.format(dat);
	    }


		public static String getDayOfWeek(Date dat) {
		    return dayOfWeek.format(dat);
		}


	    public static String convertDateToMmDdYyHhMmSs(Date date) {
	        return sDateInTime.format(date);
	    }

	    public static String convertDateToMmDdYyHhMm(Date date) {
	        return sDateInTimeToMin.format(date);
	    }


	    public static Date getRenewDate(Date sDate, String duration) {

	        Calendar calDate = Calendar.getInstance();
	        sDate = new Date(sDate.getTime() + calDate.get(Calendar.DST_OFFSET));
	        calDate.setTime(sDate);
	        if (duration.equals(WEEKLY)) {
	            calDate.add(Calendar.DATE, 7);
	        }
	        else if (duration.equals(FORTNIGHTLY)) {
	            calDate.add(Calendar.DATE, 14);
	        }
	        else if (duration.equals(MONTHLY)) {
	            calDate.add(Calendar.MONTH, 1);
	            //calDate.add(Calendar.DATE, 1);
	        }
	        else if (duration.equals(QUARTERLY)) {
	            calDate.add(Calendar.MONTH, 3);
	            //calDate.add(Calendar.DATE, 1);
	        }
	        else if (duration.equals(HALFYEARLY)) {
	            calDate.add(Calendar.MONTH, 6);
	            //calDate.add(Calendar.DATE, 1);
	        }
	        else if (duration.equals(ANNUALY)) {
	            calDate.add(Calendar.YEAR, 1);
	            //calDate.add(Calendar.DATE, 1);
	        }
	        else if (duration.equals(ONEDAY)) {
	            calDate.add(Calendar.DATE, 1);
	        }
	        else if (duration.equals(DAILY)) {
	            calDate.add(Calendar.DATE, 0);
	        }
	//Added for ER3
	        else if (duration.equals(TWODAYS)) {
	            calDate.add(Calendar.DATE, 2);
	        }
	        else if (duration.equals(THREEDAYS)) {
	            calDate.add(Calendar.DATE, 3);
	        }
	        else if (duration.equals(FOURDAYS)) {
	            calDate.add(Calendar.DATE, 4);
	        }
	        else if (duration.equals(FIVEDAYS)) {
	            calDate.add(Calendar.DATE, 5);
	        }
	        else if (duration.equals(SIXDAYS)) {
	            calDate.add(Calendar.DATE, 6);
	        }
	        else if (duration.equals(SEVENDAYS)) {
	            calDate.add(Calendar.DATE, 7);
	        }
	        else if (duration.equals(EIGHTDAYS)) {
	            calDate.add(Calendar.DATE, 8);
	        }
	        else if (duration.equals(NINEDAYS)) {
	            calDate.add(Calendar.DATE, 9);
	        }
	        else if (duration.equals(TENDAYS)) {
	            calDate.add(Calendar.DATE, 10);
	        }
	        else if (duration.equals(ELEVENDAYS)){
	            calDate.add(Calendar.DATE, 11);
	        }
	        else if (duration.equals(TWELVEDAYS)) {
	            calDate.add(Calendar.DATE, 12);
	        }
	        else if (duration.equals(THIRTEENDAYS)) {
	            calDate.add(Calendar.DATE, 13);
	        }
	        else if (duration.equals(FOURTEENDAYS)) {
	            calDate.add(Calendar.DATE, 14);
	        }
	        else if (duration.equals(SIXTEENDAYS)) {
	            calDate.add(Calendar.DATE, 16);
	        }
	        else if (duration.equals(EIGHTEENDAYS)) {
	            calDate.add(Calendar.DATE, 18);
	        }
	        else if (duration.equals(TWENTYDAYS)) {
	            calDate.add(Calendar.DATE, 20);
	        }
	        else if (duration.equals(TWENTYTWODAYS)) {
	            calDate.add(Calendar.DATE, 22);
	        }
	        else if (duration.equals(TWENTYFOURDAYS)) {
	            calDate.add(Calendar.DATE, 24);
	        }
	        else if (duration.equals(TWENTYSIXDAYS)) {
	            calDate.add(Calendar.DATE, 26);
	        }
	        else if (duration.equals(TWENTYEIGHTDAYS)) {
	            calDate.add(Calendar.DATE, 28);
	        }
	        else if (duration.equals(THIRTYDAYS)) {
	            calDate.add(Calendar.DATE, 30);
	        }
	        else if (duration.equals(THIRTYFIVEDAYS)) {
	            calDate.add(Calendar.DATE, 35);
	        }
	        else if (duration.equals(FOURTYDAYS)) {
	            calDate.add(Calendar.DATE, 40);
	        }
	        else if (duration.equals(FOURTYFIVEDAYS)) {
	            calDate.add(Calendar.DATE, 45);
	        }

	        return calDate.getTime();

	    }


	    public static Date getRenewDate(String duration) {

	        Date tDate = new Date();

			return getRenewDate(tDate, duration);

	    }


	    public static Date getRenewDate(Date sDate, int duration) {

			if (duration == 2) {
				return getRenewDate(sDate, WEEKLY);
			}
			else if (duration == 3) {
				return getRenewDate(sDate, FORTNIGHTLY);
			}
			else if (duration == 4) {
				return getRenewDate(sDate, MONTHLY);
			}
			else if (duration == 5) {
				return getRenewDate(sDate, QUARTERLY);
			}
			else if (duration == 6) {
				return getRenewDate(sDate, HALFYEARLY);
			}
			else if (duration == 7) {
				return getRenewDate(sDate, ANNUALY);
			}
	        else if (duration == 8) {
				return getRenewDate(sDate, ONEDAY);
	        }
	        else if (duration == 9) {
				return getRenewDate(sDate, DAILY);
	        }
		//Added for ER3

	                else if (duration == 1002) {
	                        return getRenewDate(sDate, TWODAYS);
	                }
	                else if (duration == 1003) {
	                        return getRenewDate(sDate, THREEDAYS);
	                }
	                else if (duration == 1004) {
	                        return getRenewDate(sDate, FOURDAYS);
	                }
	                else if (duration == 1005) {
	                        return getRenewDate(sDate, FIVEDAYS);
	                }
	                else if (duration == 1006) {
	                        return getRenewDate(sDate, SIXDAYS);
	                }
	                else if (duration == 1007) {
	                        return getRenewDate(sDate, SEVENDAYS);
	                }
	                else if (duration == 1008) {
	                        return getRenewDate(sDate, EIGHTDAYS);
	                }
	                else if (duration == 1009) {
	                        return getRenewDate(sDate, NINEDAYS);
	                }
	                else if (duration == 1010) {
	                        return getRenewDate(sDate, TENDAYS);
	                }
	                else if (duration == 1011) {
	                        return getRenewDate(sDate, ELEVENDAYS);
	                }
	                else if (duration == 1012) {
	                        return getRenewDate(sDate, TWELVEDAYS);
	                }
	                else if (duration == 1013) {
	                        return getRenewDate(sDate, THIRTEENDAYS);
	                }
	                else if (duration == 1014) {
	                        return getRenewDate(sDate, FOURTEENDAYS);
	                }
	                else if (duration == 1016) {
	                        return getRenewDate(sDate, SIXTEENDAYS);
	                }
	                else if (duration == 1018) {
	                        return getRenewDate(sDate, EIGHTEENDAYS);
	                }
	                else if (duration == 1020) {
	                        return getRenewDate(sDate, TWENTYDAYS);
	                }
	                else if (duration == 1022 ) {
	                        return getRenewDate(sDate, TWENTYTWODAYS);
	                }
	                else if (duration == 1024) {
	                        return getRenewDate(sDate, TWENTYFOURDAYS);
	                }
	                else if (duration == 1026) {
	                        return getRenewDate(sDate, TWENTYSIXDAYS);
	                }
	                else if (duration == 1028) {
	                        return getRenewDate(sDate, TWENTYEIGHTDAYS);
	                }
	                else if (duration == 1030) {
	                        return getRenewDate(sDate, THIRTYDAYS);
	                }
	                else if (duration == 1035) {
	                        return getRenewDate(sDate, THIRTYFIVEDAYS);
	                }
	                else if (duration == 1040) {
	                        return getRenewDate(sDate, FOURTYDAYS);
	                }
	                else if (duration == 1045) {
	                        return getRenewDate(sDate, FOURTYFIVEDAYS);
	                }

	                else {
				return new Date();
			}

	    }


	    public static Date getRenewDate(int duration) {

	        Date tDate = new Date();

			return getRenewDate(tDate, duration);

	    }
	
	    /**
	     * CR2245 - calculate the number of days between 2 dates
	     * @param start
	     * @param end
	     * @return
	     */
	    public static int countDaysBetween(Date start, Date end) {
	    	
//	    	if (end.before(start)) {
//	    		throw new IllegalArgumentException("The end date must be later than the start date");
//	    	}

	    	//reset all hours mins and secs to zero on start date
	    	Calendar startCal = GregorianCalendar.getInstance();
	    	startCal.setTime(start);
	    	startCal.set(Calendar.HOUR_OF_DAY, 0);
	    	startCal.set(Calendar.MINUTE, 0);
	    	startCal.set(Calendar.SECOND, 0);
	    	startCal.set(Calendar.MILLISECOND, 0);
	    	long startTime = startCal.getTimeInMillis() + startCal.get(Calendar.DST_OFFSET);
	    	//reset all hours mins and secs to zero on end date
	    	Calendar endCal = GregorianCalendar.getInstance();
	    	endCal.setTime(end);
	    	endCal.set(Calendar.HOUR_OF_DAY, 0);
	    	endCal.set(Calendar.MINUTE, 0);
	    	endCal.set(Calendar.SECOND, 0);
	    	endCal.set(Calendar.MILLISECOND, 0);
	    	long endTime = endCal.getTimeInMillis() + endCal.get(Calendar.DST_OFFSET);
	    	 
	    	float tmp = ((float)(endTime-startTime))/MILLISECONDS_IN_DAY;
	    	int result = Math.round(tmp);
	    	return result;
	    	
	    }

		public static Calendar convertDateToCal(Date date) {
			Calendar rv = Calendar.getInstance();
			rv.setTime(date);
			return rv;
		}
	
}
