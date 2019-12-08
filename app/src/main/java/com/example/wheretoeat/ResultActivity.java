package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewHere_Result, textViewRestaurantName_Result, textViewRestaurantAddress_Result, textViewPrice_Result, textViewDollarSign_Result, textViewRestaurantPhone_Result;
    Button buttonGoToWebsite_Result, buttonCallRestaurant_Result, buttonTakeMeHere_Result, buttonResetSearch_Result;

    String restWebsite;
    boolean searchComplete = false;

    private FirebaseAuth mAuth;

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
        textViewRestaurantPhone_Result = findViewById(R.id.textViewRestaurantPhone_Result);

        mAuth = FirebaseAuth.getInstance();

        String collectionName = SearchActivity.searchQuery.qCuisineType;
        String documentName = Integer.toString(SearchActivity.searchQuery.qPrice);

        try {
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
                        restWebsite = randomResult.restWebsite;

                        FirebaseFirestore queriesDb = FirebaseFirestore.getInstance();
                        queriesDb.collection("queries").document(restaurantName).collection("entries").add(SearchActivity.searchQuery);

                        textViewDollarSign_Result.setText(randomResult.restPrice);
                        textViewRestaurantName_Result.setText(randomResult.restName);
                        textViewRestaurantAddress_Result.setText(randomResult.restLocation);
                        textViewRestaurantPhone_Result.setText(randomResult.restPhoneNumber);

                        searchComplete = true;
                    } else {
                        Toast.makeText(ResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });   
        } catch (Exception e) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (!searchComplete) {
            Toast.makeText(this, "Must wait till your restaurant is done fetching before accessing this feature!", Toast.LENGTH_SHORT).show();

            return;
        }

        if (view == buttonResetSearch_Result){
            Intent searchIntent = new Intent(this,SearchActivity.class);
            startActivity(searchIntent);
        } else if (view == buttonCallRestaurant_Result){
            String strippedPhoneNum = "tel:" + textViewRestaurantPhone_Result.getText().toString().replaceAll("\\D+","");

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(strippedPhoneNum));

            if (ContextCompat.checkSelfPermission(ResultActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ResultActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                startActivity(callIntent);
            }
        } else if (view == buttonGoToWebsite_Result){
            String websiteString = URLUtil.guessUrl(restWebsite);

            openURL(websiteString);
        } else if (view == buttonTakeMeHere_Result){
            try {
                String encodedAddr = URLEncoder.encode(textViewRestaurantAddress_Result.getText().toString(), StandardCharsets.UTF_8.toString());
                String fullAddr = "https://www.google.com/maps/dir//" + encodedAddr;
                openURL(fullAddr);
            } catch (UnsupportedEncodingException ex) {
                Toast.makeText(this, "Error opening maps", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Support function; used in website + directions
    public void openURL(String url) {
        String urlString = URLUtil.guessUrl(url);

        try {
            Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
            openURL.setData(Uri.parse(urlString));
            startActivity(openURL);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSignOut) {
            Toast.makeText(this, "Signing user out!", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            Intent loginIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(loginIntent);
        } else if (item.getItemId() == R.id.itemSearchMain) {
            Intent searchIntent = new Intent(ResultActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
