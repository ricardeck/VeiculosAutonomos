package ui;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.IOException;
import java.awt.Font;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class Menu extends JFrame implements VeiculosAutonomosUIIF{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setForeground(new Color(175, 238, 238));
		setTitle("Busca Frete");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 476);
		
		//adiciona itens no menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu arquivo = new JMenu("Arquivo");
		JMenu editar = new JMenu("Editar");
		JMenu exibir = new JMenu("Exibir");
	    menuBar.add(arquivo);	
	    menuBar.add(editar);
	    menuBar.add(exibir);
	    
	    //adiciona subitens no menu
	    JMenuItem inserirAcao = new JMenuItem("Inserir");
        JMenuItem buscarAcao = new JMenuItem("Buscar veiculo");
        JMenuItem sairAcao = new JMenuItem("Sair");
        JMenuItem atualizarAcao = new JMenuItem("Atualizar");
        JMenuItem exibirAcao = new JMenuItem("Exibir veiculos");
		
        //agrupa a cada item correspondente
        arquivo.add(inserirAcao);
        arquivo.add(buscarAcao);
        arquivo.addSeparator();
        arquivo.add(sairAcao);
        editar.add(atualizarAcao);
        exibir.add(exibirAcao);
        
        sairAcao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
 
            }
        });
        
        inserirAcao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
 				   Inserir inserir = new Inserir();
 					inserir.setVisible(true);
 				} catch (IOException ex) {
 					ex.printStackTrace();
 				}
            }
        });
        
        buscarAcao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
				try {
					Buscar ui = new Buscar();
					ui.setVisible(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
        });
		
        atualizarAcao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
				try {
					Atualizar ui = new Atualizar();
					ui.setVisible(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
        });
        
        exibirAcao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
				Listar ui;
				try {
					ui = new Listar();
					ui.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        });
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{104, 327, 0};
		gbl_contentPane.rowHeights = new int[]{128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSejaBemvindo = new JLabel("Busca Frete");
		lblSejaBemvindo.setIcon(new ImageIcon(Menu.class.getResource("/img/bussola.png")));
		lblSejaBemvindo.setFont(new Font("MS PGothic", Font.BOLD, 30));
		GridBagConstraints gbc_lblSejaBemvindo = new GridBagConstraints();
		gbc_lblSejaBemvindo.insets = new Insets(0, 0, 5, 0);
		gbc_lblSejaBemvindo.anchor = GridBagConstraints.WEST;
		gbc_lblSejaBemvindo.gridx = 1;
		gbc_lblSejaBemvindo.gridy = 0;
		contentPane.add(lblSejaBemvindo, gbc_lblSejaBemvindo);
		
		JButton button = new JButton("Inserir veículo");
		button.setFont(new Font("MS PGothic", Font.BOLD, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   try {
				   Inserir inserir = new Inserir();
					inserir.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton btnBuscar = new JButton("Buscar veículo");
		btnBuscar.setFont(new Font("MS PGothic", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Buscar ui;
				try {
					ui = new Buscar();
					ui.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 1;
		gbc_rigidArea_1.gridy = 1;
		contentPane.add(rigidArea_1, gbc_rigidArea_1);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_2.gridx = 1;
		gbc_rigidArea_2.gridy = 2;
		contentPane.add(rigidArea_2, gbc_rigidArea_2);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 4;
		contentPane.add(btnBuscar, gbc_btnBuscar);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 1;
		gbc_rigidArea.gridy = 5;
		contentPane.add(rigidArea, gbc_rigidArea);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 1;
		gbc_button.gridy = 7;
		contentPane.add(button, gbc_button);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_3.gridx = 1;
		gbc_rigidArea_3.gridy = 8;
		contentPane.add(rigidArea_3, gbc_rigidArea_3);
		
		JButton buttonUpdate = new JButton("Atualizar ve\u00EDculo");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  Atualizar atualiza;
				try {
					atualiza = new Atualizar();
					atualiza.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		buttonUpdate.setFont(new Font("MS PGothic", Font.BOLD, 14));
		GridBagConstraints gbc_buttonUpdate = new GridBagConstraints();
		gbc_buttonUpdate.gridx = 1;
		gbc_buttonUpdate.gridy = 10;
		contentPane.add(buttonUpdate, gbc_buttonUpdate);
	}

	@Override
	public void exibir() throws Exception {
		Menu.main(null);
		
	}

	@Override
	public void MensagemDeErro(String msg) {
		
		
	}		


}
