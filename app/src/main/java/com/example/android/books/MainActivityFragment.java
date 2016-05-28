package com.example.android.books;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String booksJsonStr = null;
    JSONObject jObj = null;
    RecyclerView rv;
    RVAdapter mAdapter;
    EditText input_search;
    String base_api_url="https://www.googleapis.com/books/v1/volumes?q=";
    List<Book> books;
    Button search_button;
    ImageView book_search_image;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        input_search = (EditText) rootView.findViewById(R.id.input_search);

        book_search_image = (ImageView) rootView.findViewById(R.id.image_search_book);
        book_search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_text = input_search.getText().toString();

                if(search_text.length() == 0) {
                    Snackbar snackbar = Snackbar
                            .make(v, "Please enter search string", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    String search_string = input_search.getText().toString();
                    initializeData(base_api_url+search_string.replaceAll(" ",""));
                }
            }
        });

        initializeData("");

        return rootView;
    }

    private void initializeData(String url) {
        books = new ArrayList<>();
        if(url == "")
        {
            return;
        }
//commenting out the static book data

//        books.add(new Book("The Alchemist", "Paulo Coelho", R.drawable.the_alchemist, R.drawable.the_alchemist, "The protagonist of The Alchemist is a young Andalusian shepherd who lives in Spain. He begins his journey to Egypt when he is chased by his frequent dreams of a mysterious child who coaxes him to look for treasure buried at the base of the Egyptian pyramids. He comes across Melchizedek, the king of Salem during his journey to Egypt. Santiago is enlightened by him to the concept of a 'Personal Legend' or a quest that would help him realize the core and depth of the desires buried in his heart. He meets an aspiring alchemist and a beautiful young woman en route. He also meets an alchemist who accompanies him in his expedition to enlighten him with his profound wisdom and knowledge. Through this journey he discovers the truths regarding why the treasure evades him each time. The Alchemist is a journey of wisdom, self-discovery and philosophy and finding your own 'Personal Legend' against the comparatively invaluable material possessions.", "image_url"));
//        books.add(new Book("Out of the Box", "Harsha Bhogle", R.drawable.out_of_the_box, R.drawable.out_of_the_box, "India s Richie Benaud equivalent, the guru of cricket from India. Adam Gilchrist Aptly described as India s first non-playing cricket celebrity , Harsha Bhogle has captured the entire gamut of Indian cricket in his weekly column in the Indian Express: from the advent of Dhoni to the decline of the Fab Four; from the art of coaching the Indian team to the BCCI s money-mindedness; from cricketers shooting commercials just before a match to the nail-biting finish of many an IPL clash. Out of the Box brings together the very best of Harsha s writing, in a book that will be a veritable delight to any cricket fan. Knowledgeable, frank and witty, and with a sense of drama comparable to that of cricket itself, Harsha is a master at evoking the many moods of the game. And he is at his best when paying tribute to cricketing greats: Lara, Jayasuriya, Ganguly, Laxman, Dravid, Sehwag and the incomparable Tendulkar. As he follows India s fortunes on the cricket field at home and overseas, Harsha asks the question that is on everyone s lips today: can India really be no.1 in all three forms of the game? The answer lies in our history and in the pages of Out of the Box.", "img_url"));
//        books.add(new Book("Five point someone", "Chetan Bhagat", R.drawable.five_point_someone, R.drawable.five_point_someone, "A Chetan Bhagats debut novel that presents a dark and humorous perspective on the indian education system. Set in the premises of IIT, the story revolves around three students who become friends while struggling to keep their place in this highly esteemed institute.", "img_url"));
//        books.add(new Book("half girlfriend", "Chetan Bhagat", R.drawable.half_girlfriend, R.drawable.half_girlfriend, "Once upon a time there was a Bihari boy called Madhav. He fell in love with a girl called Riya. \nMadhav didn't speak English well. Riya did. \nMadhav wanted a relationship. Riya didn't. \nRiya just wanted friendship. Madhav didn't. \nRiya suggested a compramise. \nShe agreed to be his half girlfriend.", "img_url"));
//        books.add(new Book("2 States", "Chetan Bhagat", R.drawable.two_states, R.drawable.two_states, "Love marriages around the world are simple:\nBoy loves girl. Girl loves boy. They get married. \nIn India, there are a few more steps: \nBoy loves girl. Girl loves boy. \nGirls's family has to love the boy. Boy's family has to love girl. \nGirls family has to love boy's family. Boy's family has to love girl's family. \nGirl and boy still love each other. They get married.\n\nWelcome to 2 States, the story of Krish and Ananya who are from two different states of India, deeply in love with each other and want to get married. Of course their parents don't agree. To convert their love story into love marriage, the couple has a tough battle ahead of them, for it is easy to fight and rebel, but much harder to convince. Will they make it?\n", "img_url"));
//        books.add(new Book("The 3 mistakes of my life", "Chetan Bhagat", R.drawable.three_mistakes, R.drawable.three_mistakes, "In late 2000, a young boy in Ahmedabad dreams of owning a business. To accomodate his friends passion, he opens a cricket shop. However, nothing comes easy in a turbulent city. To realise their goals, they will have to face it all-religious, politics, calamities, unacceptable love and, above all, their own mistakes. Will they make it? Can an individual's dreams overcome the nightmares of real life? Can we succeed despite mistakes?", "img_url"));
//        books.add(new Book("One night at the call center", "Chetan Bhagat", R.drawable.one_night, R.drawable.one_night, "In winter 2004, a writer met a young girl on an overnight train journey. To pass the time she offered to tell him a story. However she had one condition: that he make it into his second book. He hesitated but asked what the story is about. \n\nThe girl said the story was about six people working in a call center, set in one night. \n\nShe said it was the night they got a phone call. \n\nA phone call from God. \n\nAre you ready to take the call?", "img_url"));
//        books.add(new Book("Revolution 2020", "Chetan Bhagat", R.drawable.revolution2020, R.drawable.revolution2020, "Once upon a time, in small-town India, thre lived two intelligent boys. \n\nOne wanted to use his intelligence to make money. \n\nOne wanted to use his intelligence to start a revolution. \n\nThe problem was they both loved the same girl. \n\nWelcome to Revolution 2020. A story about childhood friends Gopal, Raghav and Aarti, who struggle to find success, love and happiness in varanasi. However it is not easy to attain these in an unfair society that rewards the corrupt. As Gopal gives into the system, and Raghav fights it, who will win?", "img_url"));

//        URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=chetanbhagat");
//        String url = "https://www.googleapis.com/books/v1/volumes?q=harrypotter";

        //Data will be fetched from the google books api
        new fetchBooks().execute(url);
    }

    public class fetchBooks extends AsyncTask<String, Void, Object> {

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String no_internet_msg = "No Internet Connection";
            String no_records_msg = "No fecords found.";
            //Handle errors
            if(o != null) {
                if (o.toString() == "emtpyInputStream") {
                    Snackbar snackbar = Snackbar
                            .make(getView(), no_internet_msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (o.toString() == "emptyBuffer") {
                    Snackbar snackbar = Snackbar
                            .make(getView(), no_records_msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (o.toString() == "unknownHost"){
                    Snackbar snackbar = Snackbar
                            .make(getView(), no_internet_msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
            RVAdapter adapter = new RVAdapter(books);
            rv.setAdapter(adapter);
        }

        @Override
        protected Object doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                //connect to the google api using the url provided
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                String buffer = new String();

                //If connection fails display error message
                if (inputStream == null) {
                    return "emtpyInputStream";
                }

                //Read the data from response
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer = buffer + line;
                }

                //Handle empty data
                if (buffer.length() == 0) {
                    return "emptyBuffer";
                }

                //convert the string output to json object
                booksJsonStr = buffer.toString();
                jObj = new JSONObject(booksJsonStr);
                JSONArray booksArray = jObj.getJSONArray("items");

                books = new ArrayList<>();
                Book book = null;

                //Read the json data and create the book object and add it into array list
                for (int i = 0; i < booksArray.length(); i++) {
                    try {
                        JSONObject object = booksArray.getJSONObject(i);

                        String book_title = object.getJSONObject("volumeInfo").getString("title");
                        String authers = "";
                        JSONArray authers_array = object.getJSONObject("volumeInfo").getJSONArray("authors");

                        for (int j = 0; j < object.getJSONObject("volumeInfo").getJSONArray("authors").length(); j++) {
                            if (authers != "")
                            {
                                authers = authers + ", " + object.getJSONObject("volumeInfo").getJSONArray("authors").get(j).toString();
                            }
                            else
                            {
                                authers = object.getJSONObject("volumeInfo").getJSONArray("authors").get(j).toString();
                            }

                        }
                        String img_url = object.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                        String short_description = object.getJSONObject("volumeInfo").getString("description");
                        book = new Book(book_title, authers, 1, 1, short_description, img_url);
                        books.add(book);
                    } catch (Exception e) {
                        Log.e("Json Parsing error", e.toString());
                    }
                }

            } catch (UnknownHostException e){
                return "unknownHost";
            }
            catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                return "ioException";
            } catch (Exception e) {
                Log.e("exception", e.toString());
                return "emptyBuffer";
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            return null;
        }
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BookViewHolder> {
        List<Book> books;

        RVAdapter(List<Book> books) {
            this.books = books;
        }

        @Override
        public RVAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);

            BookViewHolder bvh = new BookViewHolder(v);
            return bvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.BookViewHolder holder, final int position) {

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    * On clicking on book pass the book details to the intent, to display in the next activity
                    * */
                    Intent intent = new Intent(getContext(), BookDetails.class);
                    intent.putExtra("book_name", books.get(position).book_name);
                    intent.putExtra("auther_name", books.get(position).book_auther);
//                    intent.putExtra("book_cover", books.get(position).book_cover_id);
                    intent.putExtra("book_cover", books.get(position).getImg_url());
                    intent.putExtra("short_description", books.get(position).description);
                    startActivity(intent);

                }
            });

            /*Pass the data to recycler view */
            holder.bookName.setText(books.get(position).book_name);
            holder.autherName.setText(books.get(position).book_auther);
            Picasso.with(getContext()).load(books.get(position).getImg_url()).into(holder.bookCover);
            //holder.bookCover.setImageResource(books.get(position).book_cover_id);
        }

        @Override
        public int getItemCount() {
            return books.size();
        }

        public class BookViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView bookName;
            TextView autherName;
            ImageView bookCover;

            BookViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                bookName = (TextView) itemView.findViewById(R.id.book_name);
                autherName = (TextView) itemView.findViewById(R.id.book_auther);
                bookCover = (ImageView) itemView.findViewById(R.id.book_cover);
            }
        }
    }
}
