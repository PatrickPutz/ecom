package org.campus02.ecom;

public class BasketData {
	
	private final String buyingLocation;
	private final String productCategory;
	private final Double orderTotal;
	private final String paymentType;
	
	public BasketData(String buyingLocation, String productCategory, Double orderTotal, String paymentType) {
		super();


		this.buyingLocation = buyingLocation;
		this.productCategory = productCategory;
		this.orderTotal = orderTotal;
		this.paymentType = paymentType;
	}

	public String getBuyingLocation() {
		return buyingLocation;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public String getPaymentType() {
		return paymentType;
	}

	@Override
	public String toString() {
		return "BasketData [buyingLocation=" + buyingLocation + ", productCategory=" + productCategory + ", orderTotal="
				+ orderTotal + ", paymentType=" + paymentType + "]";
	}
	
}
