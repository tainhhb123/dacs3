<?php
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function.php';

  if (isset($_POST["submit"])) {
    $product_name = $_POST["add_name"];
    $product_image = $_POST["add_image"];

    $query = "insert into category values ('', '$product_name', '$product_image')";
    mysqli_query($con, $query);
    echo "<script> alert ('Data Inserted Successfully'); </script>";
 
    header('Location: index.php');
    exit();
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-3">
  <h2>Add Product</h2>
  <form action="/Cosmetics/css/insert_page_1.php" method="post" >
    <div class="mb-3 mt-3">
      <label for="name">Name Product:</label>
      <input type="text" class="form-control" placeholder="Enter product name" name="add_name">
    </div>
    <div class="mb-3">
      <label for="pwd">Product Image:</label>
      <input type="text" class="form-control" placeholder="Enter product image" name="add_image">
    </div>

    <button type="submit" name = "submit" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>
