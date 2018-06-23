package com.think.xml.listener;

import android.app.Activity;

import com.example.demonstrate.adapter.testname.p1.w3.BaseT3P1W3ILis;
import com.think.xml.MainActivity;
import com.think.xml.R;
import com.think.xml.XmlRecyclerViewActivity;
import com.think.xml.activity.Test2Activity;

/**
 * Created by think on 2018/3/19.
 */

public class PageLis1 extends BaseT3P1W3ILis {
    public PageLis1(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return MainActivity.class;
        } else if (which == 1) {
            return XmlRecyclerViewActivity.class;
        } else if (which == 2) {
            return Test2Activity.class;
        } else if (which == 3) {
           /* return MainActivity.class;*/
        }
        return null;
    }

    @Override
    public int getDialogListId() {
        return R.array.test3_week3_dialog1_items;
    }
}
