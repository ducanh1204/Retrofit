package vm.poly.edu.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private Button btnGET, btnPOST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tvData);
        btnGET = findViewById(R.id.btnGET);
        btnPOST = findViewById(R.id.btnPOST);


        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.getData().enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        tvData.setText(response.body().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        btnPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                retrofitService.postData("Xin chao","Day la retrofit post",12).enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        tvData.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}