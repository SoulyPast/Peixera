import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.util.Random;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class App extends GraphicsProgram {

	private static final long serialVersionUID = -3496973863839908191L;
	private static final int[] ANGLES = { 0, 90, 180, 270 };
	private static final int[] Dolfin = { 45, 135, 225, 315 };
	private static final int normal = 6;
	private static final int Tauro = 5;
	private static final int Dulfin = 3;
	private static final int Tur = 2;
	Random aleatori = new Random();
	Peixera lloc;
	
	// METODE PRINCIPAL D'EXECUCIÓ DEL PROGRAMA
	
	public void run() {
		
		AudioClip sonidoFondo = Applet.newAudioClip(getClass().getResource("../Music/Sonido.wav"));
		sonidoFondo.play();
		
		setSize(1000, 800);
		setBackground(Color.white);
		pause(100);
		lloc = new Peixera(this, getWidth(), getHeight());

		invocarPeixosNormals();
		invocarTaurons();
		invocarTortugues();
		invocardolfin();

		pause(1000);
		lloc.posicionarPeix();
		pause(1000);

		while (lloc.finalitza()) {
			lloc.mourePeixos();
			pause(10);
		}

		while (lloc.pulpo()) {
			// lloc.moupulpo();
			pause(10);
		}
	}

	// crearPeixNormals

	public Peix crearPeixNormal() {
		GImage pez;
		int anglePeix = ANGLES[aleatori.nextInt(ANGLES.length)];
		int sexePeix = aleatori.nextInt(2);
		String textPeix = "../ressource/peix" + sexePeix + "" + anglePeix + ".png";
		pez = new GImage(textPeix);
		add(pez);
		pez.setSize(30, 30);
		Peix temp = new PeixNormal(pez, anglePeix, sexePeix);
		return temp;
	}

	// crearTauro

	public Peix crearTauro() {
		GImage taur;
		int angleTauro = ANGLES[aleatori.nextInt(ANGLES.length)];
		int sexeTauro = aleatori.nextInt(2);
		String textTauro = "../ressource/tauro" + sexeTauro + "" + angleTauro + ".png";
		taur = new GImage(textTauro);
		taur.setSize(30, 30);
		add(taur);
		Peix temp = new Tauro(taur, angleTauro, sexeTauro);
		return temp;
	}

	// crearTortugues

	public Peix crearTortugues() {
		GImage Tortugues;
		int angleTortugues = ANGLES[aleatori.nextInt(ANGLES.length)];
		int sexeTortugues = aleatori.nextInt(2);
		String textTauro = "../ressource/Tortugue" + sexeTortugues + "" + angleTortugues + ".jpg";
		Tortugues = new GImage(textTauro);
		Tortugues.setSize(30, 30);
		add(Tortugues);
		Peix temp = new Tortugues(Tortugues, angleTortugues, sexeTortugues);
		return temp;
	}

	// crear dolfin

	public Peix creardolfin() {
		GImage dolfin;
		int angledolfin = Dolfin[aleatori.nextInt(Dolfin.length)];
		int sexedolfin = aleatori.nextInt(2);
		String textTauro = "../ressource/dolfin" + sexedolfin + "" + angledolfin + ".jpg";
		dolfin = new GImage(textTauro);
		dolfin.setSize(30, 30);
		add(dolfin);
		Peix temp = new Tauro(dolfin, angledolfin, sexedolfin);
		return temp;
	}

	public void invocarPeixosNormals() {
		for (int i = 0; i < normal; i++) {
			Peix temp = crearPeixNormal();
			lloc.crearPeixos(temp);
		}
	}

	public void invocarTaurons() {
		for (int i = 0; i < Tauro; i++) {
			Peix temp = crearTauro();
			lloc.crearPeixos(temp);
		}
	}

	public void invocarTortugues() {
		for (int i = 0; i < Tur; i++) {
			Peix temp = crearTortugues();
			lloc.crearPeixos(temp);
		}
	}

	public void invocardolfin() {
		for (int i = 0; i < Dulfin; i++) {
			Peix temp = creardolfin();
			lloc.crearPeixos(temp);
		}
	}

}
