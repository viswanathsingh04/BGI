package bgi.com.bgi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bgi.com.bgi.R;
import bgi.com.bgi.bean.Store_list;

/**
 * Created by VPS on 28-12-2017.
 */

public class StoreDataAdapter extends RecyclerView.Adapter<StoreDataAdapter.MyViewHolder> {
    List<Store_list> stores;

    public StoreDataAdapter(List<Store_list> stores) {
        this.stores = stores;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.storedatalist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Store_list store_list = stores.get(position);
        holder.title.setText(store_list.getTitle());
        holder.shop_name.setText(store_list.getShop_name());
        holder.location.setText(store_list.getLocation());
        holder.offer.setText(store_list.getOffer());
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, shop_name, location, offer;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            location = (TextView) itemView.findViewById(R.id.location);
            offer = (TextView) itemView.findViewById(R.id.offer);
        }
    }
}
