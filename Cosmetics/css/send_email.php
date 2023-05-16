<?php 
  require_once __DIR__ . '/../config/category.php';
  require_once __DIR__ . '/../config/function_order.php';

  $result = display_data();
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
  <h2>Send Email</h2>
  <form action="/Cosmetics/css/send.php" method="post">
  <div class="mb-3 mt-3">
      <label for="email">Email:</label>
      <input type="email" class="form-control" placeholder="Enter email" name="email" value="<?php echo isset($_GET['email']) ? $_GET['email'] : ''; ?>">
    </div>
    <div class="mb-3">
      <label for="pwd">Subject:</label>
      <input type="text" class="form-control" placeholder="Enter Subject" name="subject">
    </div>
    <div class="mb-3">
      <label for="pwd">Message:</label>
      <input type="text" class="form-control" placeholder="Enter Message" name="message">
    </div>
  


    <button type="submit" class="btn btn-primary" name="send">Send</button>
  </form>
</div>

</body>
</html>
