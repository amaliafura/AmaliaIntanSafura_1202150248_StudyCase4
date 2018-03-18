package com.example.android.amaliaintansafura_1202150248_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PencariGambar extends AppCompatActivity {
    private EditText mUrl;
    private Button mStart;
    private ImageView mImage;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        mUrl = (EditText) findViewById(R.id.url);
        mStart = (Button) findViewById(R.id.startAstnctax);
        mImage = (ImageView)findViewById(R.id.image);
    }

    public void StartTask(View view) {
        loadImageInit(); //untuk mestart method loadImageInit
    }
    private void loadImageInit(){
        String ImgUrl = mUrl.getText().toString();
        //untuk AsyncTask mencari gambar di internet
        new imageLoad().execute(ImgUrl);
    }
    private class imageLoad extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Untuk membuat Progress Dialog
            mProgressDialog = new ProgressDialog(PencariGambar.this);

            // Mengatur message Progress Dialog
            mProgressDialog.setMessage("Loading...");

            // menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // menguduh gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmat
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // untuk menampung gambar yang sesuai dengan url dan menampilkan gambar tersebut
            mImage.setImageBitmap(bitmap);

            // menghilangkan Progress Dialog
            mProgressDialog.dismiss();
        }
    }

}
