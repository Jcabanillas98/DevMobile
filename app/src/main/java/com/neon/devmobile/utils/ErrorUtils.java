package com.neon.devmobile.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.neon.devmobile.R;

@SuppressLint("StaticFieldLeak")
public class ErrorUtils {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    private ErrorUtils() {
    }

    public static boolean isEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    public static boolean isValidPassword(EditText editText) {
        return editText.getText().toString().length() < 8;
    }

    public static void setError(LinearLayout linearLayout, TextView textView, boolean show) {
        linearLayout.setBackground(ContextCompat.getDrawable(getContext(), show ? R.drawable.drop_shadow_error : R.drawable.drop_shadow));
        textView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public static void setError(LinearLayout linearLayout, TextView textView, String string, boolean show) {
        linearLayout.setBackground(ContextCompat.getDrawable(getContext(), show ? R.drawable.drop_shadow_error : R.drawable.drop_shadow));
        textView.setText(string);
        textView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public static void addErrorListener(EditText editText, LinearLayout linearLayout, TextView textView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editText.getText().toString().isEmpty())
                    setError(linearLayout, textView, false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
