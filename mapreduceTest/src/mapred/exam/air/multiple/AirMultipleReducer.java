package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	IntWritable resultVal = new IntWritable();
	Text resultkey = new Text();
	MultipleOutputs<Text, IntWritable> multiout;

	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiout.close();
	}

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {

		multiout = new MultipleOutputs<Text, IntWritable>(context);
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] data = key.toString().split(",");
		resultkey.set(data[1]);

		if (data[0].equals("departure")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiout.write("departure", resultkey, resultVal);

		} else if (data[0].equals("arrival")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiout.write("arrival", resultkey, resultVal);

		} else if (data[0].equals("dNA")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiout.write("dNA", resultkey, resultVal);

		} else if (data[0].equals("aNA")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiout.write("aNA", resultkey, resultVal);
		}
	}
}
