package com.com.blog.view.post;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.bean.LoginUser;
import com.com.blog.model.Post;
import com.com.blog.repository.PostRepository;
import com.com.blog.repository.dto.CMRespDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class PostDetailViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private MutableLiveData<Post> postOne = new MutableLiveData<>(); // 로그인 유저 관리 객체

//    public PostDetailViewModel() {
//        Post post = new Post();
//        postOne.setValue(post);
//    }

    public MutableLiveData<Post> getPostOne() {
        return postOne;
    }

    public void findById(int id) {
        Call<CMRespDto<Post>> call = PostRepository.retrofit.create(PostRepository.class).findById(id);
        call.enqueue(new Callback<CMRespDto<Post>>() {
            @Override
            public void onResponse(Call<CMRespDto<Post>> call, Response<CMRespDto<Post>> response) {
                CMRespDto<Post> cmPost = response.body();
                Log.d(TAG, "onResponse: " +cmPost.getData());
                postOne.setValue(cmPost.getData());

            }

            @Override
            public void onFailure(Call<CMRespDto<Post>> call, Throwable t) {

            }
        });
    }
}
