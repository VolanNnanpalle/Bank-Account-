<%-- 
    This jsp allows the user to open more acconts 
    Document   : openAccount
    Created on : May 9, 2017, 3:08:30 AM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Open Account</title>
    </head>
     <center><strong>OPEN ACCOUNT</strong></center>
       <br/>
       <br/>
    <body bgcolor =" #ed1c2">
        <form method ="get" action="/WebBankAccount/Master" >
            <center> Enter Which Account Type Do You Want: Saving, Checking or Money Market
                <br />
                <select name = "new account">
                    <option>Checking</option>
                    <option>Saving</option>
                    <option>Money Market</option>
                </select>
            </center>
            <br/>
        </center>
        <center> Enter the amount you want to store in your account
            <br />
            <input type = "text" name = "amount">
        </center>
        <br/>
        <center> Input password to verify
            <br />
            <input type = "password" name = "password">
        </center>
        <br/>
        <!--open button-->
        <center> <input type = "submit" name ="action" value="Open"></center>

    </form>
</body>
</html>
