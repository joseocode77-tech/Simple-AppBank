package ProyectoFinal;

public class Goal {
    private String accountGoal;
    private double saving;
    private static final String NAME = "GUARDADITOS";

    public Goal(String accountGoal, double saving) {
        this.accountGoal = accountGoal;
        this.saving = saving;
    }

    public static void getName(){
        System.out.println("=============== "+NAME+" ===============");
    }

    public String getAccountGoal() {
        return accountGoal;
    }

    public double getSaving() {
        return saving;
    }

    public void setAccountGoal(String accountGoal) {
        this.accountGoal = accountGoal;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }
}
