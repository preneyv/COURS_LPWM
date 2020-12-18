<?php
class userManager{

    private $_managerDb;

    public function __construct($db)
    {
        require_once('./Connexion.class.php');
        $this->_managerDb = $db;
    }

    public function getUserById($id){

        $filter = ['id' => $id];
        $option = [];
        $read = new MongoDB\Driver\Query($filter, $option);
        //Exécution de la requête
        $cursor =  $manager->executeQuery('CollectUser.users', $read);
        foreach($cursor as $user)
        {
                return $user;
        }
    }

    public function getUserByPassAndEmail($pass, $email){

        $filter = ['password' => $pass, 'email'=> $email];
        $option = [];
        $read = new MongoDB\Driver\Query($filter, $option);
        //Exécution de la requête
        $cursor =  $manager->executeQuery('CollectUser.users', $read);
        foreach($cursor as $user)
        {
                return $user;
        }
    }

    public function createUser($userTab){

        $user = new User();
        $user->hydrate($userTab);
        return $user;
    }
}
?>
