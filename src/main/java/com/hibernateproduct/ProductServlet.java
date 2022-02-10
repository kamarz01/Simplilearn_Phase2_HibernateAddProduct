package com.hibernateproduct;

import com.hibernateproduct.entity.Product;
import com.hibernateproduct.utils.HibernateUtils;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.hibernate.Transaction;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "ProductServlet", value = "/Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pPrice = 0;
        String pName = request.getParameter("productName");
        String pDesc = request.getParameter("productDescription");

        try{
            pPrice = Integer.parseInt(request.getParameter("productPrice"));
            if(pName.isEmpty() || pDesc.isEmpty() || pPrice <= 0){
                request.setAttribute("message","Please insert all fields or check it's correct.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.include(request,response);
            }else{
                Transaction transaction = null;
                try{
                    Product product = new Product();
                    product.setProductName(pName);
                    product.setProductPrice(pPrice);
                    product.setProductDescription(pDesc);
                    Session session = HibernateUtils.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    session.save(product);
                    transaction.commit();
                    session.close();
                    request.setAttribute("message","Product Inserted successfully.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.include(request,response);
                }catch (Exception e){
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }

            }
        }catch (Exception ex){
            request.setAttribute("message","Please insert all fields or check it's correct.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request,response);
        }

    }
}
