public class HorribleSteve {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        while ((i < 500) && Flik.isSameNumber(i, j)) {
            ++i;
            ++j;
        }
        System.out.println("i is " + i);
    }
}
