package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categorias;
import model.Productos;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	static EntityManager em = fabrica.createEntityManager();
	private JLabel lblProveedor;
	private JComboBox cboProveedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(271, 106, 54, 14);
		contentPane.add(lblProveedor);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(335, 102, 78, 22);
		contentPane.add(cboProveedor);

		llenaCombo();
	}

	void llenaCombo() {
		//obtener el listado de las categorias
		List<Categorias> lstCategorias = em.createQuery("select c from Categorias c", Categorias.class).getResultList();
		//pasar el listado al combo
		cboCategorias.addItem("Seleccione..");
		for(Categorias c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		List<Proveedor> lstProveedor = em.createQuery("select pr from Proveedor p", Proveedor.class).getResultList();
		cboProveedor.addItem("Seleccione.. ");
		for(Proveedor pr : lstProveedor) {
			cboProveedor.addItem(pr.getNombre_rs());
		}
	}

	void listado() {
		// select * from tb_xxxx --> obj list
		List<Productos> lstProductos = em.createQuery("select p from Productos p", Productos.class).getResultList();
		txtSalida.setText("");
		for (Productos p: lstProductos) {
		imprimir("Codigo...: " + p.getId_prod());
		imprimir("Nombre...: " + p.getDes_prod());
		imprimir("Stock    : " + p.getStk_prod());
		imprimir("Precio   : " + p.getPre_prod());
		imprimir("Categoria : " + p.getObjCategoria().getDescripcion());
		imprimir("Proveedor : " + p.getObjProveedor().getNombre_rs());
		imprimir("----------------------------------------------");
		
		
		
		}
	}
	
	void imprimir(String msg){
		txtSalida.append(msg + 	"\n");
	}

	void registrar() {
		Productos p = new Productos();
		p.setId_prod(txtCodigo.getText());
		p.setDes_prod(txtDescripcion.getText());
		p.setStk_prod(Integer.parseInt(txtStock.getText()));
		p.setPre_prod(Double.parseDouble(txtPrecio.getText()));
		p.setIdcategoria(cboCategorias.getSelectedIndex());
		p.setEst_prod(1);  // valor x default
		p.setIdproveedor(1); // TAREA...Combo proveed
		
		em.getTransaction().begin();
		// insert into ..... values ......
		em.persist(p);
		// confirma
		em.getTransaction().commit();
		
		JOptionPane.showMessageDialog(null,"Se Registro con Exito!!");
	}
}
