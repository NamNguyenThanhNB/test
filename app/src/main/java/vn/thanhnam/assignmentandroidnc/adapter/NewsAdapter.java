package vn.thanhnam.assignmentandroidnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    private Context context;
    private List<News> newsList;
    private NewsAdapter newsAdapter;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_, parent, false);
        NewsHolder newsHolder = new NewsHolder(view);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        holder.tvText1.setText(newsList.get(position).title);
        holder.tvText2.setText(newsList.get(position).description);
        holder.tvText3.setText(newsList.get(position).date);
        holder.imgIcon.setImageResource(0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).link));
                context.startActivity(browserIntent);
            }
        });
//        String icon = (newsList.get(position).image);
//        if (newsList.get(position).image != null ) {
//            Toast.makeText(context, newsList.get(position).image + "", Toast.LENGTH_SHORT).show();
//            new DownLoadImageTask(holder.imgIcon).execute(icon);
//        }


//        holder.tvText2.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).url));
//            context.startActivity(browserIntent);
//        }
//    });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    // hàm download image từ web
    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
