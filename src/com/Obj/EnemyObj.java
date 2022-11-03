package com.Obj;

import com.GameUtils.List;
import com.PlaneGame;

import java.awt.*;

public class EnemyObj extends GameObj{
    public EnemyObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        super(img, x, y, width, height, speed, game);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y-=speed;
        //碰撞检测
        for (GameObj obj : List.gameObjs) {
            if(obj instanceof MyShellObj){
                if(this.get_Rectangle().intersects(obj.get_Rectangle())) {
                    List.Delete_Obj.add(obj);
                    List.Delete_Obj.add(this);
                    PlaneGame.score += 10;
                }
            }
        }
        //敌我飞机碰撞检测
        if(this.get_Rectangle().intersects(this.getGame().plane.get_Rectangle())){
            PlaneGame.state = 2;
        }
    }
}