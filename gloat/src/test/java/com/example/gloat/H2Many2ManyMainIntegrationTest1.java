package com.example.gloat;

import com.example.gloat.model.Candidate;
import com.example.gloat.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class H2Many2ManyMainIntegrationTest1 {

//    private HibernateUtil hibernateUtil;
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    void setUp() {
//        sessionFactory = HibernateUtil.createSessionFactory();
//        session = sessionFactory.openSession();
    }

    @Test
    public void givenData_whenInsert_thenCreatesMtoMRelationship() {
        String[] candidateData = { "software developer"};
        String[] skillData = { "Java" , "C" };
        Set<Skill> skills = new HashSet<>();

        for (String skill : skillData) {
            skills.add(new Skill(skill));
        }

        for (String candid : candidateData) {
            Candidate candidate = new Candidate(candid.split(" ")[0]);

            assertEquals(0, candidate.getSkills().size());
            candidate.setSkills(skills);
            session.persist(candidate);

            assertNotNull(candidate);
        }
    }

    @Test
    public void givenSession_whenRead_thenReturnsMtoMData() {
        @SuppressWarnings("unchecked")
        List<Candidate> candidateList = session.createQuery("FROM Candidate")
                .list();

        assertNotNull(candidateList);

        for(Candidate candidate : candidateList) {
            assertNotNull(candidate.getSkills());
        }
    }

}
