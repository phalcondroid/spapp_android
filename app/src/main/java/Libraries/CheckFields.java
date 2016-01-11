package Libraries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by julianmolina on 6/01/16.
 */
public class CheckFields {

    /**
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param field
     * @return
     */
    public static boolean isNotEmpty(String field) {
        if (field.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param pass
     * @return
     */
    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}
