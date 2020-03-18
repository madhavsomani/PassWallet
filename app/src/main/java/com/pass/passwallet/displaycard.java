package com.pass.passwallet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class displaycard extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.displaycard, container , false);


        TextView name = (TextView)rootview.findViewById(R.id.card_listview_name);
        TextView cardno = (TextView)rootview.findViewById(R.id.card_listview_cardno);
        TextView expdate = (TextView)rootview.findViewById(R.id.card_listview_exp);
        TextView cvv = (TextView)rootview.findViewById(R.id.card_listview_cvv);

        Button delete = (Button)rootview.findViewById(R.id.card_listview_delete);

        Bundle b = this.getArguments();
        final String idtopass = b.getString("idtopass");

        final databasehelper db = new databasehelper(getActivity());
        Cursor v = db.getdatabyid(idtopass,2);

        if(v != null && v.moveToFirst()) {

             name.setText("Name : "+v.getString(2));
            cardno.setText("CardNo : "+v.getString(3));
            expdate.setText("ExpDate : "+v.getString(4));
            cvv.setText("Cvv/Cvv2 : "+v.getString(5));
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = db.datadelet(idtopass,2);
                if(check > 0) {
                    Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_SHORT).show();
                    Fragment fm = new card();
                    FragmentManager manger = getFragmentManager();
                    manger.beginTransaction().replace(R.id.container,fm).commit();
                }
                else
                    Toast.makeText(getActivity(), "Data not Deleted", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }
}
