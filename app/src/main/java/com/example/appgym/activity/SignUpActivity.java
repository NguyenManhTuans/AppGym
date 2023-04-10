package com.example.appgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgym.R;
import com.example.appgym.database.database;
import com.example.appgym.model.User;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    EditText inputUsername, inputEmail, inputPassword, inputConfirmPassword;
    TextView txtBackToLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnRegister = findViewById(R.id.btnRegister);
        txtBackToLogin = findViewById(R.id.txtBackToLogin);

        inputUsername = findViewById(R.id.txtInputUsername);
        inputEmail = findViewById(R.id.txtInputEmail);
        inputPassword = findViewById(R.id.txtInputPassword);
        inputConfirmPassword = findViewById(R.id.txtInputConfirmPassword);

        database db = new database(SignUpActivity.this, "appgym.sqlite", null, 1);
        ArrayList<User> users = new ArrayList<>();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString();
                String pass=inputPassword.getText().toString();
                String name=inputUsername.getText().toString();
                String pass2 = inputConfirmPassword.getText().toString();
                if(email.equals("") || pass2.equals("") || name.equals("") || pass.equals("")){
                    Toast.makeText(SignUpActivity.this, "Thông tin không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    Cursor user = db.GetData_Condition("SELECT username FROM users WHERE username = ?", new String[]{ name });
                    if(user != null && user.moveToNext()){
                        Toast.makeText(SignUpActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        user.close();
                    }else{
                        if(inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())){
                            ContentValues values = new ContentValues();
                            values.put("username", inputUsername.getText().toString());
                            values.put("email", inputEmail.getText().toString());
                            values.put("password", inputPassword.getText().toString());
                            db.insertData("users", values);
                            values.clear();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.putExtra("username",name);
                            intent.putExtra("password",pass);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        txtBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}