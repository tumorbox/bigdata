package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Text outputKey = new Text();
	static final IntWritable one = new IntWritable(1);

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] line = value.toString().split(",");
			if (line != null & line.length > 0) {
							
				// 출발지연
				if (!line[15].equals("NA") && Integer.parseInt(line[15]) > 0) {
					outputKey.set("departure,"+ line[1]+"월");
					context.write(outputKey, one);
				}else if (!line[14].equals("NA") && Integer.parseInt(line[14]) > 0) {
					outputKey.set("arrival,"+line[1]+"월");
					context.write(outputKey, one);
				}else if(line[15].equals("NA")){
					outputKey.set("dNA,"+line[1]);
					context.write(outputKey, one);
				}else if(line[14].equals("NA")){
					outputKey.set("aNA,"+line[1]);
					context.write(outputKey, one);
				}
			}
		}
	}
}