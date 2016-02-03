package me.dipsikha.crunchtime;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends Activity {
    private Button mCalculate;
    private TextView mCalories;
    private TextView mReps;
    private EditText mEdit;
    private String mExcercise;
    private TextView mEquivalents;
    private int mNumReps = 0;

    private final String[] mExcerciseDatabase = new String[] {"Select an exercise", "Push Ups",
            "Sit Ups", "Squats", "Leg Lifts", "Plank", "Jumping Jacks",
            "Pull Ups", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing"};

    private final String[] minutes = new String[] {"Jumping Jacks", "Leg Lifts", "Plank", "Cycling", "Walking",
            "Jogging", "Swimming", "Stair-Climbing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.excercise, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String item = spinner.getSelectedItem().toString();
                if (Arrays.asList(minutes).contains(item)) {
                    mReps.setText("Minutes");
                } else if (item.equals("Select an exercise")) {
                    mReps.setText("");
                } else {
                    mReps.setText("Reps");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCalculate = (Button) findViewById(R.id.calculate);
        mReps = (TextView) findViewById(R.id.reps);
        mEquivalents = (TextView) findViewById(R.id.equivalents);
        mCalories =  (TextView) findViewById(R.id.calories);
        mEdit = (EditText)findViewById(R.id.number);
        Button mConvert = (Button) findViewById(R.id.convert);
        mConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalorieToExcercise.class));
            }
        });




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
                mExcercise = spinner.getSelectedItem().toString();
                if (!mExcercise.equals("Select an exercise")) {
                    mNumReps = Integer.parseInt(mEdit.getText().toString());
                    Double totalCalories = (mNumReps*CalorieConversionRate.get(mExcercise));
                    mCalories.setText(totalCalories.intValue()+ " calories burned!");
                    // set up equivalents text
                    StringBuilder eq = new StringBuilder();
                    eq.append("You could also burn the same number of calories by doing:\n\n");
                    for (int i = 1; i < mExcerciseDatabase.length; i++) {
                        if (!mExcerciseDatabase[i].equals(mExcercise)) {
                            int numex = (int) (totalCalories/CalorieConversionRate.get(mExcerciseDatabase[i]));
                            if (Arrays.asList(minutes).contains(mExcerciseDatabase[i])) {
                                eq.append(numex+" minutes of "+ mExcerciseDatabase[i]+"\n");
                            } else {
                                eq.append(numex+" "+ mExcerciseDatabase[i]+"\n");
                            }
                        }
                    }
                    mEquivalents.setText(eq.toString());

                }


            }
        });

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            if (Arrays.asList(minutes).contains(parent.getItemAtPosition(pos).toString())) {
                mReps.setText("Minutes");
            }
            Log.v("Tag", "selected");
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
