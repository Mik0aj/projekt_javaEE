package com.strona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Parameters", urlPatterns = {"/Parameters"})
public class Parameters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String parametr1 = request.getParameter("parametr1");
        String parametr2 = request.getParameter("parametr2");
        String parametr3 = request.getParameter("parametr3");

        out.println("Wczytanie 3 parametrow z zadania :");
        out.println(parametr1);
        out.println(parametr2);
        out.println(parametr3);
    }
}
