package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchResultAdapter Searchadapter;

    private List<SearchResultModel> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Retrieve the search parameters from the intent
        Intent intent = getIntent();
        String state = intent.getStringExtra("STATE");
        String district = intent.getStringExtra("DISTRICT");
        String village = intent.getStringExtra("VILLAGE");
        String programName = intent.getStringExtra("PROGRAM_NAME");
        // Set up RecyclerView and FirestoreRecyclerAdapter for displaying the results
        recyclerView = findViewById(R.id.recycler_view_activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Fetch activities from Firestore
        fetchActivitiesFromFirestore(state,district,village,programName);
        activityList = new ArrayList<>();
        Searchadapter = new SearchResultAdapter(activityList,this);
        recyclerView.setAdapter(Searchadapter);
    }

    // Inside SearchResultActivity

    private void fetchActivitiesFromFirestore(String state, String district, String village, String programName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("activities")
                .whereEqualTo("state", state);


        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    // Retrieve the activity ID and village name
                    String activityId = document.getId();
                    String villageName = document.getString("village");
                    // Create a SearchResultModel object and add it to the activityList
                    SearchResultModel model = new SearchResultModel(activityId, villageName);
                    activityList.add(model);
                }
                // Notify the adapter of the data change
                Searchadapter.notifyDataSetChanged();
            } else {
                // Handle errors if needed
            }
        });
    }


    // Your FirestoreRecyclerAdapter implementation goes here
    // Implement necessary ViewHolder and data binding methods
    // For example, extending FirestoreRecyclerAdapter<YourDataModel, YourViewHolder>
}
