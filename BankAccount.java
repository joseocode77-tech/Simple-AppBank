package ProyectoFinal;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final String holderName;
    private double balance;
    private final List<Transaction> transactions;
    private List<Goal> goals;

    private static final String BANK_NAME = "BCP Perú";

    public BankAccount(String holderName, double balance) {
        this.holderName = holderName;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.goals = new ArrayList<>();
    }

    public static void getNameBank(){
        System.out.println("================== "+BANK_NAME+" ==================");
    }

    public String getAccountHolder() {
        return holderName;
    }

    public void deposit(double addCash){
        String typeTransaction = "DEPOSITO";
        if (addCash > 0){
            balance += addCash;
            System.out.println("Deposito de dinero exitoso :)");
            transactions.add(new Transaction(addCash, typeTransaction));
        } else {
            System.out.println("El monto depositado es invalido");
        }
    }

    public void withdraw(double takeCash){
        String typeTransaction = "RETIRO";
        if (takeCash > 0 && takeCash <= balance) {
            balance -= takeCash;
            System.out.println("Retiro de dinero exitoso :)");
            transactions.add(new Transaction(takeCash, typeTransaction));
        } else {
            System.out.println("El monto de retiro no es valida");
        }
    }

    public void showBalance(){
        System.out.println("Dinero disponible: "+ balance);
    }

    public void showTransactions(){
        System.out.println("Historial de movimientos: ");
        for (Transaction transaction : transactions){
            if (transaction.getType().equals("RETIRO")){
                System.out.println("-"+transaction.getAmount());
            } else {
                System.out.println("+"+transaction.getAmount());
            }
        }
    }

    public boolean createGoals(String accountGoal){
        double saving = 0;
        String refactorAccount = accountGoal.trim();
        for (Goal goal : goals){
            if (goal.getAccountGoal().equalsIgnoreCase(accountGoal)){
                return false;
            }
        }
        goals.add(new Goal(refactorAccount, saving));
        return true;
    }

    public List<String> showGoals(){
        List<String> listGoal = new ArrayList<>();
        if (!goals.isEmpty()){
            for (Goal goal : goals){
                listGoal.add(goal.getAccountGoal()+" S/."+goal.getSaving());
            }
            return listGoal;
        }
        return null;
    }

    public List<String> searchGoal(String search){
        List<String> results = new ArrayList<>();
        String searchModify = search.trim().toLowerCase();
        for (Goal goal : goals){
            String goalModify = goal.getAccountGoal().toLowerCase();
            if (goalModify.contains(searchModify)){
                results.add(goalModify);
            }
        }
        return results;
    }

    public Goal existsGoal(String nameGoal){
        for (Goal goal : goals){
            if (goal.getAccountGoal().equalsIgnoreCase(nameGoal)){
                return goal;
            }
        }
        return null;
    }

    public boolean renameGoal(String currentNameGoal, String newNameGoal){
        if (!newNameGoal.isBlank()){
            Goal goalGet = existsGoal(currentNameGoal);
            goalGet.setAccountGoal(newNameGoal);
            return true;
        }
        return false;
    }

    public boolean deleteGoal(String nameGoal){
        Goal goalGet = existsGoal(nameGoal);
        balance += goalGet.getSaving();
        goals.remove(goalGet);
        return true;
    }

    public boolean setCash(String cashAddName, double cash){
        Goal goalGet = existsGoal(cashAddName);
        if (cash <= balance && cash > 0.9){
            balance -= cash;
            double newCashGoal = goalGet.getSaving() + cash;
            goalGet.setSaving(newCashGoal);
            return true;
        }
        return false;
    }

    public boolean backCash(String cashBakName, double cash){
        Goal goalGet = existsGoal(cashBakName);
        if (goalGet.getSaving() >= cash && cash > 0.9){
            balance += cash;
            double newCashGoal = goalGet.getSaving() - cash;
            goalGet.setSaving(newCashGoal);
            return true;
        }
        return false;
    }
}