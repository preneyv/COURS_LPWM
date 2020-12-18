<?php
class userController{
    private $_userManager;
    private $_user;

    public function __construct($collect)
    {
        require('../model/user.php');
        require_once('../model/usermanager.php');
        $this->_userManager = new Usermanager($collect);

    }

    public function doLogin(){
        $userTabFilter=array(
            '$and' => [
                'email' => $_POST['staticEmail'],
                'password' => $_POST['inputPassword']
                ]
        );
        if($result = $this->_userManager->getUserByPassAndEmail($userTabFilter) != null)
        {
            $user= array(
                'id' => $result['_id'],
                'email' => $result['email'],
                'password' => $result['password'],
                'firstname' => $result['firstname'],
                'lastname' => $result['lastname'],
                'pseudo' => $result['pseudo']
            );
            $this->_user = $this->_userManager->createUser($result['_id'],$user);
            $_SESSION['userStateLogIn'] = $this->_user == 'null' ? ['res'=>'Echec à la connexion','couleur' => 'red']:['res'=>'Connexion réussie','couleur' => 'green'];
        
        }else{
            $_SESSION['userStateLogIn'] = ['res'=>'Echec à la connexion','couleur' => 'red'];
        }

        //header("Location : ../vue/form.php");
    }

    public function doLogup()
    {
        $user=array(
            'email' => $_POST['staticEmail'],
            'firstname' => $_POST['inputFirstName'],
            'lastname' => $_POST['inputLastName'],
            'password' => $_POST['inputPassword'],
            'pseudo' => $_POST['inputPseudo'],
        );
        var_dump($user);

        if(isset($user['email']) && isset($user['firstname']) && isset($user['lastname']) && isset($user['password']) && isset($user['pseudo']))
        {
            $idNewAdd = $this->_userManager->addUser($user);
            $user = $this->_userManager->createUser($idNewAdd,$user);
            $_SESSION['userStateLogUp'] = $idNewAdd == 'null' ? ['res'=>'Echec à la création','couleur' => 'red']:['res'=>'Inscription réussie','couleur' => 'green'];
            header('Location : ../vue/form.php');

        }else{
            $_SESSION['userStateLogUp'] = ['res'=>'Echec à la création','couleur' => 'red'];
            header("Location : ../vue/form.php");
        }
        
    }
}
?>
