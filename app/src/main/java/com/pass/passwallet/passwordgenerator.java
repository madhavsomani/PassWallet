package com.pass.passwallet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class passwordgenerator extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.passwordgenerator , container , false);

        final TextView password = (TextView)rootview.findViewById(R.id.passwordgeneratetext);
        Button gen = (Button)rootview.findViewById(R.id.generate);

        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                password.setText(Long.toString(r.nextLong(), 36));

            }
        });



        return rootview;
    }
}
