public class Balloom extends Entities {
	public int arrow;
	public int speed;
	public int[] type = new int[20];
	public Boolean active = true;
	public Balloom(int x, int y, String imgpath, int width, int height) {
		super(x, y, imgpath, width, height);
	}
	public boolean canMove(int arrow, Entities entities) {
		Convention convention = new Convention();
		if (arrow == 0) {
			if ((this.x - entities.x == convention.PIXEL_WIDTH) && (Math.abs(this.y - entities.y) < convention.PIXEL_HEIGHT)) return false;
			return true;
		}
		else if (arrow == 1) {
			if ((this.y - entities.y == convention.PIXEL_HEIGHT) && (Math.abs(this.x - entities.x) < convention.PIXEL_WIDTH)) return false;
			return true;
		}
		else if (arrow == 2) {
			if ((-this.x + entities.x == convention.PIXEL_WIDTH) && (Math.abs(this.y - entities.y) < convention.PIXEL_HEIGHT)) return false;
			return true;
		}
		else if (arrow == 3) {
			if ((-this.y + entities.y == convention.PIXEL_HEIGHT) && (Math.abs(this.x - entities.x) < convention.PIXEL_WIDTH)) return false;
			return true;
		}
		return true;
	}
	public void move(int arrow, int type) {
		Convention convention = new Convention();
		if (arrow == 0) {
			this.x -= this.speed;
			if (type == 0) {
				this.imgpath = convention.BALLOOM_LEFT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.BALLOOM_LEFT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.BALLOOM_LEFT3_PATH;
			}
		}
		else if (arrow == 2) {
			this.x += this.speed;
			if (type == 0) {
				this.imgpath = convention.BALLOOM_RIGHT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.BALLOOM_RIGHT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.BALLOOM_RIGHT3_PATH;
			}
		}
	}
}

