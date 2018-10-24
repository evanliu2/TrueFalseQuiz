package com.example.truefalsequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private Button buttonTrue;
    private Button buttonFalse;
    private TextView textViewScore;
    private TextView textViewQuestion;
    private List<Question> questionList;
    private Quiz quiz;

    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);
        String sxml = readTextFile(XmlFileInputStream);

        // create a gson object
        Gson gson = new Gson();
// read your json file into an array of questions
        Question[] questions =  gson.fromJson(sxml, Question[].class);
// convert your array to a list using the Arrays utility class
        questionList = Arrays.asList(questions);
// verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        quiz = new Quiz(questionList);

        wireWidgets();
        setListeners();
        initializeQuiz(quiz);

    }

    private void initializeQuiz(Quiz quiz){
        if(quiz.getCurrentQuestionNum() == 5)
        {
            Intent finalScore = new Intent(MainActivity.this, MainActivity.class);
            finalScore.putExtra(EXTRA_MESSAGE, quiz.getScore());
            startActivity(finalScore);
        }
        else
        {
            textViewQuestion.setText(quiz.getCurrentQuestion().getQuestion());
        }
    }

    private void updateQuiz(Quiz quiz){
        textViewScore.setText("Score:" + quiz.getScore());
        quiz.setCurrentQuestion(quiz.getCurrentQuestionNum() + 1);
        initializeQuiz(quiz);
    }

    private String readTextFile(InputStream inputStream) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {

            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);

            }

            outputStream.close();
            inputStream.close();

        }

        catch (IOException e) {

        }

        return outputStream.toString();

    }

    private void wireWidgets(){
        buttonTrue = findViewById(R.id.button_truefalse_true);
        buttonFalse = findViewById(R.id.button_truefalse_false);
        textViewQuestion = findViewById(R.id.textView_truefalse_question);
        textViewScore = findViewById(R.id.textView_truefalse_score);

    }

    private void setListeners(){
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quiz.getCurrentQuestion().getAnswer() == true)
                {
                    quiz.setScore(quiz.getScore() + 1);
                }

                else
                {
                    quiz.setScore(quiz.getScore() - 1);
                }

                updateQuiz(quiz);

            }

        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quiz.getCurrentQuestion().getAnswer() == false)
                {
                    quiz.setScore(quiz.getScore() + 1);
                }

                else
                {
                    quiz.setScore(quiz.getScore() - 1);
                }

                updateQuiz(quiz);

            }
        });
    }

    }


