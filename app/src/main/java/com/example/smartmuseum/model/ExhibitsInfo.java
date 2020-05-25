package com.example.smartmuseum.model;

/*展品信息*/
public class ExhibitsInfo {

    //介绍信息
    private String introduceInfo;

    //AR互动
    private String arInteractive;

    //评价
    private String evaluation;

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }

    public String getArInteractive() {
        return arInteractive;
    }

    public void setArInteractive(String arInteractive) {
        this.arInteractive = arInteractive;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
