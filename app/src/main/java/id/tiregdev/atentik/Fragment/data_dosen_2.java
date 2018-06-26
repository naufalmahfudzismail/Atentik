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

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Adapter.dosen_adapter_dosen;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 13/03/18.
 */

public class data_dosen_2 extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;
    SearchView searchView;
    String tokens;
    List<object_mhsw_dosen> dosen = new ArrayList<>();
    List<object_mhsw_dosen> dosen2 = new ArrayList<>();
    dosen_adapter_dosen rcAdapter;
    RelativeLayout mainLayout, wrapSearchDosen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_dosen, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this.getActivity());
        setupAdaptermhsw_dosen();
        setSearch();
        dialogOpsi();
        changeColor();
        return v;
    }

    public void setSearch(){
        searchView = v.findViewById(R.id.search_bar);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.putih));
        searchEditText.setHintTextColor(getResources().getColor(R.color.background));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    public void changeColor(){
        mainLayout = v.findViewById(R.id.mainLayout);
        mainLayout.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        wrapSearchDosen = v.findViewById(R.id.wrapSearchDosen);
        wrapSearchDosen.setBackgroundColor(getResources().getColor(R.color.AbuBG));
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
        final Spinner sortJabatan = exitDialogView.findViewById(R.id.sortKelas);

        final String jabatan[] = {
                "Dosen Tetap PNS", "Dosen Tetap NON PNS", "Dosen Honorer"
        };

        final ArrayAdapter<String> AdapterJbtn = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, jabatan);
        AdapterJbtn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortJabatan.setAdapter(AdapterJbtn);

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
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_mhsw_dosen>> call = client.dataDosenDsn("Bearer " + tokens);
        call.enqueue(new Callback<List<object_mhsw_dosen>>() {
            @Override
            public void onResponse(Call<List<object_mhsw_dosen>> call, Response<List<object_mhsw_dosen>> response) {
                if(response.isSuccessful())
                {
                    List<object_mhsw_dosen> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        dosen.add(new object_mhsw_dosen(simpan.get(i).getNama(),simpan.get(i).getStatus_dosen(),simpan.get(i).getNip(), simpan.get(i).getTlp(), simpan.get(i).getNidn(), simpan.get(i).getEmail(), R.drawable.ava));
                    }
                    List<object_mhsw_dosen> rowListItem = dosen;
                    dosen2.addAll(dosen);
                    lLayout = new LinearLayoutManager(getContext());

                    rView = v.findViewById(R.id.rview);
                    rView.setLayoutManager(lLayout);

                    rcAdapter = new dosen_adapter_dosen(getContext(), dosen);
                    rView.setAdapter(rcAdapter);
                    rView.setNestedScrollingEnabled(false);
                }


            }
            @Override
            public void onFailure(Call<List<object_mhsw_dosen>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        aa.setBackgroundColor(getResources().getColor(R.color.biru));
    }

    public void filter(String text) {
        dosen.clear();
        if(text.isEmpty()){
            dosen.addAll(dosen2);
        }
        else{
            text = text.toLowerCase();
            for(int i = 0; i<dosen2.size(); i++){
                if(dosen2.get(i).getNama().toLowerCase().contains(text) ||  dosen2.get(i).getNipAtauNim().toLowerCase().contains(text)){
                    dosen.add(dosen2.get(i));
                }
            }
        }
        rcAdapter.notifyDataSetChanged();
    }
}
