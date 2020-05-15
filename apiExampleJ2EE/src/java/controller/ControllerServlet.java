/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package controller;

//import cart.ShoppingCart;
import entity.Category;
import entity.CbUsers;
import entity.CbUsersActivity;
import entity.Cd1cEkfProduction;
import entity.Product;
import entity.ProductsAmount;
import entity.ProductsAmountNoRel;
import entity.ProductsReceiptNoRel;
//import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.lang3.StringEscapeUtils;
import resources.Tools;
import session.CategoryFacade;
import session.CbLogsFacade;
import session.CbUsersActivityFacade;
import session.CbUsersFacade;
import session.Cd1cEkfProductionFacade;
import session.ProductFacade;
import session.ProductsAmountFacade;
import session.ProductsAmountNoRelFacade;
import session.ProductsReceiptNoRelFacade;
//import session.OrderManager;
//import session.ProductFacade;
import validate.Validator;

/**
 *
 * @author tgiunipero
 */
@WebServlet(name = "Controller",
            loadOnStartup = 1,
            urlPatterns = {"/products",
                           "/warehouses",
                           "/pramounts",
                           "/prreceipts",
                            "/test"})
public class ControllerServlet extends HttpServlet {

    private String surcharge;

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductsAmountNoRelFacade productsAmountFacade;
    @EJB
    private ProductsReceiptNoRelFacade productsReceiptFacade;
    @EJB
    private CbLogsFacade cbLogsFacade;
    @EJB
    private Cd1cEkfProductionFacade cbEKFProdFacade;
    @EJB
    private CbUsersFacade cbUsersFacade;
    @EJB 
    private CbUsersActivityFacade cbUsersActivityFacade;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        // initialize servlet with configuration information
        surcharge = servletConfig.getServletContext().getInitParameter("deliverySurcharge");

        // store category list in servlet context
        //getServletContext().setAttribute("warehouses", categoryFacade.findAll());
        
        //getServletContext().
        //String userPath = request.getServletPath();
        //String url = "/WEB-INF/view" + userPath + ".jsp";

        //try {
        //    request.getRequestDispatcher(url).forward(request, response);
        //} catch (Exception ex) {
        //    ex.printStackTrace();
        //}
    }


    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Category selectedCategory;
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");

        String ukey =  request.getParameter("key");
        if(ukey!=null)  {
        if(ukey.length()>10)    {
            List<CbUsers> uList = cbUsersFacade.findByEkfapikey(ukey);
            if(uList.size()==1)    {
            CbUsers usr = uList.get(0);
            String tst = "";
            
            boolean hasThisAct = false;
                boolean correct_quota=false;
                //Collection<CbUsersActivity> uActs = usr.getCbUsersActivityCollection();
                try {
                    //em.flush();
                    //em.clear();
                List<CbUsersActivity> uActs = cbUsersActivityFacade.getCbUsersActivityCollection(usr);
                for(CbUsersActivity uAct: uActs)    {
                    if(uAct.getObjectName().equals(userPath))    {
                        hasThisAct = true;
                        //em.refresh(uAct);
                        if(uAct.getActivityCounter()<1000) {
                            uAct.setActivityCounter(uAct.getActivityCounter()+1);
                            try {
                                cbUsersActivityFacade.edit(uAct);
                                correct_quota = true;
                            }   catch(Exception e)  {
                                
                            }
                        } else {
                            //if(
                            //
                            Date cdt = new Date();
                            long mls1 = uAct.getDate().getTime();
                            long mls2 = cdt.getTime();
                            Calendar cl1 = Calendar.getInstance();
                            cl1.setTimeInMillis(mls1);
                            Calendar cl2 = Calendar.getInstance();
                            cl2.setTimeInMillis(mls2);
                            String tstmp1 = cl1.get(Calendar.DAY_OF_MONTH)+"="+cl1.get(Calendar.MONTH)+"="+cl1.get(Calendar.YEAR);
                            String tstmp2 = cl2.get(Calendar.DAY_OF_MONTH)+"="+cl2.get(Calendar.MONTH)+"="+cl2.get(Calendar.YEAR);
                            tst="==="+tstmp1+"==="+tstmp2;
                                
                            if(!tstmp1.equals(tstmp2)) {
                                uAct.setActivityCounter(1);
                                uAct.setDate(new Date());
                                try {
                                    cbUsersActivityFacade.edit(uAct);
                                    correct_quota = true;
                                }   catch(Exception e)  {

                                }
                            }
                            
                            //DateTime dt = new DateTime
                        }
       
                        //cbUsersActivityFacade.fixActivity( usr.getId());
                        //uAct.
                        break;
                       
                    }
                }
                
                if(!hasThisAct) {
                    cbUsersActivityFacade.createActivity(usr, userPath);
                    correct_quota=true;
                    
                    //uActs.clear();
                }
                } catch(Exception e)    {
                    
                }
                finally {
                    cbUsersActivityFacade.fixClear();
                }
            
            // if category page is requested
            if (userPath.equals("/products")) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                int pnum =  Tools.parseInt(request.getParameter("pnum"),0);
                if(pnum<0) pnum=0;
                int psize = 1000;
                

                PrintWriter out = response.getWriter();
                StringBuilder sb = new StringBuilder();
                if(correct_quota)   {
                    
                List<Cd1cEkfProduction> LPr = cbEKFProdFacade.limitedPrList(pnum*psize, psize);
                String pcnt = cbEKFProdFacade.ekfProductionCount();
                sb.append("{\"status\":\"ok\",\"qsize\":\""+LPr.size()+"\",\"pnum\":\""+pnum+"\",\"psize\":\""+psize+"\",\"stor_cnt\":\""+pcnt+"\",\"data\":[");
                boolean next=false;
                for (Cd1cEkfProduction pitem : LPr) {
                        sb.append((next?",":""));//.append(pitem.toJsonString());
                        sb.append("{\"id\":\"").append(pitem.getId()).append("\",\"name\":\"").append(StringEscapeUtils.escapeJson(pitem.getName())).append("\",\"vendor_code\":\"").
                append(StringEscapeUtils.escapeJson(pitem.getVendorCode())).append("\",\"short_name\":\"").append(StringEscapeUtils.escapeJson(pitem.getShortName())).
                                append("\",\"barcode\":\"").append(pitem.getBarcode()).append("\"}");
                        next=true;
                }
                out.println(sb.toString());
                out.println("]}");
                }
                else
                out.println("{\"status\":\"error\",\"err_code\":\"004\",\"msg\":\"Quota end.\",\"data\":[]}");
                out.close();

            // if cart page is requested
            } else if (userPath.equals("/test")) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                PrintWriter out = response.getWriter();
                if(correct_quota)
                out.println("{\"status\":\"ok==="+tst+"\",\"data\":[]}");
                else
                out.println("{\"status\":\"error==="+tst+"\",\"err_code\":\"004\",\"msg\":\"Quota end.\",\"data\":[]}");    
                out.close();
            } else if (userPath.equals("/warehouses")) {

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                short sh=1;
                List<Category> LWh = categoryFacade.findAllIms2Active(sh);
                //session.setAttribute("warehouses", LWh);

                PrintWriter out = response.getWriter();
                if(correct_quota)   {
                out.println("{");
                out.println("\"status\":\"ok\",\"data\":[");
                boolean next=false;
                for(Category witem : LWh)   {
                    out.println((next?",":"")+witem.toJsonString());
                    next=true;
                }
                out.println("]}");
                }
                else
                out.println("{\"status\":\"error\",\"err_code\":\"004\",\"msg\":\"Quota end.\",\"data\":[]}");
                out.close();

            // if checkout page is requested
            } else if (userPath.equals("/pramounts")) {

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                int pnum =  Tools.parseInt(request.getParameter("pnum"),0);
                if(pnum<0) pnum=0;
                int psize = 1000;
                

                PrintWriter out = response.getWriter();
                StringBuilder sb = new StringBuilder();
                if(correct_quota)   {
                    DecimalFormat df = new DecimalFormat("#.000");
                    List<ProductsAmountNoRel> LPr = productsAmountFacade.noneZeroPrList();
                    String pcnt = ""+LPr.size();
                    sb.append("{\"status\":\"ok\",\"qsize\":\"").append(LPr.size()).append("\",\"pnum\":\"").append(pnum).append("\",\"psize\":\"").append(pcnt).append("\",\"stor_cnt\":\"").append(pcnt).append("\",\"data\":[");
                    boolean next=false;
                    for (ProductsAmountNoRel pitem : LPr) {
                            sb.append((next?",":""));
                            sb.append("{\"id\":\"").append(pitem.getId()).append("\",\"quantity\":\"").append(df.format(pitem.getQuantity())).append("\",\"prod_id\":\"").append(pitem.getProductId()).append("\",\"whs_id\":\"").append(pitem.getWarehouseId()).append("\"}");
                            next=true;
                    }
                    out.println(sb.toString());
                    out.println("]}");
                }
                else
                out.println("{\"status\":\"error\",\"err_code\":\"004\",\"msg\":\"Quota end.\",\"data\":[]}");
                out.close();


            // if user switches language productsReceiptFacade
            } else if (userPath.equals("/prreceipts")) {

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                int pnum =  Tools.parseInt(request.getParameter("pnum"),0);
                if(pnum<0) pnum=0;
                int psize = 500;
                
                PrintWriter out = response.getWriter();
                StringBuilder sb = new StringBuilder();
                if(correct_quota)   {
                    DecimalFormat df = new DecimalFormat("#.000");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    List<ProductsReceiptNoRel> LPr = productsReceiptFacade.limitedPrList(pnum*psize, psize);
                    String pcnt = productsReceiptFacade.storCount();
                    sb.append("{\"status\":\"ok\",\"qsize\":\"").append(LPr.size()).append("\",\"pnum\":\"").append(pnum).append("\",\"psize\":\"").append(psize).append("\",\"stor_cnt\":\"").append(pcnt).append("\",\"data\":[");
                    boolean next=false;
                    for (ProductsReceiptNoRel pitem : LPr) {
                            sb.append((next?",":""));
                            sb.append("{\"id\":\"").append(pitem.getId()).append("\",\"quantity\":\"").append(df.format(pitem.getQuantity())).append("\",\"state\":\"").append(StringEscapeUtils.escapeJson(pitem.getState())).append(
                "\",\"date\":\"").append(dateFormat.format(pitem.getDate())).append("\",\"prod_id\":\"").append(pitem.getProductId()).append("\",\"whs_id\":\"").append(pitem.getWarehouseId()).append("\"}");
                            next=true;
                    }
                    out.println(sb.toString());
                    out.println("]}");
                }
                else
                out.println("{\"status\":\"error\",\"err_code\":\"004\",\"msg\":\"Quota end.\",\"data\":[]}");
                out.close();

                //entityManager.createQuery(JPQL_QUERY)
                // .setParameter(arg0, arg1)
                // .setMaxResults(10)
                // .getResultList();
            }
            
            }
            else    {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                PrintWriter out = response.getWriter();
                out.println("{\"status\":\"error\",\"err_code\":\"005\",\"msg\":\"Authorization error\",\"data\":[]}");
                out.close();
            }
        }
        else    {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
            out.println("{\"status\":\"error\",\"err_code\":\"001\",\"msg\":\"Missing authorization\",\"data\":[]}");
            out.close();
        }
        }
        else    {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
            out.println("{\"status\":\"error\",\"err_code\":\"001\",\"msg\":\"Missing authorization\",\"data\":[]}");
            out.close();
        }

        // use RequestDispatcher to forward request internally
        
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            //request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // ensures that user input is interpreted as
                                                // 8-bit Unicode (e.g., for Czech characters)

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");

        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.println("{\"status\":\"error\",\"err_code\":\"006\",\"msg\":\"Bad request type, use GET.\",\"data\":[]}");
        out.close();
    }

}