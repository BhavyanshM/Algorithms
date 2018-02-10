package elements;

public class Agent {
	public int x;
	public int y;
	public int velx;
	public int vely;
	
	public Agent(int x, int y, int vx, int vy){
		this.x = x;
		this.y = y;
		this.velx = vx;
		this.vely = vy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}
	
	
}
