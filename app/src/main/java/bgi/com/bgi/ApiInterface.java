package bgi.com.bgi;

import bgi.com.bgi.bean.Sample_data;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by VPS on 14-12-2017.
 */

public interface ApiInterface {
    //@GET("s/q3voqjgrgkmj73f/sample.json?dl=0")
    //Call<Sample_data> Getdata();

    //@GET("file/d/0Bx1jhhjOSjVjNjNsaHFkYlJRX00/view?usp=sharing")
    @GET("sample.json")
     Call<Sample_data> Getdata(
            //@Query("usp") String mm
    );
}
