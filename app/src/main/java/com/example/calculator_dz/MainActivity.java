package com.example.calculator_dz;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultText, solutionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result);
        solutionText = findViewById(R.id.solutionText);

        if (savedInstanceState != null) {
            String savedSolution = savedInstanceState.getString("solution", "");
            String savedResult = savedInstanceState.getString("result", "0");
            solutionText.setText(savedSolution);
            resultText.setText(savedResult);
        }

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("solution", solutionText.getText().toString());
        outState.putString("result", resultText.getText().toString());
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
            if (result.equals("DIV_BY_ZERO")) {
                showDivisionError();
            } else {
                resultText.setText(result);
            }
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
            if (e instanceof ArithmeticException) {
                return "DIV_BY_ZERO";
            } else {
                return "Error";
            }
        }
    }


    private void showDivisionError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.division_error_title));
        builder.setMessage(getString(R.string.division_error_message));
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}
