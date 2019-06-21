package java03Project.function;
/*依據模式 選擇給予玩家獎勵
  分為一般 & 生存模式
*/
public class Reward{
	private final int NORMAL = 1;//一般模式
	private final int LIVE = 2;//生存模式

	public Reward(Player player,int gameMode){
		if(gameMode == NORMAL){//一般模式給錢
			int money = moneyReward(player.getGameResult());
			player.setMoney(money);
		}
		else if(gameMode == LIVE){//生存模式給錢&道具
			int money = moneyReward(player.getGameResult());
			player.setMoney(money);
			//TODO 得到一個隨機的道具
		}
	}
	public int moneyReward(Rank gameResult){//依據得到的rank 決定獲得多少錢
		if(gameResult.getRank().equals("S")){
			return 500;
		}
		else if(gameResult.getRank().equals("A")){
			return 300;
		}
		else if(gameResult.getRank().equals("B")){
			return 200;
		}
		else if(gameResult.getRank().equals("C")){
			return 100;		
		}
		else{//F rank
			return 50;
		}
	}
	/*public Prop propReward(){
		return Prop.randomOneProp();
	}*/
}