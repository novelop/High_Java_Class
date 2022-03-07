package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * 
 */
public class ThreadTest17 {
	private static Vector<Integer> vec = new Vector<Integer>();
	
	// 동기화 처리가 되지 않은 List
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	// 동기화 처리를 한 List 
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());

	public static void main(String[] args) {
	
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10000; i++) {
//					vec.add(i);
					list2.add(i);
				}
				
			}
		};
		//-----------------------------------------
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)	
		};
		
		for( Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
//		System.out.println("vec의 개수 : " + vec.size());
		System.out.println("list1의 개수 : " + list2.size());
	}
	
}
