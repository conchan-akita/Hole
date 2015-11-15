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
<<<<<<< HEAD
import android.os.Handler;
=======
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
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
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
=======
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07

public class MainActivity extends AppCompatActivity{

    private RelativeLayout relativeLayout;
    //private ImageView[] baumImage = new ImageView[10];
<<<<<<< HEAD
    private RingImageView baumImage;
    private ImageView baumImage_noHole;
    private ImageView holeImage;
    private TextView pointValueText;
    private TextView countdownText;
    private int nowPoint=0;
=======
    private ImageView baumImage;
    private ImageView baumImage_noHole;
    private ImageView holeImage;
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
    private boolean clickedBaumFlg=false;
    private boolean doubleHole=false;
    DrawView drawView;
    private Bitmap baumBitmap;
    private Canvas baumBitmapCanvas;
    private Paint baumPaint;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
<<<<<<< HEAD
    public void onWindowFocusChanged(boolean hasFocus) {

        if(baumImage!=null) {

            final float baumImageCenterX = baumImage.getWidth() / 2;
            final float baumImageCenterY = baumImage.getHeight() / 2;
            baumImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN && !doubleHole) {
                        float x = event.getX();
                        float y = event.getY();
                        //drawView.mDraw();

                        // 穴あきbitmap canvas用意
                        // x,yを渡す
                        drawView.setXY(x, y);


                        // ImageView変更
                        // test code
                        // baumImage.setImageResource(R.drawable.hole);
                        // http://falco.sakura.ne.jp/tech/2013/09/android-imageview-%E3%82%88%E3%82%8A-bitmap-%E3%82%92%E5%8F%96%E5%BE%97%E3%81%99%E3%82%8B%E3%81%AB%E3%81%AF%EF%BC%9F/

                        //baumImage.
                        baumImage.setImageBitmap(drawView.makeHole());


                        // 得点の計算
                        // maxDistance = 最も離れた場所に穴を開けた場合
                        int maxDistance = (int) Math.sqrt(Math.pow((baumImageCenterX - 0), 2) + Math.pow((baumImageCenterY - 0), 2));
                        int point = maxDistance -  (int) Math.sqrt(Math.pow((baumImageCenterX - x), 2) + Math.pow((baumImageCenterY - y), 2));
                        nowPoint += point;

                        //Toast.makeText(getApplication(),"point = "+point,Toast.LENGTH_SHORT).show();
                        pointValueText.setText(Integer.toString(nowPoint));


                        clickedBaumFlg = true;
                        // 2回目のタップでは穴あかないように
                        doubleHole = true;

                        Log.v("151007", "baum clicked! x = " + x + " , y = " + y + " , baumX/2 = " + baumImageCenterX + " , baumY/2 = " + baumImageCenterY);
                    }
                    return false;
                }
            });
        }

        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        pointValueText = (TextView)findViewById(R.id.pointValueTextView);

        CountDownStart();

        drawView = new DrawView(getApplicationContext());
        //setContentView(drawView);

        // baum の imageView ver.
        baumImage = new RingImageView(getApplicationContext());
        // 透明可視デバッグ
        //baumImage.setBackgroundColor(0xffff0000);
        baumImage.setImageResource(R.drawable.baum3);
        //http://developer.android.com/reference/android/widget/ImageView.html#attr_android:scaleType
        //http://developer.android.com/reference/android/widget/ImageView.ScaleType.html
        baumImage.setScaleType(ImageView.ScaleType.CENTER);

        //final float baumImageCenterX = baumImage.getWidth()/2;
        //final float baumImageCenterY = baumImage.getHeight()/2;
        baumImage_noHole = baumImage;
        baumImage.setClickable(true);
=======
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
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07

        // 画像追加位置
        // http://developer.android.com/reference/android/widget/RelativeLayout.html
        // http://d.hatena.ne.jp/gm_ma/20120222/1329927858
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WC,WC);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        relativeLayout.addView(baumImage, params);
<<<<<<< HEAD
        // View.GONEにするとbaumImageCenterX , baumImageCenterYが0になる
        baumImage.setVisibility(View.INVISIBLE);

        //relativeLayout.addView(drawView);

        //animateTranslationX(baumImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                baumImage.setVisibility(View.VISIBLE);
                animateTranslationX(baumImage);
            }
        },6500);

    }

    private void CountDownStart(){
        //countdownText = new TextView(this);
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WC,WC);
        //params.addRule(RelativeLayout.CENTER_VERTICAL);
        //params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //relativeLayout.addView(countdownText);
        countdownText= (TextView)findViewById(R.id.countdownTextView);
        countdownText.setText("流れてくる\nバウムクーヘンの\n中心をタッチ！");
        // sleep中、UIスレッドはそのまま待たされるっぽい
        /*try {
            Thread.sleep(2000);
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // http://stackoverflow.com/questions/22803476/animators-may-only-be-run-on-looper-threads-on-sherlock-action-bar
        final Handler mHandler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                // スリープ処理をmHandler.postの外でやってみる
                try {
                    Thread.sleep(2500);
                }catch(InterruptedException e){
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        //Toast.makeText(getApplication(),"thread test",Toast.LENGTH_SHORT).show();
                        countdownText.setText("3");
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        countdownText.setText("2");
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        countdownText.setText("1");
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        countdownText.setText("");
                        //relativeLayout.removeView(countdownText);
                    }
                });
            }
        }).start();
        //relativeLayout.removeView(countdownText);
        Toast.makeText(this,"countDownStart() END.",Toast.LENGTH_SHORT).show();
=======
        //relativeLayout.addView(drawView);

        animateTranslationX(baumImage);


>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
    }

    // http://qiita.com/glayash/items/1822a852ebaa7d26a53e
    // http://techblog.yahoo.co.jp/programming/androidiphone/
    private void animateTranslationX( ImageView target) {

        int width_targetImage=target.getWidth();
        //int rand_duration = duration;
        // translationXプロパティを変化させます
<<<<<<< HEAD
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "translationX", -100f, 70f);
        //objectAnimator.ofFloat(target,)

        // 3秒かけて実行させます
        int firstSpeed=2000;
        Log.v("speed","firstSpeed = "+firstSpeed);
        objectAnimator.setDuration(firstSpeed);
=======
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "translationX", -700f, 700f);
        //objectAnimator.ofFloat(target,)

        // 3秒かけて実行させます
        objectAnimator.setDuration(Rand());
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07

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
<<<<<<< HEAD
                // Integer.toString(nowPoint));
                countdownText.setText("お疲れ様！");
=======


>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
<<<<<<< HEAD
                // 1つのバウムを何度もタップできないようにする
                doubleHole = false;
                /*Object lock = new Object();
                synchronized (lock) {
                    try {
                        lock.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                int nextSpeed = 1101;
                objectAnimator.setDuration(nextSpeed);
                Log.v("animSpeed", "speed = " + nextSpeed + " , getDuration = "+objectAnimator.getDuration());


                if (clickedBaumFlg) {
                    // 次に流れるバウムは穴の開いていない画像.
                    baumImage.setImageResource(R.drawable.baum3);
                    clickedBaumFlg = false;
=======
                objectAnimator.setDuration(Rand());
                if (clickedBaumFlg) {
                    // 次に流れるバウムは穴の開いていない画像.
                    baumImage.setImageResource(R.drawable.baum);
                    clickedBaumFlg=false;
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
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
<<<<<<< HEAD
        //ImageView baum_with_hole;
=======
        //ImageView baumWithHole;
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07

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
<<<<<<< HEAD
            baumBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.baum3,options);
            baumBitmapCanvas = new Canvas(baumBitmap);
            paint.setColor(Color.rgb(238,238,238));
            baumBitmapCanvas.drawCircle(touchedImageX, touchedImageY, 50, paint);
            baumBitmapCanvas.drawBitmap(baumBitmap, 0, 0, null);

            //baum_with_hole = new ImageView(getContext());
            //baum_with_hole.setImageBitmap(baumBitmap);
            //return baum_with_hole;
=======
            baumBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.baum,options);
            baumBitmapCanvas = new Canvas(baumBitmap);
            paint.setColor(Color.GREEN);
            baumBitmapCanvas.drawCircle(touchedImageX, touchedImageY, 50, paint);
            baumBitmapCanvas.drawBitmap(baumBitmap, 0, 0, null);

            //baumWithHole = new ImageView(getContext());
            //baumWithHole.setImageBitmap(baumBitmap);
            //return baumWithHole;
>>>>>>> b1a4254a390f82af96662ec69f63c3b1878b1c07
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

