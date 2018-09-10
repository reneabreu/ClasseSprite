package br.com.reneabreu.classesprite;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView  implements Runnable {

    boolean isRunning;
    Thread gameLoop;

    Canvas canvas;
    Sprite sprite;

    SurfaceHolder surfaceHolder;

    public DrawView(Context context){
        super(context);

        surfaceHolder = getHolder();

        // Set sprite
        sprite = new Sprite(this, BitmapFactory.decodeResource(getResources(), R.drawable.player),4,6);
        sprite.SetPos(200,200);
        sprite.SetAnimationState(0);
    }

    public void resume(){
        isRunning = true;

        gameLoop = new Thread(this);
        gameLoop.start();

    }

    public void stop(){
        isRunning = false;

        try {
            gameLoop.join();
        } catch (InterruptedException ie){

        }
    }


    @Override
    public void run() {

        while (isRunning){

            update();
            render();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie){

            }
        }
    }


    void update(){

    }

    void render(){

        if (!surfaceHolder.getSurface().isValid())
            return;

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        sprite.Draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }
}
