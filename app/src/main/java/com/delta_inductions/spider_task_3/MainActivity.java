package com.delta_inductions.spider_task_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.delta_inductions.spider_task_3.Adapter.Onitemclicklistener;
import com.delta_inductions.spider_task_3.Adapter.SuperheroAdapter;
import com.delta_inductions.spider_task_3.Api.SuperheroApi;
import com.delta_inductions.spider_task_3.Model.Superhero;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Onitemclicklistener {
private static final String BASE_URL= "https://akabab.github.io/superhero-api/api/";
private RecyclerView Herolist;
private LinearLayoutManager manager;
private SuperheroAdapter superheroAdapter;
private ArrayList<Superhero> superheroArrayList;
    private Retrofit retrofit;
    private SuperheroApi superheroApi;
    private String herodetail;
    private String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Herolist = findViewById(R.id.recyclerview);
        getRetrofit();
        superheroApi = getRetrofit();
        getSuper();
    }

    private void getSuper() {
        Call<List<Superhero>> call= superheroApi.getAll();
        call.enqueue(new Callback<List<Superhero>>() {
            @Override
            public void onResponse(Call<List<Superhero>> call, Response<List<Superhero>> response) {
                List<Superhero> list = response.body();
                superheroArrayList = new ArrayList<>(list);
                manager = new LinearLayoutManager(MainActivity.this);
                superheroAdapter = new SuperheroAdapter(MainActivity.this,superheroArrayList);
                Herolist.setLayoutManager(manager);
                Herolist.setAdapter(superheroAdapter);
                superheroAdapter.setOnitemclicklistenerList(MainActivity.this);
            }

            @Override
            public void onFailure(Call<List<Superhero>> call, Throwable t) {

            }
        });

    }

    private SuperheroApi getRetrofit() {

            if(retrofit==null)
            {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            return (retrofit.create(SuperheroApi.class));
        }



    @Override
    public void itemclickList(int position) {
        Superhero superhero = superheroArrayList.get(position);
        herodetail = "Name :" + superhero.getName() +"\n"+"Slug :"+superhero.getSlug()+"\n\n"+"PowerStats"+"\n"+"Intelligence :"+superhero.getPowerstats().getIntelligence()
                +"\n" + "Strength :"+superhero.getPowerstats().getStrength()+"\n"+"Speed :" +superhero.getPowerstats().getSpeed()+"\n"+"Durability :"+superhero.getPowerstats().getDurability()
                +"\n"+ "Power :"+superhero.getPowerstats().getPower()+"\n"+"Combat :"+superhero.getPowerstats().getCombat()+"\n\n"+"Appearance"+"\n"+"Gender :"+superhero.getAppearance().getGender()
                +"\n" + "Race :"+ superhero.getAppearance().getRace()+"\n"+"Height :"+ Arrays.toString(superhero.getAppearance().getHeight()) +"\n" +"Weight :"+ Arrays.toString(superhero.getAppearance().getWeight())+"\n"+
                "Eyecolor :"+superhero.getAppearance().getEyeColor()+"\n"+"Haircolor :"+superhero.getAppearance().getHairColor()+"\n\n"+"Biography"+"\n"+"Fullname :"+superhero.getBiography().getFullName()+"\n"+"AlterEgos :" + superhero.getBiography().getAlterEgos()+"\n"
        +"Aliases :"+ Arrays.toString(superhero.getBiography().getAliases()) +"\n"+"Place of Birth :"+ superhero.getBiography().getPlaceOfBirth()+"\n"+"First Appearance :"+superhero.getBiography().getFirstAppearance()+"\n"+"Publisher :"+superhero.getBiography().getPublisher()+"\n"
        +"Alignment :"+superhero.getBiography().getAlignment()+"\n\n"+"Work"+"\n"+"Occupation :"+superhero.getWork().getOccupation()+"\n"+"Base :"+superhero.getWork().getBase()+"\n\n"+"Connections"+"\n"+"GroupAffiliation :"+superhero.getConnections().getGroupAffiliation()+"\n"
        +"Relatives :"+superhero.getConnections().getRelatives();
        Url = superhero.getImage().getURl();
        Intent intent = new Intent(this,HeroDetail.class);
        intent.putExtra("herodetail",herodetail);
        intent.putExtra("Url",Url);
        startActivity(intent);
    }

}