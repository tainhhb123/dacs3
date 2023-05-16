<?php 
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function.php';

  $result = display_data();

  if(isset($_GET['id'])) {
      $id = $_GET['id'];

      $query = "SELECT * FROM `category` WHERE `id` = '$id'";
      $result = mysqli_query($con, $query);

      if (!$result) {
          die("failed" . mysqli_error());
      } else {
          $row = mysqli_fetch_assoc($result);
      }
  }

  if (isset($_POST['update_category'])) {
    $p_name = $_POST['p_name'];
    $p_image = $_POST['p_image'];

    $query = "UPDATE `category` SET `product_name` = '$p_name', `product_image` = '$p_image' WHERE `id` = '$id'";

    $result = mysqli_query($con, $query);

    if (!$result) {
        die("failed" . mysqli_error());
    } else {
        header('location:index.php?update_msg=You have successfully updated the data');
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
  <form action="/Cosmetics/css/update_page_1.php?id=<?php echo $id; ?>" method="post">
    <div class="mb-3 mt-3">
      <label for="text_label">Product Name</label>
      <input type="text" class="form-control" name="p_name" placeholder="Enter product name" value="<?php echo isset($row['product_name']) ? $row['product_name'] : ''; ?>">
    </div>
    <div class="mb-3">
      <label for="text_image">Product Image:</label>
      <input type="text" class="form-control" name="p_image" placeholder="Enter product image" value="<?php echo isset($row['product_image']) ? $row['product_image'] : ''; ?>">
    </div>
    <button type="submit" class="btn btn-primary" name="update_category">Update</button>
  </form>
</div>
</body>
</html>