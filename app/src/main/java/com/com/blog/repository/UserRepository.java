package com.com.blog.repository;

import com.com.blog.bean.SessionInterceptor;
import com.com.blog.model.User;
import com.com.blog.repository.dto.CMRespDto;
import com.com.blog.repository.dto.JoinDto;
import com.com.blog.repository.dto.LoginDto;
import com.com.blog.repository.dto.UpdateDto;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserRepository {
    @POST("/join") // service
    Call<CMRespDto<User>> join(@Body JoinDto joinDto);

    @POST("/login") // service
    Call<CMRespDto<User>> login(@Body LoginDto loginDto);

    @GET("/user/{id}") // 회원정보 한건보기 인증 필 service2
    Call<CMRespDto<User>> findById(@Path("id") int id);

    @PUT("/user/{id}") // 회원정보 수정 인증 필 service2
    Call<CMRespDto> updateUser(@Path("id") int id, @Body UpdateDto updateDto);

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.100.202.24:8077")
            .build();

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new SessionInterceptor()).build();

    Retrofit retrofit2 = new Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.219.154:8077")
            .build();

    UserRepository service = retrofit.create(UserRepository.class);

    UserRepository service2 = retrofit2.create(UserRepository.class);
}
