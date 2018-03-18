package com.example.android.amaliaintansafura_1202150248_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {
    private Button mButton;
    private ListView mListMahasiswa;
    private ProgressBar mProgress;
    private String[] daftarMahasiswaArray = {
            "Kang Daniel", "Sunho", "Lay", "Ong Seongwoo", "Jaehwan", "Minhyun", "B.I"
            , "Minhyuk", "Sungjae",};
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        mButton = (Button) findViewById(R.id.startAstnctax);
        mListMahasiswa = (ListView) findViewById(R.id.listMahasiswa);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        mListMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process adapter with asyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListMahasiswa.getAdapter(); //casting suggestion
            //untuk isi didalam progress bar
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setProgress(0);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgress.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : daftarMahasiswaArray) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) daftarMahasiswaArray.length) * 100);
            mProgress.setProgress(current_status);

            //set progress only working for horizontal loading
            mProgressDialog.setProgress(current_status);

            //set message will not working when using horizontal loading
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //menyembunyikan pogressbar
            mProgress.setVisibility(View.GONE);

            //menghapus progress dialog
            mProgressDialog.dismiss();
            mListMahasiswa.setVisibility(View.VISIBLE);
        }


        }


}

