package com.example.administrator.font;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class SensorUtils {

    //数据如何传  数据通过配置 解析 通过反射拿到对象拿到属性拿
    private HashMap<String,String> sensorMap;
    private Context mContext;

    private SensorUtils(Context context){
        this.mContext=context;
        sensorMap=new HashMap<>();
        initSensorConfigPath();
    }

    private void initSensorConfigPath() {
        sensorMap.put(mContext.getResources().getString(R.string.button_click_event_path),"BUTTON_CLICK_EVENT");
    }

    private static SensorUtils sensorUtils;


    public static SensorUtils getInstance(Context context){
        if(sensorUtils==null){
            sensorUtils=new SensorUtils(context);
        }
        return sensorUtils;
    }

    public  void sensor(String key, Bundle bundle){
        //打点需要事件名和bundle
        Log.e("xxx","打点事件:"+sensorMap.get(key));
    }




}
