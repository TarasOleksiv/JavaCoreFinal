package ua.goit.java8.project5.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Taras on 21.09.2017.
 */
public class DateUtils {

    public static String convertDateToString(Date indate){
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }

    public static Date getNewDate(int days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date result = cal.getTime();
        return result;
    }

    public String getRFC3339DateString(int days){
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(getNewDate(days)));
    }
}
