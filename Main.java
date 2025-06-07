import view.FuncionarioUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new FuncionarioUI().setVisible(true);
        });
    }
}
