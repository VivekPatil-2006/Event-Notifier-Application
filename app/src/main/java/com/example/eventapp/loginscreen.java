package com.example.eventapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class loginscreen extends AppCompatActivity
{
    TextView logintosignup;
    EditText login_email,login_password;
    Button login_button;
    FirebaseAuth mauth;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        logintosignup = findViewById(R.id.logintosignup);
        logintosignup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(loginscreen.this,signupscreen.class);
                startActivity(intent);
            } });
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        login_button = findViewById(R.id.login_button);
        mauth = FirebaseAuth.getInstance();
        login_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String user_email = login_email.getText().toString();
                String user_password = login_password.getText().toString();
                if(TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password))
                {
                    Toast.makeText(loginscreen.this,"Please enter the details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    mauth.signInWithEmailAndPassword(user_email,user_password).addOnSuccessListener(loginscreen.this, new OnSuccessListener<AuthResult>()
                    {
                        public void onSuccess(AuthResult authResult)
                        {
                            Toast.makeText(loginscreen.this,"Welcome",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(loginscreen.this,userloginsucess.class));
                        } });
                } }
        });
    }
}
