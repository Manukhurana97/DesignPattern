package state;

import entities.User;


public interface TaskState {
    void newState(User user);

    void startProgress(User user);

    void inProgress(User user);

    void InSprintTesting(User user);

    void canceled(User user);

    void complete(User user);
}