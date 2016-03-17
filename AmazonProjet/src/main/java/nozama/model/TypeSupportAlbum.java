package nozama.model;
// Generated 17 mars 2016 14:12:54 by Hibernate Tools 4.3.1.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TypeSupportAlbum generated by hbm2java
 */
@Entity
@Table(name = "type_support_album", catalog = "nozama")
public class TypeSupportAlbum implements java.io.Serializable,TypeSupport {


  private Integer idTypeSupport;
  private Album album;
  private String nameSupport;
  private float price;

  public TypeSupportAlbum() {}


  public TypeSupportAlbum(Album album, float price) {
    this.album = album;
    this.price = price;
  }

  public TypeSupportAlbum(Album album, String nameSupport, float price) {
    this.album = album;
    this.nameSupport = nameSupport;
    this.price = price;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "id_type_support", unique = true, nullable = false)
  public Integer getIdTypeSupport() {
    return this.idTypeSupport;
  }

  public void setIdTypeSupport(Integer idTypeSupport) {
    this.idTypeSupport = idTypeSupport;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_album", nullable = false)
  public Album getAlbum() {
    return this.album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }


  @Column(name = "name_support", length = 45)
  public String getNameSupport() {
    return this.nameSupport;
  }

  public void setNameSupport(String nameSupport) {
    this.nameSupport = nameSupport;
  }


  @Column(name = "price", nullable = false, precision = 12, scale = 0)
  public float getPrice() {
    return this.price;
  }

  public void setPrice(float price) {
    this.price = price;
  }



}


