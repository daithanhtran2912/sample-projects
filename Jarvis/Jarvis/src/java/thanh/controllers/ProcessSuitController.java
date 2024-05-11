/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanh.beans.ArmorBean;

/**
 *
 * @author T.Z.B
 */
public class ProcessSuitController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String USER = "LoadArmorDataController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("Return Suit")) {
                boolean status = false;
                String txtSuitCode = request.getParameter("txtSuitCode");
                ArmorBean beans = new ArmorBean();
                beans.setStatus(status);
                beans.setSuitCode(txtSuitCode);
                boolean success = beans.updateSuitStatus();
                if (success) {
                    request.setAttribute("STATUS", "Retrun suit successfully!");
                    url = USER;
                } else {
                    request.setAttribute("STATUS", "Retrun suit failed!");
                    url = USER;
                }
            } else if (action.equals("Take Suit")) {
                boolean status = true;
                String txtSuitCode = request.getParameter("txtSuitCode");
                ArmorBean beans = new ArmorBean();
                beans.setStatus(status);
                beans.setSuitCode(txtSuitCode);
                boolean success = beans.updateSuitStatus();
                if (success) {
                    request.setAttribute("STATUS", "Take suit successfully!");
                    url = USER;
                } else {
                    request.setAttribute("STATUS", "Take suit failed!");
                    url = USER;
                }
            } else {
                request.setAttribute("ERROR", "Cannot afford to do this action!");
            }
        } catch (Exception e) {
            log("ERROR at ProcessSuitController" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
