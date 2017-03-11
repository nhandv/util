
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.nyan.util.FileContext;
import com.nyan.util.dateUtil.DateFormat;
import com.nyan.util.stringUtils.ConvertByte;

public class App {

	public static void main(String[] args) throws Exception {
//		FileContext context = FileContext.getInstance();
//		try {
//			context.getKey(FileContext.KEY_AES);
//		} catch (IllegalArgumentException | IOException e) {
//			e.printStackTrace();
//		}
//		String s = "nyan";
//		byte[] b = null;
//		try {
//			b = s.getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(new String(b,"UTF-8"));
//		String rs =ConvertByte.getHexString(b);
//		System.out.println(rs);
//		byte[] bs = ConvertByte.hexStringToByteArray(rs);
//		System.out.println(new String(bs,"UTF-8"));
		
		Date dt1 = DateFormat.getFristDayOfMonth(1, 900);
		System.out.println(dt1);
		Date dt2 = DateFormat.getCurentDate();
		System.out.println(dt2);
	}

}
