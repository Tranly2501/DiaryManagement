
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LOCALDATETIME {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        Locale currentLocale = Locale.getDefault();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(currentLocale);

        System.out.println("Định dạng tự động: " + now.format(formatter));
    }
}
