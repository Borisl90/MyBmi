package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    TextView tvBmiVal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tvBmiVal           = findViewById(R.id.txt_bmi_desc);
        Intent i           = getIntent();
        String strBmi      = Objects.requireNonNull(i.getExtras()).getString("strDecimalFormat");
        String strRate     = i.getExtras().getString("strRate");
        String strCategory = bmiCategory(strBmi);
        tvBmiVal.setText( "ציון ה - BMI הוא " + strBmi  + " נמצא בדירוג: "+ strRate +  ", דרגת סיכון לחלות במחלות: " + strCategory);
    }

    private String bmiCategory(String strBmi)
    {
        String strDescription = "";
        double dBmiValue      = Double.parseDouble(strBmi);
        if (dBmiValue < 18.5)
        {
            strDescription = "סיכון לתת תזונה";
        }
        if (dBmiValue < 29.9 && dBmiValue > 18.4) {
            strDescription = "אין סיכון";
    }
        if (dBmiValue < 29.9 && dBmiValue > 25.0) {
            strDescription = "מוגבר כאשר יש מחלות רקע נלוות";
        }
        if (dBmiValue < 34.9 && dBmiValue > 30.0) {
            strDescription = "בינוני";
        }
        if (dBmiValue < 39.9 && dBmiValue > 35.0) {
            strDescription = "חמור";
        }
        if (dBmiValue >= 40.0)
        {
            strDescription = "חמור מאוד";
        }
        return strDescription;
    }

}