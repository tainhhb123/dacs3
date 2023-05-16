<?php
    include "connect.php";

    
    $query = "SELECT * FROM `product name` ORDER BY ID DESC LIMIT 4";

    $data = mysqli_query($conn, $query) ;
    $nparray = array();

    while ($row = mysqli_fetch_assoc($data)) {
        array_push($nparray, new NewProduct(
            $row['id'],
            $row['name'],
            $row['price'],
            $row['image'],
            $row['describe'],
            $row['idproduct']
        ));
    }

    echo json_encode($nparray);

    class NewProduct {

    public $id; 
    public $npname; 
    public $npprice; 
    public $npimage; 
    public $npdescribe; 
    public $product_id; 

        public function __construct($id, $npname, $npprice, $npimage, $npdescribe, $product_id) {
            $this->id = $id;
            $this->npname = $npname;
            $this->npprice = $npprice;
            $this->npimage = $npimage;
            $this->npdescribe = $npdescribe;
            $this->product_id = $product_id;
        }

    }

?>