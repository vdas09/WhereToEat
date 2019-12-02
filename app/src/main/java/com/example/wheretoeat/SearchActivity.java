package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Timestamp;

import com.google.firebase.firestore.FirebaseFirestore;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonTellMe_Search;
    TextView textViewHey_Search, textViewSelect_Search, textViewCuisine_Search, textViewPrice_Search, textViewTime_Search;
    Spinner spinnerCuisine_Search;
    RadioButton radioButton5Min_Search, radioButton10Min_Search, radioButton20Min_Search, radioButtonOneDollar_Search, radioButtonTwoDollar_Search, radioButtonThreeDollar_Search;
    RadioGroup radioGroupDollar_Search, radioGroupTime_Search;

    int priceSelection;

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
        //textViewTime_Search = findViewById(R.id.textViewTime_Search);

        spinnerCuisine_Search = findViewById(R.id.spinnerCuisine_Search);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cuisine_array, android.R.layout.simple_spinner_item);
        spinnerCuisine_Search.setAdapter(adapter);

        radioGroupDollar_Search = findViewById(R.id.radioGroupDollar_Search);

        radioButtonOneDollar_Search = findViewById(R.id.radioButtonOneDollar_Search);
        radioButtonTwoDollar_Search = findViewById(R.id.radioButtonTwoDollar_Search);
        radioButtonThreeDollar_Search = findViewById(R.id.radioButtonThreeDollar_Search);

        //radioGroupTime_Search = findViewById(R.id.radioGroupTime_Search);

        //radioButton5Min_Search = findViewById(R.id.radioButton5Min_Search);
        //radioButton10Min_Search = findViewById(R.id.radioButton10Min_Search);
        //radioButton20Min_Search = findViewById(R.id.radioButton20Min_Search);

        radioGroupDollar_Search.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButtonOneDollar_Search){
                    Toast.makeText(SearchActivity.this,"One Dollar", Toast.LENGTH_SHORT).show();
                    priceSelection = 1;
                }else if (i == R.id.radioButtonTwoDollar_Search){
                    Toast.makeText(SearchActivity.this,"Two Dollar", Toast.LENGTH_SHORT).show();
                    priceSelection = 2;
                }else if (i == R.id.radioButtonThreeDollar_Search){
                    Toast.makeText(SearchActivity.this,"Three Dollar", Toast.LENGTH_SHORT).show();
                    priceSelection = 3;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if(view == buttonTellMe_Search){

            String cuisineString = spinnerCuisine_Search.getSelectedItem().toString();
            int time = (int)(System.currentTimeMillis());
            Timestamp tsTemp = new Timestamp(time);

            Queries searchQuery = new Queries(cuisineString,priceSelection,tsTemp);

            db.collection("queries").add(searchQuery);
            
            Intent tellMeIntent = new Intent(this, ResultActivity.class);
            startActivity(tellMeIntent);
        }
    }
}
