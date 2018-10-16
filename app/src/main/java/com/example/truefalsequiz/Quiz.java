package com.example.truefalsequiz;

import java.util.List;

public class Quiz {

    private List<Question> questions;
    private int score;
    private int currentQuestion;

    public Quiz(List<Question> questions)
    {
        this.questions = questions;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getCurrentQuestion()
    {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion)
    {
        this.currentQuestion = currentQuestion;
    }

    public String getNextQuestion()
    {

        int i = 0;

        if(isThereAnotherQuestion())
        {
            i++;
            return questions.get(i).toString();
        }

        else
        {
            return "Out of Questions";
        }

    }

    public boolean isThereAnotherQuestion()
    {
        if(currentQuestion + 1 >= questions.size())
        {
            return false;
        }

        else
        {
            return true;
        }
    }
}
