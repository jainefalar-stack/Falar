package com.example.falar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class SecondActivity extends AppCompatActivity {

    private ImageView ivFeatureIcon;
    private TextView tvFeatureTitle;
    private TextView tvFeatureSubtitle;
    private LinearLayout containerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ivFeatureIcon = findViewById(R.id.iv_feature_icon);
        tvFeatureTitle = findViewById(R.id.tv_feature_title);
        tvFeatureSubtitle = findViewById(R.id.tv_feature_subtitle);
        containerDetails = findViewById(R.id.container_details);

        MaterialButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        String selectedFeature = "";
        if (intent != null && intent.hasExtra(MainActivity.EXTRA_HASHTAGS)) {
            selectedFeature = intent.getStringExtra(MainActivity.EXTRA_HASHTAGS);
        }

        populateFeatureDetails(selectedFeature);
    }

    private void populateFeatureDetails(String feature) {
        containerDetails.removeAllViews();

        String targetFeature = TextUtils.isEmpty(feature) ? "My Courses" : feature;

        if (targetFeature.contains("My Courses")) {
            setupMyCoursesView();
        } else if (targetFeature.contains("Achievements")) {
            setupAchievementsView();
        } else if (targetFeature.contains("Progress")) {
            setupProgressView();
        } else if (targetFeature.contains("Profile")) {
            setupProfileView();
        } else if (targetFeature.contains("Continue Learning")) {
            setupContinueLearningView();
        } else if (targetFeature.contains("Settings")) {
            setupSettingsView();
        } else {
            setupDefaultView(targetFeature);
        }
    }

    private void setupMyCoursesView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_courses);
        tvFeatureTitle.setText("My Courses");
        tvFeatureSubtitle.setText("4 active courses in progress");

        addCourseCard("📘 Android App Development with Jetpack Compose", "Module 12 of 15 • 85% Complete", 85);
        addCourseCard("📱 Kotlin Fundamentals & Clean Architecture", "Module 8 of 12 • 60% Complete", 60);
        addCourseCard("🎨 UI/UX Design for Mobile Applications", "Module 4 of 10 • 40% Complete", 40);
        addCourseCard("⚡ Data Structures & Algorithms in Java", "Module 2 of 10 • 20% Complete", 20);
    }

    private void setupAchievementsView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_star);
        tvFeatureTitle.setText("Achievements");
        tvFeatureSubtitle.setText("12 of 15 Badges Unlocked");

        addBadgeCard("🔥 7-Day Streak Master", "Maintained a learning streak for 7 consecutive days.", "Unlocked Oct 10");
        addBadgeCard("⚡ Fast Learner", "Completed 5 learning modules in a single day.", "Unlocked Oct 08");
        addBadgeCard("🏆 Top 5 Class Rank", "Reached top 5 position in class leaderboard.", "Unlocked Oct 05");
        addBadgeCard("🎓 Course Conqueror", "Successfully completed your first full Android course.", "Unlocked Sep 28");
        addBadgeCard("🎯 Quiz Master", "Scored 100% accuracy on 10 consecutive quizzes.", "Unlocked Sep 20");
        addBadgeCard("🌅 Early Bird", "Completed a study lesson before 8:00 AM.", "Unlocked Sep 15");
    }

    private void setupProgressView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_progress);
        tvFeatureTitle.setText("Learning Progress");
        tvFeatureSubtitle.setText("Overall Completion Rate: 68%");

        addProgressOverviewCard("68% Overall Completion", 68);

        addStatDetailCard("⏱ Total Study Hours", "28.5 Hours logged this month");
        addStatDetailCard("📚 Lessons Completed", "42 / 60 Total Lessons finished");
        addStatDetailCard("🎯 Quiz Performance", "18 / 20 Quizzes Passed (90% Average)");
        addStatDetailCard("🔥 Streak Active", "5 / 7 Days active this week");
    }

    private void setupProfileView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_profile);
        tvFeatureTitle.setText("User Profile");
        tvFeatureSubtitle.setText("Student Account Information");

        addProfileCard("👤 Student Name", "Falar Student");
        addProfileCard("📧 Email Address", "student@falar.app");
        addProfileCard("🌟 Learner Status", "Level 12 Active Learner");
        addProfileCard("🏅 Total XP Earned", "1,240 Points");
        addProfileCard("🔥 Current Day Streak", "7 Consecutive Days");
        addProfileCard("📅 Member Since", "October 2026");
    }

    private void setupContinueLearningView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_courses);
        tvFeatureTitle.setText("Current Lesson");
        tvFeatureSubtitle.setText("Ready to continue your session?");

        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView titleView = createTitleText("Module 12: Material 3 UI Layouts");
        TextView descView = createSubtitleText("Learn how to create beautiful, responsive Android layouts using Material Cards, Buttons, and Color Themes.");

        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setProgress(85);
        progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.button_purple)));
        LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(8));
        progressParams.setMargins(0, dpToPx(12), 0, dpToPx(16));
        progressBar.setLayoutParams(progressParams);

        MaterialButton actionBtn = new MaterialButton(this);
        actionBtn.setText("Start Lesson Now");
        actionBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        actionBtn.setTextColor(Color.WHITE);
        actionBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.button_purple)));
        actionBtn.setCornerRadius(dpToPx(12));
        actionBtn.setOnClickListener(v -> 
            Toast.makeText(SecondActivity.this, "Lesson Started! Happy learning! 🚀", Toast.LENGTH_SHORT).show()
        );

        layout.addView(titleView);
        layout.addView(descView);
        layout.addView(progressBar);
        layout.addView(actionBtn);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private void setupSettingsView() {
        ivFeatureIcon.setImageResource(R.drawable.ic_wrench);
        tvFeatureTitle.setText("Settings");
        tvFeatureSubtitle.setText("Manage your app preferences");

        addProfileCard("🔔 Notifications & Reminders", "Enabled (Daily at 8:00 PM)");
        addProfileCard("🌙 Theme Mode", "System Default (Light Green)");
        addProfileCard("🌐 Language", "English (US)");
        addProfileCard("🔒 Privacy & Account", "Secure & Protected");
    }

    private void setupDefaultView(String feature) {
        ivFeatureIcon.setImageResource(R.drawable.ic_logo);
        tvFeatureTitle.setText(feature);
        tvFeatureSubtitle.setText("Detailed overview for " + feature);

        addCourseCard("📌 " + feature + " Overview", "Access all statistics and controls for " + feature, 100);
    }

    // --- Helper Methods to Build UI Cards Programmatically ---

    private void addCourseCard(String title, String subtitle, int progress) {
        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView titleView = createTitleText(title);
        TextView subView = createSubtitleText(subtitle);

        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setProgress(progress);
        progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.brand_blue_light)));

        LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(6));
        progressParams.setMargins(0, dpToPx(10), 0, 0);
        progressBar.setLayoutParams(progressParams);

        layout.addView(titleView);
        layout.addView(subView);
        layout.addView(progressBar);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private void addBadgeCard(String title, String description, String unlockedDate) {
        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView titleView = createTitleText(title);
        TextView descView = createSubtitleText(description);

        TextView dateView = new TextView(this);
        dateView.setText(unlockedDate);
        dateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        dateView.setTextColor(ContextCompat.getColor(this, R.color.streak_orange));
        dateView.setTypeface(null, Typeface.BOLD);

        LinearLayout.LayoutParams dateParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dateParams.setMargins(0, dpToPx(6), 0, 0);
        dateView.setLayoutParams(dateParams);

        layout.addView(titleView);
        layout.addView(descView);
        layout.addView(dateView);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private void addProgressOverviewCard(String title, int progress) {
        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView titleView = createTitleText(title);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        titleView.setTextColor(ContextCompat.getColor(this, R.color.button_purple));

        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setProgress(progress);
        progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.button_purple)));

        LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(12));
        progressParams.setMargins(0, dpToPx(12), 0, 0);
        progressBar.setLayoutParams(progressParams);

        layout.addView(titleView);
        layout.addView(progressBar);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private void addStatDetailCard(String title, String detail) {
        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView titleView = createTitleText(title);
        TextView detailView = createSubtitleText(detail);

        layout.addView(titleView);
        layout.addView(detailView);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private void addProfileCard(String label, String value) {
        MaterialCardView card = createBaseCard();
        LinearLayout layout = createCardLayout();

        TextView labelView = createSubtitleText(label);
        labelView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView valueView = createTitleText(value);
        valueView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        layout.addView(labelView);
        layout.addView(valueView);

        card.addView(layout);
        containerDetails.addView(card);
    }

    private MaterialCardView createBaseCard() {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(0, 0, 0, dpToPx(12));
        card.setLayoutParams(cardParams);
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
        card.setRadius(dpToPx(16));
        card.setCardElevation(dpToPx(2));
        card.setStrokeWidth(0);
        return card;
    }

    private LinearLayout createCardLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        int p = dpToPx(16);
        layout.setPadding(p, p, p, p);
        return layout;
    }

    private TextView createTitleText(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_dark));
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    private TextView createSubtitleText(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_subtitle));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(4), 0, 0);
        textView.setLayoutParams(params);
        return textView;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}