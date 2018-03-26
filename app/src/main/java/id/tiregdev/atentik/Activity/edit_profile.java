package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.tiregdev.atentik.Adapter.adapter_cubeacon;
import id.tiregdev.atentik.Model.object_cubeacon;
import id.tiregdev.atentik.R;

public class edit_profile extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    Button email, tlp;
    EditText emailEdt, tlpEdt;
    TextView imei, statusSP, nama, status, nip;
    RelativeLayout wrapNim;
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
    }

    public void setTextContent(){
        nama = findViewById(R.id.nama);

        status = findViewById(R.id.status);
        status.setVisibility(View.GONE);

        nip = findViewById(R.id.nip);
        nip.setVisibility(View.GONE);

        wrapNim = findViewById(R.id.wrapNim);
        wrapNim.setVisibility(View.VISIBLE);
    }

    public void findID(){
        imei = findViewById(R.id.imei);
        statusSP = findViewById(R.id.statusSP);
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
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(edit_profile.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
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
                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(edit_profile.this, "Data berhasil di ubah", Toast.LENGTH_SHORT).show();
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
