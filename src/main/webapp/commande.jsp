
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Commande</title>

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


    <table class="table">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Quantite</th>

            </tr>
        </thead>
        <tbody><tr>

        </tr>
        <c:forEach items="${ requestScope.burgers}" var="burger">
           <form action ="actionCommanderUser" method ="post">
            <tr>
                <td width = "15%" ><c:out value="${ burger.label }" /></td>
                <td width = "10%" ><c:out value="${ burger.price }" /></td>
                <td><input type="text" id="quantiteBurger" name="user_quantiteBurger" maxlength="2" size = "1"></td>
            </tr>

        </c:forEach>
        <td ><class="button"><input type="submit" value="Commander"></a></td>
        </form>
        </tbody>
    </table>




</body>
</html>
