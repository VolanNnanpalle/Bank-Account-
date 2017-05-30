<%-- 
    This jsp page allows the user to transfer money from one account to another
    Document   : Transfer
    Created on : May 6, 2017, 6:46:42 PM
    Author     : Volan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body  bgcolor =" #ed1c2">
    <center><strong>TRANSFER</strong></center>
    <br />
    <br />
    <!--transfer form-->
    <form method ="post" action="/WebBankAccount/Master" method ="get">
        <center> Enter First Name
            <br />
            <input type = "text" name = "firstName">
        </center>
        <center> Enter Last Name
            <br />
            <input type = "text" name = "lastName">
        </center>
        <center> Enter Amount You Want To Transfer
            <br />
            <input type = "text" name = "amount">
        </center>
        <br />
        <center> From What Account Do You Want To Take Money From?
            <br />

            <select name = "withdraw">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>

        </center>
        <br />
        <center> What Account Do You Want To Take Deposit The Money?
            <br />

            <select name = "deposit">
                <option>Checking</option>
                <option>Saving</option>
                <option>Money Market</option>
            </select>
        </center>
        <br/>
        <!--transfer button-->
        <center> <input type = "submit" name ="action" value="Transfer-->"></center>

    </form>
</body>
</html>
