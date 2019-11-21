package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Spinner spinnerCuisine_Search;
    CheckBox checkBoxOneDollar_Search, checkBoxTwoDollar_Search, checkBoxThreeDollar_Search;
    RadioButton RadioButton5Min_Search, RadioButton10Min_Search, RadioButton20Min_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonTellMe_Search = findViewById(R.id.buttonTellMe_Search);
        buttonTellMe_Search.setOnClickListener(this);

        textViewCuisine_Search = findViewById(R.id.textViewCuisine_Search);
        textViewSelect_Search = findViewById(R.id.textViewSelect_Search);
        textViewHey_Search = findViewById(R.id.textViewHey_Search);
        textViewPrice_Search = findViewById(R.id.textViewPrice_Search);
        textViewTime_Search = findViewById(R.id.textViewTime_Search);

        spinnerCuisine_Search = findViewById(R.id.spinnerCuisine_Search);
        checkBoxOneDollar_Search = findViewById(R.id.checkBoxOneDollar_Search);
        checkBoxTwoDollar_Search = findViewById(R.id.checkBoxTwoDollar_Search);
        checkBoxThreeDollar_Search = findViewById(R.id.checkBoxThreeDollar_Search);

        RadioButton5Min_Search = findViewById(R.id.radioButton5Min_Search);
        RadioButton10Min_Search = findViewById(R.id.radioButton10Min_Search);
        RadioButton20Min_Search = findViewById(R.id.radioButton20Min_Search);


    }

    @Override
    public void onClick(View view) {
        if(view == buttonTellMe_Search){
            Intent tellMeIntent = new Intent(this, ResultActivity.class);
            startActivity(tellMeIntent);
        }
    }
}
