package com.bridgelabz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Yagyata"),
                @WebInitParam (name = "password", value = "BridgeLabz")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request. getParameter ("pwd");

        //get servlet config init params
        String userID = getServletConfig().getInitParameter ("user");
        String password = getServletConfig ().getInitParameter ("password");

        if(userID.equals(user) && password.equals (pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong,</font>");
            rd.include(request, response);
        }
    }
}