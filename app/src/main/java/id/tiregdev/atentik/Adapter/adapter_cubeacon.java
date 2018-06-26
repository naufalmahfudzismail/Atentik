package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_cubeacon extends RecyclerView.Adapter<adapter_cubeacon.holder_cubeacon> {

    private List<object_cubeacon> itemList;
    private Context context;
    String tokens;
    Locale localeID = new Locale("in", "ID");
    String jam = new SimpleDateFormat("HH:mm:ss ZZZZ", localeID).format(new Date());
    String tanggal = new SimpleDateFormat("dd MMMM yyyy", localeID).format(new Date());
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    String hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
    String haritanggal = hari + ", " + tanggal;

    public adapter_cubeacon(Context context, List<object_cubeacon> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_cubeacon onCreateViewHolder(ViewGroup parent, int viewType){
        final View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connect_cubeacon,null);

        Button btnAbsen = layoutView.findViewById(R.id.btnAbsen);
        final TextView ruangan = layoutView.findViewById(R.id.ruangan);
        final TextView cubeaconNumber = layoutView.findViewById(R.id.cubeaconNumber);
        final TextView jarak = layoutView.findViewById(R.id.jarak);
        CekToken ct = new CekToken();
        tokens = ct.Cek(layoutView.getContext());
        btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jarak.getText().equals("NEAR") || jarak.getText().equals("IMMEDIATE"))
                {
                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                        Call<object_log_mahasiswa> call = client.absenMahasiswa("Bearer " + tokens, hari, jam, tanggals, cubeaconNumber.getText().toString());
                        call.enqueue(new Callback<object_log_mahasiswa>() {
                            @Override
                            public void onResponse(Call<object_log_mahasiswa> call, Response<object_log_mahasiswa> response) {
                                if(response.isSuccessful())
                                {
                                    if(response.body().getJam_hadir().equals("-"))
                                        Toast.makeText(layoutView.getContext(), "Anda berhasil absen pada jam " + response.body().getKompen(), Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(layoutView.getContext(), response.body().getJam_hadir(), Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(layoutView.getContext(), response.raw().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<object_log_mahasiswa> call, Throwable t) {
                                Toast.makeText(layoutView.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                }
                else
                {
                    Toast.makeText(context, "Jarak Anda kurang dekat", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder_cubeacon hn = new holder_cubeacon(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_cubeacon holder, int position){
        holder.ruang.setText(itemList.get(position).getRuangan());
        holder.cubeaconNumber.setText(itemList.get(position).getName());
        holder.jarak.setText(itemList.get(position).getProximity());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_cubeacon extends RecyclerView.ViewHolder {
        public TextView ruang, cubeaconNumber, jarak;

        public holder_cubeacon(View itemView){
            super(itemView);

            ruang = itemView.findViewById(R.id.ruangan);
            cubeaconNumber = itemView.findViewById(R.id.cubeaconNumber);
            jarak = itemView.findViewById(R.id.jarak);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Bisa", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

}
