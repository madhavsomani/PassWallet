package com.pass.passwallet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class site extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.site , container , false);
        ListView sitelist = (ListView)rootview.findViewById(R.id.sitelistview);
        databasehelper db = new databasehelper(getActivity());


        Cursor res = db.getdata(1);
         String[] from = new String[] { databasehelper.COL_2 ,databasehelper.COL_3};
        int[] to = new int[]{ R.id.listview_site_url , R.id.listview_site_username};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),R.layout.site_listview,res,from ,to);
        sitelist.setAdapter(adapter);

        sitelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String idtopass = cursor.getString(0);
                Bundle b = new Bundle();
                b.putString("idtopass", idtopass);

                Fragment fm = new displaysite();
                FragmentManager manger = getFragmentManager();
                fm.setArguments(b);
                manger.beginTransaction().replace(R.id.container,fm).addToBackStack(null).commit();
            }
        });



        return rootview;
    }
}
