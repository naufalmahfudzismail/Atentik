package id.tiregdev.atentik.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.eyro.cubeacon.CBBootstrapListener;
import com.eyro.cubeacon.CBBootstrapRegion;
import com.eyro.cubeacon.CBRegion;
import com.eyro.cubeacon.Cubeacon;
import com.eyro.cubeacon.LogLevel;
import com.eyro.cubeacon.Logger;
import com.eyro.cubeacon.MonitoringState;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Util.AtentikHelper;
import id.tiregdev.atentik.Model.object_mahasiswa;
import id.tiregdev.atentik.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity implements CBBootstrapListener {

    Cubeacon cubeacon;
    ImageView register;
    Button login, logDosen;
    TextView regis;
    EditText nim, pass;
    SharedPreferences sharedPreferences;
    ProgressDialog pDialog;
    TelephonyManager telephonyManager;
    private static final String TAG = login.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);

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

        // set Cubeacon SDK log level to verbose mode
        Logger.setLogLevel(LogLevel.VERBOSE);

        // enable background power saver to save battery life up to 60%
        Cubeacon.setBackgroundPowerSaver(true);

        // initializing Cubeacon SDK
        Cubeacon.initialize(this);

        // setup region scanning when OS boot completed
        CBBootstrapRegion.setup(this,
                new CBRegion("com.eyro.cubeacon.bootstrap_region",
                        UUID.fromString("cb10023f-a318-3394-4199-a8730c7c1aec")));

        cubeacon = Cubeacon.getInstance();

        findId();
        String lname = readFile("loginnama");
        String lpass = readFile("loginpass");
        if(lname.length() == 1024)
        {
//            Toast.makeText(this, lname, Toast.LENGTH_SHORT).show();
            masuklogin(lname, lpass);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Akses diberikan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(RemoteMessage remoteMessage) {
                String messagePayload = remoteMessage.getData().get("myMessagePayload");
                if (messagePayload == null) {
                    // Message payload was not set for this notification
                    Log.i("MyActivity", "Payload was missing");
                } else {
                    Log.i("MyActivity", messagePayload);
                    // Now update the UI based on your message payload!
                    Toast.makeText(login.this, messagePayload, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void didEnterRegion(CBRegion region) {
        Log.d(TAG, "Entering region: " + region);
    }

    @Override
    public void didExitRegion(CBRegion region) {
        Log.d(TAG, "Exiting region: " + region);
    }

    @Override
    public void didDetermineStateForRegion(MonitoringState state, CBRegion region) {
        Log.d(TAG, "Region: " + region + ", state: " + state.name());
    }

    public void findId() {
        regis = findViewById(R.id.regis);
        register = findViewById(R.id.register);
        login = findViewById(R.id.btnLogin);
        logDosen = findViewById(R.id.logDosen);
        nim = findViewById(R.id.nim);
        pass = findViewById(R.id.pass);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nnim = nim.getText().toString();
                String ppass = pass.getText().toString();
                masuklogin(nnim, ppass);

            }
        });

        logDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, login_dosen.class);
                startActivity(i);
            }
        });
    }

    public void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void writeFile(String filename, String isi) {
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);

            fos.write(isi.getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e("ERROR", e.toString());
        }
    }

    private String readFile(String filename) {
        String string = "";
        try {
            byte[] bytes = new byte[1024];

            FileInputStream fis = openFileInput(filename);

            fis.read(bytes);
            fis.close();

            string = new String(bytes);
        } catch (IOException e) {
            Log.e("ERROR", e.toString());
        }
        return string;
    }

    public void masuklogin(final String nims, final String passs){
        AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
        Call<object_mahasiswa> call = client.loginMahasiswa(nims, passs, imei);
        call.enqueue(new Callback<object_mahasiswa>() {

            @Override
            public void onResponse(Call<object_mahasiswa> call, Response<object_mahasiswa> response) {

                pDialog.setMessage("Logging in ...");
                showDialog();

                if(response.isSuccessful())
                {
                    if (response.body().getId() == "0")
                    {
                        Toast.makeText(login.this, "Imei tidak cocok", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        hideDialog();
                        Toast.makeText(login.this, "Anda telah berhasil login", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Token", response.body().getToken());
                        editor.apply();
                        writeFile("loginnama", nims);
                        writeFile("loginpass", passs);
                        Intent i = new Intent(login.this, activity_main.class);
                        startActivity(i);
                    }
                }
                else if(response.code() == 422)
                {
                    hideDialog();
                    Toast.makeText(login.this, "Data yang dimasukkan tidak tepat", Toast.LENGTH_SHORT).show();
                }
                hideDialog();
            }

            @Override
            public void onFailure(Call<object_mahasiswa> call, Throwable t) {
                Toast.makeText(login.this, "Gagal: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
