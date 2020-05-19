package com.example.smartmuseum.model;

/*搜索记录*/
public class SearchRecord {
    public String recordName;
    public String address;

    public SearchRecord(String name, String address) {
        recordName = name;
        this.address = address;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
