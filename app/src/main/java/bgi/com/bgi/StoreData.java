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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bgi.com.bgi.adapter.StoreDataAdapter;
import bgi.com.bgi.bean.Store_list;
import bgi.com.bgi.bean.Storelist;
import bgi.com.bgi.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VPS on 28-12-2017.
 */

public class StoreData extends Fragment {
    StoreDataAdapter storeDataAdapter;
    List<Store_list> storelists;
    RecyclerView list_store;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.storemenu, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storelists = new ArrayList<>();
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Store List");
        LoadList();
        list_store = (RecyclerView) view.findViewById(R.id.list_store);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_store.setLayoutManager(layoutManager);
        storeDataAdapter = new StoreDataAdapter(storelists);
        list_store.setAdapter(storeDataAdapter);

    }

    private void LoadList() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.AppUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
// Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        //progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        Call<Storelist> call = apiInterface.GetList();
        Log.d("tag1", "message");
        call.enqueue(new Callback<Storelist>() {
            @Override
            public void onResponse(@NonNull Call<Storelist> call, @NonNull Response<Storelist> response) {
                Log.d("tag2", response.message());
                if (response.isSuccessful()) {
                    Storelist sd = response.body();
                    String getstatus = sd.getStatus();
                    sd.getStatus();
                    sd.getStore_list();
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }
                try {
                    List<Store_list> sampleData = fetchResults(response);
                    if (sampleData != null && sampleData.size() > 0) {
                        for (Store_list ghg : sampleData) {
                            storelists.add(ghg);
                        }
                        storeDataAdapter.notifyDataSetChanged();
                    }
                    progressDoalog.dismiss();
                    Log.v("this", "Yes!");
                    System.out.println("ArraySize" + storelists.size());
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Storelist> call, Throwable t) {
                t.getStackTrace();
                progressDoalog.dismiss();
                Log.v("this", "No Response!");
            }
        });
    }

    /**
     * @param response extracts List<{@link Store_list>} from response
     * @return
     */
    private List<Store_list> fetchResults(Response<Storelist> response) {
        Storelist sample_data = response.body();
        sample_data.getStore_list().getClass().getName();
        return sample_data.getStore_list();
    }
}
