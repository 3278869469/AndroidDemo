package com.example.fuelconsum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class show extends AppCompatActivity{
    private List<String> times = new ArrayList<String>();
    private List<String> UPrices = new ArrayList<String>();
    private List<String> AllPrices = new ArrayList<String>();
    private List<String> lcs = new ArrayList<String>();
    private List<String> oilCSs = new ArrayList<String>();

    private Integer  totalMoney = 0;
    private int n = 0;
    private Double aveFC = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个是获取布局文件的，这里是你下一个页面的布局文件
        setContentView(R.layout.show);

        FileHelper fHelper2 = new FileHelper(getApplicationContext());
        try {
            times = fHelper2.read("AddTime.txt");
            UPrices = fHelper2.read("UnitPrice.txt");
            AllPrices = fHelper2.read("Sum.txt");
            lcs = fHelper2.read("RunMileage.txt");
            oilCSs = fHelper2.read("OilConsum.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }

        n = times.size();
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < n; i++) {
            totalMoney += Integer.parseInt(AllPrices.get(i));
            aveFC += Double.parseDouble(oilCSs.get(i));

            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("time", times.get(i));
            showitem.put("UPrice", UPrices.get(i));
            showitem.put("AllPrice", AllPrices.get(i));
            showitem.put("lc", lcs.get(i));
            showitem.put("oilCS", oilCSs.get(i));
            listitem.add(showitem);

        }
        aveFC = aveFC / n;
        //显示平均油耗和累计邮费
        TextView tv1 = (TextView) findViewById(R.id.aveFC);
        TextView tv2 = (TextView) findViewById(R.id.totalMoney);
        tv1.setText(String.format("%.2f",aveFC));
        tv2.setText(totalMoney.toString());

        //创建一个 simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem,
                R.layout.list_item, new String[]{"time", "UPrice", "AllPrice", "lc", "oilCS"}, new int[]{R.id.time, R.id.UPrice,
                R.id.AllPrice, R.id.lc, R.id.oilCS});
        ListView listView = (ListView) findViewById(R.id.list_test);
        listView.setAdapter(myAdapter);


        Button buttontest;
        buttontest = (Button) findViewById(R.id.Line);
        buttontest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("TEST", "button onClick");

                //页面跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(show.this, LineShow.class);
                startActivity(intent);
            }
        });
    }
}