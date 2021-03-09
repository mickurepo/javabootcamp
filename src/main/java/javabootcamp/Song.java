package javabootcamp;

public class Song {
	private String title;
	private Author author;
	private Album album;
	private String category;
	private int votes;
	public String getTitle() {
		return title;
	}
	
	public Song(String title, Author author, Album album, String category, int votes) {
		super();
		this.title = title;
		this.author = author;
		this.album = album;
		this.category = category;
		this.votes = votes;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	

}
