package com.example.raprap.midterm;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by raprap on 2/23/2016.
 */
public class BookFragment extends Fragment {

    private TextView mBookName;

    public static BookFragment newInstance()
    {
        return new BookFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_item,container,false);
        mBookName = (TextView) view.findViewById(R.id.bookName);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FetchBooks fBooks = new FetchBooks();
        fBooks.execute();
    }

    public class FetchBooks extends AsyncTask<String,Void,Book>{


        @Override
        protected Book doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }


            Uri builtUri = Uri.parse(BookApi.BASE_URL).buildUpon()

                            .build();

            return BookApi.getBook(builtUri,"GET");
        }

        @Override
        protected void onPostExecute(Book book) {
                mBookName.setText(book.getBookName());
        }
    }

}
