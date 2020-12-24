<?php
class CalendarManager{

    private $_managerDb;
    public function __construct($db)
    {
        require_once('connexion.php');
        $this->_managerDb = $db;
    }

    public function getListEmploye()
    {
        $filter = [];
        $option = [];
        $read = new MongoDB\Driver\Query($filter, $option);
        //Exécution de la requête
        $cursor =  $this->_managerDb->executeQuery('CollectUser.employes', $read);
        $employe = [];
        foreach($cursor as $emp)
        {
                
        
                array_push($employe,$emp);
        }
        return $employe;
    }

    public function getListWeek()
    {
        $listeSemaine = ["2017"=>[],'2018'=>[],'2019'=>[],'2020'=>[]];
        $filter = [];
        $option = [];
        $read = new MongoDB\Driver\Query($filter, $option);
        
        //Exécution de la requête
        foreach($listeSemaine as $key=>$value)
        {
            $cursor =  $this->_managerDb->executeQuery('CollectUser.year'.$key, $read);
            $week = [];
            foreach($cursor as $sem)
            {
                    
                     array_push($week,$sem);
            }
            
            array_push($listeSemaine[$key],$week);
        }
      
        return $listeSemaine; 
    }
}