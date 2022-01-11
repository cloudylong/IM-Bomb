import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class IMBomb {
    // 轰炸次数
    private static int count = 1;

    public static void main(String[] args) throws Exception {
        setClipboard();
        boom();
    }

    /**
     * 设置系统剪切板内容
     */
    public static void setClipboard() {
        // 读取用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入轰炸内容：");
        String str = scanner.nextLine();
        System.out.print("请输入轰炸次数：");
        count = scanner.nextInt();

        // 设置剪切板内容
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection content = new StringSelection(str);
        clipboard.setContents(content, null);
    }

    /**
     * 判断操作系统，主要判断是否是 mac
     */
    public static String checkOS() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac"))
            return "mac";
        return "non-mac";
    }

    /**
     * 轰炸开始
     */
    public static void boom() throws Exception {
        Robot robot = new Robot();

        // 倒计时
        for (int i = 5; i > 0; i--) {
            System.out.println("Fire in the hole in " + i + "s");
            Thread.sleep(1000);
        }

        while (count-- > 0) {

            // 粘贴系统剪切板的内容
            String os = checkOS();
            if ("mac".equals(os)) {
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);

                robot.keyRelease(KeyEvent.VK_V); // 必须先释放 V 键，否则复制的内容会被字符 'v' 替代
                robot.keyRelease(KeyEvent.VK_META);
            } else {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);

                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }

            // 轰炸频率
            robot.delay(1000);

            // 发送消息
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }
}
