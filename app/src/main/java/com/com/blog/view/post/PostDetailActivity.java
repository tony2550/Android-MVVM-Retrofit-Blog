package com.com.blog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.model.Post;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.google.android.material.button.MaterialButton;


public class PostDetailActivity extends CustomAppBarActivity implements InitActivity {

    private static final String TAG = "PostDetailActivity";

    private PostDetailActivity mContext = PostDetailActivity.this;

    private MaterialButton btnDelete, btnUpdateForm;
    private TextView tvBox;
    private PostDetailViewModel postDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        init();
        initLr();
        initSetting();
    }

    @Override
    public void init() {
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdateForm = findViewById(R.id.btnUpdateForm);
        tvBox = findViewById(R.id.tvBox);
        postDetailViewModel = new ViewModelProvider(this).get(PostDetailViewModel.class);
    }

    @Override
    public void initLr() {

    }

    @Override
    public void initObserve() {
        postDetailViewModel.getPostOne().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                tvBox.append("id"+post.getId()+"\n");
                tvBox.append("title"+post.getTitle());
                tvBox.append("content"+post.getContent());
                tvBox.append("username"+post.getUser().getUsername());
                tvBox.append("created"+post.getCreated());
            }
        });
    }


    @Override
    public void initSetting() {
        onAppBarSettings(true, "Detail");
    }


    @Override
    public void initData() {
        Intent getIntent = getIntent();
        int postId = getIntent.getIntExtra("postId", 0);
        if(postId == 0) finish();

        postDetailViewModel.findById(postId);


    }
}