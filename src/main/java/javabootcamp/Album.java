package javabootcamp;

import java.util.ArrayList;
import java.util.List;

public class Album {
	private String name;
	private List<Song> listOfSongs;
	
	public Album(String name) {
		super();
		this.name = name;
		this.listOfSongs = new ArrayList<Song>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Song> getListOfSongs() {
		return listOfSongs;
	}
	public void setListOfSongs(List<Song> listOfSongs) {
		this.listOfSongs = listOfSongs;
	}
	
	

}
