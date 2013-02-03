package com.in6k.jsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

import com.in6k.user.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FormValidator extends HttpServlet {
    private String user_name;
    private String second_name;
    private String email;
    private String password;
    private String password_confirm;
    private String birthday;

    private String errors;

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user_name = request.getParameter("user_name");
        second_name = request.getParameter("second_name");
        email = request.getParameter("email");
        password = request.getParameter("password");
        password_confirm = request.getParameter("confirm");
        birthday = request.getParameter("birthday");

        errors = validator();

        if(errors == "") {
            User user = new User(user_name, second_name, email, password,birthday);
            logger.info(user.getName());
            writeUserObjectToXmlFile(user, "/home/alexandr/test/"+user_name+".xml");
        }

        request.setAttribute("errors",errors);
        request.setAttribute("user_name",user_name);
        request.setAttribute("second_name",second_name);
        request.setAttribute("email",email);
        request.setAttribute("birthday",birthday);

        request.getRequestDispatcher("index.jsp").include(request,response);
    }

    private boolean checkUserName(String userName) {
        boolean userNameNotEmpty = userName != "";
        boolean userNameNotShort = userName.length() > 2;

        return (userNameNotEmpty && userNameNotShort) ? true : false;
    }

    private boolean checkPassword(String password) {
        boolean passwordNotNull = password != "";
        boolean passwordNotShort = password.length() > 1;

        return (passwordNotNull && passwordNotShort) ? true : false;
    }

    private boolean validatePassword(String password, String confirm) {
        return (password.equals(confirm)) ? true : false;
    }

    private String validator() {
        String result = "";

        if(!checkUserName(user_name)) {
            result += "User name is not valid!" + "</br>";
            user_name = "";
        }

        if(!checkUserName(second_name)) {
            result += "User second name is not valid!" + "</br>";
            second_name = "";
        }

        if(!checkPassword(password)) {
            result += "Password is not valid!" + "</br>";
            password = "";
        }
        else {
            if (!validatePassword(password, password_confirm)) {
                result += "Confirm the password!" + "</br>";
                password = "";
                password_confirm = "";
            }
        }
        return result;
    }

    private void writeUserObjectToXmlFile(User user, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            try {
                XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
                try {
                    xmlEncoder.writeObject(user);
                    xmlEncoder.flush();
                } finally {
                    xmlEncoder.close();
                }
            } finally {
                fileOutputStream.close();
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}