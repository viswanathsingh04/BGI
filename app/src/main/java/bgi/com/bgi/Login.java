package bgi.com.bgi;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import bgi.com.bgi.utils.Common;
import bgi.com.bgi.utils.Constant;
import bgi.com.bgi.utils.NetworkChangeReceiver;

public class Login extends Common implements View.OnClickListener {

    String s_mobile, s_passcode;
    private Button mButton;
    private TextView mTextView2;
    private TextView mTextView4;
    private BroadcastReceiver mNetworkReceiver;
    public static TextView mTvCheckConnection;
    private TextInputEditText mTxtMob;
    private TextInputEditText mTxtPasscode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTvCheckConnection = (TextView) findViewById(R.id.tv_check_connection);
        mTxtMob = (TextInputEditText) findViewById(R.id.txt_mob);
        mTxtPasscode = (TextInputEditText) findViewById(R.id.txt_passcode);
        mTextView2.setOnClickListener(this);
        mTextView4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button) {
            if (mTxtMob.getText().toString().trim().equals("")) {
                Toast.makeText(Login.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            } else if (mTxtPasscode.getText().toString().trim().equals("")) {
                Toast.makeText(Login.this, "Please Enter Passcode", Toast.LENGTH_SHORT).show();
            } else {
                hideKeyBoard(Login.this);
                if (isMobileNetworkAvailable()) {
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    finish();
                } else {
                    Ghost_alert();
                }
            }
        } else if (i == R.id.textView2) {
            Toast.makeText(Login.this, "Forget Passcode Link Clicked", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.textView4) {
            startActivity(new Intent(getApplicationContext(), Register.class));
        }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    public static void dialog(boolean value) {
        if (value) {
            mTvCheckConnection.setVisibility(View.VISIBLE);
            mTvCheckConnection.setText("We are back !!!");
            mTvCheckConnection.setBackgroundColor(Color.GREEN);
            mTvCheckConnection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    mTvCheckConnection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        } else {
            mTvCheckConnection.setVisibility(View.VISIBLE);
            mTvCheckConnection.setText("Could not Connect to internet");
            mTvCheckConnection.setBackgroundColor(Color.RED);
            mTvCheckConnection.setTextColor(Color.WHITE);
        }
    }
    public class LoginAsyncTask extends AsyncTask<Void, Void, String> {
        String response = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(Login.this);
        }

        @Override
        protected String doInBackground(Void... voids) {
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(Constant.AppUrl.LOGIN);
                String param = Constant.ApiParams.MOBILE + "=" + URLEncoder.encode(s_mobile, "UTF-8") + "&" +
                        Constant.ApiParams.PASSWORD + "=" + URLEncoder.encode(s_passcode, "UTF-8");
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                Scanner inStream = new Scanner(conn.getInputStream());
                while (inStream.hasNextLine())
                    response += (inStream.nextLine());
            } catch (IOException ex) {
                Log.e("Error", ex.getMessage());
                ex.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissProgressDialog();
            try {
                JSONObject jsonObject = new JSONObject(s);
                String status = jsonObject.getString("response");
                switch (status) {
                    case Constant.ResponseCodes.SUCCESS:/*
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(Constant.PreferenceManager.USER_ID, "1");
                        editor.apply();*/
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    case Constant.ResponseCodes.FAILED:
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Login Failed, Please Try Again later...", Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (Exception ex) {
                Log.e("Error", ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
