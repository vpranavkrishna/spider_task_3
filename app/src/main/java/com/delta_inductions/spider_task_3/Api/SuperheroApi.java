package com.delta_inductions.spider_task_3.Api;

import com.delta_inductions.spider_task_3.Model.Superhero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperheroApi {
    @GET("all.json")
    Call<List<Superhero>> getAll();
}
