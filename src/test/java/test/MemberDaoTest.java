package test;

import junit.framework.TestCase;
import nl.topicus.members.domain.dao.MemberDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Thijs Reeringh on 4/26/2016.
 */
public class MemberDaoTest extends TestCase {
    @Autowired
    private MemberDao memberDao;


    @Override
    public void setUp() {}

    @Test
    public void testRenderMyPage() {
        memberDao.getMembers();
    }
}
