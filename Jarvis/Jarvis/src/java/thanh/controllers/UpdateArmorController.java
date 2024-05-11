/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.controllers;

import java.io.IOException;
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
public class UpdateArmorController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "admin.jsp";

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
            String txtSuitCode = request.getParameter("txtSuitCode");
            String txtSuitName = request.getParameter("txtSuitName");
            String txtMaterial = request.getParameter("txtMaterial");
            String txtWeapon = request.getParameter("txtWeapon");
            String txtImageURL = request.getParameter("txtImageURL");
            String[] status = request.getParameterValues("txtStatus");
            boolean available = false;
            if (status[0].equals("online")) {
                available = true;
            } else if (status[0].equals("offline")) {
                available = false;
            }
            ArmorBean beans = new ArmorBean();
            if (txtImageURL.equals("")) {
                beans.setSuitCode(txtSuitCode);
                ArmorDTO dto = beans.findSuitBySuitCode();
                txtImageURL = dto.getImageURL();
            }

            ArmorDTO dto = new ArmorDTO(txtSuitCode, txtSuitName, txtMaterial, txtWeapon, txtImageURL, available);
            beans.setDto(dto);
            boolean check = beans.updateSuitBySuitCode();
            if (check) {
                request.setAttribute("ARMOR_INFO", dto);
                request.setAttribute("STATUS", "Update successfully!");
                url = ADMIN;
            } else {
                request.setAttribute("STATUS", "Update failed!");
                url = ADMIN;
            }
        } catch (Exception e) {
            log("ERROR at UpdateArmorController" + e.getMessage());
            request.setAttribute("ERROR", "Cannot update suit!");
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
