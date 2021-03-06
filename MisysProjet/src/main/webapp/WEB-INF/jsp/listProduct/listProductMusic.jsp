<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Filtre :</h3>
	</div>
	<div class="panel-body">

		<div class="blocFiltre">
			<input type="hidden" id="recordType" name="recordType" value="${recordType}" /> Format :
			<span data-value="single" class="buttonWithoutBackground <c:if test="${recordType == 'single' || recordType == 'AllType'}">selectedFormat</c:if>"> Single </span>
			<span data-value="album" class="buttonWithoutBackground <c:if test="${recordType == 'album' || recordType == 'AllType' }">selectedFormat</c:if>"> Album </span>
		</div>
		<form action="/liste-toutes-les-musiques" class="formMusic" method="get">

			<div class="blocFiltre">
				Support : <select id="support" name="support">
					<option value="AllSupport" <c:if test="${support == 'AllSupport' }">selected</c:if>>Tous les supports</option>
					<option value="CD" <c:if test="${support == 'CD' }">selected</c:if>>CD</option>
					<option value="Vinyle" <c:if test="${support == 'Vinyle' }">selected</c:if>>Vinyle</option>
					<option value="Telechargement" <c:if test="${support == 'Telechargement' }">selected</c:if>>Téléchargement</option>
				</select>
			</div>

			<div class="blocFiltre">
				Années : <select id="years" name="years">
					<option value="AllYears">Toutes les années</option>
					<option value="2010" <c:if test="${years == '2010' }">selected</c:if>>Années 2010 et +</option>
					<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Années 2000 - 2009</option>
					<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Années 1990 - 1999</option>
					<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Années 1980 - 1989</option>
					<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Années 1970 - 1979</option>
					<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Années 1960 - 1969</option>
					<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Années 1950 - 1959</option>
					<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Années 1940 - 1949</option>
					<option value="1939" <c:if test="${years == '1939' }">selected</c:if>>Années antérieur</option>
				</select>
			</div>

			<div class="blocFiltre">
				Genre de musique : <select id="type" name="type">
					<option value="ALL">Tous les genres de musique</option>
					<option value="VarieteFrancaise" <c:if test="${type == 'VarieteFrancaise' }">selected</c:if>>Variété française</option>
					<option value="PopRockInde" <c:if test="${type == 'PopRockInde' }">selected</c:if>>Pop-rock indé</option>
					<option value="MusiqueClassique" <c:if test="${type == 'MusiqueClassique' }">selected</c:if>>Musique classique</option>
					<option value="HarRockMetal" <c:if test="${type == 'HarRockMetal' }">selected</c:if>>Hard rock, métal</option>
					<option value="JassBlues" <c:if test="${type == 'JassBlues' }">selected</c:if>>Jazz, blues</option>
					<option value="RBSoulFunk" <c:if test="${type == 'RBSoulFunk' }">selected</c:if>>R&amp;B, soul, funk</option>
					<option value="MusiqueDuMonde" <c:if test="${type == 'MusiqueDuMonde' }">selected</c:if>>Musiques du monde</option>
					<option value="Rap" <c:if test="${type == 'Rap' }">selected</c:if>>Rap</option>
					<option value="MusiquePourEnfant" <c:if test="${type == 'MusiquePourEnfant' }">selected</c:if>>Musique pour enfants</option>
					<option value="Electro" <c:if test="${type == 'Electro' }">selected</c:if>>Electro</option>
					<option value="Opera" <c:if test="${type == 'Opera' }">selected</c:if>>Opéra</option>
					<option value="BoMusiqueFilm" <c:if test="${type == 'BoMusiqueFilm' }">selected</c:if>>BO, musiques de film</option>
				</select>
			</div>

			<div class="blocFiltre">
				<input type="submit" class="btn btn-primary" value="Filtrer" />
			</div>
		</form>

	</div>
</div>
<div class="clearBoth"></div>


	<jsp:include page="listProduct.jsp" />


<div class="paginationMusic center100"></div>

<script>
	var varNumberPage = ${numberPage};
	var varStartPage = ${startPage};
</script>

<script src="/resources/js/jquery.twbsPagination.min.js"></script>
<script src="/resources/js/listeProduct.js"></script>

<jsp:include page="../templates/footer.jsp" />