package com.example.calculate_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewInputNumbers;
    Button buttonNumber7, buttonNumber8, buttonNumber9, buttonDivision;
    Button buttonNumber4, buttonNumber5, buttonNumber6, buttonMultiplication;
    Button buttonNumber0, buttonClear, buttonAddition, buttonEqual;
    Button buttonNumber1, buttonNumber2, buttonNumber3, buttonSubtraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewInputNumbers = findViewById(R.id.input_value);
        assignID();
        setOnClickListeners();
    }

    private void assignID() {
        buttonNumber0 = findViewById(R.id.button_zero);
        buttonNumber1 = findViewById(R.id.button_one);
        buttonNumber2 = findViewById(R.id.button_two);
        buttonNumber3 = findViewById(R.id.button_three);
        buttonNumber4 = findViewById(R.id.button_four);
        buttonNumber5 = findViewById(R.id.button_five);
        buttonNumber6 = findViewById(R.id.button_six);
        buttonNumber7 = findViewById(R.id.button_seven);
        buttonNumber8 = findViewById(R.id.button_eight);
        buttonNumber9 = findViewById(R.id.button_nine);
        buttonClear = findViewById(R.id.button_clear);
        buttonDivision = findViewById(R.id.button_division);
        buttonMultiplication =  findViewById(R.id.button_multiplication);
        buttonSubtraction = findViewById(R.id.button_subtraction);
        buttonAddition =  findViewById(R.id.button_addition);
        buttonEqual = findViewById(R.id.button_equal);
    }

    public void setOnClickListeners() {
        buttonNumber0.setOnClickListener(this);
        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();
        String numberCal = textViewInputNumbers.getText().toString();
        String finalResult = handleResult(numberCal);
        if(btnText.equals("C")){
            textViewInputNumbers.setText("");
            return;
        }
        if(btnText.equals("=")) {
            textViewInputNumbers.setText(finalResult);
            return;
        }
        else {
            numberCal = numberCal + btnText;
        }
        textViewInputNumbers.setText(numberCal);
    }
    public String handleResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "JavScript", 1, null).toString();
            if(finalResult.endsWith("0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}

