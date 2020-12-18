<?php
class userController{
    private $_userManager;
    private $_user;

    public function __construct($collect)
    {
        require('../model/User.class.php');
        require_once('../model/Usermanager.class.php');
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
        
    }
}
?>
