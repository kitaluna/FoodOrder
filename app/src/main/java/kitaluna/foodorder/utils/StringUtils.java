package kitaluna.foodorder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Van Hien on 1/2/2018.
 */

public class StringUtils {

        public static boolean checkEmail(String email) {
            if (!email.contains("@") || !email.contains(".com")){
                return false;
            } else {
                String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                Pattern regex = Pattern.compile(emailPattern);
                Matcher matcher = regex.matcher(email);
                if (matcher.find()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

}
