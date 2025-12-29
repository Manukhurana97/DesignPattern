package enums;

public enum ConnectionType {
    PENDING("PENDING", "Follow request send successfully", "%s send you a friend request"),
    ACCEPTED("ACCEPTED", "%s Accepted your request", ""),
    REJECTED("REJECTED", "REJECTED your request", ""),
    WITHDRAWN("WITHDRAWN", "", "");

    public final String status;
    public final String followerMgs;
    public final String followeeMgs;


    ConnectionType(String status, String followerMgs, String followeeMgs) {
        this.status = status;
        this.followerMgs = followerMgs;
        this.followeeMgs = followeeMgs;
    }
}
