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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.dosen_adapter_notif;
import id.tiregdev.atentik.Model.object_notif;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class notif_dosen extends Fragment {

    View v, child;
    RecyclerView rView;
    LinearLayoutManager lLayout;
    LinearLayout wrapNotif;
    TextView judul;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notif, container, false);
        child = getLayoutInflater().inflate(R.layout.list_notif, null);
        judul = child.findViewById(R.id.judul);
        judul.setTextColor(getResources().getColor(R.color.putih));
        setupAdapter();
        changeColor();
        return v;
    }
    public void changeColor(){
        wrapNotif = v.findViewById(R.id.wrapNotif);
        wrapNotif.setBackgroundColor(getResources().getColor(R.color.AbuBG));
    }

    public void setupAdapter(){
        List<object_notif> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        dosen_adapter_notif rcAdapter = new dosen_adapter_notif(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
        rView.addItemDecoration(dividerItemDecoration);
    }

    private List<object_notif> getAllItemList(){
        List<object_notif> allItems = new ArrayList<>();
        allItems.add(new object_notif("Update Apss", getResources().getString(R.string.sampleTxt),"02/02/2018"));
        allItems.add(new object_notif("Info Reward", getResources().getString(R.string.sampleTxt),"01/02/2018"));
        allItems.add(new object_notif("Info SP", getResources().getString(R.string.sampleTxt),"01/02/2018"));
        allItems.add(new object_notif("Teguran", getResources().getString(R.string.sampleTxt),"15/01/2018"));

        return allItems;
    }
}
