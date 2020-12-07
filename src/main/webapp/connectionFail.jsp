<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Page de connection</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">

    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/floating-labels.css" >
  </head>
  <body>


  <!-- FORMULAIRE : -->


    <form class="form-signin" action="ConnectionUser" method="POST">
  <div class="text-center mb-4">

    <h1 class="h3 mb-3 font-weight-normal">Se connecter à BigBurger</h1>
    <p>Veuillez saisir votre nom ainsi que votre mot de passe afin d'accéder aux fonctionnalités que vous offre le site</p>
  </div>

  <div class="form-label-group">
    <input type="text" id="inputLogin" class="form-control" name="name_parameter" placeholder="Nom" required autofocus>
    <label for="inputLogin">Nom :</label>
  </div>

  <div class="form-label-group">
    <input type="password" id="inputPassword" class="form-control" name="password_parameter" placeholder="mot de passe" required>
    <label for="inputPassword">Mot de passe :</label>
  </div>

  <!-- <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>  -->
<p> Nom ou mot de passe incorrect</p>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
  <p class="mt-5 mb-3 text-muted text-center">&copy; Family Bros since 1998</p>
</form>
</body>
</html>
