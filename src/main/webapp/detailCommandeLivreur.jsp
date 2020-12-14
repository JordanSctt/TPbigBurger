
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

        <c:if test="${userConnected.role == 'admin'}">
        <li class="nav-item">
          <a type="button" class="btn btn-secondary" href="affichageCommande">Afficher les commandes</a>
        </li>
        <li class="nav-item">
          <div class="dropdown">
              <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Gestion</a>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="gestionCuisto">Gestion Cuisto</a>
              <a class="dropdown-item" href="gestionLivreur">Gestion Livreur</a>
              <a class="dropdown-item" href="burgerAdd.jsp">Ajouter un burger</a>
              </div>
              </div>
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
                  <div class="dropdown">
                      <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Commander</a>
                      <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                      <a class="dropdown-item" href="CommandeUser">A emporter</a>
                      <a class="dropdown-item" href="CommandeUserLivraison">Se faire livrer</a>
                      </div>
                      </div>
        </li>

        </c:otherwise>
        </c:choose>

      </ul>
    </div>
  </nav>
</header>
  <body>

  <div class="text-center">
   	 <div class="text-center mb-4">
     		<h1 class="h1 mb-3 font-weight-normal">Affichage des commandes (Admin.)</h1>
     		<a type="button" class="btn btn-outline-primary center" href="accueil.jsp">Accueil</a>
   	 </div>
      <table class="table">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Numero commande</th>
            <th scope="col">Heure commande</th>
            <th scope="col">Date debut Livraison</th>
            <th scope="col">Date fin livraison estime</th>
            <th scope="col">Nom client</th>
            <th scope="col">Phone client</th>
            <th scope="col">Adresse</th>


          </tr>
        </thead>
        <tbody>


           <tr>
             <td width ="10%"><c:out value="${ commande.id }" /></td>
             <td width ="10%"><c:out value="${commande.startDatePrep}" /></td>
             <td width ="10%"> <c:out value="${commande.startDateLivraison}" /></td>
             <td width ="10%"><c:out value="${commande.estimationLivraison}" /></td>
             <td width ="10%"><c:out value="${commande.name}" /></td>
             <td width ="10%"><c:out value="${commande.phone}" /></td>
             <td width ="10%"><c:out value="${commande.adresse}" /></td>



            </tr>
  </tbody>
             </table>
           </div>
                 <table class="table">
                        <thead class="thead-dark">
                          <tr>
                            <th scope="col">Burger</th>
                            <th scope="col">Prix burger</th>
                            <th scope="col">Quantite</th>

                          </tr>
                        </thead>
                        <tbody>
                  <c:forEach items="${requestScope.commande.commandeItemsDTOList}" var="commandeItems">
           <tr>
                        <td><c:out value="${commandeItems.label}" /></td>
                        <td><c:out value="${commandeItems.price}" />_euros</td>
                        <td><c:out value="${commandeItems.quantity}" />_unite(s)</td>
                    </tr>
                </c:forEach>

              </tr>


        </tbody>
      </table>
    </div>
  </body>
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
          <script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="css/bootstrap.bundle.min.js"></script>
    </html>
