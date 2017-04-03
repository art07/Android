package ua.art.myapppackage;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

class CalcEventHandler implements View.OnClickListener {
    private FragCalculator parent;
    private String textInput = "";
    private double firstNumbers = 0.0;
    private double secondNumbers = 0.0;
    private double totalNumber = 0.0;
    private char ch = ' ';
    private boolean point = false;

    CalcEventHandler(FragCalculator parent) {
        this.parent = parent;
    }

    @Override
    public void onClick(View v) {
        TextView bTextView = (TextView) v;

        String tempTextInput;
        if (bTextView.getId() == R.id.bClear) {
            variablesCleaning();
        } else if (bTextView.getId() == R.id.bPercent) {
            if(!textInput.equals("") && firstNumbers != 0.0) {
                secondNumbers = Double.parseDouble(textInput);
                switch (ch) {
                    case '*': totalNumber = firstNumbers * (secondNumbers / 100);
                        break;
                    case '+': totalNumber = firstNumbers + ((secondNumbers / 100) * firstNumbers);
                        break;
                    case '-': totalNumber = firstNumbers - ((secondNumbers / 100) * firstNumbers);
                        break;
                }
                textInput = String.valueOf(totalNumber);
                parent.getFieldList().get(0).setText("");
                tempTextInput = textInput;
                variablesCleaning();
                textInput = tempTextInput;
                parent.getFieldList().get(1).setText(textInput);
            }
        } else if (bTextView.getId() == R.id.bDivide) {
            ch = '/';
            saveFirstNumber();
        } else if (bTextView.getId() == R.id.bMultiply) {
            ch = '*';
            saveFirstNumber();
        } else if (bTextView.getId() == R.id.bMinus) {
            ch = '-';
            saveFirstNumber();
        } else if (bTextView.getId() == R.id.bPlus) {
            ch = '+';
            saveFirstNumber();
        } else if (bTextView.getId() == R.id.bEqual) {
            if(!textInput.equals("") && firstNumbers != 0.0) {
                secondNumbers = Double.parseDouble(textInput);
                switch (ch) {
                    case '/':
                        if (secondNumbers != 0.0) {
                            totalNumber = firstNumbers / secondNumbers;
                        } else {
                            variablesCleaning();
                            parent.getFieldList().get(1).setText(textInput);
                            parent.getFieldList().get(1).setHint(R.string.division_by_zero);
                            return;
                        }
                        break;
                    case '*':
                        totalNumber = firstNumbers * secondNumbers;
                        break;
                    case '-':
                        totalNumber = firstNumbers - secondNumbers;
                        break;
                    case '+':
                        totalNumber = firstNumbers + secondNumbers;
                        break;
                }
                textInput = String.valueOf(totalNumber);
                parent.getFieldList().get(0).setText("");
                tempTextInput = textInput;
                variablesCleaning();
                textInput = tempTextInput;
                parent.getFieldList().get(1).setText(textInput);
            }
        } else if (bTextView.getId() == R.id.bPoint) {
            if(!point) {
                parent.getFieldList().get(1).setText(textInput += bTextView.getText());
                point = true;
            }
        } else if (bTextView.getId() == R.id.bDoubleZero) {
            if(!textInput.equals("") && !textInput.equals("0")) {
                parent.getFieldList().get(1).setText(textInput += bTextView.getText());
            }
        } else {
            parent.getFieldList().get(1).setText(textInput += bTextView.getText());
        }
        buttonAnimation(bTextView);
    }

    private void saveFirstNumber() {
        if(!textInput.equals("")) {
            parent.getFieldList().get(1).setHint("0");
            parent.getFieldList().get(0).setText(textInput + " " + ch);
            firstNumbers = Double.parseDouble(textInput);
            point = false;
            textInput = "";
            parent.getFieldList().get(1).setText(textInput);
        }
    }

    private void variablesCleaning() {
        textInput = "";
        firstNumbers = 0;
        secondNumbers = 0;
        totalNumber = 0;
        ch = ' ';
        point = false;
        parent.getFieldList().get(0).setText("");
        parent.getFieldList().get(1).setText(textInput);
    }

    private void buttonAnimation(TextView bTextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = bTextView.getWidth() / 2;
            int centerY = bTextView.getHeight() / 2;
            float startRadius = bTextView.getWidth();
            float endRadius = 15;
            ViewAnimationUtils.createCircularReveal(bTextView, centerX, centerY, startRadius, endRadius).start();
        }
    }
}
