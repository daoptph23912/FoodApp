package com.example.food_app_design.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class Utils {
//    kiểm soát lỗi khi nhập từ bàn phím
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.
                    getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
