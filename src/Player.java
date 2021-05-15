public class Player extends Entities {
	public int speed;
	public int arrow = -1;
	public int[] type = new int[20];
	public int flamelength;
	public int numberofbombs;
	public Boolean active = true;
	public Player(int x, int y, String imgpath, int width, int height) {
		super(x, y, imgpath, width, height);
	}
	public int canMove(int arrow, Entities entities) {
		int move_length = this.speed;
		if (arrow == 0) {
			if ((this.x >= entities.x + entities.width) && (entities.y - this.height < this.y) && (this.y < entities.y + entities.height)) move_length = Math.min(move_length, this.x - entities.x - entities.width);
		}
		else if (arrow == 1) {
			if ((this.y >= entities.y + entities.height) && (entities.x - this.width < this.x) && (this.x < entities.x + entities.width)) move_length = Math.min(move_length, this.y - entities.y - entities.height);
		}
		else if (arrow == 2) {
			if ((this.x + this.width <= entities.x) && (entities.y - this.height < this.y) && (this.y < entities.y + entities.height)) move_length = Math.min(move_length, entities.x - this.x - this.width);
		}
		else if (arrow == 3) {
			if ((this.y + this.height <= entities.y) && (entities.x - this.width < this.x) && (this.x < entities.x + entities.width)) move_length = Math.min(move_length, entities.y - this.y - this.height);
		}
		return move_length;
	}
	public void move(int arrow , int type , int movelength) {
		Convention convention = new Convention();
		if (arrow == 0) {
			this.x -= movelength;
			if (type == 0) {
				this.imgpath = convention.PLAYER_LEFT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.PLAYER_LEFT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.PLAYER_LEFT3_PATH;
			}
		}
		else if (arrow == 1) {
			this.y -= movelength;
			if (type == 0) {
				this.imgpath = convention.PLAYER_UP1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.PLAYER_UP2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.PLAYER_UP3_PATH;
			}
		}
		else if (arrow == 2) {
			this.x += movelength;
			if (type == 0) {
				this.imgpath = convention.PLAYER_RIGHT1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.PLAYER_RIGHT2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.PLAYER_RIGHT3_PATH;
			}
		}
		else if (arrow == 3) {
			this.y += movelength;
			if (type == 0) {
				this.imgpath = convention.PLAYER_DOWN1_PATH;
			}
			else if (type == 1) {
				this.imgpath = convention.PLAYER_DOWN2_PATH;
			}
			else if (type == 2) {
				this.imgpath = convention.PLAYER_DOWN3_PATH;
			}
		}
	}
}
