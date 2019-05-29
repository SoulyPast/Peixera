import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import acm.graphics.GRectangle;

public class Peixera {
	Random aleatori = new Random();

	// LISTA DE PEIXOS

	List<Peix> peixos = new ArrayList<Peix>();

	List<Peix> peixnou = new ArrayList<Peix>();

	// ATRIBUTS DE LA PEIXERA
	int altura;
	int amplada;
	App pantalla;

	// CONSTRUCTOR DE PEIXERA
	public Peixera(App pantalla, int x, int y) {
		amplada = x;
		altura = y;
		this.pantalla = pantalla;
	}

	// METODE PER AFEGIR ELS PEIXOS A L'ARRAI DE PEIXOS ACTIUS
	public void crearPeixos(Peix pes) {
		peixos.add(pes);
	}

	// CONTROLS DEL POSICIONAMENT INICIAL DELS PEIXOS (TOTALMENT ALEATORI)
	public void posicionarPeix() {
		for (Peix p : peixos) {
			p.posicionarPeix(aleatori.nextInt(amplada - p.getAmplada()), aleatori.nextInt(altura - p.getAltura()));
		}
	}

	// METODE QUE CONTROLA EL MOVIMENT DELS PEIXOS
	public void mourePeixos() {
		// Recorrer la llista de peixos
		for (Peix p : peixos) {
			/// Si el peix NO esta mort...
			if (!p.isEliminar()) {
				p.movimentPeix(); // Moure els peixos
				colisionaParets(p); // Comprovar les colisions amb les parets
				Peix col = colisionaPeixos(p); // Metode que comprova la
				if (colisionaPeixos(p) != null) { // Si el resultat torna un

					boolean haMatat = false;
					if (p.menjoAlgu(col)) {
						col.setEliminar();
						haMatat = true;
					}
					if (col.menjoAlgu(p)) {
						p.setEliminar();
						haMatat = true;
					}
					if (!haMatat) {
						if (p.isCriant() == false && col.isCriant() == false) {

							if (p instanceof PeixNormal) {
								crearPeixBebe(p, col);
							}

							if (p instanceof Tortugues) {
								crearTortugaBebe(p, col);
							}

							p.setCriant(true);
							col.setCriant(true);
						}
					}

				} else {
					p.setCriant(false);
				}

				// Si el peix SI esta mort...
			}
		}
		colisionsBebes();
	}

	// mouviment del pulpo

	public void moupulpo(ArrayList<Peix> Peces) {
		for (int i = 0; i < Peces.size(); i++) {
			Peces.get(i).movimentPeix();
			if (Peces.get(i).imatge.getX() >= 765 && Peces.get(i).imatge.getY() <= 0) {
				Peces.get(i).setAngle(270);
			} else if (Peces.get(i).imatge.getY() >= 575 && Peces.get(i).imatge.getX() >= 765) {
				Peces.get(i).setAngle(180);
			} else if (Peces.get(i).imatge.getX() <= 0 && Peces.get(i).imatge.getY() >= 575) {
				Peces.get(i).setAngle(90);
			} else if (Peces.get(i).imatge.getY() <= 0 || Peces.get(i).imatge.getX() <= 0) {
				Peces.get(i).setAngle(0);
			}
		}
	}

	// crear peix normal
	public void crearPeixBebe(Peix p, Peix col) {
		GRectangle posicioFill = p.espaiOcupa().intersection(col.espaiOcupa());
		Peix nouBebe = pantalla.crearPeixNormal();
		peixnou.add(nouBebe);
		nouBebe.posicionarPeix((int) posicioFill.getX(), (int) posicioFill.getY());
	}

	// crear peix tauro
	public void crearTauroBebe(Peix p, Peix col) {
		GRectangle posicioFill = p.espaiOcupa().intersection(col.espaiOcupa());
		Peix nouBebe = pantalla.crearTauro();
		peixnou.add(nouBebe);
		nouBebe.posicionarPeix((int) posicioFill.getX(), (int) posicioFill.getY());
	}

	// crear peix tortuga
	public void crearTortugaBebe(Peix p, Peix col) {
		GRectangle posicioFill = p.espaiOcupa().intersection(col.espaiOcupa());
		Peix nouBebe = pantalla.crearTauro();
		peixnou.add(nouBebe);
		nouBebe.posicionarPeix((int) posicioFill.getX(), (int) posicioFill.getY());
	}

	public void colisionsBebes() {
		for (int i = peixnou.size() - 1; i >= 0; i--) {
			if (colisionaPeixos(peixnou.get(i)) == null) {
				Peix bebe = peixnou.get(i);
				peixos.add(bebe);
				peixnou.remove(bebe);
			}
		}
	}

	// CONTROL DE LA FINALITZACIÓ DEL PROGRAMA SI HENS QUEDEM SENSE PEIXOS

	public boolean finalitza() {
		int acaba = peixos.size();
		if (acaba == 1) {
			return false;
		}
		return true;
	}

	// METODE QUE CONTROLA LA COLISIÓ DELS PEIXOS AMB LES PARETS DE LA NOSTRA
	// FINESTRE

	public void colisionaParets(Peix p) {

		// PART DEL CODI QUE FA QUE ELS PEIXOS APAREGUIN PER L'ALTRE COSATAT EN
		// CAS DE QUE SURTIN DE LA PANTALLA

		if (p.espaiOcupa().getX() > amplada) {
			int temp1 = (int) p.espaiOcupa().getX() + 1;
			int temp2 = temp1 % amplada;
			int temp3 = temp2 - p.getAmplada();
			p.posicionarPeix(temp3, (int) p.espaiOcupa().getY());
		}
		if (p.espaiOcupa().getX() < 0 - p.getAmplada()) {
			p.posicionarPeix(amplada, (int) p.espaiOcupa().getY());
		}
		if (p.espaiOcupa().getY() > altura) {
			int temp1 = (int) p.espaiOcupa().getY() + 1;
			int temp2 = temp1 % altura;
			int temp3 = temp2 - p.getAltura();
			p.posicionarPeix((int) p.espaiOcupa().getX(), temp3);
		}
		if (p.espaiOcupa().getY() < 0 - p.getAltura()) {
			p.posicionarPeix((int) p.espaiOcupa().getX(), altura);
		}
	}

	// ColisionaPeixos

	public Peix colisionaPeixos(Peix p) {
		for (Peix pes : peixos) {
			if (p != pes && !(pes.isEliminar())) {
				if (p.colisiona(pes)) {
					return pes;
				}
			}
		}
		return null;
	}

	// pulpo

	public boolean pulpo() {
		return false;
	}
}
