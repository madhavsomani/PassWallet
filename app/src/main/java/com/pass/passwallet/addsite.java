package com.pass.passwallet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addsite extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.addsite , container , false);

        final databasehelper db = new databasehelper(getActivity());
        final EditText siteurl = (EditText)rootview.findViewById(R.id.siteurl_editext);
        final EditText username = (EditText)rootview.findViewById(R.id.username_edittext);
        final EditText password = (EditText)rootview.findViewById(R.id.password_editext);
        Button addsite = (Button)rootview.findViewById(R.id.addsite_button);

        addsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check  = db.insertdata(siteurl.getText().toString(), username.getText().toString(), password.getText().toString());
                if(check == true)
                    Toast.makeText(getActivity(), "Site Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Error adding the Site!", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }
}
