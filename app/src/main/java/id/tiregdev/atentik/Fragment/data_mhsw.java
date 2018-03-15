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
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_mhsw_dosen;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class data_mhsw extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_mhsw, container, false);
        setupAdaptermhsw_dosen();
        return v;
    }

    public void setupAdaptermhsw_dosen(){
        List<object_mhsw_dosen> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        adapter_mhsw_dosen rcAdapter = new adapter_mhsw_dosen(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

    }

    private List<object_mhsw_dosen> getAllItemList(){
        List<object_mhsw_dosen> allItems = new ArrayList<>();
        allItems.add(new object_mhsw_dosen("Yusuf Setya Nugraha", "TMJ 5","4313010022", "yusufsn@gmail.com"));
        allItems.add(new object_mhsw_dosen("Setya Nugraha", "TMJ 5","4313010022", "yusufsn22@gmail.com"));
        allItems.add(new object_mhsw_dosen("Nugraha", "TMJ 5","4313010022", "yusufsn11@gmail.com"));
        allItems.add(new object_mhsw_dosen("Nugraha Yusuf Setya", "TMJ 5","4313010022", "yusufsn32@gmail.com"));
        allItems.add(new object_mhsw_dosen("Setya Nugraha Yusuf", "TMJ 5","4313010022", "yusufsn52@gmail.com"));
        allItems.add(new object_mhsw_dosen("Yusuf Nugraha", "TMJ 5","4313010022", "yusufsn21@gmail.com"));
        allItems.add(new object_mhsw_dosen("Nugraha Setya", "TMJ 5","4313010022", "yusufsn34@gmail.com"));

        return allItems;
    }
}
