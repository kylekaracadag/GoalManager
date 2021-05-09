package com.example.goalmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Goal> list;
    private RecyclerView recyclerView;
    private GoalActivity goalActivity;
    GoalAdapter goalAdapter;
    Button goalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.goalsList);
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String text = getText(MainActivity.this);
        readFile();
//        list.add(new Goal(text));

//        list.add(new Goal("Test One"));
//        list.add(new Goal("Test Two"));
//        list.add(new Goal("Test Three"));
//        list.add(new Goal("Test Four"));

        goalAdapter = new GoalAdapter(MainActivity.this, list);
        recyclerView.setAdapter(goalAdapter);

        goalButton = (Button) findViewById(R.id.addGoalButton);
        goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });
    }

    public void readFile() {
        try {
            FileInputStream fileInputStream = openFileInput("input1.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
                list.add(new Goal(stringBuffer.toString()));
            }

//            list.add(new Goal(stringBuffer.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getText(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("txt", "");
    }
}