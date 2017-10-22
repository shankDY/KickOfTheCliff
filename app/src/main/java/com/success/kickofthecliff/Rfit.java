package com.success.kickofthecliff;

import android.app.Application;

import com.success.kickofthecliff.dto.SummerApi;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Rfit extends Application{
    private static SummerApi summerApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.104:8080/") //Базовая часть адреса
                    .addConverterFactory(JacksonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                    .build();
            summerApi = retrofit.create(SummerApi.class); //Создаем объект, при помощи которого будем выполнять запросы
        }
    }

    public static SummerApi getSummerApi() {
        return summerApi;
    }

}
