package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    Button email, tlp;
    EditText emailEdt, tlpEdt;
    TextView imei, statusSP, nama, status, nip, nim, kelas;
    RelativeLayout wrapNim;
    ImageView photo;
    String tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        CekToken ct = new CekToken();
        tokens = ct.Cek(this);
        setEmail();
        setTlp();
        findID();
        setTextContent();
    }

    public void setTextContent(){
        nama = findViewById(R.id.nama);

//        status = findViewById(R.id.spORstatus);
//        status.setVisibility(View.GONE);

        nip = findViewById(R.id.nip);
        nip.setVisibility(View.GONE);

        wrapNim = findViewById(R.id.wrapNim);
        wrapNim.setVisibility(View.VISIBLE);
    }

    public void findID(){
        imei = findViewById(R.id.imeiORjabatan);
        statusSP = findViewById(R.id.spORstatus);
        nim = findViewById(R.id.nim);
        kelas = findViewById(R.id.kelas);

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_mahasiswa> call = client.profilMahasiswa("Bearer " + tokens);
        call.enqueue(new Callback<object_mahasiswa>() {
            @Override
            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                if(response.isSuccessful())
                {
                    nama.setText(response.body().getNama());
                    email.setText(response.body().getEmail());
                    tlp.setText(response.body().getTlp());
                    imei.setText(response.body().getImei_hp());
                    statusSP.setText(response.body().getStatus_sp());
                    nim.setText(response.body().getNim());
                    kelas.setText(response.body().getKelas());
                    Glide.with(getBaseContext()).load("https://atentik.id/assets/img/faces/" + response.body().getPhoto()).into(photo);
                }
            }

            @Override
            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                Toast.makeText(edit_profile.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        imei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile.this, "Imei tidak dapat di ubah", Toast.LENGTH_SHORT).show();
            }
        });

        statusSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile.this, "Status SP tidak dapat di ubah", Toast.LENGTH_SHORT).show();
            }
        });

        photo = findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
    }

    public void setTlp(){
        tlp = findViewById(R.id.tlp);
        tlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(edit_profile.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_edit_tlp, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(edit_profile.this).create();
                tlpEdt = exitDialogView.findViewById(R.id.edtTlp);
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                        Call<object_mahasiswa> call = client.profilTlpMahasiswa("Bearer " + tokens, tlpEdt.getText().toString());
                        call.enqueue(new Callback<object_mahasiswa>() {
                            @Override
                            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(edit_profile.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                                Toast.makeText(edit_profile.this, t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        exitDialog.dismiss();
                    }
                });
                exitDialogView.findViewById(R.id.tidak).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void setEmail(){
        email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(edit_profile.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_edit_email, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(edit_profile.this).create();
                emailEdt = exitDialogView.findViewById(R.id.edtEmail);
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                        Call<object_mahasiswa> call = client.profilEmailMahasiswa("Bearer " + tokens, emailEdt.getText().toString());
                        call.enqueue(new Callback<object_mahasiswa>() {
                            @Override
                            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(edit_profile.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
                                }
                                else if(response.code() == 422 || response.code() == 401)
                                {
                                    Toast.makeText(edit_profile.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                                Toast.makeText(edit_profile.this, t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        exitDialog.dismiss();
                    }
                });
                exitDialogView.findViewById(R.id.tidak).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                });
                exitDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        edit_profile.this.finish();
    }
}
