package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdvancedActivity extends AppCompatActivity implements View.OnClickListener {
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button b_c, b_divide, b_multiply, b_subtract, b_add, b_result;
    Button b_dot, b_c2, b_sign;
    Button b_percent, b_sin, b_tan, b_cos, b_x2, b_xy, b_log, b_ln, b_sqrt;
    TextView resultView, operationView;
    float num1, num2, result;
    String op;
    boolean dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.advanced);
        setupButtons();

        if (savedInstanceState != null) {
            num1 = savedInstanceState.getFloat("num1");
            num2 = savedInstanceState.getFloat("num2");
            result = savedInstanceState.getFloat("result");
            op = savedInstanceState.getString("op");
            dot = savedInstanceState.getBoolean("dot");
            resultView.setText(savedInstanceState.getString("resultView"));
            operationView.setText(savedInstanceState.getString("operationView"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat("num1", num1);
        outState.putFloat("num2", num2);
        outState.putFloat("result", result);
        outState.putString("op", op);
        outState.putBoolean("dot", dot);
        if (resultView != null && operationView != null) {
            outState.putString("resultView", resultView.getText().toString());
            outState.putString("operationView", operationView.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                if (result != 0)
                    clear_output();
                operationView.append(((Button) v).getText().toString());
                break;
            case R.id.button_add:
            case R.id.button_subtract:
            case R.id.button_multiply:
            case R.id.button_divide:
            case R.id.button_percent:
            case R.id.button_xy:
                if(num1 != 0 && !operationView.getText().toString().equals("") && !op.equals("")){
                    num2 = Float.parseFloat(operationView.getText().toString());
                    get_result();
                    operationView.setText(num1 + op + num2);
                    resultView.setText(String.valueOf(result));

                    op = "";
                    num1 = 0;
                    num2 = 0;
                    dot = false;
                }
                if (!operationView.getText().toString().equals("")) {
                    if (!resultView.getText().toString().equals("")) {
                        num1 = Float.parseFloat(resultView.getText().toString());
                        if (resultView.getText().toString().contains(".")) {
                            dot = true;
                        }
                        result = 0;
                    } else {
                        num1 = Float.parseFloat(operationView.getText().toString());
                    }
                    dot = false;
                    op = ((Button) v).getText().toString();
                    operationView.setText("");
                }
                break;
            case R.id.button_result:
                if (operationView.getText().toString().equals("") || Objects.equals(op, "")) {
                    break;
                }
                num2 = Float.parseFloat(operationView.getText().toString());

                get_result();
                if (v.getId() == R.id.button_divide && num2 == 0) {
                    break;
                } else {
                    operationView.setText(num1 + op + num2);
                    resultView.setText(String.valueOf(result));

                    op = "";
                    num1 = 0;
                    num2 = 0;
                    dot = false;
                }
                break;

            case R.id.button_sign:
                if (result != 0) {
                    break;
                }
                String operation = operationView.getText().toString();
                if (!operation.isEmpty()) {
                    float value = Float.parseFloat(operation);
                    operationView.setText(String.valueOf(-value));
                }
                break;
            case R.id.button_c:
                if (!resultView.getText().toString().equals("") && op.equals("")) {
                    operationView.setText(resultView.getText().toString());
                    resultView.setText("");
                    if (operationView.getText().toString().contains(".")) {
                        dot = true;
                    }
                    result = 0;
                }
                String txt = operationView.getText().toString();
                if (txt.length() >= 1) {
                    if (!txt.endsWith(".")) {
                        if (txt.equals("-")) {
                            num1 = 0;
                        } else if (op.equals("")) {
                            num1 = Float.parseFloat(txt);
                        } else {
                            num2 = Float.parseFloat(txt);
                        }
                    } else {
                        if (dot) {
                            dot = false;
                        }
                    }
                    operationView.setText(txt.substring(0, txt.length() - 1));
                }

                break;
            case R.id.button_c2:
                clear_output();
                break;
            case R.id.button_dot:
                if (!dot) {
                    if (operationView.getText().toString().equals("")) {
                        operationView.append("0");
                    }
                    operationView.append(".");
                    dot = true;
                }
                break;
            case R.id.button_sin:
            case R.id.button_cos:
            case R.id.button_tan:
            case R.id.button_sqrt:
            case R.id.button_ln:
            case R.id.button_log:
            case R.id.button_x2:
                if (!operationView.getText().toString().equals("")) {
                    if (!resultView.getText().toString().equals("")) {
                        num1 = Float.parseFloat(resultView.getText().toString());
                        if (resultView.getText().toString().contains(".")) {
                            dot = true;
                        }
                        result = 0;
                    } else {
                        num1 = Float.parseFloat(operationView.getText().toString());
                    }
                    dot = false;
                    op = ((Button) v).getText().toString();
                    switch (op) {
                        case "cos":
                            result = (float) Math.cos(num1);
                            break;
                        case "sin":
                            result = (float) Math.sin(num1);
                            break;
                        case "tan":
                            result = (float) Math.tan(num1);
                            break;
                        case "√":
                            if (num1 < 0) {
                                Toast.makeText(getApplicationContext(), "Cannot take the square root of a negative number", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            result = (float) Math.sqrt(num1);
                            break;
                        case "x^2":
                            result = (float) Math.pow(num1, 2);
                            break;
                        case "log":
                            if (num1 <= 0 || num1 == 1) {
                                Toast.makeText(getApplicationContext(), "Invalid number. Please try again", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            result = (float) Math.log10(num1);
                            break;
                        case "ln":
                            if (num1 <= 0 || num1 == 1) {
                                Toast.makeText(getApplicationContext(), "Invalid number. Please try again", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            result = (float) Math.log(num1);
                            break;
                    }
                    if (Objects.equals(op, "x^2")) {
                        operationView.setText(num1 + "^2");
                    } else {
                        operationView.setText(op + "(" + num1 + ")");
                    }
                    op = "";
                    resultView.setText(String.valueOf(result));
                }

                break;
            default:
                break;
        }
    }

    private void get_result() {
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot be divided by 0", Toast.LENGTH_SHORT).show();
                }
                break;
            case "x^y":
                op = "^";
                result = (float) Math.pow(num1, num2);
                break;
            case "%":
                result = (num1 / 100) * num2;
                break;

        }
    }

    private void clear_output() {
        resultView.setText("");
        operationView.setText("");
        num1 = 0;
        num2 = 0;
        result = 0;
        op = "";
        dot = false;
    }

    private void setupButtons() {
        op = "";
        dot = false;
        resultView = findViewById(R.id.resultView);
        operationView = findViewById(R.id.operationView);
        b0 = findViewById(R.id.button_0);
        b1 = findViewById(R.id.button_1);
        b2 = findViewById(R.id.button_2);
        b3 = findViewById(R.id.button_3);
        b4 = findViewById(R.id.button_4);
        b5 = findViewById(R.id.button_5);
        b6 = findViewById(R.id.button_6);
        b7 = findViewById(R.id.button_7);
        b8 = findViewById(R.id.button_8);
        b9 = findViewById(R.id.button_9);
        b_c = findViewById(R.id.button_c);
        b_c2 = findViewById(R.id.button_c2);
        b_divide = findViewById(R.id.button_divide);
        b_multiply = findViewById(R.id.button_multiply);
        b_subtract = findViewById(R.id.button_subtract);
        b_add = findViewById(R.id.button_add);
        b_result = findViewById(R.id.button_result);
        b_dot = findViewById(R.id.button_dot);
        b_sign = findViewById(R.id.button_sign);
        b_percent = findViewById(R.id.button_percent);
        b_sin = findViewById(R.id.button_sin);
        b_tan = findViewById(R.id.button_tan);
        b_cos = findViewById(R.id.button_cos);
        b_x2 = findViewById(R.id.button_x2);
        b_xy = findViewById(R.id.button_xy);
        b_log = findViewById(R.id.button_log);
        b_ln = findViewById(R.id.button_ln);
        b_sqrt = findViewById(R.id.button_sqrt);


        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b_divide.setOnClickListener(this);
        b_multiply.setOnClickListener(this);
        b_subtract.setOnClickListener(this);
        b_add.setOnClickListener(this);
        b_c.setOnClickListener(this);
        b_c2.setOnClickListener(this);
        b_result.setOnClickListener(this);
        b_dot.setOnClickListener(this);
        b_sign.setOnClickListener(this);
        b_percent.setOnClickListener(this);
        b_sin.setOnClickListener(this);
        b_tan.setOnClickListener(this);
        b_cos.setOnClickListener(this);
        b_x2.setOnClickListener(this);
        b_xy.setOnClickListener(this);
        b_log.setOnClickListener(this);
        b_ln.setOnClickListener(this);
        b_sqrt.setOnClickListener(this);
    }

}