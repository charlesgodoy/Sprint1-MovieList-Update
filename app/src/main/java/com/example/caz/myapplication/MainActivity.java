package com.example.caz.myapplication;

        import android.app.Activity;
        import android.arch.lifecycle.ViewModelProviders;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Paint;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Context         context;
    private LinearLayout    listLayout;
    private MovieViewModel movieViewModel;

    public static final int REQUEST_CODE = 1;

    // Charles Godoy
    // AND1 - Sprint1 - Nov 9, 2018

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        listLayout = findViewById(R.id.list_layout);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        final android.arch.lifecycle.Observer<ArrayList<Movie>> observer = new android.arch.lifecycle.Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {

                if(movies != null) {
                    refreshListView(movies);
                }

            }
        };

        movieViewModel.getMoviesList().observe(this, observer);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                Movie newMovie = new Movie(Movie.NO_ID);

                intent.putExtra(EditActivity.EDIT_CHECK_KEY, newMovie);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    private TextView getDefaultTextView(final Movie movie) {
        TextView textView = new TextView(context);
        textView.setText(movie.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);

        if(movie.isWatchedMovie() == true){
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(EditActivity.EDIT_CHECK_KEY, movie);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return textView;
    }

    private void refreshListView(ArrayList<Movie> movies) {
        listLayout.removeAllViews();
        for(Movie movie : movies) {
            listLayout.addView(getDefaultTextView(movie));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_CODE) {
                if(data != null) {
                    Movie returnedMovie = (Movie)data.getSerializableExtra(EditActivity.EDIT_CHECK_KEY);

                    movieViewModel.addMovie(returnedMovie);
                }
            }
        }



    }
}