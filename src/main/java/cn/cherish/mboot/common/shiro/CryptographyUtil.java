package cn.cherish.mboot.common.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;

/**
 * 类描述：Shrio的加密工具类
 * 创建人：Cherish
 * 联系方式：18826137274/785427346@qq.com
 * 创建时间：2016年4月19日 下午11:12:28
 * @version 1.0
 */
public class CryptographyUtil {

	/**
	 * MD5加密密码或其他信息
	 * @param source 原信息
	 * @param salt 加盐
	 * @param hashIterations 散列次数
	 * @return String
	 * @date 2016年5月2日 上午11:38:03
	 */
	public static String md5(String source, String salt, int hashIterations){
		return new Md5Hash(source, salt, hashIterations).toString();
	}
	
	/**
	 * MD5加密密码或其他信息
	 * @param source	原信息
	 * @param salt		加盐
	 * @return String
	 */
	public static String md5(String source, String salt){
		return new Md5Hash(source, salt).toString();
	}
	
	/**
	 * MD5加密密码或其他信息
	 * @param source	原信息
	 * @return String
	 */
	public static String md5(String source){
		return new Md5Hash(source).toString();
	}

	/**
	 * sha1加密密码或其他信息
	 * @param source 原信息
	 * @param salt 加盐
	 * @param hashIterations 散列次数
	 * @return String
	 * @date 2016年5月2日 上午11:38:03
	 */
	public static String sha1(String source, String salt, int hashIterations){
		return new Sha1Hash(source, salt, hashIterations).toString();
	}

	/**
	 * sha1加密密码或其他信息
	 * @param source	原信息
	 * @param salt		加盐
	 * @return String
	 */
	public static String sha1(String source, String salt){
		return new Sha1Hash(source, salt).toString();
	}

	/**
	 * sha1加密密码或其他信息
	 * @param source	原信息
	 * @return String
	 */
	public static String sha1(String source){
		return new Sha1Hash(source).toString();
	}
	
	/*public static void main(String[] args) {
		String passwd = cherishSha1("cherish");
		System.out.println(passwd);
	}*/

	private static final String CHERISH_SALT = "cherish";
	public static String cherishSha1(String password){
		return new Sha1Hash(password, CHERISH_SALT, 2).toString();
	}

}
