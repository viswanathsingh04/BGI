package bgi.com.bgi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bgi.com.bgi.adapter.SampleAdapter;
import bgi.com.bgi.bean.Data;
import bgi.com.bgi.bean.Sample_data;
import bgi.com.bgi.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VPS on 14-12-2017.
 */

public class RecycleList extends Fragment implements View.OnClickListener {
    TextView txt_internal, txt_external;
    RecyclerView recyclerView;
    List<Data> sample_data1;
    String mm = "0";
    SampleAdapter sampleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sample_data1 = new ArrayList<>();
        getActivity().setTitle("RecyclerView Data");
        recyclerView = view.findViewById(R.id.recyclerView);
        txt_internal = view.findViewById(R.id.txt_internal);
        txt_external = view.findViewById(R.id.txt_external);
        txt_external.setOnClickListener(this);
        txt_internal.setOnClickListener(this);
        LoadData();
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        sampleAdapter = new SampleAdapter(sample_data1);
        recyclerView.setAdapter(sampleAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txt_internal) {
            Toast.makeText(getActivity(), "Clicked Internal Link", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.txt_external) {
            Toast.makeText(getActivity(), "External Link Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.AppUrl.DASHBOARD_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Sample_data> call = apiInterface.Getdata(0);
        Log.d("tag1", "message");
        call.enqueue(new Callback<Sample_data>() {
            @Override
            public void onResponse(@NonNull Call<Sample_data> call, @NonNull Response<Sample_data> response) {
            Log.d("tag2", "message2");
                if (response.isSuccessful()) {
                    Sample_data sd = response.body();
                    String getstatus = sd.getStatus();
                    sd.getData();
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }
                try {
                    List<Data> sampleData = fetchResults(response);
                    if (sampleData != null && sampleData.size() > 0) {
                        for (Data ghg : sampleData) {
                            sample_data1.add(ghg);
                        }
                        sampleAdapter.notifyDataSetChanged();
                    }
                    System.out.println("ArraySize" + sample_data1.size());
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sample_data> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    /**
     * @param response extracts List<{@link Data>} from response
     * @return
     */
    private List<Data> fetchResults(Response<Sample_data> response) {
        Sample_data sample_data = response.body();
        sample_data.getData().getClass().getName();
        return sample_data.getData();
    }
}
