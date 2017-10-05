package com.success.kickofthecliff.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.success.kickofthecliff.R;
import com.success.kickofthecliff.adapter.KickListAdapter;
import com.success.kickofthecliff.adapter.KickListWinterAdapter;
import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.dto.KickWinterDTO;
import com.success.kickofthecliff.fragment.AbstractTabFragment;

import java.util.List;

public class WinterFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_winter;

    private List<KickWinterDTO> data;//данные с сервера
    private KickListWinterAdapter adapter;

    public static WinterFragment getInstance(Context context, List<KickWinterDTO> data){//получение экземпляра SummerFragment
        Bundle args = new Bundle();
        WinterFragment fragment = new WinterFragment();
        fragment.setArguments(args);
        fragment.setData(data);//передача данных от сервера
        fragment.setContext(context);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//создание вью
        view = inflater.inflate(LAYOUT, container, false);//наполнение вью данными

        RecyclerView rv2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        rv2.setLayoutManager(new LinearLayoutManager(context));
        adapter = new KickListWinterAdapter(data);//передача данных в нужный адаптер
        rv2.setAdapter(adapter);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<KickWinterDTO> data) {
        this.data = data;
    }

    public void refreshData(List<KickWinterDTO> data){//обновляет данные, делает перерисовку
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
