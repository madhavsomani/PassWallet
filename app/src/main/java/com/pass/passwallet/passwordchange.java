package com.pass.passwallet;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class passwordchange extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.passwordchange , container , false);

        final EditText oldpasswordtext = (EditText)rootview.findViewById(R.id.oldpasswordtext);
        final EditText newpasswordtext = (EditText)rootview.findViewById(R.id.newpasswordtext);
        final EditText confirmpasswordtext = (EditText)rootview.findViewById(R.id.confirmpasswordtext);
        Button changepassword = (Button)rootview.findViewById(R.id.changepassword);

        final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor edit = spf.edit();

            final String Curentpassword = spf.getString("Masterpassword" , "1234");



        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldpassword = oldpasswordtext.getText().toString();
                String newpassword = newpasswordtext.getText().toString();
                String confirmpassword = confirmpasswordtext.getText().toString();

                if (oldpassword.equals(Curentpassword))
                {
                    if (newpassword.equals(confirmpassword))
                    {
                        edit.putString("Masterpassword", newpassword);
                        edit.commit();
                        Toast.makeText(getActivity(), "Password Changed!", Toast.LENGTH_SHORT).show();

                    }else if(newpassword.equals(""))
                    {
                        Toast.makeText(getActivity(), "Enter The New Password First!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "New and Confirm Password didn't Match!", Toast.LENGTH_SHORT).show();
                    }

                }else if(oldpassword.equals(""))
                {
                    Toast.makeText(getActivity(), "Enter The Old Password First!", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getActivity(), "Old Password is Not Correct!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return rootview;
    }
}
