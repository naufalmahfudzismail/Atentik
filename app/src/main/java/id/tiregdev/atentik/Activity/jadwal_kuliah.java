package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Fragment.jadwal_jumat;
import id.tiregdev.atentik.Fragment.jadwal_kamis;
import id.tiregdev.atentik.Fragment.jadwal_rabu;
import id.tiregdev.atentik.Fragment.jadwal_selasa;
import id.tiregdev.atentik.Fragment.jadwal_senin;
import id.tiregdev.atentik.R;

public class jadwal_kuliah extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        setMore();
        setUpPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here
                jadwal_kuliah.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMore(){
        ImageView more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(jadwal_kuliah.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_more_jadwal, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(jadwal_kuliah.this).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(jadwal_kuliah.this, "Unduh data sukses", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void setUpPager(){
        pager = findViewById(R.id.pager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        setupViewPager(pager);
    }

    private void setupViewPager(ViewPager viewPager) {

        jadwal_kuliah.Adapter adapter = new jadwal_kuliah.Adapter(getSupportFragmentManager());
        adapter.addFragment(new jadwal_senin(), "SEN");
        adapter.addFragment(new jadwal_selasa(), "SEL");
        adapter.addFragment(new jadwal_rabu(), "RAB");
        adapter.addFragment(new jadwal_kamis(), "KAM");
        adapter.addFragment(new jadwal_jumat(), "JUM");

        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
