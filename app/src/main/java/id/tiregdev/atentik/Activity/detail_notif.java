package id.tiregdev.atentik.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import id.tiregdev.atentik.R;

public class detail_notif extends AppCompatActivity {

    TextView judul, tgl, isi;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notif);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setTitle("Detail Notification");

        sharedpreferences = getSharedPreferences("notif", Context.MODE_PRIVATE);

        String judull = sharedpreferences.getString("judul", null);
        String isii = sharedpreferences.getString("isi", null);
        String waktuu = sharedpreferences.getString("waktu", null);

        judul = findViewById(R.id.judul);
        tgl = findViewById(R.id.tgl);
        isi = findViewById(R.id.isi);

        judul.setText(judull);
        tgl.setText(waktuu);
        isi.setText(isii);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                detail_notif.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
