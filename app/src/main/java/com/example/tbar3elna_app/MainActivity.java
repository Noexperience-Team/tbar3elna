package com.example.tbar3elna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private Button ajoutbtn;
    TextView name;
private static  final String CHANNEL_ID="101";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String namee = user.getDisplayName();
        name=(TextView) findViewById(R.id.Namee);
        name.setText(namee);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        ajoutbtn = findViewById(R.id.ajouter);

        ajoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i = new Intent(MainActivity.this, liste_view.class);
                startActivity(i);
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.item3:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String name = user.getDisplayName();
                FirebaseDatabase db;
                db = FirebaseDatabase.getInstance();
                DatabaseReference reference;
                db.getReference().child("Users").child(name).child("need").setValue("false");
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout (View view){
       FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all","????????????????","???????????????? ???? ???????????????? ?? ???????? ???????? ?????? ??????????",getApplicationContext(),MainActivity.this);
        notificationsSender.SendNotifications();
        Change();
    }

public void Change(){
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String name = user.getDisplayName();
    FirebaseDatabase db;
    db = FirebaseDatabase.getInstance();
    DatabaseReference reference;
     db.getReference().child("Users").child(name).child("need").setValue("true");
    Toast.makeText(this,name,Toast.LENGTH_SHORT).show();

}
}