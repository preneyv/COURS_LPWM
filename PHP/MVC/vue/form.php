<?php if(!isset($_SESSION))
	{
		session_start();
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container" style="padding-top: 15%">
        
      
            <div class="accordion" id="accordionExample">
                <div class="card" style="border-bottom : 1px solid rgb(0,0,0,.125)">
                  <div class="card-header" id="headingOne">
                    <h2 class="mb-0">S'inscrire</h2>
                    <?php
                    //si l'utilisateur n'existe pas et que l'inscription s'est bien passé
                    if(isset($_SESSION['user'])){echo "<h6 style='color:green;float : right'>Vous etes connecté</h6>";}  
                    //si l'utilisateur existe déjà
                    if(isset($_SESSION['notConnected'])){echo "<h6 style='color:red;float : right'>Ce compte n'existe pas</h6>";}    
                        
                        unset($_SESSION['notConnected']);               
                    ?>
                  </div>

                  <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                    <div class="card-body">
                        <form action="./connexion.php" method="post" id="formula" name="formula">
                            <div class="form-group row">
                              <label for="inputFirstName" class="col-sm-2 col-form-label">Pseudo</label>
                              <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputPseudo" name="inputPseudo" placeholder="Pseudo">
                              </div>
                            </div>
                            <div class="form-group row">
                              <label for="inputFirstName" class="col-sm-2 col-form-label">Mot de passe</label>
                              <div class="col-sm-10">
                                <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Mot de passe">
                              </div>
                            </div>
                            <div class="form-group row">
                              <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                              <div class="col-sm-10">
                                <input type="email" class="form-control" id="staticEmail" name="staticEmail" placeholder="email@example.com">
                              </div>
                            </div>
                             <button type="submit" name="btnSubmit" id="btnSubmit" class="btn btn-primary">Valider</button>
                          </form>
                    </div>
                  </div>
               </div>
             </div>
        </div>
    </body>
</html>