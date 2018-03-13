package id.tiregdev.atentik.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infideap.drawerbehavior.AdvanceDrawerLayout;

import id.tiregdev.atentik.Fragment.about;
import id.tiregdev.atentik.Fragment.data_dosen;
import id.tiregdev.atentik.Fragment.data_mhsw;
import id.tiregdev.atentik.Fragment.home;
import id.tiregdev.atentik.Fragment.notif;
import id.tiregdev.atentik.Fragment.tracking;
import id.tiregdev.atentik.R;

public class activity_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AdvanceDrawerLayout drawer;
    TextView nama, nim, kelas;
    public static Context mainContext;
    private boolean backPressedToExitOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setDrawer();
    }

    public void setDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
//        nama = toolbar.findViewById(R.id.nama);
//        nim =  toolbar.findViewById(R.id.nim);
//        kelas = toolbar.findViewById(R.id.kelas);
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
            case R.id.tracking:
                fragment = new tracking();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                title.setVisibility(View.VISIBLE);
                nTitle.setVisibility(View.GONE);
                title.setText("Tracking Position");
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
                Toast.makeText(this, "logout sukses", Toast.LENGTH_SHORT).show();
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
}
