package ProyectoFinal;

public class Transaction {
    private final double amount;
    private final String type;

    public Transaction(double amount, String type){
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType(){
        return type;
    }
}
