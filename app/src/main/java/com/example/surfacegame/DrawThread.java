package com.example.surfacegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private volatile boolean running=true;
    private Paint background=new Paint();
    private Bitmap bitmap;
    private int X;
    private int Y;
    {
        background.setColor(Color.BLUE);
        background.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
        this.surfaceHolder = surfaceHolder;
    }
    public void requestStop()
    {
        running=false;
    }
    public void setXY(int x,int y)
    {
        X=x;
        Y=y;
    }
    @Override
    public void run() {
        int sX=0,sY=0;
        while (running)
        {
            Canvas canvas=surfaceHolder.lockCanvas();
            if(canvas!=null){
                try
                {
                    canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),background);
                    canvas.drawBitmap(bitmap,sX,sY,background);
                    sX=X;
                    sY=Y;
                }
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
