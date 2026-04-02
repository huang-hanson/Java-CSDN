package com.hanson.java.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 读取订单统计 JSON 文件的工具类
 */
public class JsonReader {

    public static void main(String[] args) {
        try {
            // 方式 1：从 classpath 读取资源文件
            OrderStatisticsResponse response = readFromResource("/json/query_order_statistics.json");

            // 方式 2：从文件系统路径读取
            // OrderStatisticsResponse response = readFromFilePath("D:\\code\\java\\Java-CSDN\\java-base\\src\\main\\resources\\json\\query_order_statistics.json");

            System.out.println("状态：" + response.getStatus());
            System.out.println("消息：" + response.getMessage());
            System.out.println("商品详情数量：" + response.getResultBody().getItemDetails().size());

            // 遍历打印商品详情
            for (ItemDetail item : response.getResultBody().getItemDetails()) {
                System.out.println("商品：" + item.getItemName() + ", SKU: " + item.getSkuName()
                        + ", 数量：" + item.getTotalNumber() + ", 金额：" + item.getTotalAmount());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Gson GSON = new Gson();

    /**
     * 从 classpath 资源文件读取 JSON
     */
    public static OrderStatisticsResponse readFromResource(String resourcePath) throws IOException {
        try (Reader reader = new java.io.InputStreamReader(
                JsonReader.class.getResourceAsStream(resourcePath))) {
            if (reader == null) {
                throw new IOException("无法找到资源文件：" + resourcePath);
            }
            return GSON.fromJson(reader, OrderStatisticsResponse.class);
        }
    }

    /**
     * 从文件系统路径读取 JSON
     */
    public static OrderStatisticsResponse readFromFilePath(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return GSON.fromJson(content, OrderStatisticsResponse.class);
    }

    /**
     * 从 File 对象读取 JSON
     */
    public static OrderStatisticsResponse readFromFile(File file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file.toPath())) {
            return GSON.fromJson(reader, OrderStatisticsResponse.class);
        }
    }
}

/**
 * 响应对象
 */
class OrderStatisticsResponse {
    private String status;
    private String message;
    private ResultBody resultBody;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBody getResultBody() {
        return resultBody;
    }

    public void setResultBody(ResultBody resultBody) {
        this.resultBody = resultBody;
    }
}

/**
 * 结果体对象
 */
class ResultBody {
    private List<ItemDetail> itemDetails;

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }
}

/**
 * 商品详情对象
 */
class ItemDetail {
    private String itemId;
    private String itemName;
    private String skuId;
    private String skuIdStr;
    private String skuName;
    private String businessId;
    private String totalNumber;
    private Double totalAmount;
    private Double totalFeeAmount;
    private Double totalTax;
    private Double totalTaxFreeAmount;
    private Double totalPartnerShareAmount;
    private Double totalIosSharePrice;
    private Double totalReceiveAmount;
    private Double price;
    private String payMethod;
    private String payMethodStr;
    private String iosProductId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuIdStr() {
        return skuIdStr;
    }

    public void setSkuIdStr(String skuIdStr) {
        this.skuIdStr = skuIdStr;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalFeeAmount() {
        return totalFeeAmount;
    }

    public void setTotalFeeAmount(Double totalFeeAmount) {
        this.totalFeeAmount = totalFeeAmount;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalTaxFreeAmount() {
        return totalTaxFreeAmount;
    }

    public void setTotalTaxFreeAmount(Double totalTaxFreeAmount) {
        this.totalTaxFreeAmount = totalTaxFreeAmount;
    }

    public Double getTotalPartnerShareAmount() {
        return totalPartnerShareAmount;
    }

    public void setTotalPartnerShareAmount(Double totalPartnerShareAmount) {
        this.totalPartnerShareAmount = totalPartnerShareAmount;
    }

    public Double getTotalIosSharePrice() {
        return totalIosSharePrice;
    }

    public void setTotalIosSharePrice(Double totalIosSharePrice) {
        this.totalIosSharePrice = totalIosSharePrice;
    }

    public Double getTotalReceiveAmount() {
        return totalReceiveAmount;
    }

    public void setTotalReceiveAmount(Double totalReceiveAmount) {
        this.totalReceiveAmount = totalReceiveAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodStr() {
        return payMethodStr;
    }

    public void setPayMethodStr(String payMethodStr) {
        this.payMethodStr = payMethodStr;
    }

    public String getIosProductId() {
        return iosProductId;
    }

    public void setIosProductId(String iosProductId) {
        this.iosProductId = iosProductId;
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", skuName='" + skuName + '\'' +
                ", totalNumber='" + totalNumber + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
