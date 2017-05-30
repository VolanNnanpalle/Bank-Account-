<%-- 
    This jsp page allows the user to check the balance of all their accounts 
    Document   : checkBalance
    Created on : May 6, 2017, 6:44:56 PM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Balance</title>
    </head>

    <body  bgcolor =" #ed1c2">
    <center><strong>CHECK BALANCE</strong></center>
    <br />
    <br />
    <!--check balance form-->
    <form method ="get" action="/WebBankAccount/Master" >
        <center> Enter Last Name
            <br />
            <input type = "text" name = "lastName">
            <br/>
            <!--check button-->
          <input type = "submit" name ="action" value="Check">
        </center>
        
    </form>
</body>
</html>
