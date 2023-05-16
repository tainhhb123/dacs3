<?php 
    include "connect.php";
    $json = $_POST['json'];
    $data = json_decode($json, true);
    foreach ($data as $value) {
        $ordercode = $value['ordercode'];
        $productcode = $value['productorder'];
        $name = $value['name'];
        $price = $value['price'];
        $quantity = $value['quantity'];
        $query = "INSERT INTO orderdetail (id, ordercode, productorder, name, price, quantity)
        VALUES (null, '$ordercode', '$productcode', '$name', '$price', '$quantity')" ;
        $dta = mysqli_query($conn, $query);
    }
    if ($dta) {
        echo "1";
    } else {
        echo "0";
    }
?>