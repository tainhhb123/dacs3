<?php 
  require_once __DIR__ . '/../config/category.php';

  function display_data() {
    global $con;
    $query= "SELECT * FROM `order`" ;
    $result = mysqli_query($con, $query);
    return $result;
  }
?>