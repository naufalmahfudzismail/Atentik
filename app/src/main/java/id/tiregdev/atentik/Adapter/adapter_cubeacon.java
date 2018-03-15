package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_cubeacon extends RecyclerView.Adapter<adapter_cubeacon.holder_cubeacon> {

    private List<object_cubeacon> itemList;
    private Context context;

    public adapter_cubeacon(Context context, List<object_cubeacon> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_cubeacon onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_connect_cubeacon,null);
        holder_cubeacon hn = new holder_cubeacon(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_cubeacon holder, int position){
        holder.ruang.setText(itemList.get(position).getRuangan());
        holder.cubeaconNumber.setText(itemList.get(position).getCubeacon());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_cubeacon extends RecyclerView.ViewHolder {
        public TextView ruang, cubeaconNumber;

        public holder_cubeacon(View itemView){
            super(itemView);

            ruang = itemView.findViewById(R.id.ruangan);
            cubeaconNumber = itemView.findViewById(R.id.cubeaconNumber);
        }
    }

}
