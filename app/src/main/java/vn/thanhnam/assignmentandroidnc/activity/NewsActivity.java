package vn.thanhnam.assignmentandroidnc.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.adapter.NewsAdapter;
import vn.thanhnam.assignmentandroidnc.model.News;

public class NewsActivity extends AppCompatActivity {

    // khai bao duong dan~ url de request.
    private EditText edtURL;
    private RecyclerView rvList;
    String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        rvList = findViewById(R.id.rvList);
        GetNews getNews = new GetNews();
        getNews.execute("https://vietnamnet.vn/rss/thoi-su-chinh-tri.rss");
    }

    public void openWebview(View view) {

    }

    public class GetNews extends AsyncTask<String, Long, List<News>> {

        @Override
        protected List<News> doInBackground(String... strings) {
            // lay ra tu url tham so dau tien
            String link = strings[0];
            List<News> newsArrayLists = new ArrayList<>();
            // khoi tao doi tuong URL
            try {
                URL url = new URL(link);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                // khoi tao doi tuong xmlpullparser .(khung)
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);

                //khoi tao doi tuong boc tach xml
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                //truyen dl cho doi tuong boc xml
                xmlPullParser.setInput(inputStream, "utf-8");

                //tien hanh doc dl cho xmlpullparser
                int eventType = xmlPullParser.getEventType();
                News news = null;
                String text = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {

                        //neu the mo = item => khoi tao doi tuong news
                        case XmlPullParser.START_TAG:
                            if (xmlPullParser.getName().equalsIgnoreCase("item")) {
                                news = new News();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;
                        case XmlPullParser.END_TAG:

                            if (news != null) {
                                String tag = xmlPullParser.getName();
                                if (tag.equalsIgnoreCase("title")) {
                                    news.title = text;
                                } else if (tag.equalsIgnoreCase("description")) {
                                    news.description = text;
                                } else if (tag.equalsIgnoreCase("image")) {
                                    news.image = text;
                                } else if (tag.equalsIgnoreCase("pubDate")) {
                                    news.date = text;
                                } else if (tag.equalsIgnoreCase("link")) {
                                    news.link = text;
                                } else if (tag.equalsIgnoreCase("item")) {
                                    news.url = link;
                                    newsArrayLists.add(news);
                                }
                                break;
                            }
                    }


                    // di chuyen toi tag ke tiep
                    eventType = xmlPullParser.next(); //move to next element
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return newsArrayLists;
        }

        @Override
        protected void onPostExecute(List<News> newsArrayLists) {
            super.onPostExecute(newsArrayLists);

            for (int i = 0; i < newsArrayLists.size(); i++) {
                Log.e("list", newsArrayLists.get(i).image + "\n");
            }
            NewsAdapter newsAdapter = new NewsAdapter(NewsActivity.this, newsArrayLists);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsActivity.this);
            rvList.setLayoutManager(linearLayoutManager);
            rvList.setAdapter(newsAdapter);

        }
    }

}
