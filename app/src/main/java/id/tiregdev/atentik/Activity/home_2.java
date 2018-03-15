package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import id.tiregdev.atentik.R;

public class home_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        dialogOpsi();
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
}
