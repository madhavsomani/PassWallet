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


public class addnote extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.addnote , container , false);


        final databasehelper db = new databasehelper(getActivity());
        final EditText title = (EditText)rootview.findViewById(R.id.title_addnote);
        final EditText text = (EditText)rootview.findViewById(R.id.text_addnote);

        Button addnote = (Button)rootview.findViewById(R.id.addnote);

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check  = db.insertnotedata(title.getText().toString(), text.getText().toString());
                if(check == true)
                    Toast.makeText(getActivity(), "Note Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Error adding the Note!", Toast.LENGTH_SHORT).show();
            }
        });



        return rootview;
    }
}