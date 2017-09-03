package com.win.utility.model.sale_events;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sale_events")
public abstract class SaleEventItemEntity {

    @Id
    private SaleEventItemEntityPK id;
    private int groupId;
    private int groupPercent;
    private int saleEventType;
    private String type;
    private String name;
    private String graphicId;
    private Integer promotionType;
    private String url;

    public SaleEventItemEntity() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public SaleEventItemEntity(SaleEventItemEntityPK id) {
        this.id = id;
    }

    public SaleEventItemEntityPK getId() {
        return id;
    }

    public void setId(SaleEventItemEntityPK id) {
        this.id = id;
    }

    public int getGroupPercent() {
        return groupPercent;
    }

    public void setGroupPercent(int groupPercent) {
        this.groupPercent = groupPercent;
    }

    public int getSaleEventType() {
        return saleEventType;
    }

    public void setSaleEventType(int saleEventType) {
        this.saleEventType = saleEventType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(String graphicId) {
        this.graphicId = graphicId;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
