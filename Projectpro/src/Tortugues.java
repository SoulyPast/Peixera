
import acm.graphics.GImage;

public class Tortugues extends Peix {

	public Tortugues(GImage image, int angle, int sexe) {
		super(image, angle, sexe);
		// TODO Auto-generated constructor stub
	}

	public boolean menjoAlgu(Peix p) {
		if (p instanceof Tortugues && p.getSexe() == this.getSexe()) {
			return true;
		}
		return true;
	}

}
