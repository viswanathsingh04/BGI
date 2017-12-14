package bgi.com.bgi.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;

import bgi.com.bgi.R;


/**
 * Created by Android on 12/12/2017.
 */
public class Common extends AppCompatActivity implements View.OnClickListener {

    public Context content;
    public Button mReload;
    public Animation animation_slide_in_right;
    public Typeface Light, Regular;
    public NetworkChangeReceiver connectionDetector;
    public boolean isInternetAvailable, isNetAvailable;
    public ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animation_slide_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Light = Typeface.createFromAsset(getAssets(), "Ubuntu-Light.ttf");
        Regular = Typeface.createFromAsset(getAssets(), "Ubuntu-Regular.ttf");
    }
    /**
     * Checking Internet
     */
    public boolean isMobileNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }
    /**
     * Load Json file from Assert folder
     */
    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Hide the soft keyboard
     */
    public void hideKeyBoard(Context context) {
        if (getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
    /**
     * Shows the Progress Dialog
     */
    public void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        // progressDialog.setTitle("App Name");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    /**
     * Dismiss the Progress Dialog
     */
    public void dismissProgressDialog() {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the Email Validation Alert
     */
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * Shows the Alert Dialog
     */
    public void Ghost_alert() {
        AlertDialog.Builder bmneu = new AlertDialog.Builder(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        LayoutInflater inflate = getLayoutInflater();
        View v = inflate.inflate(R.layout.custom_ghost_alert, null);
        mReload = (Button) v.findViewById(R.id.reload);
        mReload.setOnClickListener(this);
        bmneu.setView(v);
        AlertDialog alertHelp = bmneu.create();
        alertHelp.show();

        ScrollView.LayoutParams params = (ScrollView.LayoutParams) v.getLayoutParams();
        params.height = getResources().getDisplayMetrics().heightPixels;
        params.width = getResources().getDisplayMetrics().widthPixels;
        v.setLayoutParams(params);
    }

    /**
     * Click Event for Reload Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reload:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
        }
    }

    public void showExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Common.this);
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}