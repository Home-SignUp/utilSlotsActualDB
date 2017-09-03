package com.prepare;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @see https://habrahabr.ru/post/237043/
 *      https://gist.github.com/psayre23/c30a821239f4818b0709
 *
 * Вопросы и ответы на собеседовании по теме Java Collection Framework
 * -------------------------------------------------------------------
 * @see http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-1
 *      http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-2
 *      http://www.parshinpn.pro/content/voprosy-i-otvety-na-sobesedovanii-po-teme-java-collection-framework-chast-3
 */

public class FriendTest {

    @Test
    public void test0() {
        /*
         * @see https://www.programcreek.com/2013/09/top-10-questions-for-java-collections
         *      http://info.javarush.ru/translation/2014/06/26/Топ-10-вопросов-о-коллекциях-в-Java.html
         */
        int[] array = {1,2,3,4,5};
        List list = Arrays.asList(ArrayUtils.toObject(array));
        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()) {
            int i = itr.next();
            System.out.print( i + " " );
        }
    }

    @Test
    public void test1() {
        Friend friendA = new Friend("A");
        friendA.addFriends(new Friend("B"));

        Friend friendB = new Friend("B");
        friendB.addFriends(new Friend("C"));

        Collection<Friend> friends = new ArrayList<>();
        friends.add(friendA);
        friends.add(friendB);

        System.out.println( "res = " + func(friends) );
    }

    @Test
    public void test2() {
        Friend friendB = new Friend("B");
        friendB.addFriends(new Friend("C"));

        Friend friendA = new Friend("A");
        friendA.addFriends(friendB);

        System.out.println( "res = " + func(friendA) );
    }

    /*
     * Add  |  Get  |  Contains | Remove | Next
     */
    @Test
    public void test() {
        // Add  |  Get  |  Contains | Remove
        List<Integer> al = new ArrayList<>();
        al.add(100);
        al.add(200);
        al.get(0);
        al.contains(100);
        al.remove(0);

        List<Integer> ll = new LinkedList<>();
        ll.add(100);
        ll.add(200);
        ll.get(0);
        ll.contains(100);
        ll.remove(0);

        List cwal = new CopyOnWriteArrayList<>();
        cwal.add(100);
        cwal.add(200);
        cwal.get(0);
        cwal.contains(100);
        cwal.remove(0);

        //  Next | Remove
        Iterator<Integer> iAl = al.iterator();
        iAl.next();
        iAl.remove();

        Iterator<Integer> iLl = ll.iterator();
        iLl.next();
        iLl.remove();

        // Add  |  Next | Remove
        ListIterator liAl = al.listIterator();
//        liAl.next();
//        liAl.add(300);
//        liAl.remove();
    }

    private boolean func(Collection<Friend> friends){
        for (Friend friend : friends){
            Iterator<Friend> iFriend = friend.getFriends().iterator();
            Friend friendToFriend = iFriend.hasNext() ? iFriend.next() : null;
            if (friendToFriend!=null){
                switch (friend.getEmail()){
                    case "A":
//                        System.out.println( "A >> " + (friendToFriend.getEmail().equals("B") ? "OK" : "ERR") );
                        if (!friendToFriend.getEmail().equals("B")) return false;
                        break;
                    case "B":
//                        System.out.println( "B >> " + (friendToFriend.getEmail().equals("C") ? "OK" : "ERR") );
                        if (!friendToFriend.getEmail().equals("C")) return false;
                        break;
                }
            }
        }
        return true;
    }

    /*
     * #2-2. Pass for all along its children
     */
    private boolean func(final Friend friend) {
        if (friend!=null){
            Iterator<Friend> iCurrentFriend = friend.getFriends().iterator();
            while (iCurrentFriend.hasNext()){
                Friend currentFriend = iCurrentFriend.next();
                if (!check(currentFriend)) {
                    return false;
                }
                iCurrentFriend = currentFriend.getFriends().iterator();
            }
        }
        return true;
    }

    /*
     * #1-2. It is case what a condition for Friend have corresponded to its child
     */
    boolean check(Friend current) {
        Iterator<Friend> iFriendToFriend = current.getFriends().iterator();

        while (iFriendToFriend.hasNext()) {
            Friend friendToFriend = iFriendToFriend.next();

            switch (current.getEmail()) {
                case "A":
                    if (!friendToFriend.getEmail().equals("B")) return false;
                    break;
                case "B":
                    if (!friendToFriend.getEmail().equals("C")) return false;
                    break;
            }
        }
        return true;
    }

}
