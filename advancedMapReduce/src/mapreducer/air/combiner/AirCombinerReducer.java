package mapreducer.air.combiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AirCombinerReducer
	extends Reducer<Text, IntWritable,Text,IntWritable>{
	IntWritable resultVal = new IntWritable();
	
	@Override
	protected void reduce(Text key,
			Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value:values) {//reduce에 전달된
			sum = sum+value.get();      //입력데이터의 값을
		}                               //꺼내서 모두 더하기
		resultVal.set(sum);
		context.write(key, resultVal);
		context.getCounter("mycounter", "combiner_wordcount").increment(1);	//reducer가 콜될때마다 하나씩 증가하게 (input데이터 하나당 한번씩)
	}
}







