package com.success.kickofthecliff.BottomActivities;

/** Главное активити**/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.success.kickofthecliff.R;
import com.success.kickofthecliff.Rfit;
import com.success.kickofthecliff.adapter.TabsFragmentAdapter;
import com.success.kickofthecliff.additional_classes.ScrollingActivity;
import com.success.kickofthecliff.dto.KickSummerDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_home;

    @Override
    int getContentViewId() {
        return LAYOUT;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;
    List<KickSummerDTO> kickSummerDTO;
    List<KickWinterDTO> winterDTO;
    private ImageView im;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);

        kickSummerDTO = new ArrayList<>();
        winterDTO = new ArrayList<>();
        initToolbar();
        initTabs();




/*отображение данных*/
  Rfit.getKickApi().getSummerData().enqueue(new Callback<List<KickSummerDTO>>() {
            @Override
            public void onResponse(Call<List<KickSummerDTO>> call, Response<List<KickSummerDTO>> response) {
                kickSummerDTO.addAll(response.body());
                adapter.setData(kickSummerDTO);//проставление данных в адаптер

            }

            @Override
            public void onFailure(Call<List<KickSummerDTO>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        Rfit.getKickApi().getWinterData().enqueue(new Callback<List<KickWinterDTO>>() {
            @Override
            public void onResponse(Call<List<KickWinterDTO>> call, Response<List<KickWinterDTO>> response) {
                winterDTO.addAll(response.body());
                adapter.setDataWinter(winterDTO);//проставление данных в адаптер

            }

            @Override
            public void onFailure(Call<List<KickWinterDTO>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void initToolbar() {//создание тулбара
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //Add tabs icon with setIcon() or simple text with .setText()
        tabLayout.getTabAt(0).setText(R.string.tab_item_summer);
        tabLayout.getTabAt(1).setText(R.string.tab_item_winter);

    }


    /*Метод для перехода и передачи данных на другое активити*/
    public static void start(Context context, int position, List<KickSummerDTO> data){//переход
        Intent intent=new Intent(context, ScrollingActivity.class);
        /*передача данных в другое активити*/
        intent.putExtra("title", data.get(position).getTitle());
        intent.putExtra("photo", data.get(position).getPhoto());
        intent.putExtra("text", data.get(position).getKickInfo());
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

    public static void startWinter(Context context, int position, List<KickWinterDTO> data){//переход
        Intent intent=new Intent(context, ScrollingActivity.class);
        intent.putExtra("title", data.get(position).getWinter_title());
        intent.putExtra("photo", data.get(position).getWinter_photo());
        intent.putExtra("text", data.get(position).getWinter_kickInfo());
        context.startActivity(intent);
    }



}
