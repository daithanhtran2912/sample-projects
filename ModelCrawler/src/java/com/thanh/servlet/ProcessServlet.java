/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanh.servlet;

import com.thanh.dao.CategoryDAO;
import com.thanh.dao.ProductDAO;
import com.thanh.dto.ProductDTO;
import com.thanh.dto.ProductListDTO;
import com.thanh.entity.Category;
import com.thanh.utils.JAXBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

/**
 *
 * @author T.Z.B
 */
public class ProcessServlet extends HttpServlet {

    private static final String INVALID = "invalid.html";
    private static final String HOME_PAGE = "home.jsp";
    private static final String SEARCH_PAGE = "search.jsp";

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
        PrintWriter out = response.getWriter();

        String btnAction = request.getParameter("btnAction");
        String url = HOME_PAGE;

        HttpSession session = request.getSession();

        try {

            if (session.getAttribute("SEARCHRESULT") == null) {
                ProductListDTO dto = ProductDAO.getInstance().getSessionProduct();
                String listProductXml = JAXBUtils.marshallProductList(dto);
                session.setAttribute("SEARCHRESULT", listProductXml);
            }

            if (btnAction == null || btnAction.equals("")) {
                ProductDAO dao = ProductDAO.getInstance();
                CategoryDAO catDao = CategoryDAO.getInstance();
                List<Category> categories = catDao.getAll();

                List<String> listTopProduct = new ArrayList<>();
                for (Category category : categories) {

                    List<ProductDTO> products = dao.getListTopProduct(category.getCategory());
                    if (products != null && products.size() > 0) {
                        ProductListDTO listProduct = new ProductListDTO();
                        listProduct.setListProduct(products);
                        listProduct.setCategory(category.getCategory());

                        try {
                            // marshall listProduct
                            String listProductXml = JAXBUtils.marshallProductList(listProduct);
                            url = HOME_PAGE;
                            listTopProduct.add(listProductXml);
                        } catch (JAXBException ex) {
                            Logger.getLogger(ProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                session.setAttribute("PRODUCT", listTopProduct);
            } else if (btnAction.equals("Advance Search")) {

                String txtSearchValue = request.getParameter("txtSearchValue");
                ProductDAO dao = ProductDAO.getInstance();
                ProductListDTO listResult = dao.getProductLikeByNameOrManufacturerOrScale(txtSearchValue);
                String listResultXml = JAXBUtils.marshallProductList(listResult);
                System.out.println(listResultXml);
                request.setAttribute("SEARCHRESULT", listResultXml);

                request.setAttribute("SEARCHVALUE", txtSearchValue);
                url = SEARCH_PAGE;
            }

        } catch (Exception ex) {
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            if (out != null) {
                out.close();
            }
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
