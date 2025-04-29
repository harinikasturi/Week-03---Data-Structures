class Friend {
    int friendId;
    Friend next;

    public Friend(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class User {
    int userId;
    String name;
    int age;
    Friend friends;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = null;
        this.next = null;
    }
}

class SocialMedia {
    private User head;


    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
    }

    public void addFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found");
            return;
        }
        addFriendToUser(user1, userId2);
        addFriendToUser(user2, userId1);
    }

    private void addFriendToUser(User user, int friendId) {
        Friend newFriend = new Friend(friendId);
        newFriend.next = user.friends;
        user.friends = newFriend;
    }

    public void removeFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found");
            return;
        }
        removeFriendFromUser(user1, userId2);
        removeFriendFromUser(user2, userId1);
    }

    private void removeFriendFromUser(User user, int friendId) {
        if (user.friends == null) return;
        if (user.friends.friendId == friendId) {
            user.friends = user.friends.next;
            return;
        }
        Friend current = user.friends;
        while (current.next != null && current.next.friendId != friendId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found");
            return;
        }

        System.out.print("Mutual friends between " + user1.name + " and " + user2.name + ": ");

        Friend friend1 = user1.friends;
        while (friend1 != null) {
            Friend friend2 = user2.friends;
            while (friend2 != null) {
                if (friend1.friendId == friend2.friendId) {
                    User mutualFriend = findUser(friend1.friendId);
                    if (mutualFriend != null) {
                        System.out.print(mutualFriend.name + " ");
                    }
                    break;
                }
                friend2 = friend2.next;
            }
            friend1 = friend1.next;
        }
        System.out.println();
    }

    public void displayFriends(int userId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        System.out.print("Friends of " + user.name + ": ");
        Friend current = user.friends;
        while (current != null) {
            User friendUser = findUser(current.friendId);
            if (friendUser != null) {
                System.out.print(friendUser.name + " ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public User searchByName(String name) {
        User current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public User findUser(int userId) {
        User current = head;
        while (current != null) {
            if (current.userId == userId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int countFriends(int userId) {
        User user = findUser(userId);
        if (user == null) return 0;
        int count = 0;
        Friend current = user.friends;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void displayAllUsers() {
        User current = head;
        while (current != null) {
            System.out.println("User ID: " + current.userId + ", Name: " + current.name +
                    ", Age: " + current.age + ", Friends: " + countFriends(current.userId));
            current = current.next;
        }
    }
}