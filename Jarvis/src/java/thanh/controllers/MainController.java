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

/**
 *
 * @author T.Z.B
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOG_OUT = "LogOutController";
    private static final String LOAD = "LoadController";
    private static final String LOAD_MISSION_DATA = "LoadMissionDataController";
    private static final String LOAD_ARMOR_DATA = "LoadArmorDataController";
    private static final String FIND_MEMBER = "FindMemberController";
    private static final String REMOVE_MEMBER = "RemoveMemberController";
    private static final String UPDATE_MEMBER_PROFILE = "UpdateMemberController";
    private static final String CREATE_NEW_MEMBER = "CreateMemberController";
    private static final String FIND_MISSION = "FindMissionController";
    private static final String REMOVE_MISSION = "RemoveMissionController";
    private static final String CREATE_NEW_MISSION = "CreateMissionController";
    private static final String FIND_ARMOR = "FindArmorController";
    private static final String REMOVE_ARMOR = "RemoveArmorController";
    private static final String VIEW_PROFILE = "ViewProfileController";
    private static final String ADD_NEW_ARMOR = "AddNewArmorController";
    private static final String UPDATE_ARMOR = "UpdateArmorController";
    private static final String UPDATE_MISSION = "UpdateMissionController";
    private static final String LOAD_USER_DATA = "LoadUserDataController";
    private static final String PROCESS_SUIT = "ProcessSuitController";
    private static final String SUBMIT_MISSION = "SubmitMissionController";

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
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Log Out")) {
                url = LOG_OUT;
            } else if (action.equals("LoadData")) {
                url = LOAD;
            } else if (action.equals("Find Member")) {
                url = FIND_MEMBER;
            } else if (action.equals("Remove Member")) {
                url = REMOVE_MEMBER;
            } else if (action.equals("Update Member's Profile") || action.equals("Update Profile")) {
                url = UPDATE_MEMBER_PROFILE;
            } else if (action.equals("Create New Member")) {
                url = CREATE_NEW_MEMBER;
            } else if (action.equals("LoadMissionData")) {
                url = LOAD_MISSION_DATA;
            } else if (action.equals("Find Mission")) {
                url = FIND_MISSION;
            } else if (action.equals("Remove Mission")) {
                url = REMOVE_MISSION;
            } else if (action.equals("Create New Mission")) {
                url = CREATE_NEW_MISSION;
            } else if (action.equals("LoadArmorData")) {
                url = LOAD_ARMOR_DATA;
            } else if (action.equals("Find Armor")) {
                url = FIND_ARMOR;
            } else if (action.equals("Update Suit")) {
                url = UPDATE_ARMOR;
            } else if (action.equals("Remove Armor")) {
                url = REMOVE_ARMOR;
            } else if (action.equals("ViewProfile")) {
                url = VIEW_PROFILE;
            } else if (action.equals("Add New Armor")) {
                url = ADD_NEW_ARMOR;
            } else if (action.equals("Update Mission")) {
                url = UPDATE_MISSION;
            } else if (action.equals("Load User Data")) {
                url = LOAD_USER_DATA;
            } else if (action.equals("Return Suit") || action.equals("Take Suit")) {
                url = PROCESS_SUIT;
            } else if (action.equals("Submit Mission")) {
                url = SUBMIT_MISSION;
            } else {
                request.setAttribute("ERROR", "Action did not suppored!");
            }
        } catch (Exception e) {
            log("ERROR at MainController" + e.getMessage());
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
