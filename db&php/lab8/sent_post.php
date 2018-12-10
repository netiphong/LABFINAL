<?php
    header("Content-type:text/javascript;charset=utf-8");
    define('HOST','localhost');
    define('USER','root');
    define('PASS','');
    define('DB','lab_connect_mysql');

    $con = mysqli_connect(HOST,USER,PASS,DB) or die ('Unable to connect');

    $std_id = $_POST['std_id'];
    $std_name = $_POST['std_name'];
    $std_tel = $_POST['std_tel'];
    $std_email = $_POST['std_email'];
    $spinner = $_POST['spinner'];
    $ints = (int)$spinner;
    $gender = $_POST['gender'];

    
    if(isset($_POST)){
        if($_POST['isAdd'] == 'true'){

            $sql = "INSERT INTO content VALUES ('".$std_id."','".$std_name."','".$std_tel."','".$std_email."',".$ints.",'".$gender."')";
            mysqli_query($con,$sql);
        }
    }

    mysqli_close($con);
    ?>