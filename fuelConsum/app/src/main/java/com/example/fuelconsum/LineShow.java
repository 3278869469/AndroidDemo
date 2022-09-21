package com.example.fuelconsum;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineShow  extends AppCompatActivity {

    private List<String> times = new ArrayList<String>();
    private List<String> oilCSs = new ArrayList<String>();
    private List<String> AllPrices = new ArrayList<String>();
    private LineView lineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个是获取布局文件的，这里是你下一个页面的布局文件
        setContentView(R.layout.line);


        FileHelper fHelper2 = new FileHelper(getApplicationContext());
        try {
            times = fHelper2.read("AddTime.txt");
            AllPrices = fHelper2.read("Sum.txt");
            oilCSs = fHelper2.read("OilConsum.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }

        lineView= (LineView) findViewById(R.id.lineView);
        lineView.setViewData(oilCSs,times);
    }

}
