<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<ol class="breadcrumb">
	<li>
		<a href="/"><span>Accueil </span></a>
	</li>
	<li>
		<a href="/liste-tous-les-films"><span>Movie</span></a>
	</li>
	<li class="active">
		<span>${products.name} </span>
	</li>
</ol>
<div class="container">
	<div class="row">
		<div class="col-md-5 col-md-offset-0">
			<img class="img-product img-responsive" src="/resources/img/product/<c:out value="${products.urlPicture}" />" alt="${products.name}" />
		</div>
		<div class="col-md-7">
			<h2>
				<c:out value="${products.name}" />
			</h2>
			<div class="artist-product">
				<a href="/artiste/"></a>
				<span>
					Date de sortie
					<fmt:formatDate pattern="dd/MM/yyyy" value="${products.dateReleased}" />
				</span>

			</div>


			<p>
				<c:if test="${fn:length(products.description) == 0}">
					Pas de description...
				</c:if>
				<c:out value="${products.description}" />
			</p>

			<div class="row">
				<c:set var="listType" value="${products.listType}" />

				<c:forEach var="type" items="${listType}">
					<div class="blockProductBottom">

						<div class="col-md-12 price-product">
							<div class="priceProduct">
								<img src="/resources/img/${type.support}.png" />
								<fmt:formatNumber value="${type.price}" type="currency" />
							</div>
							<button class="btn btn-primary" type="button">Ajouter au panier</button>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>
	<div class="page-header">
		<h3>Détail du produit</h3>
	</div>
	<p>Temps total :</p>
	<p>Label :</p>
	<div class="media">
		<div class="media-body">
			<h4>Love this!</h4>
			<div>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star-half"></span>
			</div>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis maximus nisl ac diam feugiat, non vestibulum libero posuere. Vivamus pharetra leo non nulla egestas, nec malesuada orci finibus.</p>
			<p>
				<span class="reviewer-name">
					<strong>John Doe</strong>
				</span>
				<span class="review-date">7 Oct 2015</span>
			</p>
		</div>
	</div>
	<div class="media">
		<div class="media-body">
			<h4>Fantastic product</h4>
			<div>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
				<span class="fa fa-star"></span>
			</div>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis maximus nisl ac diam feugiat, non vestibulum libero posuere. Vivamus pharetra leo non nulla egestas, nec malesuada orci finibus.</p>
			<p>
				<span class="reviewer-name">
					<strong>Jane Doe</strong>
				</span>
				<span class="review-date">7 Oct 2015</span>
			</p>
		</div>
	</div>
</div>

<jsp:include page="templates/footer.jsp" />
