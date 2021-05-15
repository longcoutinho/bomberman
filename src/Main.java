import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.media.*;
import java.io.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Main extends Application {
	Convention convention;
	Stage stage;
	Group root = new Group();
	Scene scene = new Scene(root);
	public static void main(String[] args) {
		launch(args);
	}
	Game game = new Game();
	Player player = new Player(0, 0, convention.PLAYER_DOWN1_PATH, convention.PLAYER_WIDTH, convention.PLAYER_HEIGHT);
	GameResult gameResult = new GameResult(300, 100, convention.GAME_WIN_PATH, 600, 300);
	ImageView[] listimageView = new ImageView[10000];
	public int cur = 0;
	public int mapwidth = 0;
	public int mapheight = 0;
	List<Wall> listofwalls = new ArrayList<Wall>();
	List<Grass> listofgrass = new ArrayList<Grass>();
	List<Brick> listofbricks = new ArrayList<Brick>();
	List<Balloom> listofballooms = new ArrayList<Balloom>();
	List<Oneal> listofoneals = new ArrayList<Oneal>();
	List<Player> listofplayers = new ArrayList<Player>();
	List<Bomb> listofbombs = new ArrayList<Bomb>();
	List<Flame> listofflames = new ArrayList<Flame>();
	List<Portal> listofportals = new ArrayList<Portal>();
	List<SpeedItem> listofspeeditems = new ArrayList<SpeedItem>();
	List<BombItem> listofbombitems = new ArrayList<BombItem>();
	List<FlameItem> listofflameitems = new ArrayList<FlameItem>();
	int[][] list_indexofbombs = new int[100][100];
	int[][] list_indexofbricks = new int[100][100];
	int[][] list_indexofwalls = new int[100][100];
	int[][] list_indexofflames = new int[100][100];
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public void renderMap() {
		try {
			File myObj = new File("map.txt");
			Scanner myReader = new Scanner(myObj);
			String firstline = myReader.nextLine();
			String[] mapsize = firstline.split(" ", 3);
			mapwidth = Integer.parseInt(mapsize[2]);
			mapheight = Integer.parseInt(mapsize[1]);
			stage.setWidth(convention.PIXEL_WIDTH * mapwidth);
			stage.setHeight(convention.PIXEL_HEIGHT * mapheight);
			int currow = 1;
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				for(int i = 0 ; i < mapwidth ; i++) {
					char kitu = data.charAt(i);
					Grass grass = new Grass(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.GRASS_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
					listofgrass.add(grass);
					Bomb bomb = new Bomb(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BOMB_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
					listofbombs.add(bomb);
					if (kitu == '#') {
						Wall wall = new Wall(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.WALL_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofwalls.add(wall);
					}
					else if (kitu == '*') {
						Brick brick = new Brick(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BRICK_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbricks.add(brick);
					}
					else if (kitu == 'p') {
						Player player1 = new Player(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.PLAYER_DOWN1_PATH, player.width, player.height);
						player = player1;
						listofplayers.add(player);
					}
					else if (kitu == '1') {
						Balloom balloom = new Balloom(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BALLOOM_LEFT1_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofballooms.add(balloom);
					}
					else if (kitu == '2') {
						Oneal oneal = new Oneal(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.ONEAL_RIGHT1_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofoneals.add(oneal);
					}
					else if (kitu == 'x') {
						Portal portal = new Portal(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.PORTAL_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofportals.add(portal);
						Brick brick = new Brick(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BRICK_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbricks.add(brick);
					}
					else if (kitu == 'b') {
						BombItem bombItem = new BombItem(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BOMB_ITEM, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbombitems.add(bombItem);
						Brick brick = new Brick(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BRICK_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbricks.add(brick);
					}
					else if (kitu == 'f') {
						FlameItem flameItem = new FlameItem(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.FLAME_ITEM, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofflameitems.add(flameItem);
						Brick brick = new Brick(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BRICK_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbricks.add(brick);
					}
					else if (kitu == 's') {
						SpeedItem speedItem = new SpeedItem(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.SPEED_ITEM, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofspeeditems.add(speedItem);
						Brick brick = new Brick(i * convention.PIXEL_WIDTH, (currow - 1) * convention.PIXEL_HEIGHT, convention.BRICK_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbricks.add(brick);
					}
				}
				currow++;
			}
			myReader.close();
		}	catch (FileNotFoundException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}



	public void createEntities(Entities entities) throws FileNotFoundException {
		Image newimg = new Image(new FileInputStream(entities.imgpath));
		listimageView[++cur] = new ImageView(newimg);
		listimageView[cur].setX(entities.x);
		listimageView[cur].setY(entities.y);
		listimageView[cur].setFitWidth(entities.width);
		listimageView[cur].setFitHeight(entities.height);
		root.getChildren().add(listimageView[cur]);
		entities.list_index = cur;
		if (entities instanceof Bomb) {
			list_indexofbombs[entities.position_now().x][entities.position_now().y] = entities.list_index;
		}
		if (entities instanceof Brick) {
			list_indexofbricks[entities.position_now().x][entities.position_now().y] = entities.list_index;
		}
		if (entities instanceof Flame) {
			list_indexofflames[entities.position_now().x][entities.position_now().y] = entities.list_index;
		}
		if (entities instanceof Wall) {
			list_indexofwalls[entities.position_now().x][entities.position_now().y] = entities.list_index;
		}
	}

	public void createMap() throws FileNotFoundException {
		for(int i = 0 ; i < listofgrass.size() ; i++)
			createEntities(listofgrass.get(i));
		for(int i = 0 ; i < listofwalls.size() ; i++)
			createEntities(listofwalls.get(i));
		for(int i = 0 ; i < listofbombs.size() ; i++)
			createEntities(listofbombs.get(i));
		for(int i = 0 ; i < listofportals.size() ; i++)
			createEntities(listofportals.get(i));
		for(int i = 0 ; i < listofspeeditems.size() ; i++)
			createEntities(listofspeeditems.get(i));
		for(int i = 0 ; i < listofbombitems.size() ; i++)
			createEntities(listofbombitems.get(i));
		for(int i = 0 ; i < listofflameitems.size() ; i++)
			createEntities(listofflameitems.get(i));
		for(int i = 0 ; i < listofbricks.size() ; i++)
			createEntities(listofbricks.get(i));
		for(int i = 0 ; i < listofballooms.size() ; i++)
			createEntities(listofballooms.get(i));
		for(int i = 0 ; i < listofoneals.size() ; i++)
			createEntities(listofoneals.get(i));
		for(int i = 0 ; i < listofplayers.size() ; i++)
			createEntities(listofplayers.get(i));
		int len = listofbombs.size();
		for(int i = len - 1 ; i >= 0 ; i--) {
			listimageView[listofbombs.get(i).list_index].setVisible(false);
			listofbombs.remove(i);
		}
		createEntities(gameResult);
		listimageView[gameResult.list_index].setVisible(false);
	}

	public void defaultsetting() {
		player.speed = convention.PLAYER_SPEED1;
		player.numberofbombs = convention.PLAYER_NUMBER_OF_BOMBS1;
		player.flamelength = convention.PLAYER_FLAME_LENGTH1;
		for(int i = 0 ; i < listofballooms.size() ; i++) {
			listofballooms.get(i).speed = convention.BALLOOM_SPEED;
			listofballooms.get(i).arrow = randInt(0, 1) * 2;
			listofoneals.get(i).speed = convention.ONEAL_SPEED;
			listofoneals.get(i).arrow = randInt(0, 1) * 2;
		}
	}

	public void balloommove() throws FileNotFoundException {
		for(int i = 0 ; i < listofballooms.size() ; i++) {
			if (listofballooms.get(i).active == true) {
				int kt = 0;
				for (int j = 0; j < listofbricks.size(); j++)
					if (listofballooms.get(i).canMove(listofballooms.get(i).arrow, listofbricks.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofwalls.size(); j++)
					if (listofballooms.get(i).canMove(listofballooms.get(i).arrow, listofwalls.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofbombs.size(); j++)
					if (listofballooms.get(i).canMove(listofballooms.get(i).arrow, listofbombs.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofportals.size(); j++)
					if (listofballooms.get(i).canMove(listofballooms.get(i).arrow, listofportals.get(j)) == false) {
						kt = 1;
						break;
					}
				if (kt == 1) listofballooms.get(i).arrow = 2 - listofballooms.get(i).arrow;
				else if (kt == 0) {
					listofballooms.get(i).move(listofballooms.get(i).arrow, listofballooms.get(i).type[listofballooms.get(i).arrow]);
					listofballooms.get(i).type[listofballooms.get(i).arrow] = (listofballooms.get(i).type[listofballooms.get(i).arrow] + 1) % 3;
					Image newimg = new Image(new FileInputStream(listofballooms.get(i).imgpath));
					listimageView[listofballooms.get(i).list_index].setImage(newimg);
					listimageView[listofballooms.get(i).list_index].setX(listofballooms.get(i).x);
					listimageView[listofballooms.get(i).list_index].setY(listofballooms.get(i).y);
				}
			}
		}
	}

	public void onealmove() throws FileNotFoundException {
		for(int i = 0 ; i < listofoneals.size() ; i++) {
			if (listofoneals.get(i).active == true) {
				int kt = 0;
				for (int j = 0; j < listofbricks.size(); j++)
					if (listofoneals.get(i).canMove(listofoneals.get(i).arrow, listofbricks.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofwalls.size(); j++)
					if (listofoneals.get(i).canMove(listofoneals.get(i).arrow, listofwalls.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofbombs.size(); j++)
					if (listofoneals.get(i).canMove(listofoneals.get(i).arrow, listofbombs.get(j)) == false) {
						kt = 1;
						break;
					}
				for (int j = 0; j < listofportals.size(); j++)
					if (listofoneals.get(i).canMove(listofoneals.get(i).arrow, listofportals.get(j)) == false) {
						kt = 1;
						break;
					}
				if (kt == 1) listofoneals.get(i).arrow = 2 - listofoneals.get(i).arrow;
				else if (kt == 0) {
					listofoneals.get(i).move(listofoneals.get(i).arrow, listofoneals.get(i).type[listofoneals.get(i).arrow]);
					listofoneals.get(i).type[listofoneals.get(i).arrow] = (listofoneals.get(i).type[listofoneals.get(i).arrow] + 1) % 3;
					Image newimg = new Image(new FileInputStream(listofoneals.get(i).imgpath));
					listimageView[listofoneals.get(i).list_index].setImage(newimg);
					listimageView[listofoneals.get(i).list_index].setX(listofoneals.get(i).x);
					listimageView[listofoneals.get(i).list_index].setY(listofoneals.get(i).y);
					int randvisible = randInt(0, 1);
					if (randvisible == 1) listimageView[listofoneals.get(i).list_index].setVisible(false);
					else {
						listimageView[listofoneals.get(i).list_index].setVisible(true);
					}
				}
			}
		}
	}

	public void playermove() throws FileNotFoundException {
		if (player.active == true && player.arrow >= 0) {
			int movelength = player.speed;
			for (int i = 0; i < listofbricks.size(); i++)
				movelength = Math.min(movelength, player.canMove(player.arrow, listofbricks.get(i)));
			for (int i = 0; i < listofwalls.size(); i++)
				movelength = Math.min(movelength, player.canMove(player.arrow, listofwalls.get(i)));
			for (int i = 0; i < listofbombs.size(); i++)
				movelength = Math.min(movelength, player.canMove(player.arrow, listofbombs.get(i)));
			for (int i = 0; i < listofportals.size() ; i++)
			if (listofportals.get(i).enable == false) movelength = Math.min(movelength, player.canMove(player.arrow, listofportals.get(i)));
			player.move(player.arrow, player.type[player.arrow], movelength);
			player.type[player.arrow] = (player.type[player.arrow] + 1) % 3;
			Image newimage = new Image(new FileInputStream(player.imgpath));
			listimageView[player.list_index].setImage(newimage);
			listimageView[player.list_index].setX(player.x);
			listimageView[player.list_index].setY(player.y);
		}
	}

	void play_sound() {
		String ssound = "explosion.mp3";
		Media sound = new Media(ssound);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	public void make_explosion(Bomb bomb, int px, int py) {
		Boolean checkbombused = true;
		for(int i = 0 ; i < listofbombs.size() ; i++)
			if (listofbombs.get(i).equals(bomb)) checkbombused = false;
		if (checkbombused == false) {
			listimageView[list_indexofbombs[px][py]].setVisible(false);
			player.numberofbombs++;
			Flame flame_center = new Flame(bomb.x, bomb.y, convention.EXPLOSION_CENTER, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						createEntities(flame_center);
						listofflames.add(flame_center);
						Timer newt = new Timer();
						newt.schedule(new TimerTask() {
							@Override
							public void run() {
								listofflames.remove(flame_center);
								listimageView[flame_center.list_index].setVisible(false);
							}
						}, convention.TIME_EXPLOSION);
						play_sound();
						int kt1 = 0, kt2 = 0, kt3 = 0, kt4 = 0;
						for (int i = 1; i <= player.flamelength; i++) {
							if (bomb.position_now().x - i < 0) {
								kt1 = 1;
							}
							if (bomb.position_now().x + i > mapwidth * convention.PIXEL_WIDTH) {
								kt2 = 1;
							}
							if (bomb.position_now().y - i < 0) {
								kt3 = 1;
							}
							if (bomb.position_now().y + i > mapheight * convention.PIXEL_HEIGHT) {
								kt4 = 1;
							}
							if (kt1 == 0) {
								if (list_indexofbricks[bomb.position_now().x - i][bomb.position_now().y] > 0) {
									kt1 = 1;
									for (int j = 0; j < listofbricks.size(); j++)
										if (listofbricks.get(j).x == bomb.x - i * convention.PIXEL_WIDTH && listofbricks.get(j).y == bomb.y) {
											listimageView[listofbricks.get(j).list_index].setVisible(false);
											list_indexofbricks[listofbricks.get(j).position_now().x][listofbricks.get(j).position_now().y] = 0;
											listofbricks.remove(j);
											break;
										}
								}
								if (list_indexofwalls[bomb.position_now().x - i][bomb.position_now().y] > 0)
									kt1 = 1;
								else {
									Flame flame1 = new Flame(bomb.x - i * convention.PIXEL_WIDTH, bomb.y, convention.EXPLOSION_HORIZONTAL, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
									if (i == player.flamelength)
										flame1.imgpath = convention.EXPLOSION_HORIZONTAL_LEFT_LAST;
									createEntities(flame1);
									listofflames.add(flame1);
									Timer t = new Timer();
									t.schedule(new TimerTask() {
										@Override
										public void run() {
											listofflames.remove(flame1);
											listimageView[flame1.list_index].setVisible(false);
										}
									}, convention.TIME_EXPLOSION);
								}
							}
							if (kt2 == 0) {
								if (list_indexofbricks[bomb.position_now().x + i][bomb.position_now().y] > 0) {
									kt2 = 1;
									for (int j = 0; j < listofbricks.size(); j++)
										if (listofbricks.get(j).x == bomb.x + i * convention.PIXEL_WIDTH && listofbricks.get(j).y == bomb.y) {
											listimageView[listofbricks.get(j).list_index].setVisible(false);
											list_indexofbricks[listofbricks.get(j).position_now().x][listofbricks.get(j).position_now().y] = 0;
											listofbricks.remove(j);
											break;
										}
								}
								if (list_indexofwalls[bomb.position_now().x + i][bomb.position_now().y] > 0)
									kt2 = 1;
								else {
									Flame flame2 = new Flame(bomb.x + i * convention.PIXEL_WIDTH, bomb.y, convention.EXPLOSION_HORIZONTAL, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
									if (i == player.flamelength)
										flame2.imgpath = convention.EXPLOSION_HORIZONTAL_RIGHT_LAST;
									createEntities(flame2);
									listofflames.add(flame2);
									Timer t = new Timer();
									t.schedule(new TimerTask() {
										@Override
										public void run() {
											listofflames.remove(flame2);
											listimageView[flame2.list_index].setVisible(false);
										}
									}, convention.TIME_EXPLOSION);
								}
							}
							if (kt3 == 0) {
								if (list_indexofbricks[bomb.position_now().x][bomb.position_now().y - i] > 0) {
									kt3 = 1;
									for (int j = 0; j < listofbricks.size(); j++)
										if (listofbricks.get(j).x == bomb.x && listofbricks.get(j).y == bomb.y - i * convention.PIXEL_HEIGHT) {
											listimageView[listofbricks.get(j).list_index].setVisible(false);
											list_indexofbricks[listofbricks.get(j).position_now().x][listofbricks.get(j).position_now().y] = 0;
											listofbricks.remove(j);
											break;
										}
								}
								if (list_indexofwalls[bomb.position_now().x][bomb.position_now().y - i] > 0)
									kt3 = 1;
								else {
									Flame flame3 = new Flame(bomb.x, bomb.y - i * convention.PIXEL_HEIGHT, convention.EXPLOSION_VERTICAL, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
									if (i == player.flamelength)
										flame3.imgpath = convention.EXPLOSION_VERTICAL_UP_LAST;
									createEntities(flame3);
									listofflames.add(flame3);
									Timer t = new Timer();
									t.schedule(new TimerTask() {
										@Override
										public void run() {
											listofflames.remove(flame3);
											listimageView[flame3.list_index].setVisible(false);
										}
									}, convention.TIME_EXPLOSION);
								}
							}
							if (kt4 == 0) {
								if (list_indexofbricks[bomb.position_now().x][bomb.position_now().y + i] > 0) {
									kt4 = 1;
									for (int j = 0; j < listofbricks.size(); j++)
										if (listofbricks.get(j).x == bomb.x && listofbricks.get(j).y == bomb.y + i * convention.PIXEL_HEIGHT) {
											listimageView[listofbricks.get(j).list_index].setVisible(false);
											list_indexofbricks[listofbricks.get(j).position_now().x][listofbricks.get(j).position_now().y] = 0;
											listofbricks.remove(j);
											break;
										}
								}
								if (list_indexofwalls[bomb.position_now().x][bomb.position_now().y + i] > 0)
									kt4 = 1;
								else {
									Flame flame4 = new Flame(bomb.x, bomb.y + i * convention.PIXEL_HEIGHT, convention.EXPLOSION_VERTICAL, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
									if (i == player.flamelength)
										flame4.imgpath = convention.EXPLOSION_VERTICAL_DOWN_LAST;
									createEntities(flame4);
									listofflames.add(flame4);
									Timer t = new Timer();
									t.schedule(new TimerTask() {
										@Override
										public void run() {
											listofflames.remove(flame4);
											listimageView[flame4.list_index].setVisible(false);
										}
									}, convention.TIME_EXPLOSION);
								}
							}
						}
					} catch (FileNotFoundException ex) {
						System.out.println("Cannot find the image path");
					}
				}
			});
			listofbombs.remove(bomb);
		}
	}

	public void checkcollision() throws FileNotFoundException{
		//System.out.println(player.x + "   " + player.y);
		for(int i = 0 ; i < listofspeeditems.size() ; i++)
		if (player.collision(listofspeeditems.get(i))) {
			player.speed = convention.PLAYER_SPEED2;
			for(int j = 0 ; j < listofspeeditems.size() ; j++)
			listimageView[listofspeeditems.get(j).list_index].setVisible(false);
		}
		for(int i = 0 ; i < listofbombitems.size() ; i++)
			if (player.collision(listofbombitems.get(i))) {
				player.numberofbombs = convention.PLAYER_NUMBER_OF_BOMBS2;
				for(int j = 0 ; j < listofbombitems.size() ; j++)
					listimageView[listofbombitems.get(j).list_index].setVisible(false);
			}
		for(int i = 0 ; i < listofflameitems.size() ; i++)
			if (player.collision(listofflameitems.get(i))) {
				player.flamelength = convention.PLAYER_FLAME_LENGTH2;
				for(int j = 0 ; j < listofflameitems.size() ; j++)
					listimageView[listofflameitems.get(j).list_index].setVisible(false);
			}
		if (listofballooms.size() == 0 && listofoneals.size() == 0) {
			for(int j = 0; j < listofportals.size() ; j++)
				listofportals.get(j).enable = true;
		}

		for(int i = 0 ; i < listofportals.size() ; i++)
			if (player.collision(listofportals.get(i))) {
			game.win = true;
			break;
		}

		for(int i = 0 ; i < listofballooms.size() ; i++)
			if (player.collision(listofballooms.get(i)) == true) {
				game.lose = true;
				break;
			}

		for(int i = 0 ; i < listofoneals.size() ; i++)
			if (player.collision(listofoneals.get(i)) == true) {
				game.lose = true;
				break;
			}


		for(int i = 0 ; i < listofflames.size() ; i++)
			for(int j = 0 ; j < listofballooms.size() ; j++)
				if (listofflames.get(i).collision(listofballooms.get(j))) {
					listofballooms.get(j).active = false;
					Image balloomsdie = new Image(new FileInputStream(convention.BALLOOM_DEAD));
					listimageView[listofballooms.get(j).list_index].setImage(balloomsdie);
					int current_index = listofballooms.get(j).list_index;
					listofballooms.remove(j);
					Timer t1 = new Timer();
					t1.schedule(new TimerTask() {
						@Override
						public void run() {
							listimageView[current_index].setVisible(false);
						}
					}, 1000);
				}

		for(int i = 0 ; i < listofflames.size() ; i++)
			for(int j = 0 ; j < listofoneals.size() ; j++)
				if (listofflames.get(i).collision(listofoneals.get(j))) {
					listofoneals.get(j).active = false;
					listimageView[listofoneals.get(j).list_index].setVisible(true);
					Image onealsdie = new Image(new FileInputStream(convention.ONEAL_DEAD));
					listimageView[listofoneals.get(j).list_index].setImage(onealsdie);
					int current_index = listofoneals.get(j).list_index;
					listofoneals.remove(j);
					Timer t1 = new Timer();
					t1.schedule(new TimerTask() {
						@Override
						public void run() {
							listimageView[current_index].setVisible(false);
						}
					}, 1000);
				}

		for(int i = 0  ; i < listofflames.size() ; i++)
			if (listofflames.get(i).collision(player) == true) {
				game.lose = true;
				break;
			}

		for(int i = 0 ; i < listofflames.size() ; i++)
			for(int j = 0 ; j < listofbombs.size() ; j++)
				if (listofflames.get(i).collision(listofbombs.get(j))) {
					make_explosion(listofbombs.get(j), listofbombs.get(j).position_now().x, listofbombs.get(j).position_now().y);
				}
	}

	public void checkgame() throws FileNotFoundException{
		if (game.win == true) {
			player.active = false;
			for(int j = 0 ; j < listofballooms.size(); j++) {
				listofballooms.get(j).active = false;
			}
			for(int j = 0 ; j < listofoneals.size(); j++) {
				listofoneals.get(j).active = false;
			}
			listimageView[gameResult.list_index].setVisible(true);
		}
		if (game.lose == true) {
			player.active = false;
			for(int j = 0 ; j < listofballooms.size(); j++) {
				listofballooms.get(j).active = false;
			}
			for(int j = 0 ; j < listofoneals.size(); j++) {
				listofoneals.get(j).active = false;
			}
			Image loseimg = new Image(new FileInputStream(convention.GAME_LOSE_PATH));
			listimageView[gameResult.list_index].setImage(loseimg);
			listimageView[gameResult.list_index].setVisible(true);
		}
	}
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		this.stage = stage;
		renderMap();
		createMap();
		defaultsetting();

		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								balloommove();
								onealmove();
							}
							catch (FileNotFoundException ex) {
								System.out.println("Cannot file the image path");
							}
						}
					});
			}},0,100);
		Timer t2 = new Timer();
		t2.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					checkcollision();
					checkgame();
				}
				catch (FileNotFoundException ex) {
					System.out.println("Cannot find the image path");
				}
			}
		},0, 1);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					player.arrow = 0;
				}
				else if (event.getCode() == KeyCode.UP) {
					player.arrow = 1;
				}
				else if (event.getCode() == KeyCode.RIGHT) {
					player.arrow = 2;
				}
				else if (event.getCode() == KeyCode.DOWN) {
					player.arrow = 3;
				}
				else if (event.getCode() == KeyCode.SPACE) {
					if (player.numberofbombs > 0) {
						int px = player.position_now().x;
						int py = player.position_now().y;
						listimageView[list_indexofbombs[px][py]].setVisible(true);
						Bomb bomb = new Bomb(px * convention.PIXEL_WIDTH, py * convention.PIXEL_HEIGHT, convention.BOMB_PATH, convention.PIXEL_WIDTH, convention.PIXEL_HEIGHT);
						listofbombs.add(bomb);
						player.numberofbombs--;
						Timer newtimer = new Timer();
						newtimer.schedule(new TimerTask() {
							@Override
							public void run() {
								make_explosion(bomb, px, py);
							}
						}, convention.TIME_BOMB);
					}
				}
				Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								playermove();
							}
							catch (FileNotFoundException ex) {
								System.out.println("Cannot find the image path");
							}
						}
					});
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				player.arrow = -1;
			}
		});
		stage.setTitle("BOMBERMAN");
		stage.setScene(scene);
		stage.show();
	}
}

