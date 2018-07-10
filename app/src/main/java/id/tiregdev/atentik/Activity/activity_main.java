package id.tiregdev.atentik.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.eyro.cubeacon.CBBeacon;
import com.eyro.cubeacon.CBRangingListener;
import com.eyro.cubeacon.CBRegion;
import com.eyro.cubeacon.CBServiceListener;
import com.eyro.cubeacon.Cubeacon;
import com.eyro.cubeacon.LogLevel;
import com.eyro.cubeacon.Logger;
import com.eyro.cubeacon.SystemRequirementManager;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.pusher.pushnotifications.PushNotifications;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Fragment.about;
import id.tiregdev.atentik.Fragment.data_dosen;
import id.tiregdev.atentik.Fragment.data_mhsw;
import id.tiregdev.atentik.Fragment.home;
import id.tiregdev.atentik.Fragment.notif;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CBRangingListener, CBServiceListener {

    private AdvanceDrawerLayout drawer;
    Cubeacon cubeacon;
    SimpleAdapter adapter;
    List<CBBeacon> beacons;
    List<Map<String, String>> data;
    private static final String TAG = activity_main.class.getSimpleName();
    TextView nama, nimOrNip, kelasOrStatus, namaheader, nimheader;
    String tokens;
    BluetoothAdapter bluetoothadapter;
    int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        data = new ArrayList<>();

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View includedLayout = findViewById(R.id.appsbar);
        NavigationView headerLayout = findViewById(R.id.nav_view);
        View headerView = headerLayout.getHeaderView(0);
        nama = includedLayout.findViewById(R.id.nama);
        nimOrNip = includedLayout.findViewById(R.id.nimOrNip);
        kelasOrStatus = includedLayout.findViewById(R.id.kelasOrStatus);
        namaheader = headerView.findViewById(R.id.nama);
        nimheader = headerView.findViewById(R.id.nim);

        CekToken ct = new CekToken();
        tokens = ct.Cek(this);

        // set Cubeacon SDK log level to verbose mode
        Logger.setLogLevel(LogLevel.VERBOSE);

        // enable background power saver to save battery life up to 60%
        Cubeacon.setBackgroundPowerSaver(true);

        // initializing Cubeacon SDK
        Cubeacon.initialize(this);

        cubeacon = Cubeacon.getInstance();

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_mahasiswa> call = client.profilMahasiswa("Bearer " + tokens);
        call.enqueue(new Callback<object_mahasiswa>() {
            @Override
            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                if(response.isSuccessful())
                {
                    nama.setText(response.body().getNama());
                    nimOrNip.setText(response.body().getNim());
                    kelasOrStatus.setText(response.body().getKelas());
                    namaheader.setText(response.body().getNama());
                    nimheader.setText(response.body().getNim());
                    PushNotifications.start(getApplicationContext(), "937b430b-6f6b-4a25-b435-2f98ed561236");
                    PushNotifications.subscribe("alluser");
                    PushNotifications.subscribe("allmahasiswa");
                    PushNotifications.subscribe(nimOrNip.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                Toast.makeText(activity_main.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        Toast.makeText(this, "tes", Toast.LENGTH_SHORT).show();

        setDrawer();
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
        // update view using runnable
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
                times++;
                if(times > 8)
                {
                    times = 0;
                    for (final CBBeacon beacon : beacons) {
                String isi = "kosong";
                if(beacon.getProximity().toString().equals("NEAR") || beacon.getProximity().toString().equals("IMMEDIATE"))
                {
                    isi = beacon.getName();
                }
                AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                Call<object_cubeacon> callz = client.lokasiMahasiswa("Bearer " + tokens, isi);
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
//            }
//            Toast.makeText(this, beacon.getName() + " " + beacon.getProximity(), Toast.LENGTH_SHORT).show();
                    }
                }
                if(!bluetoothadapter.isEnabled())
                {
                    activity_main.this.finish();
                }
//                Toast.makeText(activity_main.this, isi, Toast.LENGTH_SHORT).show();
//                rcAdapter.notifyDataSetChanged();
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

    public void setDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
//        nama = toolbar.findViewById(R.id.nama);
//        nimOrNip =  toolbar.findViewById(R.id.nimOrNip);
//        kelasOrStatus = toolbar.findViewById(R.id.kelasOrStatus);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.setViewScale(Gravity.START, 0.9f);
        drawer.setRadius(Gravity.START, 35);
        drawer.setViewElevation(Gravity.START, 20);

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Apa anda yakin ingin keluar aplikasi ini?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finishAffinity();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
//        }
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        TextView title = findViewById(R.id.toolbarTitle);
        RelativeLayout nTitle = findViewById(R.id.wrapperToolbar);

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new home();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.GONE);
                nTitle.setVisibility(View.VISIBLE);
                break;
            case R.id.notif:
                fragment = new notif();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.VISIBLE);
                nTitle.setVisibility(View.GONE);
                title.setText("Notification");
                break;
            case R.id.data_mhsw:
                fragment = new data_mhsw();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.VISIBLE);
                nTitle.setVisibility(View.GONE);
                title.setText("Data Mahasiswa");
                break;
            case R.id.data_dosen:
                fragment = new data_dosen();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.VISIBLE);
                nTitle.setVisibility(View.GONE);
                title.setText("Data Dosen");
                break;
            case R.id.about:
                fragment = new about();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.VISIBLE);
                nTitle.setVisibility(View.GONE);
                title.setText("About apps");
                break;
            case R.id.logout:

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

                Call<object_mahasiswa> call = client.logoutMahasiswa("Bearer " + tokens);
                call.enqueue(new Callback<object_mahasiswa>() {
                    @Override
                    public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(activity_main.this, "Logout Sukses", Toast.LENGTH_SHORT).show();
                            deletesFile("loginnama");
                            deletesFile("loginpass");
//                            Intent i = new Intent(activity_main.this, login.class);
//                            startActivity(i);
                            PushNotifications.unsubscribeAll();
                            activity_main.this.finish();
                        }
                        else
                            Toast.makeText(activity_main.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                        Toast.makeText(activity_main.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void deletesFile(String filename) {
        File file = new File(getFilesDir(), filename);
        file.delete();
    }
}
