/**
 * @author Ramírez García Juana Rubí
 * @author Valle Rodriguez Julio Cesar
 */

package compilador;

//Clase para realizar el análisis léxico
import compilador.lexico.AnalisisLexico;
import compilador.semantico.AnalisisSemantico;

//Clase para realizar el análisis sintáctico
import compilador.sintactico.AnalisisSintactico;

//Componentes para la interfaz
import javax.swing.JFrame;                                                      
import javax.swing.ImageIcon;                                                   

//Componentes para Look And Feel
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.UIManager;

//Componentes para abrir el archivo
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//Componente para usar ArrayList
import java.util.ArrayList;

//Componente para Mensajes
import javax.swing.JOptionPane;

//Componente para mostrar tablas de simbolos
import javax.swing.table.DefaultTableModel;

public class main_compiler extends JFrame{
    
    private File a_Archivo;                                                     // Archivo que se usara para guardar y abrir el codigo fuente
    private ArrayList <Token> a_TablaSimbolos = new ArrayList <Token>();        // ArrayList para Tabla de Simbolos
    private ArrayList <Token> a_TablaLexico = new ArrayList <Token>();          // ArrayList para Tabla de Simbolos
    private boolean a_bnGuardaArchivo;                                          // Bandera para Guardar Archivo
    private boolean a_bdLexico=false;                                           // Bandera del Análisis Léxico
    private boolean a_bdSintactico=false;                                       // Bandera del Análisis Léxico
    private boolean a_bdSemantico=false;                                        // Bandera del Análisis Léxico
    private String a_codigoFuente="";                                           // Se crea una variable que contendra todo el texto del documento (a_codigoFuente)
    private boolean a_Color=false;
            
    public main_compiler() {
        initComponents();                                                       // Inicialización de componentes
        ImageIcon img = new ImageIcon("src/compilador/resource/compiler.png");  // Carga un icono para la aplicación
        setIconImage(img.getImage());                                           // Asigna ícono al proyecto
        setLocationRelativeTo(null);                                            // Centra la ventana
        TextLineNumber tln_contador=new TextLineNumber(a_txtpCodigo);           // Carga la libreria TextLineNumber para numerar las lineas de codigo
        a_scrllCodigo.setRowHeaderView(tln_contador);                           // Agregar la libreria al scroll del codigo
        setExtendedState(this.MAXIMIZED_BOTH);                                  // Maximiza la ventana
        setVisible(true);                                                       // Hace visible la ventana al usuario
    }
    
    //Generacion de Componente
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        a_ToolBar = new javax.swing.JToolBar();
        a_btnNuevo = new javax.swing.JButton();
        a_btnAbrir = new javax.swing.JButton();
        a_btnGuardar = new javax.swing.JButton();
        a_btnGuardarComo = new javax.swing.JButton();
        a_btnCompilar = new javax.swing.JButton();
        a_pnlCodigo = new javax.swing.JPanel();
        a_scrllCodigo = new javax.swing.JScrollPane();
        a_txtpCodigo = new javax.swing.JTextPane();
        a_pnlSimbolos = new javax.swing.JPanel();
        a_scrllSimbolos = new javax.swing.JScrollPane();
        a_tblSimbolos = new javax.swing.JTable();
        a_pnlConsola = new javax.swing.JPanel();
        a_scrllConsola = new javax.swing.JScrollPane();
        a_txtaConsola = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        a_btnLexico = new javax.swing.JButton();
        a_btnSintactico = new javax.swing.JButton();
        a_btnSemantico = new javax.swing.JButton();
        a_MenuBar = new javax.swing.JMenuBar();
        a_mnuArchivo = new javax.swing.JMenu();
        a_mniNuevo = new javax.swing.JMenuItem();
        a_mniAbrir = new javax.swing.JMenuItem();
        a_mniGuardar = new javax.swing.JMenuItem();
        a_mniGuardarComo = new javax.swing.JMenuItem();
        a_mniSalir = new javax.swing.JMenuItem();
        a_mnuHerramientas = new javax.swing.JMenu();
        a_mniCompilar = new javax.swing.JMenuItem();
        a_mnuAyuda = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");

        a_ToolBar.setFloatable(false);
        a_ToolBar.setRollover(true);

        a_btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/new.png"))); // NOI18N
        a_btnNuevo.setText("Nuevo");
        a_btnNuevo.setFocusable(false);
        a_btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        a_btnNuevo.setMaximumSize(new java.awt.Dimension(100, 47));
        a_btnNuevo.setMinimumSize(new java.awt.Dimension(100, 47));
        a_btnNuevo.setPreferredSize(new java.awt.Dimension(100, 47));
        a_btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnNuevoActionPerformed(evt);
            }
        });
        a_ToolBar.add(a_btnNuevo);

        a_btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/open.png"))); // NOI18N
        a_btnAbrir.setText("Abrir");
        a_btnAbrir.setFocusable(false);
        a_btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        a_btnAbrir.setMaximumSize(new java.awt.Dimension(120, 47));
        a_btnAbrir.setMinimumSize(new java.awt.Dimension(120, 47));
        a_btnAbrir.setPreferredSize(new java.awt.Dimension(100, 47));
        a_btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnAbrirActionPerformed(evt);
            }
        });
        a_ToolBar.add(a_btnAbrir);

        a_btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/save.png"))); // NOI18N
        a_btnGuardar.setText("Guardar");
        a_btnGuardar.setEnabled(false);
        a_btnGuardar.setFocusable(false);
        a_btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        a_btnGuardar.setMaximumSize(new java.awt.Dimension(120, 47));
        a_btnGuardar.setMinimumSize(new java.awt.Dimension(120, 47));
        a_btnGuardar.setPreferredSize(new java.awt.Dimension(120, 47));
        a_btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnGuardarActionPerformed(evt);
            }
        });
        a_ToolBar.add(a_btnGuardar);

        a_btnGuardarComo.setText("Guardar como...");
        a_btnGuardarComo.setEnabled(false);
        a_btnGuardarComo.setFocusable(false);
        a_btnGuardarComo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        a_btnGuardarComo.setMaximumSize(new java.awt.Dimension(200, 47));
        a_btnGuardarComo.setMinimumSize(new java.awt.Dimension(200, 47));
        a_btnGuardarComo.setPreferredSize(new java.awt.Dimension(200, 47));
        a_btnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnGuardarComoActionPerformed(evt);
            }
        });
        a_ToolBar.add(a_btnGuardarComo);

        a_btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/build.png"))); // NOI18N
        a_btnCompilar.setText("Compilar");
        a_btnCompilar.setToolTipText("Compilar");
        a_btnCompilar.setEnabled(false);
        a_btnCompilar.setFocusable(false);
        a_btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        a_btnCompilar.setMaximumSize(new java.awt.Dimension(120, 47));
        a_btnCompilar.setMinimumSize(new java.awt.Dimension(120, 47));
        a_btnCompilar.setPreferredSize(new java.awt.Dimension(120, 47));
        a_btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnCompilarActionPerformed(evt);
            }
        });
        a_ToolBar.add(a_btnCompilar);

        a_pnlCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder("Codígo"));

        a_txtpCodigo.setEnabled(false);
        a_txtpCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                a_txtpCodigoMousePressed(evt);
            }
        });
        a_txtpCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                a_txtpCodigoKeyPressed(evt);
            }
        });
        a_scrllCodigo.setViewportView(a_txtpCodigo);

        javax.swing.GroupLayout a_pnlCodigoLayout = new javax.swing.GroupLayout(a_pnlCodigo);
        a_pnlCodigo.setLayout(a_pnlCodigoLayout);
        a_pnlCodigoLayout.setHorizontalGroup(
            a_pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a_pnlCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_scrllCodigo)
                .addContainerGap())
        );
        a_pnlCodigoLayout.setVerticalGroup(
            a_pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a_pnlCodigoLayout.createSequentialGroup()
                .addComponent(a_scrllCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        a_pnlSimbolos.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla de Simbolos"));

        a_tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        a_scrllSimbolos.setViewportView(a_tblSimbolos);

        javax.swing.GroupLayout a_pnlSimbolosLayout = new javax.swing.GroupLayout(a_pnlSimbolos);
        a_pnlSimbolos.setLayout(a_pnlSimbolosLayout);
        a_pnlSimbolosLayout.setHorizontalGroup(
            a_pnlSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a_pnlSimbolosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_scrllSimbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        a_pnlSimbolosLayout.setVerticalGroup(
            a_pnlSimbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a_pnlSimbolosLayout.createSequentialGroup()
                .addComponent(a_scrllSimbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        a_pnlConsola.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));
        a_pnlConsola.setForeground(new java.awt.Color(255, 0, 0));

        a_txtaConsola.setEditable(false);
        a_txtaConsola.setColumns(20);
        a_txtaConsola.setForeground(new java.awt.Color(255, 51, 51));
        a_txtaConsola.setRows(5);
        a_scrllConsola.setViewportView(a_txtaConsola);

        javax.swing.GroupLayout a_pnlConsolaLayout = new javax.swing.GroupLayout(a_pnlConsola);
        a_pnlConsola.setLayout(a_pnlConsolaLayout);
        a_pnlConsolaLayout.setHorizontalGroup(
            a_pnlConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a_pnlConsolaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_scrllConsola)
                .addContainerGap())
        );
        a_pnlConsolaLayout.setVerticalGroup(
            a_pnlConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, a_pnlConsolaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_scrllConsola)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Semáforo"));

        a_btnLexico.setBackground(new java.awt.Color(0, 0, 0));
        a_btnLexico.setText("Análisis Léxico");
        a_btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnLexicoActionPerformed(evt);
            }
        });

        a_btnSintactico.setBackground(new java.awt.Color(0, 0, 0));
        a_btnSintactico.setText("Análisis Sintáctico");
        a_btnSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnSintacticoActionPerformed(evt);
            }
        });

        a_btnSemantico.setBackground(new java.awt.Color(0, 0, 0));
        a_btnSemantico.setText("Análisis Semántico");
        a_btnSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_btnSemanticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(a_btnLexico, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_btnSintactico, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_btnSemantico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a_btnLexico)
                    .addComponent(a_btnSintactico)
                    .addComponent(a_btnSemantico))
                .addContainerGap())
        );

        a_mnuArchivo.setText("Archivo");

        a_mniNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        a_mniNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/new_16.png"))); // NOI18N
        a_mniNuevo.setText("Nuevo");
        a_mniNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_mniNuevoActionPerformed(evt);
            }
        });
        a_mnuArchivo.add(a_mniNuevo);

        a_mniAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        a_mniAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/open_16.png"))); // NOI18N
        a_mniAbrir.setText("Abrir");
        a_mniAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_mniAbrirActionPerformed(evt);
            }
        });
        a_mnuArchivo.add(a_mniAbrir);

        a_mniGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        a_mniGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/save_16.png"))); // NOI18N
        a_mniGuardar.setText("Guardar");
        a_mniGuardar.setEnabled(false);
        a_mniGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_mniGuardarActionPerformed(evt);
            }
        });
        a_mnuArchivo.add(a_mniGuardar);

        a_mniGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        a_mniGuardarComo.setText("Guardar como...");
        a_mniGuardarComo.setEnabled(false);
        a_mniGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_mniGuardarComoActionPerformed(evt);
            }
        });
        a_mnuArchivo.add(a_mniGuardarComo);

        a_mniSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        a_mniSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/exit_16.png"))); // NOI18N
        a_mniSalir.setText("Salir");
        a_mniSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_mniSalirActionPerformed(evt);
            }
        });
        a_mnuArchivo.add(a_mniSalir);

        a_MenuBar.add(a_mnuArchivo);

        a_mnuHerramientas.setText("Herramientas");

        a_mniCompilar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        a_mniCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/resource/build_16.png"))); // NOI18N
        a_mniCompilar.setText("Compilar");
        a_mnuHerramientas.add(a_mniCompilar);

        a_MenuBar.add(a_mnuHerramientas);

        a_mnuAyuda.setText("Ayuda");

        jMenuItem1.setText("Casos de uso lexico");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem1);

        jMenuItem2.setText("Casos de uso sintáctico");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem2);

        jMenuItem3.setText("Casos de uso semántico");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem3);

        jMenuItem7.setText("Errores");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem7);

        jMenuItem5.setText("Gramatica");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem5);

        jMenuItem6.setText("Lenguaje");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem6);

        jMenuItem8.setText("Versiones");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        a_mnuAyuda.add(jMenuItem8);

        a_MenuBar.add(a_mnuAyuda);

        setJMenuBar(a_MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(a_ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a_pnlConsola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a_pnlCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(a_pnlSimbolos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(a_ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a_pnlCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a_pnlSimbolos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_pnlConsola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_NuevoArchivo(){
        int  v_opcion;                                                          // Variable para captura de opciones
        if(!a_txtpCodigo.getText().equals("")){                                 // Analiza si el textpane se encuentra vacio
            // Si el documento no se ecuentra vacio pregunta si se desea guardar el documento
            v_opcion=JOptionPane.showConfirmDialog(this,"¿Desea guardar el documento?");
            if(v_opcion==0){                                                    // Si la opcion es "si"
                if(m_Guardar()){                                                // Llama el metodo para guardar el documento
                    a_codigoFuente="";
                    a_txtpCodigo.setContentType("text");
                    a_txtpCodigo.setText("");                                   // Limpia el textpane para generar un nuevo digo fuente
                    a_txtpCodigo.setEnabled(true);                              // Habilita el JTextPane para ingresar el código fuente
                    a_mniGuardar.setEnabled(true);                              // Habilita el JMenuItem Guardar
                    a_mniGuardarComo.setEnabled(true);                          // Habilita el JMenuItem Guardar como
                    a_btnGuardar.setEnabled(true);                              // Habilita el JButton Guardar del JToolBar
                    a_btnGuardarComo.setEnabled(true);                          // Habilita el JButton Guardar como del JToolBar
                    a_btnCompilar.setEnabled(true);                             // Habilita el JButton Compilar del JToolBar
                    a_btnLexico.setEnabled(true);                               // 
                    a_bnGuardaArchivo=true;                                     // Cambia el estado de la bandera bnGuardarArchivo a verdadero
                }
            }
            if(v_opcion==1){                                                    // Si la opcion es "no"
                a_codigoFuente="";
                a_txtpCodigo.setContentType("text");
                a_txtpCodigo.setText("");                                       // Limpia el textpane para generar un nuevo codigo fuente
                a_txtpCodigo.setEnabled(true);                                  // Habilita el textpane para editarlo
                a_mniGuardar.setEnabled(true);                                  // Habilita el JMenuItem Guardar
                a_mniGuardarComo.setEnabled(true);                              // Habilita el JMenuItem Guardar como
                a_btnGuardar.setEnabled(true);                                  // Habilita el JButton Guardar del JToolBar
                a_btnGuardarComo.setEnabled(true);                              // Habilita el JButton Guardar como del JToolBar
                a_btnCompilar.setEnabled(true);                                 // Habilita el JButton Compilar del JToolBar
                a_btnLexico.setEnabled(true);
                a_bnGuardaArchivo=true;                                         // Cambia el estado de la bandera bnGuardarArchivo a verdadero
            }
        }else{                                                                  // Si el documento se encuentra vacio
            a_txtpCodigo.setContentType("text");
            a_txtpCodigo.setText("");                                           // Limpia el textpane para generar un nuevo codigo fuente
            a_codigoFuente="";
            a_txtpCodigo.setEnabled(true);                                      // Habilita el textpane para editarlo
            a_mniGuardar.setEnabled(true);                                      // Habilita el JMenuItem Guardar
            a_mniGuardarComo.setEnabled(true);                                  // Habilita el JMenuItem Guardar como
            a_btnGuardar.setEnabled(true);                                      // Habilita el JButton Guardar del JToolBar
            a_btnGuardarComo.setEnabled(true);                                  // Habilita el JButton Guardar como del JToolBar
            a_btnCompilar.setEnabled(true);                                     // Habilita el JButton Compilar del JToolBar
            a_btnLexico.setEnabled(true);                                       // Habilita el 
            a_bnGuardaArchivo=true;                                             // Cambia el estado de la bandera bnGuardarArchivo a verdadero
        }
    }
    
    private void m_AbrirArchivo(){
        JFileChooser v_AbrirArchivo=new JFileChooser();                         // Genera un nuevo JFileChooser para abrir el archivo
        // Filtro para mostrar solo los archivos con extension *.lya
        v_AbrirArchivo.setFileFilter(new FileNameExtensionFilter("Todos los archivos *.lya","lya","LYA"));
        int v_Seleccion =v_AbrirArchivo.showDialog(null,"Abrir");               // Se comprueba si ha aceptado abrir el archivo
        if(v_Seleccion==JFileChooser.APPROVE_OPTION){                           // Si la opcion fue aceptar
            try{
                a_codigoFuente="";
                a_Archivo = v_AbrirArchivo.getSelectedFile();                   // Se abre el documento en un JFile (a_Archivo)
                String v_Linea;                                                 // Se crea una variable para leer el documento linea por linea (v_Linea)
                FileReader v_frArchivo=new FileReader(a_Archivo);               // Se usa un FileReader para leer el archivo almacenado en a_Archivo (v_frArchivo)
                BufferedReader v_brArchivo=new BufferedReader(v_frArchivo);     // Se usa un BufferedReader para leer el archivo contenido en v_frArchivo de manera más optima (v_brArchivo)
                while((v_Linea=v_brArchivo.readLine())!=null){                  // Se lee la linea actual del BufferedReader (v_brArchivo) y se compara que sea diferente a nulo
                    a_codigoFuente+=v_Linea+"\n";                               // Si la linea es diferente de nulo añade la linea a la variable que contendra el texto del codigo fuente
                }
                a_txtpCodigo.setContentType("text");
                a_txtpCodigo.setText(a_codigoFuente);                           // Inserta el texto del documento en el JTextPane (a_txtpCodigo)
                a_txtpCodigo.setEnabled(true);                                  // Limpia el textpane para generar un nuevo codigo fuente
                a_mniGuardar.setEnabled(true);                                  // Habilita el MenuItem para Guardar
                a_mniGuardarComo.setEnabled(true);                              // Habilita el MenuItem para Guardar Como...
                a_btnGuardar.setEnabled(true);                                  // Habilita el Button para Guardar
                a_btnGuardarComo.setEnabled(true);                              // Habilita el Button para Guardar Como...
                a_btnCompilar.setEnabled(true);                                 // Habilita el Button para Compilar
                a_btnLexico.setEnabled(true);
                a_bnGuardaArchivo=false;
                v_brArchivo.close();                                            // Cierra el BufferedReader (v_brArchivo)
                v_frArchivo.close();                                            // Cierra el FileReader (v_frArchivo)
            }catch(Exception Ex){
                JOptionPane.showMessageDialog(this,"Error al abrir el archivo");//Mensaje de error al abrir el archivo
            }
        }
    }
    
    private boolean m_Guardar(){
        if(!a_bnGuardaArchivo){                                                 // Revisa la bandera para guardar el documento
            try{                                
                BufferedWriter v_bwArchivo;                                     // Crea un BufferedWriter para escribir el codigo fuente (v_bwArchivo)
                v_bwArchivo = new BufferedWriter(new FileWriter(a_Archivo));    // Inicializa el BufferedWriter para escribir el codigo fuente
                if(a_Color==false){
                    a_codigoFuente=a_txtpCodigo.getText();
                }
                v_bwArchivo.write(a_codigoFuente);                      // Escribe nuestro codigo fuente almacenado en a_txtpCodigo
                v_bwArchivo.close();                                            // Cierra el BufferedWriter para guardar los cambios en el archivo
                return true;                                                    // Regresar un true para certificar que el archivo se guardo
            }catch(Exception Ex){
                JOptionPane.showMessageDialog(this,"Error al guardar el archivo");//Mensaje de error al guardar el archivo
                return false;                                                   // Regresa un false si en documento no se pudo guardar correctamente
            }
        }else{
            return m_GuardarComo();                                             // Llama el método para guardar un nuevo documento y regresar el valro obtenido
        }
    }
    
    private boolean m_GuardarComo(){
        JFileChooser v_guardarArchivo=new JFileChooser();                       // Genera un nuevo JFileChooser para guardar el archivo
        //Filtro para mostrar solo los archivos con extension *.lya
        v_guardarArchivo.addChoosableFileFilter(new FileNameExtensionFilter("Todos los archivos *.lya","lya","LYA"));
        int v_Seleccion = v_guardarArchivo.showSaveDialog(null);                // Comprueba que haya presionado aceptar
        if(v_Seleccion==JFileChooser.APPROVE_OPTION){                           // Si acepto guardar el archivo
            try{
                a_Archivo = v_guardarArchivo.getSelectedFile();                 // Se genera el archivo en un File (a_Archivo)
                String v_Path= a_Archivo.getAbsolutePath();                     // Obtenemos el path del archivo a guardar
                PrintWriter v_pwArchivo = new PrintWriter(a_Archivo);           // Se genera un PrintWriter para escribir nuestro archivo en el disco duro
                if(a_Color==false){
                    a_codigoFuente=a_txtpCodigo.getText();
                }
                v_pwArchivo.print(a_codigoFuente);                      // Se escribe el codigo fuente almacenado en a_txtpCodigo
                v_pwArchivo.close();                                            // Se cierra el archivo para escribirlo
                if(!v_Path.endsWith(".lya")){                                   // Se comprueba que el archivo se haya guardado en la ruta correcta
                    File v_tempArchivo = new File(v_Path+".lya");               // Si no genera un nuevo archivo para renombrarlo
                    a_Archivo.renameTo(v_tempArchivo);                          // Renombra el nuevo archivo
                }
                a_bnGuardaArchivo=false;                                        // Cambia la bandera a_bnGuardaArchivo a falso
                return true;                                                    // Regresar un true para certificar que el archivo se guardo
            }catch(Exception Ex){
                JOptionPane.showMessageDialog(this,"Error al guardar el archivo");//Mensaje en caso de error al guardar el archivo
                return false;                                                   // Regresa un false si en documento no se pudo guardar correctamente
            }
        }else{
            return false;                                                       // Regresa un false si el documento no se desea guardar
        }
    }
    
    private void m_Lexico(){
        /*
        String v_Linea;                                                         // Se crea una variable para leer el documento linea por linea
        try{
            a_txtaConsola.setText("");                                          // Limpia la consola de errores
            a_TablaSimbolos = new ArrayList<Token>();                           // Limpia la tabla de simbolos
            a_TablaLexico = new ArrayList<Token>();                             // Limpia la tabla de simbolos
            a_bdLexico=false;                                                   // Reinicia para la bandera del análisis léxico
            FileReader v_frArchivo=new FileReader(a_Archivo);                   // Se usa un FileReader para leer el documento (v_frArchivo)
            BufferedReader v_brArchivo=new BufferedReader(v_frArchivo);         // Se usa un BufferedReader para leer el archivo contenido en v_frArchivo de manera más optima (v_brArchivo)
            while((v_Linea=v_brArchivo.readLine())!=null){                      // Se lee la linea actual del BufferedReader (v_brArchivo) y se compara que sea diferente a nulo
                a_codigoFuente+=v_Linea+"\n";                                   // Si la linea es diferente de nulo añade la linea a la variable que contendra el texto del documento
            }
            v_brArchivo.close();                                                // Cierra el BufferedReader (v_brArchivo)
            v_frArchivo.close();                                                // Cierra el FileReader (v_brArchivo)
        }catch(Exception Ex){
            System.out.println(Ex.getMessage());
            JOptionPane.showMessageDialog(this,"Error al abrir el archivo");    // Mensaje en caso de error al abrir el archivo
        }*/
        AnalisisLexico o_anaLexico=new AnalisisLexico(a_codigoFuente);          // Llama a la clase para el analisis léxico
        a_TablaSimbolos=o_anaLexico.m_getTablaDeSimbolos();                   // Obtiene la tabla de simbolos generada
        a_TablaLexico=o_anaLexico.m_getTablaLexico();
        a_txtaConsola.setText(o_anaLexico.m_getConsola());                      // Obtiene los errores encontrados en el analisis
        a_bdLexico=o_anaLexico.m_getLexico();                                   // Obtiene la bandera para continuar con el analisis sintáctico

        a_txtpCodigo.setContentType("text/html");
        a_txtpCodigo.setText(o_anaLexico.m_getCodFuenteHTML()+"</pre>");

        m_muestraTabla();                                                       // Muetra la tabla de simbolos
        if(a_bdLexico){                                                         // Analiza la bandera el analisis léxico
            a_btnLexico.setBackground(Color.GREEN);                             // Si es correcto pone en boton en verde (Correcto)
            a_btnSintactico.setEnabled(true);                                   // Habilita el botón para el análisis sintáctico
            a_btnSintactico.setBackground(Color.YELLOW);                        // El botón del análisis sintáctico se pone en amarillo (Preparado)
        }else{
            a_btnLexico.setBackground(Color.RED);                               // El botón del análisis léxico se pone en rojo (Falló)
        }
    }
    
    void m_muestraTabla(){
        String[] v_tblModel=new String[]{"No","Lexema","TipoLexema","Tipo","Valor","Linea","ID"};
        DefaultTableModel v_Modelo=new DefaultTableModel(null,v_tblModel);
        a_tblSimbolos.setModel(v_Modelo);
        String[] v_Datos={"","","","","","",""};
        for(int v_indice=0;v_indice<a_TablaSimbolos.size();v_indice++){
            Token v_Temporal=a_TablaSimbolos.get(v_indice);
            v_Datos[0]=v_Temporal.m_getNoToken()+"";
            v_Datos[1]=v_Temporal.m_getLexema();
            v_Datos[2]=v_Temporal.m_getTipoLexema()+"";
            v_Datos[3]=v_Temporal.m_getTipo()+"";
            v_Datos[4]=v_Temporal.m_getValor()+"";
            v_Datos[5]=v_Temporal.m_getNoLinea()+"";
            v_Datos[6]=v_Temporal.m_getIdToken()+"";
            v_Modelo.addRow(v_Datos);
        }
    }
    
    private void m_Sintactico(){
        AnalisisSintactico o_analisisSintactico = new AnalisisSintactico(a_TablaLexico,a_TablaSimbolos);
        a_TablaSimbolos=o_analisisSintactico.m_getTabla();
        a_TablaLexico=o_analisisSintactico.m_getLexico();
        a_txtaConsola.setText(a_txtaConsola.getText()+o_analisisSintactico.m_getConsola());
        m_muestraTabla();
        if (o_analisisSintactico.m_getError()) {
            a_btnSintactico.setBackground(Color.RED);                           // El botón del análisis léxico se pone en rojo (Falló)
        }else{
            a_btnSintactico.setBackground(Color.GREEN);                         // El botón del análisis léxico se pone en rojo (Falló)
            a_btnSemantico.setBackground(Color.YELLOW);                        // El botón del análisis sintáctico se pone en amarillo (Preparado)
        }
    }
    
    private void m_Semantico(){
        AnalisisSemantico o_analisisSemantico = new AnalisisSemantico(a_TablaLexico, a_TablaSimbolos);
        a_txtaConsola.setText(a_txtaConsola.getText()+o_analisisSemantico.m_getConsola());
        if (o_analisisSemantico.m_getError()) {
            a_btnSemantico.setBackground(Color.RED);                           // El botón del análisis léxico se pone en rojo (Falló)
        }else{
            a_btnSemantico.setBackground(Color.GREEN);                         // El botón del análisis léxico se pone en rojo (Falló)
        }
    }
    
    private void a_mniNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_mniNuevoActionPerformed
        m_NuevoArchivo();
    }//GEN-LAST:event_a_mniNuevoActionPerformed

    private void a_mniAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_mniAbrirActionPerformed
        m_AbrirArchivo();
    }//GEN-LAST:event_a_mniAbrirActionPerformed

    private void a_btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnCompilarActionPerformed
        if(m_Guardar()){
            m_Lexico();
            a_Color=true;
            m_Sintactico();
            m_Semantico();
        }
        
    }//GEN-LAST:event_a_btnCompilarActionPerformed

    private void a_mniGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_mniGuardarActionPerformed
        m_Guardar();
    }//GEN-LAST:event_a_mniGuardarActionPerformed

    private void a_btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnNuevoActionPerformed
        m_NuevoArchivo();
    }//GEN-LAST:event_a_btnNuevoActionPerformed

    private void a_btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnGuardarActionPerformed
        m_Guardar();
    }//GEN-LAST:event_a_btnGuardarActionPerformed

    private void a_btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnAbrirActionPerformed
        m_AbrirArchivo();
    }//GEN-LAST:event_a_btnAbrirActionPerformed

    private void a_btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnGuardarComoActionPerformed
        m_GuardarComo();
    }//GEN-LAST:event_a_btnGuardarComoActionPerformed

    private void a_mniGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_mniGuardarComoActionPerformed
        m_GuardarComo();
    }//GEN-LAST:event_a_mniGuardarComoActionPerformed

    private void a_txtpCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_a_txtpCodigoKeyPressed
        
        a_btnLexico.setBackground(Color.YELLOW);
        a_btnSintactico.setBackground(Color.BLACK);
        a_btnSemantico.setBackground(Color.BLACK);
        
    }//GEN-LAST:event_a_txtpCodigoKeyPressed

    private void a_btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnLexicoActionPerformed
        if(m_Guardar()){
            m_Lexico();
            a_Color=true;
        }
    }//GEN-LAST:event_a_btnLexicoActionPerformed

    private void a_btnSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnSintacticoActionPerformed
        if(m_Guardar()){
            m_Lexico();
            a_Color=true;
            m_Sintactico();
        }
    }//GEN-LAST:event_a_btnSintacticoActionPerformed

    private void a_mniSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_mniSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_a_mniSalirActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            File path = new File ("resource/casos_lexicos.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            File path = new File ("resource/casos_sintacticos.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            File path = new File ("resource/casos_semanticos.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void a_btnSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_btnSemanticoActionPerformed
        if(m_Guardar()){
            m_Lexico();
            a_Color=true;
            m_Sintactico();
            m_Semantico();
        }
    }//GEN-LAST:event_a_btnSemanticoActionPerformed

    private void a_txtpCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a_txtpCodigoMousePressed
        a_bdSintactico=false;
        a_Color=false;
        a_txtpCodigo.setContentType("text");
        a_txtpCodigo.setText(a_codigoFuente);
    }//GEN-LAST:event_a_txtpCodigoMousePressed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            File path = new File ("resource/errores.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            File path = new File ("resource/gramatica.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            File path = new File ("resource/lenguaje.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            File path = new File ("resource/versiones.pdf");
            Desktop.getDesktop().open(path);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static void main(String args[]) {
        try{
            UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());         //Carga el tema look and feel
        }catch (Exception e){
            e.printStackTrace();
        }
        main_compiler o_compiler=new main_compiler();                           //Generación del Objeto
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar a_MenuBar;
    private javax.swing.JToolBar a_ToolBar;
    private javax.swing.JButton a_btnAbrir;
    private javax.swing.JButton a_btnCompilar;
    private javax.swing.JButton a_btnGuardar;
    private javax.swing.JButton a_btnGuardarComo;
    private javax.swing.JButton a_btnLexico;
    private javax.swing.JButton a_btnNuevo;
    private javax.swing.JButton a_btnSemantico;
    private javax.swing.JButton a_btnSintactico;
    private javax.swing.JMenuItem a_mniAbrir;
    private javax.swing.JMenuItem a_mniCompilar;
    private javax.swing.JMenuItem a_mniGuardar;
    private javax.swing.JMenuItem a_mniGuardarComo;
    private javax.swing.JMenuItem a_mniNuevo;
    private javax.swing.JMenuItem a_mniSalir;
    private javax.swing.JMenu a_mnuArchivo;
    private javax.swing.JMenu a_mnuAyuda;
    private javax.swing.JMenu a_mnuHerramientas;
    private javax.swing.JPanel a_pnlCodigo;
    private javax.swing.JPanel a_pnlConsola;
    private javax.swing.JPanel a_pnlSimbolos;
    private javax.swing.JScrollPane a_scrllCodigo;
    private javax.swing.JScrollPane a_scrllConsola;
    private javax.swing.JScrollPane a_scrllSimbolos;
    private javax.swing.JTable a_tblSimbolos;
    private javax.swing.JTextArea a_txtaConsola;
    private javax.swing.JTextPane a_txtpCodigo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
