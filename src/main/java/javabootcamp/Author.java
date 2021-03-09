package javabootcamp;

import java.util.ArrayList;
import java.util.List;

public class Author {
	String name;
	List<Album> albums;
	public Author(String name) {
		super();
		this.name = name;
		this.albums = new ArrayList<Album>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	
	/*
	 * 
	 * DATA TO DEVELOPMENT
	 * 
	 */
	
	
	

}
