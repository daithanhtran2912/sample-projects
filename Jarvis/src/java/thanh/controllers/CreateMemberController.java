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
import thanh.beans.RegistrationBean;
import thanh.registration.RegistrationDTO;

/**
 *
 * @author T.Z.B
 */
public class CreateMemberController extends HttpServlet {

    private static final String LOAD = "LoadController";

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
        String url = LOAD;
        try {
            String txtUsername = request.getParameter("txtUsername");
            String txtPassword = request.getParameter("txtPassword");
            String txtFirstname = request.getParameter("txtFirstname");
            String txtLastname = request.getParameter("txtLastname");
            String txtEmail = request.getParameter("txtEmail");
            String[] radioRole = request.getParameterValues("txtRole");
            String txtImageURL = request.getParameter("txtImageURL");
            boolean permission = true;
            String txtRole = null;

            if (txtImageURL.equals("")) {
                txtImageURL = "image/avengers_trilogy_poster_by_tclarke597-dbv7gy2.png";
            }
            if (radioRole[0].equals("user")) {
                txtRole = radioRole[0];
            } else if (radioRole[0].equals("admin")) {
                txtRole = radioRole[0];
            }

            RegistrationBean beans = new RegistrationBean();
            RegistrationDTO dto = new RegistrationDTO(txtUsername, txtPassword, txtFirstname, txtLastname, txtEmail, txtRole, txtImageURL, permission);
            beans.setDto(dto);
            boolean check = beans.createNewMember();
            if (check) {
                request.setAttribute("STATUS", "Insert successfully!");
                url = LOAD;
            } else {
                request.setAttribute("STATUS", "Insert failed!");
                url = LOAD;
            }
        } catch (Exception e) {
            log("ERROR at CreateMemberController" + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute("DUPLICATE_CODE", "Duplicate Username!");
                url = LOAD;
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
