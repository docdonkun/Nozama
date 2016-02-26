package nozama.model;
// Generated 26 f�vr. 2016 19:41:07 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Movie generated by hbm2java
 */
@Entity
@Table(name = "movie", catalog = "nozama")
public class Movie implements java.io.Serializable {

	private Integer idMovie;
	private Product product;
	private String type;
	private Date dateReleased;
	private Set<TypeSupportMovie> typeSupportMovies = new HashSet<TypeSupportMovie>(0);

	public Movie() {
	}

	public Movie(Product product, String type, Date dateReleased) {
		this.product = product;
		this.type = type;
		this.dateReleased = dateReleased;
	}

	public Movie(Product product, String type, Date dateReleased, Set<TypeSupportMovie> typeSupportMovies) {
		this.product = product;
		this.type = type;
		this.dateReleased = dateReleased;
		this.typeSupportMovies = typeSupportMovies;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_movie", unique = true, nullable = false)
	public Integer getIdMovie() {
		return this.idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "type", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_released", nullable = false, length = 10)
	public Date getDateReleased() {
		return this.dateReleased;
	}

	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
	public Set<TypeSupportMovie> getTypeSupportMovies() {
		return this.typeSupportMovies;
	}

	public void setTypeSupportMovies(Set<TypeSupportMovie> typeSupportMovies) {
		this.typeSupportMovies = typeSupportMovies;
	}

}
