package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_mhsw_dosen extends RecyclerView.Adapter<adapter_mhsw_dosen.holder_mhsw_dosen> {

    private List<object_mhsw_dosen> itemList;
    private Context context;

    public adapter_mhsw_dosen(Context context, List<object_mhsw_dosen> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_mhsw_dosen onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_dosen_dan_mhsw,null);
        holder_mhsw_dosen hn = new holder_mhsw_dosen(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_mhsw_dosen holder, int position){
        holder.nama.setText(itemList.get(position).getNama());
        holder.jabatanAatauKelas.setText(itemList.get(position).getJabatanAatauKelas());
        holder.nipAtauNim.setText(itemList.get(position).getNipAtauNim());
        holder.emailAtauTlpn.setText(itemList.get(position).getEmailAtauTlpn());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_mhsw_dosen extends RecyclerView.ViewHolder {
        public TextView nama, jabatanAatauKelas, nipAtauNim, emailAtauTlpn;

        public holder_mhsw_dosen(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jabatanAatauKelas = itemView.findViewById(R.id.jabatanAtauKelas);
            nipAtauNim = itemView.findViewById(R.id.nipAtauNim);
            emailAtauTlpn = itemView.findViewById(R.id.emailAtauTlp);
        }
    }

}
