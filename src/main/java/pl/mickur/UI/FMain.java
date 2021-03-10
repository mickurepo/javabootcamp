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
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

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
	private void showFileWindow() {
		
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
	
	private boolean addSongByStrings(String title, String authorName, String albumName, String category, String votes) {
		Author author = getAuthorByName(authorName);
		Album album = getAlbumByAuthorAndName(author, albumName);
		Song song = new Song(title, author, album, category, Integer.parseInt(votes));
		
		Song existingSameSong = getSongByStrings(song);
		if (existingSameSong != null) {//means exists same song
			existingSameSong.setVotes(existingSameSong.getVotes()+song.getVotes());
			return true;
		}
		else 
			listSongs.add(song);
		return false;
			
	}
	
	private Song getSongByStrings(Song song) {
		String title = song.getTitle();
        String author = song.getAuthor().getName();
        String album = song.getAlbum().getName();
//        String category = song.getCategory();
//        int votes = song.getVotes();
        
        for (Song s : listSongs) {
        	String sTitle = s.getTitle();
        	String sAuthor = s.getAuthor().getName();
        	String sAlbum = s.getAlbum().getName();
//        	String sCategory = s.getCategory();
//        	int sVotes = s.getVotes();
        	
        	if (sTitle.equals(title) &&
        		sAuthor.equals(author) &&
        		sAlbum.equals(album))
//        		sCategory.equals(category) &&
//        		sVotes == sVotes)
        		return s;
        }
		return null;
	}
	
	private List<Song> listSongs;
	private List<Author> listAuthors;
	private List<Album> listAlbums;
	
	public FMain() {
		listSongs = new ArrayList<Song>();
		listAuthors = new ArrayList<Author>();
		listAlbums = new ArrayList<Album>();
		
		initialize();
		showFileWindow();
		showSongs();
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
	private void showSongs() {
		panelSongs.removeAll();
		for (Song s : listSongs) {
			String title = s.getTitle();
            Author author = s.getAuthor();
            Album album = s.getAlbum();
            String category = s.getCategory();
            int votes = s.getVotes();
			PanelSong panelSong = new PanelSong(title, author, album, category, votes);
			panelSongs.add(panelSong);
		}
		
		SwingUtilities.updateComponentTreeUI(frame);
		frame.invalidate();
		frame.validate();
		frame.repaint();
		panelSongs.revalidate();
		panelSongs.repaint();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private JPanel panelSongs;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel headerPanel = new JPanel();
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JButton btnLoad = new JButton("Load Songs");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFileWindow();
				showSongs();
			}
		});
		headerPanel.add(btnLoad);
		
		JPanel footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		JButton btnAddSong = new JButton("Add Song");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final FNewSong fNewSong = new FNewSong();
//				fNewSong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fNewSong.pack();
				fNewSong.setVisible(true);
				fNewSong.btnAddSong.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String title = fNewSong.tfTitle.getText();
						String author = fNewSong.tfAuthor.getText();
						String album = fNewSong.tfAlbum.getText();
						String category = fNewSong.tfCategory.getText();
						int votes = (Integer) fNewSong.spinnerVotes.getValue();
						
						if (!title.isEmpty() &&
							!author.isEmpty() &&
							!album.isEmpty() &&
							!category.isEmpty()) {
							if (addSongByStrings(title, author, album, category, Integer.toString(votes))) {
								JOptionPane.showMessageDialog(fNewSong, "This song already exists in database.");
							}
							else {
								fNewSong.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
								showSongs();
								JOptionPane.showMessageDialog(fNewSong, "The song added succesfully.");
							}
						}
						else {
							JOptionPane.showMessageDialog(fNewSong, "The data imput is incorrect.");
						}
							
						
						
						
					}
				});
			}
		});
		footerPanel.add(btnAddSong);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		panelSongs = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panelSongs);
		panelSongs.setLayout(new BoxLayout(panelSongs, BoxLayout.Y_AXIS));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(30, 30));
		bodyPanel.add(scrollPane);
		
		
		
	}

}
