public class BankAccount {
    // private - доступен только внутри этого класса
    private String accountNumber;
    
    // по умолчанию (без спецификатора) - доступен в том же пакете
    String bankName;
    
    // protected - доступен в том же пакете и наследникам
    protected double interestRate;
    
    // public - доступен всем
    public String ownerName;
    
    // private метод - доступен только внутри класса
    private String getEncryptedAccountNumber() {
        return "***" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    // метод по умолчанию - доступен в том же пакете
    void setBankName(String name) {
        this.bankName = name;
    }
    
    // protected метод - доступен в пакете и наследникам
    protected void applyInterest() {
        System.out.println("Начислены проценты по ставке: " + interestRate);
    }
    
    // public метод - доступен всем
    public void displayAccountInfo() {
        System.out.println("Владелец: " + ownerName);
        System.out.println("Счет: " + getEncryptedAccountNumber());
        System.out.println("Банк: " + bankName);
    }
    
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("1234567890");
        
        // private - ОШИБКА: недоступно извне
        // account.accountNumber = "111"; // ошибка компиляции
        // String num = account.getEncryptedAccountNumber(); // ошибка компиляции
        
        // по умолчанию - доступно в том же пакете (если Main в том же пакете)
        account.bankName = "MyBank";
        account.setBankName("NewBank");
        
        // protected - доступно в том же пакете (если Main в том же пакете)
        account.interestRate = 5.0;
        account.applyInterest();
        
        // public - доступно везде
        account.ownerName = "Иван Иванов";
        account.displayAccountInfo();
    }
}