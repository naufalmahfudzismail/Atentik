package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.tiregdev.atentik.Adapter.adapter_kompen_terbanyak;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_kompen_terbanyak;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.Model.object_total;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_2 extends AppCompatActivity {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    TextView nama, totalMasuk, totalTelat, totalIzin, totalGakMasuk, totalSakit, totalBekom, totalKompen, totalUang;
    String tokens;
    Locale localeID = new Locale("in", "ID");
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    List<object_kompen_terbanyak> kompenMhsw = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        nama = findViewById(R.id.nama);
        totalMasuk = findViewById(R.id.totalMasuk);
        totalTelat = findViewById(R.id.totalTelat);
        totalIzin = findViewById(R.id.totalIzin);
        totalGakMasuk = findViewById(R.id.totalGakMasuk);
        totalSakit = findViewById(R.id.totalSakit);
        totalBekom = findViewById(R.id.totalBekom);
        totalKompen = findViewById(R.id.totalKompen);
        totalUang = findViewById(R.id.totalUang);

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_mahasiswa> call = client.profilMahasiswa("Bearer " + tokens);
        call.enqueue(new Callback<object_mahasiswa>() {
            @Override
            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                if(response.isSuccessful())
                {
                    nama.setText(response.body().getNama());
                }
            }

            @Override
            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                Toast.makeText(home_2.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<object_total> calls = client.totalSeluruhAbsensiMahasiswa("Bearer " + tokens, tanggals);
        calls.enqueue(new Callback<object_total>() {
            @Override
            public void onResponse(Call<object_total> call, Response<object_total> response) {
                if(response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        totalMasuk.setText(String.valueOf(response.body().getTotal_masuk()));
                        totalTelat.setText(String.valueOf(response.body().getTotal_telat()));
                        totalIzin.setText(String.valueOf(response.body().getTotal_izin()));
                        totalGakMasuk.setText(String.valueOf(response.body().getTotal_tidak_masuk()));
                        totalSakit.setText(String.valueOf(response.body().getTotal_sakit()));
                        totalBekom.setText(String.valueOf(response.body().getTotal_bekom()));
                        totalKompen.setText(String.valueOf(response.body().getTotal_kompen()));
                        totalUang.setText(String.valueOf(response.body().getBiaya()) + " IDR");
                    }
                    else
                    {
                        totalMasuk.setText("0");
                        totalTelat.setText("0");
                        totalIzin.setText("0");
                        totalGakMasuk.setText("0");
                        totalSakit.setText("0");
                        totalBekom.setText("0");
                        totalKompen.setText("0");
                        totalUang.setText("0 IDR");
                    }
                }
            }

            @Override
            public void onFailure(Call<object_total> call, Throwable t) {
                Toast.makeText(home_2.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialogOpsi();
        setupAdapterkompen_terbanyak();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                home_2.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dialogOpsi() {
        ImageView more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater factory = LayoutInflater.from(home_2.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_more, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(home_2.this).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.filterPage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        dialogFilter();
                    }
                });

                exitDialogView.findViewById(R.id.statistik).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Intent in = new Intent(home_2.this, statistik_view.class);
                        startActivity(in);
                    }
                });
                exitDialog.show();
            }
        });
    }


    public void dialogFilter(){
        final LayoutInflater factory = LayoutInflater.from(home_2.this);
        final View exitDialogView = factory.inflate(R.layout.dialog_filter, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(home_2.this).create();

        final Spinner sortTgl = exitDialogView.findViewById(R.id.sortTanggal);
        final Spinner sortBln = exitDialogView.findViewById(R.id.sortBulan);
        final Spinner sortThn = exitDialogView.findViewById(R.id.sortTahun);

        final String tahun[] = {
                "2018", "2019", "2020", "2021", "2022"
        };

        final String bulan[] = {
                "Januari", "Februari", "Maret", "Aapril", "Mei",
                "Juni", "Juli", "Agustus",
                "September", "Oktober", "November", "Desember"
        };

        final String tanggal[] = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        };

        final ArrayAdapter<String> AdapterTgl = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, tanggal);
        AdapterTgl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortTgl.setAdapter(AdapterTgl);

        final ArrayAdapter<String> AdapterBln = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, bulan);
        AdapterBln.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortBln.setAdapter(AdapterBln);

        final ArrayAdapter<String> AdapterThn = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, tahun);
        AdapterThn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortThn.setAdapter(AdapterThn);

        exitDialog.setView(exitDialogView);
        exitDialogView.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
                Toast.makeText(home_2.this, "Filter Sukses", Toast.LENGTH_SHORT).show();
            }
        });
        exitDialog.show();
    }

    public void setupAdapterkompen_terbanyak(){

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_kompen_terbanyak>> callz = client.kompenTerbanyak("Bearer " + tokens);
        callz.enqueue(new Callback<List<object_kompen_terbanyak>>() {
            @Override
            public void onResponse(Call<List<object_kompen_terbanyak>> call, Response<List<object_kompen_terbanyak>> response) {
                if(response.isSuccessful())
                {
                    List<object_kompen_terbanyak> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        kompenMhsw.add(new object_kompen_terbanyak(String.valueOf(i+1)+".",simpan.get(i).getNama(),simpan.get(i).getNama_kelas(),simpan.get(i).getKompen(), simpan.get(i).getStatus_sp(),R.drawable.ava));
                    }
                    List<object_kompen_terbanyak> rowListItem = kompenMhsw;
                    lLayout = new LinearLayoutManager(home_2.this);

                    rView = findViewById(R.id.rView);
                    rView.setLayoutManager(lLayout);

                    adapter_kompen_terbanyak rcAdapter = new adapter_kompen_terbanyak(home_2.this, rowListItem);
                    rView.setAdapter(rcAdapter);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(home_2.this, lLayout.getOrientation());
                    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(home_2.this, R.drawable.line_shape));
                    rView.addItemDecoration(dividerItemDecoration);
                }
            }

            @Override
            public void onFailure(Call<List<object_kompen_terbanyak>> call, Throwable t) {
                Toast.makeText(home_2.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<object_kompen_terbanyak> getAllItemList(){
        List<object_kompen_terbanyak> allItems = new ArrayList<>();
//        allItems.add(new object_kompen_terbanyak("1.","Kurain Aji", "TMD 4 Reg","121 Jam", "SP 3", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("2.","Aji Kurain", "TI 2a Reg","111 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("3.","Kirain Aji", "TI 2b MSU","101 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("4.","Yusuf Aji", "CCIT 4a","91 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("5.","Aji Setya", "TI 4 AeU","90 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("6.","Nugraha Aji", "TMJ 6 Reg","88 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("7.","Aji", "TMD 2 Reg","86 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("8.","Kurain Yusuf", "TMD 4 Reg","83 Jam", "SP 2", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("9.","Ajino Moto", "TI 4 Reg","75 Jam", "SP 1", R.drawable.ava));
//        allItems.add(new object_kompen_terbanyak("10.","Kirain Yusuf", "TI 2c MSU","70 Jam", "SP 1", R.drawable.ava));

        return allItems;
    }
}
