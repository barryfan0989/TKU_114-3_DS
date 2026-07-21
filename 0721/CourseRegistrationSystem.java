import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Add some initial courses
        courses.add(new Course("C01", "Java Programming", 3)); // small capacity to test boundary easily
        courses.add(new Course("C02", "Data Structures", 50));
        courses.add(new Course("C03", "Database Systems", 40));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== 選課管理系統 =====");
            System.out.println("1. 新增課程");
            System.out.println("2. 學生選課 (Register)");
            System.out.println("3. 學生退選 (Drop)");
            System.out.println("4. 刪除課程");
            System.out.println("5. 搜尋課程");
            System.out.println("6. 顯示所有課程與統計資訊");
            System.out.println("0. 結束程式");
            System.out.print("請輸入您的選擇 (0~6)：");

            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入有效數字！");
                scanner.next(); // clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCourse(scanner, courses);
                    break;
                case 2:
                    registerCourse(scanner, courses);
                    break;
                case 3:
                    dropCourse(scanner, courses);
                    break;
                case 4:
                    deleteCourse(scanner, courses);
                    break;
                case 5:
                    searchCourse(scanner, courses);
                    break;
                case 6:
                    displaySystemStatistics(courses);
                    break;
                case 0:
                    System.out.println("系統結束，感謝使用！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 0 到 6 之間的整數！");
                    break;
            }
        }
        scanner.close();
    }

    public static void addCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入欲新增課程代碼：");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("錯誤：課程代碼不能為空白！");
            return;
        }

        if (findCourse(courses, code) != null) {
            System.out.println("錯誤：此課程代碼已存在！");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：課程名稱不能為空白！");
            return;
        }

        int capacity;
        while (true) {
            System.out.print("請輸入選課容量 (必須大於 0)：");
            if (scanner.hasNextInt()) {
                capacity = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("錯誤：請輸入有效的整數容量！");
                scanner.next();
                scanner.nextLine();
            }
        }

        if (capacity <= 0) {
            System.out.println("錯誤：選課容量必須大於 0！");
            return;
        }

        courses.add(new Course(code, name, capacity));
        System.out.println("課程新增成功！");
    }

    public static void registerCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入欲選修之課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourse(courses, code);
        if (course == null) {
            System.out.println("錯誤：找不到該課程！");
            return;
        }

        if (course.enroll()) {
            System.out.println("選課成功！課程已登記人數：" + course.getEnrolled() + " / " + course.getCapacity());
        } else {
            System.out.println("選課失敗：該課程 \"" + course.getName() + "\" 已額滿！容量上限為 " + course.getCapacity() + " 人。");
        }
    }

    public static void dropCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入欲退選之課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourse(courses, code);
        if (course == null) {
            System.out.println("錯誤：找不到該課程！");
            return;
        }

        if (course.drop()) {
            System.out.println("退選成功！課程目前登記人數：" + course.getEnrolled() + " / " + course.getCapacity());
        } else {
            System.out.println("退選失敗：該課程目前尚無學生選課，人數不能為負數！");
        }
    }

    public static void deleteCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入欲刪除的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourse(courses, code);
        if (course == null) {
            System.out.println("錯誤：找不到該課程，無法刪除！");
            return;
        }

        courses.remove(course);
        System.out.println("刪除成功！已移除課程：" + course.getName());
    }

    public static void searchCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("請輸入欲搜尋的關鍵字 (代碼或名稱)：");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋內容不可為空白！");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(keyword) || course.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(course);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到任何符合關鍵字的課程。");
        }
        System.out.println("----------------");
    }

    public static void displaySystemStatistics(ArrayList<Course> courses) {
        System.out.println("\n=== 課程清單與選課狀態 ===");
        if (courses.isEmpty()) {
            System.out.println("(無任何課程資料)");
            System.out.println("-------------------------");
            System.out.println("總課程數：0 門");
            System.out.println("總選課人次：0 人次");
            System.out.println("額滿課程：無");
            return;
        }

        int totalCourses = courses.size();
        int totalEnrollment = 0;
        ArrayList<String> fullCourseNames = new ArrayList<>();

        for (Course course : courses) {
            System.out.println(course);
            totalEnrollment += course.getEnrolled();
            if (course.isFull()) {
                fullCourseNames.add(course.getName() + " (" + course.getCode() + ")");
            }
        }

        System.out.println("-------------------------");
        System.out.println("總課程數：" + totalCourses + " 門");
        System.out.println("總選課人次：" + totalEnrollment + " 人次");
        if (fullCourseNames.isEmpty()) {
            System.out.println("額滿課程：無");
        } else {
            System.out.println("額滿課程：" + String.join(", ", fullCourseNames));
        }
    }

    // Helper method to find a course by code case-insensitively
    public static Course findCourse(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
