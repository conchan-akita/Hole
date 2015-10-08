package jp.akita.conchan.hole;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity{

    private RelativeLayout relativeLayout;
    //private ImageView[] baumImage = new ImageView[10];
    private ImageView baumImage;
    private ImageView baumImage_noHole;
    private ImageView holeImage;
    private boolean clickedBaumFlg=false;
    private boolean doubleHole=false;
    DrawView drawView;
    private Bitmap baumBitmap;
    private Canvas baumBitmapCanvas;
    private Paint baumPaint;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

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
        //setContentView(drawView);

        // baum の imageView ver.
        baumImage = new ImageView(this);
        baumImage.setImageResource(R.drawable.baum);
        baumImage_noHole = baumImage;
        baumImage.setClickable(true);
        baumImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN /*&& !doubleHole*/) {
                    float x = event.getX();
                    float y = event.getY();
                    //drawView.mDraw();

                    // 穴あきbitmap canvas用意
                    // x,yを渡す
                    drawView.setXY(x,y);


                    // ImageView変更
                    // test code
                    // baumImage.setImageResource(R.drawable.hole);
                    // http://falco.sakura.ne.jp/tech/2013/09/android-imageview-%E3%82%88%E3%82%8A-bitmap-%E3%82%92%E5%8F%96%E5%BE%97%E3%81%99%E3%82%8B%E3%81%AB%E3%81%AF%EF%BC%9F/

                    //baumImage.
                    baumImage.setImageBitmap(drawView.makeHole());


                    clickedBaumFlg=true;
                    // 2回目のタップでは穴あかないように
                    doubleHole=true;

                    Log.v("151007", "baum clicked! x = "+x+" , y = "+y);
                }
                return false;
            }
        });

        // 画像追加位置
        // http://developer.android.com/reference/android/widget/RelativeLayout.html
        // http://d.hatena.ne.jp/gm_ma/20120222/1329927858
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WC,WC);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        relativeLayout.addView(baumImage, params);
        //relativeLayout.addView(drawView);

        animateTranslationX(baumImage);


    }

    // http://qiita.com/glayash/items/1822a852ebaa7d26a53e
    // http://techblog.yahoo.co.jp/programming/androidiphone/
    private void animateTranslationX( ImageView target) {

        int width_targetImage=target.getWidth();
        //int rand_duration = duration;
        // translationXプロパティを変化させます
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "translationX", -700f, 700f);
        //objectAnimator.ofFloat(target,)

        // 3秒かけて実行させます
        objectAnimator.setDuration(Rand());

        //objectAnimator.setCurrentPlayTime(Rand());

        //repeat
        objectAnimator.setRepeatCount(10 - 1);

        // animation speed
        objectAnimator.setInterpolator(new LinearInterpolator());

        // listener
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Log.v("151007", "animation END.");


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                objectAnimator.setDuration(Rand());
                if (clickedBaumFlg) {
                    // 次に流れるバウムは穴の開いていない画像.
                    baumImage.setImageResource(R.drawable.baum);
                    clickedBaumFlg=false;
                }
            }
        });

        // アニメーションを開始します
        objectAnimator.start();
    }


    // http://techbooster.org/android/application/715/
    // http://www.hp3200.com/android-app-development/b-touch.html
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
            //drawView.invalidate();
        }
        return false;
    }


    public int Rand(){
        // (0~1) * 1000 + 1000   min1000 max2000
        return (int)(Math.random()*1000)+1000;
    }


    public class DrawView extends View{

        Context context;
        float touchedImageX;
        float touchedImageY;
        Bitmap baumBitmap;
        Canvas baumBitmapCanvas;
        Paint paint = new Paint();
        //ImageView baumWithHole;

        public DrawView(Context context) {
            super(context);
            this.context = context;
        }

        public void setXY(float x, float y){
            touchedImageX=x;
            touchedImageY=y;
        }

        public Bitmap makeHole(){
            BitmapFactory.Options options = new BitmapFactory.Options();
            // API Level 11(3.0) 以上しか使えない
            // http://dev.classmethod.jp/smartphone/android/android-immutable-bitmap-mutable/
            options.inMutable = true;
            baumBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.baum,options);
            baumBitmapCanvas = new Canvas(baumBitmap);
            paint.setColor(Color.GREEN);
            baumBitmapCanvas.drawCircle(100, 100, 50, paint);
            baumBitmapCanvas.drawBitmap(baumBitmap, 0, 0, null);

            //baumWithHole = new ImageView(getContext());
            //baumWithHole.setImageBitmap(baumBitmap);
            //return baumWithHole;
            return baumBitmap;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
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

