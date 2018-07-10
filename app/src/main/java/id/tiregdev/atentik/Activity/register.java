package id.tiregdev.atentik.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
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

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    ImageView btnLogin;
    Button btnRegis;
    TextView login;
    EditText nama, nim, pass, confirmPass;
    TelephonyManager telephonyManager;
    String imei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE);
            } else {
                imei = telephonyManager.getDeviceId();
            }
        }
        else {
            imei = telephonyManager.getDeviceId();
        }
        findId();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Akses diberikan", Toast.LENGTH_SHORT).show();
        }
    }
    public void findId() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegis = findViewById(R.id.btnRegis);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        pass = findViewById(R.id.pass);
        confirmPass = findViewById(R.id.confirmPass);
        login = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.this.finish();
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namas = nama.getText().toString();
                String nims = nim.getText().toString();
                String passs = pass.getText().toString();
                String confirmpasss = confirmPass.getText().toString();

                if(passs.length() <8 || confirmpasss.length() <8)
                {
                    Toast.makeText(register.this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                    Call<object_mahasiswa> call = client.regisMahasiswa(namas, nims, passs, confirmpasss, imei);
//                Call<object_mahasiswa> call = client.harun();
                    call.enqueue(new Callback<object_mahasiswa>() {
                        @Override
                        public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                            if(response.isSuccessful())
                            {
                                if(response.body().getId() == null)
                                    Toast.makeText(register.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                                else if(response.body().getId() == "0")
                                    Toast.makeText(register.this, "Data sudah ada", Toast.LENGTH_SHORT).show();
                                else
                                {
                                    Toast.makeText(register.this, "Anda telah teregistrasi", Toast.LENGTH_SHORT).show();
                                    register.this.finish();
                                }
                            }
                            else if(response.code() == 422 || response.code() == 401)
                                Toast.makeText(register.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                            String salah = "Koneksi internet tidak tersambung";
                            Log.d("ALALALA", t.toString());
                            Toast.makeText(register.this, "Gagal: \n" + salah, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.this.finish();
            }
        });

    }

}
