package id.tiregdev.atentik.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Activity.edit_profile;
import id.tiregdev.atentik.Activity.edit_profile_dosen;
import id.tiregdev.atentik.Activity.home_2;
import id.tiregdev.atentik.Activity.jadwal_kuliah_dosen;
import id.tiregdev.atentik.Activity.peraturan;
import id.tiregdev.atentik.Adapter.adapter_cubeacon;
import id.tiregdev.atentik.Adapter.adapter_kompen_terbanyak;
import id.tiregdev.atentik.Adapter.adapter_kompen_terbanyak_dosen;
import id.tiregdev.atentik.Adapter.adapter_log;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_kompen_terbanyak;
import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class home_dosen extends Fragment {

    ImageView filter;
    CardView openWeb, wrapJadwal, cvProfile;
    Button editProfile;
    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_dosen, container, false);
        setUpfilter();
        findId();
        setupAdapterkompen_terbanyak();
        return v;
    }

    public void findId(){
        final String url = "https://www.atentik.com/dashboard";
        openWeb = v.findViewById(R.id.openWeb);
        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        wrapJadwal = v.findViewById(R.id.wrapJadwal);
        wrapJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), jadwal_kuliah_dosen.class);
                startActivity(i);
            }
        });

        cvProfile = v.findViewById(R.id.cvProfile);
        cvProfile.setPreventCornerOverlap(false);
        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), edit_profile_dosen.class);
                startActivity(i);
            }
        });

        editProfile = v.findViewById(R.id.editProfileBtn);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), edit_profile_dosen.class);
                startActivity(i);
            }
        });
    }

    public void setupAdapterkompen_terbanyak(){
        List<object_kompen_terbanyak> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getActivity());

        rView = v.findViewById(R.id.rView);
        rView.setLayoutManager(lLayout);

        adapter_kompen_terbanyak_dosen rcAdapter = new adapter_kompen_terbanyak_dosen(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
        rView.addItemDecoration(dividerItemDecoration);

    }

    private List<object_kompen_terbanyak> getAllItemList(){
        List<object_kompen_terbanyak> allItems = new ArrayList<>();
        allItems.add(new object_kompen_terbanyak("1.","Kurain Aji", "TMD 4 Reg","121 Jam", "SP 3", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("2.","Aji Kurain", "TI 2a Reg","111 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("3.","Kirain Aji", "TI 2b MSU","101 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("4.","Yusuf Aji", "CCIT 4a","91 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("5.","Aji Setya", "TI 4 AeU","90 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("6.","Nugraha Aji", "TMJ 6 Reg","88 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("7.","Aji", "TMD 2 Reg","86 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("8.","Kurain Yusuf", "TMD 4 Reg","83 Jam", "SP 2", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("9.","Ajino Moto", "TI 4 Reg","75 Jam", "SP 1", R.drawable.ava));
        allItems.add(new object_kompen_terbanyak("10.","Kirain Yusuf", "TI 2c MSU","70 Jam", "SP 1", R.drawable.ava));

        return allItems;
    }


    public void setUpfilter(){
        filter = v.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(getActivity());
                final View exitDialogView = factory.inflate(R.layout.dialog_filter, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();

                final Spinner sortTgl = exitDialogView.findViewById(R.id.sortTanggal);
                final Spinner sortBln = exitDialogView.findViewById(R.id.sortBulan);
                final Spinner sortThn = exitDialogView.findViewById(R.id.sortTahun);

                final String tahun[] = {
                        "2018", "2019", "2020", "2021", "2022"
                };

                final String bulan[] = {
                        "Januari", "Februari", "Maret", "Aapril", "Mei",
                        "Juni", "Juli", "Agustus",
                        "September", "Oktober", "November", "Desember"
                };

                final String tanggal[] = {
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
                };

                final ArrayAdapter<String> AdapterTgl = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, tanggal);
                AdapterTgl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sortTgl.setAdapter(AdapterTgl);

                final ArrayAdapter<String> AdapterBln = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, bulan);
                AdapterBln.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sortBln.setAdapter(AdapterBln);

                final ArrayAdapter<String> AdapterThn = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, tahun);
                AdapterThn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sortThn.setAdapter(AdapterThn);

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(getActivity(), "Filter Sukses", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
            }
        });
    }

}

