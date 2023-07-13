/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

/**
 *
 * @author Panana
 */
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import panels.InscriptionsPanel;
import panels.ConfigurationPanel;
public class PrincipalFrame extends JFrame
{
   //contenedor principal
    private Container _container;
    
   //componentes de tipo JPanel
    private JPanel jpLateralMenu;
    private JPanel jpLateralMenuButtons;
    private JPanel jpTitle;
    private JPanel jpBase;
    private JPanel jpCardLayoutBase; //panel base de las tarjetas
    //componentes del cardLayout
    private JPanel jpAbout;
    private ConfigurationPanel jpConfig; //panel de configuración
    private InscriptionsPanel jpInscrptions;
   //componentes de tipo JButton

    //Botones para el panel lateral
    private JButton jbInscriptionsLateral, jbConfigLateral, jbAboutLateral;
    
   //componentes de tipo JLabel
    //Etiquetas de inicio
    private JLabel jlTitle;
    //etiqueta que contendra una imagen del logo
    private JLabel jlLogo;
    
    //Componentes miembros del panel jpAbout
    private JLabel jlVersion;
    private JLabel jlDev;
    private JLabel jlDepto;
   
    
   //Objectos tipo Layout
    private CardLayout _cardLayout;
    
   //cadena para identificar los paneles del cardlayout
    private static String[] CARD_NAME = {"INSCRIPTIONS","ABOUT","CONFIG"};
    
    public PrincipalFrame()
    {
        //Configuracion base del frame principal
        setBounds(0,0,500,500);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        initComponents();
        setTitle("SIGAE");
        //Configurar colores a los jpanel
        jpLateralMenu.setBackground(new Color(53, 76, 111));
        jpLateralMenuButtons.setBackground(new Color(53, 76, 111));
        jpBase.setBackground(new Color(246, 246, 246));
        jpTitle.setBackground(new Color(252, 252, 252));
        _container.setBackground(new Color(246, 246, 246));
        jpAbout.setBackground(new Color(252, 252, 252));
        //jpConfig.setBackground(new Color(252, 252, 252));
        
        //Configurar Layouts y agregar paneles a paneles
        _container.setLayout(new BorderLayout(6, 6));
        _container.add(jpLateralMenu, BorderLayout.WEST);
        _container.add(jpBase, BorderLayout.CENTER);
        jpBase.setLayout(new BorderLayout(4, 4));
        jpBase.add(jpTitle, BorderLayout.NORTH);
        jpBase.add(jpCardLayoutBase, BorderLayout.CENTER);  
        jpCardLayoutBase.setLayout(_cardLayout);        
        jpCardLayoutBase.add(CARD_NAME[0], jpInscrptions);
        jpCardLayoutBase.add(CARD_NAME[1], jpAbout);
        jpCardLayoutBase.add(CARD_NAME[2], jpConfig);
        jpLateralMenu.setLayout(new BorderLayout());
        jpLateralMenuButtons.setLayout(new BoxLayout(jpLateralMenuButtons, BoxLayout.Y_AXIS));
        

        
        //agregando componentes al jpTitle
        jpTitle.add(jlTitle);
        
        //agregando componentes al jpLateralMenu
        jpLateralMenu.add(jlLogo, BorderLayout.NORTH);
        jpLateralMenu.add(jpLateralMenuButtons, BorderLayout.CENTER);
        jpLateralMenuButtons.add(jbInscriptionsLateral);
        jpLateralMenuButtons.add(jbConfigLateral);
        jpLateralMenuButtons.add(jbAboutLateral);
        
        //Configurando botones
        jbInscriptionsLateral.setText("Inscripciones");
        jbConfigLateral.setText("Configuración");
        jbAboutLateral.setText("Acerca de");
        
        jbInscriptionsLateral.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbConfigLateral.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbAboutLateral.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        showCard(jpCardLayoutBase, CARD_NAME[0]);
        
        //Configurar panel Acerca de
        jpAbout.setLayout(new BoxLayout(jpAbout, BoxLayout.Y_AXIS));
        jpAbout.add(jlDepto);
        jpAbout.add(jlDev);
        jpAbout.add(jlVersion);
        jlDepto.setText("Sistema de Gestión de Actividades Extraescolares");
        jlDev.setText("Desarrollador: LOPEZ HERNANDEZ JAVIER ISAC");
        jlVersion.setText("Version: 1.0");
       
        
        events();
    }  
    //Inicializar los componentes graficos
    private void initComponents()
    {
        _container = this.getContentPane();
        jpBase = new JPanel();
        jpInscrptions = new InscriptionsPanel();
        jpAbout = new JPanel();
        jpLateralMenu = new JPanel();
        jpLateralMenuButtons = new JPanel();
        jpTitle = new JPanel();
        jlTitle = new JLabel("Inscripciones");
        _cardLayout = new CardLayout();
        jpCardLayoutBase = new JPanel();
        jbAboutLateral = new JButton(new ImageIcon(ClassLoader.getSystemResource("images/acercaDe_icon.png")));
        jbConfigLateral = new JButton(new ImageIcon(ClassLoader.getSystemResource("images/config_icon.png")));
        jbInscriptionsLateral = new JButton(new ImageIcon(ClassLoader.getSystemResource("images/inscriptions_icon.png")));
        jlDepto = new JLabel();
        jlDev = new JLabel();
        jlVersion = new JLabel();
        jlLogo = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/logo_itcancun.png")));
        setIconImage(getIconImage());
        jpConfig = new ConfigurationPanel();
    }
    
    //Colocar icono en el jframe
    @Override
    public Image getIconImage()
    {
        Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/logo_itcancun_icon2.png"));
        return image;
    }
    
    //Eventos
    private void events()
    {
        jbAboutLateral.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jlTitle.setText("Acerca de");
                showCard(jpCardLayoutBase, CARD_NAME[1]);
            }
        });
        
        jbConfigLateral.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jlTitle.setText("Configuración");
                 showCard(jpCardLayoutBase, CARD_NAME[2]);
            }
        });
        
        jbInscriptionsLateral.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jlTitle.setText("Inscripciones");
                showCard(jpCardLayoutBase, CARD_NAME[0]);
                
            }
        });

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt)
            {
               System.exit(0); 
            }
        });
    }
    
    
    //Metodos para el control de las cartas del cardlayout
    private void next(JPanel jpCardLayoutBase)
    {
        _cardLayout.next(jpCardLayoutBase);
    }
    
    private void previousCard(JPanel jpCardLayoutBase)
    {
        _cardLayout.previous(jpCardLayoutBase);
    }
    
    private void lastCard(JPanel jpCardLayoutBase)
    {
        _cardLayout.last(jpCardLayoutBase);
    }
    
    private void showCard(JPanel jpCardLayoutBase, String cardName)
    {
        _cardLayout.show(jpCardLayoutBase, cardName);
    }

}
