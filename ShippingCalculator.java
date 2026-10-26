public class ShippingCalculator {
    public static void main(String[] args) {
        double orderTotal = 1250.50;
        int stockQuantity = 0; 

        double shippingFree = (orderTotal>= 1000.0) ? 0.0 : 150.0;
        String itemStatus = (stockQuantity > 0) ? "In Stock" : "OUt of Stock";

        System.out.println("Shipping Free: PHP" + shippingFree);
        System.out.println("Item Avilability:" + itemStatus);
    }
}