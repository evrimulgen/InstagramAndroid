package com.emreeran.instagramclient;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Emre Eran on 07/01/16.
 */
public class InstagramLoginDialog extends Dialog {
    @SuppressWarnings("unused") // Class tag, used for logging
    private static final String TAG = InstagramLoginDialog.class.getSimpleName();

//    String url = "https://api.instagram.com/oauth/authorize/?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL + "&response_type=code";

    private String mClientId;
    private String mRedirectUrl;
    private InstagramClientListener mListener;

    public InstagramLoginDialog(Context context, String clientId, String redirectUrl, InstagramClientListener listener) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        mClientId = clientId;
        mRedirectUrl = redirectUrl;
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_instagram_login);

        WebView webView = (WebView) findViewById(R.id.dialog_instagram_login_web_view);
        String url = "https://api.instagram.com/oauth/authorize/?client_id=" + mClientId + "&redirect_uri=" + mRedirectUrl +
                "&response_type=code";

        webView.loadUrl(url);
        webView.setWebViewClient(new InstagramWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setSaveFormData(false);
    }

    private class InstagramWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(mRedirectUrl)) {
                if (url.contains("code")) {
                    String temp[] = url.split("=");
                    mListener.onSuccess(temp[1]);
                } else if (url.contains("error")) {
                    String temp[] = url.split("=");
                    mListener.onError(temp[temp.length - 1]);
                }

                InstagramLoginDialog.this.dismiss();

                return true;
            }

            return false;
        }
    }

    public interface InstagramClientListener {
        void onSuccess(String code);

        void onError(String error);
    }
}
