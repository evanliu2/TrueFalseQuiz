package com.example.truefalsequiz;

public class Question {

    private String question;
    private boolean answer;

    public Question(String inputQuestion, boolean inputAnswer)
    {
        this.question = inputQuestion;
        this.answer = inputAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
