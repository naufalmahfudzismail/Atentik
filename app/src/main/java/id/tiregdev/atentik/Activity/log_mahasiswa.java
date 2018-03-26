package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_log_mahasiswa;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.R;

public class log_mahasiswa extends AppCompatActivity {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    TextView save;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mahasiswa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        setupAdapterLog();
        setSave();
    }

    public void setSave(){
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log_mahasiswa.this.finish();
                Toast.makeText(log_mahasiswa.this, "Save sukses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                log_mahasiswa.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupAdapterLog(){
        List<object_log_mahasiswa> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(log_mahasiswa.this);

        rView = findViewById(R.id.rView);
        rView.setLayoutManager(lLayout);

        adapter_log_mahasiswa rcAdapter = new adapter_log_mahasiswa(log_mahasiswa.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<object_log_mahasiswa> getAllItemList(){
        List<object_log_mahasiswa> allItems = new ArrayList<>();
        allItems.add(new object_log_mahasiswa("Muhammad Hafizh", "4314010034","08.10", "10", "1"));
        allItems.add(new object_log_mahasiswa("Ilham Al Fajri", "4314010023","10.15", "15", "1"));
        allItems.add(new object_log_mahasiswa("Kadek Pandu", "4314010013","13.00", "0", "0"));
        allItems.add(new object_log_mahasiswa("Rifqie Fadlurrahman", "4414010036","08.10", "10", "1"));
        allItems.add(new object_log_mahasiswa("Zaky Nuralifi", "4414010034","10.15", "15", "1"));

        return allItems;
    }
}
