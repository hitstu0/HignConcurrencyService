package com.example.station.Data;

import lombok.Data;

@Data
public class OrderInfo {
    private int id;
    private int userId;
    private int goodsId;
    private int nums;
    private boolean payed;
}
