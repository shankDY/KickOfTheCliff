package com.success.kickofthecliff.adapter;

/*Класс для табов*/
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;
import com.success.kickofthecliff.fragment.AbstractTabFragment;
import com.success.kickofthecliff.fragment.SummerFragment;
import com.success.kickofthecliff.fragment.WinterFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private SummerFragment summerFragment;
    private WinterFragment winterFragment;

    private List<KickDTO> data;//данный для SummerFragment
    private List<KickWinterDTO> dataWinter;//данный для WinterFragment

    public TabsFragmentAdapter(Context context, FragmentManager fm) {//конструктор
        super(fm);
        this.context = context;//создание табов
        this.data = new ArrayList<>();//делаем данные изначально пустыми
        this.dataWinter = new ArrayList<>();
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {//возвращает заголовок

        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {//возвращает таб по позиции
        return tabs.get(position);
    }

    @Override
    public int getCount() {//возврещает количество
        return tabs.size();
    }

    private void initTabsMap(Context context) {//создание табов и передача информации в них
        tabs = new HashMap<>();
        summerFragment = SummerFragment.getInstance(context, data);
        winterFragment = WinterFragment.getInstance(context, dataWinter);
        tabs.put(0, summerFragment);
        tabs.put(1, winterFragment);
    }

    public void setData(List<KickDTO> data) {
        this.data = data;
        summerFragment.refreshData(data);//обновление данных в фрагменте лето
    }
    public void setDataWinter(List<KickWinterDTO> dataWinter) {
        this.dataWinter = dataWinter;
        winterFragment.refreshData(dataWinter);//обновление данных в фрагменте лето
    }
}
