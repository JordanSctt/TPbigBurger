<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Page d'ajout de burger</title>

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

    <form class="form-signin" action="ajoutProduit" method="POST">
  <div class="text-center mb-4">

    <h1 class="h3 mb-3 font-weight-normal">Ajouter un produit a la carte</h1>
    <p>Veuillez saisir un type, un nom ainsi qu'un prix pour votre nouveau produit</p>
  </div>

  <!-- <div class="form-label-group">
    <input type="text" id="inputLogin" class="form-control" name="label_parameter" placeholder="Login" required autofocus>
    <label for="inputLogin">Nom :</label>
  </div> -->
  <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
    <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off" name="type_parameter">
    <label class="btn btn-outline-primary" for="btncheck1">BURGER</label>
  
    <input type="checkbox" class="btn-check" id="btncheck2" autocomplete="off" name="type_parameter">
    <label class="btn btn-outline-primary" for="btncheck2">BOISSON</label>
  
    <input type="checkbox" class="btn-check" id="btncheck3" autocomplete="off" name="type_parameter">
    <label class="btn btn-outline-primary" for="btncheck3">DESSERT</label>
  </div>

  <div class="form-label-group">
  <select class="form-select" aria-label="Default select example" name="label_parameter">
    <option selected>Ouvrir pour selectionner un nouveau produit</option>
    <option value="Fish">burger Fish</option>
    <option value="Chiken">burger Chiken</option>
    <option value="Swiss">burger Swiss</option>
    <option value="Black">burger Black</option>
    <option value="Biere">boisson Biere</option>
    <option value="Eauplate">boisson Eauplate</option>
    <option value="Tarte">dessert Tarte</option>
    <option value="Fruits">dessert Fruits</option>
  </select>
  </div>

  <div class="form-label-group">
    <input type="number"  class="form-control" name="price_parameter" placeholder="prix" required>
    <label for="inputPassword">prix :</label>
  </div>


  <!-- <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>  -->
  
  <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter</button>
  <p class="mt-5 mb-3 text-muted text-center">&copy; Famille Bros since 1998</p>
</form>
</body>
</html>
