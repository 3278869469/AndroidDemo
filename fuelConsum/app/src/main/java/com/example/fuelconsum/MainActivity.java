package com.example.fuelconsum;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText AddTime;
    private EditText RunMileage;
    private EditText oil;
    private EditText UnitPrice;

    private Button btnsave;
    private Button btnclean;
    private Button btnread;
    private Context mContext;

//    String filename = "test.txt";
//    private List<String> times = new ArrayList<String>();
//    private List<String> UPrices = new ArrayList<String>();
//    private List<String> AllPrices = new ArrayList<String>();
//    private List<String> lcs = new ArrayList<String>();
//    private List<String> oilCSs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        bindViews();

    }

    private void bindViews() {
        AddTime = (EditText) findViewById(R.id.AddTime);
        RunMileage = (EditText) findViewById(R.id.RunMileage);
        oil = (EditText) findViewById(R.id.oil);
        UnitPrice = (EditText) findViewById(R.id.UnitPrice);
        btnclean = (Button) findViewById(R.id.btnclean);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnread = (Button) findViewById(R.id.btnread);

        btnclean.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnread.setOnClickListener(this);

        //??????????????????DatePick???????????????
        AddTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
    }

    private void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MainActivity.this.AddTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);//?????????????????????-?????????
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //???????????????
            case R.id.btnclean:
                AddTime.setText("");
                RunMileage.setText("");
                oil.setText("");
                UnitPrice.setText("");
                break;

            //????????????
            case R.id.btnsave:
                FileHelper fHelper = new FileHelper(mContext);
                String sAddTime = AddTime.getText().toString();
                String soil = oil.getText().toString();
                String sRunMileage = RunMileage.getText().toString();
                String sUnitPrice = UnitPrice.getText().toString();

                //???????????????
                int ioil = Integer.valueOf(soil).intValue();
                int iRunMileage = Integer.valueOf(sRunMileage).intValue();
                int iUnitPrice = Integer.valueOf(sUnitPrice).intValue();

                Double dOilConsum = (double) ioil / iRunMileage;
                Integer sum = iUnitPrice * ioil;

                //??????????????????????????????????????????????????????????????????????????????????????????
                String OilConsum = String.format("%.2f",dOilConsum);
                String Sum = sum.toString();
                sRunMileage = sRunMileage + " ";
                sUnitPrice = sUnitPrice + " ";
                sAddTime = sAddTime + " ";
                Sum = Sum + " ";
                OilConsum = OilConsum + " ";

                try {
                    //????????????
                    fHelper.save("AddTime.txt", sAddTime);
                    fHelper.save("UnitPrice.txt", sUnitPrice);
                    fHelper.save("Sum.txt", Sum);
                    fHelper.save("RunMileage.txt", sRunMileage);
                    fHelper.save("OilConsum.txt", OilConsum);
                    Toast.makeText(getApplicationContext(), "??????????????????",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "??????????????????",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            //????????????
            case R.id.btnread:
//                String detail = "";
//                FileHelper fHelper2 = new FileHelper(getApplicationContext());
//                try {
////                    detail = fHelper2.read(filename);
//                    fHelper2.read(filename);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
//                for (int i = 0; i < times.size(); i++) {
//
//                    Map<String, Object> showitem = new HashMap<String, Object>();
//                    showitem.put("time", times.get(i));
//                    showitem.put("UPrice", UPrices.get(i));
//                    showitem.put("AllPrice", AllPrices.get(i));
//                    showitem.put("lc", lcs.get(i));
//                    showitem.put("oilCS", oilCSs.get(i));
//                    listitem.add(showitem);
//
//                }
                //???????????? simpleAdapter
//                SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem,
//                        R.layout.list_item, new String[]{"time", "UPrice", "AllPrice", "lc", "oilCS"}, new int[]{R.id.time, R.id.UPrice,
//                        R.id.AllPrice, R.id.lc, R.id.oilCS});
//                ListView listView = (ListView) findViewById(R.id.list_test);
//                listView.setAdapter(myAdapter);

//                Toast.makeText(getApplicationContext(), detail+"****"+times.size(), Toast.LENGTH_SHORT).show();


                //????????????
                Intent intent = new Intent();
                //????????????MainActivity.this???????????????????????????????????????????????????????????????
                intent.setClass(MainActivity.this, show.class);
                startActivity(intent);
                break;
        }

    }

}