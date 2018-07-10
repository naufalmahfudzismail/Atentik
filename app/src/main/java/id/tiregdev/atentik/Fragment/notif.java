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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.Adapter.adapter_notif;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_notif;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 13/03/18.
 */

public class notif extends Fragment {

    View v;
    RecyclerView rView;
    String tokens;
    LinearLayoutManager lLayout;
    List<object_notif> notif = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notif, container, false);
        CekToken ct = new CekToken();
        tokens = ct.Cek(this.getActivity());
        setupAdapter();
        return v;
    }

    public void setupAdapter(){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<List<object_notif>> call = client.lihatNotif("Bearer " + tokens);
        call.enqueue(new Callback<List<object_notif>>() {
            @Override
            public void onResponse(Call<List<object_notif>> call, Response<List<object_notif>> response) {
                if(response.isSuccessful())
                {
                    List<object_notif> simpan = response.body();
                    for(int i = 0; i < simpan.size(); i++)
                    {
                        try {
                            Date tanggal = format.parse(simpan.get(i).getCreated_at());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        notif.add(new object_notif(simpan.get(i).getJudul(),simpan.get(i).getIsi(),simpan.get(i).getCreated_at()));
                    }
                    List<object_notif> rowListItem = notif;
                    lLayout = new LinearLayoutManager(getContext());

                    rView = v.findViewById(R.id.rview);
                    rView.setLayoutManager(lLayout);

                    adapter_notif rcAdapter = new adapter_notif(getContext(), rowListItem);
                    rView.setAdapter(rcAdapter);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), lLayout.getOrientation());
                    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
                    rView.addItemDecoration(dividerItemDecoration);
                }
                else
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<object_notif>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<object_notif> getAllItemList(){
        List<object_notif> allItems = new ArrayList<>();
//        allItems.add(new object_notif("Update Apss", getResources().getString(R.string.sampleTxt),"02/02/2018"));
//        allItems.add(new object_notif("Info Reward", getResources().getString(R.string.sampleTxt),"01/02/2018"));
//        allItems.add(new object_notif("Info SP", getResources().getString(R.string.sampleTxt),"01/02/2018"));
//        allItems.add(new object_notif("Teguran", getResources().getString(R.string.sampleTxt),"15/01/2018"));

        return allItems;
    }
}
