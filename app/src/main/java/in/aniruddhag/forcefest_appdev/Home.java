package in.aniruddhag.forcefest_appdev;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Home extends AppCompatActivity {

    View view;
    Activity activity;
    WebView webView;

    protected View onCreate(Activity activity) {
        this.activity = activity;
        LayoutInflater li = activity.getLayoutInflater();
        view = li.inflate(R.layout.activity_home, null);

        webView = view.findViewById(R.id.webViewHome);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.ready.gov/kit");

        return view;
    }
}