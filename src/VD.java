import java.time.Instant ;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class VD {
    public static void main(String[] args) {
        Instant nowUtc = Instant.now();
        ZoneId userZone = ZoneId.systemDefault();
        ZonedDateTime localTime = nowUtc.atZone(userZone);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String showTime = localTime.format(f);
        System.out.println("Thời gian hiển thị: " + showTime);
    }
}

