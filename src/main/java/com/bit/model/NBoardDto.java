package com.bit.model;

import java.util.Date;

public class NBoardDto {
	private String nqid, content, img, sub, id;
	private Date wDate;
	public String getNqid() {
		return nqid;
	}
	public void setNqid(String nqid) {
		this.nqid = nqid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((nqid == null) ? 0 : nqid.hashCode());
		result = prime * result + ((sub == null) ? 0 : sub.hashCode());
		result = prime * result + ((wDate == null) ? 0 : wDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NBoardDto other = (NBoardDto) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (nqid == null) {
			if (other.nqid != null)
				return false;
		} else if (!nqid.equals(other.nqid))
			return false;
		if (sub == null) {
			if (other.sub != null)
				return false;
		} else if (!sub.equals(other.sub))
			return false;
		if (wDate == null) {
			if (other.wDate != null)
				return false;
		} else if (!wDate.equals(other.wDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NBoardDto [nqid=" + nqid + ", content=" + content + ", img=" + img + ", sub=" + sub + ", id=" + id
				+ ", wDate=" + wDate + "]";
	}

	
}
