package ui;

import entidades.Veiculo;
import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;

import sessao.AppFrete;
import sessao.AppFreteIF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.io.IOException;


public class Buscar extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLatOrigem;
	private JLabel lblAdicionarEmail;
	private VeiculoDAOIF veiculoDao = (VeiculoDAOIF) DaoFactory.createVeiculoDao();
	private AppFreteIF app = new AppFrete(veiculoDao);
	private JTextField textLongOrigem;
	private JTextField txtLatDestino;
	private JTextField textLongDestino;
	private JTextField textPesoCarga;
	private JLabel label;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void MensagemDeErro(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public Buscar() throws IOException {
		setTitle("Buscar Ve\u00EDculo");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 429, 395);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		layeredPane.setBackground(new Color(255, 255, 255));
		contentPane.add(layeredPane, BorderLayout.CENTER);

		txtLatOrigem = new JTextField();
		txtLatOrigem.setBounds(119, 11, 88, 20);
		layeredPane.add(txtLatOrigem);
		txtLatOrigem.setColumns(10);

		lblAdicionarEmail = new JLabel("Latitude Origem");
		lblAdicionarEmail.setBounds(10, 14, 138, 14);
		layeredPane.add(lblAdicionarEmail);
		
		textLongOrigem = new JTextField();
		textLongOrigem.setColumns(10);
		textLongOrigem.setBounds(119, 42, 88, 20);
		layeredPane.add(textLongOrigem);
		
		JLabel lblLongitudeOrigem = new JLabel("Longitude Origem");
		lblLongitudeOrigem.setBounds(10, 45, 111, 14);
		layeredPane.add(lblLongitudeOrigem);
		
		JLabel lblLatitudeDestino = new JLabel("Latitude Destino");
		lblLatitudeDestino.setBounds(10, 125, 138, 14);
		layeredPane.add(lblLatitudeDestino);
		
		txtLatDestino = new JTextField();
		txtLatDestino.setColumns(10);
		txtLatDestino.setBounds(119, 122, 88, 20);
		layeredPane.add(txtLatDestino);
		
		textLongDestino = new JTextField();
		textLongDestino.setColumns(10);
		textLongDestino.setBounds(119, 150, 88, 20);
		layeredPane.add(textLongDestino);
		
		JLabel lblLongitudeDestino = new JLabel("Longitude Destino");
		lblLongitudeDestino.setBounds(10, 153, 127, 14);
		layeredPane.add(lblLongitudeDestino);
		
		textPesoCarga = new JTextField();
		textPesoCarga.setColumns(10);
		textPesoCarga.setBounds(119, 226, 88, 20);
		layeredPane.add(textPesoCarga);
		
		JLabel lblPesoDaCarga = new JLabel("Peso da Carga");
		lblPesoDaCarga.setBounds(10, 229, 88, 14);
		layeredPane.add(lblPesoDaCarga);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setToolTipText("Consulta o melhor valor para o seu frete");
		btnConsultar.setBounds(244, 299, 138, 36);
		layeredPane.add(btnConsultar);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/img/cami.png")));
		label.setBounds(244, 75, 159, 129);
		layeredPane.add(label);
		
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Veiculo veiculo = null ;
				double custo = 0;
				
				try {
					if (txtLatOrigem.getText().isEmpty() || textLongOrigem.getText().isEmpty() ||
							txtLatDestino.getText().isEmpty() || textLongDestino.getText().isEmpty() ||
							textPesoCarga.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campo em Branco");
					}else if(!app.ehInteiro(txtLatOrigem.getText()) || !app.ehInteiro(textLongOrigem.getText())|| 
							!app.ehInteiro(txtLatDestino.getText()) || !app.ehInteiro(textLongDestino.getText())||
							!app.ehInteiro(textPesoCarga.getText())) {
							JOptionPane.showMessageDialog(null, "Somente Numeros Inteiros");
//							throws new IntegerException();
					}else {
							veiculo = app.melhorFrete(Integer.parseInt(txtLatOrigem.getText()),
													  Integer.parseInt(textLongOrigem.getText()),
													  Integer.parseInt(txtLatDestino.getText()),
													  Integer.parseInt(textLongDestino.getText()),
													  Integer.parseInt(textPesoCarga.getText()));
						
							if(veiculo==null) {
								JOptionPane.showMessageDialog(null, "Não foi encontrado Veiculo com capacidade para esta carga");
								dispose();
							}
							else {
								
							
							custo = veiculo.CustoFrete(Integer.parseInt(txtLatOrigem.getText()),
													   Integer.parseInt(textLongOrigem.getText()),
													   Integer.parseInt(txtLatDestino.getText()),
													   Integer.parseInt(textLongDestino.getText()),
													   Integer.parseInt(textPesoCarga.getText()));
							String resultado = String.format("%.2f", custo);
							JOptionPane.showMessageDialog(null,"Veiculo " + veiculo.getMatricula() + " - R$" + resultado);
							int confirma = JOptionPane.showConfirmDialog(null, "Deseja confirmar a viagem?"); 
								if(confirma == JOptionPane.YES_OPTION) {
								   app.setLocalizacao( veiculo.getMatricula(), Integer.parseInt(txtLatDestino.getText()), Integer.parseInt(textLongDestino.getText()));
								}else
									dispose();
								}
					
					       txtLatOrigem.setText(" ");
					       textLongOrigem.setText(" ");
					       txtLatDestino.setText(" ");
					       textLongDestino.setText(" ");
					       textPesoCarga.setText(" ");
//					       Thread.currentThread().sleep(3000);
					       
					       dispose();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}
}

