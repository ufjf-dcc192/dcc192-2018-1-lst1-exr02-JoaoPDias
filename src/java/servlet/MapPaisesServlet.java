/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
@WebServlet(name = "MapPaisesServlet", urlPatterns = {"/mapa.html"})
public class MapPaisesServlet extends HttpServlet {

    Map<String, String> paises;

    public MapPaisesServlet() {
        paises = new HashMap<>();
        paises.put("Brasil", "Amarelo");
        paises.put("Alemanha", "Amarelo");
        paises.put("Suíça", "Vermelho");
        paises.put("Japão", "Vermelho");
        paises.put("Finlândia", "Azul");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Países/Cores</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Países/Cores</h1>");
            out.println("<dl>");
            String ordenacao = request.getParameter("ordenacao");
            switch (ordenacao) {
                case "paises":
                    for (Map.Entry<String, String> pais : paises.entrySet()) {
                        out.println("<dt>" + pais.getKey() + "</dt>");
                        out.println("<dd>" + pais.getValue() + "</dd>");
                    }
                    break;
                case "cores":
                    Map<String, ArrayList<String>> cores = new HashMap<>();
                    for (Map.Entry<String, String> pais : paises.entrySet()) {
                        if (!cores.containsKey(pais.getValue())) {
                            ArrayList<String> colecao = new ArrayList();
                            colecao.add(pais.getKey());
                            cores.put(pais.getValue(), colecao);
                        } else {
                            cores.get(pais.getValue()).add(pais.getKey());
                        };

                    }
                    for (Map.Entry<String, ArrayList<String>> cor : cores.entrySet()) {
                        out.println("<dt>" + cor.getKey() + "</dt>");
                        for (String paisao : cor.getValue()) {
                            out.println("<dd>" + paisao + "</dd>");
                        }
                    }
            }
            out.println("<a href='mapa.html?ordenacao=paises'>Por País</a> /");
            out.println("<a href='mapa.html?ordenacao=cores'>Por Cor</a>)</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
