package me.dipsikha.crunchtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Arrays;
import java.util.HashMap;


public class CalorieToExcercise extends Activity {

    private Button mCalculate;

    private EditText mEdit;
    private TextView mEquivalents;
    private int mNumCalories;

    private final String[] mExcerciseDatabase = new String[] {"Select an exercise", "Push Ups",
            "Sit Ups", "Squats", "Leg Lifts", "Plank", "Jumping Jacks",
            "Pull Ups", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing"};

    private final String[] minutes = new String[] {"Jumping Jacks", "Leg Lifts", "Plank", "Cycling", "Walking",
            "Jogging", "Swimming", "Stair-Climbing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_to_excercise);

        Button mConvert = (Button) findViewById(R.id.convert);
        mConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalorieToExcercise.this, MainActivity.class));
            }
        });

        mCalculate = (Button) findViewById(R.id.calculate);
        mEquivalents = (TextView) findViewById(R.id.equivalents);
        mEdit = (EditText)findViewById(R.id.number);

        final HashMap<String, Double> CalorieConversionRate = new HashMap<String, Double>();
        CalorieConversionRate.put("Push Ups", (double) 100/350);
        CalorieConversionRate.put("Sit Ups", (double) 100/200);
        CalorieConversionRate.put("Squats", (double) 100/225);
        CalorieConversionRate.put("Leg Lifts",(double) 100/25);
        CalorieConversionRate.put("Plank", (double) 100/25);
        CalorieConversionRate.put("Jumping Jacks", (double) 100/10);
        CalorieConversionRate.put("Pull Ups", (double) 100/100);
        CalorieConversionRate.put("Cycling",(double) 100/12);
        CalorieConversionRate.put("Walking", (double) 100/20);
        CalorieConversionRate.put("Jogging", (double) 100/12);
        CalorieConversionRate.put("Swimming", (double) 100/13);
        CalorieConversionRate.put("Stair-Climbing",(double) 100/15);

        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numCalories = Integer.parseInt(mEdit.getText().toString());
                // set up equivalents text
                StringBuilder eq = new StringBuilder();
                eq.append("Burn this many calories with:\n\n");
                for (int i = 1; i < mExcerciseDatabase.length; i++) {
                        int numex = (int) (numCalories/CalorieConversionRate.get(mExcerciseDatabase[i]));
                        if (Arrays.asList(minutes).contains(mExcerciseDatabase[i])) {
                            eq.append(numex+" minutes of "+ mExcerciseDatabase[i]+"\n");
                        } else {
                            eq.append(numex+" "+ mExcerciseDatabase[i]+"\n");
                        }

                }
                mEquivalents.setText(eq.toString());
            }
        });

    }

}
