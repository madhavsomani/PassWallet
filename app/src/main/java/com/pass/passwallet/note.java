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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class note extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.note , container , false);

        ListView notelist = (ListView)rootview.findViewById(R.id.notelistview);
        databasehelper db = new databasehelper(getActivity());


        Cursor res = db.getdata(3);

        String[] from = new String[] { databasehelper.COL_2_3 };
        int[] to = new int[]{ R.id.title_textview };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),R.layout.notelistview,res,from ,to);
        notelist.setAdapter(adapter);

        notelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String idtopass = cursor.getString(0);
                Bundle b = new Bundle();
                b.putString("idtopass", idtopass);

                Fragment fm = new displaynote();
                FragmentManager manger = getFragmentManager();
                fm.setArguments(b);
                manger.beginTransaction().replace(R.id.container,fm).addToBackStack(null).commit();
            }
        });



        return rootview;
    }
}

