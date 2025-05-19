package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/set-status")
public class DynamicStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String statusParam = req.getParameter("status");
        int code;
        try {
            code = Integer.parseInt(statusParam);
        } catch (Exception e) {
            code = HttpServletResponse.SC_BAD_REQUEST;
        }

        resp.setStatus(code);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Status %d</title></head><body>", code);
        out.printf("<h1>Resposta HTTP: %d %s</h1>%n", code, resp.getStatus());
        out.println("</body></html>");
    }
}
