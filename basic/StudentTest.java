package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름(String), 국어점수, 영어점수, 수학점수, 총점 , 등수를 멤버로 갖는 Student클래스를 만든다.
 *      이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 한다.
 *      이 클래스는 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 *      
 *      이 Student객체는 List에 저장하여 관리한다.
 *      
 *      List에 저장된 Student객체를 총점의 내림차순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스도 작성하시오. 
 *      
 *      (단, 등수는 List 에 전체 데이터가 모두 저장된 후에 구한다.)
 */

public class StudentTest {
	public void setRanking(List<Student> stdList) {
		for(Student std1 : stdList) { //기준이 되는 데이터를 위한 반복문 (등수를 구할 값)
			int rank = 1; //처음에는 등수를 1로  초기화 한다. 
			for(Student std2 : stdList) { //비교 대상을 찾기위한 반복문 
				if(std1.getSum() < std2.getSum()) {//기준보다 큰값을 만나면 rank값을 증가시킨다. 
					rank++;
				}
			}
			std1.setRank(rank);
		}	
	}
	
	

	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		
		List<Student> st = new ArrayList<Student>();
		st.add(new Student(5,"홍길동",97,33,44));
		st.add(new Student(3,"이순신",64,76,88));
		st.add(new Student(1,"성춘향",88,67,99));
		st.add(new Student(4,"강감찬",89,23,99));
		st.add(new Student(2,"일지매",56,86,42));
		st.add(new Student(7,"변학도",56,86,42));
		st.add(new Student(9,"노혜지",58,90,85));
		st.add(new Student(8,"앙구미",55,90,19));
		st.add(new Student(6,"일본인",95,50,44));
        //등수를 구하는 메서드 호출 
		test.setRanking(st);
		
		System.out.println("정렬전...");
		for(Student s : st) {
			System.out.println(s);
		}
		System.out.println();
		
		//학번의 오름차순으로 정렬하기 
		Collections.sort(st);
		
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Student s : st) {
			System.out.println(s);
		}
		System.out.println();
		
		//총점의 역순으로 정렬하기 
		Collections.sort(st, new SortSumDesc());
		
		System.out.println("총점의 내림차순정렬후...");
		for(Student s : st) {
			System.out.println(s);
		}
		
	}

}
class Student implements Comparable<Student>{
	int stuNum;
	String name;
	int kor;
	int eng;
	int math;
	int sum;
	int rank;
	
	public Student(int stuNum, String name, int kor, int eng, int math) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		sum = kor + eng + math;
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}
	//학번의 오름차순의 내부 정렬 기준 만들기 
	@Override
	public int compareTo(Student num) {
		return Integer.compare(stuNum, num.getStuNum());
	}

}
//총점의 역순으로 정렬하는데
//총점이 같으면 이름의 오름차순으로, 졍렬이 되는 외부 정렬 기준클래스
class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student std1, Student std2) {
	    if(std1.getSum() == std2.getSum() ) {
	       return std1.getName().compareTo(std2.getName());
	    }
		return Integer.compare(std1.getSum(), std2.getSum()) * -1;
	}
	
}
