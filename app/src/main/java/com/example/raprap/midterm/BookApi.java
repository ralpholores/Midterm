package com.example.raprap.midterm;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by raprap on 2/23/2016.
 */
public class BookApi {


    public static final String BASE_URL     = "http://joseniandroid.herokuapp.com/api/books";
    private static String BOOK_NAME = "title";

    public static Book getBook(Uri uri,@NonNull String requestMethod){

        String json = HttpUtils.getResponse("http://joseniandroid.herokuapp.com/api/books",requestMethod);
        Log.d("Response: " , json);

        if(TextUtils.isEmpty(json)){
            return null;
        }


        String bookTitle = "";
                try {
            JSONArray jArray = new JSONArray(json);
            for(int i = 0;i < jArray.length();i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);
                bookTitle = jObject.getString(BOOK_NAME);

            }

            Book b = new Book()
                    .setBookName(bookTitle);

            return b;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
