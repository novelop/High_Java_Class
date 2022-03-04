package kr.or.ddit.basic;
/*
 *  10마리의 말들이 경주하는 경마 프로그램 작성하기 
 *  
 *  말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
 *  이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다. 
 *  그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다. 
 *  (compare인터페이스 구현)
 *  
 *  경기 구간은 1 ~ 50 구간으로 되어 있다. 
 *   
 *   경기가 끝나면 등수 순으로 출력한다. 
 *   
 *   경기 진행 중일 때는 중간 중간에 말들의 위치를 아래와 같이 나타낸다. 
 *   예) 
 *   01번말 :-------->----------------------------
 *   02번말 :----------->-------------------------
 *   ...
 *   10번말 :--------->---------------------------
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	private int location;
	
	
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public Horse(String horseName, int rank, int location) {
		super();
		this.horseName = horseName;
		this.rank = rank;
		this.location = location;
	}
	public Horse() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Horse o) {
		return new Integer(rank).compareTo(o.getRank());
	}
	
	  
	
}