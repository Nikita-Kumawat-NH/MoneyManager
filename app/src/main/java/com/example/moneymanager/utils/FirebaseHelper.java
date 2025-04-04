package com.example.moneymanager.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getUserTransactionsRef() {
        return database.child("Transactions").child(auth.getCurrentUser().getUid());
    }

    public static DatabaseReference getUserProfileRef() {
        return database.child("Users").child(auth.getCurrentUser().getUid());
    }

    public static void logoutUser() {
        auth.signOut();
    }
}
