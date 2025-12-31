//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.SwingUtilities;//quản lý luồng giao diện (Event Dispatch Thread – EDT)
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // đưa đoạn code bên trong vào hàng đợi EDT.Khi EDT sẵn sàng  thì thực thi
            new LoginView().setVisible(true);
        });
    }
}
