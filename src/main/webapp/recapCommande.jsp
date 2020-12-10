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

        <c:if test="${isAdmin}">
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="administration">Afficher les commandes</a>
        </li>
        </c:if>


        <li class="nav-item">
          <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Bienvenue <c:out value = "${sessionScope.userConnected.name}"/>
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="#">Mon compte</a>
              <a class="dropdown-item" href="historyCommande">Historique commande</a>
              <a class="dropdown-item" href="#">parametres</a>
            </div>
          </div>
        </li>
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="disconnect">Se deconnecter</a>
        </li>
        <li class="nav-item">
          <a type="button"  class="btn btn-secondary" href="CommandeUser">Commander</a>
        </li>

        </c:otherwise>
        </c:choose>

      </ul>
    </div>
  </nav>
</header>
<main role="main">


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->



    <!-- Three columns of text below the carousel
    <!-- START THE FEATURETTES -->

<center><h1>RECAPITULATIF</h1></center>


         <table class="table">
                    <thead>
                        <tr>

                            <th>Burger</th>
                            <th>Quantite</th>
                            <th>Prix unitaire</th>
                            <th>Total</th>


                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ requestScope.commande.commandeItemsDTOList}" var="commandeI">
                        <tr>
                            <td> <c:out value = "${commandeI.label}"/></td>
                            <td ><c:out value = "${commandeI.quantity}"/></td>
                            <td><c:out value = "${commandeI.price} euros"/></td>
                            <td>${commandeI.totalPrixLigne} euros</td>

                        </tr>
</c:forEach>
                    </tbody>
               </table>

               <tr>

               </tr>
<p><h1>Date recup : <c:out value = "${commande.heureRecup}"/></h1></p>
<p><h1>Prix Total : <c:out value = "${commande.prixTotal}"/> euros</h1></p>

<p><a type="button" class="btn btn-primary" href="accueil.jsp">Retour Accueil</a></p>

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