package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Activity.detail_notif;
import id.tiregdev.atentik.Model.object_lokasi;
import id.tiregdev.atentik.Model.object_tracking;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 15/03/18.
 */

public class adapter_tracking extends RecyclerView.Adapter<adapter_tracking.holder_tracking> {

    private List<object_lokasi> itemList;
    private Context context;

    public adapter_tracking(Context context, List<object_lokasi> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @Override
    public holder_tracking onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tracking, null);
        holder_tracking hn = new holder_tracking(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_tracking holder, int position) {
        holder.nama.setText(itemList.get(position).getNama());
        holder.nimOrNip.setText(itemList.get(position).getNimornip());
        holder.posisi.setText(itemList.get(position).getPosisi());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class holder_tracking extends RecyclerView.ViewHolder {
        public TextView nama, nimOrNip, posisi;

        public holder_tracking(final View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            nimOrNip = itemView.findViewById(R.id.nimOrNip);
            posisi = itemView.findViewById(R.id.posisi);
        }
    }
}
