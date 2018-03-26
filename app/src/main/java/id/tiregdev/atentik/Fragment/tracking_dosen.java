package id.tiregdev.atentik.Fragment;

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

public class tracking_dosen extends Fragment {

    View v;
    ScrollView wrapTracking;
    TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tracking, container, false);
        changeColor();
        return v;
    }

    public void changeColor(){
        wrapTracking = v.findViewById(R.id.wrapTracking);
        wrapTracking.setBackgroundColor(getResources().getColor(R.color.AbuBG));

        txt = v.findViewById(R.id.txt);
        txt.setTextColor(getResources().getColor(R.color.putih));
    }
}
