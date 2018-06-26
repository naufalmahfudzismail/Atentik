package id.tiregdev.atentik.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Adapter.adapter_jadwal;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 13/03/18.
 */

public class jadwal_selasa extends Fragment {

    RecyclerView rView;
    LinearLayoutManager lLayout;
    View v;
    String tokens;
    List<object_jadwal> jdwlselasa = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_jadwal, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this.getActivity());
        setupAdapter();
        return v;
    }

    public void setupAdapter(){
        jdwlselasa.clear();
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_jadwal>> call = client.jadwalMahasiswa("Bearer " + tokens, "Selasa");
        call.enqueue(new Callback<List<object_jadwal>>() {
            @Override
            public void onResponse(Call<List<object_jadwal>> call, Response<List<object_jadwal>> response) {
                if(response.isSuccessful())
                {
                    List<object_jadwal> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        jdwlselasa.add(new object_jadwal(simpan.get(i).getNama_matkul(),simpan.get(i).getNama_dosen(),simpan.get(i).getJam_mulai() + " - " + simpan.get(i).getJam_selesai(), simpan.get(i).getRuangan(), "Selasa"));
                    }
                    List<object_jadwal> rowListItem = jdwlselasa;
                    lLayout = new LinearLayoutManager(getContext());

                    rView = v.findViewById(R.id.rView);
                    rView.setLayoutManager(lLayout);

                    adapter_jadwal rcAdapter = new adapter_jadwal(getContext(), rowListItem);
                    rView.setAdapter(rcAdapter);
                }
                else
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<object_jadwal>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private List<object_jadwal> getAllItemList(){
        List<object_jadwal> allItems = new ArrayList<>();
//        allItems.add(new object_jadwal(getString(R.string.mk3), "Dr. Geraldi Joni STr., MI.","08.00 - 10.30", "4","AA301"));
//        allItems.add(new object_jadwal(getString(R.string.mk4), "Kurain Jodi SHum., MI.","10.30 - 12.00", "3","AA301"));

        return allItems;
    }
}
