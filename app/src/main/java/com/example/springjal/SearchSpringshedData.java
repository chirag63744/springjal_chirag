package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class SearchSpringshedData extends AppCompatActivity {

    EditText stateEditText, districtEditText, villageEditText, programEditText;
    RelativeLayout fetchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_springshed_data);

        stateEditText = findViewById(R.id.stateEditText);
        districtEditText = findViewById(R.id.districtEditText);
        villageEditText = findViewById(R.id.villageEditText);
        programEditText = findViewById(R.id.programEditText);
        fetchButton = findViewById(R.id.fetchbtn);

        fetchButton.setOnClickListener(v -> {
            String state = stateEditText.getText().toString();
            String district = districtEditText.getText().toString();
            String village = villageEditText.getText().toString();
            String programName = programEditText.getText().toString();

            // Create an Intent to pass the search parameters to the SearchResultActivity
            Intent intent = new Intent(SearchSpringshedData.this, SearchResultActivity.class);
            intent.putExtra("STATE", state);
            intent.putExtra("DISTRICT", district);
            intent.putExtra("VILLAGE", village);
            intent.putExtra("PROGRAM_NAME", programName);
            startActivity(intent);
        });
    }
}
