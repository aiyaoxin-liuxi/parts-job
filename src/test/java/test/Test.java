package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mysql.fabric.xmlrpc.base.Array;

import freemarker.template.utility.StringUtil;

public class Test {
	 /** 
     * 判断是否匹配 
     * @param target 目标文本串 
     * @param mode 模式串 
     * @return 匹配结果 
     */  
    public static boolean matchString(String target, String mode) {  
        //为了和算法保持一致，使index从1开始，增加一前缀  
        String newTarget = "x" + target;   
        String newMode = 'x' + mode;  
          
        int[] K = calculateK(mode);  
          
        int i = 1;  
        int j = 1;  
        while(i <= target.length() && j <= mode.length()) {  
            if (j == 0 || newTarget.charAt(i) == newMode.charAt(j)) {  
                i++;  
                j++;  
            } else {  
                j = K[j];  
            }  
        }  
          
        if (j > mode.length()) {  
            return true;  
        }  
        return false;  
    }  
      
    /* 
     * 计算K值 
     */  
    private static int[] calculateK(String mode) {  
        //为了和算法保持一致，使index从1开始，增加一前缀  
        String newMode = "x" + mode;  
        int[] K = new int[newMode.length()];  
        int i = 1;  
        K[1] = 0;  
        int j = 0;  
          
        while(i < mode.length()) {  
            if (j == 0 || newMode.charAt(i) == newMode.charAt(j)){  
                i++;  
                j++;  
                K[i] = j;  
            } else {  
                j = K[j];  
            }  
        }  
          
        return K;  
    }  
    /** 
     * @param args 
     * @throws UnsupportedEncodingException 
     */  
    public static void main(String[] args) throws UnsupportedEncodingException {  
    	int num = 0;
    	String mu = "测试啊测试:【变量】再测试啊测试()【变量】啛啛喳喳擦擦擦（）";
    	
    	String[] ss = StringUtil.split(mu, "【变量】", false);
    	List<String> list = new ArrayList<String>();
    	for (String s : ss) {
    		num += s.length();
    		String sa = URLEncoder.encode(s, "utf-8");
    	    list.add(sa);
		}
    	System.out.println(num);
    	String chuan = "测试啊测试:123再测试啊测试()321啛啛喳喳擦擦擦（）";
    	boolean bb = true;
    	for (int i = 0; i < list.size(); i++) {
			
    		String s = list.get(i);
    		System.out.println(URLDecoder.decode(s, "utf-8"));
    		if(Test.matchString(chuan, URLDecoder.decode(s, "utf-8"))){
    			continue;
    		} else {
    			bb = false;
    		}
		}
    	System.out.println(bb);
    	
//    	String chuan = "测试啊测试123再测试啊测试321啛啛喳喳擦擦擦";
//    	
//    	
//    	
//    	
//    	
//        String a = "bcabcabcabbcabcabcabcab";  
//        String b = "bcabcabcabc";//"ababbaaba";//  
//        System.out.println(Test.matchString(a, b));  
  
    } 
	
    
	

}
