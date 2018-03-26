package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.crypto.CipherOutputStream;

import id.tiregdev.atentik.R;

public class set_jadwal_masuk_dosen extends AppCompatActivity {

    AppCompatRadioButton dosenTelat;;
    RadioGroup RG;


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
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_jadwal_masuk_dosen.this.finish();
                Toast.makeText(set_jadwal_masuk_dosen.this, "Jadwal berhasil di set", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRG(){
        final EditText setJamMasuk = findViewById(R.id.setJamMasuk);
        dosenTelat = findViewById(R.id.dosenTelat);
//        dosenTelat.setSupportButtonTintList(ContextCompat.getColorStateList(this,R.color.colorAccent));
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
