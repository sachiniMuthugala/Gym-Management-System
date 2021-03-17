package sample;

import java.io.Serializable;
import java.util.Comparator;

public class NameComparator implements Comparator<DefaultMember> , Serializable {
    @Override
    public int compare(DefaultMember u1, DefaultMember u2) {
        return u1.getName().compareTo(u2.getName());
    }
}