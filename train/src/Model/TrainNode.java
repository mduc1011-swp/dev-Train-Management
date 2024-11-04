package Model;

public class TrainNode {
    private final Train train; // Thay đổi từ public sang private để bảo vệ tính encapsulation
    public TrainNode left, right; // Giữ nguyên vì bạn cần truy cập từ TrainTree

    public TrainNode(Train train) {
        this.train = train;
        this.left = null;
        this.right = null;
    }

    public Train getTrain() {
        return train;
    }

    public TrainNode getLeft() {
        return left;
    }

    public TrainNode getRight() {
        return right;
    }
}

