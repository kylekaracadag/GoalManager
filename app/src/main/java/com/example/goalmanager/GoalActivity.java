package com.example.goalmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {
    private static final String FILE_NAME = "input.txt";

    Button backBttn;
    Button saveBttn;
    EditText goalText;
    String tmpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_goal);

        backBttn = (Button) findViewById(R.id.backButton);
        saveBttn = (Button) findViewById(R.id.saveButton);
        goalText = findViewById(R.id.newGoalText);

        saveBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmpText = goalText.getText().toString();
                setText(GoalActivity.this, tmpText);
                writeFile();
//                Intent intent = new Intent(GoalActivity.this, MainActivity.class);
//                intent.putExtra("tmp", tmpText);
//                startActivity(intent);
            }
        });

        backBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalActivity.this, MainActivity.class));
            }
        });
    }

    public void writeFile() {
        String textToSave = tmpText;

        try {
            FileOutputStream fileOutputStream = openFileOutput("input1.txt", MODE_PRIVATE);
            fileOutputStream.write(textToSave.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setText(Context context, String text) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("txt", text);
        editor.commit();
    }
}

