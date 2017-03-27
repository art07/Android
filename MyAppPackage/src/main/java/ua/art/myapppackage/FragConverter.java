package ua.art.myapppackage;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import br.com.sapereaude.maskedEditText.MaskedEditText;

public class FragConverter extends Fragment {
    private MaskedEditText editTextRate1;
    private MaskedEditText editTextRate2;
    private EditText mainEditText;
    private TextView totalField;
    private TextView field1;
    private TextView field2;
    private Button bStart;
    private ToggleButton toggleButton;
    private Spinner spinner;

    public FragConverter() {
    }

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_converter, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onStart() {
        super.onStart();
        ConvEventHandler convEventHandler = new ConvEventHandler(this);

        editTextRate1 = (MaskedEditText) getView().findViewById(R.id.editTextRate1);
        editTextRate2 = (MaskedEditText) getView().findViewById(R.id.editTextRate2);
        mainEditText = (EditText) getView().findViewById(R.id.main_edit_text);
        mainEditText.addTextChangedListener(convEventHandler);
        mainEditText.setEnabled(false);
        totalField = (TextView) getView().findViewById(R.id.total_field);

        field1 = (TextView) getView().findViewById(R.id.field1);
        field2 = (TextView) getView().findViewById(R.id.field2);

        TextView link1 = (TextView) getView().findViewById(R.id.text_view_link1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView link2 = (TextView) getView().findViewById(R.id.text_view_link2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        bStart = (Button) getView().findViewById(R.id.bStart);
        bStart.setOnClickListener(convEventHandler);

        toggleButton = (ToggleButton) getView().findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(convEventHandler);
        toggleButton.setEnabled(false);

        spinner = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(convEventHandler);
        spinner.setEnabled(false);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    MaskedEditText getEditTextRate1() {
        return editTextRate1;
    }

    MaskedEditText getEditTextRate2() {
        return editTextRate2;
    }

    EditText getMainEditText() {
        return mainEditText;
    }

    TextView getTotalField() {
        return totalField;
    }

    TextView getField1() {
        return field1;
    }

    TextView getField2() {
        return field2;
    }

    Button getbStart() {
        return bStart;
    }

    ToggleButton getToggleButton() {
        return toggleButton;
    }

    Spinner getSpinner() {
        return spinner;
    }
}
