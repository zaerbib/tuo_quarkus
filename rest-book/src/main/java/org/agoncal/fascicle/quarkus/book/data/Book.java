package org.agoncal.fascicle.quarkus.book.data;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Schema(description = "Book representation")
@Entity
public class Book extends PanacheEntity {
	
	@Schema(required = true)
	@NotNull
	@NotEmpty
	public String title;	
	@Column(name = "isbn_13")
	public String isbn13;	
	@Column(name = "isbn10")
	public String isbn10;	
	public String author;	
	@Column(name = "year_of_publication")
	public Integer yearOfPublication;
	@Column(name = "nb_of_pages")
	public Integer nbOfPages;	
	@Min(1) @Max(10)
	public Integer rank;	
	public BigDecimal price;
	@Column(name = "small_image_url")
	public URL smallImageUrl;
	@Column(name = "medium_image_url")
	public URL mediumImageUrl;
	@Column(length = 10000)
	@Size(min = 1, max = 100000)
	public String description;
	
	/**
	 * find random
	 * @return
	 */
	public static Book findRandom() {
		long countBooks = Book.count();
		int randomBook = new Random().nextInt((int) countBooks);
		return Book.findAll().page(randomBook, 1).firstResult();
	}
	
}
