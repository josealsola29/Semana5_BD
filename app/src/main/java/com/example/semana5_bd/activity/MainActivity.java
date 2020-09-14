package com.example.semana5_bd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.semana5_bd.PerfilFragment;
import com.example.semana5_bd.R;
import com.example.semana5_bd.RecyclerViewFragment;
import com.example.semana5_bd.adapter.PetPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.miActionBar);
        tabLayout = findViewById(R.id.tlPets);
        viewPager2 = findViewById(R.id.vp);

        setSupportActionBar(toolbar);
        setUpViewPaper();

    }

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPaper() {
        viewPager2.setAdapter(new PetPageAdapter(this, agregarFragment()));
        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position ==0)
                            tab.setIcon(R.drawable.ic_baseline_home);
                        else
                            tab.setIcon(R.drawable.icons8_jake);
                    }
                }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pets, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.menu_contact:
                intent = new Intent(this, Contacto.class);
//            intent.putStringArrayListExtra("favs",)
                startActivity(intent);
                break;

            case R.id.menu_about:
                intent = new Intent(this, AcercaDe.class);
//            intent.putStringArrayListExtra("favs",)
                startActivity(intent);
                break;
            case R.id.menu_favoritePets:
                intent = new Intent(this, TopFavoritesPets.class);
//            intent.putStringArrayListExtra("favs",)
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}