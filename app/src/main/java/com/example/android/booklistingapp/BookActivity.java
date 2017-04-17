package com.example.android.booklistingapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    public static final String LOG_TAG = BookActivity.class.getName();
    private String USGS_REQUEST_URL ;
    private BookAdapter adapter;
    private String searchText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        Bundle b = getIntent().getExtras();
        String USGS_REQUEST_URL = b.getString("key", "");
        ListView bookListView = (ListView) findViewById(R.id.list);

        adapter = new BookAdapter(this, new ArrayList<Book>());

        bookListView.setAdapter(adapter);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            BookAsyncTask task = new BookAsyncTask();
            task.execute(USGS_REQUEST_URL);
        } else {
            Intent activity = new Intent(BookActivity.this, MainActivity.class);
            startActivity(activity);
            Toast.makeText(getApplicationContext(), "Network offline", Toast.LENGTH_LONG).show();
        }
    }


    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Book> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> data) {
            adapter.clear();
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
            else
            {
                Intent activity = new Intent(BookActivity.this, MainActivity.class);
                startActivity(activity);
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
        }
    }
}

