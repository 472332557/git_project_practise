package com.liangzc.example;

public class FeeItemType {

    private String feeItemTypeId;

    private String feeItemTypeName;

    private String Type;


    public FeeItemType(String feeItemTypeId, String feeItemTypeName, String type) {
        this.feeItemTypeId = feeItemTypeId;
        this.feeItemTypeName = feeItemTypeName;
        Type = type;
    }

    public String getFeeItemTypeId() {
        return feeItemTypeId;
    }

    public void setFeeItemTypeId(String feeItemTypeId) {
        this.feeItemTypeId = feeItemTypeId;
    }

    public String getFeeItemTypeName() {
        return feeItemTypeName;
    }

    public void setFeeItemTypeName(String feeItemTypeName) {
        this.feeItemTypeName = feeItemTypeName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "FeeItemType{" +
                "feeItemTypeId='" + feeItemTypeId + '\'' +
                ", feeItemTypeName='" + feeItemTypeName + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
