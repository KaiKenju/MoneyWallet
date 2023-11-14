package vn.edu.usth.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import vn.edu.usth.moneywallet.adapter.AdapterViewPager;
import vn.edu.usth.moneywallet.fragment.FragmentFavorite;
import vn.edu.usth.moneywallet.fragment.FragmentHome;
import vn.edu.usth.moneywallet.fragment.FragmentUser;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerMain = findViewById(R.id.pagerMain);
        bottomNav = findViewById(R.id.bottomNav);

        fragmentArrayList.add(new FragmentHome());
        fragmentArrayList.add(new FragmentFavorite());
        fragmentArrayList.add(new FragmentUser());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        //set adapter
        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.setSelectedItemId(R.id.itHome);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.itFavorite);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.itUser);
                        break;
                }

                super.onPageSelected(position);

            }
        });
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itHome:
                        pagerMain.setCurrentItem(0);
                        break;
                    case R.id.itFavorite:
                        pagerMain.setCurrentItem(1);
                        break;
                    case R.id.itUser:
                        pagerMain.setCurrentItem(2);
                        break;

                }
                return true;
            }
        });


    }
}