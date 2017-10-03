package com.destiny.work.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Destiny_hao on 2017/9/11.
 */
@Entity
@Table(name = "tb_item", schema = "ssh", catalog = "")
public class TbItemEntity {
    private long id;
    private String title;
    private String sellPoint;
    private long price;
    private int num;
    private String barcode;
    private String image;
    private long cid;
    private byte status;
    private Timestamp created;
    private Timestamp updated;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "sell_point")
    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    @Basic
    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "barcode")
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "cid")
    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "updated")
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbItemEntity that = (TbItemEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (num != that.num) return false;
        if (cid != that.cid) return false;
        if (status != that.status) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (sellPoint != null ? !sellPoint.equals(that.sellPoint) : that.sellPoint != null) return false;
        if (barcode != null ? !barcode.equals(that.barcode) : that.barcode != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (sellPoint != null ? sellPoint.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + num;
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (int) (cid ^ (cid >>> 32));
        result = 31 * result + (int) status;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }
}
