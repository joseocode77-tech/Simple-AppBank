package ProyectoFinal;

import java.util.List;
import java.util.Scanner;

public class BankApp {
    private final BankAccount bankAccount;

    public BankApp(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void showMenu(Scanner scanner) {
        System.out.println("Bienvenido "+bankAccount.getAccountHolder()+" a su app bancaria :)");
        System.out.println("------------------------------------");
        boolean endApp = true;
        do {
            System.out.println("Eliga una opcion entre: \n a. Depositar dinero" +
                    "\n b. Retirar dinero" +
                    "\n c. Historial de movimientos" +
                    "\n d. Mostrar saldo" +
                    "\n e. Gestionar wardaditos" +
                    "\n f. Salir");
            char option = scanner.next().charAt(0);
            switch (option){
                case 'a':
                    System.out.println("Ingrese el monto a agregar a su saldo");
                    double addCash = scanner.nextDouble();
                    bankAccount.deposit(addCash);
                    System.out.println("-----------------------------------");
                    break;
                case 'b':
                    System.out.println("Ingrese el monto a retirar de su saldo");
                    double takeCash = scanner.nextDouble();
                    bankAccount.withdraw(takeCash);
                    System.out.println("-----------------------------------");
                    break;
                case 'c':
                    bankAccount.showTransactions();
                    System.out.println("-----------------------------------");
                    break;
                case 'd':
                    bankAccount.showBalance();
                    System.out.println("-----------------------------------");
                    break;
                case 'e':
                    showMenuGoals(scanner);
                    System.out.println("-----------------------------------");
                    break;
                case 'f':
                    System.out.println("Cerrando la app...");
                    endApp = false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while(endApp);
    }

    public void showMenuGoals(Scanner scanner){
        Goal.getName();
        boolean endMenuGoals = true;
        do {
            System.out.println("Eliga una opcion entre: " +
                    "\n a. Crear Wardadito" +
                    "\n b. Listar Wardaditos" +
                    "\n c. Buscar Wardadito" +
                    "\n d. Renombrar Wardadito" +
                    "\n e. Eliminar Wardadito" +
                    "\n f. Enviar dinero al Wardadito" +
                    "\n g. Regresar dinero a la cuenta" +
                    "\n h. Volver");
            char option = scanner.next().charAt(0);
            scanner.nextLine();
            switch (option){
                case 'a':
                    System.out.println("Ingrese nombre de Wardadito: ");
                    String nameGoal = scanner.nextLine();
                    if (nameGoal.isBlank()){
                        System.out.println("El nombre del wardadito no debe estar vacio");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    if (bankAccount.createGoals(nameGoal)){
                        System.out.println("Wardadito creado con exito :)");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("No valido. Ya existe un Wardadito con este nombre");
                    System.out.println("-----------------------------------");
                    break;
                case 'b':
                    List<String> listGoal = bankAccount.showGoals();
                    if (listGoal == null){
                        System.out.println("No tiene Wardaditos creados");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    for (String goal : listGoal){
                        System.out.println(goal);
                    }
                    System.out.println("-----------------------------------");
                    break;
                case 'c':
                    System.out.println("¿Que Wardadito desea buscar?");
                    String search = scanner.nextLine();
                    List<String> results = bankAccount.searchGoal(search);
                    if (search.isBlank()) {
                        System.out.println("Debes ingresar al menos una letra");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    if (results.isEmpty()){
                        System.out.println("No se encontraron Wardaditos");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Resultados de busqueda: ");
                    for (String result : results){
                        System.out.println(result);
                        System.out.println("-----------------------------------");
                    }
                    break;
                case 'd':
                    System.out.println("Ingrese el nombre del wardadito a modificar: ");
                    String currentName = scanner.nextLine();
                    if(bankAccount.existsGoal(currentName) == null){
                        System.out.println("Nombre Wardadito no encontrado, asegurese de escribirlo correcatmente");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Nombre de guardadito encontrado");
                    System.out.println("Ahora ingrese el nuevo nombre del wardadito: ");
                    String newName = scanner.nextLine();
                    if (bankAccount.renameGoal(currentName, newName)){
                        System.out.println("El wardadito ha sido renombrado con exito :)");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("El nombre no debe estar en blanco");
                    System.out.println("-----------------------------------");
                    break;
                case 'e':
                    System.out.println("Ingrese el nombre del wardadito a eliminar: ");
                    String name = scanner.nextLine();
                    if(bankAccount.existsGoal(name) == null){
                        System.out.println("Nombre Wardadito no encontrado, asegurese de escribirlo correcatmente");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    if (bankAccount.deleteGoal(name)){
                        System.out.println("El wardadito ha sido eliminado con exito");
                        System.out.println("-----------------------------------");
                        break;
                    }
                case 'f':
                    System.out.println("¿A que wardadito desea agregar dinero?");
                    String cashAddName = scanner.nextLine();
                    if(bankAccount.existsGoal(cashAddName) == null){
                        System.out.println("Nombre Wardadito no encontrado, asegurese de escribirlo correcatmente");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Ingrese el monto que desea agregar: ");
                    double cashAdd = scanner.nextDouble();
                    if (bankAccount.setCash(cashAddName, cashAdd)){
                        System.out.println("El dinero ha sido agregado con exito :)");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Debe ingresar un monto permitido");
                    System.out.println("-----------------------------------");
                    break;
                case 'g':
                    System.out.println("¿De que wardadito desea retirar dinero?");
                    String cashBackName = scanner.nextLine();
                    if(bankAccount.existsGoal(cashBackName) == null){
                        System.out.println("Nombre Wardadito no encontrado, asegurese de escribirlo correcatmente");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Ingrese el monto que desea agregar: ");
                    double cashBack = scanner.nextDouble();
                    if (bankAccount.backCash(cashBackName, cashBack)){
                        System.out.println("El dinero ha sido regresado con exito :)");
                        System.out.println("-----------------------------------");
                        break;
                    }
                    System.out.println("Debe ingresar un monto permitido");
                    System.out.println("-----------------------------------");
                    break;
                case 'h':
                    System.out.println("Redireccionando...");
                    System.out.println("-----------------------------------");
                    endMenuGoals = false;
                    break;
                default:
                    System.out.println("Debe ingresar una opcion correcta");
            }
        } while (endMenuGoals);
    }
}