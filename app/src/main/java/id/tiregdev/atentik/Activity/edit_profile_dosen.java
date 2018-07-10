package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_dosen;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile_dosen extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    Button email, tlp;
    EditText emailEdt, tlpEdt;
    TextView jabatan, nb, nama, status, nip, jabatanTxt, statusTxt;
    ScrollView wrapEditProfile;
    RelativeLayout wrapHeader, wrapNim;
    Toolbar toolbar;
    LinearLayout wrapContent;
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
        changeColor();
        setTextContent();
    }

    public void setTextContent(){
        nama = findViewById(R.id.nama);
        nama.setText("Dr. Geraldi Joni STr., MI");

        status = findViewById(R.id.status);
        status.setVisibility(View.VISIBLE);
        status.setText("Dosen Tetap Non PNS");

        nip = findViewById(R.id.nip);
        nip.setVisibility(View.VISIBLE);
        nip.setText("09887962256182627");

        wrapNim = findViewById(R.id.wrapNim);
        wrapNim.setVisibility(View.GONE);

        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_dosen> call = client.profilDosen("Bearer " + tokens);
        call.enqueue(new Callback<object_dosen>() {
            @Override
            public void onResponse(Call<object_dosen> call, Response<object_dosen> response) {
                if(response.isSuccessful())
                {
                    nama.setText(response.body().getNama());
                    email.setText(response.body().getEmail());
                    tlp.setText(response.body().getTlp());
//                    imei.setText(response.body().getImei_hp());
                    status.setText(response.body().getStatus_dosen());
                    nip.setText(response.body().getNip());
                    Glide.with(getBaseContext()).load("https://atentik.id/assets/img/faces/" + response.body().getPhoto()).into(photo);
                }
            }

            @Override
            public void onFailure(Call<object_dosen> call, Throwable t) {
                Toast.makeText(edit_profile_dosen.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeColor(){
        wrapEditProfile = findViewById(R.id.wrapEditProfile);
        wrapEditProfile.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        wrapHeader = findViewById(R.id.wrapHeader);
        wrapHeader.setBackgroundColor(getResources().getColor(R.color.pink));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.pink));

        nb = findViewById(R.id.nb);
        nb.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void findID(){
        jabatan = findViewById(R.id.imeiORjabatan);
        jabatan.setText("KPS TMJ");

        jabatanTxt = findViewById(R.id.imeiORjabatanTxt);
        jabatanTxt.setText("Jabatan");

        status = findViewById(R.id.spORstatus);
        status.setText("Dosen PNS");

        statusTxt = findViewById(R.id.spORstatusTxt);
        statusTxt.setText("Status Dosen");

        jabatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile_dosen.this, "Jabatan tidak dapat di ubah", Toast.LENGTH_SHORT).show();
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile_dosen.this, "Status dosen tidak dapat di ubah", Toast.LENGTH_SHORT).show();
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
                final LayoutInflater factory = LayoutInflater.from(edit_profile_dosen.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_edit_tlp, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(edit_profile_dosen.this).create();
                tlpEdt = exitDialogView.findViewById(R.id.edtTlp);
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                        Call<object_dosen> call = client.profilTlpDosen("Bearer " + tokens, tlpEdt.getText().toString());
                        call.enqueue(new Callback<object_dosen>() {
                            @Override
                            public void onResponse(Call<object_dosen> call, Response<object_dosen> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(edit_profile_dosen.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
                                }
                                else if(response.code() == 422 || response.code() == 401)
                                {
                                    Toast.makeText(edit_profile_dosen.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<object_dosen> call, Throwable t) {
                                Toast.makeText(edit_profile_dosen.this, t.toString(), Toast.LENGTH_SHORT).show();
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
                final LayoutInflater factory = LayoutInflater.from(edit_profile_dosen.this);
                final View exitDialogView = factory.inflate(R.layout.dialog_edit_email, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(edit_profile_dosen.this).create();
                emailEdt = exitDialogView.findViewById(R.id.edtEmail);
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                        Call<object_dosen> call = client.profilEmailDosen("Bearer " + tokens, emailEdt.getText().toString());
                        call.enqueue(new Callback<object_dosen>() {
                            @Override
                            public void onResponse(Call<object_dosen> call, Response<object_dosen> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(edit_profile_dosen.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<object_dosen> call, Throwable t) {
                                Toast.makeText(edit_profile_dosen.this, t.toString(), Toast.LENGTH_SHORT).show();
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
        edit_profile_dosen.this.finish();
    }
}
