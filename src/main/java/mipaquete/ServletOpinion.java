package mipaquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOpinion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = safe(request.getParameter("nombre"));
        String apellidos = safe(request.getParameter("apellidos"));
        String opinion = safe(request.getParameter("opinion"));
        String comentarios = safe(request.getParameter("comentarios"));

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Valores recogidos en el formulario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Valores recogidos del formulario</h1>");
            out.println("<p><b>Nombre:</b> " + escapeHtml(nombre) + "</p>");
            out.println("<p><b>Apellidos:</b> " + escapeHtml(apellidos) + "</p>");
            out.println("<p><b>Opinion:</b> " + escapeHtml(opinion) + "</p>");
            out.println("<p><b>Comentarios:</b> " + escapeHtml(comentarios) + "</p>");
            out.println("<p><a href=\"index.jsp\">Volver al formulario</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Este servlet lee los datos de un formulario y los muestra en pantalla";
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private String escapeHtml(String value) {
        String escaped = value;
        escaped = escaped.replace("&", "&amp;");
        escaped = escaped.replace("<", "&lt;");
        escaped = escaped.replace(">", "&gt;");
        escaped = escaped.replace("\"", "&quot;");
        return escaped.replace("'", "&#39;");
    }
}
