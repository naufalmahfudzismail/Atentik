package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.tiregdev.atentik.Activity.log_mahasiswa;
import id.tiregdev.atentik.Activity.set_jadwal_masuk_dosen_2;
import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Activity.set_jadwal_masuk_dosen;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_jadwal_dosen extends RecyclerView.Adapter<adapter_jadwal_dosen.holder_jadwal> {

    private List<object_jadwal> itemList;
    private Context context;

    public adapter_jadwal_dosen(Context context, List<object_jadwal> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_jadwal onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal,null);
        holder_jadwal hn = new holder_jadwal(layoutView);

        RelativeLayout wrapperCV = layoutView.findViewById(R.id.wrapperCV);
        wrapperCV.setBackgroundColor(ContextCompat.getColor(context, R.color.bgCV));

        TextView matkul = layoutView.findViewById(R.id.namaMatkul);
        matkul.setTextColor(ContextCompat.getColor(context, R.color.putih));

        TextView jam = layoutView.findViewById(R.id.jam);
        jam.setTextColor(ContextCompat.getColor(context, R.color.putih));

//        TextView jpm = layoutView.findViewById(R.id.jpm);
//        jpm.setTextColor(ContextCompat.getColor(context, R.color.putih));

        TextView ruangan = layoutView.findViewById(R.id.ruangan);
        ruangan.setTextColor(ContextCompat.getColor(context, R.color.putih));

        TextView namaKelas = layoutView.findViewById(R.id.namaDosenAtauNamaKelas);
        namaKelas.setTextColor(ContextCompat.getColor(context, R.color.putih));

        TextView spasi = layoutView.findViewById(R.id.spasi);
        spasi.setTextColor(ContextCompat.getColor(context, R.color.putih));

//        TextView option = layoutView.findViewById(R.id.optionMenu);
//        option.setTextColor(ContextCompat.getColor(context, R.color.putih));
//        option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final LayoutInflater factory = LayoutInflater.from(context);
//                final View exitDialogView = factory.inflate(R.layout.dialog_set_jadwal_dosen, null);
//                final AlertDialog exitDialog = new AlertDialog.Builder(context).create();
//
//                exitDialog.setView(exitDialogView);
//                exitDialogView.findViewById(R.id.setJadwal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        exitDialog.dismiss();
//                        Intent i = new Intent(context, set_jadwal_masuk_dosen_2.class);
//                        context.startActivity(i);
//                    }
//                });
//
//                exitDialogView.findViewById(R.id.setLogMahasiswa).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        exitDialog.dismiss();
//                        Intent i = new Intent(context, log_mahasiswa.class);
//                        context.startActivity(i);
//                    }
//                });
//                exitDialog.show();
//            }
//        });

        return hn;
    }

    @Override
    public void onBindViewHolder(holder_jadwal holder, int position){
        holder.namaMatkul.setText(itemList.get(position).getNamaMatkul());
        holder.jam.setText(itemList.get(position).getJam());
//        holder.jpm.setText(itemList.get(position).getJpm());
        holder.ruangan.setText(itemList.get(position).getRuangan());
        holder.namaDosenAtauNamaKelas.setText(itemList.get(position).getNamaDosenAtauNamaKelas());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_jadwal extends RecyclerView.ViewHolder {
        public TextView namaMatkul, namaDosenAtauNamaKelas, jam, jpm, ruangan;

        public holder_jadwal(View itemView){
            super(itemView);

            namaMatkul = itemView.findViewById(R.id.namaMatkul);
            jam = itemView.findViewById(R.id.jam);
//            jpm = itemView.findViewById(R.id.jpm);
            ruangan = itemView.findViewById(R.id.ruangan);
            namaDosenAtauNamaKelas = itemView.findViewById(R.id.namaDosenAtauNamaKelas);
        }
    }

}
