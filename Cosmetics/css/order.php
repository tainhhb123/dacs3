<?php 
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function_order.php';

  $result = display_data();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <title>Document</title>
    <style>
      #add_data {
        margin-bottom: 10px;
        float: right;
        text-decoration: none;
        background-color: green;
        color: white;
        padding: 9px 38px;
        border-radius: 4px;
      }

      .sidebar {
        width: 200px;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #f0f0f0;
      }

      .sidebar ul {
        list-style-type: none;
        padding: 0;
      }

      .sidebar ul li {
        padding: 10px;
      }

      .sidebar ul li a {
        text-decoration: none;
        color: black;
      }

      .sidebar ul li a:hover {
        color: blue;
      }
      #li_1{
        margin-top: 10px;
      }
      #text_admin {
        text-decoration: none;
        color: black;
        font-style: oblique;
        padding-left: 60px;
      }
      #p_admin{
        background-color: #5aaf5a;
        padding: 10px;
      }
      .material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
    </style>
</head>
<body>
  <div class="sidebar">
    <ul>
    <p id="p_admin"><a href="index.php" id="text_admin">ADMIN</a></p>
      <li><a href="order.php">Order</a></li>
      <li><a href="order_details.php">Order Details</a></li>
      <li><a href="product.php">Product</a></li>
    </ul>
  </div>



  <div class="container mt-3" style="margin-left: 200px;">
    <h2>Order Table</h2>


    <table class="table table-bordered table-sm">
      <thead>
        <tr class="bg-dark text-white">
          <th>ID</th>
          <th>Name</th>
          <th>Phone</th>
          <th>Email</th>
          <th>Address</th>
          <th>Delete</th>
          <th>Send Email</th>
          
        </tr>
      </thead>
      <tbody>
        <?php
          while($row = mysqli_fetch_assoc($result)) {
        ?>
          <tr>
            <td><?php echo $row['id'] ?></td>
            <td><?php echo $row['name'] ?></td>
            <td><?php echo $row['phone'] ?></td>
            <td><?php echo $row['email'] ?></td>
            <td><?php echo $row['address'] ?></td>
            <td><a href="delete_page_order.php?id=<?php echo $row['id']; ?>&confirm=true" onclick="return confirm('Are you sure delete this?')" class="btn btn-danger">Delete</a></td>
            <td><a href = "send_email.php?email=<?php echo $row['email']; ?>"><span class="material-symbols-outlined">send</span></a></td>
        </tr>
        <?php
          }
       ?>
   
    </tbody>
  </table>
</div>

    
</body>
</html>