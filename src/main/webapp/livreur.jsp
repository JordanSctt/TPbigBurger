
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Page admin livreurs</title>

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
  
  
  <div class="text-center">  
 	 <div class="text-center mb-4">
   		<h1 class="h1 mb-3 font-weight-normal">Affichage des livreurs (Admin.)</h1>
   		<a type="button" class="btn btn-outline-primary center" href="accueil.jsp">Accueil</a>
 	 </div>
    <table class="table">
      <thead class="thead-dark">
        <tr>
          <th scope="col">Numero livreur</th>
          <th scope="col">Nom livreur</th>
          <th scope="col">Etat de presence livreur</th>
        </tr>
      </thead>
      <tbody>   
        <c:forEach items="${requestScope.livreurs}" var="livreur">    
            <tr>        
                <td><c:out value="${livreur.id }" /></td>
                <td><c:out value="${livreur.name}" /></td>
                <td><c:out value="${livreur.presence}" /></td>
            </tr>
        </c:forEach> 
                
      </tbody>
    </table>  	
    <!--<a href="${pageContext.request.contextPath}/affichageCommande/detail?commande_id=<c:out value="${ commande.id }" />"/>Detail<a/>  -->	     	 
  </div>
</body>
</html>
