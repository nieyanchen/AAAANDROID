package com.example.administrator.first;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements  Runnable, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private String TAG = "mylist2";
    Handler handler;
    private List<HashMap<String,String>> listItem;  //存放文字、图片信息
    private SimpleAdapter listItemAdapter;  //适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            public void handleMessage(Message msg){
                if (msg.what==7){
                    listItem = (List<HashMap<String,String>>) msg.obj;
                    listItemAdapter  = new SimpleAdapter(   MyList2Activity.this,listItem,
                            R.layout.activity_my_list3,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);

    }


    private void initListView(){
        listItem = new ArrayList<HashMap<String,String>>();
        for (int i = 0; i < 10;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("ItemTitle","Rate: " + i); //标题描述
            map.put("ItemDetail","detail: " + i); //详情描述
            listItem.add(map);
        }
        //生成适配器的Item 和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItem,
                R.layout.activity_my_list3, //ListItem 的xml布局实现
                new String[]{"ItemTitle","ItemDetail"},
        new int[] {R.id.itemTitle,R.id.itemDetail}
        );
    }
    public void run() {
        //获取网络数据 当如list带回到主线程中
        List<HashMap<String, String>> retlist = new ArrayList<HashMap<String,String>>();
        Document doc = null;
        try {
//            Thread.sleep(1000);
            doc = Jsoup.connect("http://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG,"run" + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table2 = tables.get(1);

            //获取TD中的数据
            Elements tds = table2.getElementsByTag("td");
            for (int i =0;i<tds.size();i+=8){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);

                String str1 = td1.text();
                String val = td2.text();
                Log.i(TAG,"run:"+str1 + "==>" + val );
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
                retlist.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"ERRRORRRR");
        Message msg = handler.obtainMessage(7);
        msg.obj = retlist;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick:parent=" + parent);
        Log.i(TAG, "onItemClick:view=" + view);
        Log.i(TAG, "onItemClick:position=" + position);
        Log.i(TAG, "onItemClick:id=" + id);
        HashMap<String, String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick:titleStr=" + titleStr);
        Log.i(TAG, "onItemClick:detailStr=" + detailStr);

        TextView title= (TextView) view.findViewById(R.id.itemTitle);
        TextView detail= (TextView) view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());
        Log.i(TAG, "onItemClick: title2="+title2);
        Log.i(TAG, "onItemClick: detail2="+detail2);

        //打开新的页面 传入参数
        Intent rateCalc = new Intent(this,RateCalcActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(rateCalc);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按列表项position"+position);
        //删除操作
        //listItem.remove(position);
        //listItemAdapter.notifyDataSetChanged();
        //构造对话框，确认操作
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 对话框事件处理");
                listItem.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        Log.i(TAG, "onItemLongClick: size= "+listItem.size());
        return true;
    }
}
