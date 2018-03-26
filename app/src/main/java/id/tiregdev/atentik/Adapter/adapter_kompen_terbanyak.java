package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Activity.home_2;
import id.tiregdev.atentik.Activity.statistik_view;
import id.tiregdev.atentik.Model.object_kompen_terbanyak;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_kompen_terbanyak extends RecyclerView.Adapter<adapter_kompen_terbanyak.holder_kompen_terbanyak> {

    private List<object_kompen_terbanyak> itemList;
    private Context context;

    public adapter_kompen_terbanyak(Context context, List<object_kompen_terbanyak> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_kompen_terbanyak onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kompen_terbanyak,null);
        holder_kompen_terbanyak hn = new holder_kompen_terbanyak(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_kompen_terbanyak holder, int position){
        holder.urutan.setText(itemList.get(position).getUrutan());
        holder.nama.setText(itemList.get(position).getNama());
        holder.kelas.setText(itemList.get(position).getKelas());
        holder.jumlahKompen.setText(itemList.get(position).getJumlahKompen());
        holder.statusSP.setText(itemList.get(position).getStatusSP());
        holder.ava.setImageResource(itemList.get(position).getAva());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_kompen_terbanyak extends RecyclerView.ViewHolder {
        public TextView urutan, nama, kelas, jumlahKompen, statusSP;
        public ImageView ava;

        public holder_kompen_terbanyak(View itemView){
            super(itemView);

            urutan = itemView.findViewById(R.id.urutan);
            nama = itemView.findViewById(R.id.nama);
            kelas = itemView.findViewById(R.id.kelas);
            jumlahKompen = itemView.findViewById(R.id.jumlahKompen);
            statusSP = itemView.findViewById(R.id.statusSP);
            ava = itemView.findViewById(R.id.ava);
        }
    }

}
