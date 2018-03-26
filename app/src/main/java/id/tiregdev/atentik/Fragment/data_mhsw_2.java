package id.tiregdev.atentik.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.dosen_adapter_mhsw_dosen;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class data_mhsw_2 extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;
    SearchView searchView;
    RelativeLayout mainLayout, wrapSearchDosen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_mhsw, container, false);
        setupAdaptermhsw_dosen();
        setSearch();
        dialogOpsi();
        changeColor();
        return v;
    }

    public void changeColor(){
        mainLayout = v.findViewById(R.id.mainLayout);
        mainLayout.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        wrapSearchDosen = v.findViewById(R.id.wrapSearchDosen);
        wrapSearchDosen.setBackgroundColor(getResources().getColor(R.color.AbuBG));
    }

    public void setSearch(){
        searchView = v.findViewById(R.id.search_bar);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.putih));
        searchEditText.setHintTextColor(getResources().getColor(R.color.background));
    }

    public void dialogOpsi() {
        ImageView more = v.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater factory = LayoutInflater.from(getActivity());
                final View exitDialogView = factory.inflate(R.layout.dialog_more_dsn_mhsw, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.filterKelas).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        dialogFilter();
                    }
                });

                exitDialogView.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(getActivity(), "Unduh data sukses", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void dialogFilter(){
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View exitDialogView = factory.inflate(R.layout.dialog_filter_dsn_mhsw, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();
        final Spinner sortKelas = exitDialogView.findViewById(R.id.sortKelas);

        final String kelas[] = {
                "TI 2a Reg", "TI 2b Reg", "TMJ 2 Reg", "TMD 2 Reg",
                "TI 4a Reg", "TI 4b Reg", "TMJ 4 Reg", "TMD 4 Reg",
                "TI 6 Reg", "TMJ 6 Reg", "TMD 6 Reg",
                "TI 8 Reg", "TMJ 8 Reg", "TMD 6 Reg"
        };

        final ArrayAdapter<String> AdapterKls = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, kelas);
        AdapterKls.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortKelas.setAdapter(AdapterKls);

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

    public void setupAdaptermhsw_dosen(){
        List<object_mhsw_dosen> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        dosen_adapter_mhsw_dosen rcAdapter = new dosen_adapter_mhsw_dosen(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        rView.setNestedScrollingEnabled(false);

    }

    private List<object_mhsw_dosen> getAllItemList(){
        List<object_mhsw_dosen> allItems = new ArrayList<>();
        allItems.add(new object_mhsw_dosen("Yusuf Setya Nugraha", "TMJ 5","4313010022", "yusufsn@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Setya Nugraha", "TMJ 5","4313010022", "yusufsn22@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Nugraha", "TMJ 5","4313010022", "yusufsn11@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Nugraha Yusuf Setya", "TMJ 5","4313010022", "yusufsn32@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Setya Nugraha Yusuf", "TMJ 5","4313010022", "yusufsn52@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Yusuf Nugraha", "TMJ 5","4313010022", "yusufsn21@gmail.com", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Nugraha Setya", "TMJ 5","4313010022", "yusufsn34@gmail.com", R.drawable.ava));

        return allItems;
    }
}