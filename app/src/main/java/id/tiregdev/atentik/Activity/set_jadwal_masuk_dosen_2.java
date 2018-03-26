package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import id.tiregdev.atentik.R;

public class set_jadwal_masuk_dosen_2 extends AppCompatActivity {

    RadioButton dosenTelat, dosenTdkMasuk, dosenTdkMasukMhswMasuk;
    RadioGroup RG;
    LinearLayout wrapSetJadwal;
    Toolbar toolbar;
    TextView namaMatkul, namaDosen, namaTxt, namaUpdate, waktuUpdate;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_jadwal_masuk_dosen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        setSubmit();
        setRG();
        changeColor();
    }

    public void changeColor(){
        wrapSetJadwal = findViewById(R.id.wrapSetJadwalMasukDosen);
        wrapSetJadwal.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        namaMatkul = findViewById(R.id.namaMatkul);
        namaMatkul.setTextColor(getResources().getColor(R.color.putih));

        namaDosen = findViewById(R.id.namaDosen);
        namaDosen.setTextColor(getResources().getColor(R.color.putih));

        namaTxt = findViewById(R.id.namaTxt);
        namaTxt.setTextColor(getResources().getColor(R.color.putih));

        namaUpdate = findViewById(R.id.namaUpdate);
        namaUpdate.setTextColor(getResources().getColor(R.color.toska));

        waktuUpdate = findViewById(R.id.waktuUpdate);
        waktuUpdate.setTextColor(getResources().getColor(R.color.toska));

        dosenTelat.setTextColor(getResources().getColor(R.color.putih));

        dosenTdkMasuk = findViewById(R.id.dosenTidakMasuk);
        dosenTdkMasuk.setTextColor(getResources().getColor(R.color.putih));

        dosenTdkMasukMhswMasuk = findViewById(R.id.dosenTidakMasukMhswMasuk);
        dosenTdkMasukMhswMasuk.setTextColor(getResources().getColor(R.color.putih));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        submit = findViewById(R.id.submit);
        submit.setBackgroundResource(R.drawable.bg_button_dark);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                set_jadwal_masuk_dosen_2.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSubmit(){
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_jadwal_masuk_dosen_2.this.finish();
                Toast.makeText(set_jadwal_masuk_dosen_2.this, "Jadwal berhasil di set", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRG(){
        final EditText setJamMasuk = findViewById(R.id.setJamMasuk);
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
