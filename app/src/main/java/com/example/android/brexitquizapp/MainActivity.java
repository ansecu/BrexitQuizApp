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
    String q1AnswerCorrect;
    EditText q1AnswerUserEditText;

    String q3AnswerCorrect;
    RadioGroup q3RadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //casting views and getting correct answers for question 1
        q1AnswerUserEditText = (EditText) findViewById(R.id.question_1_answer);
        q1AnswerCorrect = getString(R.string.question_1_answer_correct);
        //casting views and getting correct answers for question 2

        //casting views and getting correct answers for question 3
        q3AnswerCorrect = getString(R.string.question_3_answer_correct);
        q3RadioGroup = (RadioGroup) findViewById(R.id.answer3);

    }



    //global variables
    int totalCorrect = 0;
    int totalWrong = 0;
    int totalNotSelected = 0;

    /**
     * Method for evaluating EditText questions.
     *
     *@param answerCorrect is an uppercase string obtained from res/strings;
     *@param answerUserEditText is a view where the user has entered his response.
     *
     */

    public void evaluateQuestionEditText(String answerCorrect, EditText answerUserEditText) {
        //converts Editable into string
        String answerUser = answerUserEditText.getText().toString();
        //convert String into uppercase characters
        answerUser = answerUser.toUpperCase();
        //check results
        if (answerUser.equals(answerCorrect)) {
            totalCorrect += 1;

            //} else  if (answer1User == "") {
            //} else  if (TextUtils.isEmpty(answer1User)) {
      //} else if (answerUser.matches("")) {
        } else if (answerUser.isEmpty()) {
            totalNotSelected += 1;
        } else {
            totalWrong += 1;
        }
    }

    /**
     * Method for evaluating RadioButton questions.
     *
     *@param radioGroup is the answer we are evaluating.
     *@param correctAnswer is the correct answer obtained from @strings
     *
     */

    public void evaluateQuestionRadioButton(RadioGroup radioGroup, String correctAnswer) {
        //get selected Radio Button Id
        int selectedId = radioGroup.getCheckedRadioButtonId();
        //find selected radioButton by returned Id
        RadioButton radioButtonSelected = (RadioButton) findViewById(selectedId);
        //get Text from selected RadioButton
        //String stringAnswerUser = radioButtonSelected.getText().toString();
        //Evaluate answer
        if (selectedId == -1) {
            totalNotSelected += 1;
       // else if (stringAnswerUser == correctAnswer) {
      //} else if (correctAnswer.equals(radioButtonSelected)) {
     // } else if (correctAnswer.contentEquals(radioButtonSelected.getText())) {
        } else if (correctAnswer.equals(radioButtonSelected.getText().toString())) {
            totalCorrect += 1;
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
        evaluateQuestionEditText(q1AnswerCorrect , q1AnswerUserEditText);
        evaluateQuestion2();
        //todo: transform evaluateQuestion4() and evaluateQuestion5() into evaluateRadioButton(x,y)
        //
        evaluateQuestionRadioButton(q3RadioGroup, q3AnswerCorrect);
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
