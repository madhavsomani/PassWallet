package com.pass.passwallet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    TextView text1,text2,text3;
    databasehelper db;
    FloatingActionButton fab ;
    Button fab3 , fab1 , fab2;
    private Boolean isFabOpen = true;
    Animation fab_open , fab_close , rotate_forward , rotate_backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new databasehelper(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fm = new site();
        FragmentManager manger = getFragmentManager();
        manger.beginTransaction().replace(R.id.container , fm ).commit();



        //////////////////////////Fab Animation

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (Button)findViewById(R.id.card);
        fab2 = (Button)findViewById(R.id.note);
        fab3 = (Button)findViewById(R.id.site);
         text1 = (TextView)findViewById(R.id.textaddcardfab);
         text2 = (TextView)findViewById(R.id.textaddnotefab);
         text3 = (TextView)findViewById(R.id.textaddsitefab);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        animateFAB();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.card:

                Fragment fm = new addcard();
                FragmentManager manager = getFragmentManager();

                manager.beginTransaction().replace(R.id.container,fm).commit();
                animateFAB();
                Log.d("Raj", "Fab 1");
                break;
            case R.id.note:

                Fragment fm2 = new addnote();
                FragmentManager manager2 = getFragmentManager();
                manager2.beginTransaction().replace(R.id.container,fm2).commit();
                animateFAB();
                Log.d("Raj", "Fab 2");
                break;
            case R.id.site:

                Fragment fm3 = new addsite();
                FragmentManager manager3 = getFragmentManager();

                manager3.beginTransaction().replace(R.id.container,fm3).commit();
                animateFAB();
                Log.d("Raj", "Fab 2");
                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            text1.startAnimation(fab_close);
            text2.startAnimation(fab_close);
            text3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            text1.setClickable(false);
            text2.setClickable(false);
            text3.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            text1.startAnimation(fab_open);
            text2.startAnimation(fab_open);
            text3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            text1.setClickable(true);
            text2.setClickable(true);
            text3.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }




        //////////////////////


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
            } else {
                getFragmentManager().popBackStack();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            Intent i = new Intent(MainActivity.this , login.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_site) {

            Fragment fm = new site();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container , fm ).commit();
            fab.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_note) {
            Fragment fm = new note();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container , fm ).commit();
            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_debitcard) {
            Fragment fm = new card();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container , fm ).commit();
            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_master_password) {
            Fragment fm = new passwordchange();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container, fm).addToBackStack(null).commit();

        } else if (id == R.id.nav_password_generator) {

            Fragment fm = new passwordgenerator();
            FragmentManager manger = getFragmentManager();
            manger.beginTransaction().replace(R.id.container , fm ).addToBackStack(null).commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
