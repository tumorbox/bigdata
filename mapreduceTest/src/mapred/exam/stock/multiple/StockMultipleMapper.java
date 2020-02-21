package mapred.exam.stock.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 하둡을 실행할 때 사용자가 입력하는 옵션을 Mapper내부에서 사용할 수 있도록 옵션이 어떤값으로 입력되었냐에 따라 다른 작업을 할 수있도록 처리

public class StockMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	String jobType; // 사용자가 입력하는 옵션 값을 저장하기 위한 변수
	Text outputKey = new Text();
	static final IntWritable one = new IntWritable(1);

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		jobType = context.getConfiguration().get("jobType");
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
		
			String[] line = value.toString().split(",");
			if (line != null & line.length > 0) {
				String year = line[2].substring(0, 4);
				double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
				if (result > 0 ) {
					outputKey.set("up, "+year);
					context.write(outputKey, one);
					
				}else if(result < 0 ) {
					outputKey.set("down, "+year);
					context.write(outputKey, one);
					
				}else {
					outputKey.set("equl, "+year);
					context.write(outputKey, one);
				}
			}
		}
	}
}
