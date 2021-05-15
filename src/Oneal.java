public class Oneal extends Balloom {
	public Oneal(int x, int y, String imgpath, int width, int height) {
		super(x, y, imgpath, width, height);
	}
	public void move(int arrow, int type) {
		Convention convention = new Convention();
		if (arrow == 0) {
			this.x -= this.speed;
			if (type == 0) {
				this.imgpath = convention.ONEAL_LEFT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.ONEAL_LEFT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.ONEAL_LEFT3_PATH;
			}
		}
		else if (arrow == 2) {
			this.x += this.speed;
			if (type == 0) {
				this.imgpath = convention.ONEAL_RIGHT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.ONEAL_RIGHT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.ONEAL_RIGHT3_PATH;
			}
		}
	}
}

