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
        
        $pass = $_POST['password'];
        $mail = $_POST['email'];
        if($result = $this->_userManager->getUserByPassAndEmail($pass,$mail))
        {
            $user= array(
                'id' => $result['_id'],
                'email' => $result['email'],
                'password' => $result['password'],
                'firstname' => $result['firstname'],
                'lastname' => $result['lastname'],
                'pseudo' => $result['pseudo']
            );
            $this->_user = $this->_userManager->createUser($user);
            $_SESSION['user'] = $this->_user;
        }else{
            $_SESSION['notConnected'] = true;
        }

        header('../vue/connectForm.php');
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
            $_SESSION['notConnected'] = true;
            header("Location : ../vue/form.php");
        }
        
    }
}
?>
