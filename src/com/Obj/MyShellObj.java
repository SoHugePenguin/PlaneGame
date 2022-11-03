package com.Obj;

import com.PlaneGame;

import java.awt.*;

public class MyShellObj extends  GameObj{
    public static int type;
    public MyShellObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        super(img, x, y, width, height, speed, game);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y-=speed;
    }
}
