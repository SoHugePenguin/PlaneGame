package com.GameUtils;

import com.Obj.GameObj;
import com.PlaneGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class List {
    public static Image bg, sun, p1, p2, p3, p4, p5, p6, p7, p8, shell, shell2, heart;
    //游戏物的集合
    public static CopyOnWriteArrayList<GameObj> gameObjs = new CopyOnWriteArrayList<>();
    //CopyOnWriteArrayList应对多线程的时对数组修改会产生的越界报错现象ConcurrentModificationException

    //图片资源获取
    static {
        try {
            bg = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/bg.png")));
            sun = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/sun.png")));
            p1 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p1.png")));
            p2 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p2.png")));
            p3 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p3.png")));
            p4 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p4.png")));
            p5 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p5.png")));
            p6 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p6.png")));
            p7 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p7.png")));
            p8 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/p8.png")));
            shell = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/fireball.png")));
            shell2 = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/shell2.png")));
            heart = ImageIO.read(Objects.requireNonNull(PlaneGame.class.getClassLoader().getResource("resources/heart.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
