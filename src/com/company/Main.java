package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static String[][] map=new String[10][10];
    public static mapEditor playerPos=new mapEditor();
    public static mapEditor boxPos = new mapEditor();
    private static Scanner inputManager=new Scanner(System.in);

    public static void main(String[] args)
    {
        loadLevel_1();
        mainloop();
    }

    public static void mainloop()
    {
        mapEditor.printMap();
        System.out.println("输入W向上移动，S向下移动，A向左移动，D向右移动");
        String info=inputManager.next();
        if (mapEditor.movement(info))
        {
            System.out.println("恭喜你！赢得了游戏！");
        }
        else
        {
            mainloop();
        }
    }

    public static void loadLevel_1()
    {
        mapEditor.initwall(true);
        List<mapEditor> list=new ArrayList<>();
        playerPos.posX = 1; playerPos.posY = 1; boxPos.posY = 2; boxPos.posX = 2;
        list.add(new mapEditor(1,1)); list.add(new mapEditor(2,2)); list.add(new mapEditor(8,5));
        list.add(new mapEditor(1,3)); list.add(new mapEditor(2,3)); list.add(new mapEditor(3,3));
        list.add(new mapEditor(2,5)); list.add(new mapEditor(3,5)); list.add(new mapEditor(3,6));
        list.add(new mapEditor(3,8)); list.add(new mapEditor(4,8)); list.add(new mapEditor(5,4));
        list.add(new mapEditor(5,5)); list.add(new mapEditor(5,6)); list.add(new mapEditor(6,4));
        String[] info =new String[]{"&","o","*",
        "H","H","H",
        "H","H","H",
        "H","H","H",
        "H","H","H"};
        mapEditor.EditMap(list,info);
    }

    private static class mapEditor
    {
        public int posX;
        public int posY;

        public mapEditor()
        {
            posX = 0;
            posY = 0;
        }

        public mapEditor(int y,int x)
        {
            posY = y;
            posX = x;
        }

        public static void initwall(boolean clear)
        {
            for (int x =0;x<10;x++)
            {
                if (x == 0 || x == 9)
                {
                    for (int i=0;i<10;i++)
                    {
                        map[x][i] = "H";
                    }
                }
                else
                {
                    map[x][0] = "H";
                    map[x][9] = "H";
                }
            }
            if (clear)
            {
                for (int x=1;x<9;x++)
                {
                    for (int i=1;i<9;i++)
                    {
                        map[x][i] = " ";
                    }
                }
            }
        }

        public static void EditMap(List<mapEditor> vector2, String[] info)
        {
            for (int x=0;x<info.length;x++)
            {
                Main.map[vector2.get(x).posY][vector2.get(x).posX] = info[x];
            }
        }

        public static void printMap()
        {
            String printInfo="";
            for (int x=0;x<10;x++)
            {
                for (int i=0;i<10;i++)
                {
                    printInfo += Main.map[x][i];
                    printInfo += " ";
                }
                printInfo += "\n";
            }
            System.out.println(printInfo);
        }

        public static boolean movement(String dir)
        {
            switch (dir)
            {
                case "W":
                {
                    if (Main.map[Main.playerPos.posY - 1][Main.playerPos.posX].equals(" "))
                    {
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                        Main.playerPos.posY -= 1;
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                    }
                    else if (Main.map[Main.playerPos.posY - 1][Main.playerPos.posX].equals("o"))
                    {
                        if (Main.map[Main.boxPos.posY - 1][Main.boxPos.posX].equals("*"))
                        {
                            return true;
                        }
                        else if (Main.map[Main.boxPos.posY - 1][Main.boxPos.posX].equals("H"))
                        {
                            return false;
                        }
                        else
                        {
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = " ";
                            Main.boxPos.posY -= 1;
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = "o";
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                            Main.playerPos.posY -= 1;
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                        }
                    }
                    break;
                }
                case "S":
                {
                    if (Main.map[Main.playerPos.posY + 1][Main.playerPos.posX].equals(" "))
                    {
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                        Main.playerPos.posY += 1;
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                    }
                    else if (Main.map[Main.playerPos.posY + 1][Main.playerPos.posX].equals("o"))
                    {
                        if (Main.map[Main.boxPos.posY + 1][Main.boxPos.posX].equals("*"))
                        {
                            return true;
                        }
                        else if (Main.map[Main.boxPos.posY + 1][Main.boxPos.posX].equals("H"))
                        {
                            return false;
                        }
                        else
                        {
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = " ";
                            Main.boxPos.posY += 1;
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = "o";
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                            Main.playerPos.posY += 1;
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                        }
                    }
                    break;
                }
                case "A":
                {
                    if (Main.map[Main.playerPos.posY][Main.playerPos.posX - 1].equals(" "))
                    {
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                        Main.playerPos.posX -= 1;
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                    }
                    else if (Main.map[Main.playerPos.posY][Main.playerPos.posX - 1].equals("o"))
                    {
                        if (Main.map[Main.boxPos.posY][Main.boxPos.posX - 1].equals("*"))
                        {
                            return true;
                        }
                        else if (Main.map[Main.boxPos.posY][Main.boxPos.posX - 1].equals("H"))
                        {
                            return false;
                        }
                        else
                        {
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = " ";
                            Main.boxPos.posX -= 1;
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = "o";
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                            Main.playerPos.posX -= 1;
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                        }
                    }
                    break;
                }
                case "D":
                {
                    if (Main.map[Main.playerPos.posY][Main.playerPos.posX + 1].equals(" "))
                    {
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                        Main.playerPos.posX += 1;
                        Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                    }
                    else if (Main.map[Main.playerPos.posY][Main.playerPos.posX + 1].equals("o"))
                    {
                        if (Main.map[Main.boxPos.posY][Main.boxPos.posX + 1].equals("*"))
                        {
                            return true;
                        }
                        else if (Main.map[Main.boxPos.posY][Main.boxPos.posX + 1].equals("H"))
                        {
                            return false;
                        }
                        else
                        {
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = " ";
                            Main.boxPos.posX += 1;
                            Main.map[Main.boxPos.posY][Main.boxPos.posX] = "o";
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = " ";
                            Main.playerPos.posX += 1;
                            Main.map[Main.playerPos.posY][Main.playerPos.posX] = "&";
                        }
                    }
                    break;
                }
            }
            return false;
        }
    }
}


