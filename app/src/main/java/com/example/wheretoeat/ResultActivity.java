package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewHere_Result, textViewRestaurantName_Result, textViewRestaurantAddress_Result, textViewPrice_Result, textViewDollarSign_Result, textViewWalkingTime_Result, textViewWalkingTimeAmount_Result;
    Button buttonGoToWebsite_Result, buttonCallRestaurant_Result, buttonTakeMeHere_Result, buttonResetSearch_Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonCallRestaurant_Result = findViewById(R.id.buttonCallRestaurant_Result);
        buttonGoToWebsite_Result = findViewById(R.id.buttonGoToWebsite_Results);
        buttonTakeMeHere_Result = findViewById(R.id.buttonTakeMeHere_Result);
        buttonResetSearch_Result = findViewById(R.id.buttonResetSearch_Result);

        buttonCallRestaurant_Result.setOnClickListener(this);
        buttonGoToWebsite_Result.setOnClickListener(this);
        buttonTakeMeHere_Result.setOnClickListener(this);
        buttonResetSearch_Result.setOnClickListener(this);

        textViewDollarSign_Result = findViewById(R.id.textViewDollarSign_Result);
        textViewHere_Result = findViewById(R.id.textViewHere_Result);
        textViewRestaurantName_Result = findViewById(R.id.textViewRestaurantName_Result);
        textViewRestaurantAddress_Result = findViewById(R.id.textViewRestaurantAddress_Result);
        textViewPrice_Result = findViewById(R.id.textViewPrice_Result);
        textViewWalkingTime_Result = findViewById(R.id.textViewWalkingTime_Result);
        textViewWalkingTimeAmount_Result = findViewById(R.id.textViewWalkingTimeAmount_Result);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonResetSearch_Result){
            Intent searchIntent = new Intent(this,SearchActivity.class);
            startActivity(searchIntent);
        }else if(view == buttonCallRestaurant_Result){
            //Write code for calling restaurant
        }else if(view == buttonGoToWebsite_Result){
            //Write code for going to website URL
        }else if (view == buttonTakeMeHere_Result){
            //Write code for launching maps
        }
    }
}
