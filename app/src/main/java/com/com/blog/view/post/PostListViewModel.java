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

// 데이터 받는
public class PostListViewModel extends ViewModel {

    private static final String TAG = "PostListModel";
    private MutableLiveData<List<Post>> ldPosts = new MutableLiveData<>();

    public PostListViewModel() {
        List<Post> posts = new ArrayList<>();
        ldPosts.setValue(posts);
    }

    public MutableLiveData<List<Post>> getPosts() {
        return ldPosts;
    }

    public void findAll() {
        Call<CMRespDto<List<Post>>> call = PostRepository.retrofit.create(PostRepository.class).findAll();
        call.enqueue(new Callback<CMRespDto<List<Post>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Post>>> call, Response<CMRespDto<List<Post>>> response) {
                CMRespDto<List<Post>> cmPosts = response.body();
                Log.d(TAG, "onResponse: " +cmPosts.getData());
                ldPosts.setValue(cmPosts.getData());
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Post>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage()); // toast로 바꿔야함
            }
        });
    }
    

}
