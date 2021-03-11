package javabootcamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

@XmlRootElement(name = "song")
public class SongHelper {
	@CsvBindByName(column = "Title")
	private String title;
	@CsvBindByName(column = "Author")
	private String author;
	@CsvBindByName(column = "Album")
	private String album;
	@CsvBindByName(column = "Category")
	private String category;
	@CsvBindByName(column = "Votes")
	private String votes;
	public String getTitle() {
		return title;
	}
	
	public SongHelper() {
		
	}
	
	public SongHelper(String title, String author, String album, String category, String votes) {
		this.title = title;
		this.author = author;
		this.album = album;
		this.category = category;
		this.votes = votes;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlElement
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	@XmlElement
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@XmlElement
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	
	

}
