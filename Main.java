package com.addressbook.control;

import java.util.Scanner;

/**
 * @author quan
 * date 2020-05-30
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("----------------------------------------\n        欢迎使用个人通讯录！ \n----------------------------------------\n");

        Main.start();
    }

    private static void start() {
//        初始化
        boolean status = false;
        String choice;
        Scanner sc = new Scanner(System.in);
        Control ctrl = new Control();

        ctrl.cmdList();
        while (!status) {
            System.out.print("请输入操作命令：");

            try {
                choice = sc.next();
            }
            catch(Exception e) {
                System.out.println("输入不合法！，请重新输入！");
                sc = new Scanner(System.in);  // 清空异常缓存
                continue;
            }

            switch (choice) {
                case "11":
                    ctrl.addContact();
                    break;
                case "12":
                    ctrl.modContact();
                    break;
                case "13":
                    ctrl.delContact();
                    break;
                case "14":
                    Control.seeContact();
                    break;
                case "15":
                    ctrl.finContact();
                    break;
                case "21":
                    ctrl.addGroup();
                    break;
                case "22":
                    ctrl.modGroup();
                    break;
                case "23":
                    ctrl.delGroup();
                    break;
                case "24":
                    Control.seeGroup();
                    break;
                case "25":
                    ctrl.finGroup();
                    break;
                case "exit":
                    status = true;
                    break;
                case "ls":
                    ctrl.cmdList();
                    break;
                default:
                    System.out.println("输入错误或改操作不存在，请重新输入！");
            }
        }
        System.out.println("\n退出成功！");
        sc.close();
    }
}


