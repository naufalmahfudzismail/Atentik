package id.tiregdev.atentik.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import id.tiregdev.atentik.R;

public class detail_notif_dosen extends AppCompatActivity {

    ScrollView wrapDetailNotif;
    Toolbar toolbar;
    TextView judul, tgl, isi, toolbar_title;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notif);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        changeColor();
    }

    public void changeColor(){
        wrapDetailNotif = findViewById(R.id.wrapDetailNotif);
        wrapDetailNotif.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.AbuBG));
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setVisibility(View.VISIBLE);
        toolbar_title.setText("Detail Notification");

        judul = findViewById(R.id.judul);
        judul.setTextColor(getResources().getColor(R.color.pink));

        tgl = findViewById(R.id.tgl);
        tgl.setTextColor(getResources().getColor(R.color.putih));

        isi = findViewById(R.id.isi);
        isi.setTextColor(getResources().getColor(R.color.putih));

        sharedpreferences = getSharedPreferences("notif", Context.MODE_PRIVATE);

        String judull = sharedpreferences.getString("judul", null);
        String isii = sharedpreferences.getString("isi", null);
        String waktuu = sharedpreferences.getString("waktu", null);

        judul.setText(judull);
        tgl.setText(waktuu);
        isi.setText(isii);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                detail_notif_dosen.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
