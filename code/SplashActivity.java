package id.co.hananda.atry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.splashScreenTheme);
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_splash); //kayanya ini buat masuk ke activitynya terus ROFL.
        Thread thread= new Thread() {
            public void run() {
                try {
                    sleep(3000);
                }   catch (InterruptedException e){
                    e.printStackTrace();
                }   finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        };
        thread.start();
    }
}
