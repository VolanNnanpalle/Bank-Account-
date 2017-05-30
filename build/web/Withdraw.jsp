<%-- 
    This jsp page allows the user to withdraw money from one account
    Document   : Withdraw
    Created on : May 6, 2017, 6:46:04 PM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw</title>
    </head>
      <center><strong>WITHDRAW</strong></center>
      <br/>
      <br/>
    <body  bgcolor =" #ed1c2">
        <!--withdraw form -->
               <form method ="get" action="/WebBankAccount/Master" >
            <center> Enter First Name
                <br />
                <input type = "text" name = "firstName">
            </center>
            <center> Enter Last Name
                <br />
                <input type = "text" name = "lastName">
            </center>
            <center> Enter Amount You Want To Withdraw
                <br />
                <input type = "text" name = "amount">
            </center>
            <br />
            <center> What Account Do You Want To Withdraw The Money?
                <br />
                <select name = "withdraw">
                    <option>Checking</option>
                    <option>Saving</option>
                    <option>Money Market</option>
                </select>
            </center>
            <br/>
            <!--withdraw button-->
            <center> <input type = "submit" name ="action" value="Withdraw-->"></center>

        </form>
    </body>
</html>
