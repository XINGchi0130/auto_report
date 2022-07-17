package core.Servlet;

import core.Person.Person;
import core.PostgreSQL.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    public Servlet() {
        super();
    }



    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得页面数据
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Person person = new Person(username, email, password);
//        System.out.println(person.getUsername());
//        System.out.println(person.getEmail());
//        System.out.println(person.getPassword());

        if(username != "" && email != "" && password != ""){
            //构造Util
            Util util = new Util("",
                    "",
                    "",
                    "",
                    "");

            //判断是否已经有该user了,有就返回true
            if(checkIs(person,util) && checkQQEmail(person.getEmail())){
                //更新email和passwd
                String sql =  "update name_list set" +
                        " email = " + "'" + person.getEmail() + "'," +
                        " passwd = " + "'" + person.getPassword() + "'" +
                        " where nums = " + "'" + person.getUsername() + "'";
                util.Insert(sql);
                JOptionPane.showMessageDialog(null, "数据更新成功");
//            System.out.println("数据更新成功");
            } else {
                //添加新user
                //判断输入是否合法
                if(checkQQEmail(person.getEmail()) && checkId(person.getUsername())){
                    String sql = "insert into name_list (nums,passwd,email)" +
                            "VALUES ('" + person.getUsername() + "' , '" + person.getPassword() + "' , '" + person.getEmail() + "')";
                    util.Insert(sql);
                    JOptionPane.showMessageDialog(null, "数据上传成功");
//                System.out.println("数据上传成功");
                }
                else {
                    JOptionPane.showMessageDialog(null, "学号或者QQ邮箱输入非法","error",JOptionPane.ERROR_MESSAGE);
//                System.out.println("学号或者QQ邮箱输入非法");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "请输入信息","error",JOptionPane.ERROR_MESSAGE);
        }
    }



    public static boolean checkQQEmail(String s) {
        if (s == ""){
//            System.out.println("email = ''");
            return false;
        }

        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(s);

        if (matcher.matches() && checkQQEmailEnd(s)) {
//            System.out.println("邮箱输入合法");
            return true;
        } else {
//            System.out.println("邮箱输入非法");
            return false;
        }
    }

    public static boolean checkQQEmailEnd(String s){
        String x = s.substring(s.length() - 7);
//        System.out.println(x);
        if (x.equals("@qq.com")){
//            System.out.println("QQ邮箱后是@qq.com");
            return true;
        }
        return false;
    }

    public static boolean checkId(String s){
        if(s == ""){
//            System.out.println("username = ''");
            return false;
        }

        int x = Integer.parseInt(s.substring(0,4));
        int y = Integer.parseInt(s.substring(s.length() - 2));
        if(s.length() == 10 || s.length() == 12){
            if(x == 2022 || x == 2021 || x == 2020 || x == 2019 || x == 2018){
                if(y <= 60 && y > 0){
//                    System.out.println("学号输入合法");
                    return true;
                }
//                System.out.println("学号最后2位数非法");
                return false;
            }
//            System.out.println("学号前4位数非法");
            return false;
        } else {
//            System.out.println("学号长度非法");
            return false;
        }
    }

    public static boolean checkIs(Person person, Util util){
        String sql = "select * from name_list where nums = '" + person.getUsername() + "'";
        List<HashMap<String, Object>> list =  util.Select(sql);
        if(list.isEmpty()){
//            System.out.println("list是空的");
            return false;
        }else {
//            System.out.println("list不是空的");
            return true;
        }
    }
}