package pl.edu.ug.brain_trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView timerTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView resultTextView;

    GridLayout gridLayout;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions;


    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        startTimer();
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");
        newQuestion();
    }

    public void startTimer(){
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Koniec czasu");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void go(View view){
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){
        if(locationOfCorrectAnswer == Integer.parseInt(view.getTag().toString())){
            resultTextView.setText("Super - dobra odpowiedź");
            score++;
        }else{
            resultTextView.setText("Błędna odpowiedź");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b) );

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for (int i =0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = random.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        gridLayout = findViewById(R.id.gridLayout);

        newQuestion();

        startTimer();
    }
}