class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            

            row.add(1);
            

            for (int j = 1; j < i; j++) {
                int prevRowLeft = triangle.get(i - 1).get(j - 1);
                int prevRowRight = triangle.get(i - 1).get(j);
                row.add(prevRowLeft + prevRowRight);
            }

           
            if (i > 0) {
                row.add(1);
            }

            triangle.add(row);
        }

        return triangle;
    }
}
