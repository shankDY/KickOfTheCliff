package com.success.kickofthecliff;

/** Главное активити**/

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.success.kickofthecliff.adapter.TabsFragmentAdapter;
import com.success.kickofthecliff.additional_classes.ScrollingActivity;
import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;
    List<KickDTO> kickDTO;
    List<KickWinterDTO> winterDTO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        kickDTO = new ArrayList<>();
        winterDTO = new ArrayList<>();
        initToolbar();
        initNavigationView();
        initTabs();


        /*отображение данных*/
        Rfit.getSummerApi().getSummerData().enqueue(new Callback<List<KickDTO>>() {
            @Override
            public void onResponse(Call<List<KickDTO>> call, Response<List<KickDTO>> response) {
                kickDTO.addAll(response.body());
                adapter.setData(kickDTO);//проставление данных в адаптер

            }

            @Override
            public void onFailure(Call<List<KickDTO>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

        Rfit.getSummerApi().getWinterData().enqueue(new Callback<List<KickWinterDTO>>() {
            @Override
            public void onResponse(Call<List<KickWinterDTO>> call, Response<List<KickWinterDTO>> response) {
                winterDTO.addAll(response.body());
                adapter.setDataWinter(winterDTO);//проставление данных в адаптер

            }

            @Override
            public void onFailure(Call<List<KickWinterDTO>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initToolbar() {//создание тулбара
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initTabs() {//создание табов
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

       // new KickTask().execute();//выполнение метода

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //Add tabs icon with setIcon() or simple text with .setText()
        tabLayout.getTabAt(0).setIcon(R.drawable.white_balance_sunny);
        tabLayout.getTabAt(1).setIcon(R.drawable.snowflake);

    }

    private void initNavigationView() {//создание навигатион вью
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //кнопочка для вызова бокового меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    /*Метод для перехода и передачи данных на другое активити*/
    public static void start(Context context, int position, List<KickDTO> data){//переход
        Intent intent=new Intent(context, ScrollingActivity.class);
        /*передача данных в другое активити*/
        intent.putExtra("title", data.get(position).getTitle());
        intent.putExtra("photo", data.get(position).getPhoto());
        intent.putExtra("text", data.get(position).getKickInfo());
        context.startActivity(intent);
    }

    public static void startWinter(Context context, int position, List<KickWinterDTO> data){//переход
        Intent intent=new Intent(context, ScrollingActivity.class);
        /*передача данных в другое активити*/
        intent.putExtra("title", data.get(position).getWinter_title());
        intent.putExtra("photo", data.get(position).getWinter_photo());
        intent.putExtra("text", data.get(position).getWinter_kickInfo());
        context.startActivity(intent);
    }



}
