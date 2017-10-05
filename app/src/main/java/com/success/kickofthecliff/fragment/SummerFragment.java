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
import com.success.kickofthecliff.dto.KickDTO;
import com.success.kickofthecliff.fragment.AbstractTabFragment;

import java.util.List;

public class SummerFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_summer;

    private List<KickDTO> data;//данные с сервера
    private KickListAdapter adapter;

    public static SummerFragment getInstance(Context context, List<KickDTO> data){//получение экземпляра SummerFragment
        Bundle args = new Bundle();
        SummerFragment fragment = new SummerFragment();
        fragment.setArguments(args);
        fragment.setData(data);//передача данных от сервера
        fragment.setContext(context);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//создание вью
        view = inflater.inflate(LAYOUT, container, false);//наполнение вью данными

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new KickListAdapter(data);//передача данных в нужный адаптер
        rv.setAdapter(adapter);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<KickDTO> data) {
        this.data = data;
    }

    public void refreshData(List<KickDTO> data){//обновляет данные, делает перерисовку
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
