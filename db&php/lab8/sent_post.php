<?php
    header("Content-type:text/javascript;charset=utf-8");
    define('HOST','localhost');
    define('USER','root');
    define('PASS','');
    define('DB','lab_connect_mysql');

    $con = mysqli_connect(HOST,USER,PASS,DB) or die ('Unable to connect');

    $std_id = $_POST['stdid'];
    $std_name = $_POST['stdname'];
    $std_tel = $_POST['stdtel'];
    $std_email = $_POST['stdemail'];
    $genae = $_POST['genae'];
    $intge = (int)$genae;
    
    if(isset($_POST)){
        if($_POST['isAdd'] == 'true'){

            $sql = "INSERT INTO content VALUES ('".$std_id."','".$std_name."','".$std_tel."','".$std_email."',".$intge.")";
            mysqli_query($con,$sql);
        }
    }

    mysqli_close($con);
    ?>