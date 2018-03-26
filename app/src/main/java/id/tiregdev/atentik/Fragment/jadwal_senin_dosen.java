package id.tiregdev.atentik.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_jadwal;
import id.tiregdev.atentik.Adapter.adapter_jadwal_dosen;
import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class jadwal_senin_dosen extends Fragment {

    RecyclerView rView;
    LinearLayoutManager lLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_jadwal, container, false);
        setupAdapter();
        return v;
    }

    public void setupAdapter(){
        List<object_jadwal> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rView);
        rView.setLayoutManager(lLayout);

        adapter_jadwal_dosen rcAdapter = new adapter_jadwal_dosen(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<object_jadwal> getAllItemList(){
        List<object_jadwal> allItems = new ArrayList<>();
        allItems.add(new object_jadwal(getString(R.string.mk1), "Dr. Geraldi Joni STr., MI.","08.00 - 10.30", "4","AA301"));
        allItems.add(new object_jadwal(getString(R.string.mk2), "Kurain Jodi SHum., MI.","10.30 - 12.00", "3","AA301"));

        return allItems;
    }
}
