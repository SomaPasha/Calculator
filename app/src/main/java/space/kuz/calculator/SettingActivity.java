package space.kuz.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import static space.kuz.calculator.Сonstant.DATA;
import static space.kuz.calculator.Сonstant.SWITCH_SETTING;

public class SettingActivity extends AppCompatActivity {
    private Switch switchSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        changeTheme();
    }

    private void changeTheme() {
        switchSetting = (Switch) findViewById(R.id.setting_switch);
        SharedPreferences sharedPreferences = this.getSharedPreferences(DATA,Context.MODE_PRIVATE);
        boolean  strength = sharedPreferences.getBoolean(SWITCH_SETTING,false);
        switchSetting.setChecked(strength);
        switchSetting.setOnClickListener(v ->  {
            boolean checked = ((Switch) v).isChecked();
            if (checked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("DATA",Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("SWITCH_SETTING", switchSetting.isChecked()).apply();
        outState.putBoolean("SWITCH_SETTING", switchSetting.isChecked());
        super.onSaveInstanceState(outState);
    }


}