package com.example.tbar3elna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class liste_view extends AppCompatActivity {
    DatabaseReference ref ;
    ListView listView ;
    FirebaseDatabase database ;
    ArrayList<String> list ;
    ArrayAdapter<String> adapter;
    User patient ;
    User pa;

    List<User> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_view);
        FirebaseDatabase dt=FirebaseDatabase.getInstance();
        DatabaseReference r=dt.getReference("Users");
        list = new ArrayList<> () ;
        patientList=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.listview_layout, list);
        patient=new User();
        listView = (ListView) findViewById(R.id.listView) ;
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren() )
                {
                    patient=ds.getValue(User.class);
                    if (patient.getNeed().equals("true")) {
                        patientList.add(patient);
                    }
                }
                UserListAdapter adapter=new UserListAdapter(liste_view.this,patientList);
                listView.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
