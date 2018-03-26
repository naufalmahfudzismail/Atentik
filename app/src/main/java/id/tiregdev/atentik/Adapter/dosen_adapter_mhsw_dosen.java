package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class dosen_adapter_mhsw_dosen extends RecyclerView.Adapter<dosen_adapter_mhsw_dosen.holder_mhsw_dosen> {

    private List<object_mhsw_dosen> itemList;
    private Context context;
    TextView nama, jabatanAtauKelas, spasi, nipAtauNim, emailAtauTlp;

    public dosen_adapter_mhsw_dosen(Context context, List<object_mhsw_dosen> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_mhsw_dosen onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_dosen_dan_mhsw,null);

        RelativeLayout wrap = layoutView.findViewById(R.id.wrapperCV);
        wrap.setBackgroundColor(ContextCompat.getColor(context, R.color.bgCV));

        nama = layoutView.findViewById(R.id.nama);
        nama.setTextColor(ContextCompat.getColor(context, R.color.putih));

        jabatanAtauKelas = layoutView.findViewById(R.id.jabatanAtauKelas);
        jabatanAtauKelas.setTextColor(ContextCompat.getColor(context, R.color.putih));

        spasi = layoutView.findViewById(R.id.spasi);
        spasi.setTextColor(ContextCompat.getColor(context, R.color.putih));

        nipAtauNim = layoutView.findViewById(R.id.nipAtauNim);
        nipAtauNim.setTextColor(ContextCompat.getColor(context, R.color.putih));

        emailAtauTlp = layoutView.findViewById(R.id.emailAtauTlp);
        emailAtauTlp.setTextColor(ContextCompat.getColor(context, R.color.putih));

        holder_mhsw_dosen hn = new holder_mhsw_dosen(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_mhsw_dosen holder, int position){
        holder.nama.setText(itemList.get(position).getNama());
        holder.jabatanAatauKelas.setText(itemList.get(position).getJabatanAatauKelas());
        holder.nipAtauNim.setText(itemList.get(position).getNipAtauNim());
        holder.emailAtauTlpn.setText(itemList.get(position).getEmailAtauTlpn());
        holder.ava.setImageResource(itemList.get(position).getAva());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_mhsw_dosen extends RecyclerView.ViewHolder {
        public TextView nama, jabatanAatauKelas, nipAtauNim, emailAtauTlpn;
        public ImageView ava;

        public holder_mhsw_dosen(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jabatanAatauKelas = itemView.findViewById(R.id.jabatanAtauKelas);
            nipAtauNim = itemView.findViewById(R.id.nipAtauNim);
            emailAtauTlpn = itemView.findViewById(R.id.emailAtauTlp);
            ava = itemView.findViewById(R.id.ava);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LayoutInflater factory = LayoutInflater.from(context);
                    final View exitDialogView = factory.inflate(R.layout.dialog_detail_dsn_mhsw, null);
                    final AlertDialog exitDialog = new AlertDialog.Builder(context).create();

                    exitDialog.setView(exitDialogView);
                    exitDialog.show();
                }
            });
        }
    }

}
