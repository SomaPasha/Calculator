package space.kuz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> textSing;
    ArrayList<String> subTextSing;
    private EditText basicEditText;
    private Float result;
    private int indexCalculate =0;
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIV = "÷";
    private static final String POINT = ".";
    private static final String LEFTSIGN = "(";
    private static final String RIGHTSING = ")";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtonOnClickListener();
    }

    private void initButtonOnClickListener() {
        basicEditText = (EditText) findViewById(R.id.basic_edit_text);
        basicEditText.setText("");
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
            if (basicEditText.length() != 0) { // чтобы при нажатии не вылетала
                basicEditText.setText(basicEditText.getText().subSequence(0, basicEditText.getText().length() - 1));
            }
        });

        pointButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && basicEditText.getText().toString().indexOf(POINT) == -1 && checkSing()
            &&  !basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length()).equals(LEFTSIGN)
            && !basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length()).equals(RIGHTSING)
            ) {
                inputNumber(pointButton);
            }
        });

        divButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(divButton);
            }
        });

        multiplyButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(multiplyButton);
            }
        });

        plusButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0 && checkSing()) {
                inputNumber(plusButton);
            }
        });

        minusButton.setOnClickListener(v -> {
            if (basicEditText.length() != 0) {
                if (checkSing()) {
                    inputNumber(minusButton);
                }
            } else {
                inputNumber(minusButton);
            }
        });

        equalsButton.setOnClickListener(v -> {
            convertFormula();
            try {
                calculateFormulaHard();
            } catch (Exception e){
                Toast.makeText(this,"ОШИБКА!!! Введите заново",Toast.LENGTH_LONG).show();
            }

        });

        leftBracketButton.setOnClickListener(v -> {
                if(basicEditText.length() == 0){
                    inputNumber(leftBracketButton);
                } else if ( !basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length()).equals(POINT)) {
                inputNumber(leftBracketButton);
            }
        });
        rightBracketButton.setOnClickListener(v ->{
            if(basicEditText.length() == 0){
                inputNumber(rightBracketButton);
            } else if ( !basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length()).equals(POINT)) {
                inputNumber(rightBracketButton);
            }
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

    private void inputNumber(Button button) {
        basicEditText.setText(basicEditText.getText().toString() + button.getText());
    }

    private boolean checkSing() {
        String endChar = basicEditText.getText().toString().substring(basicEditText.getText().length() - 1, basicEditText.getText().length());
        return !endChar.equals(PLUS) && !endChar.equals(MINUS) && !endChar.equals(MULTIPLY) && !endChar.equals(DIV) && !endChar.equals(POINT);
    }

    private void convertFormula() {
        boolean checkNumber = false;
        String Number = "";
        StringBuilder formulaText = new StringBuilder(basicEditText.getText().toString());
        textSing = new ArrayList();
        String oneChar;
        while (formulaText.length() != 0) {
            oneChar = formulaText.substring(0, 1);
            formulaText.delete(0, 1);
            if (oneChar.equals(PLUS) || oneChar.equals(MINUS) || oneChar.equals(MULTIPLY) || oneChar.equals(DIV) || oneChar.equals(LEFTSIGN) || oneChar.equals(RIGHTSING)) {
                checkNumber = true;
                if (checkNumber && !Number.equals("")) {
                    textSing.add(Number);
                    checkNumber = false;
                    Number = "";
                }
                textSing.add(oneChar);

            } else {
                Number = Number + oneChar;
                if (formulaText.length() == 0) {
                    textSing.add(Number);
                }
            }
        }
    }

    private void calculateFormulaHard() {
        while (textSing.size()!=1) {
            if (textSing.indexOf(RIGHTSING) == -1 && textSing.indexOf(LEFTSIGN) == -1) {
                calculateFormulaSimple(textSing);
            } else {

                subTextSing = new ArrayList<>();
                int endLeftSing = textSing.lastIndexOf(LEFTSIGN);
                int k = endLeftSing + 1;
                while (!textSing.get(k).equals(RIGHTSING)) {
                    subTextSing.add(textSing.get(k));
                    k++;
                }
                while (!textSing.get(endLeftSing).equals(RIGHTSING)) {
                    textSing.remove(endLeftSing);
                }
                textSing.set(endLeftSing, calculateFormulaSimple(subTextSing));

            }
        }
        writeEditText();
    }

    private String calculateFormulaSimple(ArrayList<String> subTextSing) {
        // Условие для проверки если "-" в самом начале
        checkFirstMinus(subTextSing);
        // Операции в формуле
                oneOperation(MULTIPLY,subTextSing);
                oneOperation(DIV,subTextSing);
                oneOperation(MINUS,subTextSing);
                oneOperation(PLUS,subTextSing);
        // Вывод результата
        return subTextSing.get(0);
    }

    private void oneOperation(String sing, ArrayList<String> subTextSing) {
        indexCalculate = subTextSing.indexOf(sing);
        while (indexCalculate != -1 && subTextSing.size()>1) {
            switch (sing){
                case(MULTIPLY):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) * Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case(DIV):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) / Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case(MINUS):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) - Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case(PLUS):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) + Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
            }
            subTextSing.set(indexCalculate, result + "");
            subTextSing.remove(indexCalculate + 1);
            subTextSing.remove(indexCalculate - 1);
            indexCalculate = subTextSing.indexOf(sing);
        }
    }

    private void writeEditText(){
        basicEditText.setText("");
        for (String s : textSing
        ) {
            basicEditText.setText(basicEditText.getText() + s);
        }
    }

    private void checkFirstMinus(ArrayList<String> subTextSing){
        if(subTextSing.get(0).equals(MINUS)) {
            subTextSing.set(1, String.valueOf(Float.valueOf(subTextSing.get(1))*-1));
            subTextSing.remove(0);
        }
    }

}