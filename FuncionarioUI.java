package view;

import model.TipoDocumento;
import model.EstadoCivil;
import dao.TipoDocumentoDAO;
import dao.EstadoCivilDAO;
import dao.FuncionarioDAO;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FuncionarioUI extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtApellido, txtDireccion, txtTelefono, txtNumeroDocumento;
    private JComboBox<String> cbSexo;
    private JButton btnAgregar, btnEditar, btnEliminar, btnLimpiar;
    private JComboBox<TipoDocumento> cbTipoDoc;
    private JComboBox<EstadoCivil> cbEstadoCivil;

    private int idSeleccionado = -1;

    public FuncionarioUI() {
        setTitle("Gestión de Funcionarios");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(9, 2));

        panelCampos.add(new JLabel("Tipo de documento:"));
        cbTipoDoc = new JComboBox<>();
        panelCampos.add(cbTipoDoc);

        panelCampos.add(new JLabel("Estado civil:"));
        cbEstadoCivil = new JComboBox<>();
        panelCampos.add(cbEstadoCivil);

        panelCampos.add(new JLabel("Nombres:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Apellidos:"));
        txtApellido = new JTextField();
        panelCampos.add(txtApellido);

        panelCampos.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion);

        panelCampos.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

        panelCampos.add(new JLabel("Número de Documento:"));
        txtNumeroDocumento = new JTextField();
        panelCampos.add(txtNumeroDocumento);

        panelCampos.add(new JLabel("Sexo:"));
        cbSexo = new JComboBox<>();
        cbSexo.addItem("M");
        cbSexo.addItem("F");
        panelCampos.add(cbSexo);

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"ID", "Nombres", "Apellidos", "Dirección", "Teléfono"});
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(panelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarTabla();
        cargarTipoDocumento();
        cargarEstadoCivil();

        btnAgregar.addActionListener(e -> agregar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtApellido.setText(modelo.getValueAt(fila, 2).toString());
                    txtDireccion.setText(modelo.getValueAt(fila, 3).toString());
                    txtTelefono.setText(modelo.getValueAt(fila, 4).toString());
                }
            }
        });
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Funcionario> lista = new FuncionarioDAO().listar();
        for (Funcionario f : lista) {
            modelo.addRow(new Object[]{
                    f.getIdFuncionario(),
                    f.getNombres(),
                    f.getApellidos(),
                    f.getDireccion(),
                    f.getTelefono()
            });
        }
    }

    private void agregar() {
        Funcionario f = new Funcionario();
        f.setNombres(txtNombre.getText());
        f.setApellidos(txtApellido.getText());
        f.setDireccion(txtDireccion.getText());
        f.setTelefono(txtTelefono.getText());
        f.setNumeroDocumento(txtNumeroDocumento.getText());
        f.setSexo(cbSexo.getSelectedItem().toString());

        TipoDocumento tipo = (TipoDocumento) cbTipoDoc.getSelectedItem();
        EstadoCivil estado = (EstadoCivil) cbEstadoCivil.getSelectedItem();
        f.setIdTipoDoc(tipo.getId());
        f.setIdEstadoCivil(estado.getId());

        new FuncionarioDAO().agregar(f);
        cargarTabla();
        limpiar();
    }

    private void editar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un funcionario para editar.");
            return;
        }

        Funcionario f = new Funcionario();
        f.setIdFuncionario(idSeleccionado);
        f.setNombres(txtNombre.getText());
        f.setApellidos(txtApellido.getText());
        f.setDireccion(txtDireccion.getText());
        f.setTelefono(txtTelefono.getText());
        f.setNumeroDocumento(txtNumeroDocumento.getText());
        f.setSexo(cbSexo.getSelectedItem().toString());

        new FuncionarioDAO().actualizar(f);
        cargarTabla();
        limpiar();
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un funcionario para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new FuncionarioDAO().eliminar(idSeleccionado);
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtNumeroDocumento.setText("");
        cbSexo.setSelectedIndex(-1);
        cbTipoDoc.setSelectedIndex(-1);
        cbEstadoCivil.setSelectedIndex(-1);
        idSeleccionado = -1;
        tabla.clearSelection();
    }

    private void cargarTipoDocumento() {
        cbTipoDoc.removeAllItems();
        TipoDocumentoDAO dao = new TipoDocumentoDAO();
        for (TipoDocumento td : dao.listar()) {
            cbTipoDoc.addItem(td);
        }
    }



    private void cargarEstadoCivil() {
        cbEstadoCivil.removeAllItems();
        EstadoCivilDAO dao = new EstadoCivilDAO();
        for (EstadoCivil ec : dao.listar()) {
            cbEstadoCivil.addItem(ec);
        }
    }
}