<?php
class connexion{

    private $_collection;
    private $_identifiant;
    private $_password;
    private $_manager;

    public function __construct(){
        $this->_identifiant = "val21200";
        $this->_password = "bm58ot05";
   }

   public function doConnect(){
        try{
            $this->_manager = new MongoDB\Driver\Manager('mongodb+srv://'.$this->_identifiant.':'.$this->_password.'@cluster0.36nor.mongodb.net/test');
        }catch(MongoDB\Driver\Exception\InvalidArgumentException $e )
        {
                $e->getMessage();
        }
    }

    public function getManagerDB(){return $this->_manager;}
    

}
?>