package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SenkuGUI extends JFrame{
	private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	private	int Ancho=screenSize.width/2;
	private	int Alto=screenSize.height/2;
	private JFrame opciones;
	private JButton menusito;
	private JFrame SenkuGame;
	private PanelTableroPrueba juego;
	private JButton refrescar;
	private JButton cambiarColor;
	private JButton siguiente;
	private JTextField field;
	private JTextField field2;
	private JPanel Dimensiones;
	/*Frame Menu*/
	private JFrame MenuF;
	
	/*Panel Nuevo*/
    private JButton Nuevo;
    
    /*Panel Abrir*/
	private JButton Abrir;
    
    /*Panel Salvar*/
	private JButton Salvar;
	
	/*Panel Salvar Como*/
	private JButton Salvar_Como;
	
	/*Panel Salir*/
	private JButton Salir;
	
	private SenkuGUI(){
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		juego=new PanelTableroPrueba(3,7);
		setTitle("Senku");
		setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		setSize(new Dimension(Ancho,Alto));
		menusito=new JButton("Menusito");
		opciones=new JFrame("Opciones");
		add(menusito);
		/* prepara elementos menu*/
		MenuF = new JFrame("menu");
		Abrir = new JButton("Abrir");
		Salvar = new JButton("Salvar");
		Nuevo = new JButton("Nuevo");
		Salvar_Como = new JButton("Salvar Como");
		Salir = new JButton("Salir");
		SenkuGame = new JFrame("Senku!");
		refrescar=new JButton("refrescar");
		cambiarColor=new JButton("Cambiar Color");
		siguiente=new JButton("Siguiente");
		field2=new JTextField();
		field=new JTextField();
		Dimensiones=new JPanel(new GridLayout(1,2));
		Dimensiones.add(field);
		Dimensiones.add(field2);
		opciones.setSize(new Dimension(Ancho/2,Alto/2));
		opciones.setLocation((screenSize.width-(Ancho/2))/2,(screenSize.height-(Alto/2))/2);
		opciones.setLayout(new GridLayout(4,1));
		opciones.getContentPane().add(cambiarColor);
		opciones.getContentPane().add(Dimensiones);
		opciones.getContentPane().add(new JTextArea());
		opciones.getContentPane().add(siguiente);
		
		
	}
	
	public void prepareAcciones(){
		setDefaultCloseOperation(0);
        	addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent evt){
				Salga();
			}
		});
		MenuF.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent evt2){
				Salga2();
			}
		});
		menusito.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Pausa();
			}
		});
		Abrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				openChooser(Abrir);
			}
		});
		Salvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				saveChooser(Salvar);
			}
		});
		
		siguiente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String intento=field.getText();
				try{
					crearJuego(Integer.parseInt(field.getText()),Integer.parseInt(field2.getText()));
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		});
		refrescar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				refresque(3,7);
			}
		});
		cambiarColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				juego.fichasColor();
			}
		});
		juego.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ev){
				juego.ClickFicha(ev);
			}
		});
		Nuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				opciones.setVisible(true);
			}
		});
	}
	
	public void prepareElementosMenu(){
		MenuF.setLayout(new GridLayout(5,1));
		MenuF.add(Nuevo);
		MenuF.add(Abrir);
		MenuF.add(Salvar);
		MenuF.add(Salvar_Como);
		MenuF.add(Salir);
		MenuF.setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		MenuF.setSize(new Dimension(Ancho,Alto));
		MenuF.setVisible(true);
	}
	public void prepareElementosTablero(int fil, int col){
		opciones.setVisible(false);
		juego=new PanelTableroPrueba(fil,col);
		SenkuGame.setLocation((screenSize.width-Ancho)/2,(screenSize.height-Alto)/2);
		SenkuGame.setSize(new Dimension(Ancho,Alto));
        SenkuGame.getContentPane().setLayout(new BorderLayout());
        SenkuGame.getContentPane().add(juego,BorderLayout.CENTER);
        SenkuGame.getContentPane().add(refrescar,BorderLayout.SOUTH);
		juego.repaint();
		SenkuGame.setVisible(true);
		
	}	
	
	public void Salga(){
		if (JOptionPane.showConfirmDialog(this,"Are you sure you want to exit",
				"Exit?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					System.exit(0);
		}
	}
		public void Salga2(){
		if (JOptionPane.showConfirmDialog(this,"Are you sure you want to exit to Menu?",
				"Exit?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					MenuF.dispose();
		}
	}
	public void Pausa(){
		prepareElementosMenu();
	}
	public void crearJuego(int fil, int col){
		prepareElementosTablero(fil,col);
	}
	public void refresque(int fil, int col){
		prepareElementosTablero(fil,col);
	}
	
	/*salvar
	*/
	public void openChooser(Component par){
		JFileChooser fc=new JFileChooser();
		FileNameExtensionFilter filtro =new FileNameExtensionFilter("Archivos java","java");
		fc.setFileFilter(filtro);
		int r=fc.showOpenDialog(par);
		if(r==JFileChooser.APPROVE_OPTION){
			fc.getSelectedFile();
			System.out.println("You chose to open this file: " +
            fc.getSelectedFile().getName());
		}
		
	}
	/*guardar
	*/	
	public void saveChooser(Component par){
		JFileChooser fc=new JFileChooser();
		int r=fc.showSaveDialog(par);
		if(r==JFileChooser.APPROVE_OPTION){
			System.out.println("El archivo fue guardado"+ "Action :"+JFileChooser.APPROVE_OPTION);
		}
	}


public static void main(String args[]){
	SenkuGUI gui=new SenkuGUI();
	gui.setVisible(true);
	}
}
