<%--    
    This jsp page allows the user to 
    Document   : Deposit
    Created on : May 6, 2017, 6:45:35 PM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deposit</title>
    </head>
       <center><strong>DEPOSIT</strong></center>
       <br/>
       <br/>
    <body  bgcolor =" #ed1c2">
        <!--deposit form-->
        <form method ="get" action="/WebBankAccount/Master" >
            <center> Enter First Name
                <br />
                <input type = "text" name = "firstName">
            </center>
            <center> Enter Last Name
                <br />
                <input type = "text" name = "lastName">
            </center>
            <center> Enter Amount You Want To Deposit
                <br />
                <input type = "text" name = "amount">
            </center>
            <br />
            <center> What Account Do You Want To Deposit The Money?
                <br />
                <select name = "deposit">
                    <option>Checking</option>
                    <option>Saving</option>
                    <option>Money Market</option>
                </select>
            </center>
            <br/>
            <!--deposit button-->
            <center> <input type = "submit" name ="action" value="Deposit-->"></center>

        </form>
    </body>
</html>
