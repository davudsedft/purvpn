package com.dabut.purcow;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFirebaseDatabase {
    private DatabaseReference myRef;

    public MyFirebaseDatabase() {
        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
    }

    public void writeDataToDatabase(String data) {
        // Write data to the database
        myRef.setValue(data);
    }

    public void readDataFromDatabase() {
        // Read data from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                System.out.println("Value from database: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value: " + error.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        MyFirebaseDatabase firebaseDatabase = new MyFirebaseDatabase();
        firebaseDatabase.writeDataToDatabase("Hello, World!");
        firebaseDatabase.readDataFromDatabase();
    }
}
