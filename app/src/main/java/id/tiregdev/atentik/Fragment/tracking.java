package id.tiregdev.atentik.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_tracking;
import id.tiregdev.atentik.Model.object_lokasi;
import id.tiregdev.atentik.Model.object_tracking;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class tracking extends Fragment {

    View v;
    RecyclerView rView;
    LinearLayoutManager lLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tracking, container, false);
        setupAdapter();
        return v;
    }
    
    public void setupAdapter(){
        List<object_lokasi> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        adapter_tracking rcAdapter = new adapter_tracking(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<object_lokasi> getAllItemList(){
        List<object_lokasi> allItems = new ArrayList<>();
//        allItems.add(new object_tracking("Pak Fauzi", "0723110000023","Sedang berada di AA301 dari jam 07.30"));
//        allItems.add(new object_tracking("Hafizh", "4314010030","Sedang berada di AA305 dari jam 08.30"));
//        allItems.add(new object_tracking("Pak Mauldy", "012337110000023","Sedang berada di AA302 dari jam 07.30"));
//        allItems.add(new object_tracking("Toriq","4314010021","Sedang berada di AA204 dari jam 10.30"));

        return allItems;
    }
}
