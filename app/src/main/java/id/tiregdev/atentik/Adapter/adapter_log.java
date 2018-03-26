package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_log extends RecyclerView.Adapter<adapter_log.holder_log> {

    private List<object_log> itemList;
    private Context context;

    public adapter_log(Context context, List<object_log> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_log onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_log,null);
        holder_log hn = new holder_log(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_log holder, int position){
        holder.matkul.setText(itemList.get(position).getMatkul());
        holder.ruang.setText(itemList.get(position).getRuang());
        holder.jamHadir.setText(itemList.get(position).getJamHadir());
        holder.telat.setText(itemList.get(position).getTelat());
        holder.kompen.setText(itemList.get(position).getKompen());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_log extends RecyclerView.ViewHolder {
        public TextView matkul, ruang, jamHadir, telat, kompen;

        public holder_log(View itemView){
            super(itemView);

            matkul = itemView.findViewById(R.id.matkul);
            ruang = itemView.findViewById(R.id.ruangan);
            jamHadir = itemView.findViewById(R.id.jam);
            telat = itemView.findViewById(R.id.telat);
            kompen = itemView.findViewById(R.id.kompen);
        }
    }

}
