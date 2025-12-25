package com.example.calculator_dz;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultText, solutionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result);
        solutionText = findViewById(R.id.solutionText);


        assignId(R.id.button1);
        assignId(R.id.button2);
        assignId(R.id.button3);
        assignId(R.id.button4);
        assignId(R.id.button5);
        assignId(R.id.button6);
        assignId(R.id.button7);
        assignId(R.id.button8);
        assignId(R.id.button9);
        assignId(R.id.button0);
        assignId(R.id.buttonAC);
        assignId(R.id.buttonOpenBracket);
        assignId(R.id.buttonCloseBracket);
        assignId(R.id.buttonDivide);
        assignId(R.id.buttonMultiply);
        assignId(R.id.buttonMinus);
        assignId(R.id.buttonPlus);
        assignId(R.id.buttonC);
        assignId(R.id.buttonDot);
        assignId(R.id.buttonEqual);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }

    void assignId(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solutionText.getText().toString();

        if(buttonText.equals("AC")){
            solutionText.setText("");
            return;
        }

        if(buttonText.equals("C")){
            resultText.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            String result = getResult(dataCalculate);
            resultText.setText(result);
            return;
        }


        dataCalculate = dataCalculate+buttonText;

        solutionText.setText(dataCalculate);

        resultText.setText(buttonText);

    }

    String getResult(String data) {
        try {
            Expression expression = new ExpressionBuilder(data).build();
            double result = expression.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

}