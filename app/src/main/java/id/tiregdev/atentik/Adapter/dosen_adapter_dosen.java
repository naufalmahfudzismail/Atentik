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

public class dosen_adapter_dosen extends RecyclerView.Adapter<dosen_adapter_dosen.holder_mhsw_dosen> {

    private List<object_mhsw_dosen> itemList;
    private Context context;
    TextView namadialog, nip, nidnTxt, nidn, email, tlp, jabatan, spasi;
    RelativeLayout wrapDialog;

    public dosen_adapter_dosen(Context context, List<object_mhsw_dosen> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_mhsw_dosen onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_dosen_dan_mhsw,null);

//        RelativeLayout wrap = layoutView.findViewById(R.id.wrapperCV);
//        wrap.setBackgroundColor(ContextCompat.getColor(context, R.color.bgCV));
//
//        nama = layoutView.findViewById(R.id.nama);
//        nama.setTextColor(ContextCompat.getColor(context, R.color.putih));
//
//        jabatan = layoutView.findViewById(R.id.jabatanAtauKelas);
//        jabatan.setTextColor(ContextCompat.getColor(context, R.color.putih));
//
//        spasi = layoutView.findViewById(R.id.spasi);
//        spasi.setTextColor(ContextCompat.getColor(context, R.color.putih));
//
//        nip = layoutView.findViewById(R.id.nipAtauNim);
//        nip.setTextColor(ContextCompat.getColor(context, R.color.putih));
//
//        tlp = layoutView.findViewById(R.id.emailAtauTlp);
//        tlp.setTextColor(ContextCompat.getColor(context, R.color.putih));

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
        holder.ava.setImageResource(itemList.get(position).getAva());
    }

    @Override
    public int getItemCount(){
        return  this.itemList.size();
    }

    public class holder_mhsw_dosen extends RecyclerView.ViewHolder {
        public TextView nama, jabatanAatauKelas, nipAtauNim, emailAtauTlpn, jumlahKompenAtauStatusDosen, statusSPatauEmailDosen;
        public ImageView ava;

        public holder_mhsw_dosen(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jabatanAatauKelas = itemView.findViewById(R.id.jabatanAtauKelas);
            nipAtauNim = itemView.findViewById(R.id.nipAtauNim);
            emailAtauTlpn = itemView.findViewById(R.id.emailAtauTlp);
            jumlahKompenAtauStatusDosen = itemView.findViewById(R.id.jumlahKompenAtauStatusDosen);
            statusSPatauEmailDosen = itemView.findViewById(R.id.statusSPatauEmailDosen);

            RelativeLayout wrap = itemView.findViewById(R.id.wrapperCV);
            wrap.setBackgroundColor(ContextCompat.getColor(context, R.color.bgCV));

            nama.setTextColor(ContextCompat.getColor(context, R.color.putih));

            jabatanAatauKelas.setTextColor(ContextCompat.getColor(context, R.color.putih));

            spasi = itemView.findViewById(R.id.spasi);
            spasi.setTextColor(ContextCompat.getColor(context, R.color.putih));

            nipAtauNim.setTextColor(ContextCompat.getColor(context, R.color.putih));

            emailAtauTlpn.setTextColor(ContextCompat.getColor(context, R.color.putih));

            ava = itemView.findViewById(R.id.ava);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LayoutInflater factory = LayoutInflater.from(context);
                    final View exitDialogView = factory.inflate(R.layout.dialog_detail_dosen, null);
                    final AlertDialog exitDialog = new AlertDialog.Builder(context).create();

                    wrapDialog = exitDialogView.findViewById(R.id.wrapDialog);
                    wrapDialog.setBackgroundColor(context.getResources().getColor(R.color.AbuBG));

                    namadialog= exitDialogView.findViewById(R.id.nama);
                    namadialog.setTextColor(context.getResources().getColor(R.color.pink));

                    nip = exitDialogView.findViewById(R.id.nip);
                    nip.setTextColor(context.getResources().getColor(R.color.putih));

                    nidnTxt = exitDialogView.findViewById(R.id.nidnTxt);
                    nidnTxt.setTextColor(context.getResources().getColor(R.color.putih));

                    nidn = exitDialogView.findViewById(R.id.nidn);
                    nidn.setTextColor(context.getResources().getColor(R.color.putih));

                    email = exitDialogView.findViewById(R.id.email);
                    email.setTextColor(context.getResources().getColor(R.color.putih));

                    namadialog.setText(nama.getText());
                    nip.setText(nipAtauNim.getText());
                    nidn.setText(jumlahKompenAtauStatusDosen.getText());
                    email.setText(statusSPatauEmailDosen.getText());

                    exitDialog.setView(exitDialogView);
                    exitDialog.show();
                }
            });
        }
    }

}
