package mapred.exam.stock;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] line = value.toString().split(",");
			// 년도, 상승마감
			if (line != null & line.length > 0) {
				outputKey.set(line[2].substring(0, 4));
				double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
				if (result > 0) {
					context.write(outputKey, outputVal);
				}
			}
		}
		/*
		 * if(key.toString()!="0") {
		 * 
		 * String [] split = value.toString().split(",");
		 * 
		 * if(Double.parseDouble(split[6])-Double.parseDouble(split[3])>0) { String []
		 * date = split[2].split("-"); outputKey.set(date[0]); context.write(outputKey,
		 * outputVal);
		 * 
		 * } }
		 */
	}
}
