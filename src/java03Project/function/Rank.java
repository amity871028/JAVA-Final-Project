package java03Project.function;
/*

根據下列的標準去計算rank and 總積分

perfect、good、bad
(分數:Perfect:100 good : 50 bad : 0 )
Result has :S、A、B、C 、over C 顯示 F  
*/

public class Rank {
	private final int PERFECT = 100;
	private final int GOOD = 50;
	private final int BAD = 0;
	private int perfectCount;
	private int goodCount;
	private int badCount;
	private int totalPoints;
	private String rank;

	public Rank(){
		rank = new String("F");
		perfectCount = 0;
		goodCount = 0;
		badCount = 0;
		totalPoints = 0;
	}
	public String getRank(){
		return rank;
	}
	public int getPerfectCount(){
		return perfectCount;
	}
	public int getTotalPoints(){
		return totalPoints;
	}
	/*計算rank*/
	public void rankOperation(Word word){
		if(word.getWordsLength() == 0){
			return;
		}
		int tempLength = word.getWordsLength();
		if(getPerfectCount()>=(int)(tempLength*0.9)){//當perfect的數量 超過總字數的9成,取得S rank
			rank = new String("S");
		}
		else if(getPerfectCount()>=(int)(tempLength*0.8)){//當perfect的數量 超過總字數的8成,取得A rank
			rank = new String("A");
		}
		else if(getPerfectCount()>=(int)(tempLength*0.7)){//當perfect的數量 超過總字數的7成,取得B rank
			rank = new String("B");
		}
		else if(getPerfectCount()>=(int)(tempLength*0.6)){//當perfect的數量 超過總字數的6成,取得C rank
			rank = new String("C");
		}
		else{
			rank = new String("F");
		}
	}
	/*計算總得分*/
	public void totalPointsOperation(){
		totalPoints = PERFECT * perfectCount + GOOD * goodCount;
	}

} // end class Rank