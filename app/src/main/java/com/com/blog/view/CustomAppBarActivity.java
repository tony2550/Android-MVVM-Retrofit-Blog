package com.com.blog.view;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.com.blog.R;
import com.com.blog.view.post.PostDetailActivity;

public class CustomAppBarActivity extends AppCompatActivity {

    private static final String TAG = "CustomAppBarActivity";

    protected void onAppBarSettings(boolean isBackButton, String title){
        Toolbar myToolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        ab.setDisplayHomeAsUpEnabled(isBackButton);
    }

    protected void onAppBarSettings(String title){
        Toolbar myToolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuWrite:
                Log.d(TAG, "onOptionsItemSelected: Posting");
                return true;

            case R.id.menuUserInfo:
                Log.d(TAG, "onOptionsItemSelected: UserInfo");
                return true;

            case R.id.menuLogout:
                Log.d(TAG, "onOptionsItemSelected: LogOut");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
