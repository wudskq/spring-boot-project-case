package cn.com.wudskq.sevice.aes;

public interface DecryptUtil {
   
    /**  
     * 解密  
     *  
     * @param result resultType的实例  
     * @return T  
     * @throws IllegalAccessException 字段不可访问异常  
     */  
     <T> T decrypt(T result) throws IllegalAccessException;  
      
}  