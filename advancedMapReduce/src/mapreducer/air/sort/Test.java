package mapreducer.air.sort;

public class Test {

	public static void main(String[] args) {
		
		/* 문자열 비교 - compareTo
		 * 
		 * compareTo의 결과
		 * 0 : 기준문자열, 매개변수 문자열이 같은 경우
		 * -1 : 기준문자열 < 매개변수 문자열 (아스키 코드값)
		 * 1 : 기준문자열 > 매개변수 문자열
		 * 
		 */
		String data1 = "a";
		String data2 = "b";
		System.out.println((int)'a');
		System.out.println((int)'b');
		System.out.println(data1.compareTo(data2));
		
		// hashcode
		// 헤시코드란 객체를 구분할 수 있는 정수값
		// ==> 정수값은 객체가 할당된 주소를 갖고 생성한다.
		// ==> hashcode메소드를 출력
		
		CustomKey key1 = new CustomKey();
		CustomKey key2 = new CustomKey();
		
		System.out.println(key1);
		System.out.println(key2);
		System.out.println(key1.hashCode());
		System.out.println(key2.hashCode());
		
		key1.setYear("1987");
		key2.setYear("1987");
		
		// 문자열에서 호출하는 해시코드는 Object의 hashcode메소드가 오버라이딩되어 주소를 가지고 정수값을 만들지 않고 문자열을 이용해서
		// 구분 정수값을 만들어서 리턴한다.
		// 따라서 문자열이 같으면  hashcode가 같다.
		
		System.out.println(key1.getYear().hashCode());
		System.out.println(key2.getYear().hashCode());

		System.out.println(key2.getYear().hashCode()%1);
		
	}

}