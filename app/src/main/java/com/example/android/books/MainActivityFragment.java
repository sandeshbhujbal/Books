package com.example.android.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<Book> books;

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

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        initializeData();
        RVAdapter adapter = new RVAdapter(books);
        rv.setAdapter(adapter);
        return rootView;
    }

    private void initializeData() {
        books = new ArrayList<>();
        books.add(new Book("The Alchemist", "Paulo Coelho", R.drawable.the_alchemist, R.drawable.the_alchemist, "Written by Paulo Coelho, this is the story of a shepherd’s journey to the Egyptian pyramids to find treasure. Learn how to discover your own legend, be your own purpose, and understand omens."));
        books.add(new Book("Out of the Box", "Harsh Bhogle", R.drawable.out_of_the_box, R.drawable.out_of_the_box, "India s Richie Benaud equivalent, the guru of cricket from India. Adam Gilchrist Aptly described as India s first non-playing cricket celebrity , Harsha Bhogle has captured the entire gamut of Indian cricket in his weekly column in the Indian Express: from the advent of Dhoni to the decline of the Fab Four; from the art of coaching the Indian team to the BCCI s money-mindedness; from cricketers shooting commercials just before a match to the nail-biting finish of many an IPL clash. Out of the Box brings together the very best of Harsha s writing, in a book that will be a veritable delight to any cricket fan. Knowledgeable, frank and witty, and with a sense of drama comparable to that of cricket itself, Harsha is a master at evoking the many moods of the game. And he is at his best when paying tribute to cricketing greats: Lara, Jayasuriya, Ganguly, Laxman, Dravid, Sehwag and the incomparable Tendulkar. As he follows India s fortunes on the cricket field at home and overseas, Harsha asks the question that is on everyone s lips today: can India really be no.1 in all three forms of the game? The answer lies in our history and in the pages of Out of the Box."));
        books.add(new Book("Five point someone", "Chetan Bhagat", R.drawable.five_point_someone, R.drawable.five_point_someone, "A Chetan Bhagats debut novel that presents a dark and humorous perspective on the indian education system. Set in the premises of IIT, the story revolves around three students who become friends while struggling to keep their place in this highly esteemed institute."));
        books.add(new Book("half girlfriend", "Chetan Bhagat", R.drawable.half_girlfriend, R.drawable.half_girlfriend, "Once upon a time there was a Bihari boy called Madhav. He fell in love with a girl called Riya. \n Madhav didn't speak English well. Riya did. \n Madhav wanted a relationship. Riya didn't. \n Riya just wanted friendship. Madhav didn't. \n Riya suggested a compramise. \n She agreed to be his half girlfriend."));
        books.add(new Book("2 States", "Chetan Bhagat", R.drawable.two_states, R.drawable.two_states, "Love marriages around the world are simple. Boy loves girl. Girl loves boy. They get married. In India, there are a few more steps: Boy loves girl. Girl loves boy. Girls's family has to love the boy. Boy's family has to love girl. Girls family has to love boy's family. Boy's family has to love girl's family. Girl and boy still love each other. They get married."));
        books.add(new Book("The 3 mistakes of my life", "Chetan Bhagat", R.drawable.three_mistakes, R.drawable.three_mistakes, "In late 2000, a young boy in Ahmedabad dreams of owning a business. To accomodate his friends passion, he opens a cricket shop. However, nothing comes easy in a turbulent city. To realise their goals, they will have to face it all-religious, politics, calamities, unacceptable love and, above all, their own mistakes. Will they make it? Can an individual's dreams overcome the nightmares of real life? Can we succeed despite mistakes?"));
        books.add(new Book("One night at the call center", "Chetan Bhagat", R.drawable.one_night, R.drawable.one_night, "In winter 2004, a writer met a young girl on an overnight train journey. To pass the time she offered to tell him a story. However she had one condition: that he make it into his second book. He hesitated but asked what the story is about. The girl said the stoyr was about six people working in a call center, set in one night. She said it was the night they got a phone call. A phone call from God. Are you ready to take the call?"));
        books.add(new Book("Revolution 2020", "Chetan Bhagat", R.drawable.revolution2020, R.drawable.revolution2020, "Once upon a time, in small-town India, thre lived two intelligent boys. One wanted to use his intelligence to make money. One wanted to use his intelligence to start a revolution. The problem was they both loved the same girl. Welcome to Revolution 2020. A story about childhood friends Gopal, Raghav and Aarti, who struggle to find success, love and happiness in varanasi. However it is not easy to attain these in an unfair society that rewards the corrupt. As Gopal gives into the system, and Raghav fights it, who will win?"));


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
//            holder.cv .setOnClickListener(new MyOnClickListener());

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Recycle Click hellooo" + books.get(position).book_name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), BookDetails.class);
                    intent.putExtra("book_name", books.get(position).book_name);
                    intent.putExtra("auther_name", books.get(position).book_auther);
                    intent.putExtra("book_cover", books.get(position).book_cover_id);
                    intent.putExtra("short_description",books.get(position).description);
                    startActivity(intent);

                }
            });

            holder.bookName.setText(books.get(position).book_name);
            holder.autherName.setText(books.get(position).book_auther);
            holder.bookCover.setImageResource(books.get(position).book_cover_id);
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
