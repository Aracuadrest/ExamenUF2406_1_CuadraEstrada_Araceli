package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Envio;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculadoraEnvios extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private final ButtonGroup GrupoOrigen = new ButtonGroup();
	private final ButtonGroup GrupoDestino = new ButtonGroup();
	private JRadioButton rdbNacional;
	private AbstractButton rdbNacional_1;
	private JComboBox comboTipo;
	private JSpinner spinnerPeso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraEnvios frame = new CalculadoraEnvios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculadoraEnvios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		
		JLabel lblOrigen = new JLabel("Ciudad Origen:");
		lblOrigen.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblOrigen, "cell 0 0,alignx trailing");
		
		txtOrigen = new JTextField();
		txtOrigen.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(txtOrigen, "cell 1 0,growx");
		txtOrigen.setColumns(10);
		
		 rdbNacional = new JRadioButton("Nacional");
		GrupoOrigen.add(rdbNacional);
		rdbNacional.setSelected(true);
		rdbNacional.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(rdbNacional, "flowx,cell 1 1");
		
		JLabel lblDestino = new JLabel("Ciudad Destino:");
		lblDestino.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblDestino, "cell 0 2,alignx trailing");
		
		txtDestino = new JTextField();
		txtDestino.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtDestino.setColumns(10);
		contentPane.add(txtDestino, "cell 1 2,growx");
		
		 rdbNacional_1 = new JRadioButton("Nacional");
		GrupoDestino.add(rdbNacional_1);
		rdbNacional_1.setSelected(true);
		rdbNacional_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(rdbNacional_1, "flowx,cell 1 3");
		
		JRadioButton rdbExtranjero = new JRadioButton("Extranjero");
		GrupoOrigen.add(rdbExtranjero);
		rdbExtranjero.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(rdbExtranjero, "cell 1 1");
		
		JRadioButton rdbExtranjero_1 = new JRadioButton("Extranjero");
		GrupoDestino.add(rdbExtranjero_1);
		rdbExtranjero_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(rdbExtranjero_1, "cell 1 3");
		
		JLabel lblTipo = new JLabel("Tipo de env\u00EDo:");
		lblTipo.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblTipo, "cell 0 4,alignx trailing");
		
		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Paq 10 - Antes de las 10 h", "Paq 14 - Antes de las 14 h", "Paq 24 - Al d\u00EDa siguiente"}));
		comboTipo.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(comboTipo, "cell 1 4,growx");
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblPeso, "cell 0 5,alignx right");
		
		 spinnerPeso = new JSpinner();
		spinnerPeso.setModel(new SpinnerNumberModel(1, 1, 40, 1));
		spinnerPeso.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(spinnerPeso, "cell 1 5");
		
		JButton btnCalcular = new JButton("Calcular Precio");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculaPrecio();
			}
		});
		btnCalcular.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(btnCalcular, "cell 0 7 2 1,alignx center");
	}

	protected void calculaPrecio() {
		if (txtOrigen.getText()==null || txtOrigen.getText().isBlank() ||
				txtDestino.getText()==null || txtDestino.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Debe introducir los campos Ciudad Origen y Ciudad Destino", 
					"Compruebe los datos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String origen = txtOrigen.getText();
		boolean origenNacional = rdbNacional.isSelected();
		String destino = txtDestino.getText();
		boolean destinoNacional = rdbNacional_1.isSelected();
		String tipo = (String) comboTipo.getSelectedItem();
		int peso = (int) spinnerPeso.getValue();
		
		Envio e = new Envio(origen, origenNacional, destino, 
				destinoNacional, tipo, peso);
		
		JOptionPane.showMessageDialog(this, "El importe total del envío es: "+e.calcularImporte(), 
				"Importe del envío", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
