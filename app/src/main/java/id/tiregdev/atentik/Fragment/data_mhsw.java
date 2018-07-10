package id.tiregdev.atentik.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Adapter.adapter_dialog_mhsw;
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

public class data_mhsw extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;
    SearchView searchView;
    String tokens;
    List<object_mhsw_dosen> skelas = new ArrayList<>();
    List<object_mhsw_dosen> mhsw = new ArrayList<>();
    List<object_mhsw_dosen> mhsw2 = new ArrayList<>();
    adapter_dialog_mhsw rcAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_mhsw, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this.getActivity());
        setupAdaptermhsw_dosen();
        setSearch();
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

    public void dialogFilter(){
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View exitDialogView = factory.inflate(R.layout.dialog_filter_dsn_mhsw, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();
        final Spinner sortKelas = exitDialogView.findViewById(R.id.sortKelas);
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_mhsw_dosen>> call = client.seluruhKelas("Bearer " + tokens);
        call.enqueue(new Callback<List<object_mhsw_dosen>>() {
            @Override
            public void onResponse(Call<List<object_mhsw_dosen>> call, Response<List<object_mhsw_dosen>> response) {
                if(response.isSuccessful())
                {
                    List<object_mhsw_dosen> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
//                        skelas.add(new object_mhsw_dosen(simpan.get(i).getNama_kelas());
                    }
                    mhsw2.addAll(mhsw);
                }
                else
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<object_mhsw_dosen>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_mhsw_dosen>> call = client.dataMahasiswa("Bearer " + tokens);
        call.enqueue(new Callback<List<object_mhsw_dosen>>() {
            @Override
            public void onResponse(Call<List<object_mhsw_dosen>> call, Response<List<object_mhsw_dosen>> response) {
                if(response.isSuccessful())
                {
                    List<object_mhsw_dosen> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        mhsw.add(new object_mhsw_dosen(simpan.get(i).getNama(),simpan.get(i).getNama_kelas(),simpan.get(i).getNim(), simpan.get(i).getEmail(), simpan.get(i).getKompen(), simpan.get(i).getStatus_sp(), simpan.get(i).getPhoto()));
                    }
                    List<object_mhsw_dosen> rowListItem = mhsw;
                    mhsw2.addAll(mhsw);
                    lLayout = new LinearLayoutManager(getContext());

                    rView = v.findViewById(R.id.rview);
                    rView.setLayoutManager(lLayout);

                    rcAdapter = new adapter_dialog_mhsw(getContext(), mhsw);
                    rView.setAdapter(rcAdapter);
                    rView.setNestedScrollingEnabled(false);
                }
                    else
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<object_mhsw_dosen>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void filter(String text) {
        mhsw.clear();
        if(text.isEmpty()){
            mhsw.addAll(mhsw2);
        }
          else{
            text = text.toLowerCase();
            for(int i = 0; i<mhsw2.size(); i++){
                if(mhsw2.get(i).getNama().toLowerCase().contains(text) ||  mhsw2.get(i).getNipAtauNim().toLowerCase().contains(text) || mhsw2.get(i).getJabatanAatauKelas().toLowerCase().contains(text)){
                    mhsw.add(mhsw2.get(i));
                }
            }
        }
        rcAdapter.notifyDataSetChanged();
    }

    private List<object_mhsw_dosen> getAllItemList(){
        List<object_mhsw_dosen> allItems = new ArrayList<>();
//        allItems.add(new object_mhsw_dosen("Yusuf Setya Nugraha", "TMJ 5","4313010022", "yusufsn@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Setya Nugraha", "TMJ 5","4313010022", "yusufsn22@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Nugraha", "TMJ 5","4313010022", "yusufsn11@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Nugraha Yusuf Setya", "TMJ 5","4313010022", "yusufsn32@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Setya Nugraha Yusuf", "TMJ 5","4313010022", "yusufsn52@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Yusuf Nugraha", "TMJ 5","4313010022", "yusufsn21@gmail.com", R.drawable.ava));
//        allItems.add(new object_mhsw_dosen("Nugraha Setya", "TMJ 5","4313010022", "yusufsn34@gmail.com", R.drawable.ava));

        return allItems;
    }
}
