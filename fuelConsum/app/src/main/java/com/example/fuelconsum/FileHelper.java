package com.example.fuelconsum;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileHelper {
    private Context mContext;

    public FileHelper() {
    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /*
     *	 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     */
    public void save(String filename, String filecontent) throws Exception {
        //模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
        FileOutputStream output = mContext.openFileOutput(filename, Context.MODE_APPEND);
        output.write(filecontent.getBytes());         //将 String 字符串以字节流的形式写入到输出流中
        output.close();                                     //关闭输出流
    }

    /*
     *	 这里定义的是文件读取的方法
     */
    public List<String> read(String filename) throws IOException {
        //打开文件输入流
        FileInputStream input = mContext.openFileInput(filename);
        byte[] temp = new byte[1024];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        //读取文件内容:
        while ((len = input.read(temp)) > 0) {
            sb.append(new String(temp, 0, len));
        }
        //关闭输入流
        input.close();
        //空格分隔字符串，分隔成数组
        String[] array = sb.toString().split(" ");
        List<String> ANS = Arrays.asList(array);
        return ANS;

    }
}