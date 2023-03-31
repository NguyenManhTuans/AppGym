package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgym.activity.LoginActivity;
import com.example.appgym.activity.SignUpActivity;
import com.example.appgym.database.database;
import com.example.appgym.model.User;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView inputProfileName, inputHeight, inputWeight;
    Button btnSaveUser;
    database db;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getView();
        String name1 = getIntent().getStringExtra("username");
        inputProfileName.setText(name1);
        db = new database(UserProfileActivity.this, "appgym.sqlite", null, 1);
        users = new ArrayList<>();
        getData();
        String height=Double.toString (users.get(0).getHeight()) ;
        String weight=Double.toString (users.get(0).getWeight()) ;
        inputHeight.setText(height+" cm");
        inputWeight.setText(weight+" KG");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height=inputHeight.getText().toString();
                String weight=inputWeight.getText().toString();
                if(height.equals("")||weight.equals("")){
                    Toast.makeText(UserProfileActivity.this, "Không dc de trong", Toast.LENGTH_SHORT).show();
                }
                else{
                    ContentValues user=new ContentValues();
                    user.put("height",height);
                    user.put("weight",weight);
                    db.updateData("users",user,"username=?",new String[]{name1});
                    Toast.makeText(UserProfileActivity.this, "Thanh COng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getData(){
        String name1 = getIntent().getStringExtra("username");
        Cursor dataUser = db.GetData_Condition("SELECT * FROM users Where username=?",new String[]{name1});
        users.clear();
        while(dataUser.moveToNext()){
           // int id=dataUser.getInt(0);
            String usernameLogin=dataUser.getString(0);
            String passwordLogin=dataUser.getString(2);
            String emailLogin = dataUser.getString(1);
            double height=dataUser.getDouble(3);
            double weight=dataUser.getDouble(4);
            users.add(new User(usernameLogin, emailLogin, passwordLogin,height,weight));
        }
        dataUser.close();
    }




    public void getView(){
        btnBack=findViewById(R.id.backButton);
        inputProfileName = findViewById(R.id.inputProfileName);
        inputHeight= findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.inputWeight);
        btnSaveUser = findViewById(R.id.btnSaveUser);
    }
}