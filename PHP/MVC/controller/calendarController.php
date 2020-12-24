<?php
class CalendarController{

    private $_calendarManager;

    public function __construct($collect)
    {
        
        require_once('../model/calendarManager.php');
        $this->_calendarManager = new CalendarManager($collect);
    }

    public function startCalendar(){
        $this->getAllEmploye();
        $this->getAllWeek();
        header('Location : ../vue/calendrier.php');
    }

    public function getAllEmploye()
    {
        $_SESSION['listeEmployes'] = $this->_calendarManager->getListEmploye();
    }

    public function getAllWeek()
    {
        $_SESSION['listeSemaine'] = $this->_calendarManager->getListWeek();
    }
}