package javabootcamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Song {
	private String title;
	private Author author;
	private Album album;
	private Category category;
	private int votes;
	private boolean canVote;
	public String getTitle() {
		return title;
	}
	
	public Song(String title, Author author, Album album, Category category, int votes) {
		super();
		this.title = title;
		this.author = author;
		this.album = album;
		this.category = category;
		this.votes = votes;
		this.canVote = true;
	}
	
	public boolean isCanVote() {
		return canVote;
	}

	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	@XmlElement
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Album getAlbum() {
		return album;
	}
	@XmlElement
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Category getCategory() {
		return category;
	}
	@XmlElement
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getVotes() {
		return votes;
	}
	@XmlElement
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	

}
