/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanh.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanh.beans.MissionBean;
import thanh.mission.MissionDTO;

/**
 *
 * @author T.Z.B
 */
public class UpdateMissionController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "LoadMissionDataController";

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
            String txtMissionId = request.getParameter("txtMissionId");
            String txtMissionName = request.getParameter("txtMissionName");
            String txtDate = request.getParameter("txtDate");
            String txtLocate = request.getParameter("txtLocate");
            String txtDescription = request.getParameter("txtDescription");
            String[] status = request.getParameterValues("txtStatus");
            boolean txtStatus = false;
            if (status[0].equals("finished")) {
                txtStatus = true;
            } else if (status[0].equals("not yet")) {
                txtStatus = false;
            }
            String[] listMember = request.getParameterValues("txtMember");

            if (isDateValid(txtDate)) {
                MissionBean beans = new MissionBean();
                MissionDTO dto = new MissionDTO(txtMissionId, txtMissionName, txtDate, txtLocate, txtDescription, txtStatus);
                beans.setDto(dto);
                boolean success = beans.updateMissionById();
                if (success) {
                    //if list member = null, check the list from database to clear all members or to forward to jsp page *
                    //if list member != null, clear all member in Missions and reInsert member from the list
                    //* checking if list member in databse is null or not
                    //if null => forward to jsp page
                    //if not clear all members from Mission
                    beans.setMissionId(txtMissionId);
                    List<String> joiner = beans.getMemberOfAMission();
                    if (listMember == null) {
                        if (joiner.isEmpty()) {
                            request.setAttribute("STATUS", "Update successfully!");
                            url = ADMIN;
                        } else {
                            if (beans.removeAllMemberInMission()) {
                                request.setAttribute("STATUS", "Update successfully!");
                                url = ADMIN;
                            }
                        }
                    } else if (listMember.length > 0) {
                        if (joiner.isEmpty()) {
                            beans.setMembers(listMember);
                            if (beans.inputMemberToMission()) {
                                request.setAttribute("STATUS", "Update successfully!");
                                url = ADMIN;
                            }
                        } else {
                            if (beans.removeAllMemberInMission()) {
                                beans.setMembers(listMember);
                                if (beans.inputMemberToMission()) {
                                    request.setAttribute("STATUS", "Update successfully!");
                                    url = ADMIN;
                                }
                            }
                        }
                    }
                } else {
                    request.setAttribute("STATUS", "Update failed!");
                    url = ADMIN;
                }
            } else {
                request.setAttribute("INVALID_DATE", "Invalid type of date!");
                url = ADMIN;
            }
        } catch (Exception e) {
            log("ERROR at UpdateMissionController" + e.getMessage());
            request.setAttribute("ERROR", "Cannot update mission!");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
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
