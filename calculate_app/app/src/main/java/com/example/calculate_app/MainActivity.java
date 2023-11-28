package com.example.calculate_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calculate_app.databinding.ActivityMainBinding;

import org.mariuszgromada.math.mxparser.*;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    TextView viewInputNumbers = binding.inputValue;

    Button buttonNumber0 = binding.buttonZero;
    Button buttonNumber1 = binding.buttonOne;
    Button buttonNumber2 = binding.buttonTwo;
    Button buttonNumber3 = binding.buttonThree;
    Button buttonNumber4 = binding.buttonFour;
    Button buttonNumber5 = binding.buttonFive;
    Button buttonNumber6 = binding.buttonSix;
    Button buttonNumber7 = binding.buttonSeven;
    Button buttonNumber8 = binding.buttonEight;
    Button buttonNumber9 = binding.buttonNine;

    Button buttonDivision = binding.buttonDivision;
    Button buttonAddition = binding.buttonAddition;
    Button buttonClear = binding.buttonClear;
    Button buttonSubtraction = binding.buttonSubtraction;
    Button buttonMultiplication = binding.buttonMultiplication;
    Button buttonEqual = binding.buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListeners();
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
        String enterNumber = viewInputNumbers.getText().toString();
        if(btnText.equals("C")){
            viewInputNumbers.setText("");
            return;
        }
        if(btnText.equals("=")) {
            viewInputNumbers.setText(handleResult(enterNumber));
            return;
        }
        else {
            enterNumber = enterNumber + btnText;
        }
        viewInputNumbers.setText(enterNumber);
    }

    public String handleResult(String data) {
        try{
                Expression expression = new Expression(data);
                String finalResult = String.valueOf(expression.calculate());
                if(finalResult.endsWith("0")) {
                     finalResult = finalResult.replace(".0", "");
                }
                return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}

