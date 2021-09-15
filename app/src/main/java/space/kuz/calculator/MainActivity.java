package space.kuz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText basicEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtonOnClickLisener();
    }

    private void initButtonOnClickLisener() {
        EditText basicEditText = (EditText) findViewById(R.id.basic_edit_text);
        Button nullButton = findViewById(R.id.null_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button pointButton = findViewById(R.id.point_button);
        Button divButton = findViewById(R.id.div_button);
        Button myltyplyButton= findViewById(R.id.multiply_button);
        Button plusButton = findViewById(R.id.plus_button);
        Button minusButton = findViewById(R.id.minus_button);
        Button equalsButton = findViewById(R.id.equals_button);
        Button leftBracketButton = findViewById(R.id.left_bracket_button);
        Button rightBracketButton = findViewById(R.id.right_bracket_button);
        Button  oneButton = findViewById(R.id.one_button);
        Button  twoButton = findViewById(R.id.two_button);
        Button  threeButton= findViewById(R.id.three_button);
        Button fourButton = findViewById(R.id.four_button);
        Button  fiveButton = findViewById(R.id.five_button);
        Button  sixButton = findViewById(R.id.six_button);
        Button  sevenButton= findViewById(R.id.seven_button);
        Button eightButton = findViewById(R.id.eight_button);
        Button nineButton = findViewById(R.id.nine_button);
        Button zeroButton = findViewById(R.id.zero_button);
        nullButton.setOnClickListener(v -> { basicEditText.setText("0");});
       deleteButton.setOnClickListener(v -> {
           if(basicEditText.length()!=0) {
               basicEditText.setText(basicEditText.getText().subSequence(0, basicEditText.getText().length()-1));
           }
       });
       pointButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+pointButton.getText()));
       divButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+divButton.getText()));
        myltyplyButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+myltyplyButton.getText()));
        plusButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+plusButton.getText()));
        minusButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+minusButton.getText()));
        equalsButton.setOnClickListener(v -> {});
        leftBracketButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+leftBracketButton.getText()));
        rightBracketButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+rightBracketButton.getText()));
        oneButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+oneButton.getText()));
        twoButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+twoButton.getText()));
        threeButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+threeButton.getText()));
        fourButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+fourButton.getText()));
        fiveButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+fiveButton.getText()));
        sixButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+sixButton.getText()));
        sevenButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+sevenButton.getText()));
        eightButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+eightButton.getText()));
        nineButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+nineButton.getText()));
        zeroButton.setOnClickListener(v -> basicEditText.setText(basicEditText.getText()+""+zeroButton.getText()));









    }
}