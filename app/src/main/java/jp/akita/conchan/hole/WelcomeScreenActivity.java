package jp.akita.conchan.hole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by iconchan on 2015/10/06.
 */
public class WelcomeScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen_activity);

        ((Button)findViewById(R.id.startButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent varIntent = new Intent(WelcomeScreenActivity.this,MainActivity.class);
                startActivity(varIntent);
            }
        });
    }
}
