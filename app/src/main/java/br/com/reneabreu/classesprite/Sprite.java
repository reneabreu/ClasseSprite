package br.com.reneabreu.classesprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    // Starting position = 0
    private int posX = 0, posY = 0;
    // Sprite Settings
    private int rows, cols, width, height;
    // Adding DrawView
    private DrawView drawView;
    // Choose sprite
    private Bitmap bmp;

    // Current frame in animation row
    private int currentFrame = 1;
    // Current animation state in sprite sheet cols
    private int currentAnimationState = 0;

    public Sprite(DrawView drawView, Bitmap bmp, int rows, int columns){
        this.drawView = drawView;
        this.bmp = bmp;
        this.rows = rows;
        this.cols = columns;

        this.width = bmp.getWidth() / columns;
        this.height = bmp.getHeight() / rows;
    }

    public void SetPos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    // Use this to change sprite sheet. Each sprite sheet have a different size, number of frames and number of animations.
    public void SetSprite(Bitmap newSprite, int rows, int columns){
        this.bmp = newSprite;
        this.rows = rows;
        this.cols = columns;
    }

    public void SetAnimationState(int state){
        this.currentAnimationState = state;
    }

    public void Update(){
        currentFrame = ++currentFrame % cols;
    }

    public void Draw(Canvas canvas) {

        Update();

        // Draw whole sprite sheet
        //canvas.drawBitmap(bmp, posX, posY, null);

        // Cutting sprite from sheet
        int srcX = currentFrame * width;
        int srcY = currentAnimationState * height;

        // src is a source rectangle inside the bitmap
        Rect src = new Rect(srcX,srcY, srcX + width, srcY + height);
        // dst is a destine rectangle in the canvas
        Rect dst = new Rect(posX, posY, posX + width, posY + height);

        // Draw cutted sprite
        canvas.drawBitmap(bmp,src,dst,null);
    }


}
