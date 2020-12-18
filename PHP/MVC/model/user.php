<?php
class user {
    private $_id;
    private $_email;
    private $_password;
    private $_firstname;
    private $_lastname;
    private $_pseudo;

    public function __construct(){

    }
    public function __construct($id, $email, $password,$_firstname,$lastname, $pseudo){

        $this->_id = $id;
        $this->_email = $email;
        $this->_password = $password;
        $this->_firstname = $firstname;
        $this->_lastname = $lastname;
        $this->_pseudo = $pseudo;
    }

    public function hydrate($donnees)
    {
        foreach($donnees as $key => $value)
        {
            $methode = 'set'.$key;
            if(method_exists($this,$methode))
            {
                $this->$methode($value);
            }
        }
    }

    public function getId(){return $this->_id;}
    public function getEmail(){return $this->_email;}
    public function getFirstname(){return $this->_firstname;}
    public function getLastname(){return $this->_lastname;}
    public function getPseudo(){return $this->_pseudo;}

    public function setId($id){$this->_id = $id;}
    public function setEmail($email){$this->_email = $email;}
    public function setPassword($password){$this->password = $password;}
    public function setFirstname($firstname){$this->_firstname = $firstname;}
    public function setLastname($lastname){$this->_lastname = $lastname;}
    public function setPseudo($pseudo){$this->_pseudo = $pseudo;}
}
?>