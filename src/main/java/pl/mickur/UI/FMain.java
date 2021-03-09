package pl.mickur.UI;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import javax.xml.parsers.*;
import java.io.*;

import org.xml.sax.SAXException;

import javabootcamp.Album;
import javabootcamp.Author;
import javabootcamp.Song;

import java.awt.BorderLayout;

public class FMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FMain window = new FMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private void selectFileWindow() {
		
		JFileChooser fileChooser = new JFileChooser();
		
		int result = fileChooser.showOpenDialog(frame);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    
//		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    
		    if (selectedFile.canRead()) {
		    	parseXML(selectedFile);
		    }
		}
	}
	
	private String getElementNameByTagName(Element eElement, String tagName) {
		return eElement.getElementsByTagName(tagName).item(0).getTextContent(); 
	}
	
	private void parseXML(File file) {
		try {

    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	    org.w3c.dom.Document doc = dBuilder.parse(file);

    	    doc.getDocumentElement().normalize();
  
    	    NodeList nList = doc.getElementsByTagName("song");

    	    for (int i=0; i<nList.getLength(); i++) {

    	        Node nNode = nList.item(i);
    	                
    	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    	            Element eElement = (Element) nNode;
    	            String title = getElementNameByTagName(eElement, "title");
    	            String author = getElementNameByTagName(eElement, "author");
    	            String album = getElementNameByTagName(eElement, "album");
    	            String category = getElementNameByTagName(eElement, "category");
    	            String votes = getElementNameByTagName(eElement, "votes");
    	            
//    	            System.out.println(title);
//    	            System.out.println(author);
//    	            System.out.println(album);
//    	            System.out.println(category);
//    	            System.out.println(votes);
    	            
    	            addSongByStrings(title, author, album, category, votes);
    	        }
    	    }
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	}
	
	private Author getAuthorByName(String name) {
		Author author = null;
		for (Author a : listAuthors) {
			if (a.getName().equals(name))
				return a;
		}
		
		author = new Author(name);
		listAuthors.add(author);
		return author;
	}
	
	private Album getAlbumByAuthorAndName(Author author, String name) {
		Album album = null;
		List<Album> listAlbumsOfArtist = author.getAlbums();
		for (Album a : listAlbumsOfArtist) {
			if (a.getName().equals(name))
				return a;
		}
		album = new Album(name);
		listAlbums.add(album);
		return album;
	}
	
	private void addSongByStrings(String title, String authorName, String albumName, String category, String votes) {
		Author author = getAuthorByName(authorName);
		Album album = getAlbumByAuthorAndName(author, albumName);
		Song song = new Song(title, author, album, category, Integer.parseInt(votes));
		listSongs.add(song);
	}
	
	private List<Song> listSongs;
	private List<Author> listAuthors;
	private List<Album> listAlbums;
	
	public FMain() {
		initialize();
		
		listSongs = new ArrayList<Song>();
		listAuthors = new ArrayList<Author>();
		listAlbums = new ArrayList<Album>();
		
		selectFileWindow();
		
//		printSongs();
//		printAuthors();
//		printAlbums();
		
		
	}

	private void printSongs() {
		for (Song s : listSongs) {
			System.out.println(s.getTitle());
			
		}
	}
	private void printAlbums() {
		for (Album a : listAlbums) {
			System.out.println(a.getName());
//			for (Song s : a.getListOfSongs()) {
//				System.out.println(s.getTitle());
//			}
		}
	}
	private void printAuthors() {
		for (Author author : listAuthors) {
			System.out.println(author.getName());
//			for (Album album : author.getAlbums()) {
//				System.out.println(album.getName());
//			}
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel headerPanel = new JPanel();
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JPanel footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
	}

}
