package com.Obj;

import com.PlaneGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObj{
    public PlaneObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        super(img, x, y, width, height, speed, game);
        this.game.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                PlaneObj.super.x = e.getX() - 30;
                PlaneObj.super.y = e.getY() - 30;
            }
        });
    }
}
