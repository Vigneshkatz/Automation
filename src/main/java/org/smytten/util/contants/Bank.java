package org.smytten.util.contants;

public enum Bank {
    HDFC("HDFC"),
    ICICI("ICICI"),
    Axis("Axis"),
    Kotak("Kotak"),
    Yes_Bank("Yes Bank"),
    Pay_Now("Pay Now");

    private final String value;

    Bank(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
