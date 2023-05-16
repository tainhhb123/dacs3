<?php 
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function_product.php';

  $result = display_data();

  if(isset($_GET['id'])) {
      $id = $_GET['id'];

      $query = "SELECT * FROM `product name` WHERE `id` = '$id'";
      $result = mysqli_query($con, $query);

      if (!$result) {
          die("failed" . mysqli_error());
      } else {
          $row = mysqli_fetch_assoc($result);
      }
  }

  if (isset($_POST['update_product'])) {
    $p_name = $_POST['p_name'];
    $p_price = $_POST['p_price'];
    $p_image = $_POST['p_image'];
    $p_describe = $_POST['p_describe'];
    $p_idproduct = $_POST['p_idproduct'];

    $query = "UPDATE `product name` SET `name` = '$p_name',`price` = '$p_price', `image` = '$p_image', `describe` = '$p_describe',`idproduct` = '$p_idproduct' WHERE `id` = '$id'";

    $result = mysqli_query($con, $query);

    if (!$result) {
        die("failed" . mysqli_error());
    } else {
        header('location:product.php?update_msg=You have successfully updated the data');
    }
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
  <h2>Edit form</h2>
  <form action="/Cosmetics/css/update_page_product.php?id=<?php echo $id; ?>" method="post">
    <div class="mb-3 mt-3">
      <label for="text_label">Product Name</label>
      <input type="text" class="form-control" name="p_name" placeholder="Enter product name" value="<?php echo isset($row['name']) ? $row['name'] : ''; ?>">
    </div>
    <div class="mb-3 mt-3">
      <label for="text_label">Price</label>
      <input type="number" class="form-control" name="p_price" placeholder="Enter product price" value="<?php echo isset($row['price']) ? $row['price'] : ''; ?>">
    </div>
    <div class="mb-3">
      <label for="text_image">Image:</label>
      <input type="text" class="form-control" name="p_image" placeholder="Enter product image" value="<?php echo isset($row['image']) ? $row['image'] : ''; ?>">
    </div>
    <div class="mb-3">
      <label for="text_describe">Describe:</label>
      <input type="text" class="form-control" name="p_describe" placeholder="Enter product describe" value="<?php echo isset($row['describe']) ? $row['describe'] : ''; ?>">
    </div>
    <div class="mb-3">
      <label for="text_image">Product ID update_product:</label>
      <input type="number" class="form-control" name="p_idproduct" placeholder="Enter product ID" value="<?php echo isset($row['idproduct']) ? $row['idproduct'] : ''; ?>">
    </div>
    <button type="submit" class="btn btn-primary" name="update_product">Update</button>
  </form>
</div>
</body>
</html>