 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>BigBurger</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/carousel/">

    <!-- Bootstrap core CSS -->
    
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
    <link href="css/carousel.css" rel="stylesheet">
    
  </head>
  <body>
    <header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top">
    <a class="navbar-brand" href="#">BigBurger</a>

    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto"> 
        
        <!-- Boutton pour passer une commande, nous dirige vers la page de commande des burgers -->
        <li class="nav-item">
          <button type="button" class="btn btn-secondary">Commander</button>
        </li>

        <!-- --------------- -->
        <c:choose>

        <c:when test = "${empty sessionScope.userConnected}">           
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="connection.jsp">Se connecter</a>
        </li>
         <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="inscription.jsp">S'inscrire</a>
        </li>       
        </c:when>

        <c:otherwise>
         <!-- 
        <c:if test="${isAdmin}">               
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="administration">Afficher les commandes</a>          
        </li>                
        </c:if>
        -->

        <li class="nav-item">
          <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Bienvenue <c:out value = "${sessionScope.userConnected.name}"/>
            </a>          
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="#">Mon compte</a>
              <a class="dropdown-item" href="#">Historique commande</a>
              <a class="dropdown-item" href="#">parametres</a>
            </div>
          </div>
        </li>
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="disconnect">Se deconnecter</a>
        </li>    
        
        </c:otherwise> 
        </c:choose>  
        	    	
      </ul>      
    </div>
  </nav>
</header>
<main role="main">

  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">       
          <img src="images/burgerbg.jpg" alt="" width="100%" height="100%">                  
        <div class="container">
          <div class="carousel-caption text-left">
            <h1 class="light">BigBurger</h1>
            <p class="light">Depuis 1998, BIGBurger vous offre le bonheur de deguster les meilleurs burgers de la region !!</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Passer commande</a></p>
          </div>
        </div>
      </div>
      <div class="carousel-item">        
          <img src="images/burgerbg2.jpg" alt="" width="100%" height="100%">                  
        <div class="container">
          <div class="carousel-caption">
            <h1 class="light">Click and collect !</h1>
            <p class="light">Commandez puis venez retirer votre commande !</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">En savoir plus</a></p>
          </div>
        </div>
      </div>
      <div class="carousel-item">        
          <img src="images/livreur-pizza.jpg" alt="" width="100%" height="100%">                  
        <div class="container">
          <div class="carousel-caption text-right">
            <h1 class="light">Prochainement livraison ! </h1>
            <p class="light">On pourra bientot vous livrez !</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">En savoir plus</a></p>
          </div>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel 
    <div class="row">
      <div class="col-lg-4">        
          <title>Royale</title>
          <img src="images/mini_png_pizza/royale.png" alt="">
        <h2>La Royale !</h2>
        <p>La pizza royale fera valser vos papilles! Fort de son experience mario vous as retravaillé cette pizza, afin que l'équilibre des gouts vous explose en bouche lors de la dégustation, n'hésitez plus, goutez là ! </p>
        <p><a class="btn btn-secondary" href="#" role="button">Commandez &raquo;</a></p>
      </div>

      <div class="col-lg-4">        
          <title>Raclette</title>
          <img src="images/mini_png_pizza/raclette.png" alt="">
        <h2>La Raclette !</h2>
        <p>Mario as vécu quelques années en haute Savoie et il as ramené avec lui cette recette de pizza raclette! avec son roblochon directement reçu d'un éleveur situé en haute Savoie, vous voyagerez dans les montagnes lors de la dégustation</p>
        <p><a class="btn btn-secondary" href="#" role="button">Commandez &raquo;</a></p>
      </div>

      <div class="col-lg-4">        
          <title>Chêvre miel</title>
          <img src="images/mini_png_pizza/chevre.png" alt="">
        <h2>La Chêvre Miel !</h2>
        <p>Succés incontournable pour cette pizza au chêvre miel, les connaisseur seront reconnaitre un vrai travail d'équilibre entre le salé et le sucré dans cette pizza qui fera danser vos papilles !</p>
        <p><a class="btn btn-secondary" href="#" role="button">Commandez &raquo;</a></p>
      </div>
    </div>
    -->

    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">BigBurger <span class="text-muted">mode creativite active</span></h2>
        <p class="lead">Les meilleurs BigBurger du monde, les meilleurs BigBurger du monde, les meilleurs BigBurger du monde, les meilleurs BigBurger du monde, les meilleurs BigBurger du monde, </p>
      </div>
      <div class="col-md-5">        
          <img src="images/burger1.jpg" width="550" height="412">      
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading">Livraison bientot ! <span class="text-muted">Grace a des livreurs de qualité !</span></h2>
        <p class="lead">Avec notre engagement de livraison, nous pourrons vous garantissons de manger en un temps record ! les meilleurs BigBurger du monde, les meilleurs BigBurger du monde</p>
      </div>
      <div class="col-md-5 order-md-1">
        <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="images/livreur-burger.jpg" width="550" height="366">        
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">Visiter notre carte sans attendre ! <span class="text-muted"> Tous nos produits y figure</span></h2>
        <p class="lead">Grace a notre carte faite sur mesure, vous pourrez directement faire votre commande en detail, ajoutez vos souhaits les uns apres les autres puis valider votre commande</p>
        <p class="lead"><button type="button" class="btn btn-secondary btn-sm">Commander</button></p>
      </div>
      <div class="col-md-5">
        <img src="images/burger2.png" width="550" height="366">         
      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
  <footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>&copy; 2017-2020 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
  </footer>
</main>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
      <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="css/bootstrap.bundle.min.js"></script>
</html>
