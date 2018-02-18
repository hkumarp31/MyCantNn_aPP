package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.hario.mycantnn_app.Modal.CartActivity;
import com.example.hario.mycantnn_app.Modal.ClientNotificationActivity;
import com.example.hario.mycantnn_app.Modal.Fivefragment;
import com.example.hario.mycantnn_app.Modal.Fourfragment;
import com.example.hario.mycantnn_app.Modal.OrderStatus;
import com.example.hario.mycantnn_app.Modal.RecyclerInfo;
import com.example.hario.mycantnn_app.Modal.Sixfragment;
import com.example.hario.mycantnn_app.Modal.Threefragment;
import com.example.hario.mycantnn_app.Modal.Twofragment;

import java.util.ArrayList;
import java.util.List;

public class client extends AppCompatActivity {
    public static ArrayList<RecyclerInfo> mailAddedItems;
    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mailAddedItems = new ArrayList<>();
        toolbar = findViewById(R.id.toolbarr);
        // setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        break;

                    case R.id.navigation_OrderStatus12:

                        Intent intent1 = new Intent(client.this, OrderStatus.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_cart:

                        Intent intent2 = new Intent(client.this, CartActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_profile:
                        Intent intent4 = new Intent(client.this, profile.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.ClientNotificationBottomNavigation:
                        startActivity(new Intent(client.this, ClientNotificationActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                }
                return false;
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecyclerActivity(), "SNACKS");
        adapter.addFragment(new Twofragment(), "CAKES/CREAM");
        adapter.addFragment(new Threefragment(), "DRINKS");
        adapter.addFragment(new Fourfragment(), "SWEETS");
        adapter.addFragment(new Fivefragment(), "DISHES");
        adapter.addFragment(new Sixfragment(), "OTHER ITEMS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
