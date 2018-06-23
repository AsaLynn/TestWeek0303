package com.think.xml.activity;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.think.xml.listener.PageLis1;

public class EnterActivity extends FirstActivity {


    @Override
    protected void click0() {
        DialogPage
                .getInstance()
                .setOnDialogItemListener(new PageLis1(this));
    }
}
