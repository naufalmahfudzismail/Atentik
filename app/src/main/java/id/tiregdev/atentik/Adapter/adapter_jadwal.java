package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.tiregdev.atentik.Model.object_jadwal;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Activity.set_jadwal_masuk_dosen;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_jadwal extends RecyclerView.Adapter<adapter_jadwal.holder_jadwal>
{

	private List<object_jadwal> itemList;
	private Context context;
	TextView namaMatkul, namaDosen, jadwalHari;

	public adapter_jadwal(Context context, List<object_jadwal> itemList)
	{
		this.itemList = itemList;
		this.context = context;
	}

	@Override
	public holder_jadwal onCreateViewHolder(ViewGroup parent, int viewType)
	{
		final View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal, null);
		holder_jadwal hn = new holder_jadwal(layoutView);

//        option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final LayoutInflater factory = LayoutInflater.from(context);
//                final View exitDialogView = factory.inflate(R.layout.dialog_set_jadwal, null);
//                final AlertDialog exitDialog = new AlertDialog.Builder(context).create();
//
//                namaMatkul = layoutView.findViewById(R.id.namaMatkul);
//                namaDosen = layoutView.findViewById(R.id.namaDosenAtauNamaKelas);
//                jadwalHari = layoutView.findViewById(R.id.jadwalHari);
//                exitDialog.setView(exitDialogView);
//                exitDialogView.findViewById(R.id.setJadwal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        exitDialog.dismiss();
//                        Intent i = new Intent(context, set_jadwal_masuk_dosen.class);
//                        Bundle extras = new Bundle();
//                        extras.putString("nmatkul", namaMatkul.getText().toString());
//                        extras.putString("ndosen", namaDosen.getText().toString());
//                        extras.putString("nhari", jadwalHari.getText().toString());
//                        i.putExtras(extras);
//                        context.startActivity(i);
//                    }
//                });
//                exitDialog.show();
//            }
//        });

		return hn;
	}

	@Override
	public void onBindViewHolder(holder_jadwal holder, int position)
	{
		holder.namaMatkul.setText(itemList.get(position).getNamaMatkul());
		holder.jam.setText(itemList.get(position).getJam());
//        holder.jpm.setText(itemList.get(position).getJpm());
		holder.ruangan.setText(itemList.get(position).getRuangan());
		holder.namaDosenAtauNamaKelas.setText(itemList.get(position).getNamaDosenAtauNamaKelas());
		holder.jadwalHari.setText(itemList.get(position).getHari());
	}

	@Override
	public int getItemCount()
	{
		return this.itemList.size();
	}

	public class holder_jadwal extends RecyclerView.ViewHolder
	{
		public TextView namaMatkul, namaDosenAtauNamaKelas, jam, ruangan, jadwalHari;

		public holder_jadwal(View itemView)
		{
			super(itemView);

			namaMatkul = itemView.findViewById(R.id.namaMatkul);
			jam = itemView.findViewById(R.id.jam);
//            jpm = itemView.findViewById(R.id.jpm);
			ruangan = itemView.findViewById(R.id.ruangan);
			namaDosenAtauNamaKelas = itemView.findViewById(R.id.namaDosenAtauNamaKelas);
			jadwalHari = itemView.findViewById(R.id.jadwalHari);
		}
	}

}
