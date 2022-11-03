package com.Obj;

import com.GameUtils.List;
import com.PlaneGame;

import java.awt.*;

public class EnemyShellObj extends GameObj{

    public EnemyShellObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        super(img, x, y, width, height, speed, game);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y-=speed;
        if(this.get_Rectangle().intersects(this.getGame().plane.get_Rectangle())){
            PlaneGame.HP--;
            List.Delete_Obj.add(this);
            if(PlaneGame.HP <=0){
                PlaneGame.state = 2;
            }
        }
    }
}
