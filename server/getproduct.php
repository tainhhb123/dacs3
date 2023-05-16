<?php
    include "connect.php";
    $page = $_GET['page'];
    // $idsp = $_POST['idproduct']; 
    $idsp = 1;
    $space = 5;
    $limit = ($page - 1) * $space;

    $query = "SELECT * FROM `product name` WHERE idproduct = $idsp LIMIT $limit, $space";

    $data = mysqli_query($conn, $query);
    $arr_product = array();
   
    while ($row = mysqli_fetch_assoc($data)) {
        array_push($arr_product, new Product(
            $row['id'],
            $row['name'],
            $row['price'],
            $row['image'],
            $row['describe'],
            $row['idproduct'],
        ));
    }
    echo json_encode($arr_product);

    class Product{
        public $id; 
        public $pname; 
        public $pprice; 
        public $pimage; 
        public $pdescribe; 
        public $pid; 
    
        function __construct ($id, $pname, $pprice, $pimage, $pdescribe, $pid) {
            $this->id = $id;
            $this->pname = $pname;
            $this->pprice = $pprice;
            $this->pimage = $pimage;
            $this->pdescribe = $pdescribe;
            $this->pid = $pid;

        }
    }
?>