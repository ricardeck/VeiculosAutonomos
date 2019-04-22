package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollBar;
import entidades.Veiculo;

import java.util.ArrayList;

import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;

import sessao.AppFrete;
import sessao.AppFreteIF;


public class Listar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
	private AppFreteIF app = new AppFrete(veiculoDao);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar frame = new Listar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Listar() throws Exception {
		setTitle("Ve\u00EDculos Cadastrados");
		setBounds(100, 100, 591, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);
		
		JList list = new JList();
		contentPane.add(list, BorderLayout.CENTER);
				
     	ArrayList<Veiculo> obj =  veiculoDao.findAll();
		DefaultListModel model = new DefaultListModel(); //para exibir no list

		for (Veiculo o : obj) 
			model.addElement(o);
		 list.setModel(model);
			
	
			
	
	}

}
