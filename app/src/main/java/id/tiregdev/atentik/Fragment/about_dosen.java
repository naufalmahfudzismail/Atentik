package id.tiregdev.atentik.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class about_dosen extends Fragment {

    View v, line;
    TextView openWeb, desc, develop, appName;
    ScrollView wrap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about, container, false);
        setOpenWeb();
        changeColor();
        return v;
    }

    public void setOpenWeb(){
        final String url = "https://www.atentik.com";
        openWeb = v.findViewById(R.id.web);
        openWeb.setTextColor(getResources().getColor(R.color.pink));
        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

    public void changeColor(){
        line = v.findViewById(R.id.line);
        line.setBackgroundColor(getResources().getColor(R.color.AbuMuda));

        desc = v.findViewById(R.id.desc);
        desc.setTextColor(getResources().getColor(R.color.putih));

        develop = v.findViewById(R.id.develop);
        develop.setTextColor(getResources().getColor(R.color.putih));

        appName = v.findViewById(R.id.appName);
        appName.setTextColor(getResources().getColor(R.color.putih));

        wrap = v.findViewById(R.id.wrapAbout);
        wrap.setBackgroundColor(getResources().getColor(R.color.AbuBG));
    }
}
