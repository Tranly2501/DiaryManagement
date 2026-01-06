import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FileExporter {
    private static String getCurrentTimestamp() {
        ZonedDateTime localTime = Instant.now().atZone(ZoneId.systemDefault());
        return localTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static void exportWithAppend(String content) {
        FileDialog dialog = new FileDialog((Frame) null, "Chọn nơi lưu nhật ký", FileDialog.SAVE);
        dialog.setFile("NhatKy.txt");
        dialog.setVisible(true);

        String directory = dialog.getDirectory();
        String fileName = dialog.getFile();

        if (directory == null || fileName == null) {
            System.out.println("Người dùng đã hủy lưu file.");
            return;
        }

        if (!fileName.toLowerCase().endsWith(".txt")) {
            fileName += ".txt";
        }
        File file = new File(directory, fileName);

        String timePrefix = "[" + getCurrentTimestamp() + "] ";

        try (BufferedWriter writer = Files.newBufferedWriter(
                file.toPath(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND )) {
            writer.write(timePrefix + content);
            writer.newLine();
            System.out.println("Ghi thành công vào: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        exportWithAppend("xin chao");
    }
}