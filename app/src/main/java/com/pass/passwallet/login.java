package com.pass.passwallet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MadhavSomani on 3/24/2016.
 */
public class login extends Activity{
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masterpassword);

        final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = spf.edit();
        TextView text = (TextView)findViewById(R.id.textView2);
        final String Curentpassword = spf.getString("Masterpassword", "1234");
        counter = spf.getInt("counter", 3);
        if(!Curentpassword.equals("1234"))
        {
            text.setText("");
        }

      final EditText passwordfield = (EditText)findViewById(R.id.editText);
        final Button login = (Button)findViewById(R.id.button);
        final String pass = passwordfield.getText().toString();

        int sec = spf.getInt("sec", 30);

        if(counter==0) {

            Toast.makeText(login.this, "Passwallet Locked (Time Reaming : "+sec+ " Sec)", Toast.LENGTH_SHORT).show();
            finish();

        }

            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String pass = passwordfield.getText().toString();

                if(pass.equals(Curentpassword))
                {
                    Intent i = new Intent(login.this , MainActivity.class);
                    startActivity(i);
                    finish();

                }else if(pass.equals(""))
                {
                    Toast.makeText(login.this, "Enter The Master Password First!", Toast.LENGTH_SHORT).show();
                }else
                {

                    counter--;
                    if(counter==0)
                    {

                        Intent  i = new Intent(login.this , service.class);
                        startService(i);
                        final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(login.this);
                        final SharedPreferences.Editor edit = spf.edit();
                        edit.putInt("counter" , counter);
                        edit.commit();
                        finish();

                    }else
                    {
                        Toast.makeText(login.this, "Wrong Password , Remaining Try : "+counter, Toast.LENGTH_SHORT).show();
                        final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(login.this);
                        final SharedPreferences.Editor edit = spf.edit();
                        edit.putInt("counter" , counter);
                        edit.commit();
                    }

                }




            }
        });
    }


}
