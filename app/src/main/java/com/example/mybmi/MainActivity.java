package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.text.DecimalFormat;

public class MainActivity<decimal> extends AppCompatActivity implements View.OnClickListener {

    Button button_calc;
    TextView display, strRate;
    double p_bmiResualt = 0.0;
    EditText etWeight, etHeight;
        String strDecimalFormat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_calc = findViewById(R.id.btn_calc_bmi);
        display     = findViewById(R.id.txt_bmi_result);
        button_calc.setOnClickListener(this);
        etHeight    = findViewById(R.id.txt_height);
        etWeight    = findViewById(R.id.txt_weight);
        strRate     = findViewById(R.id.txt_bmi_rate);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if(v == button_calc)
        {
            if(validateValues(etHeight.getText().toString(), etWeight.getText().toString()))
            {
                double dHeight    = Double.parseDouble(etHeight.getText().toString());
                double dWeight    = Double.parseDouble(etWeight.getText().toString());
                p_bmiResualt      = dWeight/(dHeight*dHeight);
                DecimalFormat df  = new DecimalFormat("#.##");
                strDecimalFormat  = df.format(p_bmiResualt);
                String strRateRes = bmiStringRate(strDecimalFormat);
                display.setText(strDecimalFormat);
                strRate.setText(strRateRes);
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("strDecimalFormat", strDecimalFormat);
                i.putExtra("strRate", strRateRes);
                startActivity(i);
            }
        }
    }

    private boolean validateValues(String strHeight, String strWeight) {
        boolean bOk = false;
        if((strHeight.isEmpty() || strWeight.isEmpty()))
        {
            try {
                throw new Exception("נא למלא את כל השדות (גובה ומשקל)");
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        if((strHeight.equals(".") || strWeight.equals(".")))
        {
            try {
                throw new Exception("נא למלא קלט תקין, רק תו נקודה לא קלט תקין");
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        int nCountDot = strHeight.length() - strHeight.replace(".", "").length();
        if(nCountDot > 1)
        {
            try {
                throw new Exception("נא למלא קלט תקין, לא יכול להיות יותר מנקודה 1 בשדה גובה");
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        nCountDot = strHeight.length() - strWeight.replace(".", "").length();
        if(nCountDot > 1)
        {
            try {
                throw new Exception("נא למלא קלט תקין, לא יכול להיות יותר מנקודה 1 בשדה משקל");
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        double dHeight = Double.parseDouble(strHeight);
        double dWeight = Double.parseDouble(strWeight);
        if (dHeight < 1)
        {
            try
            {
                throw new Exception("הגובה לא יכול להיות קטן מ - 1");
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        if (dWeight < 1)
        {
            try
            {
                throw new Exception("המשקל לא יכול להיות קטן מ - 1");
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        if (!strHeight.matches("^([0-9]+\\.([0-9]+)?)"))
        {
            try
            {
                throw new Exception("הגובה חייב להיות במטרים (חייב להיות התו '.')");
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                return bOk;
            }
        }
        bOk = true;
        return  bOk;
    }

    private String bmiStringRate(String strBmi) {
        String strRate   = "";
        double dBmiValue = Double.parseDouble(strBmi);
        if (dBmiValue < 18.5) {
            strRate = "תת משקל";
        }
        if (dBmiValue < 29.9 && dBmiValue > 18.4) {
            strRate = "משקל תקין";
        }
        if (dBmiValue < 29.9 && dBmiValue > 25.0) {
            strRate = "משקל עודף";
        }
        if (dBmiValue < 34.9 && dBmiValue > 30.0) {
            strRate = "השמנה דרגה I";
        }
        if (dBmiValue < 39.9 && dBmiValue > 35.0) {
            strRate = "השמנה דרגה II";
        }
        if (dBmiValue >= 40.0) {
            strRate = "השמנה דרגה III";
        }
        return strRate;
    }

}