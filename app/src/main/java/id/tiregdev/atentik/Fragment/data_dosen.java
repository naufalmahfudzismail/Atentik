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

import id.tiregdev.atentik.Adapter.adapter_mhsw_dosen;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class data_dosen extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_dosen, container, false);
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
        allItems.add(new object_mhsw_dosen("Fajar Hadi Putranto", "Dosen Tetap Non PNS","213390297889129", "08912789799"));
        allItems.add(new object_mhsw_dosen("Setya Putranto", "Dosen PNS","545390297889129", "08912789729"));
        allItems.add(new object_mhsw_dosen("Hadi Nugraha", "Dosen PNS","6763390297889129", "08912789739"));
        allItems.add(new object_mhsw_dosen("Putra Yusuf", "Dosen Tetap Dosen PNS","441390297889129", "08912789749"));
        allItems.add(new object_mhsw_dosen("Setya Nugraha Yusuf", "Freelance","734390297889129", "08912789759"));
        allItems.add(new object_mhsw_dosen("Nugraha Aji", "Freelance","217390297889129", "08912789796"));
        allItems.add(new object_mhsw_dosen("Al Khawarizm", "Dosen PNS","813390297889129", "08912789779"));

        return allItems;
    }
}
