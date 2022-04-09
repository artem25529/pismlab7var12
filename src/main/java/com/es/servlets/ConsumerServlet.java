package com.es.servlets;

import com.es.jms.Consumer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/consumer")
public class ConsumerServlet extends HttpServlet {
    @EJB
    protected Consumer consumer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = consumer.receiveMessage();
        if (message == null) {
            req.setAttribute("error", "connection stopped cause of timeout");
        } else {
            req.setAttribute("res", message);
        }
        req.getRequestDispatcher("/consume.jsp").forward(req, resp);
    }
}
