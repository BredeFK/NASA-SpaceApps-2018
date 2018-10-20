package nasa.space.apps.rocketlaunch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    private boolean isChecked = true;
    private int selectedLps = 0;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Bundle bundle = getIntent().getExtras();
        int length = 0;
        if (bundle != null) {
            length = bundle.getInt("length");
        }

        String[] items = new String[length];
        for (int i = 0; i < length; i++) {
            items[i] = bundle.getString("name" + i);
        }


        final Spinner spinner = findViewById(R.id.settings_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        selectedLps = preferences.getInt("lps", 0);
        spinner.setSelection(selectedLps);
        Switch aSwitch = findViewById(R.id.settings_notifications);

        isChecked = preferences.getBoolean("checked", true);
        aSwitch.setChecked(isChecked);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSelected(position, spinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateIsChecked(isChecked);
            }
        });

    }

    private void updateSelected(int pos, String selected) {
        selectedLps = pos;
        editor = preferences.edit();
        editor.putInt("lps", selectedLps);
        editor.putString("lpsChoice", selected);
        editor.apply();
        // TODO : Send to Main

    }

    private void updateIsChecked(boolean isCheked) {
        this.isChecked = isCheked;
        editor = preferences.edit();
        editor.putBoolean("checked", this.isChecked);
        editor.apply();
        // TODO : Send to notifications
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
