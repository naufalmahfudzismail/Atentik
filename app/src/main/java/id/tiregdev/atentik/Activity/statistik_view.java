package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class statistik_view extends AppCompatActivity {

    TextView openWeb, nama;
    String tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        nama = findViewById(R.id.nama);

        CekToken ct = new CekToken();
        tokens = ct.Cek(this);

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
                Toast.makeText(statistik_view.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialogOpsi();
        setOpenWeb();
    }

    public void setOpenWeb(){
        final String url = "https://www.atentik.com/mahasiswa";
        openWeb = findViewById(R.id.web);
        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                statistik_view.this.finish();
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
                final LayoutInflater factory = LayoutInflater.from(statistik_view.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_more_2, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(statistik_view.this).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.filterPage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
//                        dialogFilter();
                        Toast.makeText(statistik_view.this, "Filter tidak tersedia", Toast.LENGTH_SHORT).show();
                    }
                });

                exitDialogView.findViewById(R.id.jumlah).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Intent i = new Intent(statistik_view.this, home_2.class);
                        startActivity(i);
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void dialogFilter(){
        final LayoutInflater factory = LayoutInflater.from(statistik_view.this);
        final View exitDialogView = factory.inflate(R.layout.dialog_filter, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(statistik_view.this).create();

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
                Toast.makeText(statistik_view.this, "Filter Sukses", Toast.LENGTH_SHORT).show();
            }
        });
        exitDialog.show();
    }
}
