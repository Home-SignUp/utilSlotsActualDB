package com.win.utility.model.sale_events;

import java.io.Serializable;

public class SaleEventItemEntityPK implements Serializable {

    private static final long serialVersionUID = 4838825079113497526L;

    private int groupId;
    private long eventId;

    public SaleEventItemEntityPK() {
    }

    public SaleEventItemEntityPK(int groupId, long eventId) {
        this.groupId = groupId;
        this.eventId = eventId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
