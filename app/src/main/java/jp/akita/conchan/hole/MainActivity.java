package jp.akita.conchan.hole;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    private RelativeLayout relativeLayout;
    //private ImageView[] baumImage = new ImageView[10];
    private ImageView baumImage;
    private ImageView holeImage;
    private int loopCount=0;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);

        /*for (int i=0;i<10;i++) {
            baumImage[i] = new ImageView(this);
            baumImage[i].setImageResource(R.drawable.baum);
            relativeLayout.addView(baumImage[i]);

            Anim(i);

        }*/

        drawView = new DrawView(this);

        baumImage = new ImageView(this);
        baumImage.setImageResource(R.drawable.baum);
        //baumImage.setBackgroundResource(R.drawable.baum);
        baumImage.setClickable(true);
        baumImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.v("151007", "baum clicked!");
                }
                return false;
            }
        });
        relativeLayout.addView(baumImage);
        Anim(Rand());

        /*ImageView baumImageSolo = new ImageView(this);
        baumImageSolo.setImageResource(R.drawable.baum);
        baumImageSolo.setBackgroundResource(R.drawable.baum_anim);
        AnimationDrawable baumAnimation = (AnimationDrawable)baumImageSolo.getBackground();
        baumAnimation.start();*/



    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            // タッチ押下
            double x = event.getX();
            double y = event.getY();
            Log.v("151007", "Touch! x = " + x + " , y = " + y);

            // hole
            //holeImage=new ImageView(this);

            //baumImage.setImageResource(R.drawable.hole);
            drawView.invalidate();
        }
        return false;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.v("151007", "onAnimationEnd");
        if(loopCount<10)
            Anim(Rand());
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void Anim(/*int i*/int duration){
        TranslateAnimation translateAnimation = new TranslateAnimation(-700,700,250,250);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(this);
        //baumImage[i].startAnimation(translateAnimation);
        baumImage.startAnimation(translateAnimation);
        loopCount++;
        //baumImage.startAnimation();
        Log.v("151006","Anim() end.");
    }

    public int Rand(){
        // (0~1) * 1000 + 1000   min1000 max2000
        return (int)(Math.random()*1000)+1000;
    }















    private class DrawView extends View{

        Resources resources;
        Bitmap holeImage;
        Paint paint;

        // コンストラクタ
        public DrawView(Context context) {
            super(context);

            setWillNotDraw(false);

            resources=this.getContext().getResources();
            holeImage= BitmapFactory.decodeResource(resources, R.drawable.hole);
            //this.invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint=new Paint();
            canvas.drawBitmap(holeImage,100,100,paint);
            Log.v("151007","onDraw End.");
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

