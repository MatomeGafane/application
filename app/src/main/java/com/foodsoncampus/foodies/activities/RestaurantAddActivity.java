package com.foodsoncampus.foodies.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.foodsoncampus.foodies.databinding.ActivityRestaurantAddBinding;

import java.util.HashMap;

public class RestaurantAddActivity extends AppCompatActivity {

    //view binding
    private ActivityRestaurantAddBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //configure progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //handle click, begin upload restaurant
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


    }

    private String category = "";


    private void validateData() {
        /*Before adding validate data*/

        //get data
         category = binding.categoryEt.getText().toString().trim();

         //validate if not empty
        if (TextUtils.isEmpty(category)){
            Toast.makeText(this, "Please enter Restaurant (category)...!", Toast.LENGTH_SHORT).show();
        }

        else {
            addCategoryFirebase();
        }
    }

    private void addCategoryFirebase() {
        //show progress
        progressDialog.setMessage("Adding Restaurant...");
        progressDialog.show();

        //get timestamp
        long timestamp = System.currentTimeMillis();

        //setup info to add in firebase db
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", ""+timestamp);
        hashMap.put("category", ""+category);
        hashMap.put("timestamp", timestamp);
        hashMap.put("uid", ""+firebaseAuth.getUid());

        //add to firebase db..... Database Root > Categories > categoryId > category info
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Restaurants");
        ref.child(""+timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //category add success
                        progressDialog.dismiss();
                        Toast.makeText(RestaurantAddActivity.this, "Restaurant  added successfully...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //category add failed
                        progressDialog.dismiss();
                        Toast.makeText(RestaurantAddActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}