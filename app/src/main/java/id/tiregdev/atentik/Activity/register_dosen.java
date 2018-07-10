package id.tiregdev.atentik.Activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_dosen;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register_dosen extends AppCompatActivity {

    ImageView btnLogin;
    TextView login, nama, nip, pass, confirmPass;
    Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dosen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findId();
    }

    public void findId(){
        btnLogin = findViewById(R.id.btnLogin);
        login = findViewById(R.id.login);
        nama = findViewById(R.id.nama);
        nip = findViewById(R.id.nip);
        pass = findViewById(R.id.pass);
        confirmPass = findViewById(R.id.confirmPass);
        btnRegis = findViewById(R.id.btnRegis);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namas = nama.getText().toString();
                String nips = nip.getText().toString();
                String passs = pass.getText().toString();
                String confirmpasss = confirmPass.getText().toString();

                if(passs.length() <8 || confirmpasss.length() <8)
                {
                    Toast.makeText(register_dosen.this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show();
                }
                else {
                    AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                    Call<object_dosen> call = client.regisDosen(namas, nips, passs, confirmpasss);
                    call.enqueue(new Callback<object_dosen>() {
                        @Override
                        public void onResponse(Call<object_dosen> call, Response<object_dosen> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getId() == null)
                                    Toast.makeText(register_dosen.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                                else if (response.body().getId() == "0")
                                    Toast.makeText(register_dosen.this, "Data sudah ada", Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(register_dosen.this, "Anda telah teregistrasi", Toast.LENGTH_SHORT).show();
                                    register_dosen.this.finish();
                                }
                            } else if (response.code() == 422 || response.code() == 401)
                                Toast.makeText(register_dosen.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<object_dosen> call, Throwable t) {
                            String salah = "Koneksi internet tidak tersambung";
                            Toast.makeText(register_dosen.this, "Gagal: \n" + salah, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_dosen.this.finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_dosen.this.finish();
            }
        });
    }
}
