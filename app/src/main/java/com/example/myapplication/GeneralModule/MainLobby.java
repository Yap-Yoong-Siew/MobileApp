package com.example.myapplication.GeneralModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class MainLobby extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lobby);
    }
// github comment
    public void Login(View view) {
        Intent Login = new Intent(this, LoginPage.class);
        finish();
        startActivity(Login);
    }

    public void Register(View view) {
        Intent Register = new Intent(this, MainLobbyRegistration.class);
        finish();
        startActivity(Register);
    }
}