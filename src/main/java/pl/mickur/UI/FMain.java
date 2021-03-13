package pl.mickur.UI;

import java.awt.EventQueue;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Element;

import javabootcamp.Album;
import javabootcamp.Author;
import javabootcamp.Category;
import javabootcamp.Song;
import javabootcamp.SongHelper;
import javabootcamp.SongsHelper;
import javabootcamp.SongsVotesComparator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class FMain {

	// Main function
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

	// Showing JFileChooser to take file (xml or csv), then pass it on to parser (parseXML(file.xml) or parseXML(file.csv))
	private void showFileWindow() {
		JFileChooser fileChooser = new JFileChooser();
		FileFilter[] filters = fileChooser.getChoosableFileFilters();
		for (FileFilter filter : filters)
			fileChooser.removeChoosableFileFilter(filter);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Document file type", "xml", "csv");
		fileChooser.setFileFilter(filter);
		
		int result = fileChooser.showOpenDialog(frame);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();

		    if (selectedFile.canRead()) {
		    	String extension = FilenameUtils.getExtension(selectedFile.getName());
		    	if (extension.equals("xml")) 
		    		parseXML(selectedFile);		    		
		    	else if (extension.equals("csv")) 
		    		parseCSV(selectedFile);
		    }
		}
	}
	
	// Generate the list of objects List<SongHelper> (prototype of Song with all string-fields)
	// Calling the addSongByStrings(...) function
	// addSongByStrings(...) function will create the new Song object by strings ...
	// ... and will create new Album object and new Author object if not find suitable
	private void parseCSV(File file) {
		final String SAMPLE_CSV_FILE_PATH = file.getAbsolutePath();

		try (
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
		) {
			CsvToBean<SongHelper> csvToBean = new CsvToBeanBuilder<SongHelper>(reader)
					.withType(SongHelper.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			List<SongHelper> csvSongs = csvToBean.parse();

			for(SongHelper csvSong: csvSongs) {
				String title = csvSong.getTitle();
				String authorName = csvSong.getAuthor();
				String albumName = csvSong.getAlbum();
				String categoryName = csvSong.getCategory();
				String votes = csvSong.getVotes();
				
				addSongByStrings(title, authorName, albumName, categoryName, votes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Function for xml parsing. Returning the tag founded by parent tag and string tagName
	// <eElement>
	// 		<tagName></tagName>
	// </eElement>
	// "tagName" is returned
	private String getElementNameByTagName(Element eElement, String tagName) {
		return eElement.getElementsByTagName(tagName).item(0).getTextContent(); 
	}
	
	// Parsing xml from file.
	// Calling the addSongByStrings(...) function
	// addSongByStrings(...) function will create the new Song object by strings ...
	// ... and will create new Album object and new Author object if not find suitable
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
    	            
    	            addSongByStrings(title, author, album, category, votes);
    	        }
    	    }
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	}
	
	// Returning the Author object with the name if exists or creating new Author object and returning it
	// By creating a new Author it will be added to the listAuthor list
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
	
	// Returning the Album object finded by handle of Author object and the name if exists
	// (Author handle necessery. Case: 2 different albums same named can own 2 different Authors)
	// or creating new Album object and returning it
	// By creating a new Album it will be added to the listAlbums list
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
	
	// Adding 
	private boolean addSongByStrings(String title, String authorName, String albumName, String categoryName, String votes) {
		Author author = getAuthorByName(authorName);
		Album album = getAlbumByAuthorAndName(author, albumName);
		categoryName = categoryName.replaceAll("&", "N");
		categoryName = categoryName.replaceAll(" ", "_").toUpperCase();
		
		category = Category.valueOf(categoryName);
		

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
	
	// Comparing all songs in listSongs to the song,
	// returning the song object, or null if not exists same song
	private Song getSongByStrings(Song song) {
		String title = song.getTitle();
        String author = song.getAuthor().getName();
        String album = song.getAlbum().getName();
        
        for (Song s : listSongs) {
        	String sTitle = s.getTitle();
        	String sAuthor = s.getAuthor().getName();
        	String sAlbum = s.getAlbum().getName();
        	if (sTitle.equals(title) &&
        		sAuthor.equals(author) &&
        		sAlbum.equals(album))
        		return s;
        }
		return null;
	}	
	
	// Main function, initializing the frame, shows the JFileChooser to get a file with data
	// initializing new lists during opening the program
	public FMain() {
		listSongs = new ArrayList<Song>();
		listAuthors = new ArrayList<Author>();
		listAlbums = new ArrayList<Album>();
		
		initialize();
		showFileWindow();
		showSongs(listSongs, panelSongs);
	}

	// Unnecessery method for developers
	private void printSongs(List<Song> list) {
		for (Song s : list) 
			System.out.println(s.getTitle());
	}
	
	// Unnecessery method for developers
	private void printAlbums() {
		for (Album a : listAlbums) {
			System.out.println(a.getName());
		}
	}
	
	// Unnecessery method for developers
	private void printAuthors() {
		for (Author author : listAuthors) {
			System.out.println(author.getName());
		}
	}
	
	// Creating panel(s) (PanelSong) for each song from the list (List<Song>) onto the pointed panel
	// clearing the panel before, and refreshing after
	private void showSongs(List<Song> list, JPanel panel) {
		panel.removeAll();
		
		Window window = SwingUtilities.getWindowAncestor(panel);
		
		for (final Song s : list) {
			final PanelSong panelSong = new PanelSong(s);
			panel.add(panelSong);
			panelSong.btnVote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					s.setVotes(s.getVotes()+1);
					s.setCanVote(false);
					showSongs(list, panel);
					showSongs(listSongs, panelSongs);
					refreshFrame(window);
				}
			});
			panelSong.jMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					s.setVotes(0);
					s.setCanVote(true);
					showSongs(list, panel);
					showSongs(listSongs, panelSongs);
					refreshFrame(window);
				}
			});
		}
		refreshFrame(window);
	}
	
	// Returning the new list of songs sorted by votes.
	// The new list is created on the basis on listSongs (cloned)
	private List<Song> getSortedListSongsByVotes() {
		List<Song> sortedListSongs = new ArrayList<Song>(listSongs);
		Collections.sort(sortedListSongs, new SongsVotesComparator());
		return sortedListSongs;
	}
	
	// Returning the new list of songs sorted by category.
	// The new list is created on the basis on listSongs (cloned)
	private List<Song> getSortedListSongsByCategory() {
		List<Song> sortedListSongs = new ArrayList<Song>(listSongs);
		Collections.sort(sortedListSongs);
		return sortedListSongs;
	}
	
	// Refreshing the window (Window: JFrame or JDialog)
	// Used to refresh the window after putting new song's panels into it dynamicly
	private void refreshFrame(Window window) {
		SwingUtilities.updateComponentTreeUI(window);
		window.invalidate();
		window.validate();
		window.repaint();
		panelSongs.revalidate();
		panelSongs.repaint();
	}
	
	// Showing new window FRating
	// range argument is used to specify the range of list wanted (top 3, top 10, top x)
	// range specified on 0 will create rating window with full list (top all)
	// argument title specifing the name (title) of the window (JFame)
	private void rating(int range, String title) {
		if (range > 0) {
			if (range <= sortedListSongs.size())
				sortedListSongs = sortedListSongs.subList(0, range);
		}
		final FRating fRating = new FRating();	
		fRating.setTitle(title);
		showSongs(sortedListSongs, fRating.getPanelSongs());
			
		fRating.getBtnSaveRaport().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fRating.getExtension().equals("XML")) {
					try {
						saveToXml(sortedListSongs);
					} catch (FileNotFoundException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if (fRating.getExtension().equals("CSV")) {
					try {
						saveToCsv(sortedListSongs);
					} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		fRating.setModal(true);
		fRating.setVisible(true);
	}
	
	// Saving the list from argument into csv file
	// the function setfileExtension(...) will put the extension .csv to filename if missing
	private void saveToCsv(List<Song> list) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave = null;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    fileToSave = fileChooser.getSelectedFile();
		    fileToSave = setFileExtenstion(fileToSave, "csv");
		    
		    try (
		            Writer writer = Files.newBufferedWriter(Paths.get(fileToSave.getAbsolutePath()));
		        ) {
	            StatefulBeanToCsv<SongHelper> beanToCsv = new StatefulBeanToCsvBuilder<SongHelper>(writer)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();

	            List<SongHelper> mySongs = new ArrayList<SongHelper>();
	            
	            for (Song s : sortedListSongs) {
	            	String title = s.getTitle();
	            	String author = s.getAuthor().getName();
	            	String album = s.getAlbum().getName();
	            	String category = s.getCategory().toString();
	            	String votes = Integer.toString(s.getVotes());
	            	
	            	SongHelper songHelper = new SongHelper(title, author, album, category, votes);
	            	mySongs.add(songHelper);
	            }
	            
	            beanToCsv.write(mySongs);
	        }
		}
	}
	
	// Correct the file extension (adding wanted extension) if missing and returning corrected 
	private File setFileExtenstion(File file, String extDest) {
		String ext = FilenameUtils.getExtension(file.getName());
		if (!ext.equals(extDest))
			return new File(file.getAbsolutePath()+"."+extDest);
		return file;
	}
	
	// Saving the list to xml JFileChooser called inside the function
	private void saveToXml(List<Song> list) throws FileNotFoundException, JAXBException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave = null;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    fileToSave = fileChooser.getSelectedFile();
		    fileToSave = setFileExtenstion(fileToSave, "xml");
		   
			ArrayList<SongHelper> listSongHelpers = new ArrayList<SongHelper>();
			for (Song s : list) {
		    	String title = s.getTitle();
		    	String author = s.getAuthor().getName();
		    	String album = s.getAlbum().getName();
		    	String category = s.getCategory().toString();
		    	String votes = Integer.toString(s.getVotes());
		    	
		    	SongHelper songHelper = new SongHelper(title, author, album, category, votes);
		    	listSongHelpers.add(songHelper);
		    }
			
			SongsHelper songsHelper = new SongsHelper();
			songsHelper.setSongHelpers(listSongHelpers);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(SongsHelper.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		 
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		     
	//	    jaxbMarshaller.marshal(songsHelper, System.out);
		    jaxbMarshaller.marshal(songsHelper, fileToSave);
		}
	}
	
	// Loading songs into the specified list and showing them onto the panel
	private void loadSongs(List<Song> list, JPanel panel) {
		showFileWindow();
		showSongs(list, panel);
	}
	
	// Action for Clear Votes button
	// Clearing votes of all songs 
	private void clearVotes() {
		for (Song s : listSongs) {
			s.setVotes(0);
			s.setCanVote(true);
		}
		showSongs(listSongs, panelSongs);
	}
	
	// Rating button action
	private void btnRatingAction() {
		sortedListSongs = getSortedListSongsByVotes();
		rating(0, "Rating - all");
	}
	
	// Rating button action
	private void btnTop10Action() {
		sortedListSongs = getSortedListSongsByVotes();
		rating(10, "Rating - Top 10");
	}
	
	// Rating button action
	private void btnTop3Action() {
		sortedListSongs = getSortedListSongsByVotes();
		rating(3, "Rating - Top 3");
	}
	
	// Rating by category button action
	private void btnCategoryRatingAction() {
		sortedListSongs = new ArrayList<Song>(listSongs);
		Collections.sort(sortedListSongs);
		rating(0, "Rating by category");
	}
	
	private void btnAddSongAction() {
		final FNewSong fNewSong = new FNewSong();
		fNewSong.setVisible(true);
		fNewSong.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = fNewSong.tfTitle.getText();
				String author = fNewSong.tfAuthor.getText();
				String album = fNewSong.tfAlbum.getText();
				String category = fNewSong.cbCategory.getSelectedItem().toString();
				int votes = (Integer) fNewSong.spinnerVotes.getValue();
				
				if (!title.isEmpty() &&
					!author.isEmpty() &&
					!album.isEmpty() &&
					!category.isEmpty()) {
					if (addSongByStrings(title, author, album, category, Integer.toString(votes))) {
						JOptionPane.showMessageDialog(fNewSong, "This song already exists in database.", "warning", JOptionPane.WARNING_MESSAGE);
					}
					else {
						fNewSong.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						showSongs(listSongs, panelSongs);
						JOptionPane.showMessageDialog(fNewSong, "The song added succesfully.");
					}
				}
				else {
					JOptionPane.showMessageDialog(fNewSong, "The data imput is incorrect.", "warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	private JFrame frame;
	
	// Enum category of song 
	private Category category;
	
	private List<Song> listSongs;
	private List<Author> listAuthors;
	private List<Album> listAlbums;
	private List<Song> sortedListSongs;
	private JPanel panelSongs;
	
	// Initialize the main form JFrame
	// work here only to make changes in front-end
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setLocationRelativeTo(null);
		frame.setTitle("- For Coreservices Javabootcamp - by Michal Kurzyk - 2021 -");
		
		JPanel panelLeft = new JPanel();
		frame.getContentPane().add(panelLeft);
		panelLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		panelLeft.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanelContainer = new JPanel();
		headerPanel.add(headerPanelContainer);
		headerPanelContainer.setLayout(new BoxLayout(headerPanelContainer, BoxLayout.Y_AXIS));
		
		JPanel headerPanelContainerN = new JPanel();
		headerPanelContainer.add(headerPanelContainerN);
		
		JButton btnLoad = new JButton("Load Songs");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSongs(listSongs, panelSongs);
			}
		});
		headerPanelContainerN.add(btnLoad);
		
		JPanel headerPanelContainerS = new JPanel();
		headerPanelContainer.add(headerPanelContainerS);
		
		JButton btnClearVotes = new JButton("Clear all votes");
		btnClearVotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearVotes();
			}
		});
		headerPanelContainerN.add(btnClearVotes);
		
		JButton btnRating = new JButton("Rating");
		btnRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRatingAction();
			}
		});
		headerPanelContainerS.add(btnRating);
		
		JButton btnTop10 = new JButton("Top 10");
		btnTop10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTop10Action();
			}
		});
		headerPanelContainerS.add(btnTop10);
		
		JButton btnTop3 = new JButton("Top 3");
		btnTop3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTop3Action();
			}
		});
		headerPanelContainerS.add(btnTop3);
		
		JButton btnCategoryRating = new JButton("Category Rating");
		btnCategoryRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				btnCategoryRatingAction();
			}
		});
		headerPanelContainerS.add(btnCategoryRating);
		
		JPanel footerPanel = new JPanel();
		panelLeft.add(footerPanel, BorderLayout.SOUTH);
		
		JButton btnAddSong = new JButton("Add Song");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddSongAction();
			}
		});
		footerPanel.add(btnAddSong);
		
		JPanel bodyPanel = new JPanel();
		panelLeft.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		panelSongs = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panelSongs);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		panelSongs.setLayout(new BoxLayout(panelSongs, BoxLayout.Y_AXIS));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(30, 30));
		bodyPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51,51,51));
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
//		getClass().getResource(name)
		ImageIcon IC = new ImageIcon(getClass().getResource("/4.png"));
		lblNewLabel.setIcon(IC);
//		lblNewLabel.setIcon(new ImageIcon(FMain.class.getResource("/img/4.png")));
		panel.add(lblNewLabel);
	}
}
