package com.com.blog.view;

public interface InitActivity {
    void init();
    void initLr();
    void initObserve();
    void initSetting();
    default void initAdapter(){}
    default void initData(){}
}
