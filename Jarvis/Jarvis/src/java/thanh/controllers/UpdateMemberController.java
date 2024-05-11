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
import javax.servlet.http.HttpSession;
import thanh.beans.RegistrationBean;
import thanh.registration.RegistrationDTO;

/**
 *
 * @author T.Z.B
 */
public class UpdateMemberController extends HttpServlet {

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
            String txtUsername = request.getParameter("txtUsername");
            String txtPassword = request.getParameter("txtPassword");
            String txtFirstname = request.getParameter("txtFirstname");
            String txtLastname = request.getParameter("txtLastname");
            String txtEmail = request.getParameter("txtEmail");
            String[] radioRole = request.getParameterValues("txtRole");
            String txtImageURL = request.getParameter("txtImageURL");
            String txtRole = radioRole[0];

            RegistrationBean beans = new RegistrationBean();
            beans.setUsername(txtUsername);
            RegistrationDTO dto = beans.findMemberByUsername();
            if (txtPassword.equals("")) {
                txtPassword = dto.getPassword();
                dto.setPassword(txtPassword);
            } else {
                dto.setPassword(txtPassword);
            }
            if (txtImageURL.equals("")) {
                txtImageURL = dto.getimageURL();
                dto.setimageURL(txtImageURL);
            } else {
                dto.setimageURL(txtImageURL);
            }
            dto.setFirstname(txtFirstname);
            dto.setLastname(txtLastname);
            dto.setEmail(txtEmail);
            dto.setRole(txtRole);
            beans.setDto(dto);
            boolean check = beans.updateMemberDetail();
            String key = request.getParameter("key");
            System.out.println(key);
            if (key.equals("ViewMemberDetail")) {
                if (check) {
                    request.setAttribute("MEMBER_DETAIL", dto);
                    request.setAttribute("STATUS", "Update successfully!");
                    url = ADMIN;
                } else {
                    request.setAttribute("STATUS", "Update user failed!");
                    url = ADMIN;
                }
            } else if (key.equals("ViewAdminProfile")) {
                if (check) {
                    HttpSession session = request.getSession();
                    session.setAttribute("ADMIN_INFO", dto);
                    request.setAttribute("STATUS", "Update successfully!");
                    url = ADMIN;
                } else {
                    request.setAttribute("STATUS", "Update failed!");
                    url = ADMIN;
                }
            } else if (key.equals("viewUserProfile")) {
                if (check) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", dto);
                    request.setAttribute("STATUS", "Update successfully!");
                    url = USER;
                } else {
                    request.setAttribute("STATUS", "Update user failed!");
                    url = USER;
                }
            }
        } catch (Exception e) {
            log("ERROR at UpdateMemberController" + e.getMessage());
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
