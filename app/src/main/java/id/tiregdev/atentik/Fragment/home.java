package id.tiregdev.atentik.Fragment;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Activity.edit_profile;
import id.tiregdev.atentik.Activity.home_2;
import id.tiregdev.atentik.Activity.peraturan;
import id.tiregdev.atentik.Adapter.adapter_cubeacon;
import id.tiregdev.atentik.Adapter.adapter_log;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.Model.object_log;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class home extends Fragment {

    TextView nextData, peraturans;
    Button editProfile;
    RecyclerView rView;
    RelativeLayout absen;
    LinearLayoutManager  lLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        CardView cv = v.findViewById(R.id.cv);
        cv.setPreventCornerOverlap(false);
        findId();
        setupAdapterLog();
        return v;
    }


    public void findId(){
        nextData = v.findViewById(R.id.nextData);
        peraturans = v.findViewById(R.id.peraturan);
        editProfile = v.findViewById(R.id.editProfile);
        absen = v.findViewById(R.id.absen);

        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                final LayoutInflater factory = LayoutInflater.from(getActivity());
                final View exitDialogView = factory.inflate(R.layout.dialog_absen, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();
                final LinearLayoutManager lLayoutCube;
                final RecyclerView rViewCube;
                final List<object_cubeacon> rowListItemCube = getAllItemLists();
                lLayoutCube = new LinearLayoutManager(exitDialogView.getContext());

                rViewCube = exitDialogView.findViewById(R.id.rViewCube);
                rViewCube.setLayoutManager(lLayoutCube);

                adapter_cubeacon rcAdapter = new adapter_cubeacon(exitDialogView.getContext(), rowListItemCube);
                rViewCube.setAdapter(rcAdapter);

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Scan Sukses", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
            }

            private List<object_cubeacon> getAllItemLists(){
                List<object_cubeacon> allItems = new ArrayList<>();
                allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_289"));
                allItems.add(new object_cubeacon(getResources().getString(R.string.aa301),"CBN_280"));

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
        List<object_log> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rView);
        rView.setLayoutManager(lLayout);

        adapter_log rcAdapter = new adapter_log(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
        rView.addItemDecoration(dividerItemDecoration);
    }

    private List<object_log> getAllItemList(){
        List<object_log> allItems = new ArrayList<>();
        allItems.add(new object_log(getResources().getString(R.string.matkul1), getResources().getString(R.string.aa301),"08.10", "10"));
        allItems.add(new object_log(getResources().getString(R.string.matkul2), getResources().getString(R.string.aa301),"10.15", "15"));
        allItems.add(new object_log(getResources().getString(R.string.matkul3), getResources().getString(R.string.aa302),"13.00", "0"));
        allItems.add(new object_log(getResources().getString(R.string.matkul1), getResources().getString(R.string.aa301),"08.10", "10"));
        allItems.add(new object_log(getResources().getString(R.string.matkul2), getResources().getString(R.string.aa301),"10.15", "15"));

        return allItems;
    }


//    public void setupAdapterCubecon(){
//
//    }


}

