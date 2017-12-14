package bgi.com.bgi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bgi.com.bgi.R;
import bgi.com.bgi.bean.Data;

/**
 * Created by VPS on 14-12-2017.
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.MyViewHolder> {
    //List<Data> data;
    List<Data> sample_data;

    public SampleAdapter(List<Data> sample_data) {
        this.sample_data = sample_data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Sample_data sample_data1 = sample_data.get(position);
        Data data1 = sample_data.get(position);
        holder.txt_name.setText(data1.getXxx());

    }

    @Override
    public int getItemCount() {
        return sample_data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }
}
