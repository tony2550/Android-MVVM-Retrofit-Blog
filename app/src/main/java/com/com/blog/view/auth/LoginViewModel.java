package com.com.blog.view.auth;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.com.blog.bean.LoginUser;
import com.com.blog.bean.Token;
import com.com.blog.model.User;
import com.com.blog.repository.UserRepository;
import com.com.blog.repository.dto.CMRespDto;
import com.com.blog.repository.dto.LoginDto;
import com.com.blog.util.PreferenceManager;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel{

    private static final String TAG = "LoginViewModel";
    private MutableLiveData<LoginUser> loginUser = new MutableLiveData<>(); // 로그인 유저 관리 객체
    PreferenceManager preferenceManager;
    private Context mContext;

    public MutableLiveData<LoginUser> getLoginUser() {
        return loginUser;
    }

    public void loginClick(LoginDto loginDto){
        Call<CMRespDto<User>> call = UserRepository.retrofit.create(UserRepository.class).login(loginDto);
        call.enqueue(new Callback<CMRespDto<User>>() {
            @Override
            public void onResponse(Call<CMRespDto<User>> call, Response<CMRespDto<User>> response) {
                CMRespDto<User> cm = response.body();
                Headers headers = response.headers();
//                Log.d(TAG, "onResponse code: "+cm.getCode());
//                Log.d(TAG, "onResponse: username "+cm.getData().getUsername());
//                Log.d(TAG, "onResponse: 토큰"+headers.get("Authorization"));
                User user = cm.getData();
                String token = headers.get("Authorization");

                LoginUser loginUserOk = new LoginUser(user, token);

                Token.token = token;

                //preferenceManager.setString(PreferenceManager.PREF_TOKEN,token);

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("auth-Token",token);
//                editor.commit();
                loginUser.setValue(loginUserOk);
            }
            @Override
            public void onFailure(Call<CMRespDto<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
//        loginUser.setValue();
//        editor.putString(auth-Token)
    };

}
