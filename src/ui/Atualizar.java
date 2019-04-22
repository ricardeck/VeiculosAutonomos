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
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
public class Atualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLatitude;
	private JLabel lblAdicionarEmail;
	private VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
	private AppFreteIF app = new AppFrete(veiculoDao);
	private JTextField textLongitude;
	private JTextField textPesoCarga;
	private JTextField textMat;
	private JLabel lblMatrcula;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atualizar frame = new Atualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Atualizar() throws IOException {
		
//		int matricula = -1;
		
		setTitle("Atualizar cadastro ve\u00EDculos");
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
		txtLatitude.setBounds(119, 48, 88, 20);
		layeredPane.add(txtLatitude);
		txtLatitude.setColumns(10);
//		txtLatitude.setText(Integer.toString(veiculoDao.findByMatricula(Integer.parseInt(matricula)).getLatitude()));

		lblAdicionarEmail = new JLabel("Latitude:");
		lblAdicionarEmail.setBounds(10, 51, 138, 14);
		layeredPane.add(lblAdicionarEmail);
		
		textLongitude = new JTextField();
		textLongitude.setColumns(10);
		textLongitude.setBounds(119, 86, 88, 20);
		layeredPane.add(textLongitude);
//		textLongitude.setText(Integer.toString(veiculoDao.findByMatricula(Integer.parseInt(matricula)).getLongitude()));
		
		JLabel lblLongitudeOrigem = new JLabel("Longitude:");
		lblLongitudeOrigem.setBounds(10, 89, 111, 14);
		layeredPane.add(lblLongitudeOrigem);
		
		textPesoCarga = new JTextField();
		textPesoCarga.setColumns(10);
		textPesoCarga.setBounds(119, 122, 88, 20);
		layeredPane.add(textPesoCarga);
//		textPesoCarga.setText(Integer.toString(veiculoDao.findByMatricula(Integer.parseInt(matricula)).getCapacidade()));
		
		JLabel lblPesoDaCarga = new JLabel("Limite de carga:");
		lblPesoDaCarga.setBounds(10, 125, 111, 14);
		layeredPane.add(lblPesoDaCarga);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setToolTipText("");
		btnAtualizar.setBounds(299, 283, 127, 34);
		layeredPane.add(btnAtualizar);
		

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/img/ficha.png")));
		label.setBounds(21, 153, 127, 176);
		layeredPane.add(label);
		
		textMat = new JTextField();
		textMat.setText("0");
		textMat.setColumns(10);
		textMat.setBounds(119, 11, 88, 20);
		layeredPane.add(textMat);
		
		lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(10, 14, 138, 14);
		layeredPane.add(lblMatrcula);
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (txtLatitude.getText().isEmpty() || textLongitude.getText().isEmpty() ||
							txtLatitude.getText().isEmpty() || textLongitude.getText().isEmpty() ||
							textPesoCarga.getText().isEmpty() || textMat.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Campo em branco");
							
					}else if(!app.ehInteiro(textMat.getText()) || !app.ehInteiro(txtLatitude.getText()) || !app.ehInteiro(textLongitude.getText())|| 
							!app.ehInteiro(textPesoCarga.getText())) {
							JOptionPane.showMessageDialog(null, "Somente numeros inteiros e positivos");
							
//					}else if(app.posicaoExiste(Integer.parseInt(txtLatitude.getText()), Integer.parseInt(textLongitude.getText()))) {
//					    JOptionPane.showMessageDialog(null, "A posicao que tenta inserir já existe");
					
					}
//							boolean MatriculaEncontrada = veiculoDao.findByMatricula(Integer.parseInt(textMat.getText())) != null;
//					
//					if (!MatriculaEncontrada) {
//						JOptionPane.showMessageDialog(null, "Matrícula não encontrada");
//					}
					else if(veiculoDao.findByMatricula(Integer.parseInt(textMat.getText())) != null){
						
							app.atualizar(Integer.parseInt(textMat.getText()), Integer.parseInt(txtLatitude.getText()),
									      Integer.parseInt(textLongitude.getText()),Integer.parseInt(textPesoCarga.getText()));
							
							JOptionPane.showMessageDialog(null,"Veiculo atualizado com sucesso");
							dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Matrícula não encontrada");
						textMat.setText("");
						txtLatitude.setText("");
						textLongitude.setText("");
						textPesoCarga.setText("");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			        
//			        Thread.currentThread();
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
			        
			}
		});
	}
}