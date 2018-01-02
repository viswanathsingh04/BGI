package bgi.com.bgi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bgi.com.bgi.R;
import bgi.com.bgi.bean.Content;

/**
 * Created by VPS on 02-01-2018.
 */

public class PickzyAdapter extends RecyclerView.Adapter<PickzyAdapter.MyViewHolder> {
    List<Content> contents;

    public PickzyAdapter(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public PickzyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pickzydata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Content item = contents.get(position);
        String name = item.getName();
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView url;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            url = (ImageView) itemView.findViewById(R.id.url);
        }
    }
}
