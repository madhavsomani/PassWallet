package com.pass.passwallet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class displaynote extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.displaynote, container, false);

        TextView title = (TextView)rootview.findViewById(R.id.displaytitile);
        TextView text = (TextView)rootview.findViewById(R.id.displaytext);


        Button delete = (Button)rootview.findViewById(R.id.displaynotedelete);

        Bundle b = this.getArguments();
        final String idtopass = b.getString("idtopass");

        final databasehelper db = new databasehelper(getActivity());
        Cursor v = db.getdatabyid(idtopass,3);

        if(v != null && v.moveToFirst()) {

            title.setText("Title :"+v.getString(2));
            text.setText(""+v.getString(3));

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = db.datadelet(idtopass,3);
                if(check > 0) {
                    Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_SHORT).show();
                    Fragment fm = new note();
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
