package com.com.blog.repository;

import com.com.blog.bean.SessionInterceptor;
import com.com.blog.model.Post;
import com.com.blog.repository.dto.CMRespDto;
import com.com.blog.repository.dto.PostDto;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
// 20210710075847
// http://localhost:8080/init/post

//{
//        "code": 1,
//        "msg": "목록보기완료",
//        "data": [
//        {
//        "id": 5,
//        "title": "제목5",
//        "content": "내용5",
//        "user": {
//        "id": 2,
//        "username": "cos",
//        "password": "1234",
//        "email": "cos@nate.com",
//        "created": "2021-07-10T07:56:50.198496",
//        "updated": "2021-07-10T07:56:50.198496"
//        },
//        "created": "2021-07-10T07:56:50.207467",
//        "updated": "2021-07-10T07:56:50.207467"
//        }


public interface PostRepository {

    @GET("/post") // 게시글 목록보기
    Call<CMRespDto<List<Post>>> findAll();

    @GET("/post/{id}") // 게시글 상세보기
    Call<CMRespDto<Post>> findById(@Path("id") int id);

    @POST("/post") // 게시글 한건 쓰기
    Call<CMRespDto> writePost(@Body PostDto postDto);

    @PUT("/post/{id}") // 게세글 한건 수정하기
    Call<CMRespDto> updatePost(@Path("id") int id, @Body PostDto postDto);

    @DELETE("/post/{id}") // 게시글 한건 삭제하기
    Call<CMRespDto> deletePost(@Path("id") int id);

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new SessionInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.100.202.24:8077")
            .build();

    PostRepository service = retrofit.create(PostRepository.class);
}
