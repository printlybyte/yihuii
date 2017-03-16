package com.example.yihuii.yihuii.MainFrame.frament1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yihuii.yihuii.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class city_search extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private TextView mTxt_back, mTxt_yes,mT_faxian;
    private Spinner mSpin_2;
    private Button mSpin_1;
    Adapater_list_ cc;
    private List<Costom_Person> mList = new ArrayList<>();
    private EditText mEdit;
    private ListView listView;
    private Adapater_list_ aa;
    private String PATH_URL = "http://123.56.237.2:8080/open.php?c=activity&m=getlist";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);
        initView();
    }

    private void initView() {
        mT_faxian= (TextView) findViewById(R.id.faxainjeiguo_txt);
        listView = (ListView) findViewById(R.id.list_item_faxain);
        mTxt_back = (TextView) findViewById(R.id.city_search_back);
        mTxt_yes = (TextView) findViewById(R.id.city_search_yes);
        mTxt_back.setOnClickListener(this);
        mTxt_yes.setOnClickListener(this);
        mEdit = (EditText) findViewById(R.id.city_search_edit);
        mEdit.setOnClickListener(this);
        mSpin_1 = (Button) findViewById(R.id.spinner_city_1);
        mSpin_2 = (Spinner) findViewById(R.id.spinner_city_2);
        mSpin_1.setOnClickListener(this);
        mSpin_2.setOnItemSelectedListener(this);
        postData_activityy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_search_back:
                finish();
                break;
            case R.id.city_search_yes:
                String AA = mEdit.getText().toString();
                if (mList == null) {

                    postData_activityy();
                } else {
                    aa.notifyDataSetChanged();
                }
                break;
            case R.id.spinner_city_1:

                city_search();
                break;
            default:
                break;
        }
    }

    private void city_search() {
        CityPicker cityPicker = new CityPicker.Builder(city_search.this).textSize(20)
                .title("城市列表")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#FFFFF0")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();

        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
//                tvResult.setText("选择结果：\n省：" + citySelected[0] + "\n市：" + citySelected[1] + "\n区："
//                        + citySelected[2] + "\n邮编：" + citySelected[3]);
        mEdit.setText(citySelected[1]+"");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }


    private void postData_activityy() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, PATH_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result.toString();
                try {
                    JSONObject obj = new JSONObject(result);

                    JSONArray array = obj.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj2 = array.getJSONObject(i);

                        Costom_Person person = new Costom_Person();
                        person.Title = obj2.getString("act_title");
                        person.image_url = obj2.getString("poster_img");
                        person.riqi_start = obj2.getString("time_start");
                        person.riqi_end = obj2.getString("time_end");
                        person.dizhi = obj2.getString("act_place");
                        person.canyu = obj2.getString("mine_state");
                        person.zhuangtai = obj2.getString("act_status");
                        mList.add(person);
                    }
                    mT_faxian.setVisibility(View.VISIBLE);
                    aa = new Adapater_list_(mList, city_search.this);
                    listView.setAdapter(aa);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }


}

                /*
                * citySelected[0]：表示：省份信息
                citySelected[1]：表示：城市信息
                citySelected[2]：表示：区县信息
                citySelected[3]：表示：邮编信息
                *
                * */


                /*
                * textSize（滚轮文字的大小，int 类型，默认为18）
                title（选择器标题，默认为“选择地区”）
                backgroundPop（背景，默认为半透明，16位进制颜色代码，带alpha值，如0xa0ffffff）
                titleBackgroundColor（标题栏背景，默认为灰色，#C7C7C7）
                confirTextColor（确认按钮字体颜色，默认为系统的colorPrimary颜色值）
                cancelTextColor（取消按钮字体颜色，默认为系统的colorPrimary颜色值）
                province（默认的显示省份，显示选择器后直接定位的item位置）
                city（默认的显示市，显示选择器后直接定位的item位置）
                district（默认的显示区，显示选择器后直接定位的item位置）
                textColor（滚轮文字的颜色 ，int 类型，默认为0xFF585858）
                provinceCyclic（省份的滚轮是否循环滚动）
                cityCyclic（市的滚轮是否循环滚动）
                districtCyclic（区的滚轮是否循环滚动）
                visibleItemsCount（滚轮显示的item个数，int 类型，默认为5个）
                itemPadding（滚轮item间距，默认为5dp）
                onlyShowProvinceAndCity(boolean flag)（是否只显示省份和市的两级联动，去掉区或者县）
                *
                * */
