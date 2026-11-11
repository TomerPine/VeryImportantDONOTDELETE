import java.util.ArrayList;

/**
 * SharedData class used to hold and share data between threads or components.
 * This class stores an {@code ArrayList<Integer>} data structure, a win status array,
 * a flag, and an integer bound/value {@code b}.
 */
public class SharedData {
    private ArrayList<Integer> array;
    private boolean[] winArray;
    private boolean flag;
    private final int b;

    /**
     * Constructs a new SharedData object with the specified integer list and integer {@code b}.
     * Also initializes the {@code winArray} based on the size of the provided list.
     *
     * @param array the {@code ArrayList<Integer>} to be stored
     * @param b     the integer value {@code b} associated with the data
     */
    public SharedData(ArrayList<Integer> array, int b) {
        this.array = array;
        this.b = b;
        if (array != null) {
            this.winArray = new boolean[array.size()];
        } else {
            this.winArray = new boolean[0];
        }
    }

    /**
     * Returns the win array.
     *
     * @return the {@code boolean[]} representing the win array
     */
    public boolean[] getWinArray() { return winArray; }

    /**
     * Sets the win array and ensures its size matches the size of the ArrayList if applicable.
     *
     * @param winArray the new win array to set
     */
    public void setWinArray(boolean[] winArray) {
        this.winArray = winArray;
    }

    /**
     * Returns the ArrayList containing the integers.
     *
     * @return the {@code ArrayList<Integer>}
     */
    public ArrayList<Integer> getArray() { return array; }

    /**
     * Sets the ArrayList containing the integers and updates the win array size accordingly.
     *
     * @param array the new {@code ArrayList<Integer>} to set
     */
    public void setArray(ArrayList<Integer> array) {
        this.array = array;
        if (array != null) {
            this.winArray = new boolean[array.size()];
        } else {
            this.winArray = new boolean[0];
        }
    }

    /**
     * Returns the integer value {@code b}.
     *
     * @return the integer {@code b}
     */
    public int getB() { return b; }

    /**
     * Returns the boolean flag.
     *
     * @return the flag value
     */
    public boolean getFlag() { return flag; }

    /**
     * Sets the boolean flag.
     *
     * @param flag the new value for the flag
     */
    public void setFlag(boolean flag) { this.flag = flag; }
}
