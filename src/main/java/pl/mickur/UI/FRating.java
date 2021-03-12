package pl.mickur.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FRating extends JDialog {

	private JPanel contentPane;
	private JPanel panelSongs;
	private JButton btnSaveRaport;
	private String extension;
	protected JButton getBtnSaveRaport() {
		return btnSaveRaport;
	}
	protected String getExtension() {
		return extension;
	}
	
	protected JPanel getPanelSongs() {
		return panelSongs;
	}

	public FRating() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		extension = "XML";
		
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));;
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		JPanel headerPanel = new JPanel();
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		btnSaveRaport = new JButton("Save raport");
		headerPanel.add(btnSaveRaport);
		
		JRadioButton rdbtnXml = new JRadioButton("XML");
		rdbtnXml.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnXml.isSelected())
					extension = "XML";
			}
		});
		rdbtnXml.setSelected(true);
		headerPanel.add(rdbtnXml);
		
		JRadioButton rdbtnCsv = new JRadioButton("CSV");
		rdbtnCsv.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnCsv.isSelected())
					extension = "CSV";
			}
		});
		headerPanel.add(rdbtnCsv);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnXml);
	    group.add(rdbtnCsv);
		
		JPanel footerPanel = new JPanel();
		getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		
		JPanel bodyPanel = new JPanel();
		getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		panelSongs = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panelSongs);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		panelSongs.setLayout(new BoxLayout(panelSongs, BoxLayout.Y_AXIS));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(30, 30));
		bodyPanel.add(scrollPane);
	}
	
	

}
