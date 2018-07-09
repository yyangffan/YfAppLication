package com.superc.yf_lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SharePreference的工具类
 */

public class ShareUtil {
    public static final String FILE_NAME="APP_FILE";

    private static ShareUtil mShareUtil;
    private static Context mContext;

    private ShareUtil() {
    }

    public static ShareUtil getInstance(Context context) {
        if (mShareUtil == null) {
            synchronized (ShareUtil.class) {
                if(mShareUtil==null) {
                    mShareUtil = new ShareUtil();
                }
            }
        }
        mContext=context;
        return mShareUtil;
    }

    public  void put(String name,Object value){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if(value instanceof String){edit.putString(name, (String) value);}
        else if(value instanceof Integer){edit.putInt(name, (Integer) value);}
        else if(value instanceof Boolean){edit.putBoolean(name, (Boolean) value);}
        else if(value instanceof Float){edit.putFloat(name, (Float) value);}
        else if(value instanceof Long){edit.putLong(name, (Long) value);}
        else{edit.putString(name,new Gson().toJson(value));}
        SharedPreferencesCompat.apply(edit);
    }
    public  Object get(String name,Object defaultValue){
        SharedPreferences sp=mContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        if(defaultValue instanceof String){return sp.getString(name, (String) defaultValue);}
        else if(defaultValue instanceof Integer){return sp.getInt(name, (Integer) defaultValue);}
        else if(defaultValue instanceof Boolean){return sp.getBoolean(name, (Boolean) defaultValue);}
        else if(defaultValue instanceof Float){return sp.getFloat(name, (Float) defaultValue);}
        else if(defaultValue instanceof Long){return sp.getLong(name, (Long) defaultValue);}
//        else{edit.putString(name,new Gson().toJson(value));}
        return null;

    }
    /**
     * 移除某个key值已经对应的值
     * @param key
     */
    public  void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }
    /**
     * 清除所有数据
     */
    public  void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }
    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();
        /**
         * 反射查找apply的方法
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
              if (sApplyMethod != null) {
                  sApplyMethod.invoke(editor);
                  return;
              }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {}
            editor.commit();
        }
    }

}
