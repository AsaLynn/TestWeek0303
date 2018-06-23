package com.think.xml.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demonstrate.DemonstrateUtil;
import com.think.xml.R;
import com.think.xml.UC;
import com.think.xml.model.ParamzBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class Test2Activity extends AppCompatActivity {
    protected RecyclerView recyclerRv;
    private Test2Activity.MyRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_recycler_view);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("新闻大全");
        supportActionBar.setSubtitle("天天新闻大全!");
        initView();
        request1();
    }

    private void request1() {
        final String mUlr = UC.URL_TEST_20180326;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUlr);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setReadTimeout(5 * 1000);
                    httpURLConnection.setConnectTimeout(5 * 1000);
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 == responseCode) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                       /* String resultStr = StreamUtils.StreamToString(inputStream);
                        LogUtils.i(resultStr);
                        Log.i("TAG", resultStr);*/
                        final ParamzBean info = parse(inputStream);
                        Test2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerAdapter.addNewData(info.getFeeds());
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ParamzBean parse(InputStream inputStream) {
//        ParamzBean newsInfo = null;
        ParamzBean.FeedsBean feedsBean = null;
        ArrayList<ParamzBean.FeedsBean> paramz = null;
        ParamzBean.FeedsBean.DataBean dataBean = null;
        ParamzBean paramzBean = null;

        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlPullParserFactory.newPullParser();//--->parser
            parser.setInput(inputStream, "utf-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        paramzBean = new ParamzBean();
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if ("paramz".equals(name)) {
                            paramz = new ArrayList<>();
                        } else if ("feeds".equals(name)) {
                            feedsBean = new ParamzBean.FeedsBean();
                        } else if ("data".equals(name)) {
                            dataBean = new ParamzBean.FeedsBean.DataBean();
                        } else if ("subject".equals(name)) {
                            dataBean.setSubject(parser.nextText());
                        } else if ("summary".equals(name)) {
                            dataBean.setSummary(parser.nextText());
                        } else if ("cover".equals(name)) {
                            dataBean.setCover("http://litchiapi.jstv.com".concat(parser.nextText()));
                        } else if ("changed".equals(name)) {
                            dataBean.setChanged(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("data".equals(parser.getName())) {
                            feedsBean.setData(dataBean);
                        } else if ("feeds".equals(parser.getName())) {
                            paramz.add(feedsBean);
                        } else if ("paramz".equals(parser.getName())) {
                            paramzBean.setFeeds(paramz);
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramzBean;
    }

    private void initView() {
        recyclerRv = (RecyclerView) findViewById(R.id.recycler_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRv.setLayoutManager(layoutManager);
        recyclerRv.setHasFixedSize(true);
//        recyclerRv.addItemDecoration();
        recyclerAdapter = new Test2Activity.MyRecyclerAdapter(recyclerRv);
        recyclerAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                DemonstrateUtil.showToastResult(parent.getContext(), recyclerAdapter.getData().get(position).getData().getSubject());
            }
        });
        recyclerRv.setAdapter(recyclerAdapter);
    }

    class MyRecyclerAdapter extends BGARecyclerViewAdapter<ParamzBean.FeedsBean> {

        public MyRecyclerAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_recycler);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ParamzBean.FeedsBean model) {
            helper.setText(R.id.title_tv, model.getData().getSubject())
                    .setText(R.id.title_str_tv, model.getData().getSummary())
                    .setText(R.id.num_tv, model.getData().getChanged());
            Glide.with(Test2Activity.this)
                    .load(model.getData().getCover())
                    .apply(new RequestOptions().override(150))
                    .into(helper.getImageView(R.id.recycler_item_iv));
        }
    }
}
