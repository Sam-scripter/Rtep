package com.example.rtep2.Test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rtep2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Test extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        drawerLayout = findViewById(R.id.drawer_layout);
//        fab = findViewById(R.id.fab);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
        }
//        View header = navigationView.getHeaderView(0);
//        ImageView navImage = header.findViewById(R.id.navImage);
//        TextView navName = header.findViewById(R.id.navName);
//        TextView navEmail = header.findViewById(R.id.navEmail);
//        /*
//        DBHelper dbHelper = new DBHelper(this);
//        Cursor cursor = dbHelper.getUser();
//        if (cursor.getCount() == 0){
//            Toast.makeText(this, "No Profile Details", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()){
//                navName.setText(""+cursor.getString(0));
//                navEmail.setText(""+cursor.getString(1));
//                byte[] imageByte = cursor.getBlob(2);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
//                navImage.setImageBitmap(bitmap);
//            }
//        }
//        */
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Test.this, UploadActivityTest.class);
//                startActivity(intent);
//            }
//        });
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new Tfragment_home()).commit();
//                break;
//            case R.id.nav_settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new Tfragment_settings()).commit();
//                break;
//            case R.id.nav_share:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new Tfragment_share()).commit();
//                break;
//            case R.id.nav_about:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new Tfragment_about()).commit();
//                break;
//            case R.id.nav_logout:
//                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
//                break;
//        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
