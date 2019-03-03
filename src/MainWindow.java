import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.SystemColor;
import java.awt.Window;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final int MOMBO=0;
	private static final int PIROLO=1;
	private static final int LUCAS=2;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int ENTRENAMIENTOS=250;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Casilla[][] tablero;
	ImageIcon icon;
	ImageIcon imgPlano;
	ImageIcon imgAgua;
	ImageIcon imgMontania;
	ImageIcon imgBarranco;
	ImageIcon imgMuro;
	ImageIcon imgCasa;
	ImageIcon imgMOMBO64;
	ImageIcon imgPirolo64;
	ImageIcon imgLucas64;
	
	JLabel lblplano;
	
	JPanel tableroGUI;
	JPanel[][] guiPanel; 
	private JLabel lblMontaa;
	private JLabel lblBarranco;
	private JLabel lblAgua;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblMuro;
	private JLabel label_4;
	private JComboBox comboBoxMontania;
	private JComboBox comboBoxBarranco;
	private JComboBox comboBoxAgua;
	private JComboBox comboBoxMuro;
	private JLabel lblRecorrer;
	private JPanel panel;
	private JButton btnMombo;
	private JLabel lblmombo64;
	private JButton btnPirolo;
	private JButton btnLucas;
	private JLabel lblPirolo64;
	private JLabel lblLucas64;
	
	
	///DATOS NECESARIOS PARA EL ENTRENAMIENTO
	int agenteEntrenando;
	float Max_Esfuerzo=0;
	float Min_Esfuerzo=0;
	NodoGrafo inicio;
	NodoGrafo fin;
	public float media_esfuerzo(){
		return ((Max_Esfuerzo+Min_Esfuerzo)/2);
	}
	//////////////////////////////////
	@SuppressWarnings("unchecked")
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 580);
		setTitle("BMF- Busqueda Mundo Fantasia");
		
		icon=new ImageIcon("src/Images/icon.png");
		imgPlano=new ImageIcon("src/Images/plano2.png");
		imgAgua=new ImageIcon("src/Images/agua2.png");
		imgMontania=new ImageIcon("src/Images/montania2.png");
		imgBarranco=new ImageIcon("src/Images/barranco3.png");
		imgMuro=new ImageIcon("src/Images/muro2.png");
		imgCasa=new ImageIcon("src/Images/casa2.png");
		imgMOMBO64=new ImageIcon("src/Images/mombo.png");
		imgPirolo64=new ImageIcon("src/Images/pirolo.png");
		imgLucas64=new ImageIcon("src/Images/lucas.png");
		
		lblplano=new JLabel(imgPlano);
		
		setIconImage(icon.getImage());
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablero=new Casilla[16][16];

		tableroGUI=new JPanel();
		tableroGUI.setBounds(10, 11, 541, 539);
		guiPanel=new JPanel[16][16];
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				guiPanel[i][j]=new JPanel(new GridLayout(1, 1));
				tableroGUI.add(guiPanel[i][j]);
			}
		}
		tableroGUI.setLayout(new GridLayout(16, 16,0,0));
		contentPane.add(tableroGUI);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println((int)(Math.random()*8));
				llenarTablero();
				setVisible(true);
			}
		});
		btnGenerar.setBounds(609, 132, 84, 23);
		contentPane.add(btnGenerar);
		
		lblMontaa = new JLabel("Monta\u00F1a");
		lblMontaa.setBounds(561, 11, 61, 14);
		contentPane.add(lblMontaa);
		
		lblBarranco = new JLabel("Barranco");
		lblBarranco.setBounds(561, 41, 61, 14);
		contentPane.add(lblBarranco);
		
		lblAgua = new JLabel("Agua");
		lblAgua.setBounds(561, 72, 46, 14);
		contentPane.add(lblAgua);
		
		label_1 = new JLabel("%");
		label_1.setBounds(703, 11, 46, 14);
		contentPane.add(label_1);
		
		label_2 = new JLabel("%");
		label_2.setBounds(703, 41, 46, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("%");
		label_3.setBounds(703, 72, 46, 14);
		contentPane.add(label_3);
		
		lblMuro = new JLabel("Muro");
		lblMuro.setBounds(561, 103, 46, 14);
		contentPane.add(lblMuro);
		
		label_4 = new JLabel("%");
		label_4.setBounds(703, 103, 46, 14);
		contentPane.add(label_4);
		
		comboBoxMontania = new JComboBox();
		comboBoxMontania.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
		comboBoxMontania.setSelectedIndex(0);
		comboBoxMontania.setMaximumRowCount(10);
		comboBoxMontania.setBounds(632, 11, 61, 20);
		contentPane.add(comboBoxMontania);
		
		comboBoxBarranco = new JComboBox();
		comboBoxBarranco.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
		comboBoxBarranco.setSelectedIndex(0);
		comboBoxBarranco.setMaximumRowCount(10);
		comboBoxBarranco.setBounds(632, 38, 61, 20);
		contentPane.add(comboBoxBarranco);
		
		comboBoxAgua = new JComboBox();
		comboBoxAgua.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
		comboBoxAgua.setSelectedIndex(0);
		comboBoxAgua.setMaximumRowCount(10);
		comboBoxAgua.setBounds(632, 69, 61, 20);
		contentPane.add(comboBoxAgua);
		
		comboBoxMuro = new JComboBox();
		comboBoxMuro.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45"}));
		comboBoxMuro.setSelectedIndex(0);
		comboBoxMuro.setMaximumRowCount(10);
		comboBoxMuro.setBounds(632, 100, 61, 20);
		contentPane.add(comboBoxMuro);
		
		lblRecorrer = new JLabel("RECORRER");
		lblRecorrer.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRecorrer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecorrer.setBounds(614, 166, 84, 14);
		contentPane.add(lblRecorrer);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(UIManager.getBorder("Button.border"));
		panel.setForeground(Color.CYAN);
		panel.setBounds(561, 198, 183, 342);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnMombo = new JButton("Mombo");
		btnMombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recorrerMOMBO();
			}
		});
		btnMombo.setBounds(48, 76, 89, 23);
		panel.add(btnMombo);
		
		lblmombo64 = new JLabel(imgMOMBO64);
		lblmombo64.setBounds(64, 11, 55, 54);
		panel.add(lblmombo64);
		
		btnPirolo = new JButton("Pirolo");
		btnPirolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recorrerPIROLO();
			}
		});
		btnPirolo.setBounds(48, 199, 89, 23);
		panel.add(btnPirolo);
		
		btnLucas = new JButton("Lucas");
		btnLucas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recorrerLUCAS();
			}
		});
		btnLucas.setBounds(48, 308, 89, 23);
		panel.add(btnLucas);
		
		lblPirolo64 = new JLabel(imgPirolo64);
		lblPirolo64.setBounds(64, 124, 55, 64);
		panel.add(lblPirolo64);
		
		lblLucas64 = new JLabel(imgLucas64);
		lblLucas64.setBounds(64, 233, 55, 64);
		panel.add(lblLucas64);
		
	}
	public void llenarTablero(){
		float mon,mur,agua, barr;
		float cantAgua,cantMon,cantBarr,cantMur;
		mon=comboBoxMontania.getSelectedIndex();
		mur=comboBoxMuro.getSelectedIndex();
		agua=comboBoxAgua.getSelectedIndex();
		barr=comboBoxBarranco.getSelectedIndex();
		if(mur>60){
			JOptionPane.showMessageDialog(null, "El porcentaje te muros no debe ser mayor al 45%");
			return;
		}
		else if(mon+agua+mur+barr>100){
			JOptionPane.showMessageDialog(null, "El total es mayor al 100%, reduzca algun valor");
			return;
		}
		for (int i = 0; i < 16; i++) {
	        for (int n = 0; n < 16; n++) {
	        	tablero[i][n]=new Casilla(i,n);
	        }
	    }
		cantAgua=(int)(256*(agua/100));
		cantMon=(int)(256*(mon/100));
		cantBarr=(int)(256*(barr/100));
		cantMur=(int)(256*(mur/100));
		int posX,posY;
		while(cantAgua>0){
			posX=(int)(Math.random()*16);
			posY=(int)(Math.random()*16);
			if(tablero[posX][posY].tipoCasilla==Casilla.PLANO){
				tablero[posX][posY].tipoCasilla=Casilla.AGUA;
			}
			else{
				cantAgua++;
			}
			cantAgua--;
		}
		while(cantMon>0){
			posX=(int)(Math.random()*16);
			posY=(int)(Math.random()*16);
			if(tablero[posX][posY].tipoCasilla==Casilla.PLANO){
				tablero[posX][posY].tipoCasilla=Casilla.MONTANIA;
			}
			else{
				cantMon++;
			}
			cantMon--;
		}
		while(cantBarr>0){
			posX=(int)(Math.random()*16);
			posY=(int)(Math.random()*16);
			if(tablero[posX][posY].tipoCasilla==Casilla.PLANO){
				tablero[posX][posY].tipoCasilla=Casilla.BARRANCO;
			}
			else{
				cantBarr++;
			}
			cantBarr--;
		}
		while(cantMur>0){
			posX=(int)(Math.random()*16);
			posY=(int)(Math.random()*16);
			if(tablero[posX][posY].tipoCasilla==Casilla.PLANO){
				tablero[posX][posY].tipoCasilla=Casilla.MURO;
				tablero[posX][posY].nodo=null;
			}
			else{
				cantMur++;
			}
			cantMur--;
		}
		/*for (int i = 0; i < 16; i++) {
	        for (int n = 0; n < 16; n++) {
	            if(tablero[i][n].tipoCasilla==-1){
	            	tablero[i][n].tipoCasilla=Casilla.PLANO;
	            }
	        }
	    }*/
		//la casita
		posX=(int)(Math.random()*16);
		posY=(int)(Math.random()*16);
		tablero[posX][posY].tipoCasilla=Casilla.CASA;
		
		for (int i = 0; i < 16; i++) {
	        for (int n = 0; n < 16; n++) {
	        	guiPanel[i][n].removeAll();
	        	switch (tablero[i][n].tipoCasilla) {
				case Casilla.AGUA:
					guiPanel[i][n].add(new JLabel(imgAgua));
					break;
				case Casilla.PLANO:
					guiPanel[i][n].add(new JLabel(imgPlano));
					break;
				case Casilla.MONTANIA:
					guiPanel[i][n].add(new JLabel(imgMontania));
					break;
				case Casilla.MURO:
					guiPanel[i][n].add(new JLabel(imgMuro));
					break;
				case Casilla.BARRANCO:
					guiPanel[i][n].add(new JLabel(imgBarranco));
					break;
				case Casilla.CASA:
					guiPanel[i][n].add(new JLabel(imgCasa));
					
					break;
				default:
					//tableroGUI.add(new JLabel(imgPlano));
					break;
				}
	        	//guiPanel[i][n].repaint();
	        }
	    }
	}
	void recorrerMOMBO(){
		armaRutas();
		Max_Esfuerzo=0;
		Min_Esfuerzo=0;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(tablero[i][j].tipoCasilla==Casilla.CASA){
					fin=tablero[i][j].nodo;
					break;
				}
			}
		}
		//determinar inicio
		inicio=null;
		int f=0,c=0;
		while(inicio==null||inicio==fin){
			f=(int)(Math.random()*16);
			c=(int)(Math.random()*16);
			inicio=tablero[f][c].nodo;
		}
		agenteEntrenando=MOMBO;
		int cantEntrenamientos=ENTRENAMIENTOS;
		JOptionPane.showMessageDialog(null,"Se entrenara "+cantEntrenamientos+" veces\n con inicio en ["+f+","+c+"]");
		while(cantEntrenamientos>0){
			entrenaGrafo();
			cantEntrenamientos--;
		}
		JOptionPane.showMessageDialog(null, "Entrenamiento terminado\nse buscara el camino con menor coste y se mostrara la ruta en consola");
		AscensoColina.BusquedaMasBarato(inicio, fin, MOMBO);
	}
	void recorrerPIROLO(){
		armaRutas();
		Max_Esfuerzo=0;
		Min_Esfuerzo=0;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(tablero[i][j].tipoCasilla==Casilla.CASA){
					fin=tablero[i][j].nodo;
					break;
				}
			}
		}
		//determinar inicio
		inicio=null;
		int f=0,c=0;
		while(inicio==null||inicio==fin){
			f=(int)(Math.random()*16);
			c=(int)(Math.random()*16);
			inicio=tablero[f][c].nodo;
		}
		agenteEntrenando=PIROLO;
		int cantEntrenamientos=ENTRENAMIENTOS;
		JOptionPane.showMessageDialog(null,"Se entrenara "+cantEntrenamientos+" veces\n con inicio en ["+f+","+c+"]");
		while(cantEntrenamientos>0){
			entrenaGrafo();
			cantEntrenamientos--;
		}
		JOptionPane.showMessageDialog(null, "Entrenamiento terminado\nse buscara el camino con menor coste y se mostrara la ruta en consola");
		AscensoColina.BusquedaMasBarato(inicio, fin, PIROLO);
	}
	void recorrerLUCAS(){
		armaRutas();
		Max_Esfuerzo=0;
		Min_Esfuerzo=0;
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(tablero[i][j].tipoCasilla==Casilla.CASA){
					fin=tablero[i][j].nodo;
					break;
				}
			}
		}
		//determinar inicio
		inicio=null;
		int f=0,c=0;
		while(inicio==null||inicio==fin){
			f=(int)(Math.random()*16);
			c=(int)(Math.random()*16);
			inicio=tablero[f][c].nodo;
		}
		agenteEntrenando=LUCAS;
		int cantEntrenamientos=ENTRENAMIENTOS;
		JOptionPane.showMessageDialog(null,"Se entrenara "+cantEntrenamientos+" veces\n con inicio en ["+f+","+c+"]");
		while(cantEntrenamientos>0){
			entrenaGrafo();
			cantEntrenamientos--;
		}
		JOptionPane.showMessageDialog(null, "Entrenamiento terminado\nse buscara el camino con menor coste y se mostrara la ruta en consola");
		AscensoColina.BusquedaMasBarato(inicio, fin, LUCAS);
	}
	void armaRutas(){
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				float eM, eP, eL;
				if(tablero[i][j].tipoCasilla!=Casilla.CASA&&tablero[i][j].tipoCasilla!=Casilla.MURO){
					//System.out.print("["+i+","+j+"] ");
					if(tablero[i][j].tipoCasilla==Casilla.AGUA){
						eM=(float) 0.3;
						eP=(float) 2.5;
						eL=(float) 1.0;
						//System.out.print("AGUA");
					}
					else if(tablero[i][j].tipoCasilla==Casilla.BARRANCO){
						eM=(float) 1.5;
						eP=(float) 1.0;
						eL=(float) 2.5;
						//System.out.print("BARRANCO");
					}
					else if(tablero[i][j].tipoCasilla==Casilla.MONTANIA){
						eM=(float) 2.5;
						eP=(float) 0.3;
						eL=(float) 1.5;
						//System.out.print("MONTANIA");
					}
					else{
						eM=(float) 1.0;
						eP=(float) 1.5;
						eL=(float) 0.3;
						//System.out.print("PLANO");
					}
					
					//System.out.print(" -> ");
					if(i-1>=0&&j-1>=0&&tablero[i-1][j-1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[0]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[0].destino=tablero[i-1][j-1].nodo;
						//System.out.print("["+(i-1)+","+(j-1)+"] , ");
					}
					if(i-1>=0&&j>=0&&tablero[i-1][j].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[1]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[1].destino=tablero[i-1][j].nodo;
						//System.out.print("["+(i-1)+","+(j)+"] , ");
					}
					if(i-1>=0&&j+1<16&&tablero[i-1][j+1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[2]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[2].destino=tablero[i-1][j+1].nodo;
						//System.out.print("["+(i-1)+","+(j+1)+"] , ");
					}
					if(j-1>=0&&tablero[i][j-1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[3]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[3].destino=tablero[i][j-1].nodo;
						//System.out.print("["+(i)+","+(j-1)+"] , ");
					}
					if(j+1<=15&&tablero[i][j+1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[4]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[4].destino=tablero[i][j+1].nodo;
						//System.out.print("["+(i)+","+(j+1)+"] , ");
					}
					if(i+1<=15&&j-1>=0&&tablero[i+1][j-1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[5]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[5].destino=tablero[i+1][j-1].nodo;
						//System.out.print("["+(i+1)+","+(j-1)+"] , ");
					}
					if(i+1<=15&&tablero[i+1][j].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[6]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[6].destino=tablero[i+1][j].nodo;
						//System.out.print("["+(i+1)+","+(j)+"] , ");
					}
					if(i+1<=15&&j+1<16&&tablero[i+1][j+1].tipoCasilla!=Casilla.MURO){
						tablero[i][j].nodo.caminos[7]=new Ruta(eM, eP, eL);
						tablero[i][j].nodo.caminos[7].destino=tablero[i+1][j+1].nodo;
						//System.out.print("["+(i+1)+","+(j+1)+"] , ");
					}
					//System.out.println("");
				}
			}
		}
	}
	void entrenaGrafo(){
		boolean encontrado=false, ciclo=false;
		Vector<NodoGrafo> visitados= new Vector<NodoGrafo>();
		Vector<Ruta> trayectoria= new Vector<Ruta>();
		NodoGrafo actual=inicio;
		int rutaATomar;
		while(actual!=fin){
			visitados.add(actual);
			rutaATomar=(int)(Math.random()*8);
			while(actual.caminos[rutaATomar]==null||visitados.contains(actual.caminos[rutaATomar].destino)){
				rutaATomar=(int)(Math.random()*8);
				if((actual.caminos[0]==null||visitados.contains(actual.caminos[0].destino))
						&&(actual.caminos[1]==null||visitados.contains(actual.caminos[1].destino))
						&&(actual.caminos[2]==null||visitados.contains(actual.caminos[2].destino))
						&&(actual.caminos[3]==null||visitados.contains(actual.caminos[3].destino))
						&&(actual.caminos[4]==null||visitados.contains(actual.caminos[4].destino))
						&&(actual.caminos[5]==null||visitados.contains(actual.caminos[5].destino))
						&&(actual.caminos[6]==null||visitados.contains(actual.caminos[6].destino))
						&&(actual.caminos[7]==null||visitados.contains(actual.caminos[7].destino))){
							ciclo=true;
							break;
						}
			}
			if(ciclo)break;
			trayectoria.add(actual.caminos[rutaATomar]);
			actual=actual.caminos[rutaATomar].destino;
			if(actual==fin) encontrado=true;
		}
		if(encontrado){
			/*//MOSTRAR RUTA DE INSTANCIA DE ENTRENAMIENTO
			for(int i=0;i<visitados.size();i++){
				System.out.print("["+visitados.get(i).fila+", "+visitados.get(i).columna+"] -> ");
			}
			System.out.println("HOME");
			*/
			float esfuerzoTayecto=0;
			for(int i=0;i<trayectoria.size();i++){
				if(agenteEntrenando==MOMBO)
					esfuerzoTayecto+=trayectoria.get(i).eM;
				else if(agenteEntrenando==PIROLO)
					esfuerzoTayecto+=trayectoria.get(i).eP;
				else
					esfuerzoTayecto+=trayectoria.get(i).eL;
			}
			esfuerzoTayecto=esfuerzoTayecto*trayectoria.size();
			if(Max_Esfuerzo==0){
				Max_Esfuerzo=esfuerzoTayecto;
				Min_Esfuerzo=esfuerzoTayecto;
			}
			else{
				if(esfuerzoTayecto>media_esfuerzo()||esfuerzoTayecto<media_esfuerzo()){
					float ajuste=esfuerzoTayecto-media_esfuerzo();
					for(int i=0;i<trayectoria.size();i++){
						if(agenteEntrenando==MOMBO)
							trayectoria.get(i).pM+=ajuste;
						else if(agenteEntrenando==PIROLO)
							trayectoria.get(i).pP+=ajuste;
						else
							trayectoria.get(i).pL+=ajuste;
					}
					if(esfuerzoTayecto>Max_Esfuerzo)Max_Esfuerzo=esfuerzoTayecto;
					else if(esfuerzoTayecto<Min_Esfuerzo)Min_Esfuerzo=esfuerzoTayecto;
				}
				//else el esfuerzo fue igual a la media por lo que el ajuste seria 0
				//y no hace falta recorrer la ruta para ajustar la ponderacion
			}
		}
		if(ciclo)entrenaGrafo();
	}
	void ActualizaTablero(){
		tableroGUI.removeAll();
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				tableroGUI.add(guiPanel[i][j]);
			}
		}
	}
}
