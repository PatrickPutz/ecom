package org.campus02.ecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasketAnalyzer {

    private ArrayList<BasketData> baskets;

    public BasketAnalyzer(ArrayList<BasketData> baskets) {
        this.baskets = baskets;
    }

    public List<BasketData> getEveryNthBasket(int n){
        List<BasketData> list = new ArrayList<>();

        for (BasketData basket : baskets) {

            for(int i = 0; i < baskets.size(); i += n){
                list.add(baskets.get(i));
            }
        }

        return list;
    }

    public List<BasketData> filterBaskets(String paymentType, Double from, Double to){
        List<BasketData> list = new ArrayList<>();

        for (BasketData b : baskets) {
            if(b.getPaymentType().equalsIgnoreCase(paymentType) && b.getOrderTotal() >= from && b.getOrderTotal() <= to)
                list.add(b);
        }

        return list;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory(){
        HashMap<String, ArrayList<Double>> result = new HashMap<>();

        for (BasketData basket : baskets) {

            if(!result.containsKey(basket.getProductCategory()))
                result.put(basket.getProductCategory(), new ArrayList<Double>());

            ArrayList<Double> list = result.get(basket.getProductCategory());
            list.add(basket.getOrderTotal());

        }

        return result;
    }

}
