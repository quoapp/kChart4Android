package com.wuchuanlong.stockview.chart;


public class SingleStockInfo {
	int color;// ����ǵ��������ɫΪ��ɫ  ������ǣ������ɫΪ��ɫ
	double open ; // ���̼�
	double close; // ���̼�
	double high;// ��߼�
	double low ;// ��߼�
	int date;// ����
	double dealCount;// һ��ɽ��ɽ����������ܳɽ���
	double dealPrice;// �ܳɽ����;
	// ��ʱͼ����
	// ������
    private int minute;
    // �ּ�
    private double now;
    //	����
    private double avgPrice;
	
	
	double maValue5;// 5�վ���ֵ
	double maValue10;// 10�վ���
	double maValue20;// 20�վ���
	
	Type type;// ������K��������K��
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public double getMaValue10() {
		return maValue10;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public double getNow() {
		return now;
	}
	public void setNow(double now) {
		this.now = now;
	}
	public double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public void setMaValue10(double maValue10) {
		this.maValue10 = maValue10;
	}
	public double getMaValue20() {
		return maValue20;
	}
	public void setMaValue20(double maValue20) {
		this.maValue20 = maValue20;
	}
	public double getMaValue5() {
		return maValue5;
	}
	public void setMaValue5(double maValue) {
		this.maValue5 = maValue;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public double getDealCount() {
		return dealCount;
	}
	public void setDealCount(double dealCount) {
		this.dealCount = dealCount;
	}
	public double getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(double dealPrice) {
		this.dealPrice = dealPrice;
	}
	
	
}