
import acm.graphics.GImage;

public class Pulpo {

	public Pulpo(GImage image, int angle, int sexe) {
		// super(image, angle, sexe);
	}

	public boolean menjoAlgu(Peix p) {
		if (p instanceof PeixNormal) {
			return true;
		}
		return false;
	}

}
