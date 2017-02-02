package cmgine.com.cnode_android_test.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import cmgine.com.cnode_android_test.R;
import cmgine.com.cnode_android_test.utils.ActivityUtils;
import cmgine.com.cnode_android_test.utils.HandlerUtils;

/**
 * Created by Jerry on 26/01/2017.
 */

public class LaunchActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        HandlerUtils.postDelayed(this, 1000);
    }

    @Override
    public void run() {
        if(ActivityUtils.isAlive(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
