package com.example.orderpad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class NewOrdersTest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , MainFragment.onFragmentSelected {

    //variables
    DrawerLayout dl;
    ActionBarDrawerToggle actionBarDrawerToggle;
    androidx.appcompat.widget.Toolbar tb;
    NavigationView nv;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    /*
    To load the fragment to any activitiy, we need two classes
    Fragment Manager is used to create an instacne of a fragment, and fragment Transaction
    will perform the fragemnt
     */
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button signOutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders_test);

        dl = findViewById(R.id.drawer_layout);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        nv = findViewById(R.id.navigation_view);
        nv.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, tb,
                R.string.open, R.string.close);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
//        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
//        signOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i = new Intent(NewOrdersTest.this, RegisterActivity.class);
//                startActivity(i);
//            }
//        });


        /*
        Fragment to be loaded into the main activity
        Default fragment in activity
         */
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new MainFragment());
        fragmentTransaction.commit();

}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        dl.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId() == R.id.orders_page) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new MainFragment());
            fragmentTransaction.commit();

        }

        if (menuItem.getItemId() == R.id.another_page) {

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new SecondFragment());
            fragmentTransaction.commit();
        }
        return true;
    }

    @Override
    public void onButtonSelected() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, new SecondFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onSignOut() {
        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(NewOrdersTest.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }


}