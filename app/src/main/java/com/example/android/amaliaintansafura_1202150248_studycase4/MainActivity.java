package com.example.android.amaliaintansafura_1202150248_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void NamaMahasiswa(View view) {
        Intent f = new Intent(this, ListMahasiswa.class); //intent ke halaman List Mahasiswa
        startActivity(f); // agar intent bisa dijalankan
    }

    public void cariGambar(View view) {
        Intent g = new Intent(this, PencariGambar.class); // intent ke halaman cari gambat
        startActivity(g); //agar intent bisa dijalankan
    }
}
