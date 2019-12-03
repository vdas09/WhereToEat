package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Vector;

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


        String collectionName = SearchActivity.searchQuery.qCuisineType;
        String documentName = Integer.toString(SearchActivity.searchQuery.qPrice);

        final FirebaseFirestore restaurantsDb = FirebaseFirestore.getInstance();
        restaurantsDb.collection(collectionName).document(documentName).collection("entries").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    Vector<Restaurant> restaurantVector = new Vector<Restaurant>();

                    for(QueryDocumentSnapshot document : task.getResult()){
                        Restaurant resultRestaurant = document.toObject(Restaurant.class);
                        restaurantVector.add(resultRestaurant);
                    }

                    int numOfRestaurants = restaurantVector.size();
                    int randomNum = (int)(Math.random()*(numOfRestaurants));

                    Restaurant randomResult = restaurantVector.get(randomNum);

                    SearchActivity.searchQuery.qResult = randomResult;
                    String restaurantName = randomResult.restName;

                    FirebaseFirestore queriesDb = FirebaseFirestore.getInstance();
                    queriesDb.collection("queries").document(restaurantName).collection("entries").add(SearchActivity.searchQuery);
                }
            }
        });

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
