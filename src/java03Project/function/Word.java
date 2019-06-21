package java03Project.function;
/*
需要製作打字列表
應該可以從File讀檔
*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class Word {
	private ArrayList<String> words;//讀取打字檔的單字
	private int wordsLength;//計算有多少個單字
	//TODO 
	//從這裡讀取打字的List	
	public Word(){

	}
	public int getWordsLength(){
		return wordsLength;
	}
	public void readWordFile(){//讀取word.txt 放到words

	}

}