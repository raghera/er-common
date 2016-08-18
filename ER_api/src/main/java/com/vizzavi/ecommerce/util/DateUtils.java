package com.vizzavi.ecommerce.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
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
    public static String EIGHTEEN_MONTHLY = "PECS_RPS_DURATION_EIGHTEEN_MONTHLY";
	public static String TWO_YEARLY = "PECS_RPS_DURATION_TWO_YEARLY";

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
    
    private String initValue = "dd/MM/yyyy";

    public DateUtils(){
    }


    public DateUtils(String initialValue){
        this.initValue = initialValue;
    }

    private final ThreadLocal<DateFormat> dateFormatddMMyyyy = new ThreadLocal<DateFormat> () {

        @Override
        public DateFormat get() {
            return super.get();
        }

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(initValue);
        }

        @Override
        public void remove() {
            super.remove();
        }

    };


    public Date convertStringToDateddMMyyyy(String dateString) throws ParseException {

        return dateFormatddMMyyyy.get().parse(dateString);

    }

    public String convertDateToStringddMMyyyy(Date date){

        return dateFormatddMMyyyy.get().format(date);

    }

    public static Date getMmDdYy(String date) throws ParseException {
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


}
