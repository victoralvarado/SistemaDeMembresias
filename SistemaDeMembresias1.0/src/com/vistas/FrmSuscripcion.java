package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoBanco;
import com.dao.DaoCobertura;
import com.dao.DaoSuscriptor;
import com.dao.DaoTipoSucriptor;
import com.dao.DaoUsuario;
import com.modelo.Banco;
import com.modelo.Cobertura;
import com.modelo.Suscriptor;
import com.modelo.TipoSucriptor;
import com.modelo.Usuario;
import com.utilidades.ComboItem;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: FrmSuscripcion
 * Fecha: 04-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmSuscripcion extends javax.swing.JFrame {
    DaoSuscriptor daos = new DaoSuscriptor();
    DaoTipoSucriptor daots = new DaoTipoSucriptor();
    Suscriptor s = new Suscriptor();
    TipoSucriptor ts = new TipoSucriptor();
    DaoUsuario daou = new DaoUsuario();
    DaoCobertura daoc = new DaoCobertura();
    Usuario u = new Usuario();
    FileInputStream fis;
    int longitudBytes;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen","jpg","png","jpeg");
    Date fechaActual = new Date();
    int conta =0;
    DaoBanco daoban = new DaoBanco();
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    JasperReport reporte;
    Conexion con = new Conexion();
    public FrmSuscripcion() {
        initComponents();
        
    }
    
    public FrmSuscripcion(String fechaN){
        initComponents();
        try {
            cargarComboC(cmbDepartamento, (ArrayList<Cobertura>)daoc.mostrarDepartamento());
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionOro.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbMunicipio.setEnabled(false);
        dateNacimiento.setDate(parseFecha(fechaN));
        dateFechaActual.setDate(fechaActual);
        try {
            llenarCombo(comboSuscripcion, (ArrayList<TipoSucriptor>)daots.mostrarTipoSus());
        } catch (Exception ex) {
            Logger.getLogger(FrmSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            llenarComboBanco(comboBanco, (ArrayList<Banco>)daoban.mostrarBancos());
        } catch (Exception ex) {
            Logger.getLogger(FrmSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean calcularEdad() {
        boolean fecha;
        int yearn = dateNacimiento.getCalendar().get(Calendar.YEAR);
        int mesn = dateNacimiento.getCalendar().get(Calendar.MONTH);
        int dayn = dateNacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        for (int i = yearn; i < year; i++) {
            conta++;
        }
        if (conta < 18) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
            fecha = false;
        } else if (conta == 18 && mesn + 1 >= mes + 1 && dayn >= day) {
            JOptionPane.showMessageDialog(this, "Usted es mayor de edad \nFecha Cumple" + yearn + "-" + mesn + "-" + dayn
                    + "\n Fecha actual" + year + "-" + mes + "-" + day);
            fecha = true;
        } else if (conta == 18 && mesn + 1 >= mes + 1 && dayn < day) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
            fecha = false;
        } else if (conta == 18 && mesn + 1 < mes + 1) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
            fecha = false;
        } else {
            fecha = true;
        }
        return fecha;
    }
    public static Date parseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
    
    public void llenarCombo(JComboBox combo, ArrayList<TipoSucriptor> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getTipoSuscriptor(), item.getNombre()));
        });
    }
    
    public void llenarComboBanco(JComboBox combo, ArrayList<Banco> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdBanco(), item.getNombre()));
        });
    }
    
    public void limpiar() {
        this.fis = null;
    }
    
    public double calcularPago() {
        double pago = 0;
        switch (this.comboTiempoSus.getSelectedIndex()) {
            case 1:
                pago =  3 * daots.getCosto(this.comboSuscripcion.getSelectedIndex());
                break;
            case 2:
                pago =  6 * daots.getCosto(this.comboSuscripcion.getSelectedIndex());
                break;
            case 3:
                pago =  9 * daots.getCosto(this.comboSuscripcion.getSelectedIndex());
                break;
            case 4:
                pago =  12 * daots.getCosto(this.comboSuscripcion.getSelectedIndex());
                break;
            default:
                pago = 0;
                break;
        }
        
        return pago;
    }
    
    public void insertar() {
        try {
            if (daou.email(this.txtCorreo.getText())) {
                this.txtCorreo.requestFocus();
            } else {
                s.setNombre(this.txtNombre.getText());
                s.setApellido(this.txtApellido.getText());
                s.setEmail(this.txtCorreo.getText());
                s.setTelefono(this.txtTelefono.getText());
                s.setDireccion(txtDireccion.getText()+", "+cmbMunicipio.getSelectedItem().toString()+", "
                        + ""+cmbDepartamento.getSelectedItem().toString());
                s.setTipoSuscriptor(this.comboSuscripcion.getSelectedIndex());
                s.setFechaNacimiento(formatoFecha.format(dateNacimiento.getDate()));
                s.setTotalCompra(calcularPago());
                switch (comboTiempoSus.getSelectedIndex()) {
                    case 1:
                        s.setTiempoSus("3");
                        break;
                    case 2:
                        s.setTiempoSus("6");
                        break;
                    case 3:
                        s.setTiempoSus("9");
                        break;
                    case 4:
                        s.setTiempoSus("12");
                        break;
                    default:
                        break;
                }
                if (this.radioFemenino.isSelected()) {
                    s.setGenero("Femenino");
                } else {
                    s.setGenero("Maculino");
                }

                s.setFecha(formatoFecha.format(dateFechaActual.getDate()));
                daos.insertarSuscriptor(s);

                u.setNombre(this.txtNombre.getText());
                u.setApellido(this.txtApellido.getText());
                u.setEmail(this.txtCorreo.getText());
                u.setTipoUsuario(3);
                u.setPassword(this.txtPassword.getText());
                u.setEstado(1);
                u.setFoto(fis);
                u.setUltimoLogin("No logueado");
                u.setFecha(formatoFecha.format(dateFechaActual.getDate()));
                daou.insertarUsuario(u);
                String email = txtCorreo.getText();
                String tipoS = comboSuscripcion.getSelectedItem().toString();
                FrmProductoSuscripcion psus = new FrmProductoSuscripcion(email, tipoS);
                this.hide();
                psus.show();
                
                
            }
        } catch (Exception e) {
        }
    }
    
    public boolean validar() {
        boolean val;
        if (lblFoto.getIcon() == null) {
            btnSeleccionar.requestFocus();
            JOptionPane.showMessageDialog(null, "Selecciona una foto",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            val = true;
        } else if (this.txtNombre.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Igrese un nombre",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtNombre.requestFocus();
            val = true;
        } else if (this.txtApellido.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Igrese un apellido",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtApellido.requestFocus();
            val = true;
        } else if (validarCorreo(txtCorreo.getText())==false) {
            JOptionPane.showMessageDialog(null, "Igrese un correo valido",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtCorreo.requestFocus();
            val = true;
        } else if (validarTel(txtTelefono.getText())== false) {
            JOptionPane.showMessageDialog(null, "Igrese un telefono valido",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtTelefono.requestFocus();
            val = true;
        } else if (!radioFemenino.isSelected() && !radioMasculino.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione un genero", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            val = true;
        } else if (this.txtDireccion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Igrese una direccion",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtApellido.requestFocus();
            val = true;
        } else if (this.comboSuscripcion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo se suscripcion", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboSuscripcion.requestFocus();
            val = true;
        } else if (this.comboTiempoSus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tiempo de suscripcion", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboTiempoSus.requestFocus();
            val = true;
        } else if (this.cmbDepartamento.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un departamento", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbDepartamento.requestFocus();
            val = true;
        } else if (this.cmbMunicipio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un municipio", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbMunicipio.requestFocus();
            val = true;
        } else if (this.txtPassword.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Igrese una contraseña",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            this.txtApellido.requestFocus();
            val = true;
        } else if (this.comboBanco.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un banco", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboBanco.requestFocus();
            val = true;
        } else if (this.comboTipoTargeta.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de targeta", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboTipoTargeta.requestFocus();
            val = true;
        } else {
            val = false;
        }
        return val;
        
    }
    
    public boolean validarTel(String tel) {
        //Validacion de numero telefonico
        Pattern patternTel = Pattern.compile("^[6-7-2{1}]+([0-9]{3})(\\-[0-9]{4})$");
        Matcher mather = patternTel.matcher(tel);
        return mather.find();
    }
    
    public boolean validarCorreo(String correo) {
        Pattern patternE = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]"
                + "+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = patternE.matcher(correo);
        return mather.find();
    }
    
    private void cargarComboC(JComboBox combo, ArrayList<Cobertura> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdCobertura(), item.getDepartamento()));
        });
    }
    
    private void cargarComboM(JComboBox combo, ArrayList<Cobertura> listM) {
        combo.addItem("-- Seleccione --");
        listM.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdCobertura(), item.getMunicipio()));
        });
    }
    
    public void mostrarMunicipio(String departamento) {
        try {
            cmbMunicipio.removeAllItems();
            cargarComboM(cmbMunicipio, (ArrayList<Cobertura>) daoc.mostrarMunicipio(departamento));
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionOro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgGenero = new javax.swing.ButtonGroup();
        pnlBackground = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        dateNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        radioFemenino = new javax.swing.JRadioButton();
        radioMasculino = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        comboSuscripcion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        comboTiempoSus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        lblTotalSuscripcion = new javax.swing.JLabel();
        lblTotalTiempoSuscripcion = new javax.swing.JLabel();
        lblTotalTipoMasTiempo = new javax.swing.JLabel();
        dateFechaActual = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        btnPagarSuscripción = new javax.swing.JButton();
        lblresivira = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        comboTipoTargeta = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox<>();
        cmbMunicipio = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(203, 243, 240));
        pnlBackground.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 196, 182), 2));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Formulario de suscripción");

        jLabel2.setText("Nombres");

        jLabel3.setText("Apellidos");

        jLabel4.setText("Cooreo Electronico");

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Telefono");

        dateNacimiento.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("Fecha de nacimiento");

        bgGenero.add(radioFemenino);
        radioFemenino.setText("Femenino");

        bgGenero.add(radioMasculino);
        radioMasculino.setText("Maculino");

        jLabel7.setText("Genero");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

        jLabel8.setText("Especifique direcccion segun su municipio");

        comboSuscripcion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSuscripcionItemStateChanged(evt);
            }
        });

        jLabel9.setText("Tipo de suscripción");

        comboTiempoSus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "3 Meses", "6 Mese", "9 Meses", "12 Meses" }));
        comboTiempoSus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTiempoSusItemStateChanged(evt);
            }
        });

        jLabel10.setText("Tiempo de suscripción");

        lblTotalSuscripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSuscripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblTotalSuscripcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTotalTiempoSuscripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalTiempoSuscripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblTotalTiempoSuscripcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTotalTipoMasTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalTipoMasTiempo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblTotalTipoMasTiempo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dateFechaActual.setDateFormatString("yyyy-MM-dd");
        dateFechaActual.setEnabled(false);

        jLabel11.setText("Fecha actual:");

        btnPagarSuscripción.setText("Continuar");
        btnPagarSuscripción.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPagarSuscripciónMouseClicked(evt);
            }
        });

        lblresivira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblresivira.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblresivira.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel12.setText("Numero de targeta:");

        jLabel13.setText("Banco:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        jLabel14.setText("Por su suscripcion resivirá");

        jLabel15.setText("Total tipo suscripcion");

        jLabel16.setText("Total tiempo suscripción");

        jLabel17.setText("Total a pagar");

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setToolTipText("");
        lblFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));
        lblFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setText("Ingrese una contraseña");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseClicked(evt);
            }
        });

        comboTipoTargeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Debito", "Credito" }));

        jLabel18.setText("Tipo de targeta");

        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        jLabel20.setText("Departamento");

        jLabel21.setText("Municipio");

        jLabel22.setText("Direccion de envio de productos");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel5)
                .addGap(95, 95, 95)
                .addComponent(jLabel6)
                .addGap(68, 68, 68)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(59, 59, 59))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTotalSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotalTiempoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotalTipoMasTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(comboTipoTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnPagarSuscripción, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblresivira, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 36, Short.MAX_VALUE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 390, Short.MAX_VALUE))))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel19))
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel1))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                            .addComponent(comboSuscripcion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dateNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                            .addComponent(comboTiempoSus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(radioFemenino)
                                            .addComponent(radioMasculino)))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel9)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel10)))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(jLabel21))
                                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel20)))))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                    .addComponent(dateNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)))
                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)))
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                            .addComponent(radioFemenino)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(radioMasculino)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTiempoSus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionar)))
                        .addGap(67, 67, 67)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(3, 3, 3)
                        .addComponent(lblTotalTiempoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dateFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblresivira, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnPagarSuscripción))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotalTipoMasTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboTipoTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarSuscripciónMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPagarSuscripciónMouseClicked
        if (!validar()) {
            if (calcularEdad()) {
                insertar();
            }
        }
    }//GEN-LAST:event_btnPagarSuscripciónMouseClicked

    private void comboSuscripcionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSuscripcionItemStateChanged
        if (this.comboSuscripcion.getSelectedIndex() != 0 && this.comboTiempoSus.getSelectedIndex() != 0) {
            lblTotalSuscripcion.setText(nf.format(daots.getCosto(this.comboSuscripcion.getSelectedIndex())));
            lblTotalTiempoSuscripcion.setText(nf.format(calcularPago()));
            lblTotalTipoMasTiempo.setText(nf.format(calcularPago()));
            lblresivira.setText(daots.getDetalle(this.comboSuscripcion.getSelectedIndex()));
        } else {
            lblTotalSuscripcion.setText(nf.format(0));
            lblTotalTiempoSuscripcion.setText(nf.format(0));
            lblTotalTipoMasTiempo.setText(nf.format(0));
            lblresivira.setText("");
        }
    }//GEN-LAST:event_comboSuscripcionItemStateChanged

    private void comboTiempoSusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTiempoSusItemStateChanged
        if (this.comboSuscripcion.getSelectedIndex() != 0 && this.comboTiempoSus.getSelectedIndex() != 0) {
            lblTotalSuscripcion.setText(nf.format(daots.getCosto(this.comboSuscripcion.getSelectedIndex())));
            lblTotalTiempoSuscripcion.setText(nf.format(calcularPago()));
            lblTotalTipoMasTiempo.setText(nf.format(calcularPago()));
            lblresivira.setText(daots.getDetalle(this.comboSuscripcion.getSelectedIndex()));
        } else {
            lblTotalSuscripcion.setText(nf.format(0));
            lblTotalTiempoSuscripcion.setText(nf.format(0));
            lblTotalTipoMasTiempo.setText(nf.format(0));
            lblresivira.setText("");
        }
    }//GEN-LAST:event_comboTiempoSusItemStateChanged

    private void btnSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseClicked
        try {
            JFileChooser se = new JFileChooser();
            se.setFileFilter(filter);
            
            int estado = se.showOpenDialog(null);
            if (estado == JFileChooser.APPROVE_OPTION) {
                fis = new FileInputStream(se.getSelectedFile());
                longitudBytes = (int) se.getSelectedFile().length();

                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT);
                lblFoto.setIcon(new ImageIcon(icono));
                lblFoto.updateUI();
                lblFoto.setText("");
            }
            else {
                fis = new FileInputStream(se.getSelectedFile());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar la imagen " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSeleccionarMouseClicked

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        if (cmbDepartamento.getSelectedIndex() > 0) {
            cmbMunicipio.setEnabled(true);
            mostrarMunicipio(cmbDepartamento.getSelectedItem().toString());
        } else {
            cmbMunicipio.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        this.dispose();
        FrmLogin login = new FrmLogin();
        login.show();
    }//GEN-LAST:event_btnCancelarMouseClicked


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSuscripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgGenero;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagarSuscripción;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbMunicipio;
    private javax.swing.JComboBox<ComboItem> comboBanco;
    private javax.swing.JComboBox<String> comboSuscripcion;
    private javax.swing.JComboBox<String> comboTiempoSus;
    private javax.swing.JComboBox<String> comboTipoTargeta;
    private com.toedter.calendar.JDateChooser dateFechaActual;
    private com.toedter.calendar.JDateChooser dateNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblTotalSuscripcion;
    private javax.swing.JLabel lblTotalTiempoSuscripcion;
    private javax.swing.JLabel lblTotalTipoMasTiempo;
    private javax.swing.JLabel lblresivira;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JRadioButton radioFemenino;
    private javax.swing.JRadioButton radioMasculino;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
