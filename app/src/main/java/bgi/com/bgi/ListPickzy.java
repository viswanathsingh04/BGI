package bgi.com.bgi;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

import bgi.com.bgi.adapter.PickzyAdapter;
import bgi.com.bgi.bean.Content;
import bgi.com.bgi.bean.Main;
import bgi.com.bgi.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VPS on 02-01-2018.
 */

public class ListPickzy extends Fragment {
    RecyclerView recyclerView;
    List<Content> contents ;
    PickzyAdapter pickzyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.pickzy, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("RecyclerView List Pickzy");
        LoadSample();
        contents = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        pickzyAdapter = new PickzyAdapter(contents);
        recyclerView.setAdapter(pickzyAdapter);

    }

    private void LoadSample() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.AppUrl.PICKZY).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Call<Main> call = apiInterface.GetPickzy();
        Log.d("tag1", "message");
        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(@NonNull Call<Main> call, @NonNull Response<Main> response) {
                Log.d("tag2", response.message());
                if (response.isSuccessful()) {
                    Main sd = response.body();
                    Log.d("maindata", String.valueOf(sd));
                    progressDoalog.dismiss();
                    try {
                        List<Content> sampleData = fetchResults(response);
                        if (sampleData != null && sampleData.size() > 0) {
                            for (Content ghg : sampleData) {
                                contents.add(ghg);
                            }
                            pickzyAdapter.notifyDataSetChanged();
                        }
                        progressDoalog.dismiss();
                        System.out.println("Pickzy-ArraySize" + contents.size());
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    /*assert sd != null;
                    String getstatus = String.valueOf(sd.getItem());
                    Toast.makeText(getActivity(), getstatus, Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onFailure(@NonNull Call<Main> call, Throwable t) {
                t.getStackTrace();
                progressDoalog.dismiss();
                Log.v("pickzy", "No Response!");
            }
        });
    }
    /**
     * @param response extracts List<{@link Content>} from response
     * @return
     */
    private List<Content> fetchResults(Response<Main> response) {
        Main sample_data = response.body();
        sample_data.getItem().getContent().getClass().getName();
        return sample_data.getItem().getContent();
    }
}
