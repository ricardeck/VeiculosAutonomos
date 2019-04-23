package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;

import sessao.AppFrete;
import sessao.AppFreteIF;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

import java.io.IOException;

public class Inserir extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLatitude;
	private JLabel lblAdicionarEmail;
	private VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
	private AppFreteIF app = new AppFrete(veiculoDao);
	private JTextField textLongitude;
	private JTextField textPesoCarga;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inserir frame = new Inserir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inserir() throws IOException {
		setTitle("Cadastrar ve\u00EDculos");
		setBounds(100, 100, 462, 389);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBackground(new Color(255, 255, 255));
		contentPane.add(layeredPane, BorderLayout.CENTER);

		txtLatitude = new JTextField();
		txtLatitude.setBounds(119, 11, 88, 20);
		layeredPane.add(txtLatitude);
		txtLatitude.setColumns(10);

		lblAdicionarEmail = new JLabel("Latitude:");
		lblAdicionarEmail.setBounds(10, 14, 138, 14);
		layeredPane.add(lblAdicionarEmail);
		
		textLongitude = new JTextField();
		textLongitude.setColumns(10);
		textLongitude.setBounds(119, 49, 88, 20);
		layeredPane.add(textLongitude);
		
		JLabel lblLongitudeOrigem = new JLabel("Longitude:");
		lblLongitudeOrigem.setBounds(10, 52, 111, 14);
		layeredPane.add(lblLongitudeOrigem);
		
		textPesoCarga = new JTextField();
		textPesoCarga.setColumns(10);
		textPesoCarga.setBounds(119, 85, 88, 20);
		layeredPane.add(textPesoCarga);
		
		JLabel lblPesoDaCarga = new JLabel("Limite de carga:");
		lblPesoDaCarga.setBounds(10, 88, 111, 14);
		layeredPane.add(lblPesoDaCarga);
		
		JButton btnConsultar = new JButton("Inserir");
		btnConsultar.setToolTipText("Consulta o melhor valor para o seu frete");
		btnConsultar.setBounds(299, 283, 127, 34);
		layeredPane.add(btnConsultar);
		
		JLabel lblTipoDeVeculo = new JLabel("Tipo de ve\u00EDculo:");
		lblTipoDeVeculo.setBounds(226, 14, 111, 14);
		layeredPane.add(lblTipoDeVeculo);
		
		JRadioButton rdbtnAuto = new JRadioButton("Terrestre");
		rdbtnAuto.setBackground(Color.WHITE);
		rdbtnAuto.setBounds(317, 10, 109, 23);
		layeredPane.add(rdbtnAuto);
		
		JRadioButton rdbtnDrone = new JRadioButton("Drone");
		rdbtnDrone.setBackground(Color.WHITE);
		rdbtnDrone.setBounds(317, 28, 109, 23);
		layeredPane.add(rdbtnDrone);
		
		ButtonGroup group = new ButtonGroup ();
		group.add (rdbtnAuto);
		group.add (rdbtnDrone);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/img/ficha.png")));
		label.setBounds(21, 153, 127, 176);
		layeredPane.add(label);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (txtLatitude.getText().isEmpty() || textLongitude.getText().isEmpty() ||
							txtLatitude.getText().isEmpty() || textLongitude.getText().isEmpty() ||
							textPesoCarga.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campo em branco");
							
					}else if (!rdbtnAuto.isSelected() && !rdbtnDrone.isSelected()) {
							JOptionPane.showMessageDialog(null, "Selecione uma opcao");
					}
					else if(!app.ehInteiro(txtLatitude.getText()) || !app.ehInteiro(textLongitude.getText())|| 
							!app.ehInteiro(textPesoCarga.getText())) {
							JOptionPane.showMessageDialog(null, "Somente numeros inteiros e positivos");
					}else {
							app.addVeiculo(Integer.parseInt(textPesoCarga.getText()), Integer.parseInt(txtLatitude.getText()),
									                 Integer.parseInt(textLongitude.getText()), (rdbtnAuto.isSelected() ? true : false));
							
							JOptionPane.showMessageDialog(null,"Veiculo cadastrado com sucesso");
							dispose();
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}
}

