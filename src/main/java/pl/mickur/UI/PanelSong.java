package pl.mickur.UI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javabootcamp.Album;
import javabootcamp.Author;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;

public class PanelSong extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelSong(String title, Author author, Album album, String category, int votes) {
		setBorder(new EmptyBorder(0, 0, 17, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pTitile = new JPanel();
		pTitile.setBorder(new TitledBorder(null, "Title", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		add(pTitile);
		pTitile.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setText(title);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pTitile.add(lblTitle);
		
		JPanel pAuthor = new JPanel();
		pAuthor.setBorder(new TitledBorder(null, "Author", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		add(pAuthor);
		pAuthor.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setText(author.getName());
		pAuthor.add(lblAuthor);
		
		JPanel pAlbum = new JPanel();
		add(pAlbum);
		pAlbum.setBorder(new TitledBorder(null, "Album", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		pAlbum.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAlbum = new JLabel("Album");
		lblAlbum.setText(album.getName());
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		pAlbum.add(lblAlbum);
		
		JPanel pCategory = new JPanel();
		pCategory.setBorder(new TitledBorder(null, "Category", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		add(pCategory);
		pCategory.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setText(category);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		pCategory.add(lblCategory);
		
		JPanel pVotes = new JPanel();
		pVotes.setBorder(new TitledBorder(null, "Votes", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		add(pVotes);
		pVotes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVotes = new JLabel("Votes");
		lblVotes.setText(Integer.toString(votes));
		lblVotes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVotes.setHorizontalAlignment(SwingConstants.CENTER);
		pVotes.add(lblVotes);

	}

}
