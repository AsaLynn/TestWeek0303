package com.think.xml;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.DialogUtil;
import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button btn;
    private String TAG = this.getClass().getSimpleName();
    private Gson gson;
    private FoodInfo foodInfo;
    private String xmlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        gson = new Gson();
        //request();
        initView();
    }

    private void request() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
//        String url = "https://github.com/zhang721788/testmaterial/blob/master/data.xml";
//        String url = "https://raw.githubusercontent.com/zhang721788/testmaterial/master/data.xml";
        //String url = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=3&limit=15&page=5";
        String url = "https://gitee.com/little_bird_oh_777/test_data_collection/raw/master/dishs.xml";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i(TAG, "onResponse: ***" + result);
                /*foodInfo = gson.fromJson(result, FoodInfo.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DemonstrateUtil.showToastResult(MainActivity.this, foodInfo.toString());
                    }
                });*/
//                xml1();
//                xml2();

            }
        });
    }

    private String xml2(FoodInfo foodInfo) {
        String xml = "";
        StringWriter stringWriter = new StringWriter();
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = xmlPullParserFactory.newSerializer();

            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("utf-8", true);

            xmlSerializer.startTag(null, "root");
            xmlSerializer.startTag(null, "data");
            for (FoodInfo.DataBean bean : foodInfo.getData()) {
                xmlSerializer.startTag(null, "food");

                xmlSerializer.startTag(null, "title");
                xmlSerializer.text(bean.getTitle());
                xmlSerializer.endTag(null, "title");

                xmlSerializer.startTag(null, "pic");
                xmlSerializer.text(bean.getPic());
                xmlSerializer.endTag(null, "pic");

                xmlSerializer.startTag(null, "num");
                xmlSerializer.text(bean.getNum() + "");
                xmlSerializer.endTag(null, "num");

                xmlSerializer.startTag(null, "id");
                xmlSerializer.text(bean.getId());
                xmlSerializer.endTag(null, "id");

                xmlSerializer.startTag(null, "food_str");
                xmlSerializer.text(bean.getFood_str());
                xmlSerializer.endTag(null, "food_str");

                xmlSerializer.startTag(null, "collect_num");
                xmlSerializer.text(bean.getCollect_num());
                xmlSerializer.endTag(null, "collect_num");

                xmlSerializer.endTag(null, "food");
            }
            xmlSerializer.endTag(null, "data");
            xmlSerializer.startTag(null, "ret");
            xmlSerializer.text(foodInfo.getRet() + "");
            xmlSerializer.endTag(null, "ret");
            xmlSerializer.endTag(null, "root");
            xmlSerializer.endDocument();
            xml = stringWriter.toString();

            Log.i(TAG, "xml: ***" + xml);
            DemonstrateUtil.showToastResult(MainActivity.this, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    private String xml1(FoodInfo foodInfo) {
        String xml = "";
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xmlSerializer.setOutput(byteArrayOutputStream, "utf-8");
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "root");
            xmlSerializer.startTag(null, "data");
            for (FoodInfo.DataBean bean : foodInfo.getData()) {
                xmlSerializer.startTag(null, "food");

                xmlSerializer.startTag(null, "title");
                xmlSerializer.text(bean.getTitle());
                xmlSerializer.endTag(null, "title");

                xmlSerializer.startTag(null, "pic");
                xmlSerializer.text(bean.getPic());
                xmlSerializer.endTag(null, "pic");

                xmlSerializer.startTag(null, "num");
                xmlSerializer.text(bean.getNum() + "");
                xmlSerializer.endTag(null, "num");

                xmlSerializer.startTag(null, "id");
                xmlSerializer.text(bean.getId());
                xmlSerializer.endTag(null, "id");

                xmlSerializer.startTag(null, "food_str");
                xmlSerializer.text(bean.getFood_str());
                xmlSerializer.endTag(null, "food_str");

                xmlSerializer.startTag(null, "collect_num");
                xmlSerializer.text(bean.getCollect_num());
                xmlSerializer.endTag(null, "collect_num");

                xmlSerializer.endTag(null, "food");
            }
            xmlSerializer.endTag(null, "data");
            xmlSerializer.startTag(null, "ret");
            xmlSerializer.text(foodInfo.getRet() + "");
            xmlSerializer.endTag(null, "ret");
            xmlSerializer.endTag(null, "root");
            xmlSerializer.endDocument();
            xml = byteArrayOutputStream.toString();
            Log.i(TAG, "xml: ***" + xml);
            DemonstrateUtil.showToastResult(MainActivity.this, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn) {
            DialogUtil.showListDialog(this, "xml操作!", new String[]{
                    "0,发送请求获取对象数据",
                    "1,将对象转换成xml字符串方式1",
                    "2,将对象转换成xml字符串方式2",
                    "3,将xml字符串解析成实体对象",
                    "4,解析xml展示到列表",
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            request();
                            break;
                        case 1:
                            if (null == foodInfo) {
                                DemonstrateUtil.showToastResult(MainActivity.this, "数据为空");
                                return;
                            }
                            xmlStr = xml1(foodInfo);
                            DemonstrateUtil.showToastResult(MainActivity.this, "成功!");
                            break;
                        case 2:
                            if (null == foodInfo) {
                                DemonstrateUtil.showToastResult(MainActivity.this, "数据为空");
                                return;
                            }
                            xmlStr = xml2(foodInfo);
                            DemonstrateUtil.showToastResult(MainActivity.this, "成功!");
                            break;
                        case 3:
                            if (TextUtils.isEmpty(xmlStr)) {
                                DemonstrateUtil.showToastResult(MainActivity.this, "数据为空");
                                return;
                            }
                            FoodInfo xmlFoodInfo = null;
                            try {
                                xmlFoodInfo = xml3(xmlStr);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < xmlFoodInfo.getData().size(); i++) {
                                DemonstrateUtil.showLogResult(xmlFoodInfo.getData().get(i).getTitle());
                            }
                            DemonstrateUtil.showToastResult(MainActivity.this, "成功!");
                            break;
                        case 4:
                            startActivity(new Intent(MainActivity.this,XmlRecyclerViewActivity.class));
                            break;
                    }
                }
            });
        }
    }

    private FoodInfo xml3(String xml) throws Exception {
        FoodInfo xmlFoodInfo = null;
        FoodInfo.DataBean bean = null;
        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;
        ArrayList<FoodInfo.DataBean> dataBeans = null;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
        xmlPullParserFactory = XmlPullParserFactory.newInstance();
        xmlPullParser = xmlPullParserFactory.newPullParser();
        xmlPullParser.setInput(byteArrayInputStream, "utf-8");
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
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(MainActivity.this);
    }
}
