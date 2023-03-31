package com.example.appgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgym.ListviewtestActivity;
import com.example.appgym.R;
import com.example.appgym.database.database;
import com.example.appgym.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsernameLogin, inputPasswordLogin;
    private String username, password;
    Button btnLogin;

    database db;

    ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
        db = new database(LoginActivity.this, "appgym.sqlite", null, 1);
        inputUsernameLogin = findViewById(R.id.txtUsername);
        inputPasswordLogin = findViewById(R.id.txtPassword);


        db.QueryData("CREATE TABLE IF NOT EXISTS users (username VARCHAR(40) PRIMARY KEY , email VARCHAR(40), password VARCHAR(40),height double, weight double )");
        String name,pass;

        name=getIntent().getStringExtra("username");
        pass=getIntent().getStringExtra("password");
        inputUsernameLogin.setText(name);
        inputPasswordLogin.setText(pass);
        getData();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = inputUsernameLogin.getText().toString();
                String pass = inputPasswordLogin.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Tên tài khoản và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    boolean trangthai = false;
                    for (int i = 0; i < users.size(); i++){
                        if(name.equals(users.get(i).getName()) && pass.equals(users.get(i).getPassword())){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            trangthai = true;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username",name);
                            startActivity(intent);
                            break;
                        }
                    }
                    if (!trangthai){
                        Toast.makeText(LoginActivity.this, "Sai Tên hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getData(){
        Cursor dataUser = db.GetData("SELECT * FROM users");
        users.clear();
        while(dataUser.moveToNext()){
            int id=dataUser.getInt(0);
            String usernameLogin=dataUser.getString(0);
            String passwordLogin=dataUser.getString(2);
            String emailLogin = dataUser.getString(1);
            users.add(new User(usernameLogin, emailLogin, passwordLogin));

        }
        dataUser.close();
    }
}
