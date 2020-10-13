package com.r.cardtc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r.cardtc.Adapter.Adapter;
import com.r.cardtc.Model.CarList;
import com.r.cardtc.Model.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ImageButton back2;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,vDatabaseReference;
    private ValueEventListener valueEventListener;

    RecyclerView recyclerView;
    Adapter adapter;
    List<CarList> carLists = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Firebase Instance ..........
back2=findViewById(R.id.back2);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Car DTC").child("Car");
        //------------------------------------------------------------------//
        //RecyclerView & Adapter----------------------------------
        adapter = new Adapter(this,carLists);

        final RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
back2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,SplashScreen.class);
        startActivity(intent);
    }
});
        //Receive Data From DataBase....................................//
        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                carLists.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    CarList carList = dataSnapshot1.getValue(CarList.class);
                    carList.setmKey(dataSnapshot1.getKey());
                    carLists.add(carList);
                }

                //List Reverss Function..................
//                Collections.reverse(carLists);
                //-----------------------------------//

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this , "Error "+databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });




    }
}