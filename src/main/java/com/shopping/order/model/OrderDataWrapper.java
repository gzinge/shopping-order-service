package com.shopping.order.model;

import java.util.List;

public class OrderDataWrapper {
    private List<ItemData> itemDataList;

    public OrderDataWrapper() {
    }

    public List<ItemData> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(List<ItemData> itemDataList) {
        this.itemDataList = itemDataList;
    }
}
