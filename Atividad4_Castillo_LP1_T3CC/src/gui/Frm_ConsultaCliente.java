package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ClienteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import reporte.GenerarReporte;

@SuppressWarnings("serial")
public class Frm_ConsultaCliente extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JPanel panel;
	private JLabel lblConsultaCliente;
	private JLabel lblNombre;
	private JButton btnBuscar;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_ConsultaCliente frame = new Frm_ConsultaCliente();
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
	public Frm_ConsultaCliente() {
		setTitle("Frm Consulta Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 561);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblConsultaCliente = new JLabel("Consulta Cliente");
		lblConsultaCliente.setFont(new Font("Sylfaen", Font.ITALIC, 40));
		lblConsultaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaCliente.setBounds(10, 11, 1175, 38);
		contentPane.add(lblConsultaCliente);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("MV Boli", Font.BOLD, 23));
		lblNombre.setBounds(319, 72, 136, 20);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(465, 76, 185, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBackground(new Color(176, 224, 230));
		btnBuscar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		btnBuscar.setBounds(750, 71, 116, 38);
		contentPane.add(btnBuscar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBackground(new Color(176, 224, 230));
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		btnLimpiar.setBounds(962, 71, 107, 38);
		contentPane.add(btnLimpiar);
		
		panel = new JPanel();
		panel.setBounds(10, 120, 1264, 391);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(arg0);
		}
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent arg0) {
		String nom=txtNombre.getText().trim();
		ClienteModel model=new ClienteModel();
		List<Cliente> cli=null;
		if(nom.equals("")) {
			cli=model.lisCliente();
		}
		else {
			cli=model.lisNomCliente(nom);
		}
		
		JRBeanCollectionDataSource daS=new JRBeanCollectionDataSource(cli);
		String file="reportClientes.jasper";
		JasperPrint jaP=GenerarReporte.genera(file, daS, null);
		JRViewer jrV=new JRViewer(jaP);
		
		panel.removeAll();
		panel.add(jrV);
		panel.repaint();
		panel.revalidate();
		
		
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent arg0) {
		txtNombre.setText("");
		txtNombre.requestFocus();
		ClienteModel model=new ClienteModel();
		List<Cliente> cli=null;
		
		cli=model.lisCliente();
		
		JRBeanCollectionDataSource daS=new JRBeanCollectionDataSource(cli);
		String file="reportClientes.jasper";
		JasperPrint jaP=GenerarReporte.genera(file, daS, null);
		JRViewer jrV=new JRViewer(jaP);
		
		panel.removeAll();
		panel.add(jrV);
		panel.repaint();
		panel.revalidate();

	}
	
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			do_txtNombre_keyTyped(arg0);
		}
	}
	protected void do_txtNombre_keyTyped(KeyEvent arg0) {
			char c=arg0.getKeyChar();
        
        
        if(sinSimbolosNiNumeros(c)=='S')
        {
            getToolkit().beep();
            arg0.consume();
            
        }
	}
	
	public static char sinSimbolosNiNumeros(char c) {
		char error='N';
		if(Character.isDigit(c)||(c=='}')||(c=='{')||(c=='*')||(c=='[')||(c==']')||(c=='&')||(c=='/')||
        (c=='¿')||(c=='?')||(c=='=')||(c=='+')||(c=='-')||(c=='_')||(c=='.')||(c==';')||(c==',')||(c==')')||(c=='(')||
        (c=='%')||(c=='$')||(c=='!')||(c=='@')||(c=='|')||(c=='#')||(c=='<')||(c=='>')||(c=='¡')||(c=='°')||(c=='¬')
        ) {
			error='S';
		}
		return error;
	}
}
