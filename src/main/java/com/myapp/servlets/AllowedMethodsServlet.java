package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/allowed")
public class AllowedMethodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setHeader("Allow", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Métodos Suportados</title></head><body>");
        out.println("<h1>Métodos HTTP Disponíveis</h1>");
        out.println("<ul>");
        for (String m : resp.getHeader("Allow").split(",\\s*")) {
            out.printf("<li>%s</li>%n", m);
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        doOptions(req, resp);
    }
}
