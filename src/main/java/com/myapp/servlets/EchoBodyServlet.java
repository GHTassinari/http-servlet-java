package com.myapp.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/echo-body")
public class EchoBodyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        echoBody(req, resp, "POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        echoBody(req, resp, "PUT");
    }

    private void echoBody(HttpServletRequest req, HttpServletResponse resp, String method)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line).append("\n");
            }
        }

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Echo " + method + "</title></head><body>");
        out.printf("<h1>Corpo da requisição via %s</h1>%n", method);
        out.printf("<pre>%s</pre>%n", body.toString());
        out.println("</body></html>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        if (id != null) {
            out.printf("Recurso com id=%s removido com sucesso.%n", id);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Parâmetro 'id' ausente. Não foi possível remover.");
        }
    }
}
