<?php
    session_start();
    require_once('../model/connexion.php');
    $dbInstance = new Connexion();
    $dbInstance->doConnect();
    $collection = $dbInstance->getManagerDB();
    $map = array(
           'user' => array(
                            'login'=>'doLogin',
                            'logup'=>'doLogup'
            ),
            'calendar' => array(
                            'start' => 'startCalendar'
            )

           );
    

    $fc=$_GET['fc'];
    $src = $_GET['ctrl'];
 
    require_once('./'.$src.'Controller.php');
    $curControler = $src.'Controller';
    $curControler = new $curControler($collection);
    $method = $map[$src][$fc];
    $curControler->$method();


?>