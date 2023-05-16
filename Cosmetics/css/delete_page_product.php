<?php 
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function_product.php';

  if (isset($_GET['id']) && isset($_GET['confirm']) && $_GET['confirm'] === 'true') {
    $id = $_GET['id'];
    $query = "DELETE FROM `product name` WHERE `id` = '$id'";
    $result = mysqli_query($con, $query);

    if (!$result) {
      die("Failed" . mysqli_error());
    } else {
      header('location:product.php?delete_msg=You have deleted the record');
    }
  }
?>
