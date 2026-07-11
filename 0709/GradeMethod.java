public class GradeMethod {
    public static void main(String[] args) {
        int javaScore = 85;
        int englishScore = 90;
        int mathScore = 78;

        double avg = calculateAverage(javaScore, englishScore, mathScore);
        String grade = getGrade(avg);

        System.out.println("Average score: " + avg);
        System.out.println("Grade: " + grade);
    }

    public static double calculateAverage(int javaScore, int englishScore, int mathScore) {
        return (javaScore + englishScore + mathScore) / 3.0;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
