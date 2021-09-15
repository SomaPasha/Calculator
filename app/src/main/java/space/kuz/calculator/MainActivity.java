package space.kuz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText basicEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtonOnClickListener();
    }

    private void initButtonOnClickListener() {
        basicEditText = (EditText) findViewById(R.id.basic_edit_text);

        Button nullButton = findViewById(R.id.null_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button pointButton = findViewById(R.id.point_button);
        Button divButton = findViewById(R.id.div_button);
        Button multiplyButton = findViewById(R.id.multiply_button);
        Button plusButton = findViewById(R.id.plus_button);
        Button minusButton = findViewById(R.id.minus_button);
        Button equalsButton = findViewById(R.id.equals_button);
        Button leftBracketButton = findViewById(R.id.left_bracket_button);
        Button rightBracketButton = findViewById(R.id.right_bracket_button);
        Button oneButton = findViewById(R.id.one_button);
        Button twoButton = findViewById(R.id.two_button);
        Button threeButton = findViewById(R.id.three_button);
        Button fourButton = findViewById(R.id.four_button);
        Button fiveButton = findViewById(R.id.five_button);
        Button sixButton = findViewById(R.id.six_button);
        Button sevenButton = findViewById(R.id.seven_button);
        Button eightButton = findViewById(R.id.eight_button);
        Button nineButton = findViewById(R.id.nine_button);
        Button zeroButton = findViewById(R.id.zero_button);

        nullButton.setOnClickListener(v -> {
            basicEditText.setText("");
        });
        deleteButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0) {
                basicEditText.setText(basicEditText.getText().subSequence(0, basicEditText.getText().length() - 1));
            }
        });
        pointButton.setOnClickListener(v -> {
            if (basicEditText.getText().toString().indexOf(",") == -1) {
                inputNumber(pointButton);
            }
        });
        divButton.setOnClickListener(v -> inputNumber(divButton));
        multiplyButton.setOnClickListener(v -> inputNumber(multiplyButton));
        plusButton.setOnClickListener(v -> inputNumber(plusButton));
        minusButton.setOnClickListener(v -> inputNumber(minusButton));
        equalsButton.setOnClickListener(v -> {
        });
        leftBracketButton.setOnClickListener(v -> inputNumber(leftBracketButton));
        rightBracketButton.setOnClickListener(v -> inputNumber(rightBracketButton));
        oneButton.setOnClickListener(v -> inputNumber(oneButton));
        twoButton.setOnClickListener(v -> inputNumber(twoButton));
        threeButton.setOnClickListener(v -> inputNumber(threeButton));
        fourButton.setOnClickListener(v -> inputNumber(fourButton));
        fiveButton.setOnClickListener(v -> inputNumber(fiveButton));
        sixButton.setOnClickListener(v -> inputNumber(sixButton));
        sevenButton.setOnClickListener(v -> inputNumber(sevenButton));
        eightButton.setOnClickListener(v -> inputNumber(eightButton));
        nineButton.setOnClickListener(v -> inputNumber(nineButton));
        zeroButton.setOnClickListener(v -> inputNumber(zeroButton));


    }

    private void inputNumber(Button button) {
        basicEditText.setText(basicEditText.getText().toString() + button.getText());
    }
}