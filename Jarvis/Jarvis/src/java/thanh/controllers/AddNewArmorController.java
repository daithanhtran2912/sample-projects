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
public class AddNewArmorController extends HttpServlet {

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
            boolean isAvailable = true;
            
            if (txtImageURL.equals("")) {
                txtImageURL = "image/Iron_Man_3_Concept_Art_by_Andy_Park_06.jpg";
            }

            ArmorDTO dto = new ArmorDTO(txtSuitCode, txtSuitName, txtMaterial, txtWeapon, txtImageURL, isAvailable);
            ArmorBean beans = new ArmorBean();
            beans.setDto(dto);
            boolean check = beans.insertNewArmor();
            beans.setSuitCode(txtSuitCode);
            beans.setUsername("ironman");
            beans.setStatus(false);
            boolean insertToHall = beans.insertSuitToHallOfArmor();
            if (check && insertToHall) {
                request.setAttribute("STATUS", "Insert successfully!");
                url = ADMIN;
            } else {
                request.setAttribute("STATUS", "Insert failed!");
                url = ADMIN;
            }
        } catch (Exception e) {
            log("ERROR at AddNewArmorController" + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute("DUPLICATE_CODE", "Dupliacte Suit Code!");
                url = ADMIN;
            }
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
