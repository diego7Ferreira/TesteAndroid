package com.example.aperture.testeandroiddiego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aperture.testeandroiddiego.models.Dados;
import com.example.aperture.testeandroiddiego.models.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Variaveis para atualizar os arquivos na ListView
    ListView lista;
    ArrayList<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo odbotãode recarregar
    public void recarrega(View view){

        lista = findViewById(R.id.listViewID);
        users = new ArrayList<String>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataServive.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataServive service = retrofit.create(DataServive.class);
        Call<Dados> requestData = service.getData();

        requestData.enqueue(new Callback<Dados>() {
            @Override
            public void onResponse(Call<Dados> call, Response<Dados> response) {
                if(response.isSuccessful()){
                    Dados dados = response.body();

                    //Passa todas as string coletadas para um Array de String 'ArrayList<String> users'
                    for(Data d : dados.data){
                        users.add("ID: " + d.getId() + "\n"
                                + "Name: " + d.getName() + "\n"
                                + "Pwd: " + d.getPwd());

                        //Teste de saida pelo termina
                        //Log.i("ID: " , d.getId());
                        //Log.i("Name: " , d.getName());
                        //Log.i("Pwd: " , d.getPwd());
                    }

                    //Prepara o adapter para a ListView
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,
                            users
                    );

                    //Atualiza o ListView
                    lista.setAdapter(adapter);

                }else{
                    //Teste de saida pelo termina
                    //Log.i("Erro: " , response.message());
                    // Tratamento de erro ...
                }
            }
            @Override
            public void onFailure(Call<Dados> call, Throwable t) {
                //Teste de saida pelo termina
                //Log.i("Erro: ", t.getMessage());
                // Tratamento de erro ...
            }
        });
    }
}
