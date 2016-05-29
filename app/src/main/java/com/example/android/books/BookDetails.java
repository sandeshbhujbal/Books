package com.example.android.books;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/*
* Activity to display book details
* */
public class BookDetails extends AppCompatActivity {
    TextView book_name_textView;
    TextView auther_name_textView;
    ImageView book_cover_imageView;
    TextView shortDescription;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        //Modify the fields in the book details activity depending upon the movie selected

        book_name_textView = (TextView) findViewById(R.id.book_name);
        book_name_textView.setText("Title: "+intent.getStringExtra("book_name"));

        auther_name_textView = (TextView) findViewById(R.id.auther_name);
        auther_name_textView.setText("Auther: "+intent.getStringExtra("auther_name"));

        book_cover_imageView = (ImageView) findViewById(R.id.book_details_cover);
        //book_cover_imageView.setImageResource(intent.getIntExtra("book_cover",5));
        //Picasso.with(this).load(intent.getStringExtra("book_cover")).into(book_cover_imageView);
        Log.e("book cover log",intent.getStringExtra("book_cover"));
        if (intent.getStringExtra("book_cover").contains("http")) {
            Log.e("temp","hellooooo");
            Picasso.with(this).load(intent.getStringExtra("book_cover")).into(book_cover_imageView);
        }
        else
        {
            String img_url = intent.getStringExtra("book_cover");
            if(img_url.contains("the_alchemist"))
                Picasso.with(this).load(R.drawable.the_alchemist).into(book_cover_imageView);
            else if(img_url.contains("out_of_box"))
                Picasso.with(this).load(R.drawable.out_of_the_box).into(book_cover_imageView);
            else if(img_url.contains("five_point"))
                Picasso.with(this).load(R.drawable.five_point_someone).into(book_cover_imageView);
            else if(img_url.contains("one_night"))
                Picasso.with(this).load(R.drawable.one_night).into(book_cover_imageView);
            else if(img_url.contains("revolution"))
                Picasso.with(this).load(R.drawable.revolution2020).into(book_cover_imageView);
            else if(img_url.contains("half_girlfriend"))
                Picasso.with(this).load(R.drawable.half_girlfriend).into(book_cover_imageView);
            else if(img_url.contains("two_states"))
                Picasso.with(this).load(R.drawable.two_states).into(book_cover_imageView);
            else if(img_url.contains("3_mistakes"))
                Picasso.with(this).load(R.drawable.three_mistakes).into(book_cover_imageView);
        }



        shortDescription = (TextView) findViewById(R.id.description);
        shortDescription.setText(intent.getStringExtra("short_description"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shareButton = (Button) findViewById(R.id.id_share_button);

        shareButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View v) {
                String book_name = intent.getStringExtra("book_name");
                String auther_name = intent.getStringExtra("auther_name");
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Hey,\nCheck this Awesome book. \nBook Name: "+book_name+"\nAuther: "+auther_name;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Book");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        book_cover_imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ImageView dialogue_cover_image;
                Dialog settingsDialog = new Dialog(v.getContext());
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.view_cover
                        , null));

                dialogue_cover_image = (ImageView)settingsDialog.findViewById(R.id.id_cover_dialogue_image);
//                dialogue_cover_image.setImageResource(intent.getIntExtra("book_cover",0));
                //Picasso.with(v.getContext()).load(intent.getStringExtra("book_cover")).into(dialogue_cover_image);

                if (intent.getStringExtra("book_cover").contains("http")) {
                    Picasso.with(v.getContext()).load(intent.getStringExtra("book_cover")).into(dialogue_cover_image);
                }
                else
                {
                    String img_url = intent.getStringExtra("book_cover");
                    if(img_url.contains("the_alchemist"))
                        Picasso.with(v.getContext()).load(R.drawable.the_alchemist).into(dialogue_cover_image);
                    else if(img_url.contains("out_of_box"))
                        Picasso.with(v.getContext()).load(R.drawable.out_of_the_box).into(dialogue_cover_image);
                    else if(img_url.contains("five_point"))
                        Picasso.with(v.getContext()).load(R.drawable.five_point_someone).into(dialogue_cover_image);
                    else if(img_url.contains("one_night"))
                        Picasso.with(v.getContext()).load(R.drawable.one_night).into(dialogue_cover_image);
                    else if(img_url.contains("revolution"))
                        Picasso.with(v.getContext()).load(R.drawable.revolution2020).into(dialogue_cover_image);
                    else if(img_url.contains("half_girlfriend"))
                        Picasso.with(v.getContext()).load(R.drawable.half_girlfriend).into(dialogue_cover_image);
                    else if(img_url.contains("two_states"))
                        Picasso.with(v.getContext()).load(R.drawable.two_states).into(dialogue_cover_image);
                    else if(img_url.contains("3_mistakes"))
                        Picasso.with(v.getContext()).load(R.drawable.three_mistakes).into(dialogue_cover_image);
                }

                settingsDialog.show();
            }
        });

    }
}
