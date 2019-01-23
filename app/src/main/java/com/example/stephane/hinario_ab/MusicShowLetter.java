package com.example.stephane.hinario_ab;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;



public class MusicShowLetter extends AppCompatActivity {

    private String music_name;
    private String author_name;
    private String music_content;

    private TextView music_name_textView;
    private TextView author_name_textView;
    private TextView music_content_textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_content);

        music_name_textView = (TextView) findViewById(R.id.music_name_content);
        author_name_textView = (TextView) findViewById(R.id.author_name_content);
        music_content_textView = (TextView) findViewById(R.id.music_content);

        Intent intent = getIntent();

        music_name = intent.getStringExtra("MUSIC_NAME");
        author_name = intent.getStringExtra("AUTHOR_NAME");
        music_content = intent.getStringExtra("MUSIC_CONTENT");

        Log.d("--- MUSIC content ---", music_content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        music_name_textView.setText(music_name);
        author_name_textView.setText(author_name);
        music_content_textView.setText(music_content);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
