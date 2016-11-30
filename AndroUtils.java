import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Created by Palash on 29/11/16.
 */
public class AndroUtils {
    private static final String TAG = "AndroUtils";
    private static Context context;

    private AndroUtils(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        new AndroUtils(context);
    }

    /**
     * Checks whether the object is null or not.
     *
     * @param object - object to be verified.
     * @return - boolean
     */
    public static boolean isNull(Object object) {
        boolean isValid = false;
        try {
            if (object == null) {
                isValid = true;
            } else {
                if (object.equals("")) {
                    isValid = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in LibWeb.isNull:" + e.getMessage());
        }
        return isValid;
    }


    /**
     * Checks whether the String is null or not.
     *
     * @param object - String to be verified.
     * @return - boolean
     */
    public static boolean isNullString(String object) {
        boolean isValid = false;
        try {
            if (object == null) {
                isValid = true;
            } else {
                if (object.trim().isEmpty()) {
                    isValid = true;
                }

                if (object.trim().equalsIgnoreCase("null")) {
                    isValid = true;
                }

            }
        } catch (Exception e) {
            System.err
                    .println("Error in LibWeb.isNullString:" + e.getMessage());
        }
        return isValid;
    }

    /**
     * Utilities For Shared Preferences
     */
    public static class Prefs {

        private static SharedPreferences sharedPreferences;
        private static SharedPreferences.Editor editor;

        /**
         * Checks the instance of Context and SharedPreference is not null.
         *
         * @return boolean
         */
        private static boolean checkSharedPrefsInstance() {
            boolean isContext = true;
            if (!isNull(context)) {
                if (isNull(sharedPreferences)) {
                    sharedPreferences = context.getSharedPreferences("", 1);
                    editor = sharedPreferences.edit();
                }
                isContext = true;
            } else {
                isContext = false;
                Log.e(TAG, "Context not found. Check whether you have initialized the instance in your" +
                        " Application class.\n If not then use " +
                        "AndroUtils.init(getApplicationContext()) in your Application class.");
            }
            return isContext;
        }

        /**
         * Insert or Update a String value in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - String value to be inserted or updated in shared Preference.
         */
        public static void putString(String key, String value) {
            if (checkSharedPrefsInstance()) {
                editor.putString(key, value);
                editor.commit();
            }

        }

        /**
         * Insert or Update a int value in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - int value to be inserted or updated in shared Preference.
         */
        public static void putInt(String key, int value) {
            if (checkSharedPrefsInstance()) {
                editor.putInt(key, value);
                editor.commit();
            }
        }

        /**
         * Insert or Update a boolean value in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - boolean value to be inserted or updated in shared Preference.
         */
        public static void putBoolean(String key, boolean value) {
            if (checkSharedPrefsInstance()) {
                editor.putBoolean(key, value);
                editor.commit();
            }
        }

        /**
         * Insert or Update a float value in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - float value to be inserted or updated in shared Preference.
         */
        public static void putFloat(String key, float value) {
            if (checkSharedPrefsInstance()) {
                editor.putFloat(key, value);
                editor.commit();
            }
        }

        /**
         * Insert or Update a long value in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - long value to be inserted or updated in shared Preference.
         */
        public static void putLong(String key, long value) {
            if (checkSharedPrefsInstance()) {
                editor.putLong(key, value);
                editor.commit();
            }
        }

        /**
         * Insert or Update a Set of Strings  in shared Preference
         * (Available in SDK version higher than HONEYCOMB).
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - long value to be inserted or updated in shared Preference.
         */
        public static void putStringSet(String key, Set<String> value) {
            if (checkSharedPrefsInstance()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    editor.putStringSet(key, value);
                    editor.commit();
                } else {
                    Log.e(TAG, "Adding A Set<String> in SharedPreferences is available in SDK version higher than HONEYCOMB");
                }

            }
        }


        /**
         * Insert or Update a List in shared Preference.
         *
         * @param key   - key for the value to be inserted or updated in shared Preference.
         * @param value - List<E> to be inserted or updated in shared Preference.
         */
        public static void putList(String key, List<?> value) {
            if (checkSharedPrefsInstance()) {
                Type type = new TypeToken<List<?>>() {
                }.getType();
                String lstStr = new Gson().toJson(value, type);
                editor.putString(key, lstStr);
                editor.commit();
            }
        }

        /**
         * Retrieve the List from SharedPreferences mapped with the key.
         *
         * @param key - key for the List to be retrieved from shared Preference.
         * @return - List of elements or null.
         */
        public static List<?> getList(String key) {
            List<?> list = null;
            if (checkSharedPrefsInstance()) {
                String lstString = sharedPreferences.getString(key, null);
                if (!isNullString(lstString)) {
                    Type type = new TypeToken<List<?>>() {
                    }.getType();
                    list = new Gson().fromJson(lstString, type);
                }
            }
            return list;
        }

        /**
         * Retrieve the String from SharedPreferences mapped with the key.
         *
         * @param key - key for the String to be retrieved from shared Preference.
         * @return - String or null.
         */
        public static String getString(String key) {
            String value = null;
            if (checkSharedPrefsInstance()) {
                value = sharedPreferences.getString(key, null);
            }
            return value;
        }

        /**
         * Retrieve the int from SharedPreferences mapped with the key.
         *
         * @param key - key for the int to be retrieved from shared Preference.
         * @return - int value or 0.
         */
        public static int getInt(String key) {
            int value = 0;
            if (checkSharedPrefsInstance()) {
                value = sharedPreferences.getInt(key, 0);
            }
            return value;
        }

        /**
         * Retrieve the float from SharedPreferences mapped with the key.
         *
         * @param key - key for the float to be retrieved from shared Preference.
         * @return - float value or 0.
         */
        public static float getFloat(String key) {
            float value = 0;
            if (checkSharedPrefsInstance()) {
                value = sharedPreferences.getFloat(key, 0);
            }
            return value;
        }

        /**
         * Retrieve the long from SharedPreferences mapped with the key.
         *
         * @param key - key for the long to be retrieved from shared Preference.
         * @return - long value or 0.
         */
        public static long getLong(String key) {
            long value = 0;
            if (checkSharedPrefsInstance()) {
                value = sharedPreferences.getLong(key, 0);
            }
            return value;
        }

        /**
         * Retrieve the boolean from SharedPreferences mapped with the key.
         *
         * @param key - key for the boolean to be retrieved from shared Preference.
         * @return - boolean value.
         */
        public static boolean getBoolean(String key) {
            boolean value = false;
            if (checkSharedPrefsInstance()) {
                value = sharedPreferences.getBoolean(key, false);
            }
            return value;
        }

        /**
         * Retrieve the Set<String> from SharedPreferences mapped with the key.
         * (Available in SDK version higher than HONEYCOMB).
         *
         * @param key - key for the Set<String> to be retrieved from shared Preference.
         * @return - Set<String> or null.
         */
        public static Set<String> getStringSet(String key) {
            Set<String> value = null;
            if (checkSharedPrefsInstance()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    value = sharedPreferences.getStringSet(key, null);
                } else {
                    Log.e(TAG, "Getting  Set<String> from SharedPreferences is available " +
                            "in SDK version higher than HONEYCOMB");
                }
            }
            return value;
        }


    }


}

