package com.think.xml;

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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class XmlRecyclerViewActivity extends AppCompatActivity {

    protected RecyclerView recyclerRv;
    private MyRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_xml_recycler_view);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("家常菜大全");
        supportActionBar.setSubtitle("亲,你饿了么?");
        initView();
        //request();
        request1();
    }

    private void request1() {
        final String mUlr = UC.URL_XML1;

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
//                        final FoodInfo foodInfo = parseXml(inputStream);
                        final FoodInfo foodInfo = parseXml1(inputStream);
                        XmlRecyclerViewActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerAdapter.addNewData(foodInfo.getData());
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void request() {
        final String mUlr = UC.URL_XML;

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
                        final FoodInfo foodInfo = parseXml(inputStream);
                        XmlRecyclerViewActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerAdapter.addNewData(foodInfo.getData());
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private FoodInfo parseXml1(InputStream inputStream) throws Exception {
        FoodInfo xmlFoodInfo = null;
        FoodInfo.DataBean bean = null;
        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;
        ArrayList<FoodInfo.DataBean> dataBeans = null;

        xmlPullParserFactory = XmlPullParserFactory.newInstance();
        xmlPullParser = xmlPullParserFactory.newPullParser();
        xmlPullParser.setInput(inputStream, "utf-8");
        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    xmlFoodInfo = new FoodInfo();
                    break;
                case XmlPullParser.START_TAG:
                    String name = xmlPullParser.getName();
                    if ("data".equals(name)) {
                        dataBeans = new ArrayList<>();
                    } else if ("dishs".equals(name)) {
                        bean = new FoodInfo.DataBean();
                    } else if ("name".equals(name)) {
                        bean.setTitle(xmlPullParser.nextText());
                    } else if ("img".equals(name)) {
                        bean.setPic(xmlPullParser.nextText());
                    } else if ("favorites".equals(name)) {
                        bean.setFavorites(xmlPullParser.nextText());
                    } else if ("code".equals(name)) {
                        bean.setId(xmlPullParser.nextText());
                    } else if ("burdens".equals(name)) {
                        bean.setFood_str(xmlPullParser.nextText());
                    } else if ("favorites".equals(name)) {
                        bean.setCollect_num(xmlPullParser.nextText());
                    } else if ("level".equals(name)) {
                        xmlFoodInfo.setRet(Integer.parseInt(xmlPullParser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("dishs".equals(xmlPullParser.getName())) {
                        dataBeans.add(bean);
                        bean = null;
                    } else if ("data".equals(xmlPullParser.getName())) {
                        xmlFoodInfo.setData(dataBeans);
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
            }
            eventType = xmlPullParser.next();
        }
        return xmlFoodInfo;
    }

    private FoodInfo parseXml(InputStream inputStream) throws Exception {
        FoodInfo xmlFoodInfo = null;
        FoodInfo.DataBean bean = null;
        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;
        ArrayList<FoodInfo.DataBean> dataBeans = null;

        xmlPullParserFactory = XmlPullParserFactory.newInstance();
        xmlPullParser = xmlPullParserFactory.newPullParser();
        xmlPullParser.setInput(inputStream, "utf-8");
        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    xmlFoodInfo = new FoodInfo();
                    break;
                case XmlPullParser.START_TAG:
                    String name = xmlPullParser.getName();
                    if ("data".equals(name)) {
                        dataBeans = new ArrayList<>();
                    } else if ("food".equals(name)) {
                        bean = new FoodInfo.DataBean();
                    } else if ("title".equals(name)) {
                        bean.setTitle(xmlPullParser.nextText());
                    } else if ("pic".equals(name)) {
                        bean.setPic(xmlPullParser.nextText());
                    } else if ("num".equals(name)) {
                        bean.setNum(Integer.parseInt(xmlPullParser.nextText()));
                    } else if ("id".equals(name)) {
                        bean.setId(xmlPullParser.nextText());
                    } else if ("food_str".equals(name)) {
                        bean.setFood_str(xmlPullParser.nextText());
                    } else if ("collect_num".equals(name)) {
                        bean.setCollect_num(xmlPullParser.nextText());
                    } else if ("ret".equals(name)) {
                        xmlFoodInfo.setRet(Integer.parseInt(xmlPullParser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("food".equals(xmlPullParser.getName())) {
                        dataBeans.add(bean);
                        bean = null;
                    } else if ("data".equals(xmlPullParser.getName())) {
                        xmlFoodInfo.setData(dataBeans);
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
            }
            eventType = xmlPullParser.next();
        }
        return xmlFoodInfo;
    }

    private void initView() {
        recyclerRv = (RecyclerView) findViewById(R.id.recycler_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRv.setLayoutManager(layoutManager);
        recyclerRv.setHasFixedSize(true);
//        recyclerRv.addItemDecoration();
        recyclerAdapter = new MyRecyclerAdapter(recyclerRv);
        recyclerAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                DemonstrateUtil.showToastResult(parent.getContext(), recyclerAdapter.getData().get(position).getTitle());
            }
        });
        recyclerRv.setAdapter(recyclerAdapter);
    }

    class MyRecyclerAdapter extends BGARecyclerViewAdapter<FoodInfo.DataBean> {

        public MyRecyclerAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_recycler);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, FoodInfo.DataBean model) {
            helper.setText(R.id.title_tv, model.getTitle())
                    .setText(R.id.title_str_tv, model.getFood_str())
                    .setText(R.id.num_tv, "数量" + model.getFavorites());
            Glide.with(XmlRecyclerViewActivity.this)
                    .load(model.getPic())
                    .apply(new RequestOptions().override(150))
                    .into(helper.getImageView(R.id.recycler_item_iv));
        }
    }
}
