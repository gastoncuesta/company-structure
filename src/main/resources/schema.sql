DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         parent VARCHAR(20),
                         root VARCHAR(20),
                         height INT NOT NULL
);

SELECT * FROM MEMBER;