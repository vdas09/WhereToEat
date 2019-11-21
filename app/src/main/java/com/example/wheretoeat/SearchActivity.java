package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonTellMe_Search;
    TextView textViewHey_Search, textViewSelect_Search, textViewCuisine_Search, textViewPrice_Search, textViewTime_Search;
    //Spinner spinnerCuisine_Search;
    //CheckBox checkBoxOneDollar_Search, checkBoxTwoDollar_Search, checkBoxThreeDollar_Search;
    //RadioButton RadioButton5Min_Search, RadioButton10Min_Search, RadioButton20Min_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonTellMe_Search.setOnClickListener(this);
        textViewCuisine_Search = findViewById(R.id.textViewCuisine_Search);
        textViewSelect_Search = findViewById(R.id.textViewSelect_Search);

    }

    @Override
    public void onClick(View view) {

    }
}
