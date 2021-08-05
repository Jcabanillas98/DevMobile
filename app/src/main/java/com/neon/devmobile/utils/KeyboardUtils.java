package com.neon.devmobile.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@SuppressLint("StaticFieldLeak")
public class KeyboardUtils {

    private static Activity activity;

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        KeyboardUtils.activity = activity;
    }

    private KeyboardUtils() {
    }

    public static void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view == null)
            view = new View(getActivity());
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void toggleKeyboard(EditText editText) {
        KeyboardUtils.hideKeyboard();
        editText.requestFocus();
        KeyboardUtils.showKeyboard();
    }

    public static void clearFocusImeDone(EditText editText) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editText.clearFocus();
            }
            return false;
        });
    }
}
