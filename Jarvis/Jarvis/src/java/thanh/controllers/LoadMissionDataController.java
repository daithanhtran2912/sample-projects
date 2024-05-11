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
import javax.servlet.http.HttpSession;
import thanh.beans.MissionBean;
import thanh.beans.RegistrationBean;
import thanh.mission.MissionDTO;

/**
 *
 * @author T.Z.B
 */
public class LoadMissionDataController extends HttpServlet {

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
            if (key.equals("LoadMissionList")) {
                MissionBean beans = new MissionBean();
                List<MissionDTO> result = beans.getAllMission();
                request.setAttribute("LIST_MISSION", result);
                url = ADMIN;
            } else if (key.equals("Add New Mission")) {
                RegistrationBean beans = new RegistrationBean();
                List<String> allMember = beans.getAllUsername();
                HttpSession session = request.getSession();
                session.setAttribute("ALL_MEMBER", allMember);
                url = ADMIN;
            } else if (key.equals("ViewMissionDetail")) {
                String txtMissionId = request.getParameter("txtMissionId");
                MissionBean beans = new MissionBean();
                beans.setMissionId(txtMissionId);
                MissionDTO dto = beans.getMissionDetail();
                request.setAttribute("MISSION_INFO", dto);

                List<String> listMember = beans.getMemberOfAMission();
                request.setAttribute("LIST_MEMBER", listMember);

                RegistrationBean regBean = new RegistrationBean();
                List<String> allMember = regBean.getAllUsername();
                HttpSession session = request.getSession();
                session.setAttribute("ALL_MEMBER", allMember);
                url = ADMIN;
            } else if (key.equals("UserLoadMissionDetail")) {
                String txtMissionId = request.getParameter("txtMissionId");
                MissionBean beans = new MissionBean();
                beans.setMissionId(txtMissionId);
                MissionDTO dto = beans.getMissionDetail();
                request.setAttribute("MISSION_INFO", dto);
                
                List<String> listMember = beans.getMemberOfAMission();
                request.setAttribute("LIST_MEMBER", listMember);
                
                RegistrationBean regBean = new RegistrationBean();
                List<String> allMember = regBean.getAllUsername();
                HttpSession session = request.getSession();
                session.setAttribute("ALL_MEMBER", allMember);
                
                String status = request.getParameter("txtMissionStatus");
                request.setAttribute("MISSION_STATUS", status);
                url = USER;
            } else {
                request.setAttribute("ERROR", "Action does not support!");
            }
        } catch (Exception e) {
            log("ERROR at LoadMissionDataController" + e.getMessage());
            request.setAttribute("ERROR", "Cannot load mission list!");
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
