package com.example.sumit.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar ;
    public class CustomAsynk extends AsyncTask<String,Integer,Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                String result ="";
               URL url = new URL(params[0]);
                URLConnection con = url.openConnection();
                InputStream in = con.getInputStream();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(in);
               return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar)findViewById(R.id.progressBar);
        bar.setMax(100);

    }
    public void click(View v){
        String s = "http://www.shalombewithyou.com/wp-content/uploads/yapb_cache/devil_side_shot.1ffg0l9baqxw4ws8sc0g8wcwg.6ylu316ao144c8c4woosog48w.th.jpeg";
        String result = "";
        Bitmap bitmap;
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        CustomAsynk cs = new CustomAsynk();
        try {
            bitmap = cs.execute(s).get();
            iv.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("result",result);
    }

}
