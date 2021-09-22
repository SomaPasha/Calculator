package space.kuz.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static space.kuz.calculator.CalculatingEquality.EMPTY;

public class MainActivity extends AppCompatActivity {
    private EditText basicEditText;
    private boolean checkPoint = true;  // флаг для правильного набора дробных чисел
    CalculatingEquality calculatingEquality;
    public static final String POINT = ".";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatingEquality = new CalculatingEquality();
        initButtonOnClickListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_setting:
                    Intent intent = new Intent(this, SettingActivity.class);
                    startActivity(intent);
                    break;
                default:
                    super.onOptionsItemSelected(item);
            }
            return true;
    }

    // Иницилизация всех кнопок и обработка их
    private void initButtonOnClickListener() {
        basicEditText = (EditText) findViewById(R.id.basic_edit_text);
        basicEditText.setText(EMPTY);
        basicEditText.setMovementMethod(new ScrollingMovementMethod());
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
            basicEditText.setText(EMPTY);
            checkPoint = true;
        });

        deleteButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0) { // чтобы при нажатии не вылетала
                if (basicEditText.getText().subSequence(basicEditText.getText().length() - 1, basicEditText.getText().length()).toString().equals(POINT)) {
                    checkPoint = true;
                }
                basicEditText.setText(basicEditText.getText().subSequence(0, basicEditText.getText().length() - 1));
            }

        });

        pointButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkPoint && checkSing()
                    && !isolationChar().equals(CalculatingEquality.LEFTSIGN)
                    && !isolationChar().equals(CalculatingEquality.RIGHTSING)
            ) {
                inputNumber(pointButton);
                checkPoint = false;
            }
        });

        divButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(divButton);
            }
            checkPoint = true;
        });

        multiplyButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(multiplyButton);
            }
            checkPoint = true;
        });

        plusButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(plusButton);
            }
            checkPoint = true;
        });

        minusButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0) {
                if (checkSing()) {
                    inputNumber(minusButton);
                }
            } else {
                inputNumber(minusButton);
            }
            checkPoint = true;
        });

        equalsButton.setOnClickListener(v -> {
            calculatingEquality.convertFormula(basicEditText);
            try {
                calculatingEquality.calculateFormulaHard(basicEditText);
            } catch (Exception e) {
                Toast.makeText(this, "ОШИБКА!!! Введите заново", Toast.LENGTH_LONG).show();
            }
            checkPoint = true;
        });

        leftBracketButton.setOnClickListener(v -> {
            if (basicEditText.length() == 0) {
                inputNumber(leftBracketButton);
            } else if (isNumber(isolationChar()) && !isolationChar().equals(POINT)) {
                inputNumber(leftBracketButton);
            }
            checkPoint = true;
        });
        rightBracketButton.setOnClickListener(v -> {
            if (basicEditText.length() == 0) {
                inputNumber(rightBracketButton);
            } else if (isNumber(isolationChar()) && !isolationChar().equals(POINT)) {
                inputNumber(rightBracketButton);
            }
            checkPoint = true;
        });
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

    // Метод ввода значения в Edit
    private void inputNumber(Button button) {
        basicEditText.setText(basicEditText.getText().toString() + button.getText());
    }

    // Метод проверки находиться рядом + - / * .
    private boolean checkSing() {
        return !isolationChar().equals(CalculatingEquality.PLUS) &&
                !isolationChar().equals(CalculatingEquality.MINUS) &&
                !isolationChar().equals(CalculatingEquality.MULTIPLY) &&
                !isolationChar().equals(CalculatingEquality.DIV) &&
                !isolationChar().equals(POINT);
    }

    // Проверка является строка числом
    private static boolean isNumber(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String isolationChar() {
        return basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length());
    }


}