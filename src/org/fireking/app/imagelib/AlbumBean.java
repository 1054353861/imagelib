package org.fireking.app.imagelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AlbumBean {

	// �ļ���
	public String folderName;

	// �ļ������ļ�����
	public int count;

	// �ļ�
	public List<ImageBean> sets = new ArrayList<ImageBean>();

	// ����ͼ
	public String thumbnail;

	public AlbumBean() {
		super();
	}

	public AlbumBean(String folderName, int count, List<ImageBean> sets,
			String thumbnail) {
		super();
		this.folderName = folderName;
		this.count = count;
		this.sets = sets;
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "AlbumBean [folderName=" + folderName + ", count=" + count
				+ ", sets=" + sets + ", thumbnail=" + thumbnail + "]";
	}

}
