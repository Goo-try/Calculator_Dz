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

    TextView edit_Text, solution_Text;
    MaterialButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0;
    MaterialButton button_AC, buttonOpen_Bracket, buttonClose_Bracket, button_Divide, button_Multiply, button_Minus, button_Plus, button_C, button_Dot, button_Equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edit_Text = findViewById(R.id.editText);
        solution_Text = findViewById(R.id.solutionText);


        assignId(button_1, R.id.button1);
        assignId(button_2, R.id.button2);
        assignId(button_3, R.id.button3);
        assignId(button_4, R.id.button4);
        assignId(button_5, R.id.button5);
        assignId(button_6, R.id.button6);
        assignId(button_7, R.id.button7);
        assignId(button_8, R.id.button8);
        assignId(button_9, R.id.button9);
        assignId(button_0, R.id.button0);
        assignId(button_AC, R.id.buttonAC);
        assignId(buttonOpen_Bracket, R.id.buttonOpenBracket);
        assignId(buttonClose_Bracket, R.id.buttonCloseBracket);
        assignId(button_Divide, R.id.buttonDivide);
        assignId(button_Multiply, R.id.buttonMultiply);
        assignId(button_Minus, R.id.buttonMinus);
        assignId(button_Plus, R.id.buttonPlus);
        assignId(button_C, R.id.buttonC);
        assignId(button_Dot, R.id.buttonDot);
        assignId(button_Equal, R.id.buttonEqual);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solution_Text.getText().toString();

        if(buttonText.equals("AC")){
            solution_Text.setText("0");
            return;
        }

        if(buttonText.equals("C")){
            edit_Text.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            String result = getResult(dataCalculate);
            edit_Text.setText(result);
            solution_Text.setText(result);
            return;
        }


        dataCalculate = dataCalculate+buttonText;

        solution_Text.setText(dataCalculate);

        edit_Text.setText(buttonText);


    }

    String getResult(String data) {
        Expression expression = new ExpressionBuilder(data).build();
        double result = expression.evaluate();
        return String.valueOf(result);
    }

}