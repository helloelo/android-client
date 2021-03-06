package tk.helloelo.helloelo;

import android.util.Log;

import com.loopj.android.http.*;

public class HelloEloClient {
    private static final String BASE_URL = /*"http://helloelo.tk:8080/";//*/"http://helloelo.tk/v1/";

    public static AsyncHttpClient client = new AsyncHttpClient();

    public static Player player;

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("yeah", getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
