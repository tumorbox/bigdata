package mapred.exam.stock.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class StockMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable resultVal = new IntWritable();
	Text resultkey = new Text();
	MultipleOutputs<Text, IntWritable> multiout;
	

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiout = new MultipleOutputs<Text, IntWritable>(context);
		
	}
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiout.close();
	}

	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		// 1. Mapper에서 전달받은 key에서 구분값을 분리
		String[] data = key.toString().split(",");
		resultkey.set(data[1]);
		
		// 2. key에 추가된 구분자별로 output
		if(data[0].equals("up")) {
			int sum=0;
			for(IntWritable value : values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);	//out value에 추가하기
			multiout.write("up", resultkey, resultVal);
		}else if (data[0].equals("down")) {
			int sum=0;
			for(IntWritable value : values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);	//out value에 추가하기
			multiout.write("down", resultkey, resultVal);
		}else {
			int sum=0;
			for(IntWritable value : values) {
				sum = sum+value.get();
			}
			resultVal.set(sum);	//out value에 추가하기
			multiout.write("equal", resultkey, resultVal);
		}
		
	}



}	

