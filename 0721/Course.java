public class Course {
    private String code;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(capacity, 0);
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setCapacity(int capacity) {
        this.capacity = Math.max(capacity, 0);
    }

    // Returns true if enrollment succeeds, false otherwise (e.g. if capacity is reached)
    public boolean enroll() {
        if (capacity > 0 && enrolled >= capacity) {
            return false;
        }
        enrolled++;
        return true;
    }

    // Returns true if withdraw succeeds, false otherwise
    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (capacity <= 0) {
            return false;
        }
        return enrolled >= capacity;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | enrolled=" + enrolled + " | capacity=" + capacity;
    }
}
