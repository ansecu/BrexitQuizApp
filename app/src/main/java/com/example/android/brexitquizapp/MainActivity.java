package com.example.android.brexitquizapp;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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



    //global variables
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

    public void evaluateQuestion2() {
        //casting views
        CheckBox q2a1checkbox = (CheckBox) findViewById(R.id.q2_a1_checkbox);
        CheckBox q2a2checkbox = (CheckBox) findViewById(R.id.q2_a2_checkbox);
        CheckBox q2a3checkbox = (CheckBox) findViewById(R.id.q2_a3_checkbox);
        //view results
        if (q2a2checkbox.isChecked() && q2a3checkbox.isChecked()) {
            totalCorrect +=1;
        } else if (!(q2a1checkbox.isChecked() || q2a2checkbox.isChecked() ||
                q2a3checkbox.isChecked())) {
            totalNotSelected +=1;
        } else {
            totalWrong +=1;
        }
    }

    public void evaluateQuestion3() {
        //casting views
        RadioButton q3a1RadioButton = (RadioButton) findViewById(R.id.q3_a1_RadioButton);
        RadioButton q3a2RadioButton = (RadioButton) findViewById(R.id.q3_a2_RadioButton);
        RadioButton q3a3RadioButton = (RadioButton) findViewById(R.id.q3_a3_RadioButton);
        //check answer
        if (q3a1RadioButton.isChecked() || q3a3RadioButton.isChecked()) {
            totalWrong +=1;
        } else if (q3a2RadioButton.isChecked()) {
            totalCorrect +=1;
        } else {
            totalNotSelected +=1;
        }
    }

    public void evaluateQuestion4() {
        //casting views
        RadioButton q4a1RadioButton = (RadioButton) findViewById(R.id.q4_a1_RadioButton);
        RadioButton q4a2RadioButton = (RadioButton) findViewById(R.id.q4_a2_RadioButton);
        RadioButton q4a3RadioButton = (RadioButton) findViewById(R.id.q4_a3_RadioButton);
        //check answer
        if (q4a1RadioButton.isChecked() || q4a2RadioButton.isChecked()) {
            totalWrong +=1;
        } else if (q4a3RadioButton.isChecked()) {
            totalCorrect +=1;
        } else {
            totalNotSelected +=1;
        }
    }

    public void evaluateQuestion5() {
        //casting views
        RadioButton q5a1RadioButton = (RadioButton) findViewById(R.id.q5_a1_RadioButton);
        RadioButton q5a2RadioButton = (RadioButton) findViewById(R.id.q5_a2_RadioButton);
        RadioButton q5a3RadioButton = (RadioButton) findViewById(R.id.q5_a3_RadioButton);
        //check answer
        if (q5a1RadioButton.isChecked() || q5a3RadioButton.isChecked()) {
            totalWrong +=1;
        } else if (q5a2RadioButton.isChecked()) {
            totalCorrect +=1;
        } else {
            totalNotSelected +=1;
        }
    }

    private void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * this method is called when pushing "comprobar" button
     *
     */
    public void comprobar(View view) {
        //recojer resultados
            evaluateQuestion1();
            evaluateQuestion2();
            evaluateQuestion3();
            evaluateQuestion4();
            evaluateQuestion5();
        //comprobar resultados
        if (totalNotSelected == 0) {
            //componer toast message
            CharSequence text = "Tienes: "+ totalCorrect + " acierto(s) y "
                    + totalWrong + " error(es).";
            //mostrar toast message
            showToast(text);
        } else {
            CharSequence text = "Falta(n): " + totalNotSelected + " pregunta(s) por contestar.";
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
