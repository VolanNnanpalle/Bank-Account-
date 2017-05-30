/*
 * Servlet
 */
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet gets all the users information and outputs the information to
 * the page
 * @author Volan
 */
@WebServlet(name="Master", urlPatterns=
{
    "/Master"
})
public class Master extends HttpServlet
{

    //attributes 
    static Retrieve r;
    String username;
    String password;

    static
    {
        try
        {
            //connects to the database
            Driver driver=new com.mysql.jdbc.Driver();
            r=new Retrieve((com.mysql.jdbc.Driver) driver,
                "jdbc:mysql://34.198.22.152/Volan_DB",
                "Volan", "Cobalt235!");

        }catch(SQLException ex)
        {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter out=response.getWriter();
        String control=request.getParameter("action");
        String link="/Error";
        //waiting for the user's action
        if(control.equalsIgnoreCase("New User"))
        {
            link="/create.html"; //links to the create page
        }
        if(control.equals("<-BACK"))
        {
            link="/index.html"; //links to home page
        }
        if(control.equalsIgnoreCase("RETURN"))
        {
            link="/index.html"; //links to home page
        }else if(control.equalsIgnoreCase("Submit"))
        {
            username=request.getParameter("username");
            password=request.getParameter("password");
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String accountType=request.getParameter("accountType");
            String amt=request.getParameter("amount");
            double amount=Double.parseDouble(amt);

            String address=request.getParameter("address");

            r.getNewAccountInfo(username, password, firstName, lastName,
                address, accountType, amount);
            r.openAccount();
            System.out.println("Accont created");
            link="/Success.jsp"; //links to success page
        }
        if(control.equalsIgnoreCase("Log in"))
        {

            username=request.getParameter("username");
            password=request.getParameter("password");
            String logInCheck=r.checkUserNamePassword(username, password);
            if(logInCheck.equalsIgnoreCase("no"))
            {
                link="/Error.jsp";
            }else if(logInCheck.equalsIgnoreCase("yes"))
            {
                username=null;
                password=null;
                link="/LoggedIn.html"; //links to logged in page 
            }
        }
        if(control.equalsIgnoreCase("CHECKBALANCE"))
        {
            link="/checkBalance.jsp"; //links to check balance jsp page
        }
        if(control.equalsIgnoreCase("Check"))
        {
            String lname=request.getParameter("lastName");
            response.setContentType("text/html");
            r.checkBalance(lname, out);
            return;

        }
        if(control.equalsIgnoreCase("<--BACK"))
        {
            link="/LoggedIn.html"; //links to login page
        }
        if(control.equalsIgnoreCase("TRANSFER"))
        {
            link="/Transfer.jsp"; //links to transfer page
        }
        if(control.equalsIgnoreCase("Transfer-->"))
        {

            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String amt=request.getParameter("amount");
            double amount=0;
            try
            {
                amount=Double.parseDouble(amt);
            }catch(Exception e)
            {

            }
            String withdraw=request.getParameter("withdraw");
            String deposit=request.getParameter("deposit");

            r.getTransferInfo(amount, withdraw, deposit, lastName,
                firstName);
            r.transfer();

            link="/AllSuccess.jsp"; //links to success page 

        }
        if(control.equalsIgnoreCase("TRANSACTION"))
        {
            link="/Transaction.jsp"; //links to transaction page

        }
        if(control.equalsIgnoreCase("See Transaction"))
        {

            String startDate=request.getParameter("start");

            String endDate=request.getParameter("end");

            String accountType=request.getParameter("account");
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");

            response.setContentType("text/html");
            r.transactions(startDate, endDate, lastName,
                firstName,
                accountType, out);
            System.out.println("Transaction worked");
            return;
        }
        if(control.equalsIgnoreCase("DEPOSIT"))
        {
            link="/Deposit.jsp"; //links to deposit page

        }
        if(control.equalsIgnoreCase("Deposit-->"))
        {
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String amt=request.getParameter("amount");
            String account=request.getParameter("deposit");

            double amount=0;
            try
            {
                amount=Double.parseDouble(amt);
            }catch(Exception e)
            {

            }
            r.deposit(firstName, lastName, account, amount);
            link="/AllSuccess.jsp"; //links to success page
        }
        if(control.equalsIgnoreCase("WITHDRAW"))
        {
            link="/Withdraw.jsp"; //links to withdraw page

        }
        if(control.equals("Withdraw-->"))
        {
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String amt=request.getParameter("amount");
            String account=request.getParameter("withdraw");

            double amount=0;
            try
            {
                amount=Double.parseDouble(amt);
            }catch(Exception e)
            {

            }
            r.withdraw(firstName, lastName, account, amount);

            link="/AllSuccess.jsp"; //link to success page


        }
        if(control.equalsIgnoreCase("OPEN MORE ACCOUNTS"))
        {
            link="/openAccount.jsp"; //links to open account page
        }
        if(control.equalsIgnoreCase("Open"))
        {
            String account=request.getParameter("new account");
            String amt=request.getParameter("amount");
            String password=request.getParameter("password");
            double amount=0;
            try
            {
                amount=Double.parseDouble(amt);
            }catch(Exception e)
            {

            }
            r.currentUserNewAccount(password, account, amount);
            link="/AllSuccess.jsp"; //links to success page 

        }
        getServletContext().getRequestDispatcher(link).
            forward(request, response); //does the forwarding to the page
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
