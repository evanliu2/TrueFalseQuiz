package com.example.truefalsequiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
    private TextView textViewquestion;
    private List<Question> questions;

    public static final String TAG = "MainActivity";

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
        List<Question> questionList = Arrays.asList(questions);
// verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        Quiz quiz = new Quiz(questionList);

        wireWidgets();

    }

    public String readTextFile(InputStream inputStream) {

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

    private void wireWidgets()
    {
        buttonTrue = findViewById(R.id.button_truefalse_true);
        buttonFalse = findViewById(R.id.button_truefalse_false);
        textViewquestion = findViewById(R.id.textView_truefalse_question);
        textViewScore = findViewById(R.id.textView_truefalse_score);

    }



}

