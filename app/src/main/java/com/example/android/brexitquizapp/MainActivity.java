package com.example.android.brexitquizapp;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

//views variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //casting views


    }



    //variables globales
    int totalCorrect = 0;
    int totalWrong = 0;
    int totalNotSelected = 0;

    /**
     * Method for evaluating Question 1.
     *
     *
     */
    public void evaluateQuestion1() {
        //obtains correct answer from strings.xml
        String answer1correct = getString(R.string.question_1_answer_correct);
        //casting editText view
        EditText answer1UserView = (EditText) findViewById(R.id.question_1_answer);
        //obtains answer on editText
        //Editable answer1UserEditable = answer1UserView.getText();
        //convert Editable into string
        String answer1User = answer1UserView.getText().toString();
        //convert String into uppercase characters
        answer1User = answer1User.toUpperCase();
        //check results
        if (answer1User.equals(answer1correct)) {
            totalCorrect += 1;

            //} else  if (answer1User == "") {
            //} else  if (TextUtils.isEmpty(answer1User)) {
        } else if (answer1User.matches("")) {
            totalNotSelected += 1;
        } else {
            totalWrong += 1;
        }
    }


    private void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void comprobar(View view) {
        //recojer resultados
            evaluateQuestion1();
        //comprobar resultados
        if (totalNotSelected == 0) {
            //componer toast message
            CharSequence text = "Tienes: "+ totalCorrect + " aciertos y "
                    + totalWrong + " errores.";
            //mostrar toast message
            showToast(text);
        } else {
            CharSequence text = "Falta(n): " + totalNotSelected + " preguntas sin contestar.";
            showToast(text);
        }

        reset();
    }

    /**
     * Method for reseting test variables.
     * It is called after showing a toast.
     *
     */
    public void reset() {
        totalCorrect = 0;
        totalWrong = 0;
        totalNotSelected = 0;
    }

}
