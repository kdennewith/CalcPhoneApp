package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultText, inputText;

    MaterialButton buttonC, buttonOpenBracket, buttonClosedBracket;

    MaterialButton button0, button1, button2, button3, button4, button5, button6,button7, button8, button9;

    MaterialButton buttonMul, buttonPlus, buttonMinus, buttonDivide, buttonEquals;

    MaterialButton buttonAC, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultText = findViewById(R.id.resultText);
        inputText = findViewById(R.id.inputText);

        assignID(buttonC,R.id.button_C);
        assignID(buttonOpenBracket,R.id.button_OpenParenthesis);
        assignID(buttonClosedBracket,R.id.button_ClosedParenthesis);
        assignID(buttonDivide,R.id.button_ForwardSlash);
        assignID(button9,R.id.button_9);
        assignID(button8,R.id.button_8);
        assignID(button7,R.id.button_7);
        assignID(button6,R.id.button_6);
        assignID(button5,R.id.button_5);
        assignID(button4,R.id.button_4);
        assignID(button3,R.id.button_3);
        assignID(button2,R.id.button_2);
        assignID(button1,R.id.button_1);
        assignID(buttonPlus,R.id.button_Plus);
        assignID(buttonMinus,R.id.button_Minus);
        assignID(buttonMul,R.id.button_Multiply);
        assignID(buttonAC,R.id.button_AC);
        assignID(button0,R.id.button_0);
        assignID(buttonDot,R.id.button_Dot);
        assignID(buttonEquals,R.id.button_Equals);

    }

    /**
     *
     * @param btn A button that is assigned to an id from your activity_main.
     * @param id An id that is attached to the btn button to set correct function for on-click.
     */
    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    /**
     *
     * @param view A "view" such as a button that is clicked.
     */
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculation = resultText.getText().toString();

        if(buttonText.equals("AC")){
            resultText.setText("");
            inputText.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            inputText.setText(getResults(dataCalculation));
            return;
        }

        if(buttonText.equals("C") && dataCalculation.length() > 1) {
            dataCalculation = dataCalculation.substring(0, dataCalculation.length() - 1);
        }else{
            if(!buttonText.equals("C")) {
                dataCalculation = dataCalculation + buttonText;
            }
        }

        resultText.setText(dataCalculation);
        String finalResult = getResults(dataCalculation);

    }

    /**
     *
     * @param data Incoming data from the calculator to be used for evaluation using context.evaluateString
     * @return an error message or evaluation of data.
     */
    String getResults(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable,data,"Javascript",1,null).toString();
        }catch(Exception e){
            return "Error. . .";
        }
    }


}