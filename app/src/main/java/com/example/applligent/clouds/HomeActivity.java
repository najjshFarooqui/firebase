package com.example.applligent.clouds;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applligent.clouds.databinding.ActivityHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
     ActivityHomeBinding binding;
     DatabaseReference databaseReference;

     List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home);
        databaseReference=FirebaseDatabase.getInstance().getReference("artist");
        artistList= new ArrayList<>();

        binding.insertRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistData : dataSnapshot.getChildren()){
                    Artist artist = artistData.getValue(Artist.class);
                   artistList.add(artist);

                }
             ArtistList adapter = new ArtistList(HomeActivity.this,artistList);
                binding.list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void add(){
        String name =binding.nameUser.getText().toString();
        String genre = binding.selectGenre.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
         String key =     databaseReference.push().getKey();
         Artist artist = new Artist(key, name ,genre);
         databaseReference.child(key).setValue(artist);
         Toast.makeText(this, name +" inserted successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"please enter the name",Toast.LENGTH_SHORT).show();
        }
    }
}
