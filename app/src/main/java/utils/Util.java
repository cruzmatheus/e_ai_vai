package utils;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by matheus on 07/01/16.
 */
public class Util {

    public static Date parseStringToDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date formattedDate = null;

        try {
            formattedDate = formatter.parse(date);
        } catch (ParseException e) {
            Logger.d("%s",e.getMessage());
        }

        return formattedDate;
    }

    public static String parseDateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = null;

        try {
            formattedDate = formatter.format(date);
        } catch (Exception e) {
            Logger.d("%s",e.getMessage());
        }

        return formattedDate;
    }

    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }
}
