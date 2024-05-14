CREATE TABLE Gamer (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(50) NOT NULL,
                        email VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL
);

CREATE TABLE Game (
                      id SERIAL PRIMARY KEY,
                      user_id INT NOT NULL,
                      place VARCHAR(100),
                      points INT,
                      date DATE,
                      time TIME,
                      FOREIGN KEY (user_id) REFERENCES Gamer(id)
);
