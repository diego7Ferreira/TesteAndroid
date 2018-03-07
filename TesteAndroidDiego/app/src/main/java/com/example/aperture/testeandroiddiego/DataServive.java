package com.example.aperture.testeandroiddiego;

import com.example.aperture.testeandroiddiego.models.Dados;
import com.example.aperture.testeandroiddiego.models.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aperture on 06/03/2018.
 */

public interface DataServive {

    public static final String URL_BASE = "https://s3-sa-east-1.amazonaws.com/pontotel-docs/";

    @GET("data.json")
    Call<Dados> getData();
}
