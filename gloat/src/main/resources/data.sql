INSERT INTO SKILL (skill_id, name) VALUES
  (1,'Java'),(2,'C++'), (3,'Python'), (4,'C');

INSERT INTO CANDIDATE (candidate_id, title) VALUES
  (1,'software developer'), (2,'software developer'), (3, 'software engineer'), (4, 'product manager');

INSERT INTO CANDIDATE_SKILL (candidate_id, skill_id) VALUES
  (1,1), (1,2), (2,2), (2,3);