package com.success.kickofthecliff;

import android.app.Application;

import com.success.kickofthecliff.dto.KickApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Rfit extends Application{
    private static KickApi kickApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                    .baseUrl(Constants.URL.HOST) //Базовая часть адреса
                    .build();
            kickApi = retrofit.create(KickApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        }


    }

    public static KickApi getKickApi() {
        return kickApi;
    }

}
