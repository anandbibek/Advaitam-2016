package in.ac.nita.advaitam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_MS = 1000;
    private Handler mHandler = new Handler();
    View logoView, titleView, subHeadingView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoView = findViewById(R.id.logoView);
        titleView = findViewById(R.id.titleView);
        subHeadingView = findViewById(R.id.subheading);
        //Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        //image.startAnimation(animation1);
        //image.animate().alpha(1).scaleY(1).scaleX(1).setDuration(300);
        mHandler.postDelayed(mEndSplash, SPLASH_DURATION_MS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        staggeredAnimate();
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mEndSplash.run();
//        return super.onTouchEvent(event);
//    }


    private void staggeredAnimate() {
        View[] animatedViews = new View[] { logoView, titleView, subHeadingView };
        Interpolator interpolator = new DecelerateInterpolator();

        for (int i = 0; i < animatedViews.length; ++i) {
            View v = animatedViews[i];
            v.setAlpha(0f);
            v.setTranslationY(120);
            v.animate()
                    .setInterpolator(interpolator)
                    .alpha(1.0f)
                    .translationY(0)
                    .setStartDelay(300 + 200 * i)
                    .start();
        }
    }
}
