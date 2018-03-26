package id.tiregdev.atentik.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.tiregdev.atentik.R;

public class login_dosen extends AppCompatActivity {

    ImageView register;
    Button login, logMhsw;
    TextView regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findId();
    }

    public void findId() {
        regis = findViewById(R.id.regis);
        register = findViewById(R.id.register);
        login = findViewById(R.id.btnLogin);
        logMhsw = findViewById(R.id.logMhsw);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_dosen.this, register_dosen.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_dosen.this, register_dosen.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_dosen.this, main_dosen.class);
                startActivity(i);
            }
        });

        logMhsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_dosen.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        login_dosen.this.finish();
    }
}
