package com.bridgelabz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request. getParameter ("pwd");

        PrintWriter out = response.getWriter ();

        if(user==null || !user.matches("^[A-Z][a-z]{2,}$")) {
            out.println("<font color=red>Invalid name (Name should start with a capital letter and have at least 3 characters)</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
            rd.include(request, response);
            return;
        }

        if (!isValidPassword(pwd)) {
            out.println("<font color='red'>Invalid password. It must have:<br>" +
                    "Minimum 8 characters<br>" +
                    "At least 1 uppercase letter<br>" +
                    "At least 1 digit<br>" +
                    "Exactly 1 special character</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
            rd.include(request, response);
            return;
        }

        // If both validations pass, login is successful
        request.setAttribute("user", user);
        request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
    }
    private boolean isValidPassword(String password) {
        if (password == null) return false;

        // Regex: At least 1 uppercase, 1 digit, 1 special char, minimum 8 chars
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";

        if (!password.matches(regex)) return false;

        // Extra check: Exactly 1 special character
        String specialChars = password.replaceAll("[a-zA-Z0-9]", "");
        return specialChars.length() == 1;
    }


//            String userID = getServletConfig().getInitParameter ("user");
//        String password = getServletConfig ().getInitParameter ("password");
//
//        if(userID.equals(user) && password.equals (pwd)) {
//            request.setAttribute("user", user);
//            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong,</font>");
//            rd.include(request, response);
//        }
}