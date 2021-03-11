package pl.mickur.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

public class FRating extends JDialog {

	private JPanel contentPane;
	private JPanel panelSongs;
	
	protected JPanel getPanelSongs() {
		return panelSongs;
	}

	public FRating() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel headerPanel = new JPanel();
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JButton btnSaveRaport = new JButton("Save raport");
		headerPanel.add(btnSaveRaport);
		
		JPanel footerPanel = new JPanel();
		getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		
		JPanel bodyPanel = new JPanel();
		getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		panelSongs = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panelSongs);
		panelSongs.setLayout(new BoxLayout(panelSongs, BoxLayout.Y_AXIS));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(30, 30));
		bodyPanel.add(scrollPane);
	}
	
	

}
