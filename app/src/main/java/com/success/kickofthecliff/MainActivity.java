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

import com.success.kickofthecliff.adapter.TabsFragmentAdapter;
import com.success.kickofthecliff.additional_classes.ScrollingActivity;
import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();
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

        new KickTask().execute();//выполнение метода

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


    private class KickTask extends AsyncTask<Void,KickWinterDTO , KickDTO[]> {//логика приложения

        @Override
        protected KickDTO[] doInBackground(Void... params) {

            RestTemplate template = new RestTemplate();//общение с сервером!!!!
            try{
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());//преобразование запросов
                publishProgress(template.getForObject(Constants.URL.GET_KICK_ITEM2, KickWinterDTO[].class));
                return template.getForObject(Constants.URL.GET_KICK_ITEM, KickDTO[].class);

            }
            catch (Exception e){
                Log.e("MainActivity",e.getMessage(),e);//ловим ошибку в логах
            }
            return null;
        }


        @Override
        protected void onPostExecute(KickDTO[] kickDTO) {//срабатывает после doInBackground
            List<KickDTO> list = new ArrayList<>();//получение списка объектов с сервера
            for(int i =0;i<kickDTO.length;i++) {
                list.add(kickDTO[i]);
            }
            adapter.setData(list);//проставление данных в адаптер

        }
        @Override
        protected void onProgressUpdate(KickWinterDTO... kickWinterDTOs) {
            super.onProgressUpdate(kickWinterDTOs);
            List<KickWinterDTO> list = new ArrayList<>();//получение списка объектов с сервера
            for(int i =0;i<kickWinterDTOs.length;i++) {
                list.add(kickWinterDTOs[i]);
            }
            adapter.setDataWinter(list);//проставление данных в адаптер

        }
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
