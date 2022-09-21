package com.example.myadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] name = new String[]{"Demi", "xx", "xxx", "Demi", "xx", "xxx", "Demi", "xx", "xxx", "Demi", "xx", "xxx"};
    private String[] says = new String[]{"2019xxxx", "恭喜EDG", "xxxxxxx~", "2019xxxx", "恭喜EDG", "xxxxxxx~", "2019xxxx", "恭喜EDG", "xxxxxxx~", "2019xxxx", "恭喜EDG", "xxxxxxx~"};
    private int[] imglds = new int[]{R.mipmap.zjl1, R.mipmap.zjl2, R.mipmap.zjl3, R.mipmap.zjl1, R.mipmap.zjl2, R.mipmap.zjl3, R.mipmap.zjl1, R.mipmap.zjl2, R.mipmap.zjl3, R.mipmap.zjl1, R.mipmap.zjl2, R.mipmap.zjl3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("img", imglds[i]);
            showitem.put("name", name[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }

//        创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.list_item, new String[]{"img", "name", "says"}, new int[]{R.id.img, R.id.name, R.id.says});
        ListView listView = (ListView) findViewById(R.id.list_test);
        listView.setAdapter(myAdapter);
    }



//        @Override
//        protected void onCreate(Bundle	savedInstanceState)	{
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            //要显示的数据
//            String[]	strs	=	{"周杰伦","周杰伦","周杰伦","周杰伦","周杰伦"};
//            //创建 ArrayAdapter
//            ArrayAdapter<String> adapter	=	new ArrayAdapter<String>
//                    (this,android.R.layout.simple_expandable_list_item_1,strs);
//            //获取 ListView 对象，通过调用 setAdapter 方法为 ListView 设置 Adapter 设置适配器
//            ListView	list_test	=	(ListView)	findViewById(R.id.list_test);
//            list_test.setAdapter(adapter);
//        }

}