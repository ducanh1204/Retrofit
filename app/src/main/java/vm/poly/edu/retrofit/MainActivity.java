package vm.poly.edu.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vm.poly.edu.retrofit.model.Customer;
import vm.poly.edu.retrofit.model.Model;
import vm.poly.edu.retrofit.model.ResultToken;
import vm.poly.edu.retrofit.network.RetrofitService;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private Button btnGET, btnPOST, btnTestAuthorization1, btnTestAuthorization2;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tvData);
        btnGET = findViewById(R.id.btnGET);
        btnPOST = findViewById(R.id.btnPOST);
        btnTestAuthorization1 = findViewById(R.id.btnTestAuthorization1);
        btnTestAuthorization2 = findViewById(R.id.btnTestAuthorization2);

        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                retrofitService.getData().enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        tvData.setText(response.body().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
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

                retrofitService.postData("Xin chao", "Day la retrofit post", 12).enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        tvData.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btnTestAuthorization1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoAuthorization1();
            }
        });
        btnTestAuthorization2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoAuthorization2();
            }
        });
        getAccessToken();
    }

    private void getAccessToken() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo.viettek.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getAccessToken("admin","password","123456").enqueue(new Callback<ResultToken>() {
            @Override
            public void onResponse(Call<ResultToken> call, Response<ResultToken> response) {
                Log.e("access token",response.body().getAccessToken());
                accessToken = response.body().getAccessToken();
                Toast.makeText(MainActivity.this, accessToken, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResultToken> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void demoAuthorization1() {
        tvData.setText("");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo.viettek.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.demoAuthorization1(999, "Bearer " + accessToken)
                .enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.body() != null) {
                            tvData.setText(response.body().getCusFullName());
                        } else {
                            tvData.setText("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void demoAuthorization2() {
        tvData.setText("");
        BasicAuthInterceptor basicAuthInterceptor = new BasicAuthInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request authenticatedRequest = request.newBuilder()
                                .header("Authorization","Bearer "+accessToken).build();
                        return chain.proceed(authenticatedRequest);
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo.viettek.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.demoAuthorization2(999)
                .enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.body() != null) {
                            tvData.setText(response.body().getCusFullName());
                        } else {
                            tvData.setText("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}