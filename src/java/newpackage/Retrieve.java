package newpackage;

import com.mysql.jdbc.Driver;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class performs all the tasks
 * @author Volan Nnanpalle
 */
public class Retrieve
{

    //attributes
    private Driver driver;
    private String url;
    private String databaseUsername;
    private String databasePassword;
    private Statement sm;
    private Connection c;
    private String newUsername;
    private String newUserPassword;
    private String firstName;
    private String lastName;
    private String userAddress;
    private String accountType;
    private double accountBalance;
    private int customerID;
    private int tempCustomerID;
    private double amountToTransfer;
    private String depositLocation;
    private String userLastName;
    private String userFirstName;


    Retrieve(Driver d, String url, String user, String p)
    {
        driver=d;
        this.url=url;
        databaseUsername=user;
        databasePassword=p;
        //to connect to the database 
        try
        {
            driver=new com.mysql.jdbc.Driver();
            c=(Connection) DriverManager.getConnection(url, databaseUsername,
                databasePassword);
            sm=c.createStatement();

            if(c!=null)
            {
                System.out.println("Connected To Wells Vargo Bank DataBase");
            }

        }catch(SQLException ex)
        {
        }

    }

    public Connection getCon()
    {
        return c;
    }

    /**
     * This method when called creates tables in the database
     */
    public void createTables()
    {
        try
        {
            String customerTable
                ="CREATE TABLE customers (c_id INT(11)AUTO_INCREMENT, username VARCHAR(30), "+
                " password VARCHAR(16), first_name VARCHAR(30), last_name VARCHAR (40), address VARCHAR(100),"+
                " PRIMARY KEY(c_id)); ";
            sm.execute(customerTable);
            String accountTable
                ="CREATE TABLE account (a_id INT(11) AUTO_INCREMENT, "+
                " acct_type VARCHAR(20), balance DOUBLE(10,2), c_id INT(11), FOREIGN KEY(c_id) REFERENCES customers(c_id), PRIMARY KEY(a_id)); ";
            sm.execute(accountTable);
            String transactionTable
                ="CREATE TABLE Transactions (t_id INT(11) AUTO_INCREMENT, a_id INT(11), "+
                " balance DOULBE(10,2) ,t_date DATE, t_type VARCHAR(20), FOREIGN KEY(a_id) REFERENCES account(a_id), PRIMARY KEY(t_id));";
            sm.execute(transactionTable);
        }catch(SQLException ex)
        {
        }
    }

    /**
     * This method allows the user to open a new account in the database
     */
    public void openAccount()
    {
        try
        {
            String sql
                ="INSERT into customers(username,password,first_name,last_name,address) "+
                "VALUES('"+newUsername+"','"+newUserPassword+"','"+firstName+
                "','"+
                lastName+"','"+
                userAddress+"')";

            sm.executeUpdate(sql);
            setAccount();
        }catch(SQLException ex)
        {
        }
    }

    /**
     * This method allows the user to create more accounts if they are already
     * in the database
     * @param accountType
     * @param accountBalance
     */
    public void openMoreAccount(String accountType, double accountBalance)
    {
        try
        {
            String sql="SELECT c_id FROM customers WHERE username = '"+
                newUsername+"' and password = '"+newUserPassword+"';";

            ResultSet rs=sm.executeQuery(sql);

            while(rs.next())
            {
                customerID=rs.getInt("c_id");
            }
            String sqlAcct
                ="INSERT INTO account(c_id,acct_type,balance) VALUE ( '"+
                customerID+"', '"+accountType+"' , '"+accountBalance+"');";
            sm.executeUpdate(sqlAcct);
        }catch(SQLException ex)
        {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null,
                ex);
        }
    }

    /**
     * This sets the users account when created
     */
    public void setAccount()
    {
        try
        {
            String sqlGetCID
                ="SELECT c_id FROM customers ORDER BY c_id DESC LIMIT 1;";
            ResultSet rs=sm.executeQuery(sqlGetCID);

            while(rs.next())
            {
                customerID=rs.getInt("c_id");
            }
            String sqlAcct
                ="INSERT into account(c_id,acct_type,balance)VALUES('"+
                customerID+"','"+accountType+"','"+accountBalance+
                "')";

            sm.executeUpdate(sqlAcct);
        }catch(SQLException ex)
        {
        }
    }

    /**
     * This method checks if the user is a current user nd then inserts them in
     * the database
     * @param password the users password
     * @param accountT the users account type they wish to create
     * @param b the balance the user wants to insert into the database
     */
    public void currentUserNewAccount(String password, String accountT, double b)

    {
        try
        {
            String sqlGetCID
                ="SELECT c_id FROM customers where password ='"+
                password+"';";
            ResultSet rs=sm.executeQuery(sqlGetCID);

            while(rs.next())
            {
                tempCustomerID=rs.getInt("c_id");
            }
            String sqlAcct
                ="INSERT into account(c_id,acct_type,balance)VALUES('"+
                tempCustomerID+"','"+accountT+"','"+b+
                "')";
            //executes the sql statement
            sm.executeUpdate(sqlAcct);
        }catch(SQLException ex)
        {
        }
    }

    /**
     * This method checks the balance of all the user's accounts
     * @param name user's last name
     * @param out PrintWriter object 
     */
    public void checkBalance(String name, PrintWriter out)
    {
        int c_id=0;
        try
        {
            String sqlGetCID="SELECT c_id from customers where last_name = '"+
                name+
                "';";
            ResultSet rs=sm.executeQuery(sqlGetCID);
            while(rs.next())
            {
                c_id=rs.getInt("c_id");
            }
            String sqlGetAllBalance="SELECT * from account where c_id = "+c_id+
                ";";
            ResultSet rs1=sm.executeQuery(sqlGetAllBalance);

            ResultSetMetaData rsmd=rs1.getMetaData();
            int nc=rsmd.getColumnCount();

            out.println("<table bgcolor=\"#00FF00\">");
            out.println(
                "<meta http-equiv=\"refresh\"  content =\"10;url=/WebBankAccount/LoggedIn.html\" />");
            out.println("<h1>Redirecting in 10 seconds.....</h1>");
            out.println("\n1)Account id\t\t2)Customer id\t\t3)Account "+
                "\t\t4)Balance\n\n");
            while(rs1.next())
            {
                for(int i=1; i<=nc; i++)
                {
                    out.println("<tr>");
                    out.println("<td>");

                    out.println(" "+rs1.getObject(i)+"\t\t");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("");
            }
            out.println("\n\n");
            out.println("</table>");
            System.out.println("\n\n");

        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Allows the user to transfer money from one account to the another
     */
    public void transfer()
    {
        try
        {
            //temporary variables
            int tempCID=0;
            int tempAID=0;
            int tempAID2=0;
            //to get the customer id for 
            String sqlGetCID="SELECT c_id FROM customers WHERE first_name = '"+
                userFirstName+"' and last_name = '"+userLastName+"';";

            ResultSet rs=sm.executeQuery(sqlGetCID);

            while(rs.next())
            {
                tempCID=rs.getInt("c_id"); //where you took the money from
            }
            //to get the customer's account Id
            String sqlGetAID=" SELECT a_id from account where acct_type = '"+
                accountType+"' and c_id = '"+tempCID+"';";
            ResultSet rs1=sm.executeQuery(sqlGetAID);
            while(rs1.next())
            {
                tempAID=rs1.getInt("a_id"); //where you took the money from
            }

            //gets the account id of where the money is being deposited
            String sqlGetAID2=" SELECT a_id from account where acct_type = '"+
                depositLocation+"' and c_id = '"+tempCID+"';";
            ResultSet rs3=sm.executeQuery(sqlGetAID2);

            while(rs3.next())
            {
                tempAID2=rs3.getInt("a_id"); //where you took put the money 
            }

            //updates the table
            String update1="UPDATE account SET balance = balance - '"+
                amountToTransfer+"' WHERE acct_type = '"+accountType+
                "' AND a_id = '"+
                tempAID+"';";
            String update2="UPDATE account SET balance = balance + '"+
                amountToTransfer+"' WHERE acct_type = '"+depositLocation+
                "' AND a_id = '"+
                tempAID2+"';";


            //inserts the data into the table
            String sqlTransactions
                ="INSERT INTO Transactions (a_id,balance,t_date,t_type) VALUES ('"+
                tempAID+"','-"+amountToTransfer+"', now(),'"+
                accountType+"');";

            String sqlTransactions2
                ="INSERT INTO Transactions (a_id,balance,t_date,t_type) VALUES ('"+
                tempAID2+"','+"+amountToTransfer+"', now(),'"+
                depositLocation+"');";
            //executes the sql statements
            sm.executeUpdate(update1);
            sm.executeUpdate(update2);
            sm.executeUpdate(sqlTransactions);
            sm.executeUpdate(sqlTransactions2);
        }catch(SQLException ex)
        {
        }
    }

    /**
     * This method allow the user to see all their transaction on a specific
     * account
     * @param start beginning date of the transaction
     * @param end ending date of the transaction
     * @param lastName user's last name
     * @param firstName user's first name
     * @param accountType account type the user wants to see
     * @param out PrintWriter object
     * @return the formated table of the transactions
     */
    public void transactions(String start, String end, String lastName,
        String firstName, String accountType, PrintWriter out)
    {
        String table="";
        int c_id=0;
        int a_id=0;

        try
        {
            String sqlGetCID="SELECT c_id from customers where last_name = '"+
                lastName+
                "' and first_name = '"+firstName+"' ;";
            ResultSet rs=sm.executeQuery(sqlGetCID);
            while(rs.next())
            {
                c_id=rs.getInt("c_id");
            }
            String sqlGetAID="SELECT a_id from account where c_id = '"+c_id+
                "' and acct_type = '"+accountType+"' ;";
            ResultSet rs1=sm.executeQuery(sqlGetAID);
            while(rs1.next())
            {
                a_id=rs1.getInt("a_id");
            }

            String sqlGetAllBalance="SELECT * from Transactions where a_id = '"+
                a_id+"' and "+" t_type = '"+accountType+"' ;";
            ResultSet rs2=sm.executeQuery(sqlGetAllBalance);
            ResultSetMetaData rsmd=rs2.getMetaData();
            int nc=rsmd.getColumnCount();
            out.println("<table bgcolor=\"#00FF00\">");
            out.println(
                "<meta http-equiv=\"refresh\"  content =\"10;url=/WebBankAccount/LoggedIn.html\" />");
            out.println("<h1>Redirecting in 10 seconds.....</h1>");
            out.println(
                "\n1)Transaction ID\t\t\t2)Account ID\t\t\t3)Transaction Amount"+
                "\t                    Transaction Date\t\t                         Accout\n ");
            while(rs2.next())
            {
                for(int i=1; i<=nc; i++)
                {

                    out.println("<tr>");
                    out.println("<td>");
                    out.println(rs2.getObject(i)+"\t\t\t");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("");
            }
            out.println("</table>");

        }catch(SQLException ex)
        {
        }
    }

    /**
     * Checks the username and password
     * @param username user's username
     * @param password user's password
     * @return yes if it is in the database and no if not
     */
    public String checkUserNamePassword(String username, String password)
    {
        String loginCheck="";
        try
        {
            String check
                ="SELECT username FROM customers where password ='"+password+
                "' and username = '"+username+"';";

            ResultSet rs=sm.executeQuery(check);

            if(!rs.next()||rs.wasNull())
            {
                loginCheck="no";
            }else
            {
                loginCheck="yes";
            }

        }catch(SQLException ex)
        {
        }
        return loginCheck;
    }

    public void deposit(String firstName, String lastName, String acctType,
        double deposit)
    {
        try
        {
            int tempCID=0;
            int tempAID=0;
            //to get the customer id for 
            String sqlGetCID="SELECT c_id FROM customers WHERE first_name = '"+
                firstName+"' and last_name = '"+lastName+"';";

            ResultSet rs=sm.executeQuery(sqlGetCID);

            while(rs.next())
            {
                tempCID=rs.getInt("c_id"); //where you took the money from
            }
            //to get the customer's account Id
            String sqlGetAID=" SELECT a_id from account where acct_type = '"+
                acctType+"' and c_id = '"+tempCID+"';";
            ResultSet rs1=sm.executeQuery(sqlGetAID);
            while(rs1.next())
            {
                tempAID=rs1.getInt("a_id"); //where you took the money from
            }

            //updates the table
            String update1="UPDATE account SET balance = balance + '"+
                deposit+"' WHERE acct_type = '"+acctType+
                "' AND a_id = '"+
                tempAID+"';";

            //inserts the data into the table
            String sqlTransactions
                ="INSERT INTO Transactions (a_id,balance,t_date,t_type) VALUES ('"+
                tempAID+"','-"+deposit+"', now(),'"+
                acctType+"');";


            sm.executeUpdate(update1);
            sm.executeUpdate(sqlTransactions);
        }catch(SQLException ex)
        {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null,
                ex);
        }

    }


    public void withdraw(String firstName, String lastName, String acctType,
        double deposit)
    {
        try
        {
            int tempCID=0;
            int tempAID=0;
            //to get the customer id for 
            String sqlGetCID="SELECT c_id FROM customers WHERE first_name = '"+
                firstName+"' and last_name = '"+lastName+"';";

            ResultSet rs=sm.executeQuery(sqlGetCID);

            while(rs.next())
            {
                tempCID=rs.getInt("c_id"); //where you took the money from
            }
            //to get the customer's account Id
            String sqlGetAID=" SELECT a_id from account where acct_type = '"+
                acctType+"' and c_id = '"+tempCID+"';";
            ResultSet rs1=sm.executeQuery(sqlGetAID);
            while(rs1.next())
            {
                tempAID=rs1.getInt("a_id"); //where you took the money from
            }

            //updates the table
            String update1="UPDATE account SET balance = balance - '"+
                deposit+"' WHERE acct_type = '"+acctType+
                "' AND a_id = '"+
                tempAID+"';";

            //inserts the data into the table
            String sqlTransactions
                ="INSERT INTO Transactions (a_id,balance,t_date,t_type) VALUES ('"+
                tempAID+"','-"+deposit+"', now(),'"+
                acctType+"');";


            sm.executeUpdate(update1);
            sm.executeUpdate(sqlTransactions);
        }catch(SQLException ex)
        {
            Logger.getLogger(Retrieve.class.getName()).log(Level.SEVERE, null,
                ex);
        }

    }

    /**
     * This method gets all the information need to create a new account
     * @param username the user's username
     * @param password the user's password
     * @param first_name the user's first name
     * @param last_name the user's last name
     * @param address the user's full address
     * @param acct_type the user's account type they want to create
     * @param balance the balance the user want to deposit into the account
     */
    public void getNewAccountInfo(String username, String password,
        String first_name, String last_name, String address, String acct_type,
        double balance)
    {
        newUsername=username;
        newUserPassword=password;
        this.firstName=first_name;
        this.lastName=last_name;
        this.userAddress=address;
        accountType=acct_type;
        this.accountBalance=balance;
    }

    /**
     * this method gets all the information needed to make a transfer from one
     * account to the other
     * @param transfer the amount being transfered
     * @param acct_type the account the money is being taken from
     * @param deposit_location the account the money is being deposited
     * @param last_name the users last name
     * @param fname the user's first name
     */
    public void getTransferInfo(double transfer, String acct_type,
        String deposit_location, String last_name, String fname)
    {
        this.amountToTransfer=transfer;
        this.accountType=acct_type;
        this.depositLocation=deposit_location;
        userLastName=last_name;
        this.userFirstName=fname;
    }

}
