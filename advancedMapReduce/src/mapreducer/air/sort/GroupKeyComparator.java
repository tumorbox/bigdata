package mapreducer.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator {

	protected GroupKeyComparator() {
		super(CustomKey.class,true);
	}
	
	//WritableComparable의 타입이 정확하지 않기 때문에 waring 발생
	// 타입에 대한 부분은 무시하고 채크하지 않고 처리하겠다는 의미
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		
		// 상위타입인데 하위타입으로 정의해서 에러 따라서 casting해주면 된다.
		CustomKey key1 = (CustomKey) obj1;
		CustomKey key2 = (CustomKey) obj2;
		return key1.getYear().compareTo(key2.getYear());

	}
	
	

}
