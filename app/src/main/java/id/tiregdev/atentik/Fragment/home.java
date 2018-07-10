package id.tiregdev.atentik.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import id.tiregdev.atentik.Activity.AbsenCubeacon;
import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Activity.edit_profile;
import id.tiregdev.atentik.Activity.home_2;
import id.tiregdev.atentik.Activity.jadwal_kuliah;
import id.tiregdev.atentik.Activity.peraturan;
import id.tiregdev.atentik.Activity.tracking_mhsw;
import id.tiregdev.atentik.Adapter.adapter_cubeacon;
import id.tiregdev.atentik.Adapter.adapter_log;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.Model.object_total;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 13/03/18.
 */

public class home extends Fragment {
    TextView nextData, peraturans, email, tlp, imei, SP, tgl, totalMasuk, totalTelat, totalKompen, totalUang;
    CardView editProfile, tracking;
    Button editProfileBtn;
    RecyclerView rView;
    RelativeLayout absen, jadwal;
    LinearLayoutManager  lLayout;
    View v;
    String tokens;
    Locale localeID = new Locale("in", "ID");
    String jam = new SimpleDateFormat("HH:mm:ss ZZZZ", localeID).format(new Date());
    String tanggal = new SimpleDateFormat("dd MMMM yyyy", localeID).format(new Date());
    String tanggals = new SimpleDateFormat("dd-MM-yyyy", localeID).format(new Date());
    String hari = new SimpleDateFormat("EEEE", localeID).format(new Date());
    String haritanggal = hari + ", " + tanggal;
    Context context = getActivity();
    private SwipeRefreshLayout swipeContainer;
    List<object_log> logs = new ArrayList<>();
    ImageView foto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this.getActivity());
        findId();
        setupAdapterLog();
        setupSwipe();

        return v;
    }

    public void setupSwipe(){
        // Lookup the swipe container view
        swipeContainer = v.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setOnRefreshListener(this);
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        Toast.makeText(getActivity(), "Works!", Toast.LENGTH_LONG).show();
                        // Stop animation (This will be after 3 seconds)
                        swipeContainer.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void findId(){
        jadwal = v.findViewById(R.id.jadwal);
        nextData = v.findViewById(R.id.nextData);
        peraturans = v.findViewById(R.id.peraturan);
        editProfile = v.findViewById(R.id.editProfile);
        foto = v.findViewById(R.id.foto);
        editProfile.setPreventCornerOverlap(false);
        absen = v.findViewById(R.id.absen);
        email = v.findViewById(R.id.email);
        tlp = v.findViewById(R.id.tlp);
        imei = v.findViewById(R.id.imei);
        SP = v.findViewById(R.id.SP);
        tgl = v.findViewById(R.id.tgl);
        totalMasuk = v.findViewById(R.id.totalMasuk);
        totalTelat = v.findViewById(R.id.totalTelat);
        totalKompen = v.findViewById(R.id.totalKompen);
        totalUang = v.findViewById(R.id.totalUang);
        tgl.setText(haritanggal);
        tracking= v.findViewById(R.id.tracking);
        tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), tracking_mhsw.class);
                startActivity(i);
            }
        });

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_mahasiswa> call = client.profilMahasiswa("Bearer " + tokens);
        call.enqueue(new Callback<object_mahasiswa>() {
            @Override
            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
               if(response.isSuccessful())
                {
                    email.setText(response.body().getEmail());
                    tlp.setText(response.body().getTlp());
                    imei.setText(response.body().getImei_hp());
                    SP.setText(response.body().getStatus_sp());
                    Glide.with(getContext()).load("https://atentik.id/assets/img/faces/" + response.body().getPhoto()).into(foto);
                }
            }

            @Override
            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<object_total> calls = client.totalAbsensiMahasiswa("Bearer " + tokens, tanggals);
        calls.enqueue(new Callback<object_total>() {
            @Override
            public void onResponse(Call<object_total> call, Response<object_total> response) {
                if(response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        totalMasuk.setText(String.valueOf(response.body().getTotal_masuk()));
                        totalTelat.setText(String.valueOf(response.body().getTotal_telat()));
                        totalKompen.setText(String.valueOf(response.body().getTotal_kompen()));
                        totalUang.setText(String.valueOf(response.body().getBiaya()) + " IDR");
                    }
                    else
                    {
                        totalMasuk.setText("0");
                        totalTelat.setText("0");
                        totalKompen.setText("0");
                        totalUang.setText("0 IDR");
                    }
                }
            }

            @Override
            public void onFailure(Call<object_total> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), jadwal_kuliah.class);
                startActivity(i);
            }
        });

        editProfileBtn = v.findViewById(R.id.editProfileBtn);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), edit_profile.class);
                startActivity(i);
            }
        });

        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                Intent i = new Intent(getActivity(), AbsenCubeacon.class);
                startActivity(i);
//                final LayoutInflater factory = LayoutInflater.from(getActivity());
//                final View exitDialogView = factory.inflate(R.layout.dialog_absen, null);
//                final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();
//                final LinearLayoutManager lLayoutCube;
//                final RecyclerView rViewCube;
//                final List<object_cubeacon> rowListItemCube = getAllItemLists();
//                lLayoutCube = new LinearLayoutManager(exitDialogView.getContext());
//
//                rViewCube = exitDialogView.findViewById(R.id.rViewCube);
//                rViewCube.setLayoutManager(lLayoutCube);
//
//                adapter_cubeacon rcAdapter = new adapter_cubeacon(exitDialogView.getContext(), rowListItemCube);
//                rViewCube.setAdapter(rcAdapter);
//
//                exitDialog.setView(exitDialogView);
//                exitDialogView.findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
//                        Call<object_log_mahasiswa> call = client.absenMahasiswa("Bearer " + tokens, hari, jam, tanggals);
//                        call.enqueue(new Callback<object_log_mahasiswa>() {
//                            @Override
//                            public void onResponse(Call<object_log_mahasiswa> call, Response<object_log_mahasiswa> response) {
//                                if(response.isSuccessful())
//                                {
//                                    if(response.body().getJam_hadir().toString().equals("-"))
//                                        Toast.makeText(getActivity(), "Anda berhasil absen pada jam " + response.body().getKompen(), Toast.LENGTH_SHORT).show();
//                                    else
//                                        Toast.makeText(getActivity(), "Anda berhasil absen pada jam " + response.body().getJam_hadir(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<object_log_mahasiswa> call, Throwable t) {
//                                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                        Toast.makeText(getActivity(), "Scan Sukses", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                exitDialog.show();
            }

            private List<object_cubeacon> getAllItemLists(){
                List<object_cubeacon> allItems = new ArrayList<>();

//                allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_289"));
//                allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_280"));

                return allItems;
            }
        });

        nextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), home_2.class);
                startActivity(i);
            }
        });

        peraturans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), peraturan.class);
                startActivity(i);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), edit_profile.class);
                startActivity(i);
            }
        });
    }

    public void setupAdapterLog(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_log>> call = client.logKehadiranMahasiswa("Bearer " + tokens, tanggals);
        call.enqueue(new Callback<List<object_log>>() {
            @Override
            public void onResponse(Call<List<object_log>> call, Response<List<object_log>> response) {
                if(response.isSuccessful())
                {
                    List<object_log> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        logs.add(new object_log(simpan.get(i).getNama_matkul(),simpan.get(i).getRuangan(),simpan.get(i).getJam_hadir(), simpan.get(i).getWaktu_telat(), simpan.get(i).getKompen()));
                    }
                    List<object_log> rowListItem = logs;
                    lLayout = new LinearLayoutManager(getContext());

                    rView = v.findViewById(R.id.rView);
                    rView.setLayoutManager(lLayout);

                    adapter_log rcAdapter = new adapter_log(getContext(), rowListItem);
                    rView.setAdapter(rcAdapter);

//                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
//                    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
//                    rView.addItemDecoration(dividerItemDecoration);
                }
            }

            @Override
            public void onFailure(Call<List<object_log>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<object_log> getAllItemList(){
        List<object_log> allItems = new ArrayList<>();
//        allItems.add(new object_log(getResources().getString(R.string.matkul1), getResources().getString(R.string.aa301),"08.10", "10", "1"));
//        allItems.add(new object_log(getResources().getString(R.string.matkul2), getResources().getString(R.string.aa301),"10.15", "15", "1"));
//        allItems.add(new object_log(getResources().getString(R.string.matkul3), getResources().getString(R.string.aa302),"13.00", "0", "0"));
//        allItems.add(new object_log(getResources().getString(R.string.matkul1), getResources().getString(R.string.aa301),"08.10", "10", "1"));
//        allItems.add(new object_log(getResources().getString(R.string.matkul2), getResources().getString(R.string.aa301),"10.15", "15", "1"));

        return allItems;
    }

}

