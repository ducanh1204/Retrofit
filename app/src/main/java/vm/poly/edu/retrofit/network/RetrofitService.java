package vm.poly.edu.retrofit.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vm.poly.edu.retrofit.model.Customer;
import vm.poly.edu.retrofit.model.Model;
import vm.poly.edu.retrofit.model.ResultToken;

public interface RetrofitService {

    @GET("posts")
    Call<List<Model>> getData();

    @POST("posts")
    @FormUrlEncoded
    Call<Model> postData(@Field("title") String title,
                         @Field("body") String body,
                         @Field("userId") int userId);

    @POST("oauth/token")
    @FormUrlEncoded
    Call<ResultToken> getAccessToken(@Field("username") String username,
                                     @Field("grant_type") String grant_type,
                                     @Field("password") String password);

    @GET("api/customers/getbyid/{id}")
    Call<Customer> demoAuthorization1(@Path("id") long id, @Header("Authorization") String authHeader);

    @GET("api/customers/getbyid/{id}")
    Call<Customer> demoAuthorization2(@Path("id") long id);
}
