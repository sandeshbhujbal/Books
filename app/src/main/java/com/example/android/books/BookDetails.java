package com.example.android.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BookDetails extends AppCompatActivity {
    TextView book_name_textView;
    TextView auther_name_textView;
    ImageView book_cover_imageView;
    TextView shortDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
//        String uname=intent.getStringExtra("name");
//        Toast.makeText(this, "Info on next page: "+uname , Toast.LENGTH_SHORT).show();

        book_name_textView = (TextView) findViewById(R.id.book_name);
        book_name_textView.setText("Title: "+intent.getStringExtra("book_name"));

        auther_name_textView = (TextView) findViewById(R.id.auther_name);
        auther_name_textView.setText("Auther: "+intent.getStringExtra("auther_name"));

        book_cover_imageView = (ImageView) findViewById(R.id.book_details_cover);
        book_cover_imageView.setImageResource(intent.getIntExtra("book_cover",5));

        shortDescription = (TextView) findViewById(R.id.description);
        shortDescription.setText(intent.getStringExtra("short_description"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

}
