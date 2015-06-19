package cc.ddhub.atbj.Util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by denzelw on 15/6/14.
 */
public class DateUtil {

    public static boolean isSameDate(long time1, long time2){
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(time1);

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(time2);

        int y = c1.get(Calendar.YEAR);
        int d = c1.get(Calendar.DAY_OF_YEAR);

        return y == c2.get(Calendar.YEAR) && d == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static long getTime(String time, String pattern){
        if (TextUtils.isEmpty(time)){
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
