package in.aniruddhag.forcefest_appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class News extends AppCompatActivity {

    Activity activity;
    View view;

    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_news, null);

        WebView webView = findViewById(R.id.wbVw_NEWS);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://economictimes.indiatimes.com/topic/war");

        return view;
    }
}