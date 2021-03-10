package pl.mickur.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javabootcamp.Song;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FNewSong extends JFrame {

	private JPanel contentPane;
	protected JTextField tfTitle;
	protected JTextField tfAuthor;
	protected JTextField tfAlbum;
	protected JTextField tfCategory;
	protected JButton btnAddSong;
	protected final JSpinner spinnerVotes;
	
	private Song newSong;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FNewSong frame = new FNewSong();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
//	private String hTitle;
//	private String hAuthor;
//	private String hAlbum;
//	private String hCategory;
//	private int hVotes;
//	public FNewSong(String hTitle, String hAuthor, String hAlbum, String hCategory, int hVotes) {
	public FNewSong() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBody = new JPanel();
		contentPane.add(panelBody);
		
		panelBody.setBorder(new EmptyBorder(0, 0, 17, 0));
		panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
		
		JPanel pTitile = new JPanel();
		pTitile.setBorder(new TitledBorder(null, "Title", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		panelBody.add(pTitile);
		pTitile.setLayout(new BorderLayout(0, 0));
		
		tfTitle = new JTextField();
		tfTitle.setHorizontalAlignment(SwingConstants.CENTER);
		tfTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pTitile.add(tfTitle, BorderLayout.NORTH);
		tfTitle.setColumns(10);
		
		JPanel pAuthor = new JPanel();
		pAuthor.setBorder(new TitledBorder(null, "Author", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		panelBody.add(pAuthor);
		pAuthor.setLayout(new BorderLayout(0, 0));
		
		tfAuthor = new JTextField();
		tfAuthor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		pAuthor.add(tfAuthor, BorderLayout.NORTH);
		tfAuthor.setColumns(10);
		
		JPanel pAlbum = new JPanel();
		panelBody.add(pAlbum);
		pAlbum.setBorder(new TitledBorder(null, "Album", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		pAlbum.setLayout(new BorderLayout(0, 0));
		
		tfAlbum = new JTextField();
		tfAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		tfAlbum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pAlbum.add(tfAlbum, BorderLayout.NORTH);
		tfAlbum.setColumns(10);
		
		JPanel pCategory = new JPanel();
		pCategory.setBorder(new TitledBorder(null, "Category", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		panelBody.add(pCategory);
		pCategory.setLayout(new BorderLayout(0, 0));
		
		tfCategory = new JTextField();
		tfCategory.setHorizontalAlignment(SwingConstants.CENTER);
		tfCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pCategory.add(tfCategory, BorderLayout.NORTH);
		tfCategory.setColumns(10);
		
		JPanel pVotes = new JPanel();
		pVotes.setBorder(new TitledBorder(null, "Votes", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		panelBody.add(pVotes);
//		pVotes.setVisible(false);
		pVotes.setLayout(new BorderLayout(0, 0));
		
		spinnerVotes = new JSpinner();
		spinnerVotes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pVotes.add(spinnerVotes, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnAddSong = new JButton("Add Song");
		panel.add(btnAddSong, BorderLayout.NORTH);
		
		
		
		
		
		
	}

}
