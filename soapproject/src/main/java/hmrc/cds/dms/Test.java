package hmrc.cds.dms;

public class Test {
	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		String y = "abc";
		String x = "xyz";

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				builder1.append(x);
			}
			
			String z = builder1.toString();
			System.out.println(z);
			builder.append(z);

		}
		String a = builder.toString();
		System.out.println(a);
	}
}