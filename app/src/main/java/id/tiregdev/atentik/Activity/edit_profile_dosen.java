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

import id.tiregdev.atentik.R;

public class edit_profile_dosen extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    Button email, tlp;
    EditText emailEdt, tlpEdt;
    TextView imei, statusSP, nb, nama, status, nip;
    ScrollView wrapEditProfile;
    RelativeLayout wrapHeader, wrapNim;
    Toolbar toolbar;
    LinearLayout wrapContent;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
        imei = findViewById(R.id.imei);
        statusSP = findViewById(R.id.statusSP);
        imei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile_dosen.this, "Imei tidak dapat di ubah", Toast.LENGTH_SHORT).show();
            }
        });

        statusSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(edit_profile_dosen.this, "Status SP tidak dapat di ubah", Toast.LENGTH_SHORT).show();
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
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(edit_profile_dosen.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
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
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(edit_profile_dosen.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
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
