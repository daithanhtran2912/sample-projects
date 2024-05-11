/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanh.armor.ArmorDTO;
import thanh.beans.ArmorBean;

/**
 *
 * @author T.Z.B
 */
public class LoadArmorDataController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";

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
            String key = request.getParameter("key");
            if (key.equals("LoadArmorList")) {
                ArmorBean beans = new ArmorBean();
                List<ArmorDTO> result = beans.getAllSuit();
                request.setAttribute("LIST_ARMOR", result);
                url = ADMIN;
            } else if (key.equals("AddNewArmor")) {
                url = ADMIN;
            } else if (key.equals("ArmorDetail")) {
                String txtSuitCode = request.getParameter("txtSuitCode");
                ArmorBean beans = new ArmorBean();
                beans.setSuitCode(txtSuitCode);
                ArmorDTO dto = beans.findSuitBySuitCode();
                request.setAttribute("ARMOR_INFO", dto);
                url = ADMIN;
            } else if (key.equals("UserArmorList")) {
                ArmorBean beans = new ArmorBean();
                List<ArmorDTO> listAvailableArmor = beans.getAvailableArmor();
                request.setAttribute("LIST_AVAIL_ARMOR", listAvailableArmor);
                url = USER;
            } else if (key.equals("UserArmorDetail")) {
                String txtSuitCode = request.getParameter("txtSuitCode");
                ArmorBean beans = new ArmorBean();
                beans.setSuitCode(txtSuitCode);
                ArmorDTO dto = beans.findSuitBySuitCode();
                request.setAttribute("SUIT_INFO", dto);
                boolean suitStatus = beans.getSuitStatus();
                request.setAttribute("SUIT_STATUS", suitStatus);
                url = USER;
            } else {
                request.setAttribute("ERROR", "Action does not support!");
            }
        } catch (Exception e) {
            log("ERROR at LoadArmorDataController" + e.getMessage());
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
