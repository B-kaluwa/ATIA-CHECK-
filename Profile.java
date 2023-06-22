package com.example.myinterface;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myinterface.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.textInputEditText.getText().toString();
                String number = binding.textInputEditText1.getText().toString();
                String mail = binding.textInputEditText2.getText().toString();
                String add = binding.textInputEditText3.getText().toString();

                binding.textView8.setText(name);
                binding.textView9.setText(number);
                binding.textView10.setText(mail);
                binding.textView11.setText(add);
            }
        });


    }


    }
