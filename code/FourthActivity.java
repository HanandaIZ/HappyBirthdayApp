package id.co.hananda.atry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }
    public void gotoSecondActivity (View view){
        Intent intent = new Intent (this, SecondActivity.class);
        startActivity(intent);
    }

    public void gotoThirdActivity (View view){
        Intent intent = new Intent (this , ThirdActivity.class);
        startActivity(intent);
    }
    public void gotoFourthActivity (View view){
        Intent intent = new Intent (this, FourthActivity.class);
        startActivity(intent);
    }
}
