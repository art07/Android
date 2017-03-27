package ua.art.myapppackage;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        if(this.getActionBar() != null) this.getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
