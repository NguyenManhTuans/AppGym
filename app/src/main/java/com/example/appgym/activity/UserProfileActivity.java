package com.example.appgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgym.R;
import com.example.appgym.database.database;
import com.example.appgym.model.User;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView inputProfileName, inputHeight, inputWeight, BmiValue, BmiStatus;
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
        Update(height,weight);
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
                    finish();
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

    public void Update(String height,String weight){
        if(height.equals("0.0")||weight.equals("0.0")||height.equals("0")||weight.equals("0")){
            inputHeight.setText("");
            inputWeight.setText("");
            BmiStatus.setText("");
            BmiValue.setText("");
        }
        else{
            inputHeight.setText(height);
            inputWeight.setText(weight);
            Double h =users.get(0).getHeight()/100;
            Double w =users.get(0).getWeight();

            Double BmiCALC = ((double)Math.round((w/(h*h))*1000.0) / 1000.0) ;

            BmiValue.setText(BmiCALC.toString());
            if(BmiCALC<18.5){
                BmiStatus.setText("Gầy");
            } else if(BmiCALC>=18.5 && BmiCALC<=24.9) {
                BmiStatus.setText("Bình thường");
            } else if (BmiCALC>=25.0 && BmiCALC<=29.9) {
                BmiStatus.setText("Hơi béo");
            } else {
                BmiStatus.setText("Rất béo");
            }
        }
    }



    public void getView(){
        btnBack=findViewById(R.id.backButton);
        inputProfileName = findViewById(R.id.inputProfileName);
        inputHeight= findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.inputWeight);
        btnSaveUser = findViewById(R.id.btnSaveUser);
        BmiValue = findViewById(R.id.txtBmiValue);
        BmiStatus=findViewById(R.id.txtBmiStatus);
    }
}