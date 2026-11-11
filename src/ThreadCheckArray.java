public class ThreadCheckArray implements Runnable {
    private boolean flag;
    private boolean[] winArray;
    SharedData sd;
    ArrayList<Integer> array;
    int b;

    /**
     * Constructs a new {@code ThreadCheckArray} task.
     * Initializes local copies of the array and target sum from the shared data,
     * and allocates the win array based on the size of the {@link ArrayList}.
     *
     * @param sd the {@link SharedData} object containing the shared state,
     *           including the {@code ArrayList<Integer>} and target sum {@code b}.
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = sd.getArrayList();
            b = sd.getB();
        }
        winArray = new boolean[array.size()];
    }

    /**
     * Recursive helper method to determine if a subset of the {@link ArrayList} sums to {@code b}.
     * Checks the shared flag to terminate early if another thread has already found a solution.
     *
     * @param n the current index/count of elements being considered from the {@link ArrayList}.
     * @param b the remaining target sum to achieve.
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        if (n == 1) {
            if(b == 0 || b == array.get(n - 1)) {
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
            if (b == array.get(n - 1)) winArray[n - 1] = true;
            return;
        }

        rec(n - 1, b - array.get(n - 1));
        if (flag) winArray[n-1] = true;
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        rec(n-1, b);
    }

    /**
     * Executes the subset sum check. Depending on the thread's name,
     * it initiates the recursive search either including or excluding the last element
     * of the {@link ArrayList}. Updates the shared data if a winning combination is found.
     */
    public void run() {
        if (array.size() != 1)
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.size() - 1, b - array.get(array.size() - 1));
            else
                rec(array.size() - 1, b);
        if (array.size() == 1)
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;
            synchronized (sd) { sd.setWinArray(winArray); }
        }
    }
}
