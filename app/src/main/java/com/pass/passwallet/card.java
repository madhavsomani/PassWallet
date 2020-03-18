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
import android.widget.TextView;

public class card extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.card , container , false);


        ListView list = (ListView)rootview.findViewById(R.id.card_listview_layout);
        databasehelper db = new databasehelper(getActivity());
        Cursor res = db.getdata(2);
        String[] from = new String[]{ databasehelper.COL_3_2 , databasehelper.COL_2_2 , databasehelper.COL_4_2};
        int[] to = new int[]{R.id.Textview_cardno , R.id.textview_name_card , R.id.textview_expdate_card};
        list.setAdapter(new SimpleCursorAdapter(getActivity(), R.layout.card_listview, res, from, to));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String idtopass = cursor.getString(0);

                Bundle b = new Bundle();
                b.putString("idtopass" , idtopass);

                Fragment fm = new displaycard();
                FragmentManager manger = getFragmentManager();
                fm.setArguments(b);
                manger.beginTransaction().replace(R.id.container , fm).addToBackStack(null).commit();


            }
        });
        return rootview;
    }
}
