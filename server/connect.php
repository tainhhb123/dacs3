<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $database = "cosmetics";

    $conn = mysqli_connect($host, $username, $password, $database);
    mysqli_query($conn, "SET NAMES 'utf8'");

    if ($conn){

    }
    else {
        echo "Ket noi that bai";
    }
?>