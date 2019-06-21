package java03Project.function;
/*
玩家的基本資料
*/
public class Player {
	private int hp;//生命值
	private Rank gameResult;//等級&積分
	private int money;//錢
	private Prop[] props;//道具

	public Player(){
		hp = 1000;
		money = 800;
		gameResult = new Rank();
		props = new Prop[3];
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	public void setRank(Rank gameResult){
		this.gameResult = gameResult;
	}
	public void setMoney(int money){
		this.money = money;
	}
	public void setProps(Prop[] props){
		this.props = props;
	}
	public Rank getGameResult(){
		return gameResult;
	}
	public int getHp() {
		return hp;
	}
	public int getMoney() {
		return money;
	}
} // end class RankOperation