package ua.art.myapppackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import java.text.NumberFormat;
import java.util.Locale;

class ConvEventHandler implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, TextWatcher {
    private final FragConverter parent;
    private double uahRate, rubRate;
    private static int currentPosition;
    private final NumberFormat valueFormatUAH = NumberFormat.getCurrencyInstance(new Locale("uk", "UA"));
    private final NumberFormat valueFormatUSD = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private final NumberFormat valueFormatRUB = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));

    ConvEventHandler(FragConverter parent) {
        this.parent = parent;
    }

    /*Button*/
    @Override
    public void onClick(View v) {
        exchangeRateAdjustment();
        parent.getbStart().setEnabled(false);
        parent.getToggleButton().setEnabled(true);
    }

    /*Toggle button*/
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            parent.getEditTextRate1().setEnabled(true);
            parent.getEditTextRate2().setEnabled(true);
            parent.getSpinner().setEnabled(false);
            parent.getMainEditText().setEnabled(false);
        } else {
            exchangeRateAdjustment();
            action();
        }
    }

    /*Spinner*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentPosition = position;
        action();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    /*end*/

    /*TextWatcher*/
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        action();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
    /*end*/

    /*EXCHANGE RATE ADJUSTMENT*/
    private void exchangeRateAdjustment() {
        if (!(parent.getEditTextRate1().getText().toString()).equals("")) {
            uahRate = Double.parseDouble(parent.getEditTextRate1().getText().toString());
        } else {
            uahRate = 0.0;
        }

        if (!(parent.getEditTextRate2().getText().toString()).equals("")) {
            rubRate = Double.parseDouble(parent.getEditTextRate2().getText().toString());
        } else {
            rubRate = 0.0;
        }
        parent.getEditTextRate1().setEnabled(false);
        parent.getEditTextRate2().setEnabled(false);
        parent.getSpinner().setEnabled(true);
        parent.getMainEditText().setEnabled(true);
    }
    /*end*/

    /*ACTION*/
    private void action() {
        if(!parent.getMainEditText().getText().toString().equals("")) {
            if (currentPosition == 0) {
                parent.getField1().setText(R.string.usd);
                parent.getField2().setText(R.string.uah);
                parent.getTotalField().setText(String.valueOf(valueFormatUAH.format(Double.parseDouble(parent.getMainEditText().getText().toString()) * uahRate)));
            } else if (currentPosition == 1) {
                parent.getField1().setText(R.string.uah);
                parent.getField2().setText(R.string.usd);
                parent.getTotalField().setText(String.valueOf(valueFormatUSD.format(Double.parseDouble(parent.getMainEditText().getText().toString()) / uahRate)));
            } else if (currentPosition == 2) {
                parent.getField1().setText(R.string.usd);
                parent.getField2().setText(R.string.rub);
                parent.getTotalField().setText(String.valueOf(valueFormatRUB.format(Double.parseDouble(parent.getMainEditText().getText().toString()) * rubRate)));
            } else if (currentPosition == 3) {
                parent.getField1().setText(R.string.rub);
                parent.getField2().setText(R.string.usd);
                parent.getTotalField().setText(String.valueOf(valueFormatUSD.format(Double.parseDouble(parent.getMainEditText().getText().toString()) / rubRate)));
            }
        } else {
            parent.getField1().setText("");
            parent.getField2().setText("");
            parent.getTotalField().setText("");
        }
    }
}
