package javabootcamp;

import com.opencsv.bean.CsvBindByName;

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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	
	

}
