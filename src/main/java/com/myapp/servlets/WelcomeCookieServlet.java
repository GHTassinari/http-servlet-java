package com.myapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class WelcomeCookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String COOKIE_NAME = "guestId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        String guest = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (COOKIE_NAME.equals(c.getName())) {
                    guest = c.getValue();
                    break;
                }
            }
        }

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Welcome</title></head><body>");
        if (guest != null) {
            out.printf("<h1>Bem-vindo de volta, %s!</h1>%n", guest);
        } else {
            String novoId = "Visitante" + System.currentTimeMillis();
            Cookie cookie = new Cookie(COOKIE_NAME, novoId);
            cookie.setMaxAge(24 * 60 * 60); // 1 dia
            resp.addCookie(cookie);
            out.printf("<h1>Olá, novo usuário! Seu ID gerado: %s</h1>%n", novoId);
        }
        out.println("</body></html>");
    }
}
