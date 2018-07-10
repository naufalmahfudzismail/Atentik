package id.tiregdev.atentik.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.eyro.cubeacon.CBBeacon;
import com.eyro.cubeacon.CBRangingListener;
import com.eyro.cubeacon.CBRegion;
import com.eyro.cubeacon.CBServiceListener;
import com.eyro.cubeacon.Cubeacon;
import com.eyro.cubeacon.LogLevel;
import com.eyro.cubeacon.Logger;
import com.eyro.cubeacon.SystemRequirementManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import id.tiregdev.atentik.Adapter.adapter_cubeacon;
import id.tiregdev.atentik.Adapter.adapter_notif;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_notif;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenCubeacon extends AppCompatActivity implements CBRangingListener, CBServiceListener{
    private static final String TAG = AbsenCubeacon.class.getSimpleName();
    private Cubeacon cubeacon;
    private SimpleAdapter adapter;
    private List<Map<String, String>> data;
    private List<CBBeacon> beacons;
    adapter_cubeacon rcAdapter;
    List<object_cubeacon> cube = new ArrayList<>();
    RecyclerView rView;
    LinearLayoutManager lLayout;
    String tokens;
    BluetoothAdapter bluetoothadapter;
    int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen_cubeacon);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setTitle("Absensi");
        setupAdapter();
        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        // assign view
//        listView = findViewById(R.id.listview);

        // set default adapter
        String[] from = new String[]{"title", "subtitle"};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        data = new ArrayList<>();
        adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, from, to);

        // set adapter to listview
//        listView.setAdapter(adapter);

        // set listview on item click listener
//        listView.setOnItemClickListener(this);

        // set Cubeacon SDK log level to verbose mode
        Logger.setLogLevel(LogLevel.VERBOSE);

        // enable background power saver to save battery life up to 60%
        Cubeacon.setBackgroundPowerSaver(true);

        // initializing Cubeacon SDK
        Cubeacon.initialize(this);

        // assign local instance of Cubeacon manager
        cubeacon = Cubeacon.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // check all requirement like is BLE available, is bluetooth on/off,
        // location service for Android API 23 or later
        if (SystemRequirementManager.checkAllRequirementUsingDefaultDialog(this)) {
            // connecting to Cubeacon service when all requirements completed
            cubeacon.connect(this);
            // disable background mode, because we're going to use full
            // scanning resource in foreground mode
            cubeacon.setBackgroundMode(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // enable background mode when this activity paused
        cubeacon.setBackgroundMode(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_cubeacon> callz = client.lokasiMahasiswa("Bearer " + tokens, "kosong");
        callz.enqueue(new Callback<object_cubeacon>() {
            @Override
            public void onResponse(Call<object_cubeacon> call, Response<object_cubeacon> response) {
                if(response.isSuccessful())
                {
//                            Toast.makeText(activity_main.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<object_cubeacon> call, Throwable t) {
//                        Toast.makeText(activity_main.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // disconnect from Cubeacon service when this activity destroyed
        cubeacon.disconnect(this);
    }

    @Override
    public void didRangeBeaconsInRegion(final List<CBBeacon> beacons, CBRegion region) {
        this.beacons = beacons;
        String title, subtitle;
        Map<String, String> map;

        // clear data
        data.clear();
        cube.clear();
        for (final CBBeacon beacon : beacons) {
                        cube.add(new object_cubeacon("AA301", beacon.getName(), beacon.getProximity().toString()));

        }
        // update view using runnable
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                times++;
                if(times > 2) {
                    times = 0;
                    rcAdapter.notifyDataSetChanged();
                }
                bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
                if(!bluetoothadapter.isEnabled())
                {
                    AbsenCubeacon.this.finish();
                }
//                if (getSupportActionBar() != null) {
//                    getSupportActionBar().setSubtitle("Ranged beacon : " + beacons.size());
//                }
            }
        });
    }

    @Override
    public void onBeaconServiceConnect() {
        // add ranging listener implementation
        cubeacon.addRangingListener(this);
        try {
            // create a new region for ranging beacons
            CBRegion region = new CBRegion("com.eyro.cubeacon.ranging_region");
            // start ranging beacons using region
            cubeacon.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
            Log.e(TAG, "Error while start ranging beacon, " + e);
        }
    }


    public void setupAdapter(){
        List<object_cubeacon> rowListItem = cube;
        lLayout = new LinearLayoutManager(AbsenCubeacon.this);

        rView = findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        rcAdapter = new adapter_cubeacon(AbsenCubeacon.this, cube);
        rView.setAdapter(rcAdapter);

    }

    private List<object_cubeacon> getAllItemList(){
        List<object_cubeacon> allItems = new ArrayList<>();
//        allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_289"));
//        allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_280"));

        return allItems;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                AbsenCubeacon.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
