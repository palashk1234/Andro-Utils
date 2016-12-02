import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Created by Palash on 29/11/2016.
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
     * Check whether the context is null or not
     *
     * @return -boolean
     */
    private static boolean checkContextInstance() {
        boolean isContext = true;
        if (!isNull(context)) {
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            if (checkContextInstance()) {
                if (isNull(sharedPreferences)) {
                    sharedPreferences = context.getSharedPreferences("", 1);
                    editor = sharedPreferences.edit();
                }
                isContext = true;
            } else {
                isContext = false;
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

    /**
     * Utilities for Activity
     */
    public static class Activity {

        /**
         * Method to Start any activity.
         *
         * @param activity - Activity to start
         */
        public static void startActivity(android.app.Activity activity) {
            if (checkContextInstance()) {
                if (!isNull(activity)) {
                    context.startActivity(new Intent(context, activity.getClass()));
                } else {
                    Log.e(TAG, "Activity should not be null");
                }
            }

        }

        /**
         * Method to Start any activity with bundle data.
         *
         * @param activity - Activity to start
         * @param bundle   - Bundle object with data to send
         * @param key      - key for Bundle object
         */
        public static void startActivityWithData(android.app.Activity activity, Bundle bundle, String key) {
            if (checkContextInstance()) {
                if (!isNull(activity)) {
                    Intent intent = new Intent(context, activity.getClass());
                    intent.putExtra(key, bundle);
                    context.startActivity(new Intent(context, activity.getClass()));
                } else {
                    Log.e(TAG, "Activity should not be null");
                }
            }

        }

        /**
         * Method to Start any activity with List.
         *
         * @param activity - Activity to start
         * @param list     - List to send to activity
         * @param key      - key for setting List object in the intent.
         */
        public static void startActivityWithList(android.app.Activity activity, List<?> list, String key) {
            if (checkContextInstance()) {
                if (!isNull(activity)) {
                    Intent intent = new Intent(context, activity.getClass());
                    if (!isNull(list)) {
                        Type type = new TypeToken<List<?>>() {
                        }.getType();
                        String lstString = new Gson().toJson(list, type);
                        intent.putExtra(key, lstString);
                    }
                    context.startActivity(new Intent(context, activity.getClass()));
                } else {
                    Log.e(TAG, "Activity should not be null");
                }
            }

        }

        /**
         * Method to Start any activity for result.
         *
         * @param firstActivity  - The calling Activity
         * @param secondActivity - The activity to be started
         * @param requestCode    - request code
         */
        public static void startActivityForResult(android.app.Activity firstActivity,
                                                  android.app.Activity secondActivity, int requestCode) {
            if (checkContextInstance()) {
                if (!isNull(firstActivity)) {
                    if (!isNull(secondActivity)) {
                        firstActivity.startActivityForResult(new Intent(context, secondActivity.getClass()),
                                requestCode);
                    } else {
                        Log.e(TAG, "Second Activity should not be null.");
                    }

                } else {
                    Log.e(TAG, "First Activity should not be null.");
                }
            }
        }
    }


    /**
     * Utilities for Dialog
     */

    public static class Dialog {

        /**
         * Method to show an Alert Dialog on screen.
         *
         * @param context               - Context of currently running activity in foreground
         * @param title                 - Title string of the dialog.
         * @param message               - Message to be shown in dialog.
         * @param positiveText          - Text to shown on positive button
         * @param negativeText          - Text to shown on positive button
         * @param positiveClickListener - Operation to be performed on click of positive button
         * @param negativeClickListener - Operation to be performed on click of negative button
         */
        public static void showAlertDialog(Context context, String title, String message, String positiveText, String negativeText,
                                           DialogInterface.OnClickListener positiveClickListener,
                                           DialogInterface.OnClickListener negativeClickListener) throws Exception {
            if (!isNull(context)) {
                new AlertDialog.Builder(context)
                        .setTitle("" + title)
                        .setMessage("" + message)
                        .setPositiveButton("" + positiveText, positiveClickListener)
                        .setNegativeButton("" + negativeText, negativeClickListener)
                        .show();
            } else {
                Log.e(TAG, "Dialog requires context of activity in foreground, Context should not be null.");
            }
        }


        /**
         * Method to show a support V7 (Material theme) Alert Dialog on screen.
         *
         * @param context               - Context of currently running activity in foreground
         * @param title                 - Title string of the dialog.
         * @param message               - Message to be shown in dialog.
         * @param positiveText          - Text to shown on positive button
         * @param negativeText          - Text to shown on positive button
         * @param positiveClickListener - Operation to be performed on click of positive button
         * @param negativeClickListener - Operation to be performed on click of negative button
         */
        public static void showAlertDialogV7(Context context, String title, String message, String positiveText, String negativeText,
                                             DialogInterface.OnClickListener positiveClickListener,
                                             DialogInterface.OnClickListener negativeClickListener) throws Exception {
            if (!isNull(context)) {
                new android.support.v7.app.AlertDialog.Builder(context)
                        .setTitle("" + title)
                        .setMessage("" + message)
                        .setPositiveButton("" + positiveText, positiveClickListener)
                        .setNegativeButton("" + negativeText, negativeClickListener)
                        .show();
            } else {
                Log.e(TAG, "Dialog requires context of activity in foreground, Context should not be null.");
            }
        }


    }

}

