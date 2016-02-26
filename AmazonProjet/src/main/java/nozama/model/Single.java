package nozama.model;
// Generated 25 f�vr. 2016 15:17:06 by Hibernate Tools 4.3.1.Final

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
 * Single generated by hbm2java
 */
@Entity
@Table(name = "single", catalog = "nozama")
public class Single implements java.io.Serializable {

	private Integer idSingle;
	private Artiste artiste;
	private Product product;
	private Date dateReleased;
	private String type;
	private Set<AlbumHasSingle> albumHasSingles = new HashSet<AlbumHasSingle>(0);
	private Set<TypeSupportSingle> typeSupportSingles = new HashSet<TypeSupportSingle>(0);

	public Single() {
	}

	public Single(Artiste artiste, Product product, String type) {
		this.artiste = artiste;
		this.product = product;
		this.type = type;
	}

	public Single(Artiste artiste, Product product, Date dateReleased, String type, Set<AlbumHasSingle> albumHasSingles, Set<TypeSupportSingle> typeSupportSingles) {
		this.artiste = artiste;
		this.product = product;
		this.dateReleased = dateReleased;
		this.type = type;
		this.albumHasSingles = albumHasSingles;
		this.typeSupportSingles = typeSupportSingles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_single", unique = true, nullable = false)
	public Integer getIdSingle() {
		return this.idSingle;
	}

	public void setIdSingle(Integer idSingle) {
		this.idSingle = idSingle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artiste", nullable = false)
	public Artiste getArtiste() {
		return this.artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_released", length = 10)
	public Date getDateReleased() {
		return this.dateReleased;
	}

	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}

	@Column(name = "type", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "single")
	public Set<AlbumHasSingle> getAlbumHasSingles() {
		return this.albumHasSingles;
	}

	public void setAlbumHasSingles(Set<AlbumHasSingle> albumHasSingles) {
		this.albumHasSingles = albumHasSingles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "single")
	public Set<TypeSupportSingle> getTypeSupportSingles() {
		return this.typeSupportSingles;
	}

	public void setTypeSupportSingles(Set<TypeSupportSingle> typeSupportSingles) {
		this.typeSupportSingles = typeSupportSingles;
	}

}