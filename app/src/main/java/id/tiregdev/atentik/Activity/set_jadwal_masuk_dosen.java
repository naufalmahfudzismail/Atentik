package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Adapter.adapter_set_jadwal;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_set_jadwal;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_set_jadwal_masuk;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class set_jadwal_masuk_dosen extends AppCompatActivity {

    AppCompatRadioButton dosenTelat;;
    RadioGroup RG;
    TextView namaMatkul, namaDosen, namaTxt, updateTxt, namaUpdate, waktuUpdate, tgl;
    Locale localeID = new Locale("in", "ID");
    String tokens, hari;
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    RadioButton rb1, rb2, rb3;
    EditText setJamMasuk;
    Button submit;
    RecyclerView rView;
    LinearLayoutManager lLayout;
    List<object_set_jadwal_masuk> sj = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_jadwal_masuk_dosen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        namaMatkul = findViewById(R.id.namaMatkul);
        namaDosen = findViewById(R.id.namaDosen);
        tgl = findViewById(R.id.tgl);
        String tanggal = new SimpleDateFormat("dd MMMM yyyy", localeID).format(new Date());
        hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
        String haritanggal = hari + ", " + tanggal;
        tgl.setText(haritanggal);
        setupAdapter();


//        tahun = new SimpleDateFormat("yyyy", localeID).format(new Date());
//
//        Bundle extras = getIntent().getExtras();
//        namaMatkul.setText(extras.getString("nmatkul"));
//        namaDosen.setText(extras.getString("ndosen"));
//        rb1 = findViewById(R.id.dosenTelat);
//        rb2 = findViewById(R.id.dosenTidakMasuk);
//        rb3 = findViewById(R.id.dosenTidakMasukMhswMasuk);
//        namaTxt = findViewById(R.id.namaTxt);
//        updateTxt = findViewById(R.id.updateTxt);
//        namaUpdate = findViewById(R.id.namaUpdate);
//        waktuUpdate = findViewById(R.id.waktuUpdate);
//
//        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
//        Call<object_set_jadwal_masuk> call = client.setJadwalMasukDosen("Bearer " + tokens, namaMatkul.getText().toString(), extras.getString("nhari"), namaDosen.getText().toString());
//        call.enqueue(new Callback<object_set_jadwal_masuk>() {
//            @Override
//            public void onResponse(Call<object_set_jadwal_masuk> call, Response<object_set_jadwal_masuk> response) {
//                if(response.isSuccessful())
//                {
//                    if(response.body() != null)
//                    {
//                        namaTxt.setVisibility(View.VISIBLE);
//                        updateTxt.setVisibility(View.VISIBLE);
//                        namaUpdate.setVisibility(View.VISIBLE);
//                        waktuUpdate.setVisibility(View.VISIBLE);
//                        namaUpdate.setText(response.body().getPengubah_jadwal());
//                        waktuUpdate.setText(response.body().getWaktu_diubah());
//                        submit.setVisibility(View.GONE);
//                        if(response.body().getPilihan().equals("1"))
//                        {
//                            rb1.setChecked(true);
//                            setJamMasuk.setText(response.body().getWaktu_masuk());
//                            setJamMasuk.setEnabled(false);
////                            rb2.setEnabled(false);
////                            rb3.setEnabled(false);
////
////                        }
////                        else if(response.body().getPilihan().equals("2"))
////                        {
////                            rb2.setChecked(true);
////                            rb1.setEnabled(false);
////                            rb3.setEnabled(false);
////                        }
////                        else
////                        {
////                            rb3.setChecked(true);
////                            rb1.setEnabled(false);
////                            rb2.setEnabled(false);
////                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<object_set_jadwal_masuk> call, Throwable t) {
//                Toast.makeText(set_jadwal_masuk_dosen.this, t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        setSubmit();
//        setRG();
    }

    public void setupAdapter(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_set_jadwal_masuk>> call = client.lihatJadwalMasukMahasiswa("Bearer " + tokens, tanggals, hari);
        call.enqueue(new Callback<List<object_set_jadwal_masuk>>() {
            @Override
            public void onResponse(Call<List<object_set_jadwal_masuk>> call, Response <List<object_set_jadwal_masuk>> response) {
                if(response.isSuccessful())
                {
                    List<object_set_jadwal_masuk> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++) {
                        sj.add(new object_set_jadwal_masuk(simpan.get(i).getPilihan(), simpan.get(i).getWaktu_masuk(), simpan.get(i).getWaktu_diubah(), simpan.get(i).getPengubah_jadwal(), simpan.get(i).getNama_matkul(), simpan.get(i).getNama()));
                    }
                    List<object_set_jadwal_masuk> rowListItem = sj;
                    lLayout = new LinearLayoutManager(set_jadwal_masuk_dosen.this);

                    rView = findViewById(R.id.rview);
                    rView.setLayoutManager(lLayout);

                    adapter_set_jadwal rcAdapter = new adapter_set_jadwal(set_jadwal_masuk_dosen.this, rowListItem);
                    rView.setAdapter(rcAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<object_set_jadwal_masuk>> call, Throwable t) {
                Toast.makeText(set_jadwal_masuk_dosen.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<object_set_jadwal_masuk> getAllItemList(){
        List<object_set_jadwal_masuk> allItems = new ArrayList<>();
//        allItems.add(new object_set_jadwal_masuk("1","05.00","Kemaren","Fajar" ,"Bahasa Indonesia", "Pak Fauzi"));
//        allItems.add(new object_set_jadwal_masuk("","Hafizh","Sekarang", "Hafizh","PKN", "Pak Mauldy"));

        return allItems;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                set_jadwal_masuk_dosen.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSubmit(){
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_jadwal_masuk_dosen.this.finish();
                Toast.makeText(set_jadwal_masuk_dosen.this, "Jadwal berhasil di set", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRG(){
        setJamMasuk = findViewById(R.id.setJamMasuk);
        dosenTelat = findViewById(R.id.dosenTelat);
        RG = findViewById(R.id.RG);

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.dosenTelat){
                    setJamMasuk.setEnabled(true);
                }
                else {
                    setJamMasuk.setEnabled(false);
                }
            }
        });
    }
}
