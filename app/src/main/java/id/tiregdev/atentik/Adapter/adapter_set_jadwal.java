package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Activity.set_jadwal_masuk_dosen;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.Model.object_set_jadwal;
import id.tiregdev.atentik.Model.object_set_jadwal_masuk;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_set_jadwal extends RecyclerView.Adapter<adapter_set_jadwal.holder_set_jadwal> {

    private List<object_set_jadwal_masuk> itemList;
    private Context context;
    String tokens, pilihans;
    Locale localeID = new Locale("in", "ID");
    String jam = new SimpleDateFormat("HH:mm:ss ZZZZ", localeID).format(new Date());
    String hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());

    public adapter_set_jadwal(Context context, List<object_set_jadwal_masuk> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_set_jadwal onCreateViewHolder(ViewGroup parent, int viewType){
        final View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_set_jadwal,null);
        holder_set_jadwal hn = new holder_set_jadwal(layoutView);
        CekToken ct = new CekToken();
        tokens = ct.Cek(layoutView.getContext());
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_set_jadwal holder, int position) {
        holder.namaMatkul.setText(itemList.get(position).getNama_matkul());
        holder.namaDosen.setText(itemList.get(position).getNama());
        if(itemList.get(position).getPilihan() != null){
            holder.namaTxt.setVisibility(View.VISIBLE);
            holder.updateTxt.setVisibility(View.VISIBLE);
            holder.namaUpdate.setVisibility(View.VISIBLE);
            holder.waktuUpdate.setVisibility(View.VISIBLE);
            holder.namaUpdate.setText(itemList.get(position).getPengubah_jadwal());
            holder.waktuUpdate.setText(itemList.get(position).getWaktu_diubah());
            holder.submit.setVisibility(View.GONE);
                        if (itemList.get(position).getPilihan().equals("1"))
                        {
                            holder.rb1.setChecked(true);
                            holder.waktuMasuk.setText(itemList.get(position).getWaktu_masuk());
                            holder.waktuMasuk.setEnabled(false);
                            holder.rb2.setEnabled(false);
                            holder.rb3.setEnabled(false);

                        }
                        else if(itemList.get(position).getPilihan().equals("2"))
                        {
                            holder.rb2.setChecked(true);
                            holder.rb1.setEnabled(false);
                            holder.rb3.setEnabled(false);
                        }
                        else if(itemList.get(position).getPilihan().equals("3"))
                        {
                            holder.rb3.setChecked(true);
                            holder.rb1.setEnabled(false);
                            holder.rb2.setEnabled(false);
                        }
        }
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_set_jadwal extends RecyclerView.ViewHolder {
        public TextView namaMatkul, namaDosen, namaUpdate, waktuUpdate, waktuMasuk, namaTxt, updateTxt;
        public RadioButton rb1, rb2, rb3;
        public RadioGroup RG;
        public Button submit;

        public holder_set_jadwal(final View itemView){
            super(itemView);

            namaMatkul = itemView.findViewById(R.id.namaMatkul);
            namaDosen = itemView.findViewById(R.id.namaDosen);
            namaUpdate = itemView.findViewById(R.id.namaUpdate);
            waktuUpdate = itemView.findViewById(R.id.waktuUpdate);
            waktuMasuk = itemView.findViewById(R.id.setJamMasuk);
            namaTxt = itemView.findViewById(R.id.namaTxt);
            updateTxt = itemView.findViewById(R.id.updateTxt);
            rb1 = itemView.findViewById(R.id.dosenTelat);
            rb2 = itemView.findViewById(R.id.dosenTidakMasuk);
            rb3 = itemView.findViewById(R.id.dosenTidakMasukMhswMasuk);
            RG = itemView.findViewById(R.id.RG);

            RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i == R.id.dosenTelat){
                        waktuMasuk.setEnabled(true);
                    }
                    else {
                        waktuMasuk.setEnabled(false);
                    }
                }
            });

            submit = itemView.findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rb1.isChecked())
                    {
                        pilihans = "1";
                    }
                    else if(rb2.isChecked())
                    {
                        pilihans = "2";
                    }
                    else if(rb3.isChecked())
                    {
                        pilihans = "3";
                    }
                    AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                    Call<object_set_jadwal_masuk> call = client.setJadwalMasukMahasiswa("Bearer " + tokens, namaMatkul.getText().toString(), hari, namaDosen.getText().toString(), tanggals, pilihans, waktuMasuk.getText().toString(), jam);
                    call.enqueue(new Callback<object_set_jadwal_masuk>() {
                        @Override
                        public void onResponse(Call<object_set_jadwal_masuk> call, Response<object_set_jadwal_masuk> response) {
                            if(response.isSuccessful())
                            {
                                Toast.makeText(itemView.getContext(), response.body().getPesan(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<object_set_jadwal_masuk> call, Throwable t) {
                            Toast.makeText(itemView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

}
