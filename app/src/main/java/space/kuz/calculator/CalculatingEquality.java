package space.kuz.calculator;

import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;

import java.util.ArrayList;
public class CalculatingEquality {
    private ArrayList<String> textSing;     // Основная формула
    private ArrayList<String> subTextSing; // Часть основной формулы (что находться в скобочках)
    private Float result; // хранитель результата
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";
    public static final String DIV = "÷";
    public static final String LEFTSIGN = "(";
    public static final String RIGHTSING = ")";
    public static final String EMPTY = "";

    // Первод строки в колекцию для удобного вычисления
    public void convertFormula(EditText basicEditText) {
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
                if (checkNumber && !Number.equals(EMPTY)) {
                    textSing.add(Number);
                    checkNumber = false;
                    Number = EMPTY;
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

    // Метод вычесления сложных формул со скобками
    public void calculateFormulaHard(EditText basicEditText ) {
        while (textSing.size() != 1) {
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
        writeEditText(basicEditText);
    }
    // Метод для вычесления простых формул (внутри скобки без скобок)
    private String calculateFormulaSimple(ArrayList<String> subTextSing) {
        // Условие для проверки если "-" в самом начале
        checkFirstMinus(subTextSing);
        // Операции в формуле
        oneOperation(MULTIPLY, subTextSing);
        oneOperation(DIV, subTextSing);
        oneOperation(MINUS, subTextSing);
        oneOperation(PLUS, subTextSing);
        // Вывод результата
        return subTextSing.get(0);
    }
    // Вычесляет элементарную операцию -*+/
    private void oneOperation(String sing, ArrayList<String> subTextSing) {
        int indexCalculate = subTextSing.indexOf(sing);
        while (indexCalculate != -1 && subTextSing.size() > 1) {
            switch (sing) {
                case (MULTIPLY):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) * Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case (DIV):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) / Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case (MINUS):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) - Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
                case (PLUS):
                    result = Float.valueOf(subTextSing.get(indexCalculate - 1)) + Float.valueOf(subTextSing.get(indexCalculate + 1));
                    break;
            }
            subTextSing.set(indexCalculate, result + EMPTY);
            subTextSing.remove(indexCalculate + 1);
            subTextSing.remove(indexCalculate - 1);
            indexCalculate = subTextSing.indexOf(sing);
        }
    }
    // Запить в EditText
    private void writeEditText(EditText  basicEditText ) {
        basicEditText.setText(EMPTY);
        for (String s : textSing
        ) {
            basicEditText.setText(basicEditText.getText() + s);
        }
    }

    // Проверка наличия первого минуса в строке
    private void checkFirstMinus(ArrayList<String> subTextSing) {
        if (subTextSing.get(0).equals(MINUS)) {
            subTextSing.set(1, String.valueOf(Float.valueOf(subTextSing.get(1)) * -1));
            subTextSing.remove(0);
        }
    }
}

