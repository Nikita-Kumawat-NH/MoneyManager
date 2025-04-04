package com.example.moneymanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moneymanager.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button addTransactionBtn, viewTransactionsBtn, reportsBtn, profileBtn, logoutBtn, settingBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        addTransactionBtn = findViewById(R.id.addTransactionBtn);
        viewTransactionsBtn = findViewById(R.id.viewTransactionsBtn);
        reportsBtn = findViewById(R.id.reportsBtn);
        profileBtn = findViewById(R.id.profileBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        settingBtn = findViewById(R.id.settingBtn);

        addTransactionBtn.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AddTransactionActivity.class)));
        viewTransactionsBtn.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, TransactionHistoryActivity.class)));
        reportsBtn.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ReportsActivity.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));
        settingBtn.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, SettingsActivity.class)));

        logoutBtn.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });
    }
}
