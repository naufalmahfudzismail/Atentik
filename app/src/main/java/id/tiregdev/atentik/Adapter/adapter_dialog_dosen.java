package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_dialog_dosen extends RecyclerView.Adapter<adapter_dialog_dosen.holder_mhsw_dosen> {

    private List<object_mhsw_dosen> itemList;
    private Context context;

    public adapter_dialog_dosen(Context context, List<object_mhsw_dosen> itemList){
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
        holder.jumlahKompenAtauStatusDosen.setText(itemList.get(position).getJumlahKompenAtauStatusDosen());
        holder.statusSPatauEmailDosen.setText(itemList.get(position).getStatusSPatauEmailDosen());

        Glide.with(context).load("https://atentik.id/assets/img/faces/" + itemList.get(position).getPhoto()).into(holder.ava);
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_mhsw_dosen extends RecyclerView.ViewHolder {
        public TextView nama, jabatanAatauKelas, nipAtauNim, emailAtauTlpn, jumlahKompenAtauStatusDosen, statusSPatauEmailDosen;
        public ImageView ava;

        public holder_mhsw_dosen(final View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jabatanAatauKelas = itemView.findViewById(R.id.jabatanAtauKelas);
            nipAtauNim = itemView.findViewById(R.id.nipAtauNim);
            emailAtauTlpn = itemView.findViewById(R.id.emailAtauTlp);
            jumlahKompenAtauStatusDosen = itemView.findViewById(R.id.jumlahKompenAtauStatusDosen);
            statusSPatauEmailDosen = itemView.findViewById(R.id.statusSPatauEmailDosen);
            ava = itemView.findViewById(R.id.ava);

            itemView.setOnClickListener(  new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LayoutInflater factory = LayoutInflater.from(context);
                    final View exitDialogView = factory.inflate(R.layout.dialog_detail_dosen, null);
                    final AlertDialog exitDialog = new AlertDialog.Builder(context).create();

                    TextView namadialog = exitDialogView.findViewById(R.id.nama);
                    TextView nip = exitDialogView.findViewById(R.id.nip);
                    TextView nidn = exitDialogView.findViewById(R.id.nidn);
                    TextView email = exitDialogView.findViewById(R.id.email);
                    namadialog.setText(nama.getText());
                    nip.setText(nipAtauNim.getText());
                    nidn.setText(jumlahKompenAtauStatusDosen.getText());
                    email.setText(statusSPatauEmailDosen.getText());

                    ImageView avava = exitDialogView.findViewById(R.id.ava);
                    Glide.with(context).load("https://atentik.id/assets/img/faces/" + itemList.get(getAdapterPosition()).getPhoto()).into(avava);

                    exitDialog.setView(exitDialogView);
                    exitDialog.show();
                }
            });
        }
    }

}
