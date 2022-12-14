package com.Obj;

import com.GameUtils.List;

import java.awt.*;

public class bgObj extends GameObj {
    public bgObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
        //获取实体数据
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(List.bg, x, y, null);
        super.paintSelf(g);
        y += speed;
        if (y >= 0) {
            y = -2000;
        }
    }
}
