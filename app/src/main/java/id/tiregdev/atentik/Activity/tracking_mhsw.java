package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_tracking;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_lokasi;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.Model.object_tracking;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tracking_mhsw extends AppCompatActivity {

    RecyclerView rView;
    LinearLayoutManager lLayout;
    String tokens;
    SearchView searchView;
    List<object_lokasi> lihatlokasi = new ArrayList<>();
    List<object_lokasi> lihatlokasi2 = new ArrayList<>();
    adapter_tracking rcAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tracking);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setTitle("Menemukan Posisi");
        CekToken ct = new CekToken();
        tokens = ct.Cek(this);

        setupAdapter();
        setSearch();
    }

    public void setSearch(){
        searchView = findViewById(R.id.search_bar);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                tracking_mhsw.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupAdapter(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_lokasi>> call = client.lihatLokasi("Bearer " + tokens);
        call.enqueue(new Callback<List<object_lokasi>>() {
            @Override
            public void onResponse(Call<List<object_lokasi>> call, Response<List<object_lokasi>> response) {
                if(response.isSuccessful())
                {
                    List<object_lokasi> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        String waktus = simpan.get(i).getWaktu().substring(11, 16).replace(':', '.');
                        lihatlokasi.add(new object_lokasi(simpan.get(i).getNama(),simpan.get(i).getNimornip(),"Sedang berada di " + simpan.get(i).getruangan() + " dari jam " + waktus));
                    }
                    List<object_lokasi> rowListItem = lihatlokasi;
                    lihatlokasi2.addAll(lihatlokasi);
                    lLayout = new LinearLayoutManager(tracking_mhsw.this);

                    rView = findViewById(R.id.rview);
                    rView.setLayoutManager(lLayout);

                    rcAdapter = new adapter_tracking(tracking_mhsw.this, rowListItem);
                    rView.setAdapter(rcAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<object_lokasi>> call, Throwable t) {

            }
        });
    }

    public void filter(String text) {
        lihatlokasi.clear();
        if(text.isEmpty()){
            lihatlokasi.addAll(lihatlokasi2);
        }
        else{
            text = text.toLowerCase();
            for(int i = 0; i<lihatlokasi2.size(); i++){
                if(lihatlokasi2.get(i).getNama().toLowerCase().contains(text) ||  lihatlokasi2.get(i).getNimornip().toLowerCase().contains(text)){
                    lihatlokasi.add(lihatlokasi2.get(i));
                }
            }
        }
        rcAdapter.notifyDataSetChanged();
    }

    private List<object_tracking> getAllItemList(){
        List<object_tracking> allItems = new ArrayList<>();
//        allItems.add(new object_tracking("Pak Fauzi", "0723110000023","Sedang berada di AA301 dari jam 07.30"));
//        allItems.add(new object_tracking("Hafizh", "4314010030","Sedang berada di AA305 dari jam 08.30"));
//        allItems.add(new object_tracking("Pak Mauldy", "012337110000023","Sedang berada di AA302 dari jam 07.30"));
//        allItems.add(new object_tracking("Toriq","4314010021","Sedang berada di AA204 dari jam 10.30"));

        return allItems;
    }
}
