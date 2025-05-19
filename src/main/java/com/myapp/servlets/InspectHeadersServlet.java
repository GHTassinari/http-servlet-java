package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inspect")
public class InspectHeadersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Inspect Headers</title></head><body>");
        out.println("<h1>Inspeção de Cabeçalhos da Requisição</h1>");
        out.println("<ul>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            out.printf("<li><strong>%s:</strong> %s</li>%n", name, value);
        }
        out.println("</ul>");
        out.printf("<p><strong>Endereço IP:</strong> %s</p>%n", request.getRemoteAddr());
        out.printf("<p><strong>Protocolo:</strong> %s</p>%n", request.getProtocol());
        out.println("</body></html>");
    }
}
