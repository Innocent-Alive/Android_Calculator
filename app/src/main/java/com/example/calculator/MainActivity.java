package com.example.calculator;
// created by Abhay Kumar Das
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String operator;
    double firstNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button zero = findViewById(R.id.zero);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);

        Button integer = findViewById(R.id.integer);
        Button point = findViewById(R.id.point);
        Button allClear = findViewById(R.id.allClear);
        Button clear = findViewById(R.id.clear);
        Button multiply = findViewById(R.id.multipy);
        Button divide = findViewById(R.id.divide);
        Button add = findViewById(R.id.add);
        Button minus = findViewById(R.id.minus);
        Button equal = findViewById(R.id.equal);
        Button percent = findViewById(R.id.percent);

        TextView screen = findViewById(R.id.screen);

        allClear.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });
        ArrayList<Button> nums = new ArrayList<>();
        nums.add(zero);
        nums.add(one);
        nums.add(two);
        nums.add(three);
        nums.add(four);
        nums.add(five);
        nums.add(six);
        nums.add(seven);
        nums.add(eight);
        nums.add(nine);
        nums.add(integer);
        for (Button b: nums){
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")){
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> operation = new ArrayList<>();
        operation.add(multiply);
        operation.add(minus);
        operation.add(add);
        operation.add(divide);
        operation.add(percent);
        for (Button b: operation){
            b.setOnClickListener(view -> {
                firstNum = Double.parseDouble(screen.getText().toString());
                operator = b.getText().toString();
                screen.setText("");
            });
        }
        clear.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length()>1){
                screen.setText(num.substring(0, num.length()-1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });
        point.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString() + ".");
            }
        });
        equal.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result;
            switch (operator){
                case "/":
                    try {
                        result = firstNum / secondNum;
                    }catch (ArithmeticException e){
                        Toast.makeText(getApplicationContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    break;
                case "x":
                    result = firstNum*secondNum;
                    break;
                case "-":
                    result = firstNum-secondNum;
                    break;
                case "+":
                    result = firstNum+secondNum;
                    break;
                case "%":
                    result = ((firstNum / 100) * secondNum);
                    break;
                default:
                    result = 0;
                    break;
            }
            screen.setText(String.valueOf(result));
            firstNum = result;
        });
    }
}