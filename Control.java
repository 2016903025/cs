package com.addressbook.control;


import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author quan
 * date 2020-05-30
 */
public class Control {
    static LinkedList<Contact> books = new LinkedList<Contact>();
    static LinkedList<Group> groups = new LinkedList<Group>();
    Scanner sc = new Scanner(System.in);

    Control() {
        groups.add(new Group(1, "默认"));
    }

    /*
    打印命令列表
     */
    public void cmdList() {
        System.out.println("\t------ 11: 添加联系人 ------");
        System.out.println("\t------ 12: 修改联系人 ------");
        System.out.println("\t------ 13: 删除联系人 ------");
        System.out.println("\t------ 14: 查看联系人 ------");
        System.out.println("\t------ 15: 查找联系人 ------\n");
        System.out.println("\t------ 21: 添加通讯组 ------");
        System.out.println("\t------ 22: 修改通讯组 ------");
        System.out.println("\t------ 23: 删除通讯组 ------");
        System.out.println("\t------ 24: 查看通讯组 ------");
        System.out.println("\t------ 25: 查找通讯组 ------\n");
        System.out.println("\t----- exit: 退出通讯录 -----");
        System.out.println("\t-----  ls: 查看操作命令 ----\n");
    }

    /*
    检查通讯组是否存在，如果已经存在就返回true否则返回false
     */
    public boolean checkGroupName(String groupName) {
        for (Object obj : groups) {
            Group groupItem = (Group) obj;
            if (groupItem.name.equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /*
    添加联系人
     */
    public void addContact() {

        System.out.print("请输入通讯组，回车则添加到默认分组：");
        String groupName;
        sc = new Scanner(System.in);
        groupName = sc.nextLine();
        if (groupName.equals("")) {
            groupName = "默认";
        } else if (!checkGroupName(groupName)) {
            System.out.println("该通讯组不存在，请先创建！");
            return;
        }
        boolean quit = true;
        while (true) {
            System.out.print("请输入联系人姓名、性别、职业、手机号、邮箱以逗号分隔，退出添加请输入0：");
            String message = sc.next();
            if (message.equals("0")) {
                return;
            }
            String[]  messageArr = message.split(",");
            boolean status = false;
            try {
                status = books.add(new Contact(books.size() + 1, messageArr[0], messageArr[1], messageArr[2], messageArr[3], messageArr[4], groupName));
            } catch (Exception e) {
                System.out.println("添加失败，请检查格式是否错误！");
                return;
            }
            if (status) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
        }

    }

    /*
    检查编号id方法，如果id存在就返回该对象，入轨id不存在就返回null
     */
    public Contact checkId() {
        int cid = -1;
        boolean found = false;
        Contact modCon = null;

        try {
            cid = sc.nextInt();
            if (cid == 0) return null;
        } catch (Exception e) {
            System.out.println("输入不合法！");
            return null;
        }

//        遍历判断联系人编号是否存在，如果存在就将found为true
        for (Object obj : books) {
            Contact item = (Contact)obj;
            if (item.id == cid) {
                found = true;
                modCon = item;
                break;
            }
        }
        if (found) {
            return modCon;
        } else {
            return null;
        }
    }

    /*
    修改联系人
     */
    public void modContact() {
        Control.seeContact();
        System.out.print("请输入需要修改的联系人编号：");
        Contact modCon = checkId();
        int choice;
        String modVal;

        if (modCon != null) {
            System.out.println("\t------ 1: 修改姓名 ------");
            System.out.println("\t------ 2: 修改性别 ------");
            System.out.println("\t------ 3: 修改职业 ------");
            System.out.println("\t------ 4: 修改手机号 ------");
            System.out.println("\t------ 5: 修改邮箱 ------\n");
            System.out.println("\t------ 0: 返回上一级 ------");
            System.out.print("请输入操作命令：");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("请输入合法的信息！");
                sc = new Scanner(System.in);
                return;
            }
            switch (choice) {
                case 1:
                    System.out.print("请输入需要修改的值：");
                    modVal = sc.next();
                    modCon.name = modVal;
                    break;
                case 2:
                    System.out.print("请输入需要修改的值：");
                    modVal = sc.next();
                    modCon.sex = modVal;
                    break;
                case 3:
                    System.out.print("请输入需要修改的值：");
                    modVal = sc.next();
                    modCon.job = modVal;
                    break;
                case 4:
                    System.out.print("请输入需要修改的值：");
                    modVal = sc.next();
                    modCon.phoneNumber = modVal;
                    break;
                case 5:
                    System.out.print("请输入需要修改的值：");
                    modVal = sc.next();
                    modCon.email = modVal;
                    break;
                default:
                    System.out.println("修改失败，该列名不存在！");
            }
            System.out.println("修改成功！");
        } else {
            System.out.println("联系人编号未找到，请检查！");
        }
    }

    /*
    删除联系人
     */
    public void delContact() {
        Control.seeContact();
        System.out.println("\t------ 0: 返回上一级 ------");
        System.out.print("请输入需要删除的联系人编号：");
        Contact modCon = checkId();

        if (modCon != null) {
            boolean status = books.remove(modCon);
            if (status) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        } else {
            System.out.println("联系人编号未找到，请检查！");
        }


    }

    /*
    查看并输出全部联系人
     */
    public static void seeContact() {
        for (Object obj : books) {
            Contact item = (Contact) obj;
            String echo = String.format("编号：%d \t 姓名：%s \t 性别：%s \t 职业：%s \t 手机号：%s \t 邮箱：%s \t 通讯组：%s",
                    item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
            System.out.println(echo);
        }
    }

    /*
    查找联系人
     */
    public void finContact() {
        Contact foundContact = null;  // 查找成功后的对象，如果没查到就是null
        int choice;     // 操作命令
        String finVal;  // 需要查找的值

        System.out.println("\t------ 1: 按姓名查找 ------");
        System.out.println("\t------ 2: 按性别查找 ------");
        System.out.println("\t------ 3: 按职业查找 ------");
        System.out.println("\t------ 4: 按手机号查找 ------");
        System.out.println("\t------ 5: 按邮箱查找 ------\n");
        System.out.println("\t------ 0: 返回上一级 ------");
        System.out.print("请输入操作命令：");

        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("请输入合法的信息！");
            sc = new Scanner(System.in);
            return;
        }

        switch (choice) {
            case 0:
                return;
            case 1:
                System.out.print("请输入需要查找的值，支持模糊查询：");
                finVal = sc.next();
                for (Object obj : books) {
                    Contact item = (Contact)obj;
                    if (item.name.contains(finVal)) {
                        String echo = String.format("编号：%d\t姓名：%s\t性别：%s\t职业：%s\t手机号：%s \t 邮箱：%s\t通讯组：%s",
                                item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
                        System.out.println(echo);
                    }
                }
                break;
            case 2:
                System.out.print("请输入需要查找的值：");
                finVal = sc.next();
                for (Object obj : books) {
                    Contact item = (Contact)obj;
                    if (item.sex.contains(finVal)) {
                        String echo = String.format("编号：%d\t姓名：%s\t性别：%s\t职业：%s\t手机号：%s \t 邮箱：%s\t通讯组：%s",
                                item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
                        System.out.println(echo);
                    }
                }
                break;
            case 3:
                System.out.print("请输入需要查找的值：");
                finVal = sc.next();
                for (Object obj : books) {
                    Contact item = (Contact)obj;
                    if (item.job.contains(finVal)) {
                        String echo = String.format("编号：%d\t姓名：%s\t性别：%s\t职业：%s\t手机号：%s \t 邮箱：%s\t通讯组：%s",
                                item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
                        System.out.println(echo);
                    }
                }
                break;
            case 4:
                System.out.print("请输入需要查找的值：");
                finVal = sc.next();
                for (Object obj : books) {
                    Contact item = (Contact)obj;
                    if (item.phoneNumber.contains(finVal)) {
                        String echo = String.format("编号：%d\t姓名：%s\t性别：%s\t职业：%s\t手机号：%s \t 邮箱：%s\t通讯组：%s",
                                item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
                        System.out.println(echo);
                    }
                }
                break;
            case 5:
                System.out.print("请输入需要查找的值：");
                finVal = sc.next();
                for (Object obj : books) {
                    Contact item = (Contact)obj;
                    if (item.email.contains(finVal)) {
                        String echo = String.format("编号：%d \t姓名：%s \t性别：%s \t职业：%s \t手机号：%s \t 邮箱：%s \t通讯组：%s",
                                item.id, item.name, item.sex, item.job, item.phoneNumber, item.email, item.group);
                        System.out.println(echo);
                    }
                }
                break;
            default:
                System.out.println("查无此人！");
        }

    }

    /*
    添加通讯组
     */
    public void addGroup() {
        System.out.println("\t------ 0: 返回上一级 ------");
        System.out.print("请输入需要添加的通讯组名：");

        String gName;
        boolean status = false;
        gName = sc.next();
        if (gName.equals("0")) return;

        if (checkGroupName(gName)) {
            System.out.println("该名称已存在，请更换其它通讯组名！");
            return;
        } else {
            status = groups.add(new Group(groups.size() + 1, gName));
        }

        if (status) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }
    }

    /*
    修改通讯组
     */
    public void modGroup() {
        Control.seeGroup();
        System.out.println("\t------ 0: 返回上一级 ------");
        System.out.print("请输入需要修改的通讯组编号：");

        int gid;
        String newName;

        try {
            gid = sc.nextInt();
            if (gid == 0) return;
        } catch (Exception e) {
            System.out.println("请输入合法的信息！");
            return;
        }

        for (Object obj : groups) {
            Group item = (Group)obj;
            if (item.id == gid) {
                String oldName = item.name;  // 重命名前的通讯组名
                System.out.print("重命名为：");
                newName = sc.next();        // 重命名后的通讯组名
                item.name = newName;
                // 修改联系人通讯组
                for (Object obj_it : books) {
                    Contact con_it = (Contact)obj_it;
                    if (con_it.group.equals(oldName)) {
                        con_it.group = newName;
                    }
                }
                System.out.println("修改成功！");
                return;
            }
        }
        System.out.println("查无此通讯组！");
    }

    /*
    删除通讯组
     */
    public void delGroup() {
        Control.seeGroup();
        System.out.println("\t------ 0: 返回上一级 ------");
        System.out.print("请输入需要删除的通讯组编号，同时会删除此分组联系人：");

        int gid;
        String gName;
        LinkedList<Contact> tempCon = new LinkedList<Contact>();  // 用来记录此分组的联系人编号

        try {
            gid = sc.nextInt();
            if (gid == 0) return;
        } catch (Exception e) {
            System.out.println("请输入合法的信息！");
            return;
        }

        for (Object obj : groups) {
            Group item = (Group)obj;
            if (item.id == gid) {
                gName = item.name;
                boolean status = groups.remove(item);
                if (status) {
                    // 删除此分组的联系人
                    for (Object obj_book : books) {
                        Contact item_book = (Contact)obj_book;
                        if (item_book.group.equals(gName)) {
                            tempCon.add(item_book);
                        }
                    }
                    for (Object obj_book : tempCon) {
                        Contact item_book = (Contact)obj_book;
                        books.remove(item_book);
                    }
                    System.out.println("删除成功！");
                } else {
                    System.out.println("删除失败！");
                }
                return;
            }
        }
        System.out.println("查无此通讯组！");
    }

    /*
    查看并输出全部通讯组
     */
    public static void seeGroup() {
        for (Object obj : groups) {
            Group item = (Group)obj;
            System.out.println(String.format("编号：%d\t通讯组：%s", item.id, item.name));
        }
    }

    /*
    查找通讯组
     */
    public void finGroup() {
        String searchMessage;
        Group foundGroup = null;
        try {
            System.out.println("\t------ 0: 返回上一级 ------");
            System.out.print("请输入需要查找的信息：");

            searchMessage = sc.next();
            if (searchMessage.equals("0")) return;
        } catch (Exception e) {
            System.out.println("请输入合法的数据！");
            return;
        }

        for (Object obj : groups) {
            Group item = (Group)obj;
            if (item.name.equals(searchMessage)) {
                foundGroup = item;
                break;
            }
        }

        if (foundGroup != null) {
            System.out.println(String.format("编号：%d\t通讯组：%s", foundGroup.id, foundGroup.name));
        } else {
            System.out.println("查无此通讯组！");
        }
    }
}
