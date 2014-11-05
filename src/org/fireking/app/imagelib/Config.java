package org.fireking.app.imagelib;

/**
 * ���������ļ�
 * 
 * @author join
 *
 */
public class Config {

	static int limit;
	static String savePathString;

	static {
		limit = 4;
		savePathString = "/temp";
	}

	/**
	 * ����������ѡ���ͼƬ����
	 * 
	 * @param limit
	 */
	public static void setLimit(int limit) {
		Config.limit = limit;
	}

	/**
	 * ��ȡ����ѡ���ͼƬ����
	 * 
	 * @return
	 */
	public static int getLimit() {
		return limit;
	}

	/**
	 * �ļ�����·��<br />
	 * ��Ҫ���������ļ�
	 * 
	 * @return
	 */
	public static void setSavePath(String path) {
		Config.savePathString = path;
	}

	/**
	 * ��ȡͼƬ����·��
	 * 
	 * @return
	 */
	public static String getSavePath() {
		return savePathString;
	}
}
