<?php 
    include "connect.php";
    $namecustomer = $_POST['namecustomer'];
    $phonecustomer = $_POST['phonecustomer'];
    $emailcustomer = $_POST['emailcustomer'];
    $addresscustomer = $_POST['addresscustomer'];
    if(strlen($namecustomer) > 0 && strlen($emailcustomer) > 0 && strlen($phonecustomer) > 0 && strlen($addresscustomer) > 0) {
       $query = "INSERT INTO `order` (id, name, phone, email, address) VALUES (null, '$namecustomer', '$phonecustomer', '$emailcustomer', '$addresscustomer') ";
        if (mysqli_query($conn, $query)) {
            $idorder = $conn->insert_id;
            echo $idorder;
        } else {
            echo "Fail: " . mysqli_error($conn);
        }
    } else {
        echo "Kiểm tra lại dữ liệu";
    }
?>