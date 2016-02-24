package com.example.raprap.midterm;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private static final String BOOK_NAME = "title";
    private TextView mBookName;

    ArrayList<HashMap<String, String>> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        new FetchBooks().execute();
    }


    public class FetchBooks extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... params) {



            String json = HttpUtils.getResponse("http://joseniandroid.herokuapp.com/api/books", "GET");
            Log.d("Response: ", json);
            if(json != null){
                try {
                    JSONArray jArray = new JSONArray(json);


                    for(int i = 0;i < jArray.length();i++)
                    {
                        JSONObject b = jArray.getJSONObject(i);
                        String title = b.getString(BOOK_NAME);
                        HashMap<String, String> book = new HashMap<String,String>();

                        book.put(BOOK_NAME,title);
                        bookList.add(book);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,bookList,R.layout.list_item,
                    new String[]{"title"},
                    new int[]{R.id.bookName});
            setListAdapter(adapter);

        }
    }
}
