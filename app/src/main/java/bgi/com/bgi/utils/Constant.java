package bgi.com.bgi.utils;

/**
 * Created by Vpsingh on 3/10/2017.
 */

public class Constant {
    public class PreferenceManager {
        public static final String PREF_NAME = "appname";

    }

    public class ResultCodes {
        public static final int AUTHORIZE_ACTIVITY_RESULT_CODE = 0;
        public static final int PICK_EXISTING_PHOTO_RESULT_CODE = 1;
        public static final int WEBVIEW_REQUEST_CODE = 100;

    }

    public class AppUrl {
        public static final String BASE_URL = "http://192.168.43.150/";
        public static final String LOGIN = "http://www.google.com";
        public static final String DASHBOARD_URL = "https://drive.google.com/";
        //public static final String DASHBOARD_URL = "https://www.dropbox.com/";
    }

    public class ApiParams {
        //register
        public static final String MOBILE = "mobile";
        public static final String PASSWORD = "password";
    }

    public class ResponseCodes {
        //Login
        public static final String SUCCESS = "MA200";
        public static final String FAILED = "MA400";
    }
}
