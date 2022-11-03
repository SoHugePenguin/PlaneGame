package com;

import com.GameUtils.List;
import com.Obj.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PlaneGame extends JFrame {
    public PlaneGame() {
        ActionListener actionListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果关闭了游戏界面，则程序停止运行，解除后台占用
                if(!plane.getGame().isShowing()){
                    System.exit(0);
                }
            }
        };
        Timer timer = new Timer(100, actionListener);
        timer.start();
    }

    //游戏刻0.025s / 1time
    int time = 0;
    int width = 1000 ,height = 1000;
    public static int state = 0;
    public static Long score = 0L;
    public static int HP = 5;
    bgObj bgObj = new bgObj(List.bg,0,-2000,2);

    //双缓存技术-1
    Image offScreen = null;
    //飞机实体
    public PlaneObj plane = new PlaneObj(List.p1,400,400,66,80,0,this);

    public static void main(String[] args) {
        System.out.println("欢迎来到全民飞机大战低配版！");
        PlaneGame planeGame = new PlaneGame();
        planeGame.launch();
    }

    public void launch() {

        state = 0;
        this.setVisible(true);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setTitle("全民飞机大战  ||   空格键暂停  ||  鼠标移动飞机  V 1.0.5");

        //添加游戏元素到游戏数据集合
        List.gameObjs.add(bgObj);
        List.gameObjs.add(plane);

        //鼠标事件识别
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 1 && state ==0){
                    state = 1;
                    System.out.println("成功进入游戏！");
                    repaint();
                } else if (state == 2) {
                    //还原所有数据并回到主页
                    List.Delete_Obj = new ArrayList<>();
                    List.EnemyObjs = new ArrayList<>();
                    List.gameObjs.removeIf(obj -> !(obj instanceof PlaneObj || obj instanceof bgObj));
                    bgObj.setY(-2000);
                    time = 0;
                    score = 0L;
                    HP = 5;
                    state = 0;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        //键盘事件识别（暂停）
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(state == 1 && e.getKeyCode() == 32){  //32即空格代号
                    System.out.println("游戏已暂停，再按一次空格键恢复！");
                    state =4;
                } else if (state ==4 && e.getKeyCode() == 32) {
                    System.out.println("游戏已恢复！");
                    state =1;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        while (true){
            if(state == 1){
                //统计游戏时长
                time ++;//如果游戏正在进行则定时执行事件。
                if(time % 10 ==0) createShellObj(); //生成我方子弹
                if(time % 30 == 0)  createEnemyObj(); //生成敌方飞机
                if(time % 60 == 0)  summon_bullet();
            }
            //重新绘制
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g){
        if(state != 4) {
//双缓存技术-2
            if(offScreen == null){
                offScreen = createImage(width,height);
            }
            Graphics gImage = offScreen.getGraphics();

            if(state ==0){
                gImage.drawImage(List.bg,0,0,null);
                gImage.drawImage(List.sun,250,400,null);
                gImage.drawImage(List.p1,450,450,null);
                gImage.setColor(Color.GREEN);
                gImage.setFont(new Font("宋体",Font.BOLD,100));
                gImage.drawString("飞机大战",150,280);
                gImage.setColor(Color.ORANGE);
                gImage.drawString("点击进入游戏",300,400);
            }

            if (state ==1) {
                //游戏时间
                int time_s, time_min=0;
                time_s = time/40;
                while (true){
                    if(time_s >= 60){
                        time_s -=60;
                        time_min++;
                    }else break;
                }
                //游戏数据集合里遍历并绘制实体
                for (GameObj obj : List.gameObjs) {
                    if (gImage != null) {
                        obj.paintSelf(gImage);
                        //游戏界面外实体删除，减少性能浪费。
                        if (obj.getY() > 4000 || obj.getY() < -4000) {
                            List.Delete_Obj.add(obj);
                        }
                    }
                }

                List.gameObjs.removeAll(List.Delete_Obj);
                List.EnemyObjs.removeAll(List.Delete_Obj);

                //时长,分值,血量显示
                assert gImage != null;
                gImage.setColor(Color.ORANGE);
                gImage.setFont(new Font("宋体",Font.BOLD,20));
                gImage.drawString("时长: " + time_min + "分" + time_s +"秒", 810, 750);

                gImage.setColor(Color.red);
                gImage.setFont(new Font("宋体",Font.BOLD,25));
                gImage.drawString("得分: " + score, 760, 55);

                gImage.drawImage(List.heart,30,60,null);
                gImage.setColor(Color.orange);
                gImage.setFont(new Font("宋体",Font.BOLD,50));
                gImage.drawString(String.valueOf(HP), 100, 115);
            }

            if(state ==2){
                //游戏失败界面
                assert gImage != null;
                gImage.setColor(Color.RED);
                gImage.setFont(new Font("宋体",Font.BOLD,100));
                gImage.drawString("你输了！",300,300);
                gImage.setFont(new Font("宋体",Font.BOLD,60));
                gImage.setColor(Color.MAGENTA);
                gImage.drawString("最终分数：" + score ,150,400);
                gImage.setColor(Color.WHITE);
                gImage.setFont(new Font("宋体",Font.BOLD,50));
                gImage.drawString("点击屏幕回到主页！",300,540);
            }

            //双缓存技术-3
            g.drawImage(offScreen,0,0,null);
        }
    }

    void createShellObj(){
        //生成我方子弹实体
        List.gameObjs.add(new MyShellObj(List.shell, plane.getX() + 26, plane.getY() - 10,16,16, 15,this ));
    }

    public static void summon_bullet(){
        for (GameObj obj : List.EnemyObjs) {
            List.gameObjs.add(new EnemyShellObj(List.shell2, obj.getX() + 20, obj.getY() + 30,16,16, -5, obj.getGame()));
        }
    }

    void createEnemyObj(){
        //生成敌机实体
        Random random = new Random();
        int id = random.nextInt(7)  + 2;
        switch (id) {
            case 2 -> List.gameObjs.add(new EnemyObj(List.p2, random.nextInt(900) + 50, 0, 64, 64, -2.4, this));
            case 3 -> List.gameObjs.add(new EnemyObj(List.p3, random.nextInt(900) + 50, 0, 64, 64, -3, this));
            case 4 -> List.gameObjs.add(new EnemyObj(List.p4, random.nextInt(900) + 50, 0, 64, 64, -2.5, this));
            case 5 -> List.gameObjs.add(new EnemyObj(List.p5, random.nextInt(900) + 50, 0, 64, 64, -3.1, this));
            case 6 -> List.gameObjs.add(new EnemyObj(List.p6, random.nextInt(900) + 50, 0, 64, 64, -2.2, this));
            case 7 -> List.gameObjs.add(new EnemyObj(List.p7, random.nextInt(900) + 50, 0, 64, 64, -3, this));
            case 8 -> List.gameObjs.add(new EnemyObj(List.p8, random.nextInt(900) + 50, 0, 64, 64, -2.8, this));
        }
        List.EnemyObjs.add(List.gameObjs.get(List.gameObjs.size()-1));
    }
}