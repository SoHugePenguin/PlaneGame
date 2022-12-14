package com.Obj;

import com.PlaneGame;

import java.awt.*;

public class GameObj extends Thread {
    Image img;
    int x, y, width, height;
    double speed;
    PlaneGame game;

    public GameObj(Image img, int x, int y, int width, int height, double speed, PlaneGame game) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.game = game;
    }

    public GameObj(Image img, int x, int y, double speed) {
    }

    //渲染实体纹理
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    //获取实体矩形碰撞箱
    public Rectangle get_Rectangle() {
        return new Rectangle(x, y, width, height);
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public PlaneGame getGame() {
        return game;
    }

    public void setGame(PlaneGame game) {
        this.game = game;
    }
}
