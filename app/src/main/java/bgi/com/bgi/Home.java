package bgi.com.bgi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bgi.com.bgi.utils.Common;
import bgi.com.bgi.utils.Constant;

public class Home extends Common implements View.OnClickListener {

    /**
     * Home
     */
    private Button mNavigate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mNavigate = (Button) findViewById(R.id.navigate);
        mNavigate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.navigate:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                break;
        }
    }

    public class DetailAsyncTask extends AsyncTask<Void, Void, String> {
        private final String USER_AGENT = "Mozilla/5.0";
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(Home.this);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                String url = Constant.AppUrl.BASE_URL;
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                res = response.toString();
                System.out.println(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissProgressDialog();
            /*try {
                jsonObject = new JSONObject(s);
                String status = jsonObject.getString("status");
                if (status.equals(Constant.ResponseCodes.CATEGORIES_SUCCESS)) {
                    jsonArray = jsonObject.getJSONArray("Online Stores");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        String store_name = jsonObject2.getString("StoreName");
                        String store_status = jsonObject2.getString("Status");
                        offer_name = jsonObject2.getString("OfferName");
                        percentage = jsonObject2.getString("OfferPercentage");
                        detail = jsonObject2.getString("OfferDetails");
                        hours_time = jsonObject2.getString("WorkingHours");
                        JSONArray jsonArraygallery = jsonObject2.getJSONArray("StoreGallery");
                        for (int j = 0; j < jsonArraygallery.length(); j++) {
                            JSONObject gallery = jsonArraygallery.getJSONObject(j);
                            DataCategory dataCategory = new DataCategory();
                            dataCategory.Image_url = gallery.getString("img_url");
                            data_info_.add(dataCategory);
                        }
                        JSONArray v_gallery = jsonObject2.getJSONArray("PromotionalVideos");
                        for (int j = 0; j < v_gallery.length(); j++) {
                            JSONObject gallery = v_gallery.getJSONObject(j);
                            DataCategory dataCategory = new DataCategory();
                            dataCategory.Video_img_url = gallery.getString("video_image");
                            dataCategory.Video_url = gallery.getString("video_url");
                            video_info_.add(dataCategory);
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }*/
        }
    }
}
