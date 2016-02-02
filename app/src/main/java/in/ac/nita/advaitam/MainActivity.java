package in.ac.nita.advaitam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_MS = 3000;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View image = findViewById(R.id.logoView);
        //Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        //image.startAnimation(animation1);
        image.animate().alpha(1).scaleY(1).scaleX(1).setDuration(300);
        mHandler.postDelayed(mEndSplash, SPLASH_DURATION_MS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mEndSplash.run();
        return super.onTouchEvent(event);
    }

    private Runnable mEndSplash = new Runnable() {
        public void run() {
            if (!isFinishing()) {
                mHandler.removeCallbacks(this);
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        };
    };
}
