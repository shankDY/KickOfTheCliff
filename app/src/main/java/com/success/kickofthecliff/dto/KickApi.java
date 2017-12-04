package com.success.kickofthecliff.dto;

import com.success.kickofthecliff.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface KickApi {
    /*
    @GET(Constants.URL.GET_SUMMER_ITEMS)
    Observable<List<KickSummerDTO>> getSummerData();

    @GET(Constants.URL.GET_WINTER_ITEMS)
    Observable<List<KickWinterDTO>> getWinterData();
*/

    @GET(Constants.URL.GET_SUMMER_ITEMS)
    Call<List<KickSummerDTO>> getSummerData();

    @GET(Constants.URL.GET_WINTER_ITEMS)
    Call<List<KickWinterDTO>> getWinterData();

}
