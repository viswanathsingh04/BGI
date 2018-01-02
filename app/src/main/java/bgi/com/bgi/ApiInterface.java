package bgi.com.bgi;

import bgi.com.bgi.bean.Main;
import bgi.com.bgi.bean.Sample_data;
import bgi.com.bgi.bean.Storelist;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by VPS on 14-12-2017.
 */

public interface ApiInterface {
    @GET("json/sample.json")
    Call<Sample_data> Getdata(
            //@Query("usp") String mm
    );

    @GET("json/list.json")
    Call<Storelist> GetList(
    );

    @GET("interview_pickzy/interview.json")
    Call<Main> GetPickzy();
}
