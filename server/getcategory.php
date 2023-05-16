<?php
    include "connect.php";
    
    $query = "SELECT * FROM `category` ";

    $data = mysqli_query($conn, $query);
    $arr_category = array();

    while ($row = mysqli_fetch_assoc($data)) {

        array_push($arr_category, new Category(
            $row['id'],
            $row['product_name'],
            $row['product_image']
        ));
    }

    echo json_encode($arr_category);

        class Category{
            public $id;
            public $name;
            public $image;
            
            public function __construct ($id, $name, $image) {
                $this->id = $id;
                $this->name = $name;
                $this->image = $image;
            }
        }
?>