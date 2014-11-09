package Parser;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Dokonaly
 *
 */
public class Infobox_book implements Serializable {
	private String name;
	private String translator;
	private String image;
	private String caption;
	private String author;
	private String country;
	private String language;
	private String subject;
	private String[] genre;
	private String published;

	private String pages;
	/**
	 * @return the genre
	 */
	public String[] getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String[] genre) {
		this.genre = genre;
	}
	
	private String isbn;
	private String preceded_by;
	private String followed_by;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the translator
	 */
	public String getTranslator() {
		return translator;
	}
	/**
	 * @param translator the translator to set
	 */
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @return the published
	 */
	public String getPublished() {
		return published;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(String published) {
		this.published = published;
	}
	
	/**
	 * @return the pages
	 */
	public String getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the preceded_by
	 */
	public String getPreceded_by() {
		return preceded_by;
	}
	/**
	 * @param preceded_by the preceded_by to set
	 */
	public void setPreceded_by(String preceded_by) {
		this.preceded_by = preceded_by;
	}
	/**
	 * @return the followed_by
	 */
	public String getFollowed_by() {
		return followed_by;
	}
	/**
	 * @param followed_by the followed_by to set
	 */
	public void setFollowed_by(String followed_by) {
		this.followed_by = followed_by;
	}
	
	
}
