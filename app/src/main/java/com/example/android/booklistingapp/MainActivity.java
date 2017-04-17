package com.example.android.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button searchBTN;
    private EditText searchText;
    private String searchWord ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        searchBTN = (Button)findViewById(R.id.searchBTN);
        searchText = (EditText)findViewById(R.id.searchText);
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchWord = searchText.getText().toString();
                Intent activity = new Intent(MainActivity.this, BookActivity.class);
                String URL = "https://www.googleapis.com/books/v1/volumes?q="+searchWord+"&maxResults=20";
                activity.putExtra("key" , URL);
                startActivity(activity);
            }
        });
    }
    public String getSearchText () {return searchWord;}
}
