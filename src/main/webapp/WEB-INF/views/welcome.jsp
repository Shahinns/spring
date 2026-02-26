<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shahin E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f4f4f4; }
        .welcome-card { background: white; padding: 30px; border-radius: 10px; display: inline-block; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
        h1 { color: #2c3e50; }
        p { color: #7f8c8d; }
    </style>
</head>
<body>

    <div class="welcome-card">
        <%
            // Saatə görə mesaj təyin edək
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            String greeting = (hour < 12) ? "Sabahınız xeyir" : (hour < 18 ? "Günortanız xeyir" : "Axşamınız xeyir");
        %>

        <p><%= greeting %>,</p>
        <h1>Shahin E-commerce saytına xoş gəlmisiniz!</h1>
        <hr>
        <p>Keyfiyyətli məhsullar və uyğun qiymətlər üçün doğru ünvandasınız.</p>
    </div>

</body>
</html>