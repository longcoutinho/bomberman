public abstract class Entities {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String imgpath;
	protected int list_index;
	public Entities(int x, int y,  String imgpath, int width, int height) {
		this.x = x;
		this.y = y;
		this.imgpath = imgpath;
		this.width = width;
		this.height = height;
	}
	public Position position_now() {
		Convention convention = new Convention();
		int first = this.x / convention.PIXEL_WIDTH;
		if (first * convention.PIXEL_WIDTH + convention.PIXEL_WIDTH - this.x < this.width / 2) first = first + 1;
		int second = this.y / convention.PIXEL_HEIGHT;
		if (second * convention.PIXEL_WIDTH + convention.PIXEL_HEIGHT - this.y < this.height / 2) second = second + 1;
		Position newpostion = new Position(first, second);
		return newpostion;
	}
	public Boolean collision(Entities entities) {
		if (entities.x >= this.x && entities.x - this.x < this.width && entities.y >= this.y && entities.y - this.y < this.height) return true;
		if (this.x >= entities.x && this.x - entities.x < entities.width && entities.y >= this.y && entities.y - this.y < this.height) return true;
		if (entities.x >= this.x && entities.x - this.x < this.width && this.y >= entities.y && this.y - entities.y < entities.height) return true;
		if (this.x >= entities.x && this.x - entities.x < entities.width && this.y >= entities.y && this.y - entities.y < entities.height) return true;
		return false;
	}
}

