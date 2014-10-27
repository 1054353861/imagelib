package org.fireking.app.imagelib;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * ͼƬ��ȡ������
 * 
 * @author join
 *
 */
public class AlbumHelper {

	Context context;
	ContentResolver contentResolver;

	private static AlbumHelper instance;

	private AlbumHelper(Context context) {
		this.context = context;
		contentResolver = context.getContentResolver();
	}

	public static AlbumHelper newInstance(Context context) {
		if (instance == null) {
			instance = new AlbumHelper(context);
		}
		return instance;
	};

	/**
	 * �õ��ļ�����Ϣ<br />
	 * �����ļ��м���,ÿ���ļ��������n����ļ�
	 */
	public List<AlbumBean> getFolders() {
		Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

		Cursor mCursor = contentResolver.query(mImageUri, null,
				MediaStore.Images.Media.MIME_TYPE + "=? or "
						+ MediaStore.Images.Media.MIME_TYPE + "=?",
				new String[] { "image/jpeg", "image/png" },
				MediaStore.Images.Media.DATE_MODIFIED);
		HashMap<String, List<ImageBean>> map = capacity(mCursor);

		List<AlbumBean> mAlbumBeans = new ArrayList<AlbumBean>();

		Set<Entry<String, List<ImageBean>>> set = map.entrySet();
		for (Iterator<Map.Entry<String, List<ImageBean>>> iterator = set
				.iterator(); iterator.hasNext();) {
			Map.Entry<String, List<ImageBean>> entry = iterator.next();
			String parentName = entry.getKey();
			ImageBean b = entry.getValue().get(0);
			mAlbumBeans.add(new AlbumBean(parentName, entry.getValue().size(),
					entry.getValue(), b.path));
		}
		System.out.println(mAlbumBeans.toString());
		return mAlbumBeans;
	}

	/**
	 * ����ȡ��ͼƬ��Ϣ���顢װ��<br />
	 * ���ļ��з���
	 */
	private HashMap<String, List<ImageBean>> capacity(Cursor mCursor) {

		HashMap<String, List<ImageBean>> beans = new HashMap<String, List<ImageBean>>();
		while (mCursor.moveToNext()) {
			// �õ�ͼƬ����·��
			String path = mCursor.getString(mCursor
					.getColumnIndex(MediaStore.Images.Media.DATA));

			long size = mCursor.getLong(mCursor
					.getColumnIndex(MediaStore.Images.Media.SIZE));

			String display_name = mCursor.getString(mCursor
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));

			String parentName = new File(path).getParentFile().getName();
			List<ImageBean> sb;
			if (beans.containsKey(parentName)) {
				sb = beans.get(parentName);
				sb.add(new ImageBean(parentName, size, display_name, path));
			} else {
				sb = new ArrayList<ImageBean>();
				sb.add(new ImageBean(parentName, size, display_name, path));
			}
			beans.put(parentName, sb);
		}
		return beans;
	}
}
