package bgi.com.bgi;

import bgi.com.bgi.bean.Sample_data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by VPS on 14-12-2017.
 */

public interface ApiInterface {
    @GET("sample.json")
    Call<Sample_data> Getdata(@Query("dl") String mm);
}
