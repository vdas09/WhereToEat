package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonTellMe_Search;
    TextView textViewHey_Search, textViewSelect_Search, textViewCuisine_Search, textViewPrice_Search, textViewTime_Search;
    Spinner spinnerCuisine_Search;
    RadioButton radioButton5Min_Search, radioButton10Min_Search, radioButton20Min_Search, radioButtonOneDollar_Search, radioButtonTwoDollar_Search, radioButtonThreeDollar_Search;
    RadioGroup radioGroupDollar_Search, radioGroupTime_Search;

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

        radioGroupDollar_Search = findViewById(R.id.radioGroupDollar_Search);
        radioGroupTime_Search = findViewById(R.id.radioGroupTime_Search);

        radioButton5Min_Search = findViewById(R.id.radioButton5Min_Search);
        radioButton10Min_Search = findViewById(R.id.radioButton10Min_Search);
        radioButton20Min_Search = findViewById(R.id.radioButton20Min_Search);

        radioButtonOneDollar_Search = findViewById(R.id.radioButtonOneDollar_Search);
        radioButtonTwoDollar_Search = findViewById(R.id.radioButtonTwoDollar_Search);
        radioButtonThreeDollar_Search = findViewById(R.id.radioButtonThreeDollar_Search);


    }

    @Override
    public void onClick(View view) {
        if(view == buttonTellMe_Search){
            Intent tellMeIntent = new Intent(this, ResultActivity.class);
            startActivity(tellMeIntent);
        }
    }
}
