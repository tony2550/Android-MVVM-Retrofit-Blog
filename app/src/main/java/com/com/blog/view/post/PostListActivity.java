package com.com.blog.view.post;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.blog.R;
import com.com.blog.model.Post;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.adapter.PostListAdapter;

import java.util.List;

public class PostListActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostListActivity";
    private PostListActivity mContext = PostListActivity.this;

    private RecyclerView rvPostList;
    private RecyclerView.LayoutManager rvLayoutManager;

    private PostListAdapter postListAdapter;

    private PostListViewModel postListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        init();
        initData();
        initObserve();
        initLr();
        initSetting();
        initAdapter();
    }

    @Override
    public void init() {
//        postController = new PostController();
        rvPostList = findViewById(R.id.rvPostList);
        postListViewModel = new ViewModelProvider(this).get(PostListViewModel.class);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initObserve() {
        postListViewModel.getPosts().observe(this, new Observer<List<Post>>() {

            @Override
            public void onChanged(List<Post> posts) {
                postListAdapter.setPosts(posts);
            }
        });
    }

    @Override
    public void initSetting() {
        onAppBarSettings("Home");
    }

    @Override
    public void initAdapter() {
        rvLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rvPostList.setLayoutManager(rvLayoutManager);

        postListAdapter = new PostListAdapter(mContext);
        rvPostList.setAdapter(postListAdapter);
    }

    @Override
    public void initData() {
        postListViewModel.findAll();
    }
}