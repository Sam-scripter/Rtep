package com.example.rtep2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rtep2.Sign_in_up.register;
import com.example.rtep2.Sign_in_up.register_department;
import com.example.rtep2.Sign_in_up.register_vehicle;
import com.example.rtep2.Test.HomeFragment;
import com.example.rtep2.Test.NotificationsFragment;
import com.example.rtep2.Test.Tfragment_home;
import com.example.rtep2.user_fragment_roles.RepairFragment;
import com.example.rtep2.user_fragment_roles.TransportFragment;
import com.example.rtep2.user_fragment_roles.adminFragment;
import com.example.rtep2.user_fragment_roles.driverFragment;
import com.example.rtep2.user_fragment_roles.superUserFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Overview extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    String accessToken;
    String role;
    String designation;
    Boolean isSuperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Retrieve the access token from intent extras
        accessToken = getIntent().getStringExtra("access_token");


        role = getIntent().getStringExtra("role");

        designation = getIntent().getStringExtra("designation");

        isSuperUser = getIntent().getBooleanExtra("is_superuser", false);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            if (isSuperUser) {
                superUserFragment fragment = superUserFragment.newInstance();
                Bundle args = new Bundle();
                args.putString("access_token", accessToken);
                Log.d("Access token again", accessToken);
                args.putString("designation", designation);
                fragment.setArguments(args);
                replaceFragment(fragment, "Super user dashboard");
                navigationView.setCheckedItem(R.id.nav_home);
            } else if ("admin".equals(role)) {
                adminFragment fragment = adminFragment.newInstance();
                Bundle args = new Bundle();
                args.putString("access_token", accessToken);
                Log.d("Access token again", accessToken);
                args.putString("designation", designation);
                fragment.setArguments(args);
                replaceFragment(fragment, "admin dashboard");
                navigationView.setCheckedItem(R.id.nav_home);
            } else if ("employee".equals(role)) {
                if ("Transport Officer".equals(designation)) {
                    TransportFragment fragment = TransportFragment.newInstance();
                    Bundle args = new Bundle();
                    args.putString("access_token", accessToken);
                    Log.d("Access token again", accessToken);
                    args.putString("designation", designation);
                    fragment.setArguments(args);
                    replaceFragment(fragment, "Transport dashboard");
                    navigationView.setCheckedItem(R.id.nav_home);
                } else if ("Repair Manager".equals(designation)) {
                    RepairFragment fragment = RepairFragment.newInstance();
                    Bundle args = new Bundle();
                    args.putString("access_token", accessToken);
                    Log.d("Access token again", accessToken);
                    args.putString("designation", designation);
                    fragment.setArguments(args);
                    replaceFragment(fragment, "repair dashboard");
                    navigationView.setCheckedItem(R.id.nav_home);
                }
            }

            bottomNavigationView.setBackground(null);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.home:
                        if (isSuperUser) {
                            superUserFragment superUserFrag = superUserFragment.newInstance();
                            Bundle args = new Bundle();
                            args.putString("access_token", accessToken);
                            Log.d("Access token again", accessToken);
                            args.putString("designation", designation);
                            superUserFrag.setArguments(args);
                            replaceFragment(superUserFrag, "Dashboard");
                        } else if ("admin".equals(role)) {
                            adminFragment adminFrag = adminFragment.newInstance();
                            Bundle args = new Bundle();
                            args.putString("access_token", accessToken);
                            Log.d("Access token again", accessToken);
                            args.putString("designation", designation);
                            adminFrag.setArguments(args);
                            replaceFragment(adminFrag, "Dashboard");
                        } else if ("employee".equals(role)) {
                            if ("Transport Officer".equals(designation)) {
                                TransportFragment TransportFrag = TransportFragment.newInstance();
                                Bundle args = new Bundle();
                                args.putString("access_token", accessToken);
                                Log.d("Access token again", accessToken);
                                args.putString("designation", designation);
                                TransportFrag.setArguments(args);
                                replaceFragment(TransportFrag, "Dashboard");
                            } else if ("Repair Manager".equals(designation)) {
                                RepairFragment RepairFrag = RepairFragment.newInstance();
                                Bundle args = new Bundle();
                                args.putString("access_token", accessToken);
                                Log.d("Access token again", accessToken);
                                args.putString("designation", designation);
                                RepairFrag.setArguments(args);
                                replaceFragment(RepairFrag, "Dashboard");
                            } else if ("driver".equals(designation)){
                                driverFragment driverFrag = driverFragment.newInstance();
                                Bundle args = new Bundle();
                                args.putString("access_token", accessToken);
                                Log.d("Access token again", accessToken);
                                args.putString("designation", designation);
                                driverFrag.setArguments(args);
                                replaceFragment(driverFrag, "Dashboard");
                            }
                        }
                        break;
                    case R.id.settings:
                        SettingsFragment settingsFragment = SettingsFragment.newInstance(accessToken);
                        replaceFragment(settingsFragment, "Settings");
                        break;
                    case R.id.reports:
//                        ReportsFragment reportsFragment = ReportsFragment.newInstance(accessToken);
//                        replaceFragment(reportsFragment);
                        break;
                    case R.id.notifications:
                        NotificationsFragment notificationsFragment = NotificationsFragment.newInstance(accessToken);
                        replaceFragment(notificationsFragment, "Notifications");
                        break;
                }
                return true;
            });

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showBottomDialog();
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (isSuperUser) {
                    superUserFragment superUserFragm = superUserFragment.newInstance();
                    Log.d("opened super user", "Here i am superuser");
                    Log.d("access token here:", accessToken);
                    replaceFragment(superUserFragm, "Dashboard");
                } else if ("admin".equals(role)) {
                    adminFragment adminFragm = adminFragment.newInstance();
                    replaceFragment(adminFragm, "Dashboard");
                } else if ("employee".equals(role)) {
                    if ("Transport Officer".equals(designation)) {
                        HomeFragment homeFragment = new HomeFragment();
                        replaceFragment(homeFragment, "Dashboard");
                    } else if ("Repair Manager".equals(designation)) {
                        HomeFragment homeFragment = new HomeFragment();
                        replaceFragment(homeFragment, "Dashboard");
                    }
                }
                break;
            case R.id.nav_settings:
                SettingsFragment settingsFragm = SettingsFragment.newInstance(accessToken);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                        settingsFragm).commit();
                break;
            case R.id.nav_profile:
                Log.d("cClicked", "profile is clicked");
                superUserFragment superUserFragm = superUserFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                        superUserFragm).commit();
                break;
            case R.id.nav_orders:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                        homeFragment).commit();;
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void replaceFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
        setToolbarTitle(title); // Set the title on the Toolbar
    }


    private void showBottomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout fuelOrder = dialog.findViewById(R.id.fuelOrder);
        LinearLayout repairOrder = dialog.findViewById(R.id.repairOrder);
        LinearLayout AddEmployee = dialog.findViewById(R.id.addEmployee);
        LinearLayout AddDepartment = dialog.findViewById(R.id.addDepartment);
        LinearLayout AddVehicle = dialog.findViewById(R.id.addVehicle);

        fuelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Overview.this, "Fuel order is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        repairOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Overview.this, "Repair order is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        AddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Overview.this, register.class);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        AddDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Overview.this, register_department.class);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        AddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Overview.this, register_vehicle.class);
                intent.putExtra("access_token", accessToken);
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}
