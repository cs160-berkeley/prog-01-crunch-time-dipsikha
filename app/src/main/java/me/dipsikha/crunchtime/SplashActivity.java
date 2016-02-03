package me.dipsikha.crunchtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {
    private static final long DELAY = 2000;
    private boolean mScheduled = false;
    private Timer mSplashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TextView logo = (TextView) findViewById(R.id.splash_text);
        FontUtils.applyOpenSans(this, logo);

        mSplashTimer = new Timer();
        mSplashTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Activity activity = SplashActivity.this;
                        activity.finish();
                        startActivity(new Intent(activity, MainActivity.class));
                    }
                },
                DELAY);
        mScheduled = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mScheduled) {
            mSplashTimer.cancel();
        }
        mSplashTimer.purge();
    }
}

