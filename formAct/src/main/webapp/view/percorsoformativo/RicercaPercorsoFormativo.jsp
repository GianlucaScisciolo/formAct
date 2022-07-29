<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.entity.*,  model.dao.* , java.util.*"%>

<%
	
%>

<!DOCTYPE html>

<html>
	<head>
    	<!-- Required meta tags -->
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
	    <!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		
    	<title>Ricerca percorsi formativi</title>
	</head>
	
	<body>
		<%@include file="/view/fragments/Header.jsp"%>
		
	    <h1>Ricerca:</h1>
	    
	    <form action="${pageContext.request.contextPath}/PercorsoFormativoServlet/RicercaPFService" method="post">
	    	<label>
    		    Argomento (Opzionale):<br>
    	        <input type="text" id="argomento" name="argomento" placeholder="esempio: Java"><br>
	    	</label>
	    	
	    	<br> <br>
	    	
    	    Costo percorso formativo:<br>
    	    <label>
    	        Costo min (Opzionale): <input type="number" id="costoMin" class="rangeCosto" name="costoMin"><br>
    	    </label>
    	    <br>
    	    <label>
    	        Costo max (Opzionale): <input type="number" id="costoMax" class="rangeCosto" name="costoMax"><br>
    	    </label>
    	    
    	    <br> <br>
    	    
    	    <label>
    	    	Disponibilit� (Opzionale):<br>
    	    	<select name="disponibilita" id="disponibilita">
    	    		<option value="">Seleziona una diponibilit�</option>
    	    	    <option value="lunedi">Luned�</option>
    	    	    <option value="martedi">Marted�</option>
    	    	    <option value="mercoledi">Mercoled�</option>
    	    	    <option value="giovedi">Gioved�</option>
    	       		<option value="venerdi">Venerd�</option>
    	    	</select>
    	    </label>
    	    
    	    <br>
    	    
    	    <button type="submit" class="btn btn-outline-info" style="align:right;">Ricerca</button>
	    </form>
	</body>
</html>