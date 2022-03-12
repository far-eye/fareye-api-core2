package utils.common;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateTime {
    private DateTime() {
    }

    public static String getCurrentDateTimeInUTC(String dateTimeFormat) {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }


    public static String convertDatetimeFormat(String date, String currentDateFormat, String expectedDateFormat) {
        try {
            Date newDate = new SimpleDateFormat(currentDateFormat).parse(date);
            return new SimpleDateFormat(expectedDateFormat).format(newDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static String convertDatetimeFormatWithTimeZone(String inputDate, String currentDateFormat, String expectedDateFormat, String expectedTimeZone) {
        try {
            Date date = new SimpleDateFormat(currentDateFormat).parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat(expectedDateFormat);
            sdf.setTimeZone(TimeZone.getTimeZone(expectedTimeZone));
            return sdf.format(date);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static String getDateTimeInUTCwithHoursDiff(String dateTimeFormat, int hoursDiff) {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(dateTimeFormat));
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        cal.add(Calendar.HOUR, hoursDiff);
        String newDateTime = sdf.format(cal.getTime());
        log.info("New date/time:" + newDateTime);
        return newDateTime;
    }

    public static String getDateTimeWithHoursDiff(String date, String dateTimeFormat, int hoursDiff) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        cal.add(Calendar.HOUR, hoursDiff);
        String newDateTime = sdf.format(cal.getTime());
        log.info("New date and time: " + newDateTime);
        return newDateTime;
    }


    public static String getDateTimeInUTCWithDaysDiff(String dateTimeFormat, int daysDiff) {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(dateTimeFormat));
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        cal.add(Calendar.DAY_OF_MONTH, daysDiff);
        String newDateTime = sdf.format(cal.getTime());
        log.info("New date n time: " + newDateTime);
        return newDateTime;
    }


    public static String getDateTimeInUtcWithMonthDiff(String dateTimeFormat, int monthDiff) {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(dateTimeFormat));
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        cal.add(Calendar.MONTH, monthDiff);
        String newDateTime = sdf.format(cal.getTime());
        log.info("New date/time: " + newDateTime);
        return newDateTime;
    }

    public static String getCurrentDateTime(String dateTimeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }


    public static String getCurrentDateTime(String dateTimeFormat,String expectedTimeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
        Date date = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(expectedTimeZone));
        return simpleDateFormat.format(date);
    }





    public static String getExecutionTime(long timeInSec) {
        String totalExecTime;
        DecimalFormat df2 = new DecimalFormat("#.##");
        if (timeInSec >= 3600)
            totalExecTime = df2.format(timeInSec / 3600.0) + " Hrs";
        else if (timeInSec >= 60)
            totalExecTime = df2.format(timeInSec / 60.0) + " Min";
        else
            totalExecTime = df2.format(timeInSec) + " Sec";
        log.info(totalExecTime);
        return totalExecTime;
    }


}

