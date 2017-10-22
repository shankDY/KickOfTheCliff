package com.success.kickofthecliff.dto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SummerApi {
    @GET("summer")
    Call<List<KickDTO>> getSummerData();

    @GET("winter")
    Call<List<KickWinterDTO>> getWinterData();


}
