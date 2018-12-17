package com.superc.yf_lib.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.superc.yf_lib.utils.ToastUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * 联网工具  使用nohttp
 */

public class Http {
    private RequestQueue mRequestQueue;
    private Context mContext;
    private boolean isGet = false;//是否为get请求  true--get请求  false--post请求
    private static Http mInstance;
    private boolean debug = true;

    private Http(Context context, boolean isGet) {
        mContext = context;
    }

    public static Http getInstance(Context context, boolean isGet) {
        return new Http(context, isGet);
    }

    /**
     * @param map 访问接口所携带的数据集合
     * @param url 访问的接口
     */
    public <T> void ConnectUrl(Map<String, String> map, final String url, final Class<T> type, final CallNetBack<T> backListener) {
        mRequestQueue = NoHttp.newRequestQueue();
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, isGet ? RequestMethod.GET : RequestMethod.POST);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        String enter = "?";
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            request.add(entry.getKey(), entry.getValue());
            enter += (entry.getKey() + "=" + entry.getValue() + "&");
        }
        if (debug) {
            Log.i("访问接口及参数", url + "    " + (enter.length() != 1 ? url + enter.substring(0, enter.length() - 1) + "\n" : "无参数"));
        }
        mRequestQueue.add(2, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                if (backListener != null) {
                    T t = new Gson().fromJson(response.get().toString(), type);
                    backListener.callNetBack(t);
                    String[] split = url.split("/");
                    if (debug) {
                        Log.e("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 返回数据", "\n" + (response.get() != null ? response.get().toString() : "无返回数据"));
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                ToastUtil.showToast(mContext, "网络异常");
                String[] split = url.split("/");
                if (backListener != null) {
                    backListener.callFailBack(response.get().toString());
                }
                if (debug) {
                    Log.i("访问:" + split[split.length - 2] + "/" + split[split.length - 1] + " 接口时出错:", "\n" + (response.get() != null ? response.get().toString() : response.toString()));
                }
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    public static abstract class CallNetBack<T> {
        /* @param data  服务器返回内容*/
        public abstract void callNetBack(T data);

        public abstract void callFailBack(String data);
    }


}
