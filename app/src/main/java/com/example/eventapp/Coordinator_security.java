package com.example.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Coordinator_security extends AppCompatActivity {

    private EditText p1, p2, p3, p4, p5, p6;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_security);

        p1 = findViewById(R.id.editTextText);
        p2 = findViewById(R.id.editTextText2);
        p3 = findViewById(R.id.editTextText3);
        p4 = findViewById(R.id.editTextText4);
        p5 = findViewById(R.id.editTextText5);
        p6 = findViewById(R.id.editTextText6);

        enterButton = findViewById(R.id.button);

        setEditTextListeners();

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e1 = p1.getText().toString();
                String e2 = p2.getText().toString();
                String e3 = p3.getText().toString();
                String e4 = p4.getText().toString();
                String e5 = p5.getText().toString();
                String e6 = p6.getText().toString();

                String enteredPassword = e1 + e2 + e3 + e4 + e5 + e6;

                if (enteredPassword.equals("197678")) {
                    // Correct password, move to the next activity
                    startActivity(new Intent(Coordinator_security.this, viaCordinator.class));
                    finish(); // Finish the current activity to prevent going back with back button
                } else {
                    // Incorrect password, show error message
                    Toast.makeText(Coordinator_security.this, "Incorrect password. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setEditTextListeners() {
        p1.addTextChangedListener(new MyTextWatcher(p2));
        p2.addTextChangedListener(new MyTextWatcher(p3));
        p3.addTextChangedListener(new MyTextWatcher(p4));
        p4.addTextChangedListener(new MyTextWatcher(p5));
        p5.addTextChangedListener(new MyTextWatcher(p6));
    }

    private class MyTextWatcher implements TextWatcher {
        private EditText nextEditText;

        MyTextWatcher(EditText nextEditText) {
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() == 1) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
