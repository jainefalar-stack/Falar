package com.example.falar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_HASHTAGS = "EXTRA_HASHTAGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Header Settings Button
        MaterialCardView btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
            openDetailScreen("Settings");
        });

        // Stats Cards
        MaterialCardView cardStreak = findViewById(R.id.card_streak);
        MaterialCardView cardPoints = findViewById(R.id.card_points);
        MaterialCardView cardRank = findViewById(R.id.card_rank);

        cardStreak.setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, "Day Streak: 7 Days!", Toast.LENGTH_SHORT).show()
        );
        cardPoints.setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, "Total Points: 1,240", Toast.LENGTH_SHORT).show()
        );
        cardRank.setOnClickListener(v -> 
            Toast.makeText(MainActivity.this, "Class Rank: #5", Toast.LENGTH_SHORT).show()
        );

        // Quick Action Cards
        MaterialCardView cardMyCourses = findViewById(R.id.card_my_courses);
        MaterialCardView cardAchievements = findViewById(R.id.card_achievements);
        MaterialCardView cardProgress = findViewById(R.id.card_progress);
        MaterialCardView cardProfile = findViewById(R.id.card_profile);

        cardMyCourses.setOnClickListener(v -> openDetailScreen("My Courses (4 in progress)"));
        cardAchievements.setOnClickListener(v -> openDetailScreen("Achievements (12 badges)"));
        cardProgress.setOnClickListener(v -> openDetailScreen("Progress (68% complete)"));
        cardProfile.setOnClickListener(v -> openDetailScreen("Profile (View account)"));

        // Continue Learning Button
        MaterialButton btnContinueLearning = findViewById(R.id.btn_continue_learning);
        btnContinueLearning.setOnClickListener(v -> openDetailScreen("Continue Learning Session"));
    }

    private void openDetailScreen(String featureName) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(EXTRA_HASHTAGS, featureName);
        startActivity(intent);
    }
}