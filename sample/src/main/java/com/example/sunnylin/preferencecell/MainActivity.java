package com.example.sunnylin.preferencecell;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView boolTextView = null;
    TextView intTextView = null;
    TextView longTextView = null;
    TextView floatTextView = null;
    TextView stringTextView = null;
    TextView enumTextView = null;
    TextView mapIntTextView = null;
    TextView mapEnumTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolTextView = findViewById(R.id.boolTypeValue);
        intTextView = findViewById(R.id.intTypeValue);
        longTextView = findViewById(R.id.longTypeValue);
        floatTextView = findViewById(R.id.floatTypeValue);
        stringTextView = findViewById(R.id.stringTypeValue);
        enumTextView = findViewById(R.id.enumTypeValue);
        mapIntTextView = findViewById(R.id.mapIntTypeValue);
        mapEnumTextView = findViewById(R.id.mapEnumTypeValue);
        readValue();
    }

    private void readValue() {
        Boolean boolType = PreferenceManage.BOOL_TYPE.get();
        boolTextView.setText(String.valueOf(boolType));
        String stringType = PreferenceManage.STRING_TYPE.get();
        stringTextView.setText(stringType);
        Integer intType = PreferenceManage.INT_TYPE.get();
        intTextView.setText(String.valueOf(intType));
        Long longType = PreferenceManage.LONG_TYPE.get();
        longTextView.setText(String.valueOf(longType));
        Float floatType = PreferenceManage.FLOAT_TYPE.get();
        floatTextView.setText(String.valueOf(floatType));
        ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
        enumTextView.setText(enumType.toString());
        Random random =new Random();
        ExampleEnum mapEnumType = PreferenceManage.MAP_INTEGER_TYPE.get(random.nextInt(10) % 2);
        mapIntTextView.setText(mapEnumType.toString());
        Integer mapIntType = PreferenceManage.MAP_ENUM_TYPE.get(ExampleEnum.values()[random.nextInt(ExampleEnum.values().length)]);
        mapEnumTextView.setText(String.valueOf(mapIntType));
    }

    public void onWriteClick(View view) {
        Random random =new Random();
        PreferenceManage.BOOL_TYPE.set(random.nextInt(10) % 2 == 0);
        String text = getString(R.string.text_sample);
        String[] split = text.split(" ");
        PreferenceManage.STRING_TYPE.set(split[random.nextInt(split.length)] + " " + split[random.nextInt(split.length)]);
        PreferenceManage.INT_TYPE.set(random.nextInt(99999));
        PreferenceManage.LONG_TYPE.set(random.nextLong());
        PreferenceManage.FLOAT_TYPE.set(random.nextFloat());
        PreferenceManage.ENUM_TYPE.set(ExampleEnum.values()[random.nextInt(ExampleEnum.values().length)]);
        PreferenceManage.MAP_INTEGER_TYPE.set(random.nextInt(10) % 2, ExampleEnum.values()[random.nextInt(ExampleEnum.values().length)]);
        PreferenceManage.MAP_ENUM_TYPE.set(ExampleEnum.values()[random.nextInt(ExampleEnum.values().length)],random.nextInt(99999));
        readValue();
    }

    public void onResetClick(View view) {
        PreferenceManage.BOOL_TYPE.reset();
        PreferenceManage.STRING_TYPE.reset();
        PreferenceManage.INT_TYPE.reset();
        PreferenceManage.LONG_TYPE.reset();
        PreferenceManage.FLOAT_TYPE.reset();
        PreferenceManage.ENUM_TYPE.reset();
        PreferenceManage.MAP_INTEGER_TYPE.reset(new Integer[]{0, 1});
        PreferenceManage.MAP_ENUM_TYPE.reset(ExampleEnum.values());
        readValue();
    }

    public void onRestartClick(View view) {
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        startActivity(mainIntent);
        System.exit(0);
    }
}
