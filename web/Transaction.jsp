<%-- 
    This jsp page allows the user to see all theri transactions
    Document   : Transaction
    Created on : May 6, 2017, 6:47:09 PM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
    </head>
    <body  bgcolor =" #ed1c2">
    <center><strong>TRANSACTION</strong></center>
    <br/>
    <br/>
    <!--Transaction form-->
    <form action="/WebBankAccount/Master"  method ="get">
    <center><strong><label for="meeting">Enter the starting date and ending date of the transactions you want to see 
            </label></strong>
        <br/>
        <br/>
        <center> Starting Date(YYYY-MM-DD)</center>
        <input  type="text"  name ="start"/></center>
    <br/>
    <center> Ending  Date(YYYY-MM-DD)</center>
    <center><input type="text" name = "end"/></center>

    <br/>
    <center> What Account Transaction Do You Want To See? 
        <br/>
        <select name = "account">
            <option>Checking</option>
            <option>Saving</option>
            <option>Money Market</option>
        </select>
    </center>
    <br/>
    <center> What Is Your First Name
        <br />
        <input type = "text" name = "firstName">
    </center>
    <br/>
    <center> What Is Your Last Name
        <br />
        <input type = "text" name = "lastName">
    </center>
    <br/>
    <!--see transactions button-->
    <center> <input type = "submit" name = "action" value ="See Transaction"></center>
    </form>
</body>
</html>
