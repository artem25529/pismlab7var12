package com.es.servlets;

import com.es.jms.Producer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mdb")
public class MdbServlet extends HttpServlet {
    @EJB
    protected Producer producer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        producer.sendJmsMessage(req.getParameter("msg"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
