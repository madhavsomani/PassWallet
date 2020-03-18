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


public class addcard extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.addcard , container , false);

        final databasehelper db = new databasehelper(getActivity());
        final EditText name = (EditText)rootview.findViewById(R.id.card_name_text);
        final EditText cardno = (EditText)rootview.findViewById(R.id.card_cardno_text);
        final EditText expdate = (EditText)rootview.findViewById(R.id.card_expdate_text);
        final EditText cvv = (EditText)rootview.findViewById(R.id.card_cvv_text);
        Button addcard = (Button)rootview.findViewById(R.id.add_card_button);

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check  = db.insertcarddata(name.getText().toString(), cardno.getText().toString(), expdate.getText().toString(), cvv.getText().toString());
                if(check == true)
                    Toast.makeText(getActivity(), "Card Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Error adding the Card!", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }
}