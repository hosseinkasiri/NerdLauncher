package com.example.nerdlauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class NerdLauncherActivity extends SingleFragmentActivity {

    @Override
    public Fragment mFragment() {
        return NerdLauncherFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
    }
}