//package com.foodsoncampus.foodies.activities;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.foodsoncampus.foodies.MenuUserFragment;
//import com.foodsoncampus.foodies.databinding.ActivityDashboardUserBinding;
//import com.foodsoncampus.foodies.models.ModelCategory;
//
//import java.util.ArrayList;
//
//public class DashboardUserActivity extends AppCompatActivity {
//
//    //to show in tabs
//    public ArrayList<ModelCategory> categoryArrayList;
//    public ViewPagerAdapter viewPagerAdapter;
//
//    //view binding
//    private ActivityDashboardUserBinding binding;
//
//    //firebase auth
//    private FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        //init firebase auth
//        firebaseAuth = FirebaseAuth.getInstance();
//        checkUser();
//
//        setupViewPagerAdapter(binding.viewPager);
//        binding.tabLayout.setupWithViewPager(binding.viewPager);
//
//        //handle click, logout
//        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseAuth.signOut();
//                startActivity(new Intent(DashboardUserActivity.this, MainActivity.class));
//                finish();
//            }
//        });
//
//        //handle click, open profile
//        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DashboardUserActivity.this, ProfileActivity.class));
//            }
//        });
//    }
//
//    private void setupViewPagerAdapter(ViewPager viewPager){
//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);
//
//        categoryArrayList = new ArrayList<>();
//
//        //load categories from firebase
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Restaurants"); //be careful of spellings
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //clear before adding to list
//                categoryArrayList.clear();
//
//                /*Load Categories - Static e.g. All, Most Viewed, Most Downloaded*/
//                //Add data to models
//                ModelCategory modelAll = new ModelCategory("01", "All", "", 1);
//                ModelCategory modelMostViewed = new ModelCategory("02", "Most Viewed", "", 1);
//                //ModelCategory modelMostDownloaded = new ModelCategory("03", "Most Downloaded", "", 1);
//                //add models to list
//                categoryArrayList.add(modelAll);
//                categoryArrayList.add(modelMostViewed);
//                //categoryArrayList.add(modelMostDownloaded);
//                //add data to view pager adapter
//                viewPagerAdapter.addFragment(MenuUserFragment.newInstance(
//                        ""+modelAll.getId(),
//                        ""+modelAll.getCategory(),
//                        ""+modelAll.getUid()
//                ), modelAll.getCategory());
//                viewPagerAdapter.addFragment(MenuUserFragment.newInstance(
//                        ""+modelMostViewed.getId(),
//                        ""+modelMostViewed.getCategory(),
//                        ""+modelMostViewed.getUid()
//                ), modelMostViewed.getCategory());
////                viewPagerAdapter.addFragment(MenuUserFragment.newInstance(
////                        ""+modelMostDownloaded.getId(),
////                        ""+modelMostDownloaded.getCategory(),
////                        ""+modelMostDownloaded.getUid()
////                ), modelMostDownloaded.getCategory());
//                //refresh list
//                viewPagerAdapter.notifyDataSetChanged();
//
//                //Now Load from firebase
//                for (DataSnapshot ds: snapshot.getChildren()){
//                    //get data
//                    ModelCategory model = ds.getValue(ModelCategory.class);
//                    //add data to list
//                    categoryArrayList.add(model);
//                    //add data to viewPagerAdapter
//                    viewPagerAdapter.addFragment(MenuUserFragment.newInstance(
//                            ""+model.getId(),
//                            ""+model.getCategory(),
//                            ""+model.getUid()), model.getCategory());
//                    //refresh list
//                    viewPagerAdapter.notifyDataSetChanged();
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });
//
//        //set adapter to view pager
//        viewPager.setAdapter(viewPagerAdapter);
//
//    }
//
//    public class ViewPagerAdapter extends FragmentPagerAdapter{
//
//        private ArrayList<MenuUserFragment> fragmentList = new ArrayList<>();
//        private ArrayList<String> fragmentTitleList = new ArrayList<>();
//        private Context context;
//
//        public ViewPagerAdapter( FragmentManager fm, int behavior, Context context) {
//            super(fm, behavior);
//            this.context = context;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        private void addFragment(MenuUserFragment fragment, String title){
//            //add fragment passed as parameter in fragmentList
//            fragmentList.add(fragment);
//            //add title passed as parameter in fragmentTitleList
//            fragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitleList.get(position);
//        }
//    }
//
//
//    private void checkUser() {
//        //get current user
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser==null){
//            //not logged in
//            binding.subTitleTv.setText("Not Logged In");
//        }
//        else {
//            //logged in, get user info
//            String email = firebaseUser.getEmail();
//            //set in textview of toolbar
//            binding.subTitleTv.setText(email);
//        }
//    }
//
//}
//
package com.foodsoncampus.foodies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.foodsoncampus.foodies.adapters.AdapterRestaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.foodsoncampus.foodies.databinding.ActivityDashboardUserBinding;
import com.foodsoncampus.foodies.models.ModelCategory;

import java.util.ArrayList;

public class DashboardUserActivity extends AppCompatActivity {

    //view binding
    private ActivityDashboardUserBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //arraylist to store category
    private ArrayList<ModelCategory> categoryArrayList;
    //adapter
    private AdapterRestaurant adapterRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
        loadCategories();


        //edit text change listern, search
//        binding.searchEt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //called as and when user type each letter
//                try {
//                    adapterRestaurant.getFilter().filter(s);
//                }
//                catch (Exception e){
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        //handle click, start restaurant add screen
//        binding.addRestaurantBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashboardUserActivity.this, RestaurantAddActivity.class));
//            }
//        });
//
//        //handle click, start pdf add screen
//        binding.addPdfFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashboardUserActivity.this, PdfAddActivity.class));
//            }
//        });

        //handle click, open profile
        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, ProfileActivity.class));
            }
        });


    }

    private void loadCategories() {
        //init araylist
        categoryArrayList = new ArrayList<>();

        //get all categories from firebase > Categories
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Restaurants");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arraylist before adding data into it
                categoryArrayList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    //get data
                    ModelCategory model = ds.getValue(ModelCategory.class);

                    //add to arraylist
                    categoryArrayList.add(model);
                }
                //setup adapter
                adapterRestaurant = new AdapterRestaurant(DashboardUserActivity.this, categoryArrayList);
                //set adapter to recyclerview
                binding.categoriesRv.setAdapter(adapterRestaurant);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void checkUser() {
//        //get current user
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser==null){
//            //not logged in, goto main screen
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
//        else {
//            //logged in, get user info
//            String email = firebaseUser.getEmail();
//            //set in textview of toolbar
//            binding.subTitleTv.setText(email);
//        }
//    }

    private void checkUser() {
        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            //not logged in
            binding.subTitleTv.setText("Not Logged In");
        }
        else {
            //logged in, get user info
            String email = firebaseUser.getEmail();
            //set in textview of toolbar
            binding.subTitleTv.setText(email);
        }
    }


}

