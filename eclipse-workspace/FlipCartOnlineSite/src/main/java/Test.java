import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		 
		String str = "My Name Is Sonal Thorat";
		
		String rev = "";
		
		String array [] = str.split(" ");
		
		for(int i=0;i<array.length;i++)
		{
			for(int j=array[i].length();j>=0;j--)
			{
				char ch = array[i].charAt(i);
				rev = rev+ch;
				System.out.println(rev);
			}
		}
	}
}
		
//		"Ym Eman Si Lanos Taroht" 
