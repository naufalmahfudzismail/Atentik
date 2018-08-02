package id.tiregdev.atentik.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener
{

	private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

	@BindView(R.id.btnLogin)
	ImageView btnLogin;

	@BindView(R.id.btnRegis)
	Button btnRegis;

	@BindView(R.id.login)
	TextView login;

	@BindView(R.id.nama)
	EditText nama;

	@BindView(R.id.nim)
	EditText nim;

	@BindView(R.id.pass)
	EditText pass;

	@BindView(R.id.confirmPass)
	EditText confirmPass;

	private String imei;


	@SuppressLint("HardwareIds")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.bind(this);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
		{
			if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
					!= PackageManager.PERMISSION_GRANTED)
			{
				requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
						PERMISSIONS_REQUEST_READ_PHONE_STATE);
			} else
			{
				imei = telephonyManager.getDeviceId();
			}
		} else
		{
			imei = telephonyManager.getDeviceId();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
	                                       @NonNull int[] grantResults)
	{
		if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
				&& grantResults[0] == PackageManager.PERMISSION_GRANTED)
		{
			Toast.makeText(this, "Akses diberikan", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();

		if (id == R.id.login)
		{
			register.this.finish();
		}

		if (id == R.id.regis)
		{
			RegisterUser();
		}

		if (id == R.id.btnLogin)
		{
			register.this.finish();
		}
	}

	public void RegisterUser()
	{
		String namas = nama.getText().toString();
		String nims = nim.getText().toString();
		String passs = pass.getText().toString();
		String confirmpasss = confirmPass.getText().toString();

		if (passs.length() < 8 || confirmpasss.length() < 8)
		{
			Toast.makeText(register.this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show();
		} else
		{
			AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
			Call<object_mahasiswa> call = client.regisMahasiswa(namas, nims, passs, confirmpasss, imei);
//                Call<object_mahasiswa> call = client.harun();
			call.enqueue(new Callback<object_mahasiswa>()
			{
				@Override
				public void onResponse(@NonNull Call<object_mahasiswa> call, @NonNull Response<object_mahasiswa> response)
				{
					if (response.isSuccessful())
					{
						if (response.body().getId() == null)
							Toast.makeText(register.this, R.string.salah_data, Toast.LENGTH_SHORT).show();
						else if (response.body().getId() == "0")
							Toast.makeText(register.this, R.string.data_sudah_ada, Toast.LENGTH_SHORT).show();
						else
						{
							Toast.makeText(register.this, R.string.sudah_regist, Toast.LENGTH_SHORT).show();
							register.this.finish();
						}
					} else if (response.code() == 422 || response.code() == 401)
						Toast.makeText(register.this, R.string.salah_data, Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onFailure(@NonNull Call<object_mahasiswa> call, @NonNull Throwable t)
				{
					Log.d("Salah nih", t.toString());
					Toast.makeText(register.this, R.string.no_koneksi, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}



}
