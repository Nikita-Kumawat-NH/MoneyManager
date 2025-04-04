package com.example.moneymanager.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.models.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransactionActivity extends AppCompatActivity {
    private EditText amount, description;
    private Spinner category;
    private Button addTransactionBtn;
    private DatabaseReference transactionsRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        auth = FirebaseAuth.getInstance();
        transactionsRef = FirebaseDatabase.getInstance().getReference("Transactions").child(auth.getCurrentUser().getUid());

        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        addTransactionBtn = findViewById(R.id.addTransactionBtn);

        addTransactionBtn.setOnClickListener(view -> addTransaction());
    }

    // In your AddTransactionActivity.java
    private void setupDatabase() {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            String uid = auth.getCurrentUser().getUid();
            transactionsRef = FirebaseDatabase.getInstance()
                    .getReference("Transactions")
                    .child(uid);
        }
    }

    private void addTransaction() {
        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            return;
        }

        String amountText = amount.getText().toString().trim();
        String descriptionText = description.getText().toString().trim();
        String categoryText = category.getSelectedItem().toString();

        if (amountText.isEmpty() || descriptionText.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = transactionsRef.push().getKey();
        Transaction transaction = new Transaction(id, amountText, descriptionText, categoryText);

        transactionsRef.child(id).setValue(transaction)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Transaction Added", Toast.LENGTH_SHORT).show();
                        Log.d("Firebase", "Transaction added successfully");
                        finish();
                    } else {
                        Toast.makeText(this, "Failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        Log.e("Firebase", "Error adding transaction", task.getException());
                    }
                });
    }

}