class Exam implements Gradeable {
    @Override
    public void grade() {
        score = 40 + 60 * Math.random();
    }
    @Override
    public double getGrade() {
        return score;
    }
    private double score;
}
