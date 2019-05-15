package com.example.administrator.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  Myadapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";
    public Myadapter(Context context, int resource, ArrayList<HashMap<String,String>>list){
        super(context,resource,list);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_my_list3,parent,false);
            
        }
        Map<String,String> map = (Map<String, String>)getItem(position);
        TextView title  = (TextView)itemView.findViewById(R.id.itemTitle);
        TextView detail  = (TextView)itemView.findViewById(R.id.itemDetail);

        title.setText("Title:"+map.get("ItemTitle"));
        title.setText("Detail:"+map.get("ItemDetail"));
        return itemView;
    }
}
