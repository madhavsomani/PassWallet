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


public class displaysite extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.displaysite , container , false);


        TextView site = (TextView)rootview.findViewById(R.id.displaysite_site);
        TextView username = (TextView)rootview.findViewById(R.id.displaysite_Username);
        TextView pass = (TextView)rootview.findViewById(R.id.displaysite_Password);
        Button delete = (Button)rootview.findViewById(R.id.delete);

        Bundle b = this.getArguments();
        final String idtopass = b.getString("idtopass");

        final databasehelper db = new databasehelper(getActivity());
        Cursor v = db.getdatabyid(idtopass,1);

        if(v != null && v.moveToFirst()) {



            site.setText("Site : "+v.getString(2));
            username.setText("Username : "+v.getString(3));
            pass.setText("Password : "+v.getString(4));
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int check = db.datadelet(idtopass,1);
                if(check > 0) {
                    Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_SHORT).show();
                    Fragment fm = new site();
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
