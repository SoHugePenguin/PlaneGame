package com.Obj;

import com.PlaneGame;

import java.awt.*;

public class GameObj {
    Image img;
    int x,y,width,height;
    double speed;
    PlaneGame game;

    //渲染实体纹理
    public void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    //获取实体矩形碰撞箱
    public Rectangle get_Rectangle(){
        return new Rectangle(x,y,width,height);
    }

    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }

    public PlaneGame getGame() {
        return game;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setGame(PlaneGame game) {
        this.game = game;
    }

    public GameObj(Image img, int x, int y, double speed) {
    }
    public GameObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.game = game;
    }
}
