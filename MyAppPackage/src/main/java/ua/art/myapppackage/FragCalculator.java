package ua.art.myapppackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragCalculator extends Fragment {
    private List<TextView> buttonList = new ArrayList<>();
    private List<TextView> fieldList = new ArrayList<>();

    public FragCalculator() {
    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_calculator, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onStart() {
        super.onStart();
        fieldList.add((TextView) getView().findViewById(R.id.textField));
        fieldList.add((TextView) getView().findViewById(R.id.textField2));

        buttonList.add((TextView) getView().findViewById(R.id.bClear));
        buttonList.add((TextView) getView().findViewById(R.id.bPercent));
        buttonList.add((TextView) getView().findViewById(R.id.bDivide));
        buttonList.add((TextView) getView().findViewById(R.id.bMultiply));
        buttonList.add((TextView) getView().findViewById(R.id.bMinus));
        buttonList.add((TextView) getView().findViewById(R.id.bPlus));
        buttonList.add((TextView) getView().findViewById(R.id.bEqual));
        buttonList.add((TextView) getView().findViewById(R.id.bPoint));
        buttonList.add((TextView) getView().findViewById(R.id.bDoubleZero));
        buttonList.add((TextView) getView().findViewById(R.id.bZero));
        buttonList.add((TextView) getView().findViewById(R.id.bOne));
        buttonList.add((TextView) getView().findViewById(R.id.bTwo));
        buttonList.add((TextView) getView().findViewById(R.id.bThree));
        buttonList.add((TextView) getView().findViewById(R.id.bFour));
        buttonList.add((TextView) getView().findViewById(R.id.bFive));
        buttonList.add((TextView) getView().findViewById(R.id.bSix));
        buttonList.add((TextView) getView().findViewById(R.id.bSeven));
        buttonList.add((TextView) getView().findViewById(R.id.bEight));
        buttonList.add((TextView) getView().findViewById(R.id.bNine));

        CalcEventHandler calcEventHandler = new CalcEventHandler(this);
        for (TextView button : buttonList) button.setOnClickListener(calcEventHandler);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    List<TextView> getFieldList() {
        return fieldList;
    }
}
